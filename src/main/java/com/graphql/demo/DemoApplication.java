package com.graphql.demo;

import com.graphql.demo.model.Car;
import com.graphql.demo.service.CarService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    ApplicationRunner init(CarService carService) {

        return args -> {
            Stream.
                    of("Ferrari", "Jaguar", "Porsche", "Lamborghini", "Bugatti",

                            "AMC Gremlin", "Triumph Stag", "Ford Pinto", "Yugo GV").forEach(name -> {
                Car car =
                        new Car();
                car.setName(name);
                carService.save(car);
            });
            carService.getAllCar().forEach(System.
                    out::println);
        };
    }
}
