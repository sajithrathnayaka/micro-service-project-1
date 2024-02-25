package com.studentmanagementsystem.vehicleservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studentmanagementsystem.vehicleservice.dto.VehicleRequest;
import com.studentmanagementsystem.vehicleservice.repository.VehicleRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.shaded.com.github.dockerjava.core.MediaType;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class VehicleServiceApplicationTests {

	/*@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private VehicleRepo vehicleRepo;

	static {
		mongoDBContainer.start();
	}

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dymDynamicPropertyRegistry) {
		dymDynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}*/

	@Test
	void shouldCreateVehicle() throws Exception {
		/*VehicleRequest vehicleRequest = getVehicleRequest();
		String productRequestString = objectMapper.writeValueAsString(vehicleRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/vehicle")
						.contentType(String.valueOf(MediaType.APPLICATION_JSON))
						.content(productRequestString))
				.andExpect(status().isCreated());
		Assertions.assertEquals(1, vehicleRepo.findAll().size());
	}

	private VehicleRequest getVehicleRequest() {
		return VehicleRequest.builder()
				.namePlate("ABC - 254")
				.brand("Honda")
				.model("CR-V")
				.year(2024)
				.build();*/
	}
}
