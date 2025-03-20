package com.example.campusexpensemanagerse06304;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.campusexpensemanagerse06304.database.UserDb;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class SignUpActivity extends AppCompatActivity {
    EditText edtUser, edtPassword, edtEmail, edtPhone;
    Button btnRegister, btnCancel;
    TextView tvLogin;
    UserDb userDb;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        userDb = new UserDb(SignUpActivity.this);
        edtUser = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnRegister = findViewById(R.id.btnRegister);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhone = findViewById(R.id.edtPhone);
        tvLogin = findViewById(R.id.tvLogin);
        signupAccount();

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });
    }

    private void signupAccount(){
       btnRegister.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String user = edtUser.getText().toString().trim();
               String password = edtPassword.getText().toString().trim();
               String email = edtEmail.getText().toString().trim();
               String phone = edtPhone.getText().toString().trim();
               if (TextUtils.isEmpty(user) || TextUtils.isEmpty(password) || TextUtils.isEmpty(email)) {
                   Toast.makeText(SignUpActivity.this, "Can not empty", Toast.LENGTH_SHORT).show();
                   return;
               }
               boolean checkUsernameEmail = userDb.checkExistsUsername(user, email);
               if(checkUsernameEmail){
                   // Khong cho dang ky tai khoan
                   edtUser.setError("User already exists");
                   edtEmail.setError("Email already exists");
                   return;
               }

               //insert data to SQLite
               @SuppressLint({"NewApi", "LocalSuppress"}) long insert = userDb.insertUserAccount(user, password, email, phone);
               if(insert == -1){
                   //fail
                   Toast.makeText(SignUpActivity.this, "Create account fail", Toast.LENGTH_SHORT).show();
               } else {
                   //success
                   Toast.makeText(SignUpActivity.this, "Create account sucess", Toast.LENGTH_SHORT).show();
                   //go to login page
                   Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                   startActivity(intent);
               }
           }
       });
       }

    private void registerAccount(){
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUser.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(password)){
                    Toast.makeText(SignUpActivity.this, "Can not empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                // tien hanh luu du lieu vao local storage
                // mac dinh se luu duoi dang 1 file .txt
                FileOutputStream fileOpS = null;
                try {
                    user += "|";
                    fileOpS = openFileOutput("account.txt", Context.MODE_APPEND);
                    fileOpS.write(user.getBytes(StandardCharsets.UTF_8));
                    fileOpS.write(password.getBytes(StandardCharsets.UTF_8));
                    fileOpS.write('\n');
                    fileOpS.close(); //dong file
                    edtUser.setText("");
                    edtPassword.setText("");
                    //quay ve trang dang nhap
                    Toast.makeText(SignUpActivity.this, "Register Success", Toast.LENGTH_SHORT).show();
                    Intent intent   = new Intent(SignUpActivity.this, SignInActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
