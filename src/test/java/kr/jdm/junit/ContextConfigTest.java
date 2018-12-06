package kr.jdm.junit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {
        "classpath:/config/spring/servlet-*.xml",
        "classpath:/config/spring/context-*.xml",
        "classpath:/config/spring/spring-security.xml"
})
public class ContextConfigTest {

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private AnnotationMethodHandlerAdapter adapter;
    private MockHttpSession session;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        this.request = new MockHttpServletRequest();
        this.response = new MockHttpServletResponse();
        this.adapter = new AnnotationMethodHandlerAdapter();
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();

    }

    @Test
    public void test() throws Exception{
        // TODO session
        // https://zorba91.tistory.com/entry/JUnit%EC%97%90%EC%84%9C-%EC%84%B8%EC%85%98-%EC%83%9D%EC%84%B1%EC%9D%84-%EC%96%B4%EB%96%BB%EA%B2%8C-%ED%95%B4%EC%95%BC%ED%95%A0%EA%B9%8Cjunit%EC%97%90%EC%84%9C-session-%EC%B2%98%EB%A6%AC

        this.mockMvc.perform(get("/test/page").param("num1","2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("serverTime"));
    }
}