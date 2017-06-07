package com.jdasin.www.simplecrud.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jdasin.www.simplecrud.R;
import com.jdasin.www.simplecrud.entities.Person;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PeopleActivity extends AppCompatActivity implements PeopleListView {

    @BindView(R.id.peopleRV)
    RecyclerView peopleRV;
    private List<Person> people;

    private PersonAdapter personAdapter;
    private PeopleListPresenter presenter;
    private void initializeData() {
        people = new ArrayList<>();
        people.add(new Person("Will Smith", "will@asdf.com"));
        people.add(new Person("Jhohan Sebastian Mastropiero.","mastropiero@asdf.com"));
        people.add(new Person("John Constantine","const@asdf.com"));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        ButterKnife.bind(this);
        presenter = new PeopleListPresenterImpl(this);
        presenter.onCreate();
        people = new ArrayList<>();
        peopleRV.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        peopleRV.setLayoutManager(layoutManager);
        personAdapter = new PersonAdapter(people);
        peopleRV.setAdapter(personAdapter);
        presenter.loadPeople(1);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showSpinner() {

    }

    @Override
    public void hideSpinner() {

    }

    @Override
    public void onPeoplePageLoaded(List<Person> people) {
        this.people.addAll(people);
        personAdapter.notifyDataSetChanged();
    }
}
