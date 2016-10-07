package com.sparcs;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

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
@TestExecutionListeners(
	listeners = {
		DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
	}
)
public abstract class BaseTest {

    @Autowired
    protected WebApplicationContext webApplicationContext;
    
    protected ObjectMapper jsonMapper;
    
	protected MockMvc mvc;
	
	protected BaseTest() {
		
		jsonMapper = new ObjectMapper();
		jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
	}
	
    @Before
    public void beforeTest() {
    	
        MockitoAnnotations.initMocks(this);
        
        mvc = webAppContextSetup(webApplicationContext).build();
    }
    
    protected String prettyPrint(Object o) throws JsonProcessingException {
    	
    	return jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
    }
}
