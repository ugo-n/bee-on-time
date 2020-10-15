package com.example.otherpart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    // Buttons take user to different pages such as MoodCalander , Checklist etc. dis
    Button option1_button;
    Button option2_button;
    Button option3_button;
    Button option4_button; // For future use
    Button option5_button; // For future use

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Starts the new Activity and a new Intent instance. It loads up the Otherpart2.class
        // It loads up a specific animation based on the R.id.
        option1_button = findViewById(R.id.option1_button);
        option1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent (MainActivity.this, Otherpart2.class ) );
                if (view.getId() == R.id.option1_button) {
                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                }
            }
        });

        option2_button= findViewById(R.id.option2_button);
        option2_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent (MainActivity.this, PositivePhrases.class ) );
                if (view.getId() == R.id.option2_button) {
                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                }
            }
        });

        option3_button = findViewById(R.id.option3_button);
        option3_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent (MainActivity.this, ShoppingList.class ) );
                if (view.getId() == R.id.option3_button) {
                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                }
            }
        });



        option4_button= findViewById(R.id.option4_button);
        option4_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent (MainActivity.this, Calendar.class ) );
                if (view.getId() == R.id.option4_button) {
                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                }
            }
        });

        option5_button= findViewById(R.id.option5_button);
        option5_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent (MainActivity.this, Calendar.class ) );
                if (view.getId() == R.id.option2_button) {
                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                }
            }
        });


    }
        // Can't seem to take this out without it erroring out. So keeping it here.
        public void onClick (View view){

        }
    }
