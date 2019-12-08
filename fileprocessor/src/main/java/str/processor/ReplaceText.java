package str.processor;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;

public interface ReplaceText {

    void findValueAndReplace(String content, String oldText, String newText) throws ParserConfigurationException, XPathExpressionException, IOException, SAXException, TransformerException;
}
