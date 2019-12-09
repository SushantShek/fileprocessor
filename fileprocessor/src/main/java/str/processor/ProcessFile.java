package str.processor;

import str.processor.impl.TXTFileProcessor;
import str.processor.impl.XMLFileProcessor;
import str.util.Constant;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class ProcessFile {

    private final static Logger LOG = Logger.getLogger(ProcessFile.class.getName());
    private XMLFileProcessor xmlProcessor = new XMLFileProcessor();
    private TXTFileProcessor txtProcessor = new TXTFileProcessor();

    /**
     * Takes in parameters
     *
     * @param fileType TXt or XML
     * @param old      String to be replace
     * @param replace  String replaced by
     */
    public void getInputFile(String fileType, String old, String replace) {



        File[] listOfFiles = finder(fileType);
        try {
            if (fileType.equalsIgnoreCase(Constant.XML_FILE)) {
                xmlProcessor.processXMLFile(listOfFiles, old, replace);
            } else if (fileType.equalsIgnoreCase(Constant.TEXT_FILE)) {
                txtProcessor.processTXTFile(listOfFiles, old, replace);
            } else {
                LOG.info("Invalid File Type");
                throw new IllegalArgumentException("File type not supported");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Finder methods takes filetype
     * and filter files of particular type
     *
     * @param fileType Finds the
     * @return List of files
     */
    private File[] finder(String fileType) {
        File dir = new File(Constant.PATH);
        return dir.listFiles((dir1, filename) -> filename.endsWith("." + fileType));
    }
}
