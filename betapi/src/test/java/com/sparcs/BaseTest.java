package com.sparcs;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

/**
*  
* @author Lee Newfeld
*/
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(
	loader = AnnotationConfigWebContextLoader.class,
	classes = {
		TestConfiguration.class,
	}
)
@TestPropertySource(locations = "classpath:test.properties")
@WebAppConfiguration
public abstract class BaseTest {

    @Autowired
    protected WebApplicationContext webApplicationContext;
    
	protected MockMvc mvc;
	
    @Before
    public void beforeTest() {
    	
        MockitoAnnotations.initMocks(this);
        
        mvc = webAppContextSetup(webApplicationContext).build();
    }
}
