package ua.com.ex.configuration;

import org.springframework.stereotype.Component;

@Component("configurable")
public class ConfigurableImpl implements Configurable {    
    
    @Override
    public boolean isParentOnly(int categoryId) {
        return categoryId == 3 ||categoryId == 6 || categoryId == 7 || categoryId == 8 || categoryId == 9 || categoryId == 10 || categoryId == 11 || categoryId == 12 || categoryId == 13 || categoryId == 14 || categoryId == 19 || categoryId == 22
                || categoryId == 23 || categoryId == 24 || categoryId == 101 || categoryId == 102 || categoryId == 103 || categoryId == 109|| categoryId == 140 || categoryId == 143 || categoryId == 144;
    }
    
   

}
