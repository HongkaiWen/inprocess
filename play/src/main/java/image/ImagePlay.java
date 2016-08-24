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
import java.util.Iterator;
import java.util.zip.Adler32;
import java.util.zip.Checksum;

/**
 * Created by player on 2016/7/20.
 */
public class ImagePlay {
    public static void main(String args[]) throws IOException, JpegProcessingException {
        Checksum checksum = FileUtils.checksum(new File("D:\\华为手机备份\\backup_PE-TL00M_2016-06-10-10-23-38\\Image\\IMG_20151027_133308.jpg"), new Adler32());
        System.out.println(checksum.getValue());

        Metadata metadata = JpegMetadataReader.readMetadata(new File("D:\\华为手机备份\\backup_PE-TL00M_2016-06-10-10-23-38\\Image\\IMG_20151027_133308.jpg"));
        Directory exif = metadata.getDirectory(ExifIFD0Directory.class);
        for (Tag tag : exif.getTags()) {
            System.out.println(tag);
        }

//        String path = "D:\\华为手机备份\\backup_PE-TL00M_2016-06-10-10-23-38\\Image";
//        Iterator<File> fileIterator = FileUtils.iterateFiles(new File(path), fileFilter, dirFilter);
//        while(fileIterator.hasNext()){
//            File next = fileIterator.next();
//            if(next.isDirectory()){
//                continue;
//            }
//            System.out.println(next.getName());
//        }
    }


    private static IOFileFilter fileFilter = new IOFileFilter() {
        public boolean accept(File file) {
            int fileSize = (int) (file.length()/1024/1024);
            return fileSize > 1;
        }

        public boolean accept(File dir, String name) {
            return true;
        }
    };

    private static IOFileFilter dirFilter = new IOFileFilter() {
        public boolean accept(File file) {
            return true;
        }

        public boolean accept(File dir, String name) {
            return true;
        }
    };


}
