package com.alianza.clients;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith( SpringExtension.class )
@WebAppConfiguration
@SpringBootTest( classes = AlianzaAppApplication.class )
public abstract class AlianzaAppApplicationTest {
	
	protected MockMvc mvc;
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	protected void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

}
