package str.processor;

import org.xml.sax.SAXException;
import str.processor.impl.TXTFileProcessor;
import str.processor.impl.XMLFileProcessor;

import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class ProcessFile {

   private XMLFileProcessor xmlProcessor = new XMLFileProcessor();
   private TXTFileProcessor txtProcessor = new TXTFileProcessor();

    public String getInputFile(String fileType,String old,String replace) {

//        final Resource resource = new ClassPathResource(path);
//        Path path = Paths.get("\\resources");
       /* String path = getClass()
                .getResource("/resources")
                .toString();*/
//        FilenameFilter filter = new FilenameFilter() {
//            public boolean accept(File dir, String name) {
//                return name.endsWith(".txt");
//            }
//        }

 /*       try {
            Files.newDirectoryStream(Paths.get("."),
                    path -> path.toString().endsWith(".xml"))
                    .forEach(System.out::println);
            *//*Files.newDirectoryStream(Paths.get("."), path -> path.toFile().isFile())
                    .forEach(System.out::println);*//*
        }catch(Exception ex){
        ex.printStackTrace();
        }*/
//        System.out.println("Path :: " + path);
        File[] listOfFiles = finder(".\\resource",fileType);//folder.listFiles();
        System.out.println("listOfFiles = " + listOfFiles.length);
        String isSuccess;

        try {
            if (fileType.equalsIgnoreCase("xml")) {
                isSuccess = xmlProcessor.processXMLFile(listOfFiles,old,replace);
            } else if (fileType.equalsIgnoreCase("txt")) {
                isSuccess = txtProcessor.processTXTFile(listOfFiles,old,replace);
            } else {
                System.out.println("Invalid File Type");
                throw new IllegalArgumentException("File type not supported");
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return isSuccess;
    }

   /* public String getInputFile(String type, String search, String replace) {
    }*/

    public File[] finder( String dirName,String fileType){
        File dir = new File(dirName);
        return dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String filename)
            { return filename.endsWith("."+fileType); }
        } );

    }
}
