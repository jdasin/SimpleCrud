package com.jdasin.www.simplecrud.list;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jdasin.www.simplecrud.R;
import com.jdasin.www.simplecrud.entities.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by az on 05-06-17.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {

    PeopleListPresenter presenter;
    private List<Person> people;

    public PersonAdapter() {
        people = new ArrayList<>();
    }

    public PersonAdapter(List<Person> people) {
        this.people = people;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.person_card, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int position) {
        personViewHolder.personName.setText(people.get(position).getName());
        personViewHolder.personEmail.setText(people.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView personName;
        TextView personEmail;

        PersonViewHolder(View personView) {
            super(personView);
            cv = (CardView)personView.findViewById(R.id.card_view);
            personName = (TextView)personView.findViewById(R.id.person_name);
            personEmail = (TextView)personView.findViewById(R.id.person_email);
        }
    }

}
