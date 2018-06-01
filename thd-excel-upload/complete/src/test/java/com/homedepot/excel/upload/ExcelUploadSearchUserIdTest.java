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
@SpringBootTest(classes=Application.class)
@AutoConfigureMockMvc
public class ExcelUploadSearchUserIdTest {
	
	@Value( "${excel.upload.search.user.test}" )
	private String serachUserJson;
	
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
	public void testSearchUserId() throws Exception
	{
		MvcResult mvcResult = this.mockmvc.perform(get("/excel/searchUserById?id=1")).andReturn();
		assertEquals(200,mvcResult.getResponse().getStatus());
		//assertEquals(serachUserJson,mvcResult.getResponse().getContentAsString());
	}
	

}
