package com.example.am99m.booksharingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ngo_details extends AppCompatActivity {

    TextView nameText,addressText,contactText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_details);
        nameText = (TextView)findViewById(R.id.name);
        addressText = (TextView)findViewById(R.id.address) ;
        contactText = (TextView)findViewById(R.id.contact);
        String name = getIntent().getExtras().get("name").toString();
        String add = getIntent().getExtras().get("address").toString();
        String contact = getIntent().getExtras().get("contact").toString();
        nameText.setText(name);
        addressText.setText(add);
        contactText.setText(contact);

    }
}
