package com.homedepot.excel.upload;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class ExcelUploadSearchUserNameNotFoundTest {
	
	@Autowired
	MockMvc mockmvc;
	
	@Autowired
	WebApplicationContext context;
	
	@Before
	public void setUp()
	{
		mockmvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	@Test
	public void testUserNameNotFound() throws Exception
	{
		MvcResult result = mockmvc.perform(get("/excel/searchUserByName?name=1")).andReturn();
		assertEquals(404,result.getResponse().getStatus());
	}
	
	

}
