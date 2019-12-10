package str.processor.impl;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import str.util.Constant;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TXTFileProcessorTest {


    @Test
    void givenFileDir_findFileWithExtension_confirmItExist() {
        File dir = new File(Constant.PATH);
        File[] files = dir.listFiles((dir1, filename) -> filename.endsWith("." + "txt"));
        assert files != null;
        assertTrue(files.length > 0);
    }

    @Test
    void givenThatFileExist_ProcessTextFile_confirmIfTransfromedStringIsSameAsExpected() throws IOException {
        File dir = new File(Constant.PATH);
        File[] files = dir.listFiles((dir1, filename) -> filename.endsWith("." + "txt"));
        assert files != null;
        File transFile = files[0];
        String input = FileUtils.readFileToString(transFile, Constant.charset);

        TXTFileProcessor transform = new TXTFileProcessor(files, "customer", "client");
        String output = transform.processText();
        assertNotNull(output);
        assert (input.contains("customer"));
        assert (!output.contains("customer"));
    }
}