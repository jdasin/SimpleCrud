package com.jdasin.www.simplecrud.list;

import com.jdasin.www.simplecrud.entities.Person;

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
    EventBus eventBus;
    public PeopleListPresenterImpl(PeopleListView view) {
        this.peopleListView = view;
        this.peopleListModel = new PeopleListModelImpl();
        eventBus = EventBus.getDefault();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
    }

    @Override
    public void loadPeople(int page) {
        peopleListView.showSpinner();
        peopleListModel.loadPeople(page);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(PeopleListEvent event) {
        onPeoplePageLoaded(event.getPeople());
    }

    public void onPeoplePageLoaded(List<Person> people) {
        peopleListView.onPeoplePageLoaded(people);
        peopleListView.hideSpinner();
    }
}