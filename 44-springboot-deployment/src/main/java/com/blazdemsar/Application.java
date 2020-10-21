
package com.blazdemsar;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.blazdemsar.utilities.BannerImpl;

@SpringBootApplication  // @Configuration + @AutoConfiguration + @ComponentScan
public class Application extends SpringBootServletInitializer{

	public static void main(String[] args) {
		// SpringApplication.run(Application.class, args);
		
		System.setProperty("server.servlet.context-path", "/MyApp");  // this way of changing servlet context not recognized by externatl Tomcat.
		
		SpringApplication app = new SpringApplication(Application.class);
		//app.setBannerMode(Mode.OFF); // Mode.OFF, Mode.LOG, Mode.CONSOLE
		app.setBanner(new BannerImpl());
		app.setBannerMode(Mode.CONSOLE);
		app.run(args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder biulder) {
		return biulder.sources(Application.class);
	}
	
	// Steps for deployment on external Tomcat
	/*	
	1) change the packaging to "war" from design view of pom.xml  or by putting <packaging>war</packaging> in pom.xml.
    2) use spring-boot-starter-tomcat
    3) use <finalName> tag to change the name of the war file. (ex. <finalName>BlazBank</finalName>)
    4) Spring Boot application that has the main() method should extend SpringBootServletInitializer and override the configure() method.
    
    5) Go to Tomcat installation and find the folder conf. Make the following entry in tomcat-users.xml.
    <user username="dinesh" password="dinesh" roles="manager-gui" /> // use username/password as appropriate 
    6) Don't use embedded tomcat jasper, instead use normal tomcat-jasper.
	*/
}