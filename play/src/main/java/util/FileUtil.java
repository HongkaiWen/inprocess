package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by hongkai on 2016/6/27.
 */
public class FileUtil {
    public static void readFileByLine(String path, LineHandler handler) throws IOException {
        File target = new File(path);
        BufferedReader reader = new BufferedReader(new FileReader(target));
        try{
            String line = null;
            int lineNumber = 0;
            while((line = reader.readLine()) != null){
                handler.handler(line, lineNumber);
                lineNumber ++;
            }
        }finally {
            reader.close();
        }
    }
}
