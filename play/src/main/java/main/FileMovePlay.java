package main;

import java.io.IOException;
import java.nio.file.*;

/**
 * Created by hongkai on 2016/1/26.
 */
public class FileMovePlay {

    public static void main(String args[]) throws IOException {
        Path source = FileSystems.getDefault().getPath("d:/temp/temp.7z");
        Files.move(source, source.resolveSibling("d:/temp/temp.7z.bak"), StandardCopyOption.REPLACE_EXISTING);
    }
}
