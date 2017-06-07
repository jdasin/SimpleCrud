package com.jdasin.www.simplecrud.list;

import com.jdasin.www.simplecrud.entities.Person;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by az on 05-06-17.
 */

public class PeopleListModelImpl implements PeopleListModel {
    @Override
    public void loadPeople(int page) {
        List<Person> people = new ArrayList<>();
        people = new ArrayList<>();
        people.add(new Person("Will Smith", "will@asdf.com"));
        people.add(new Person("Jhohan Sebastian Mastropiero.","mastropiero@asdf.com"));
        people.add(new Person("John Constantine","const@asdf.com"));
        EventBus.getDefault().post(new PeopleListEvent(people));
    }
}
