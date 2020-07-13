package com.example.am99m.booksharingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class donatebook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donatebook);
        LinearLayout seraphina_trust = (LinearLayout)findViewById(R.id.seraphina_trust);
        LinearLayout thrill_zone = (LinearLayout)findViewById(R.id.thrill_zone);
        LinearLayout youth_foundation = (LinearLayout)findViewById(R.id.youth_foundation);
        LinearLayout samadhan_ngo = (LinearLayout)findViewById(R.id.samadhan_ngo);
        LinearLayout human_rights_centre = (LinearLayout)findViewById(R.id.human_rights_centre);
        LinearLayout ukssmanch = (LinearLayout)findViewById(R.id.ukssmanch);

        seraphina_trust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent seraphina_trust = new Intent(donatebook.this,ngo_details.class);
                seraphina_trust.putExtra("name","seraphina_trust");
                seraphina_trust.putExtra("address","Society Area Clement Town");
                seraphina_trust.putExtra("contact","9876543210");
                startActivity(seraphina_trust);
            }
        });

        thrill_zone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent seraphina_trust = new Intent(donatebook.this,ngo_details.class);
                seraphina_trust.putExtra("name","thrill_zone");
                seraphina_trust.putExtra("address","Lane Canal Road Dehradun");
                seraphina_trust.putExtra("contact","9876543210");

                startActivity(seraphina_trust);
            }
        });

        youth_foundation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent seraphina_trust = new Intent(donatebook.this,ngo_details.class);
                seraphina_trust.putExtra("name","youth_foundation");
                seraphina_trust.putExtra("address","Gajendra Vihar");
                seraphina_trust.putExtra("contact","9876543210");
                startActivity(seraphina_trust);
            }
        });

        samadhan_ngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent seraphina_trust = new Intent(donatebook.this,ngo_details.class);
                seraphina_trust.putExtra("name","samadhan_ngo");
                seraphina_trust.putExtra("address","Jakhan Rajpur Road");
                seraphina_trust.putExtra("contact","9876543210");
                startActivity(seraphina_trust);
            }
        });

        human_rights_centre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent seraphina_trust = new Intent(donatebook.this,ngo_details.class);
                seraphina_trust.putExtra("name","human_rights_centre");
                seraphina_trust.putExtra("address","Society B-158 Dehradun");
                seraphina_trust.putExtra("contact","9876543210");
                startActivity(seraphina_trust);
            }
        });

        ukssmanch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent seraphina_trust = new Intent(donatebook.this,ngo_details.class);
                seraphina_trust.putExtra("name","ukssmanch");
                seraphina_trust.putExtra("address","Shimla Bypass Road");
                seraphina_trust.putExtra("contact","9876543210");
                startActivity(seraphina_trust);
            }
        });

    }
}
