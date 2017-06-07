package com.jdasin.www.simplecrud.list.events;

/**
 * Created by az on 06-06-17.
 */

public class PersonSelectedEvent {
    private int personId;
    private PersonSelectedEventType eventType;
    public PersonSelectedEvent(int personId, PersonSelectedEventType eventType) {
        this.personId = personId;
        this.setEventType(eventType);
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public PersonSelectedEventType getEventType() {
        return eventType;
    }

    public void setEventType(PersonSelectedEventType eventType) {
        this.eventType = eventType;
    }
}
