package com.homedepot.excel.upload;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.homedepot.excel.upload.repository.UserRepository;
import com.homedepot.excel.upload.service.ExcelUploadDataProcessServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class,
webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExcelUploadDataProcessServiceImplTest {

	@Autowired
	ExcelUploadDataProcessServiceImpl service;
	
//	@MockBean
//	private UserRepository userRepository;
	
	@Test
	public void testProcessFiles() {
		//Mockito.doReturn(null).when(userRepository).findAllById(Mockito.any());
		service.processFiles(null, 10);
	}
}
