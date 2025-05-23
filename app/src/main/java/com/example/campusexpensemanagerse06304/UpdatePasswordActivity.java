package com.example.campusexpensemanagerse06304;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.campusexpensemanagerse06304.database.UserDb;

public class UpdatePasswordActivity extends AppCompatActivity {
    Button btnSaveChangePw;
    EditText edtNewPw, edtConfirmPw;
    UserDb userDb;
    Intent intent;
    Bundle bundle;

    private String account = null;
    private String email = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        btnSaveChangePw = findViewById(R.id.btnSaveChangePassword);
        edtNewPw = findViewById(R.id.edtNewPassword);
        edtConfirmPw = findViewById(R.id.edtConfirmNewPassword);
        userDb  = new UserDb(UpdatePasswordActivity.this);
        intent= getIntent();
        bundle = intent.getExtras();
        if(bundle != null){
            account = bundle.getString("ACCOUNT_FORGET_PW","");
            email = bundle.getString("EMAIL_FORGET_PW","");
        }
        btnSaveChangePw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPw = edtNewPw.getText().toString().trim();
                if (TextUtils.isEmpty(newPw)) {
                    edtNewPw.setError("New Password can not empty");
                    return;
                }
                String confirmNewPw = edtConfirmPw.getText().toString().trim();
                if (! confirmNewPw.equals(newPw)) {
                    edtConfirmPw.setError("Confirm New Password not equal New Password");
                    return;
                }
                int update = userDb.changePassword(newPw, account, email);
                if (update == -1) {
                    Toast.makeText(UpdatePasswordActivity.this, "Update Failed", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(UpdatePasswordActivity.this, "Update Success", Toast.LENGTH_SHORT).show();
                    Intent intentLogin = new Intent(UpdatePasswordActivity.this, SignInActivity.class);
                    startActivity(intentLogin);
                    finish();
                }
            }

        });
    }
}
