package com.example.am99m.booksharingapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SetPassword extends AppCompatActivity {

    EditText pass1,pass2;
    String pas1,pas2,name,number;
    Button submit;
    artist art;
    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);
        pass1=(EditText)findViewById(R.id.pass);
        pass2=(EditText)findViewById(R.id.cnf_pass);
        submit=(Button)findViewById(R.id.Submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pas1=pass1.getText().toString().trim();
                pas2=pass2.getText().toString().trim();

                if(pas1.equals(pas2) && pas1.length()!=0)
                {
                    name=getIntent().getExtras().getString("name");
                    number=getIntent().getExtras().getString("mobile");
                    DatabaseReference ref= FirebaseDatabase.getInstance().getReference("User").child(number);
                    art=new artist(name,number,pas1);
                    ref.setValue(art);
                    sqLiteDatabase = getApplicationContext().openOrCreateDatabase("rec", MODE_APPEND, null);
                    sqLiteDatabase.execSQL("CREATE table if not exists login(mobile VARCHAR);");
                    sqLiteDatabase.execSQL("INSERT into login values('" + number + "');");
                    Intent main=new Intent(SetPassword.this,homePage.class);
                    startActivity(main);
                }
                else
                {
                    Toast.makeText(SetPassword.this, "Passwords doesn't match", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
