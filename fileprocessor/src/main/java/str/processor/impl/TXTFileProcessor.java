package str.processor.impl;

import org.apache.commons.io.FileUtils;
import str.util.Constant;

import java.io.File;
import java.io.IOException;

/**
 * Process Text files and replace string
 */
public class TXTFileProcessor {

    public String processTXTFile(File[] listOfFiles, String oldText, String newText) throws IOException {
        String output = "";
        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().endsWith("." + Constant.TEXT_FILE)) {
                String content = FileUtils.readFileToString(file, Constant.charset);
                output = findValueAndReplace(content, oldText, newText);
            }
        }
        return output;
    }

    /**
     * Replace the values
     *
     * @param content dom content
     * @param oldText to be replace
     * @param newText replace with
     * @return processed text
     */
    private String findValueAndReplace(String content, String oldText, String newText) {
        content = content.replaceAll(oldText, newText);
        System.out.println("========= Output =========");
        System.out.println(content);
        return content;
    }
}
