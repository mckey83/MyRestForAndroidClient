package ua.com.ex.reprository.impl.file;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import ua.com.ex.util.Util;

public class Import {    
    private static final String PATTERN_GET_ALL_FIELDS = "\\(.*?\\),|\\(.*?\\);";
    public  ArrayList<ArrayList<String>> get (String start, String end, String fileNameIn, int size){
        ArrayList<ArrayList<String>> result = new ArrayList<>();        
        try {           
            String itemQueryAll = getItemQueryAll(start, end, fileNameIn);                        
            Pattern p = Pattern.compile(PATTERN_GET_ALL_FIELDS);
            Matcher  mat = p.matcher(itemQueryAll);
            while (mat.find()) {                                  
                ArrayList<String> itemQuery = getItem(mat.group());                
                if (itemQuery.size() != size ){
                    show(itemQuery);
                }
                result.add(itemQuery);
            }          
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }  
        return result;        
    }


    private void show(ArrayList<String> ithemAsArray) {
        int index = 0;
        System.out.println("================ERROR==========================");
        for (String current : ithemAsArray){
            System.out.println(index++ +". "+current);
        }
        System.out.println();
    }

    protected String getItemQueryAll(String startMarker, String endMarker, String inputFileName) {  
        String result = readFile(inputFileName, StandardCharsets.UTF_8);
        result = StringUtils.substringAfter(result, startMarker );
        result = StringUtils.substringBefore(result, endMarker);
        return result;   
    }

    protected  String readFile(String path, Charset encoding) {
        String result = "";        
        ByteArrayOutputStream stream = Util.readExternFile(path);       
        try {
            result = stream.toString("UTF-8");
        } catch (IOException e) {               
            e.printStackTrace();
        }
        return result;
    }    

    protected ArrayList<String> getItem(String sourceQuery){ 
        sourceQuery = sourceQuery.substring(1, sourceQuery.length());
        sourceQuery = sourceQuery.substring(0, sourceQuery.length()-2); 
        ArrayList<String> result = new ArrayList<>();        
        Buffer buffer = new BufferForDigit();
        for(int i = 0; i< sourceQuery.length(); i++){
            char current = sourceQuery.charAt(i);
            if(buffer.isWork){
                if (isLast(sourceQuery, i)){
                    result.add(buffer.getResult());
                } else {
                    if(buffer.isDelimiter(current)){                    
                        result.add(buffer.getResult());
                        buffer.isWork = false;
                    }
                    else {                    
                        buffer.add(current); 
                    }
                }
            }
            else{                
                if(current!=','){
                    buffer = getBuffer(current);                    
                }
            }
        }
        return result;
    }

    private boolean isLast(String sourceQuery, int i) {
        return i == sourceQuery.length()-1;
    }

    private abstract class Buffer{
        public String buffer ="";
        public boolean isWork = true;
        public abstract boolean isDelimiter(char current);

        public void add(char current){
            buffer+= current;
        }

        public String getResult(){
            return buffer;
        }
    }

    private class BufferForDigit extends Buffer{
        @Override
        public boolean isDelimiter(char current) {           
            return current==',';
        }
    }

    private class BufferForNull extends Buffer{
        @Override
        public boolean isDelimiter(char current) {            
            return current ==',';
        }
    }

    private class BufferForText extends Buffer{
        @Override
        public boolean isDelimiter(char current) {            
            return current =='\'';
        }
    }

    private Buffer getBuffer(char current){        
        if (Character.isDigit(current)){
            BufferForDigit buffer = new BufferForDigit();
            buffer.add(current);            
            return buffer;
        }
        if (current =='\''){            
            return new BufferForText();
        }
        else {           
            BufferForNull buffer = new BufferForNull();
            buffer.add(current);
            return buffer;
        }
    }

}
