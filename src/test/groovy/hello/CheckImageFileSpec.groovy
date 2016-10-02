package hello

import com.blogspot.toomuchcoding.spock.subjcollabs.Collaborator
import com.blogspot.toomuchcoding.spock.subjcollabs.Subject
import hello.checkers.CheckImageFile
import org.springframework.web.multipart.MultipartFile
import spock.lang.Specification

/**
 * Created by andreshazard on 10/1/16.
 */
class CheckImageFileSpec extends Specification {

    @Subject
    CheckImageFile checkImageFile = new CheckImageFile();

    @Collaborator
    MultipartFile multipartFile = Stub();

    def "test that isFileAnImage with a png image file returns true"(){
        given: "An png image file"
        multipartFile.getContentType() >> "image/png"

        when: "isFileAnImage is called"
        def value = checkImageFile.isFileAnImage(multipartFile)

        then: "it will return true"
        assert value == true
    }

    def "test that isFileAnImage with a jpeg image file returns true"(){
        given: "An png image file"
        multipartFile.getContentType() >> "image/jpeg"

        when: "isFileAnImage is called"
        def value = checkImageFile.isFileAnImage(multipartFile)

        then: "it will return true"
        assert value == true
    }

    def "test that isFileAnImage with a gif image file returns true"(){
        given: "An png image file"
        multipartFile.getContentType() >> "image/gif"

        when: "isFileAnImage is called"
        def value = checkImageFile.isFileAnImage(multipartFile)

        then: "it will return true"
        assert value == true
    }

    def "test that isFileAnImage with a bmp image file returns true"(){
        given: "An png image file"
        multipartFile.getContentType() >> "image/bmp"

        when: "isFileAnImage is called"
        def value = checkImageFile.isFileAnImage(multipartFile)

        then: "it will return true"
        assert value == true
    }

    def "test that isFileAnImage with a non image file returns false"(){
        given: "An non image file"
        multipartFile.getContentType() >> "application/tar"

        when: "isFileAnImage is called"
        def value = checkImageFile.isFileAnImage(multipartFile)

        then: "it will return false"
        assert value == false
    }

}
