package str.processor.impl;

import org.apache.commons.io.FileUtils;
import str.processor.ReplaceText;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class TXTFileProcessor implements ReplaceText {

    Charset charset = StandardCharsets.UTF_8;

    public String processTXTFile(File[] listOfFiles, String oldText, String newText) throws IOException {

        for (int i = 0; i < listOfFiles.length; i++) {
            File file = listOfFiles[i];
            if (file.isFile() && file.getName().endsWith(".txt")) {
                String content = FileUtils.readFileToString(file, charset);
                findValueAndReplace(content, oldText, newText);
            }
        }
        return null;
    }

    @Override
    public void findValueAndReplace(String content, String oldText, String newText) {

        content = content.replaceAll(oldText, newText);
        try {
            System.out.println("========= Output =========");
            System.out.println(content);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
