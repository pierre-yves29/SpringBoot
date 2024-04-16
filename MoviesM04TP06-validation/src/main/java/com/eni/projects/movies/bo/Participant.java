package com.eni.projects.movies.bo;

/**
 * Model for a participant
 * @version 1.0
 * @author qprigent
 */
public class Participant extends Person {
    public Participant() {
        super();
    }

    public Participant(String lastName, String firstName) {
        super(lastName, firstName);
    }

    public Participant(long id, String lastName, String firstName) {
        super(id, lastName, firstName);
    }
}
