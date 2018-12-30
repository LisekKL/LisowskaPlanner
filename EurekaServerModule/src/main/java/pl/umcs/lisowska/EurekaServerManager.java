package pl.umcs.lisowska;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@EnableEurekaServer
@SpringBootApplication
public class EurekaServerManager
{
    public static void main(String[] args){
        SpringApplication.run(EurekaServerManager.class, args);
    }
}
