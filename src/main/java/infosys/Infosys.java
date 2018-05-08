package infosys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Infosys {

	public static void main(String[] args) {
		// Port number as argument, if not specified uses 8080
		if (args.length == 1) {
			System.getProperties().put("server.port", args);
		}else{
			System.getProperties().put("server.port", 8080);
		}
		SpringApplication.run(Infosys.class, args);
    }
}