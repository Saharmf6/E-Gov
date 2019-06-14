package org.aut.e_gov;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.aut.e_gov.models.DatabaseAccess;

public class BalanceActivity extends AppCompatActivity {
    EditText et1,edtPass2,edtCvv2, edtYear, edtMonth;
    Button btnGetBalance;
    String cardNumber;
    TextView txtBalance;
    public void findViews(){
        et1 = findViewById(R.id.editTextBalance);
        edtCvv2 = findViewById(R.id.edtCvv2Balance);
        edtYear = findViewById(R.id.edtYearBalance);
        edtMonth = findViewById(R.id.edtMonthBalance);
        edtPass2 = findViewById(R.id.edtPass2Balance);
        btnGetBalance = findViewById(R.id.btnGetBalance);
        txtBalance = findViewById(R.id.txtBalance);
    }

    public void implementListeners(){
        final DatabaseAccess dbAccess = new DatabaseAccess(this);

        et1.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                // TODO Auto-generated method stub
                if(et1.getText().toString().length()== 16)     //size as per your requirement
                {
                    edtCvv2.requestFocus();
                }
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });
        edtYear.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                // TODO Auto-generated method stub
                if(edtYear.getText().toString().length()== 2)     //size as per your requirement
                {
                    edtMonth.requestFocus();
                }
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });
        edtMonth.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                // TODO Auto-generated method stub
                if(edtMonth.getText().toString().length()== 2)     //size as per your requirement
                {
                    edtPass2.requestFocus();
                }
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });
        btnGetBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_LONG).show();

                boolean userFound = false;
                cardNumber = et1.getText().toString();
                Cursor bankCursor = dbAccess.getDb().rawQuery("SELECT * FROM Bank", null);
                bankCursor.moveToFirst();
                while (!bankCursor.isAfterLast()){
                    String thisCardNumber = bankCursor.getString(0);
                    if(thisCardNumber.equals(cardNumber)) {
                        userFound = true;
                        break;
                    }
                    else
                        bankCursor.moveToNext();
                }
                txtBalance.setText(Double.toString( bankCursor.getDouble(5)));
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        findViews();
        implementListeners();
        et1.setText("1234123412341234");
        edtCvv2.setText("1234");
        edtMonth.setText("34");
        edtYear.setText("12");
        edtPass2.setText("1234");
    }
}
