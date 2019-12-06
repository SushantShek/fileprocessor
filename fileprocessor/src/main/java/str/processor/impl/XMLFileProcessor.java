package str.processor.impl;

import main.com.str.processor.ReplaceText;
import org.apache.commons.io.FileUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

//import static com.ibm.jvm.format.TraceArgs.outputFile;

public class XMLFileProcessor implements ReplaceText {
//    private Object String;

    public String processXMLFile(File[] listOfFiles, String old, String replace) throws IOException, ParserConfigurationException, XPathExpressionException, SAXException {
        Charset charset = StandardCharsets.UTF_8;
        for (int i = 0; i < listOfFiles.length; i++) {
            File file = listOfFiles[i];
            if (file.isFile() && file.getName().endsWith(".xml")) {
                String content = FileUtils.readFileToString(file, charset);

                Document doc = DocumentBuilderFactory.newInstance()
                        .newDocumentBuilder().parse(new InputSource(String.valueOf(file)));

                try {
                    findValueAndReplace(content, "aa", "bb");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            return null;
        }

     /*   DocumentBuilder builder = JOOX.builder();
        Document input = builder.parse(resourcePath);
        Match $ = $(input);

        $.find("to")
                .get()
                .stream()
                .forEach(e -> e.setAttribute(attribute, newValue));*/

//        }
    /*
    static String inputFile = "./beans.xml";
static String outputFile = "./beans_new.xml";

// 1- Build the doc from the XML file
Document doc = DocumentBuilderFactory.newInstance()
            .newDocumentBuilder().parse(new InputSource(inputFile));

// 2- Locate the node(s) with xpath
XPath xpath = XPathFactory.newInstance().newXPath();
NodeList nodes = (NodeList)xpath.evaluate("//*[contains(@value, '!Here')]",
                                          doc, XPathConstants.NODESET);

// 3- Make the change on the selected nodes
for (int idx = 0; idx < nodes.getLength(); idx++) {
    Node value = nodes.item(idx).getAttributes().getNamedItem("value");
    String val = value.getNodeValue();
    value.setNodeValue(val.replaceAll("!Here", "What?"));
}

// 4- Save the result to a new XML doc
Transformer xformer = TransformerFactory.newInstance().newTransformer();
xformer.transform(new DOMSource(doc), new StreamResult(new File(outputFile))); */
        return null;
    }

    @Override
    public void findValueAndReplace(String content, String oldText, String newText) throws ParserConfigurationException, XPathExpressionException, IOException, SAXException {

        Document doc = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder().parse(new InputSource(new ByteArrayInputStream(content.getBytes())));

        XPath xpath = XPathFactory.newInstance().newXPath();
        NodeList nodes = (NodeList) xpath.evaluate
                ("//*[contains(@value, 'oldText')]", doc, XPathConstants.NODESET);

        for (int idx = 0; idx < nodes.getLength(); idx++) {
            Node value = nodes.item(idx).getAttributes().getNamedItem("value");
            String val = value.getNodeValue();
            value.setNodeValue(val.replaceAll(oldText, newText));

        }
    }
}