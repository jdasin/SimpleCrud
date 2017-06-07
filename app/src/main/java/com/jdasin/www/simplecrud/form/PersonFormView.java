package com.jdasin.www.simplecrud.form;

import com.jdasin.www.simplecrud.entities.Person;

/**
 * Created by az on 07-06-17.
 */

public interface PersonFormView {
    void showLoader();
    void hideLoader();
    void savePerson();
    void loadPerson(Person person);
    void closeForm();
    void showError(String error);
}
