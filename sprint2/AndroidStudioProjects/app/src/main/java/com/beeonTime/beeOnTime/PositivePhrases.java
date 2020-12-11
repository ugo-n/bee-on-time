package com.beeonTime.beeOnTime;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.*;

// This class holds the positive phrases implementation.
public class PositivePhrases extends AppCompatActivity {

    // User clicks this to activate the boost
    Button positivebutton;
    ImageView bee;
    ImageView bee2;


    Boolean isClicked = false;

    MediaPlayer happySound;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_positive_phrases);

        // Gets the phrase by button and has an
        // eventlistener to check if use clicked on the  button
        positivebutton = findViewById(R.id.positivebutton);

        // This is the image resource for the bee image
        bee = findViewById(R.id.beeimagev1);
        bee2 = findViewById(R.id.beeimagev2); // should be the wink face

        // This will hold the small sound that plays after clicking the button
        happySound = MediaPlayer.create(PositivePhrases.this,R.raw.buttonsound);

        positivebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isClicked = true;




                // A list of positive phrases to choose from
                String[] list = {
                        "You can do it !",
                        "Work hard, Play hard !",
                        "Keep going!" ,
                        "As long as you keep moving, you are always making progress! :)",
                        "Progress is progress no matter how small the change is.",
                        "Don’t give up!",
                        "If others can do it, so can you!" ,
                        "Make the change that you want! ( alt: Be the change that you want to see !)",
                        "Failure just means the opportunity to learn and grow! Embrace it :)",
                        "Tomorrow is a new day filled with new opportunities!",

                        "Great work!",
                        "You’re one more step towards your goal!",
                        "Awesome! Let’s go for another!",
                        "You’re an allstar!",
                        "Amazing! ",
                        "Alright!",
                        "Yay! You did it!",
                        "You’ve got this!",
                        "Well done!",
                        "You’re on a roll",

                        "TODAY only happens once, make it amazing!",
                        "BEE*lieve you can, and you are half-way there.",
                        "You have to be odd, to be number ONE!",
                        "Refuse to lose",
                        "Rise & Grind",
                        "Difficult roads often lead to beautiful destinations",
                        "BEE Unstoppable!",
                        "BEE true to yourself",
                        "BEE extraordinary today",

                        "You’re on the road to success !!",
                        "The next best time to start is now !!",
                        "BEEautiful finish !! There’s nothing you can’t do. :D ",
                        "Can’t stop, won’t stop - the path to success continues. >:D ",
                        "You’re doing great !! ",
                        "Nice one !! ",
                        "Feel free to give yourself a pat on the back! You’ve earned it! ",
                        "I’m proud of you already. :) ",
                        "The hardest part is starting. Jump right in !!",
                        "You’re on fire !! Keep at it !!"

                };


                // Grabs a random number/phrase to give to the user
                Random rand = new Random();
                int phraselimit = 39; // 39 availible phrases
                int int_random = rand.nextInt(phraselimit);
                String random = list[int_random] ; // value inside

                // Displays that random phrase to the user via a button
                ((TextView)findViewById(R.id.positivebutton)).setText(random);





                happySound.start();






                //This causes the bee to wink some of the time
                if(int_random % 2 == 0) {
                    bee2.setVisibility(View.INVISIBLE);
                    bee.setVisibility(View.VISIBLE);


                } else  {

                    bee.setVisibility(View.INVISIBLE);
                    bee2.setVisibility(View.VISIBLE);

                }

            }


        });


    }

    public void printThis(View view) {
    }
}