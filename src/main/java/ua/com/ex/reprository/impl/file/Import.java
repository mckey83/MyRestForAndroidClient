package ua.com.ex.reprository.impl.file;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import ua.com.ex.util.Util;

public abstract class Import {


    private static final String PATTERN_GET_TEXT_FIELDS = "\\'.*?\\'";
    private static final String PATTERN_GET_ALL_FIELDS = "\\(.*?\\),|\\(.*?\\);";


    public  ArrayList<ArrayList<String>> get (String start, String end, String fileNameIn){
        ArrayList<ArrayList<String>> result = new ArrayList<>();        
        try {           
            String itemQueryAll = getItemQueryAll(start, end, fileNameIn);                        
            Pattern p = Pattern.compile(PATTERN_GET_ALL_FIELDS);
            Matcher  mat = p.matcher(itemQueryAll);
            while (mat.find()) {
                String itemQuery = mat.group();
                itemQuery = itemQuery.substring(1, itemQuery.length()-1);               
                result.add(getItem(itemQuery));
            }          
        } catch (Exception e) {
            e.printStackTrace();
        }  
        return result;        
    }

    protected String getItemQueryAll(String startMarker, String endMarker, String inputFileName) {  
        String result = readFile(inputFileName, StandardCharsets.UTF_8);
        result = StringUtils.substringAfter(result, startMarker );
        result = StringUtils.substringBefore(result, endMarker);
        return result;   
    }

    protected  String readFile(String path, Charset encoding) {
        String result = "";        
        ByteArrayOutputStream stream = Util.readFile(path);
        try {
            result = stream.toString("UTF-8");
        } catch (IOException e) {               
            e.printStackTrace();
        }
        return result;
    }    

    protected ArrayList<String> getItem(String sourceQuery){      
        ArrayList<String> stringFieldAll = getStringFields(sourceQuery);
        sourceQuery = getWithoutStringFields(sourceQuery, stringFieldAll);
        ArrayList<String> numericFieldsAll = getNumericFields(sourceQuery);             
        return mapper(numericFieldsAll, stringFieldAll);
    }

    protected ArrayList<String> getStringFields(String sourceQuery){
        ArrayList<String> stringFieldAll = new ArrayList<>();
        Pattern p = Pattern.compile(PATTERN_GET_TEXT_FIELDS);
        Matcher  mat = p.matcher(sourceQuery);
        while (mat.find()) {
            String stringField = mat.group(); 
            stringField = stringField.replaceAll("'", "");
            stringFieldAll.add(stringField);
        }
        return stringFieldAll;
    }

    protected String getWithoutStringFields(String sourceQuery, ArrayList<String> stringFieldAll) {
        for(String current: stringFieldAll){
            sourceQuery = sourceQuery.replace(current, "");
        }
        return sourceQuery;
    }

    protected ArrayList<String> getNumericFields(String sourceQuery) {
        String[] splited = sourceQuery.split(",");        
        ArrayList<String> numericFieldsAll = new ArrayList<>(Arrays.asList(splited));
        return numericFieldsAll;
    }        

    protected abstract ArrayList<String> mapper(ArrayList<String> in,  ArrayList<String> stringFieldAll);
    protected abstract String getPrefix();    

}
