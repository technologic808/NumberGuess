package com.daftech.music.numberguess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int random = randomGen();
    EditText guess;
    Button repeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        guess = findViewById(R.id.guess);
        guess.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    int number = Integer.parseInt(guess.getText().toString());
                    isItRight(number, random);
                    handled = true;
                }
                return handled;
            }
        });
        repeat = findViewById(R.id.repeat);
        repeat.setVisibility(View.INVISIBLE);
        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Logic for when repeat is pressed
                random = randomGen();
                guess.setVisibility(View.VISIBLE);
                repeat.setVisibility(View.INVISIBLE);
            }
        });
    }

    public int randomGen() {
        return new Random().nextInt(1000);
    }

    ;

    public void isItRight(int number, int random) {
        if (number < random) {
            Toast.makeText(MainActivity.this, "Hint: It's higher!", Toast.LENGTH_SHORT).show();
        }
        if (number > random) {
            Toast.makeText(MainActivity.this, "Hint: It's lower!", Toast.LENGTH_SHORT).show();
        }
        if (number == random) {
            Toast.makeText(MainActivity.this, "You got it! You have Won!", Toast.LENGTH_SHORT).show();
            guess.setVisibility(View.INVISIBLE);
            repeat.setVisibility(View.VISIBLE);
        }
    }
}