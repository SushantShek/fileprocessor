package str.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import static org.xmlunit.assertj.XmlAssert.assertThat;

class JAXPProcessingUtilTest {

    String filePath;

    @BeforeEach
    void setUp() {
        File dir = new File(Constant.PATH);
        File[] files = dir.listFiles((dir1, filename) -> filename.endsWith("." + "xml"));
        assert files != null;
        filePath = files[0].getPath();
    }

    @Test
    void givenXMLContainsAttribute_modifyAttribute_returnTransformedXML() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerException {
        JAXPProcessingUtil transformer = new JAXPProcessingUtil(filePath);
        String attribute = "*";
        String oldValue = "trace";
        String newValue = "error";

        String result = transformer.modifyAttribute(attribute, oldValue, newValue);

        assertThat(result).hasXPath("//*[contains(@*, 'error')]");
    }

    @Test
    void givenTwoXMLFiles_modifyAttribute_compareOutputIsSameAsExpected() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerException {

        JAXPProcessingUtil transformer = new JAXPProcessingUtil(filePath);
        String attribute = "*";
        String oldValue = "trace";
        String newValue = "error";
        File folder = new File("src/test/resources/xml/");
        File[] file = folder.listFiles();
        assert file != null;
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file[0]);
        TransformerFactory factory = TransformerFactory.newInstance();
        factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        Transformer xformer = factory.newTransformer();
        xformer.setOutputProperty(OutputKeys.INDENT, "yes");
        Writer output = new StringWriter();
        xformer.transform(new DOMSource(doc), new StreamResult(output));
        String expectedXml = output.toString();

        String result = transformer.modifyAttribute(attribute, oldValue, newValue).replaceAll("(?m)^[ \t]*\r?\n", "");

        assertThat(result)
                .and(expectedXml)
                .areSimilar();
    }
}