package ua.com.ex.reprository.impl.file;
import java.util.ArrayList;

public class ImportCategory extends Import {

    @Override
    protected ArrayList<String> mapper(ArrayList<String> in, ArrayList<String> stringFieldAll) {
        ArrayList<String> out = new ArrayList<>();
        out.add(in.get(0));        
        out.add(stringFieldAll.get(0));
        out.add(in.get(2));
        out.add("0");
        return out;
    }

    @Override
    protected String getPrefix() {        
        return "INSERT INTO CATEGORIES (ID, NAME, PARENT_ID, PRODUCT_QUANTITY) VALUES ";
    }

}
