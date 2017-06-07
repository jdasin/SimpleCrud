package com.jdasin.www.simplecrud.form.events;

import com.jdasin.www.simplecrud.entities.Person;

/**
 * Created by az on 07-06-17.
 */

public class PersonFormEvent {
    private Person person;
    private Boolean succes;
    private String error;
    private PersonFormEventType eventType;
    public PersonFormEvent(Person person, Boolean succes, String error, PersonFormEventType eventType) {
        this.person = person;
        this.succes = succes;
        this.error = error;
        this.setEventType(eventType);
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

    public PersonFormEventType getEventType() {
        return eventType;
    }

    public void setEventType(PersonFormEventType eventType) {
        this.eventType = eventType;
    }
}
