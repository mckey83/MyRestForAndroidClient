package ua.com.ex.reprository.impl.file;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ImportCategory extends Import {

    private static final String PATTERN_GET_TEXT_FIELDS = "\\,'.*?\\',";
    
    @Override
    protected ArrayList<String> mapper(ArrayList<String> numericFieldsAll, ArrayList<String> stringFieldAll) {
        ArrayList<String> out = new ArrayList<>();
        out.add(numericFieldsAll.get(0));        
        out.add(stringFieldAll.get(0));        
        out.add(numericFieldsAll.get(2));        
        out.add("0");
        return out;
    }

    @Override
    protected Pattern getPatern() {        
        return Pattern.compile(PATTERN_GET_TEXT_FIELDS);
    }

    

}
