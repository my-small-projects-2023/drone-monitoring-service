package drone.monitiring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import jakarta.annotation.PreDestroy;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = "drone")
public class DroneMonitoringServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DroneMonitoringServiceApplication.class, args);
	}
	
	@PreDestroy
	void preDestroy() {
		System.out.println("DroneMonitoringServiceApplication - shutdown has been performed");
	}

}
