package com.zeo.hellospringboot;

import java.io.IOException;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SpringBootApplication
public class HelloSpringBootApplication {

	public static void main(String[] args) {
		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		WebServer webServer = serverFactory.getWebServer(servletContext -> {
				servletContext.addServlet("front", new HttpServlet() {
					@Override
					protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
						
						String name = req.getParameter("name");
						
						res.setStatus(HttpStatus.OK.value());
						res.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
						res.getWriter().println("hello" + name);
					}
				}).addMapping("*");
			
		});
		webServer.start();
	}

}
