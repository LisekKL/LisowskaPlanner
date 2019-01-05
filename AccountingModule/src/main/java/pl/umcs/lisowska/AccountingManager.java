package pl.umcs.lisowska;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AccountingManager {
    public static void main(String[] args){
        SpringApplication.run(AccountingManager.class, args);
    }
}
