package com.jdasin.www.simplecrud.form;

import com.jdasin.www.simplecrud.entities.Person;
import com.jdasin.www.simplecrud.form.events.PersonFormEvent;
import com.jdasin.www.simplecrud.form.events.PersonFormEventType;
import com.jdasin.www.simplecrud.list.events.PeopleListEvent;
import com.jdasin.www.simplecrud.list.events.PersonSelectedEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by az on 07-06-17.
 */

public class PersonFormPresenterImpl implements PersonFormPresenter {

    private PersonFormModelImpl model;
    private PersonFormView view;

    public PersonFormPresenterImpl(PersonFormView view) {
        this.view = view;
        this.model = new PersonFormModelImpl();
    }

    @Override
    public void loadPerson(Integer personId) {
        view.showLoader();
        model.loadPerson(personId);
    }

    @Override
    public void savePerson(Person person) {
        view.showLoader();
        model.savePerson(person);
    }

    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(sticky = true)
    public void onEvent(Object event) {
        if (event instanceof PersonFormEvent) {
            PersonFormEvent pfEvent = (PersonFormEvent)event;
            if (pfEvent.getSucces()) {
                view.loadPerson(pfEvent.getPerson());
                if (pfEvent.getEventType() == PersonFormEventType.SAVED) {
                    view.closeForm();
                }
            } else {
                view.showError(pfEvent.getError());
            }
            view.hideLoader();
        }

    }
}
