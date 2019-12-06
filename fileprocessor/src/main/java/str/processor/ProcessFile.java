package str.processor;

import org.xml.sax.SAXException;
import str.processor.impl.TXTFileProcessor;
import str.processor.impl.XMLFileProcessor;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Path;

public class ProcessFile {

   private XMLFileProcessor xmlProcessor = new XMLFileProcessor();
   private TXTFileProcessor txtProcessor = new TXTFileProcessor();

    public String getInputFile(String fileType,String old,String replace){

//        final Resource resource = new ClassPathResource(path);
//        Path path =Paths.
        FilenameFilter filter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        };
        File folder = new File("/resources");
        File[] listOfFiles = folder.listFiles();
        String isSuccess;

        try {
            if (fileType.equalsIgnoreCase("xml")) {
                isSuccess = xmlProcessor.processXMLFile(listOfFiles,old,replace);
            } else if (fileType.equalsIgnoreCase("txt")) {
                isSuccess = txtProcessor.processTXTFile(listOfFiles);
            } else {
                System.out.println("Invalid File Type");
                throw new IllegalArgumentException("File type not supported");
            }
        }catch (IOException | ParserConfigurationException |  XPathExpressionException | SAXException e) {
            throw new RuntimeException(e);
        }
        return isSuccess;
    }

   /* public String getInputFile(String type, String search, String replace) {
    }*/
}
