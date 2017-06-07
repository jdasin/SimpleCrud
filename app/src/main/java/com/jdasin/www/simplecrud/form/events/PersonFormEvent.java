package com.jdasin.www.simplecrud.form.events;

import com.jdasin.www.simplecrud.entities.Person;

/**
 * Created by az on 07-06-17.
 */

public class PersonFormEvent {
    private Person person;
    private Boolean succes;
    private String error;

    public PersonFormEvent(Person person, Boolean succes, String error) {
        this.person = person;
        this.succes = succes;
        this.error = error;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Boolean getSucces() {
        return succes;
    }

    public void setSucces(Boolean succes) {
        this.succes = succes;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
