package com.graphql.demo.service;

import com.graphql.demo.model.Car;
import com.graphql.demo.repository.CarRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@GraphQLApi
public class CarService {

    private CarRepository carRepos;
    private GiphyService giphyService;

    public CarService(CarRepository carRepos, GiphyService giphyService) {
        this.carRepos = carRepos;
        this.giphyService = giphyService;
    }

    @GraphQLQuery(name = "cars")
    public List<Car> getAllCar(){
        return carRepos.findAll();
    }

    @GraphQLMutation(name = "saveCar")
    public Car save(@GraphQLArgument(name = "car") Car car){
        return carRepos.save(car);
    }

    @GraphQLMutation(name = "delete")
    public void delete(@GraphQLArgument(name = "id") Long id){
        carRepos.deleteById(id);
    }

    @GraphQLQuery(name = "car")
    public Optional<Car> find(@GraphQLArgument(name = "carId") Long id){
        return carRepos.findById(id);
    }

    @GraphQLQuery(name = "isCool")
    public boolean isCool(@GraphQLContext Car car){
        return !car.getName().equals("bemo") && !car.getName().equalsIgnoreCase("ferrari")
                && !car.getName().equalsIgnoreCase("AMC Gremlin")
                && !car.getName().equalsIgnoreCase("bus");
    }

    @GraphQLQuery(name = "gif")
    public String getGiphyService(@GraphQLContext Car car){
        return giphyService.getGiphyUrl(car.getName());
    }
}
