package com.jdasin.www.simplecrud.list;

import com.jdasin.www.simplecrud.entities.Person;

import java.util.List;

/**
 * Created by az on 05-06-17.
 */

public interface PeopleListView {
    void showSpinner();
    void hideSpinner();
    void onPeoplePageLoaded(List<Person> person);
}
