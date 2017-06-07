package com.jdasin.www.simplecrud.form;

import com.jdasin.www.simplecrud.entities.Person;
import com.jdasin.www.simplecrud.entities.Person_Table;
import com.jdasin.www.simplecrud.form.events.PersonFormEvent;
import com.jdasin.www.simplecrud.form.events.PersonFormEventType;
import com.jdasin.www.simplecrud.list.events.PeopleListEvent;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.greenrobot.eventbus.EventBus;

import static com.raizlabs.android.dbflow.sql.language.property.PropertyFactory.from;

/**
 * Created by az on 07-06-17.
 */

public class PersonFormModelImpl implements PersonFormModel{
    @Override
    public void loadPerson(Integer personId) {
        Person person = null;
        String errors = null;
        try {
            if (personId != null) {
                person = SQLite.select().from(Person.class)
                        .where(Person_Table.person_id.eq(personId)).querySingle();
            } else {
                person = new Person();
            }
        } catch (Exception ex) {
            errors = ex.getMessage();
        }
        EventBus.getDefault().post(new PersonFormEvent(person, errors == null, errors, PersonFormEventType.LOADED));
    }

    @Override
    public void savePerson(Person person) {
        String errors = null;
        try {
            person.save();
        } catch (Exception ex) {
            errors = ex.getMessage();
        }
        EventBus.getDefault().post(new PersonFormEvent(person, errors == null, errors, PersonFormEventType.SAVED));
    }
}
