package com.jdasin.www.simplecrud.list;

import com.jdasin.www.simplecrud.entities.Person;
import com.jdasin.www.simplecrud.entities.Person_Table;
import com.jdasin.www.simplecrud.list.events.PeopleListEvent;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by az on 05-06-17.
 */

public class PeopleListModelImpl implements PeopleListModel {
    @Override
    public void loadPeople(int page) {
        List<Person> people = SQLite.select().
                from(Person.class).
                queryList();
        EventBus.getDefault().postSticky(new PeopleListEvent(people));

    }

    @Override
    public void deletePerson(int personId) {
        Person person = null;
        person = SQLite.select().
                from(Person.class).where(Person_Table.person_id.eq(personId)).
                querySingle();
        if (person != null) {
            person.delete();
        }
        loadPeople(1);
    }
}
