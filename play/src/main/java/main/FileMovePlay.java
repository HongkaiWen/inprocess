package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by hongkai on 2016/1/26.
 */
public class FileMovePlay {

    public static void main(String args[]) throws IOException {
        String sourceFilePath = "d:/temp/temp.7z";
        Executors.newSingleThreadExecutor().execute(new ReadFileTask(new File(sourceFilePath)));
        Path source = FileSystems.getDefault().getPath(sourceFilePath);
        Files.move(source, source.resolveSibling("d:/temp/temp.7z.bak"), StandardCopyOption.REPLACE_EXISTING);
    }


    private static class ReadFileTask implements Runnable{

        private File targetFile;

        public ReadFileTask(File targetFile){
            this.targetFile = targetFile;
        }

        @Override
        public void run() {
            FileInputStream in = null;
            try {
                in = new FileInputStream(targetFile);
                byte[] buffer = new byte[128];
                int read = -1;
                while((read = in.read(buffer)) != -1){
                    TimeUnit.SECONDS.sleep(2);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if(in != null){
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}


