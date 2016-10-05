package com.sparcs;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
*  
* @author Lee Newfeld
*/
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(
	loader = AnnotationConfigContextLoader.class,
	classes = {
		TestConfiguration.class,
	}
)
@TestPropertySource(locations = "classpath:test.properties")
public abstract class BaseTest {

    @Before
    public void beforeTest() {
    	
        MockitoAnnotations.initMocks(this);
    }
}
