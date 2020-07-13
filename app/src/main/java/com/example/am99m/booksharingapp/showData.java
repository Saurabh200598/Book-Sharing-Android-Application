package com.example.am99m.booksharingapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class showData extends AppCompatActivity {

    TextView book_name, author_name, price;
    ImageView image;
    Button call, chat;
    String no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        book_name = (TextView) findViewById(R.id.book_name);
        author_name = (TextView) findViewById(R.id.author_name);
        price = (TextView) findViewById(R.id.price);
        image = (ImageView) findViewById(R.id.image);
        call = (Button) findViewById(R.id.call);
        chat = (Button) findViewById(R.id.chat);
        @SuppressLint("WrongConstant") SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("rec", getApplicationContext().MODE_APPEND, null);
        final Cursor cursor = sqLiteDatabase.rawQuery("Select * from book_name", null);
        cursor.moveToLast();
        Toast.makeText(this, cursor.getString(0) + "", Toast.LENGTH_SHORT).show();
        StorageReference storageReference = FirebaseStorage.getInstance().getReference("Books");
        storageReference.child(cursor.getString(0)).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getApplicationContext()).load(uri).into(image);
            }
        });
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Books").child(cursor.getString(0));
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                price.setText(dataSnapshot.getValue(artist_book.class).getPrice() + " Rs.");
                author_name.setText(dataSnapshot.getValue(artist_book.class).getAuthor_name());
                book_name.setText(dataSnapshot.getValue(artist_book.class).getBook_name());
                no = dataSnapshot.getValue(artist_book.class).getMobile();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "tel:" + no;
                if (ActivityCompat.checkSelfPermission(showData.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(number)));
            }
        });
    chat.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(getApplicationContext(),ChatActivity.class);
            startActivity(i);
        }
    });
    }
}
