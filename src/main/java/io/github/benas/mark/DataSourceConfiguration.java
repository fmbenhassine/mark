package io.github.benas.mark;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class DataSourceConfiguration {

	@Bean
	public DataSource dataSource() {
		Path databaseFile = Paths.get(System.getProperty("user.home"), ".mark", "bookmarks.db");
		SingleConnectionDataSource dataSource = new SingleConnectionDataSource();
		dataSource.setDriverClassName("org.sqlite.JDBC");
		dataSource.setUrl("jdbc:sqlite:" + databaseFile);
		return dataSource;
	}

}
