package jp.co.ixui.scheduleadjustment;

import java.net.URISyntaxException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DatabaseConfig {
	@Autowired
	DataSourceProperties properties;

	@Primary
	@Bean(destroyMethod="close")
	@ConfigurationProperties(prefix="spring.datasource")
	DataSource datasource() throws URISyntaxException {

		String databaseUrl = System.getenv("MYSQL_URL");
		String databaseName = System.getenv("MYSQL_NAME");
		String url = "jdbc:mysql://" + databaseUrl +"/"+ databaseName + "?useSSL=false&characterEncoding=UTF-8&characterSetResults=UTF-8";
		String userName = System.getenv("MYSQL_USER_ID");
		String password = System.getenv("MYSQL_USER_PASS");

		 return DataSourceBuilder.create()
	                .username(userName)
	                .password(password)
	                .url(url)
	                .driverClassName("com.mysql.jdbc.Driver")
	                .build();

	}
}
