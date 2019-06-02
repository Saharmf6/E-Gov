package org.aut.e_gov;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.aut.e_gov.models.DatabaseAccess;


public class RegistrationActivity extends AppCompatActivity {
    Button btnLogin;
    TextView txtPassword, txtName, txtPhoneNumber;
    TextView txtId, inkLogin;// = findViewById(R.id.txtEmailReg);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        final DatabaseAccess dbAccess  = new DatabaseAccess(this);
        txtName = findViewById(R.id.txtNameReg);
        txtPassword = findViewById(R.id.txtPwdReg);
        txtId = findViewById(R.id.txtIdReg);
        txtPhoneNumber = findViewById(R.id.txtNumberReg);
        inkLogin = findViewById(R.id.lnkLogin);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor userCursor = dbAccess.getDb().rawQuery("SELECT * FROM User", null);
                ContentValues usercv = new ContentValues();
                usercv.put("id", txtId.getText().toString());
                usercv.put("password", txtPassword.getText().toString());
                usercv.put("name", txtName.getText().toString());
                usercv.put("phone_number", txtPhoneNumber.getText().toString());
                usercv.put("balance", 0.0);
                Log.d("RegistrationActivity",txtName.getText().toString() + " " +
                        userCursor.getCount()+1 + " " +
                        txtPassword.getText().toString() + " " +
                        txtId.getText().toString());

                dbAccess.getDb().insert("User", null, usercv);

                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                intent.putExtra("id", txtId.getText().toString());
                startActivity(intent);

            }
        });
        TextView login = (TextView)findViewById(R.id.lnkLogin);
        login.setMovementMethod(LinkMovementMethod.getInstance());
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                intent.putExtra("id", txtId.getText().toString());
                startActivity(intent);
            }
        });
    }
}