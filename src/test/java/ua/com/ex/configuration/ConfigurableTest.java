package ua.com.ex.configuration;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ua.com.ex.Rest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Rest.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConfigurableTest {

    @Autowired
    private Configurable configurable;

    @Test
    public void onlyParentPaginationTest() {           
        int[] categoriesWithOnlyParentProducts = {6 , 7 , 8 , 9 , 10 , 11 , 12 , 13 , 14 , 19 , 22 , 23 , 24 , 101 , 102 , 103 , 109, 140 , 143 , 144 };
        for (int i: categoriesWithOnlyParentProducts){
            assertTrue(configurable.isParentOnly(i));
        }
    }
}
