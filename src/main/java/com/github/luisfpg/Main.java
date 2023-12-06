package com.github.luisfpg;

import org.eclipse.persistence.indirection.IndirectSet;

import jakarta.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        var emf = Persistence.createEntityManagerFactory("default");
        try (var em = emf.createEntityManager()) {
            var transaction = em.getTransaction();
            transaction.begin();
            var count = (Number) em.createQuery("select count(s.code) from State s").getSingleResult();
            if (count.intValue() == 0) {
                try {
                    var state1 = new State("s1", "State 1");
                    em.persist(state1);
                    em.persist(new City(state1, "s1a", "City A"));
                    em.persist(new City(state1, "s1b", "City B"));
                    var state2 = new State("s2", "State 2");
                    em.persist(state2);
                    em.persist(new City(state2, "s2x", "City X"));
                    em.persist(new City(state2, "s2y", "City Y"));
                    transaction.commit();
                } catch (Exception e) {
                    transaction.rollback();
                }
                System.out.println("Created some data");
            }
        }

        try (var em = emf.createEntityManager()) {
            var state = em.find(State.class, "s1");
            var cities = (IndirectSet<City>) state.getCities();
            System.out.printf("Is collection initialized? %s%n", cities.isInstantiated());
            System.out.println("Before detach");
            em.detach(state);
            System.out.printf("State %s detached%n", state.getCode());
            System.out.printf("Is collection initialized? %s%n", cities.isInstantiated());
        }
    }
}
