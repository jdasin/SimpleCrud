package com.jdasin.www.simplecrud.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.jdasin.www.simplecrud.R;
import com.jdasin.www.simplecrud.entities.Person;
import com.jdasin.www.simplecrud.form.PersonForm;
import com.jdasin.www.simplecrud.list.events.PersonSelectedEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PeopleActivity extends AppCompatActivity implements PeopleListView {

    @BindView(R.id.peopleRV)
    RecyclerView peopleRV;
    @BindView(R.id.add_person_button)
    FloatingActionButton addPersonButton;
    private List<Person> people;

    private PersonAdapter personAdapter;
    private PeopleListPresenter presenter;

    private void initializeData() {
        people = new ArrayList<>();
        people.add(new Person("Will Smith", "will@asdf.com"));
        people.add(new Person("Jhohan Sebastian Mastropiero.", "mastropiero@asdf.com"));
        people.add(new Person("John Constantine", "const@asdf.com"));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        ButterKnife.bind(this);
        presenter = new PeopleListPresenterImpl(this);
//        EventBus.getDefault().register(this);
        people = new ArrayList<>();
        peopleRV.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        peopleRV.setLayoutManager(layoutManager);
        personAdapter = new PersonAdapter(people);
        peopleRV.setAdapter(personAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadPeople(1);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onDestroy();
    }

    @Override
    public void showSpinner() {

    }

    @Override
    public void hideSpinner() {

    }

    @Override
    public void onPeoplePageLoaded(List<Person> people) {
        this.people.clear();
        this.people.addAll(people);
        personAdapter.notifyDataSetChanged();
        Toast.makeText(this, "load", Toast.LENGTH_SHORT);
    }

    @Override
    public void onPersonSelected(int personId) {
        Toast.makeText(this, "" + personId, Toast.LENGTH_SHORT);
    }

    @OnClick(R.id.add_person_button)
    public void onViewClicked(View v) {
        Intent intent = new Intent(this, PersonForm.class);
        this.startActivity(intent);
    }
}
