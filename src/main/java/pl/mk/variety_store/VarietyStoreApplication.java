package pl.mk.variety_store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = {"application.properties", "application-ext.properties"},
        ignoreResourceNotFound = true)
public class VarietyStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(VarietyStoreApplication.class, args);
    }

}
