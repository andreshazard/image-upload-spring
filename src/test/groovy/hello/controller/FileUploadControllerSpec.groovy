package hello.controller

import com.blogspot.toomuchcoding.spock.subjcollabs.Collaborator
import com.blogspot.toomuchcoding.spock.subjcollabs.Subject
import hello.storage.StorageService
import org.hamcrest.Matchers
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import java.nio.file.Paths
import java.util.stream.Stream

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

class FileUploadControllerSpec extends  Specification {

    @Subject
    FileUploadController fileUploadController = new FileUploadController()

    def mvc = MockMvcBuilders.standaloneSetup(fileUploadController).build()

    @Collaborator
    StorageService storageService = Mock()

    def "test that path / lists all files"() {

        when: "storageService find two files on the directory"
        storageService.loadAll() >> Stream.of(Paths.get("first.txt"), Paths.get("second.txt"))

        then: "the / path should return a list containing both of those files"
        mvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(model().attribute("files",
                Matchers.contains("http://localhost/files/first.txt", "http://localhost/files/second.txt")));
    }

    def "test that file uploaded is saved"() {

        given: "a text file with any content"
        MockMultipartFile multipartFile =
                new MockMultipartFile("file", "test.txt", "text/plain", "Any Content".getBytes());

        when: "the upload is requested from the page"
        mvc.perform(fileUpload("/").file(multipartFile))
                .andExpect(status().isFound())
                .andExpect(header().string("Location", "/"));

        then: "store is called on the storageService with the file as parameter"
        1 * storageService.store(multipartFile)

    }

}