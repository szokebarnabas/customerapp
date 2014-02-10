package com.barnabasszoke.customerapp;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import com.barnabasszoke.customerapp.config.SecurityConfiguration;
@ComponentScan(basePackages = {"com.barnabasszoke.customerapp"})
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

	public SecurityWebApplicationInitializer() {
		super(SecurityConfiguration.class);
	}
}
