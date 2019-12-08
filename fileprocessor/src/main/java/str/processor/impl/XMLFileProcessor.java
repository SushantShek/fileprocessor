package str.processor.impl;

import str.util.Constant;
import str.util.JAXPProcessingUtil;

import java.io.File;

/**
 * Process XML files and replace strings
 */
public class XMLFileProcessor {

    public String processXMLFile(File[] listOfFiles, String old, String replace) {
        String output = "";
        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().endsWith("." + Constant.XML_FILE)) {
                try {
                    JAXPProcessingUtil processor = new JAXPProcessingUtil(file.getPath());
                    output = processor.modifyAttribute("*", old, replace);
                    System.out.println("============XML===============");
                    System.out.println(output);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return output;
    }
}