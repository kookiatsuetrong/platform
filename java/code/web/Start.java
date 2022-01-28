package web;
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
