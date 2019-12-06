package str.processor.impl;

import main.com.str.processor.ReplaceText;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class TXTFileProcessor implements ReplaceText {

    public String processTXTFile(File[] listOfFiles) throws IOException {
/*
        File folder = new File("/path/to/files");
        File[] listOfFiles = folder.listFiles();*/
        Charset charset = StandardCharsets.UTF_8;

        for (int i = 0; i < listOfFiles.length; i++) {
            File file = listOfFiles[i];
            if (file.isFile() && file.getName().endsWith(".txt")) {
                String content = FileUtils.readFileToString(file,charset);
                findValueAndReplace(content,"","");
            }
        }

        return null;
    }

    @Override
    public void findValueAndReplace(String content, String oldText, String newText) {

    }
}
