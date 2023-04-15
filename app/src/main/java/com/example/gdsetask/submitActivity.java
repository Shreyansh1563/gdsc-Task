package com.example.gdsetask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class submitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        Intent isubmit = getIntent();
        String []info = (isubmit.getStringExtra(MainActivity.stringkey)).split(",");
        TextView name = findViewById(R.id.nameTextView);
        TextView contacts = findViewById(R.id.contacts);
        TextView age = findViewById(R.id.age);
        TextView gender = findViewById(R.id.textView3);


        name.setText(info[0]);
        age.setText(info[1] + " years old");
        gender.setText(info[4]);
        ImageView img = findViewById(R.id.imageView2);
        contacts.setText(info[2] + "\n" + info[3]);
        img.setImageURI(isubmit.getParcelableExtra(MainActivity.imgkey));
    }
}