package hello.zip;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.core.ZipFile;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by andreshazard on 10/2/16.
 */
public class ZipTool {

    public void unzip(String zipName) {
        String source = "zip-dir/" + zipName;
        String destination = "upload-dir";
        String password = "password";

        try {
            ZipFile zipFile = new ZipFile(source);
            if (zipFile.isEncrypted()) {
                zipFile.setPassword(password);
            }
            zipFile.extractAll(destination);
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }

    public boolean isFileAZip(MultipartFile file) {
        String contentType = file.getContentType();
        if (contentType.equals("application/zip")) {
            return true;
        }
        return false;
    }
}
