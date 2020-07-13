package com.example.am99m.booksharingapp;

import android.content.Intent;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SignUpActivity extends AppCompatActivity {

    RelativeLayout rel1,rel2;
    Button sendOTP,Login;
    TextView setNumber;
    EditText getNumber,getName,et1,et2,et3,et4,et5,et6;
    FirebaseAuth auth;
    private String verificationCode;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        rel1=(RelativeLayout)findViewById(R.id.rel_lay1);
        rel2=(RelativeLayout)findViewById(R.id.rel_lay2);
        sendOTP=(Button)findViewById(R.id.send_otp);
        Login=(Button)findViewById(R.id.login);
        setNumber=(TextView)findViewById(R.id.number);
        getNumber=(EditText)findViewById(R.id.getNumber);
        getName=(EditText)findViewById(R.id.getName);
        et1=(EditText)findViewById(R.id.ed1);
        et2=(EditText)findViewById(R.id.ed2);
        et3=(EditText)findViewById(R.id.ed3);
        et4=(EditText)findViewById(R.id.ed4);
        et5=(EditText)findViewById(R.id.ed5);
        et6=(EditText)findViewById(R.id.ed6);
        rel1.setVisibility(View.VISIBLE);




        /*************************************************************************************/


        auth = FirebaseAuth.getInstance();
        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Toast.makeText(SignUpActivity.this, "verification completed", Toast.LENGTH_SHORT).show();
                SigninWithPhone(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(SignUpActivity.this, "verification failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationCode = s;
                Toast.makeText(SignUpActivity.this, "Code sent", Toast.LENGTH_SHORT).show();
            }
        };


        /*****************************************************************************************/




        sendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getNumber.getText().toString().length()==10 && (getName.getText().toString().trim().length()!=0)) {
                    rel1.setVisibility(View.GONE);
                    setNumber.setText("+91" + getNumber.getText().toString());
                    rel2.setVisibility(View.VISIBLE);
                    try {
                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                getNumber.getText().toString(),
                                60,
                                TimeUnit.SECONDS,
                                SignUpActivity.this,
                                mCallback);
                        getCursorFocus();
                        Login.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String otp = et1.getText().toString() +
                                        et2.getText().toString() +
                                        et3.getText().toString() +
                                        et4.getText().toString() +
                                        et5.getText().toString() +
                                        et6.getText().toString();
                                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode, otp);
                                SigninWithPhone(credential);
                            }
                        });


                    } catch (Exception e) {
                        Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Toast.makeText(SignUpActivity.this, "Please enter Correct Number", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    private void SigninWithPhone(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(SignUpActivity.this, "OTP Match", Toast.LENGTH_SHORT).show();
                        Intent password=new Intent(SignUpActivity.this, SetPassword.class);
                        password.putExtra("name",getName.getText().toString());
                        password.putExtra("mobile",getNumber.getText().toString());
                        startActivity(password);
                        //finish();
                    } else {
                        Toast.makeText(SignUpActivity.this,"Incorrect OTP",Toast.LENGTH_SHORT).show();
                    }

            }
        });
    }
    private  void getCursorFocus()
    {
        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Integer textLength1=et1.getText().length();
                if(textLength1>=1)
                {
                    et2.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Integer textLength2=et1.getText().length();
                if(textLength2>=1)
                {
                    et3.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Integer textLength1=et1.getText().length();
                if(textLength1>=1)
                {
                    et4.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Integer textLength1=et1.getText().length();
                if(textLength1>=1)
                {
                    et5.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Integer textLength1=et1.getText().length();
                if(textLength1>=1)
                {
                    et6.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
