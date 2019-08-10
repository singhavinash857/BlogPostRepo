package com.transformedge.apps.appconfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.transformedge.apps.loginlogouthandler.ActiveUserStore;
@Configuration
public class UserService {

	@Bean
	public ActiveUserStore activeUserStore(){
		return new ActiveUserStore();
	}

}
