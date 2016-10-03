package hello.thumbnail

import com.blogspot.toomuchcoding.spock.subjcollabs.Subject
import spock.lang.Specification

/**
 * Created by andreshazard on 10/3/16.
 */
class ThumbnailToolSpec extends Specification  {

    @Subject
    ThumbnailTool thumbnailTool = new ThumbnailTool();

    def "test isThumbnailNecessary method when width and height are greater than 128"() {

        given: "a width and height that are grater than 128"
        thumbnailTool.setWidth(160)
        thumbnailTool.setHeight(160)

        when: "calling isThumbnailNecessary method"
        def value = thumbnailTool.isThumbnailNecessary()

        then: "it should return true"
        assert value == true
    }

    def "test isThumbnailNecessary method when width and height are less than 128"() {

        given: "a width and height that are less than 128"
        thumbnailTool.setWidth(100)
        thumbnailTool.setHeight(100)

        when: "calling isThumbnailNecessary method"
        def value = thumbnailTool.isThumbnailNecessary()

        then: "it should return false"
        assert value == false
    }

    def "test getThumbnailWidthOne method"() {

        given: "a width of 128"
        thumbnailTool.setWidth(128)

        when: "calling the method"
        def value = thumbnailTool.thumbnailWidthOne

        then: "it should return 32"
        assert value == 32
    }

    def "test getThumbnailWidthTwo method"() {

        given: "a width of 128"
        thumbnailTool.setWidth(128)

        when: "calling the method"
        def value = thumbnailTool.thumbnailWidthTwo

        then: "it should return 64"
        assert value == 64
    }

    def "test getThumbnailHeightOne method"() {

        given: "a height of 128"
        thumbnailTool.setHeight(128)

        when: "calling the method"
        def value = thumbnailTool.thumbnailHeightOne

        then: "it should return 32"
        assert value == 32
    }

    def "test getThumbnailHeightTwo method"() {

        given: "a height of 128"
        thumbnailTool.setHeight(128)

        when: "calling the method"
        def value = thumbnailTool.thumbnailHeightTwo

        then: "it should return 64"
        assert value == 64
    }

}
