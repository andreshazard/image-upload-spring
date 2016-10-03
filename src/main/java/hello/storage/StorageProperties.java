package hello.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "upload-dir";

    private String zipLocation = "zip-dir";

    public String getLocation() {

        return location;
    }

    public String getZipLocation() {
        return zipLocation;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
