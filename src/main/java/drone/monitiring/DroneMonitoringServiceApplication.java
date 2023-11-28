package drone.monitiring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PreDestroy;

@SpringBootApplication
public class DroneMonitoringServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DroneMonitoringServiceApplication.class, args);
	}
	
	@PreDestroy
	void preDestroy() {
		System.out.println("DroneMonitoringServiceApplication - shutdown has been performed");
	}

}
