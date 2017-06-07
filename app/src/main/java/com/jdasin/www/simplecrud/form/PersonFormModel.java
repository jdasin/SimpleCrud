package com.jdasin.www.simplecrud.form;

import com.jdasin.www.simplecrud.entities.Person;

/**
 * Created by az on 07-06-17.
 */

public interface PersonFormModel {
    public void loadPerson(Integer personId);
    public void savePerson(Person person);
}
