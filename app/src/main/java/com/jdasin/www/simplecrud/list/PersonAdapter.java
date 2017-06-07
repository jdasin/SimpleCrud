package com.jdasin.www.simplecrud.list;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jdasin.www.simplecrud.R;
import com.jdasin.www.simplecrud.entities.Person;
import com.jdasin.www.simplecrud.list.events.PeopleListEvent;
import com.jdasin.www.simplecrud.list.events.PersonSelectedEvent;

import org.greenrobot.eventbus.EventBus;

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
        Person person = people.get(position);
        personViewHolder.getPersonName().setText(person.getName());
        personViewHolder.getPersonEmail().setText(person.getEmail());
        personViewHolder.setPersonId(person.getPersonId());
    }


    @Override
    public int getItemCount() {
        return people.size();
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        private CardView cv;
        private TextView personName;
        private TextView personEmail;
        private Button editPersonButton;
        private Button deletePersonButton;
        private int personId;
        EventBus eventBus;

        PersonViewHolder(View personView) {
            super(personView);
            setCv((CardView)personView.findViewById(R.id.card_view));
            setPersonName((TextView)personView.findViewById(R.id.person_name));
            setPersonEmail((TextView)personView.findViewById(R.id.person_email));
            setEditPersonButton((Button)personView.findViewById(R.id.edit));
            setDeletePersonButton((Button)personView.findViewById(R.id.delete));
            eventBus = EventBus.getDefault();
            getEditPersonButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    eventBus.postSticky(new PersonSelectedEvent(personId));
                }
            });
            getDeletePersonButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    eventBus.postSticky(new PersonSelectedEvent(personId));
                }
            });
        }

        public CardView getCv() {
            return cv;
        }

        public void setCv(CardView cv) {
            this.cv = cv;
        }

        public TextView getPersonName() {
            return personName;
        }

        public void setPersonName(TextView personName) {
            this.personName = personName;
        }

        public TextView getPersonEmail() {
            return personEmail;
        }

        public void setPersonEmail(TextView personEmail) {
            this.personEmail = personEmail;
        }

        public Button getEditPersonButton() {
            return editPersonButton;
        }

        public void setEditPersonButton(Button editPersonButton) {
            this.editPersonButton = editPersonButton;
        }

        public Button getDeletePersonButton() {
            return deletePersonButton;
        }

        public void setDeletePersonButton(Button deletePersonButton) {
            this.deletePersonButton = deletePersonButton;
        }

        public int getPersonId() {
            return personId;
        }

        public void setPersonId(int personId) {
            this.personId = personId;
        }
    }

}
