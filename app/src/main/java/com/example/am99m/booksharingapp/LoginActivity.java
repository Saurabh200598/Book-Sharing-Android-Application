package com.example.am99m.booksharingapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    EditText user,pass;
    Button signUp,login,forgot;
    RelativeLayout lay1,lay2;
    SQLiteDatabase sqLiteDatabase;
    Handler handler=new Handler();
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            lay1.setVisibility(View.VISIBLE);
            lay2.setVisibility(View.VISIBLE);
        }
    };
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        lay1=(RelativeLayout)findViewById(R.id.rel_lay1);
        lay2=(RelativeLayout)findViewById(R.id.rel_lay2);
        user=(EditText)findViewById(R.id.mobile);
        pass=(EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.login_Btn);

        handler.postDelayed(runnable,2000);
        signUp=(Button)findViewById(R.id.signUp);
        forgot =(Button)findViewById(R.id.forgot_pass);

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUp=new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(signUp);
            }
        });

        try {
            sqLiteDatabase = this.openOrCreateDatabase("rec", MODE_APPEND, null);
            Cursor cursor = sqLiteDatabase.rawQuery("Select * from login", null);
            cursor.moveToLast();
            if (!cursor.getString(0).equals("")) {
                Intent intent = new Intent(LoginActivity.this, homePage.class);
                startActivity(intent);
            }
        }catch(Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUp=new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(signUp);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("User");
                    ref.child(user.getText().toString()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            try {
                                if (dataSnapshot.hasChildren()) {
                                    String mb = dataSnapshot.getValue(artist.class).getMobile();
                                    String ps = dataSnapshot.getValue(artist.class).getPassword();
                                    if (mb.equals(user.getText().toString()) && ps.equals(pass.getText().toString())) {
                                        sqLiteDatabase = getApplicationContext().openOrCreateDatabase("rec", MODE_APPEND, null);
                                        sqLiteDatabase.execSQL("CREATE table if not exists login(mobile VARCHAR);");
                                        sqLiteDatabase.execSQL("INSERT into login values('" + user.getText().toString() + "');");
                                        Intent main = new Intent(LoginActivity.this, homePage.class);
                                        startActivity(main);
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Entries are Incorrect", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(LoginActivity.this, "User doesn't exists \nPlease signUp first", Toast.LENGTH_SHORT).show();
                                }
                            }catch (Exception e)
                            {
                                Toast.makeText(LoginActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
            }
        });
    }

}
