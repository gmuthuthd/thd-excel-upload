package com.homedepot.excel.upload;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class ExcelUploadSearchUserNameTest {
	
	@Value( "${excel.upload.search.username.test}" )
	private String serachUserNameJson;
	
	@Autowired 
	MockMvc mockmvc;
	
	@Autowired
	private WebApplicationContext context;
	
	@Before
	public void setUp()
	{
		mockmvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	@Test
	public void testSearchByUserName()throws Exception 
	{
		MvcResult mvcResult = this.mockmvc.perform(get("/excel/searchUserByName?name=x")).andReturn();
		assertEquals(200,mvcResult.getResponse().getStatus());
		//assertEquals(serachUserNameJson,mvcResult.getResponse().getContentAsString());
		System.out.println("Hello");
	}
}
