package com.example.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivitySuccessfulLogin extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_successful_login);

        MaterialButton logOffButton = findViewById(R.id.logOffButton);
        TextView role = findViewById(R.id.role);

        MaterialButton viewApplicantsButton = findViewById(R.id.viewApplicantsButton);
        MaterialButton viewRejectedApplicantsButton = findViewById(R.id.viewRejectedApplicantsButton);

        MaterialButton addShiftButton = findViewById(R.id.addShiftButton);
        MaterialButton viewPastAppointmentsButton = findViewById(R.id.viewPastAppointmentsButton);
        MaterialButton viewUpcomingAppointmentsButton = findViewById(R.id.viewUpcomingAppointmentsButton);
        MaterialButton viewShiftsButton = findViewById(R.id.viewShiftsButton);

        LinearLayout viewPastAppointmentsLayout = findViewById(R.id.viewPastAppointmentsLayout);
        LinearLayout viewUpcomingAppointmentsLayout = findViewById(R.id.viewUpcomingAppointmentsLayout);

        MaterialButton addAppointmentButton = findViewById(R.id.addAppointmentButton);

        String[] personDetails = getIntent().getStringArrayExtra("person details");
        String personType = personDetails[0];
        String username = personDetails[1];

        role.setText(personType);

        if (personType.equals("Admin")) {
            viewApplicantsButton.setVisibility(View.VISIBLE);
            viewRejectedApplicantsButton.setVisibility(View.VISIBLE);
        }
        else if (personType.equals("Doctor")) {
            addShiftButton.setVisibility(View.VISIBLE);
            viewPastAppointmentsButton.setVisibility(View.VISIBLE);
            viewUpcomingAppointmentsButton.setVisibility(View.VISIBLE);
            viewUpcomingAppointmentsButton.setVisibility(View.VISIBLE);
            viewShiftsButton.setVisibility(View.VISIBLE);
        } else {
            addAppointmentButton.setVisibility(View.VISIBLE);

            ViewGroup.MarginLayoutParams vlpUpcoming = (ViewGroup.MarginLayoutParams) viewUpcomingAppointmentsLayout.getLayoutParams();
            ViewGroup.MarginLayoutParams vlpPast = (ViewGroup.MarginLayoutParams) viewPastAppointmentsLayout.getLayoutParams();

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );

            vlpUpcoming.bottomMargin -= 135;
            vlpPast.bottomMargin -= 135;

            viewUpcomingAppointmentsButton.setVisibility(View.VISIBLE);
            viewPastAppointmentsButton.setVisibility(View.VISIBLE);
        }

        viewApplicantsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivitySuccessfulLogin.this, MainActivityViewApplicants.class);
                startActivity(intent);
            }
        });

        viewRejectedApplicantsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivitySuccessfulLogin.this, MainActivityViewRejectedApplicants.class);
                startActivity(intent);
            }
        });

        viewUpcomingAppointmentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (personType.equals("Doctor")) {
                    Intent intent = new Intent(MainActivitySuccessfulLogin.this, MainActivityDoctorViewUpcomingAppointments.class);
                    intent.putExtra("doctorUsername", username);
                    startActivity(intent);
                } else if (personType.equals("Patient")) {
                    Intent intent = new Intent(MainActivitySuccessfulLogin.this, MainActivityPatientViewUpcomingAppointments.class);
                    intent.putExtra("patientUsername", username);
                    startActivity(intent);
                }

            }
        });

        viewPastAppointmentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (personType.equals("Doctor")) {
                    Intent intent = new Intent(MainActivitySuccessfulLogin.this, MainActivityDoctorViewPastAppointments.class);
                    intent.putExtra("doctorUsername", username);
                    startActivity(intent);
                } else if (personType.equals("Patient")) {
                    Intent intent = new Intent(MainActivitySuccessfulLogin.this, MainActivityPatientViewPastAppointments.class);
                    intent.putExtra("patientUsername", username);
                    startActivity(intent);
                }

            }
        });

        viewShiftsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivitySuccessfulLogin.this, MainActivityViewShifts.class);
                intent.putExtra("doctorUsername", username);
                startActivity(intent);
            }
        });

        addShiftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivitySuccessfulLogin.this, MainActivityCreateShift.class);
                intent.putExtra("user", username);
                startActivity(intent);
            }
        });

        addAppointmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivitySuccessfulLogin.this, MainActivityPatientAddAppointment.class);
                intent.putExtra("", personType);
                intent.putExtra("Username", username);
                startActivity(intent);
            }
        });


        logOffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform logout actions if needed

                // Redirect to the login page
                Intent intent = new Intent(MainActivitySuccessfulLogin.this, MainActivityHome.class);
                startActivity(intent);
                finish(); // Close the current activity to prevent going back to it using the back button
            }
        });
    }
}