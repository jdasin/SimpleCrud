package com.jdasin.www.simplecrud.list.events;

/**
 * Created by az on 06-06-17.
 */

public class PersonSelectedEvent {
    private int personId;

    public PersonSelectedEvent(int personId) {
        this.personId = personId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}
