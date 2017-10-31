package com.noah;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.noah.config.AppConfig;
import com.noah.config.MvcConfig;
import com.noah.filter.DemoFilter;
import com.noah.filter.SpringBasedFilter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
	    @ContextConfiguration(name = "parent", classes = AppConfig.class),
		@ContextConfiguration(name = "child", classes = MvcConfig.class) })
//@WebAppConfiguration indicate it is a web app, without it mockMvc will not work
//throw error " A ServletContext is required to configure default servlet handling"
@WebAppConfiguration
public class AppTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setUp() {
		//if we want the filter work in mockMvc, need to manually add it
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilters(new DemoFilter(), new SpringBasedFilter()).build();
	}

	@Test
	public void testHelloController() throws Exception {
		mockMvc.perform(get("/hello/Noah")).andExpect(status().isOk())
				.andExpect(content().contentType("text/plain;charset=UTF-8"))
				.andExpect(content().string("Hello Dear Noah ..."));
	}

}

//below link details the two ways of spring mvc configuration, xml and annotation
//http://blog.csdn.net/xiao_xuwen/article/details/52890730