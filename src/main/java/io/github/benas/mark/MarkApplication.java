package io.github.benas.mark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MarkApplication {

	public static void main(String[] args) {
		initDatabaseFile();
		SpringApplication.run(MarkApplication.class, args);
	}

	private static void initDatabaseFile() {
		try {
			Utils.initDatabaseFile();
		} catch (IOException e) {
			System.err.println("Unable to create database file in ~/.mark/bookmarks.db");
			System.exit(1);
		}
	}
}
