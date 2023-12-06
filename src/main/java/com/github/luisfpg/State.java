package com.github.luisfpg;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PostLoad;

@Entity
public class State {
    @Id
    private String code;

    @Basic
    private String name;

    // Only to cascade remove
    @OneToMany(mappedBy = "state", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<City> cities = new HashSet<>();

    public State() {
    }

    public State(String code, String name) {
        this.code = code;
        this.name = name;
    }
    
    public Set<City> getCities() {
		return cities;
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

    @PostLoad
    public void postLoad() {
        System.out.printf("Loaded state %s%n", code);
    }

}