
package com.blazdemsar;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.blazdemsar.utilities.BannerImpl;

@SpringBootApplication  // @Configuration + @AutoConfiguration + @ComponentScan
public class Application {

	public static void main(String[] args) {
		// SpringApplication.run(Application.class, args);
		
		System.setProperty("server.servlet.context-path", "/MyApp");
		
		SpringApplication app = new SpringApplication(Application.class);
		//app.setBannerMode(Mode.OFF); // Mode.OFF, Mode.LOG, Mode.CONSOLE
		app.setBanner(new BannerImpl());
		app.setBannerMode(Mode.CONSOLE);
		app.run(args);
	}

}
