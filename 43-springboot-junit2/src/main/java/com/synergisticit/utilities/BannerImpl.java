package com.synergisticit.utilities;

import java.io.PrintStream;

import org.springframework.boot.Banner;
import org.springframework.core.env.Environment;

public class BannerImpl implements Banner {

	@Override
	public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
		out.println("==========================");
		out.println("Application Name: SpringBoot Project");
		out.println("Developers: Blaz, Menglee");
		
		out.println("==========================");
		
	}

}
