package str;

import str.processor.ProcessFile;

public class Main {

    public static String type="";
    public static String search="";
    public static String replace="";

    public static void main(String[] args) {
        if(args.length <2){
            System.out.println("Missing mandatory input parameters");
        }
        try{
            type=args[0];
            search=args[1];
            replace=args[2];
        }catch (Exception ex){
            System.out.println("Invalid input parameters");
            ex.getStackTrace();
        }
        ProcessFile pf = new ProcessFile();
        pf.getInputFile(type,search,replace);
    }
}
