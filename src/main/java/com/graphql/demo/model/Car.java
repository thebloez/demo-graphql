package com.graphql.demo.model;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Car {
    @Id
    @GeneratedValue
    @GraphQLQuery(name = "id", description = "Car Id")
    private Long id;

    @GraphQLQuery(name = "name", description = "Name of car")
    private @NotNull String name;
}
