package pl.umcs.lisowska;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ToDoManager {
    public static void main(String[] args){
        SpringApplication.run(ToDoManager.class, args);
    }
}
