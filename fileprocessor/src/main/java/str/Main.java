package str;

import str.processor.ProcessFile;

import java.util.logging.Logger;

public class Main {

    private final static Logger LOG = Logger.getLogger(Main.class.getName());
    private static String type="";
    private static String search="";
    private static String replace="";

    public static void main(String[] args) {
        if(args.length <2){
            LOG.info("Missing mandatory input parameters");
        }
        try{
            type=args[0];
            search=args[1];
            replace=args[2];
        }catch (Exception ex){
            LOG.info("Invalid input parameters");
            ex.getStackTrace();
        }
        ProcessFile pf = new ProcessFile();
        pf.getInputFile(type,search,replace);
    }
}
