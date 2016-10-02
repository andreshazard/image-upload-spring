package hello.zip

import com.blogspot.toomuchcoding.spock.subjcollabs.Collaborator
import com.blogspot.toomuchcoding.spock.subjcollabs.Subject
import org.springframework.web.multipart.MultipartFile
import spock.lang.Specification

/**
 * Created by andreshazard on 10/2/16.
 */
class ZipToolSpec extends Specification {

    @Subject
    ZipTool zipTool = new ZipTool();

    @Collaborator
    MultipartFile multipartFile = Stub();

    def "test isFileAZip method with a valid zip file"() {

        given: "a zip file"
        multipartFile.getContentType() >> "application/zip"

        when: "isFileAZip is call"
        def value = zipTool.isFileAZip(multipartFile)

        then: "it will return true"
        assert value == true
    }


    def "test isFileAZip method with a non zip file"() {

        given: "a non file"
        multipartFile.getContentType() >> "image/gif"

        when: "isFileAZip is call"
        def value = zipTool.isFileAZip(multipartFile)

        then: "it will return false"
        assert value == false
    }
}
