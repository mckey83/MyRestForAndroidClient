package integration;

import static org.mockito.BDDMockito.given;

import org.apache.commons.net.util.Base64;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ua.com.ex.Rest;
import ua.com.ex.controller.rest.ImageController;
import ua.com.ex.service.ImageService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Rest.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ImageControllerTest {
   
    private final String PATH_PUT = "/image/put/";   
    
    private final ResultMatcher IS_OK =  MockMvcResultMatchers.status().isOk();
    private final ResultMatcher IS_BAD_REQUEST = MockMvcResultMatchers.status().isBadRequest();
    
    private MockMvc mockMvc;

    @Mock
    private ImageService imageService;
    
    @Before
    public void setup(){        
        mockMvc = MockMvcBuilders.standaloneSetup(new ImageController(imageService)).build(); 
    } 
    
    @Test
    public void saveTest() throws Exception {          
        int id = 0;
        String image = Base64.encodeBase64String("Test!test".getBytes());
        given(imageService.save(id, image)).willReturn(true); 
        MockHttpServletRequestBuilder requestPut = getPutRequestBuilder(id,image);
        request( IS_OK, requestPut);    
    }
    
    @Test
    public void saveBadRequestTest() throws Exception {          
        int id = 0;
        String image = "";
        given(imageService.save(id, image)).willReturn(false); 
        MockHttpServletRequestBuilder requestPut = getPutRequestBuilder(id,image);
        request(IS_BAD_REQUEST, requestPut);    
    }
    
    @Test
    public void saveBadRequestTwoTest() throws Exception {          
        int id = 92;
        String image = "";
        MockHttpServletRequestBuilder requestPut = getPutRequestBuilder(id,image);
        request(IS_BAD_REQUEST, requestPut);    
    }
    
    private void request(ResultMatcher resultMatcher, MockHttpServletRequestBuilder requestBuilder) throws Exception {
        this.mockMvc.perform(requestBuilder)
        .andExpect(resultMatcher)
        .andDo(MockMvcResultHandlers.print());
    }
    
//    private void requestSpecial(ResultMatcher resultMatcher, MockHttpServletRequestBuilder requestBuilder, String message) throws Exception {
//        this.mockMvc.perform(requestBuilder)
//        .andExpect(resultMatcher)
//        .andDo(MockMvcResultHandlers.print());
//    }
    
    private MockHttpServletRequestBuilder getPutRequestBuilder(int id, String contentUi) {
        return MockMvcRequestBuilders.put(PATH_PUT+id)
        .contentType(MediaType.APPLICATION_JSON)
        .content(contentUi);
    } 
}
