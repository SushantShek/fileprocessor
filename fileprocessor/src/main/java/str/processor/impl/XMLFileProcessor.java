package str.processor.impl;

import org.xml.sax.SAXException;
import str.processor.service.ProcessTransformation;
import str.util.Constant;
import str.util.JAXPProcessingUtil;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Process XML files and replace strings
 */
public class XMLFileProcessor implements ProcessTransformation {

    private static final Logger LOG = Logger.getLogger(XMLFileProcessor.class.getName());

    private File[] listOfFile;
    private String oldValue;
    private String newValue;

    public XMLFileProcessor(File[] listOfFiles, String old, String replace) {
        this.listOfFile = listOfFiles;
        this.oldValue = old;
        this.newValue = replace;
    }

    @Override
    public String processText() {
        String output = "";
        for (File file : listOfFile) {
            if (file.isFile() && file.getName().endsWith("." + Constant.XML_FILE)) {
                try {
                    JAXPProcessingUtil processor = new JAXPProcessingUtil(file.getPath());
                    output = processor.modifyAttribute("*", oldValue, newValue);
                    output = output.replaceAll("(?m)^[ \t]*\r?\n", "");
                    LOG.info("============XML===============");
                    System.out.println(output);
                } catch (XPathExpressionException | TransformerFactoryConfigurationError | TransformerException | SAXException | IOException | ParserConfigurationException
                        ex) {
                    LOG.severe("Exception in XML Transformation" + ex);
                }
            }
        }
        return output;
    }
}