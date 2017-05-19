package ua.com.ex.reprository.impl.file;
import java.util.ArrayList;

public class ImportProduct extends Import{

    private static final int COLUMN_ID = 0;
    private static final int COLUMN_PARENT_CATEGORY_ID = 3;
    private static final int COLUMN_PRICE = 17;
    private static final int COLUMN_QUANTITY = 22;
    
    @Override
    protected ArrayList<String> mapper(ArrayList<String> in, ArrayList<String> stringFieldAll) {
        ArrayList<String> out = new ArrayList<>();
        out.add(in.get(COLUMN_ID));        
        out.add(stringFieldAll.get(1));
        out.add(in.get(COLUMN_PRICE));
        out.add(in.get(COLUMN_QUANTITY));
        out.add(in.get(COLUMN_PARENT_CATEGORY_ID));
        return out;
    }

    @Override
    protected String getPrefix() {
        // TODO Auto-generated method stub
        return "INSERT INTO PRODUCTS (ID, NAME, PRICE, QUANTITY, PARENT_CATEGORY) VALUES ";
    }

    
    
    
    

}
