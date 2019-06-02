package org.aut.e_gov;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.aut.e_gov.models.DatabaseAccess;

public class LoginActivity extends AppCompatActivity {
    TextView txtId;
    TextView txtPassword;
    TextView register;
    boolean userFound = false;
    Button btnLogin;
    private String password;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtId = findViewById(R.id.txtId);
        txtPassword = findViewById(R.id.txtPwd);
        register = (TextView)findViewById(R.id.lnkRegister);
        btnLogin = findViewById(R.id.btnLogin);
        final DatabaseAccess dbAccess = new DatabaseAccess(this);

       // id = getIntent().getStringExtra("id");

       // txtId.setText(id);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password = txtPassword.getText().toString();
                id = txtId.getText().toString();
                Cursor userCursor = dbAccess.getDb().rawQuery("SELECT * FROM User", null);
                userCursor.moveToFirst();
                Log.d("LoginActivity","id: " + id + " pass: " + password);
                userFound = false;
                while (!userCursor.isAfterLast()){
                    String thisId = userCursor.getString(0);
                    String thisPass = userCursor.getString(1);
                    if(thisPass.equals(password) && thisId.equals(id)) {
                        userFound = true;
                        break;
                    }
                    userCursor.moveToNext();
                }
                if(userFound){
                    Log.d("LoginActivity", "User was found");
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
                else {
                    Log.d("LoginActivity", "User was not found");
                    Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_LONG).show();
                }

            }
        });
        register.setMovementMethod(LinkMovementMethod.getInstance());
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
}