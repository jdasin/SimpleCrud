package com.jdasin.www.simplecrud.form;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jdasin.www.simplecrud.R;
import com.jdasin.www.simplecrud.entities.Person;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonForm extends AppCompatActivity implements PersonFormView {

    @BindView(R.id.name_field)
    EditText nameField;
    @BindView(R.id.email_field)
    EditText emailField;
    @BindView(R.id.date_field)
    EditText dateField;
    @BindView(R.id.phone_field)
    EditText phoneField;
    @BindView(R.id.save_button)
    Button saveButton;
    @BindView(R.id.cancel_button)
    Button cancelButton;

    private Person person;
    private PersonFormPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_form);
        ButterKnife.bind(this);
        presenter = new PersonFormPresenterImpl(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
        presenter.loadPerson(null);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    public void showLoader() {

    }

    @Override
    public void hideLoader() {

    }

    @Override
    public void savePerson() {
    }

    @Override
    public void loadPerson(Person person) {
        this.person = person;
        nameField.setText(person.getName());
        emailField.setText(person.getEmail());
        phoneField.setText(person.getPhoneNumber());
    }

    @Override
    public void closeForm() {
        this.finish();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, "Error:" + error, Toast.LENGTH_SHORT);
    }

    @OnClick({R.id.save_button, R.id.cancel_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.save_button:
                updatePersonInstance(person);
                presenter.savePerson(person);
                break;
            case R.id.cancel_button:
                closeForm();
                break;
        }
    }

    private void updatePersonInstance(Person person) {
        person.setName(nameField.getText().toString());
        person.setEmail(emailField.getText().toString());
        person.setPhoneNumber(phoneField.getText().toString());
    }
}
