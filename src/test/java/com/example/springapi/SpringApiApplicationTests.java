package com.example.springapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class HttpRequestsTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getInventoryShouldReturnAllItems() throws Exception {
		this.mockMvc.perform(get("/items"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$.length()").value(5));
	}

	@Test
	public void postWithoutIdShouldCreate() throws Exception {
		this.mockMvc.perform(post("/items")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\": \"Speakers\", \"price\": 39.99, \"count\": 33}"))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").value(6))
				.andExpect(jsonPath("$.name").value("Speakers"))
				.andExpect(jsonPath("$.price").value(39.99))
				.andExpect(jsonPath("$.count").value(33));
	}

}
