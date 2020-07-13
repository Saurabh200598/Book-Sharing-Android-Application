package com.example.am99m.booksharingapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class dataUploader extends AppCompatActivity {

    EditText book_name,author_name,price;
    Button upload,choose;
    ImageView image;
    DatabaseReference ref;
    StorageReference refStorage;

    String mobile,b_name,a_name,p;
    private Uri filepath;
    private final int Request=71;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_uploader);
            book_name = (EditText) findViewById(R.id.book_name);
            author_name = (EditText) findViewById(R.id.author_name);
            price = (EditText) findViewById(R.id.price);
            choose = (Button) findViewById(R.id.pic_choose);
            upload = (Button) findViewById(R.id.upload);
            image = (ImageView) findViewById(R.id.image_show);

            SQLiteDatabase sqLiteDatabase = getApplicationContext().openOrCreateDatabase("rec", MODE_APPEND, null);
            Cursor cursor = sqLiteDatabase.rawQuery("Select * from login", null);
            cursor.moveToLast();
            mobile = cursor.getString(0);





        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"),Request);
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a_name=author_name.getText().toString();
                b_name=book_name.getText().toString();
                p=price.getText().toString();

                if(filepath!=null)
                {

                    ref = FirebaseDatabase.getInstance().getReference("Books").child(b_name);

                    artist_book art=new artist_book(b_name,a_name,p,mobile);
                    ref.setValue(art);

                    try {
                        refStorage = FirebaseStorage.getInstance().getReference("Books");
                        refStorage.child(b_name).putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(dataUploader.this, "success", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(dataUploader.this, "Faliure " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }catch (Exception e)
                    {
                        Toast.makeText(dataUploader.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Request && resultCode==RESULT_OK && data != null && data.getData() !=null)
        {
            filepath=data.getData();
            try
            {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
                image.setImageBitmap(bitmap);
            }catch (Exception e)
            {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

}
