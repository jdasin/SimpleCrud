package com.jdasin.www.simplecrud.list;

import com.jdasin.www.simplecrud.entities.Person;
import com.jdasin.www.simplecrud.list.events.PeopleListEvent;
import com.jdasin.www.simplecrud.list.events.PersonDeletedEvent;
import com.jdasin.www.simplecrud.list.events.PersonSelectedEvent;
import com.jdasin.www.simplecrud.list.events.PersonSelectedEventType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by az on 05-06-17.
 */

public class PeopleListPresenterImpl implements PeopleListPresenter {

    PeopleListView peopleListView;
    PeopleListModel peopleListModel;

    public PeopleListPresenterImpl(PeopleListView view) {
        this.peopleListView = view;
        this.peopleListModel = new PeopleListModelImpl();
    }

    @Override
    public void onCreate() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void loadPeople(int page) {
        peopleListView.showSpinner();
        peopleListModel.loadPeople(page);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPeopleListEvent(Object event) {
        if (event instanceof PeopleListEvent) {
            onPeoplePageLoaded(((PeopleListEvent)event).getPeople());
        } else if (event instanceof PersonSelectedEvent) {
            PersonSelectedEvent psEvent = (PersonSelectedEvent)event;
            if (psEvent.getEventType() == PersonSelectedEventType.UPDATE) {
                peopleListView.onPersonSelected(psEvent.getPersonId());
            } else {
                peopleListModel.deletePerson(psEvent.getPersonId());
            }

        } else if (event instanceof PersonDeletedEvent) {
            loadPeople(1);
        }
    }


    public void onPeoplePageLoaded(List<Person> people) {
        peopleListView.onPeoplePageLoaded(people);
        peopleListView.hideSpinner();
    }
}
