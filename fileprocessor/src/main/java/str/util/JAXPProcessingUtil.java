package str.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 * JAXP Processor is used to parse XML
 * And Later do the String processing to replace String within Attribute
 */
public class JAXPProcessingUtil {
    private final Document input;

    /**
     * Constructor to initalize Jaxp Processor
     * @param resourcePath of the XML File
     * @throws SAXException for DOm Parser
     * @throws IOException for read files
     * @throws ParserConfigurationException For config
     */
    public JAXPProcessingUtil(String resourcePath) throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        input = factory
                .newDocumentBuilder()
                .parse(resourcePath);
    }

    /**
     * This method actually replace the Text in XML file
     * with the alternate text
     * @param attribute Xpath Att
     * @param oldValue To be replaced
     * @param newValue replaced with
     * @return String
     * @throws XPathExpressionException Regular Expression to find attribute
     * @throws TransformerFactoryConfigurationError Config
     * @throws TransformerException Processor exception
     */
    public String modifyAttribute(String attribute, String oldValue, String newValue) throws XPathExpressionException, TransformerFactoryConfigurationError, TransformerException {
        XPath xpath = XPathFactory
                .newInstance()
                .newXPath();
        String expr = String.format("//*[contains(@%s, '%s')]", attribute, oldValue);
        NodeList nodes = (NodeList) xpath.evaluate(expr, input, XPathConstants.NODESET);

        for (int i = 0; i < nodes.getLength(); i++) {
            Element value = (Element) nodes.item(i);
            NamedNodeMap map = value.getAttributes();
            for(int k=0; k<map.getLength(); k++) {
                if (map.item(k).getNodeValue()!=null) {
                    map.item(k).setNodeValue(map.item(k).getNodeValue().replace(oldValue,newValue));
                }
            }
        }

        TransformerFactory factory = TransformerFactory.newInstance();
        factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        Transformer xformer = factory.newTransformer();
        xformer.setOutputProperty(OutputKeys.INDENT, "yes");
        Writer output = new StringWriter();
        xformer.transform(new DOMSource(input), new StreamResult(output));
        return output.toString();
    }
}
