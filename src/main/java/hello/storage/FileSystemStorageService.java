package hello.storage;

import hello.checkers.CheckImageFile;
import hello.thumbnail.ThumbnailTool;
import hello.zip.ZipTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;
    private final Path zipLocation;
    private final CheckImageFile checkImageFile = new CheckImageFile();
    private final ZipTool zipTool = new ZipTool();
    private final ThumbnailTool thumbnailTool = new ThumbnailTool();

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
        this.zipLocation = Paths.get(properties.getZipLocation());
    }

    @Override
    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new IllegalArgumentException();
            }
            else if (zipTool.isFileAZip(file)) {
                Files.copy(file.getInputStream(), this.zipLocation.resolve(file.getOriginalFilename()));
                zipTool.unzip(file.getOriginalFilename());
                return;
            }
            else if (checkImageFile.isFileToBig(file)) {
                throw new IndexOutOfBoundsException();
            }
            else if (checkImageFile.isFileAnImage(file)) {
                Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
                thumbnailTool.setDataFromMultipartFile(file);
                if (thumbnailTool.isThumbnailNecessary()) {

                    thumbnailTool.createThumbnail(file.getOriginalFilename(), thumbnailTool.getThumbnailWidthOne(),
                            thumbnailTool.getThumbnailHeightOne());

                    thumbnailTool.createThumbnail(file.getOriginalFilename(), thumbnailTool.getThumbnailWidthTwo(),
                            thumbnailTool.getThumbnailHeightTwo());
                }

                return;
            }
            throw new IllegalArgumentException();
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(path -> this.rootLocation.relativize(path));
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}
