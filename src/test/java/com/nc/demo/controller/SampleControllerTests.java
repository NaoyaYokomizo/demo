package com.nc.demo.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class SampleControllerTests {

	private MockMvc mockMvc;

	@Autowired
	SampleController target;
  
	@Before
	public void setup() {
	  mockMvc = MockMvcBuilders.standaloneSetup(target).build();
	}

	@Test
	public void getSample() throws Exception {
	  // when
	  mockMvc.perform(get("/sample"))
		   .andExpect(status().isOk());
	}

	@Test
	public void getSample2() throws Exception {
	  // when
	  mockMvc.perform(get("/sample2"))
		   .andExpect(status().isOk());
	}
}
