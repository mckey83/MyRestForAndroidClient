package ua.com.ex.reprository.impl.file;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ImportProduct extends Import{

    private static final int COLUMN_ID = 0;
    private static final int COLUMN_PARENT_CATEGORY_ID = 3;
    private static final int COLUMN_PRICE = 13;
    private static final int COLUMN_QUANTITY = 18;
    
    private static final String PATTERN_GET_TEXT_FIELDS = "\\,'.*?\\'";

    @Override
    protected ArrayList<String> mapper(ArrayList<String> in, ArrayList<String> stringFieldAll) {
        ArrayList<String> out = new ArrayList<>();
        String id = in.get(COLUMN_ID);
       // System.out.print(id+" ");
        out.add(id);

        String name =  stringFieldAll.get(1);
    //    System.out.print(name+" ");
        name = name.substring(2, name.length()-1);
        out.add(name);

        String price = in.get(COLUMN_PRICE);
        System.out.println(price+" ");
        out.add(price);

        String quantity = in.get(COLUMN_QUANTITY);
     //   System.out.print(quantity+" ");
        out.add(quantity);

        String parentcategory = in.get(COLUMN_PARENT_CATEGORY_ID);
   //    System.out.print(parentcategory+"\n");
        out.add(parentcategory);
        return out;
    }

    @Override
    protected Pattern getPatern() {
        // TODO Auto-generated method stub
        return Pattern.compile(PATTERN_GET_TEXT_FIELDS);
    }








}
