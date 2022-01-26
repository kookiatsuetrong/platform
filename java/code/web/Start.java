package web;
import java.util.Arrays;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

class Start {
	public static void main(String[] data) {
		ApplicationContext context;
		context = SpringApplication.run(Initialize.class);
	}
}

@SpringBootApplication
class Initialize {
	// Nothing
}

/*
create database platform default charset 'UTF8';
create user owner identified with mysql_native_password
	by 'P@ssw0rd';
grant all on platform.* to owner;
*/