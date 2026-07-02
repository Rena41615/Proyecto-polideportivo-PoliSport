package com.polisport.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
class GatewayApplicationTests {

	@Autowired
	private RouteLocator routeLocator;

	@Test
	void contextLoads() {
		assertNotNull(routeLocator);
	}
}
