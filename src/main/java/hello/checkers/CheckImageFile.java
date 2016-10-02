package hello.checkers;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by andreshazard on 10/1/16.
 */
public class CheckImageFile {

    public boolean isFileAnImage(MultipartFile file) throws IOException {
        String contentType = file.getContentType();
        if (contentType.equals("image/pjpeg") || contentType.equals("image/jpeg") || contentType.equals("image/png")
                || contentType.equals("image/gif") || contentType.equals("image/bmp")
                || contentType.equals("image/x-png")) {
            return true;
        }
        return false;
    }

}
