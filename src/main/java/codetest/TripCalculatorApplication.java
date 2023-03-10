
package codetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Springboot application, use embedded Tomcat
 */
@SpringBootApplication
public class TripCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TripCalculatorApplication.class, args);
	}
}
