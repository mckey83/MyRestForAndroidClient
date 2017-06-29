package all;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import integration.CategoryControllerIT;
import integration.ImageControllerTest;
import integration.ProductControllerIT;
import ua.com.ex.configuration.ConfigurableTest;
import ua.com.ex.service.CategoryServiceImplTest;
import ua.com.ex.tools.SqlFileParserTest;
import ua.ex.com.repository.ImagesRepositoryTest;
import ua.ex.com.repository.ProductRepositoryTest;

@RunWith(Suite.class)
@SuiteClasses({ 
    CategoryControllerIT.class, 
    ImageControllerTest.class, 
    ProductControllerIT.class,
    ConfigurableTest.class,
    CategoryServiceImplTest.class,
    SqlFileParserTest.class,
    ImagesRepositoryTest.class,
    ProductRepositoryTest.class
    })
public class AllTests {

}
