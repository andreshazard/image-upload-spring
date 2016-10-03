package hello.thumbnail;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by andreshazard on 10/3/16.
 */
public class ThumbnailTool {

    private int width;
    private int height;

    public ThumbnailTool() {

    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    public boolean isThumbnailNecessary() {
        if (this.width > 128 && this.height > 128) {
            return true;
        }
        return false;
    }

    public int getThumbnailWidthOne() {
        int thumbnail_width_one = (this.width * 32)/128;
        return thumbnail_width_one;
    }

    public int getThumbnailHeightOne() {
        int thumbnail_height_one = (this.height * 32)/128;
        return thumbnail_height_one;
    }

    public int getThumbnailWidthTwo() {
        int thumbnail_width_two = (this.width * 64)/128;
        return thumbnail_width_two;
    }

    public int getThumbnailHeightTwo() {
        int thumbnail_height_two = (this.height * 64)/128;
        return thumbnail_height_two;
    }

    public void createThumbnail(String fileName, int width, int height) throws IOException {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        img.createGraphics().drawImage(ImageIO.read(new File("upload-dir/" + fileName)).getScaledInstance(100, 100, Image.SCALE_SMOOTH),0,0,null);
        ImageIO.write(img, "jpg", new File("upload-dir/" + fileName + "-thumbnail" + Integer.toString(width) + "X" + Integer.toString(height)));
    }

    public void setDataFromMultipartFile(MultipartFile file) throws IOException {
        BufferedImage image = ImageIO.read(file.getInputStream());
        setWidth(image.getWidth());
        setHeight(image.getHeight());
    }

}
