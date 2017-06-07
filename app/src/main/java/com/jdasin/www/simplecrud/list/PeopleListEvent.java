package com.jdasin.www.simplecrud.list;

import com.jdasin.www.simplecrud.entities.Person;

import java.util.List;

/**
 * Created by az on 06-06-17.
 */

public class PeopleListEvent {
    private List<Person> people;
    public PeopleListEvent(List<Person> people) {
        this.setPeople(people);
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }
}
