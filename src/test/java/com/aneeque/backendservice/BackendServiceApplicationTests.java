package com.aneeque.backendservice;

import com.aneeque.backendservice.service.impl.DepartmentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BackendServiceApplicationTests.class})
@ExtendWith(SpringExtension.class)
class BackendServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
