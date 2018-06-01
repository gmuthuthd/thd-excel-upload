package com.homedepot.excel.upload;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.aspectj.lang.annotation.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.homedepot.excel.upload.dto.ExcelUploadDto;
import com.homedepot.excel.upload.repository.UserRepository;
import com.homedepot.excel.upload.service.ExcelUploadDataProcessService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class ExcelUploadControllerTest {

	@Autowired
	MockMvc mvc;
	
	//@MockBean
	//private ExcelUploadController arrivalController;

	@Autowired
	private WebApplicationContext context;
	
	 @Before
	  public void setUp() {
		 mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	  }

	 

	 
	@Test
	public void testGetUserByName() throws Exception {	
		
		MvcResult result =	this.mvc.perform(get("/excel/searchUserByName?name=x")).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		assertEquals("{\"view\": null,\"model\": {\"excelUploadDto\": {\"userList\": [{\"id\": \"201\",\"name\": \"x\",\"email\": \"x@gmail.com\"}],\"uploadStatus\": null,\"exceptionList\": null}},\"status\": null,\"empty\": false,\"viewName\": \"userDisplay\",\"modelMap\": {\"excelUploadDto\": {\"userList\": [{\"id\": \"201\",\"name\": \"x\",\"email\": \"x@gmail.com\"}],\"uploadStatus\": null,\"exceptionList\": null}},\"reference\": true}", result.getResponse().getContentAsString());
		
	}
	 	 
	
}
