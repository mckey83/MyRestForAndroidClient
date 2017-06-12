package ua.com.ex.tools;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ua.com.ex.Rest;
import ua.com.ex.tools.parser.FileSqlParser;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Rest.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SqlFileParserTest {

    private static final String PRODUCT_LOCALEXBASE_TEST_SQL = "/product_localexbase_test.sql";

    private static final String PRODUCT_LOCALEXBASE_TEST_SQL_NOT_EXIST = "/localexbase_test2.sql";

    private static final String PRODUCT_LOCALEXBASE_TEST_INCORRECT_QUANTITY_OF_FIELDS_MORE = "/product_localexbase_test_incorrect_quantity_of_fields_more.sql";

    private static final String PRODUCT_LOCALEXBASE_TEST_INCORRECT_QUANTITY_OF_FIELDS_LESS = "/product_localexbase_test_incorrect_quantity_of_fields_less.sql";

    private static final String PRODUCT_MARKER_FINISH = "/*!40000 ALTER TABLE `products` ENABLE KEYS */;";

    private static final String PRODUCT_MARKER_START = "INSERT INTO `products` VALUES";

    private static final String PRODUCT_LOCALEXBASE_TEST_SQL_WITHOUT_START_MARKER = "/product_localexbase_test_without_product_start_marker.sql";

    private static final String PRODUCT_LOCALEXBASE_TEST_SQL_WITHOUT_END_MARKER = "/product_localexbase_test_without_product_end_marker.sql";

    
    private static final String CATEGORY_MARKER_START = "INSERT INTO `categories` VALUES";

    private static final String CATEGORY_MARKER_FINISH = "/*!40000 ALTER TABLE `categories` ENABLE KEYS */;";   

    private static final String CATEGORY_LOCALEXBASE_TEST_SQL = "/category_localexbase_test.sql";

    private static final String CATEGORY_LOCALEXBASE_TEST_SQL_NOT_EXIST = "/category_localexbase_test2.sql";

    @Autowired
    private FileSqlParser parser;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

      

    @Test
    public void testIsExistProductFile() {
        logger.info("*testIsExistFile() ");
        File inputXmlFile = null;
        try {      
            inputXmlFile = new File(this.getClass().getResource(PRODUCT_LOCALEXBASE_TEST_SQL).getFile());  
            assertTrue( inputXmlFile.length() > 1000);
        } catch (Exception e) {            
            logger.error("testIsTestFileExist() ", e);
        }
        
    }

    @Test
    public void testIsNotExistProductFile() {
        logger.info("*testIsNotExistFile() ");
        executeForProduct(null, PRODUCT_LOCALEXBASE_TEST_SQL_NOT_EXIST);
    }

    @Test
    public void  testReadExistOkProductFile() {
        logger.info("*testReadExistOkFile() ");
        try {   
            String path = this.getClass().getResource(PRODUCT_LOCALEXBASE_TEST_SQL).getFile();
            assertNotNull(parser.get(PRODUCT_MARKER_START, PRODUCT_MARKER_FINISH, path, 46));
        } catch (Exception e) {            
            logger.error("testIsTestFileExist() ", e);
        }
    }

    @Test
    public void  testReadExistWithourProductStartMarkerFile() {
        logger.info("*testReadExistWithourProductStartMarkerFile() ");
        String expected = "FileSqlParser.getItemQueryAll() isContainStartMarker = false isContainEndMarker = true";
        executeForProduct(expected, PRODUCT_LOCALEXBASE_TEST_SQL_WITHOUT_START_MARKER);       
    }

    @Test
    public void  testFileWithourProductEndMarkerFile() {
        logger.info("*testFileWithourProductEndMarkerFile() ");
        String expected = "FileSqlParser.getItemQueryAll() isContainStartMarker = true isContainEndMarker = false";
        executeForProduct(expected, PRODUCT_LOCALEXBASE_TEST_SQL_WITHOUT_END_MARKER);
    }

    @Test
    public void  testFileIncorrectQuantityOfFieldsMore() {
        logger.info("*testFileIncorrectQuantityOfFieldsMore() ");
        String expected = "FileSqlParser.get() incorrect quantity of fields = 47";
        executeForProduct(expected, PRODUCT_LOCALEXBASE_TEST_INCORRECT_QUANTITY_OF_FIELDS_MORE);
    }

    @Test
    public void  testFileIncorrectQuantityOfFieldsLess() {
        logger.info("testFileIncorrectQuantityOfFieldsLess() ");
        String expected = "FileSqlParser.get() incorrect quantity of fields = 44";
        executeForProduct(expected, PRODUCT_LOCALEXBASE_TEST_INCORRECT_QUANTITY_OF_FIELDS_LESS);
    }


    @Test
    public void  testQuantityOfProduct() {
        logger.info("testQuantityOfProduct() ");
        int expected = 8;
        ArrayList<ArrayList<String>> actual = null;
        try {   
            String path = this.getClass().getResource(PRODUCT_LOCALEXBASE_TEST_SQL).getFile();
            actual = parser.get(PRODUCT_MARKER_START, PRODUCT_MARKER_FINISH, path, 46);
        } catch (Exception e) {
            logger.error(e.toString());       
        }
        assertEquals(expected, actual.size());
    }

    private void executeForProduct(String expected, String sourceFile) {
        try {   
            String path = this.getClass().getResource(sourceFile).getFile();
            System.out.println("executeForProduct "+path);
            parser.get(PRODUCT_MARKER_START, PRODUCT_MARKER_FINISH, path, 46);
        } catch (Exception e) {
            logger.info(e.toString());
            String actual = e.getMessage();
            assertEquals(expected, actual);            
        }
    }



    @Test
    public void  testQuantityOfCategory() {
        logger.info("testQuantityOfCategory() ");
        int expected = 128;
        ArrayList<ArrayList<String>> actual = null;
        try {   
            String path = this.getClass().getResource(CATEGORY_LOCALEXBASE_TEST_SQL).getFile();
            actual = parser.get(CATEGORY_MARKER_START, CATEGORY_MARKER_FINISH, path, 33);
            assertEquals(expected, actual.size());
        } catch (Exception e) {
            logger.error("testQuantityOfCategory() " +e.toString());
            assertEquals(expected, actual.size());
        }
        

    }


    @Test
    public void testIsExistCategoryFile() {
        logger.info("*testIsExistCategoryFile() ");
        File inputXmlFile = null;
        try {      
            inputXmlFile = new File(this.getClass().getResource(CATEGORY_LOCALEXBASE_TEST_SQL).getFile());            
        } catch (Exception e) {            
            logger.error("*testIsExistCategoryFile() ", e);
        }
        assertNotNull(inputXmlFile);
    }



    //    private void executeForCategory(String expected, String sourceFile) {
    //        try {   
    //            String path = this.getClass().getResource(sourceFile).getFile();
    //            parser.get(CATEGORY_MARKER_START, CATEGORY_MARKER_FINISH, path, 33);
    //        } catch (Exception e) {
    //            logger.info(e.toString());
    //            String actual = e.getMessage();
    //            assertEquals(expected, actual);            
    //        }
    //    }

}
