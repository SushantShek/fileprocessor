package str.processor.impl;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import str.util.Constant;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TXTFileProcessorTest {

    TXTFileProcessor transform;

    @BeforeEach
    void setUp() {
        transform = new TXTFileProcessor();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void givenFileDir_findFileWithExtension_confirmItExist() {
        File dir = new File(Constant.PATH);
        File[] files = dir.listFiles((dir1, filename) -> filename.endsWith("." + "txt"));
        assertTrue(files.length > 0);
    }

    @Test
    void givenThatFileExist_ProcessTextFile_confirmIfTransfromedStringIsSameAsExpected() throws IOException {
        File dir = new File(Constant.PATH);
        File[] files = dir.listFiles((dir1, filename) -> filename.endsWith("." + "txt"));
        File transFile = files[0];
        String input = FileUtils.readFileToString(transFile, Constant.charset);

        String output = transform.processTXTFile(files, "customer", "client");
        assertNotNull(output);
        assert(input.contains("customer"));
        assert(!output.contains("customer"));
    }
}