package com.sparcs.bet;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.PrintingResultHandler;
import org.springframework.util.CollectionUtils;
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

	protected static final Logger log = LoggerFactory.getLogger(BaseTest.class);

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
    
    /**
     * Serialise an object to pretty printed Json.
     * 
     * @param o Object to serialise
     * @return Json as a string
     * @throws JsonProcessingException
     */
    protected String prettyPrint(Object o) throws JsonProcessingException {
    	
    	return jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
    }
    
    /**
     * @return A MockMvc {@link ResultHandler} that writes the
     * {@link MvcResult} to our test {@link #log}.
     */
	public static ResultHandler traceLog() {

		return new LoggingResultHandler();
	}

	/**
	 * Stolen from {@link MockMvcResultHandlers}.
	 */
	private static class LoggingResultHandler implements ResultHandler {

		@Override
		public void handle(MvcResult result) throws Exception {
			if (log.isTraceEnabled()) {
				StringWriter stringWriter = new StringWriter();
				ResultHandler printingResultHandler =
						new PrintWriterPrintingResultHandler(new PrintWriter(stringWriter));
				printingResultHandler.handle(result);
				log.trace("MvcResult details:\n" + stringWriter);
			}
		}
	}
	
	/**
	 * Stolen from {@link MockMvcResultHandlers}.
	 */
	private static class PrintWriterPrintingResultHandler extends PrintingResultHandler {

		public PrintWriterPrintingResultHandler(final PrintWriter writer) {
			super(new ResultValuePrinter() {
				@Override
				public void printHeading(String heading) {
					writer.println();
					writer.println(String.format("%s:", heading));
				}
				@Override
				public void printValue(String label, Object value) {
					if (value != null && value.getClass().isArray()) {
						value = CollectionUtils.arrayToList(value);
					}
					writer.println(String.format("%17s = %s", label, value));
				}
			});
		}
	}
}
