package str.processor;

import str.processor.impl.TXTFileProcessor;
import str.processor.impl.XMLFileProcessor;

import java.io.File;
import java.io.IOException;

public class ProcessFile {

    private XMLFileProcessor xmlProcessor = new XMLFileProcessor();
    private TXTFileProcessor txtProcessor = new TXTFileProcessor();

    public void getInputFile(String fileType, String old, String replace) {

        File[] listOfFiles = finder(".\\resource", fileType);//folder.listFiles();
        System.out.println("listOfFiles = " + listOfFiles.length);

        try {
            if (fileType.equalsIgnoreCase("xml")) {
                xmlProcessor.processXMLFile(listOfFiles, old, replace);
            } else if (fileType.equalsIgnoreCase("txt")) {
                txtProcessor.processTXTFile(listOfFiles, old, replace);
            } else {
                System.out.println("Invalid File Type");
                throw new IllegalArgumentException("File type not supported");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public File[] finder(String dirName, String fileType) {
        File dir = new File(dirName);
        return dir.listFiles((dir1, filename) -> filename.endsWith("." + fileType));
    }
}
