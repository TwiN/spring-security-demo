package org.twinnation.seminar.springsecuritydemo.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
@WebAppConfiguration
public class ViewControllerTest {
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders
			.webAppContextSetup(webApplicationContext)
			.apply(springSecurity())
			.build();
	}
	
	
	///////////
	// LOGIN //
	///////////
	
	
	@Test
	public void login() throws Exception {
		mockMvc
			.perform(formLogin().user("user").password("user"))
			.andExpect(authenticated());
	}
	
	
	@Test
	public void login_invalidCredentials() throws Exception {
		mockMvc
			.perform(formLogin().user("123").password("456"))
			.andExpect(unauthenticated());
	}
	
	
	///////////
	// INDEX //
	///////////
	
	
	@Test
	@WithAnonymousUser
	public void index_asUnauthenticatedUser() throws Exception {
		mockMvc
			.perform(get("/"))
			.andExpect(status().isUnauthorized());
	}
	
	
	@Test
	@WithMockUser(username = "johndoe", roles = {"USER"})
	public void index_asUser() throws Exception  {
		mockMvc
			.perform(get("/"))
			.andExpect(status().isOk());
	}
	
	
	@Test
	@WithMockUser(username = "johndoe", roles = {"ADMIN"})
	public void index_asAdmin() throws Exception  {
		mockMvc
			.perform(get("/"))
			.andExpect(status().isOk());
	}
	
	
	///////////
	// ADMIN //
	///////////
	
	
	@Test
	@WithAnonymousUser
	public void admin_asUnauthenticatedUser() throws Exception  {
		mockMvc
			.perform(get("/admin"))
			.andExpect(status().isUnauthorized());
	}
	
	
	@Test
	@WithMockUser(username = "johndoe", roles = {"USER"})
	public void admin_asUser() throws Exception  {
		mockMvc
			.perform(get("/admin"))
			.andExpect(status().isForbidden());
	}
	
	
	@Test
	@WithMockUser(username = "johndoe", roles = {"ADMIN"})
	public void admin_asAdmin() throws Exception  {
		mockMvc
			.perform(get("/admin"))
			.andExpect(status().isOk());
	}
	
}