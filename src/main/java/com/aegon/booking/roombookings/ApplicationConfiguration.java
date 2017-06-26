package com.aegon.booking.roombookings;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aegon.booking.roombookings.util.ApplicationConstants;
/**
 * 
 * This class is used to configure In-memory DB - H2
 *
 */
@Configuration
public class ApplicationConfiguration {

	@Bean
	ServletRegistrationBean h2Registration()
	{
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new WebServlet());
		servletRegistrationBean.addUrlMappings(ApplicationConstants.CONSOLE);
		return servletRegistrationBean;
	}
}
