package com.example.am99m.booksharingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ChatActivity extends AppCompatActivity {
EditText e1;ImageView iv;
TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        iv = (ImageView)findViewById(R.id.img);
        e1 = (EditText)findViewById(R.id.chat);
        t1 = (TextView)findViewById(R.id.text);
        //final String s = e1.getText().toString();
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ChatActivity.this, e1.getText().toString(), Toast.LENGTH_SHORT).show();
                t1.setText(e1.getText().toString());
            }
        });

    }
}
