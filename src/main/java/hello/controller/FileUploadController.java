package hello.controller;

/**
 * Created by andreshazard on 10/1/16.
 */

import hello.storage.StorageFileNotFoundException;
import hello.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.stream.Collectors;

@Controller
public class FileUploadController {

    private final static String IP_IMAGES_SERVER = "http://10.70.1.82:8000/" ;
    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService
                .loadAll()
                .map(path ->
                        MvcUriComponentsBuilder
                                .fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString(),
                                new ModelMap())
                                .build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    public String serveFile(@PathVariable String filename, ModelMap model) throws IOException, RuntimeException {
        try {
            Resource file = storageService.loadAsResource(filename);
            model.put(filename, file.getURL().getPath());
            model.put("ip", FileUploadController.IP_IMAGES_SERVER);
            return "viewImage";
        }
        catch (RuntimeException e) { // file does not exist
            model.put("existError", true);
            return "viewImage";
        }
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        try {
            storageService.store(file);
            redirectAttributes.addFlashAttribute("message", "You successfully uploaded, Maje! " + file.getOriginalFilename() + "!");
            return "redirect:/";
        }catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("message", "Not an Image file, Pasmado!");
            return "redirect:/";
        }catch (IndexOutOfBoundsException e) {
            redirectAttributes.addFlashAttribute("message", "Image To Big, Culero!");
            return "redirect:/";

        }
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
