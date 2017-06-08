package com.jdasin.www.simplecrud.form;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.jdasin.www.simplecrud.R;
import com.jdasin.www.simplecrud.entities.Person;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonForm extends AppCompatActivity implements PersonFormView, DatePickerDialog.OnDateSetListener {

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
    @BindView(R.id.address_field)
    EditText addressField;

    private Person person;
    private PersonFormPresenter presenter;
    private Integer personId;
    public static final String DATE_FORMAT = "dd/MMM/yyyy";

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
        if (this.getIntent() != null) {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                personId = bundle.getInt("personId");
                presenter.loadPerson(personId);
            } else {
                presenter.loadPerson(null);
            }

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    public void showLoader() {
        //TODO Add loader not only disable inputs
        cancelButton.setEnabled(false);
        saveButton.setEnabled(false);
        nameField.setEnabled(false);
        phoneField.setEnabled(false);
        emailField.setEnabled(false);
        dateField.setEnabled(false);
        addressField.setEnabled(false);
    }

    @Override
    public void hideLoader() {
        cancelButton.setEnabled(true);
        saveButton.setEnabled(true);
        nameField.setEnabled(true);
        phoneField.setEnabled(true);
        emailField.setEnabled(true);
        dateField.setEnabled(true);
        addressField.setEnabled(true);
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
        addressField.setText(person.getAddress());
        if (person.getBirthDate() != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
            String formatedDate = simpleDateFormat.format(person.getBirthDate().getTime());
            dateField.setText(formatedDate);
        }
    }

    @Override
    public void closeForm() {
        this.finish();
    }

    @Override
    public void showError(String error) {
        Snackbar.make(nameField, "Error:" + error, Snackbar.LENGTH_SHORT);
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
        person.setAddress(addressField.getText().toString());
    }

    @OnClick(R.id.date_field)
    public void onViewClicked() {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(PersonForm.this, PersonForm.this, calendar
                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        String formatedDate = simpleDateFormat.format(calendar.getTime());
        dateField.setText(formatedDate);
        person.setDateFromString(formatedDate, DATE_FORMAT);
    }
}
