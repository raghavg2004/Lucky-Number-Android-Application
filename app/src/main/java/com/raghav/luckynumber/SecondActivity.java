package com.raghav.luckynumber;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {

    TextView welcomeTxt, luckyNumberTxt;
    Button share_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        welcomeTxt = findViewById(R.id.textView2);
        luckyNumberTxt = findViewById(R.id.lucky_number_txt);
        share_btn = findViewById(R.id.share_btn);

        // Receiving Data from Main Activity
        Intent i =getIntent();
        String userName = i.getStringExtra("name");

        // Generating Random Number
        int random_num = generateRandomNumbers(); // calling of the function 'generateRandomNumbers'
        luckyNumberTxt.setText(""+random_num);

        share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareData(userName, random_num);
            }
        });
    }

    public int generateRandomNumbers(){
        Random random = new Random();
        int upper_limit = 1000;   // Random number between 0 to 1000

        int randomNumberGenerated = random.nextInt(upper_limit);
        return randomNumberGenerated;
    }

    void shareData(String userName, int randomNum){
        // Explicit Intent
        Intent i =new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");

        i.putExtra(Intent.EXTRA_SUBJECT, userName + "Got Lucky Today");
        i.putExtra(Intent.EXTRA_TEXT,  "Lucky Number is : "+randomNum);

        startActivity(Intent.createChooser(i,"Choose a Platform"));
    }
}