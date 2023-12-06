package com.github.luisfpg;
import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PostLoad;

@Entity
public class City {
    @Id
    private String code;

    @Basic
    private String name;

    @ManyToOne
    private State state;

    public City() {
    }

    public City(State state, String code, String name) {
        this.state = state;
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @PostLoad
    public void postLoad() {
        System.out.printf("Loaded city %s%n", code);
    }
}