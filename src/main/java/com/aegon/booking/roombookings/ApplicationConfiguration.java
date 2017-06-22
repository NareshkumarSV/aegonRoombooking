package com.aegon.booking.roombookings;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

	@Bean
	ServletRegistrationBean h2Registration()
	{
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new WebServlet());
		servletRegistrationBean.addUrlMappings("/console/*");
		return servletRegistrationBean;
	}
}
