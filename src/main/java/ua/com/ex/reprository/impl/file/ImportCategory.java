package ua.com.ex.reprository.impl.file;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ImportCategory extends Import {

    private static final String PATTERN_GET_TEXT_FIELDS = "\\'.*?\\',";
    
    @Override
    protected ArrayList<String> mapper(ArrayList<String> numericFieldsAll, ArrayList<String> stringFieldAll) {
        ArrayList<String> out = new ArrayList<>();
        
        //0
        String id = numericFieldsAll.get(0);
        out.add(id); 
        System.out.print("id : "+id);
        //1
        String name = stringFieldAll.get(0);
        name = name.substring(1, name.length()-2);
        out.add(name);   
        System.out.print(" name : "+ name);
        //2
        String parentId = numericFieldsAll.get(1);
        out.add(parentId);  
        System.out.print(" parentId : "+ parentId );       
        //3
        String enable = numericFieldsAll.get(6);
        out.add(enable);  
        System.out.print(" enable : "+ enable);
        //4
        String extra = numericFieldsAll.get(12);  
        out.add(extra);
        System.out.print(" extra : "+ extra +"\n");
        //5
       // out.add("20");

        
        return out;
    }

    @Override
    protected Pattern getPatern() {        
        return Pattern.compile(PATTERN_GET_TEXT_FIELDS);
    }

    

}
