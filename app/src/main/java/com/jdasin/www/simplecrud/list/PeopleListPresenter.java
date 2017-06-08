package com.jdasin.www.simplecrud.list;

/**
 * Created by az on 05-06-17.
 */

public interface PeopleListPresenter {
    public void onStart();
    public void onStop();
    public void loadPeople(int page);

}
