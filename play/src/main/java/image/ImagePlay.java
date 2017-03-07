package image;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifIFD0Directory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.zip.Adler32;
import java.util.zip.Checksum;

/**
 * Created by player on 2016/7/20.
 */
public class ImagePlay {

    private final static String metadata = "e:/imagetidying";

    private static List<String> getUploadedMd5ByDate(){
        return null;
    }

    public static void main(String args[]) throws IOException, JpegProcessingException {



        Checksum checksum = FileUtils.checksum(new File("E:/私人文件夹/照片170114/IMG_20160721_204518.jpg"), new Adler32());
        System.out.println(checksum.getValue());

        Metadata metadata = JpegMetadataReader.readMetadata(new File("E:/私人文件夹/照片170114/IMG_20160721_204518.jpg"));
        Directory exif = metadata.getDirectory(ExifIFD0Directory.class);
        for (Tag tag : exif.getTags()) {
                System.out.println(tag);
        }

    }



}
