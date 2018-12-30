package pl.umcs.lisowska;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserManager {
    public static void main(String[] args){
        SpringApplication.run(UserManager.class, args);
    }
}
