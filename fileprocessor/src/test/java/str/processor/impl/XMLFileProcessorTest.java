package str.processor.impl;

import org.junit.jupiter.api.Test;
import str.util.Constant;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

class XMLFileProcessorTest {

    @Test
    void givenFileDir_findFileWithExtension_confirmItExist() {
        File dir = new File(Constant.PATH);
        File[] files = dir.listFiles((dir1, filename) -> filename.endsWith("." + "xml"));
        assertTrue(files.length > 0);

        assert (files[0].getName().contains("xml"));
    }

    @Test
    void givenXmlFile_whenCallProcess_returnString() {
        File dir = new File(Constant.PATH);
        File[] files = dir.listFiles((dir1, filename) -> filename.endsWith("." + "xml"));

        XMLFileProcessor xmlProcessor = new XMLFileProcessor();
        String output = xmlProcessor.processXMLFile(files, "trace", "error");

        assert(output.contains("error"));

    }
}