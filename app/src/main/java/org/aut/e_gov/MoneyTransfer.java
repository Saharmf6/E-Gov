package org.aut.e_gov;

import android.content.ContentValues;
import android.content.Intent;
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

public class MoneyTransfer extends AppCompatActivity {
    EditText et1,et2,et3,et4,edt1,edt2,edt3,edt4,edtAmount,edtPass2,edtCvv2, edtYear, edtMonth;
    Button btnDone;
    String cardNumber, destCardNumber;
    Double amount;
    public void findViews(){
        et1 = findViewById(R.id.editText);
        et2 = findViewById(R.id.editText2);
        et3 = findViewById(R.id.editText3);
        et4 = findViewById(R.id.editText4);
        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        edt3 = findViewById(R.id.edt3);
        edt4 = findViewById(R.id.edt4);
        edtAmount = findViewById(R.id.edtAmount);
        btnDone = findViewById(R.id.btnDone);
        edtCvv2 = findViewById(R.id.edtCvv2);
        edtYear = findViewById(R.id.edtYear);
        edtMonth = findViewById(R.id.edtMonth);
        edtPass2 = findViewById(R.id.edtPass2);
    }
    public void implementListeners(){
        final DatabaseAccess dbAccess = new DatabaseAccess(this);
        et1.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                // TODO Auto-generated method stub
                if(et1.getText().toString().length()== 4)     //size as per your requirement
                {
                    et2.requestFocus();
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
        et2.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                // TODO Auto-generated method stub
                if(et2.getText().toString().length()== 4)     //size as per your requirement
                {
                    et3.requestFocus();
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
        et3.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                // TODO Auto-generated method stub
                if(et3.getText().toString().length()== 4)     //size as per your requirement
                {
                    et4.requestFocus();
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
        et4.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                // TODO Auto-generated method stub
                if(et4.getText().toString().length()== 4)     //size as per your requirement
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
        edt1.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                // TODO Auto-generated method stub
                if(edt1.getText().toString().length()== 4)     //size as per your requirement
                {
                    edt2.requestFocus();
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
        edt2.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                // TODO Auto-generated method stub
                if(edt2.getText().toString().length()== 4)     //size as per your requirement
                {
                    edt3.requestFocus();
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
        edt3.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                // TODO Auto-generated method stub
                if(edt3.getText().toString().length()== 4)     //size as per your requirement
                {
                    edt4.requestFocus();
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
        edt4.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                // TODO Auto-generated method stub
                if(edt4.getText().toString().length()== 4)     //size as per your requirement
                {
                    edtAmount.requestFocus();
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
        edtAmount.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                // TODO Auto-generated method stub
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                btnDone.requestFocus();

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

        et1.setText("1234");
        et2.setText("1234");
        et3.setText("1234");
        et4.setText("1234");
        edtYear.setText("12");
        edtMonth.setText("34");
        edtAmount.setText("500");
        edtPass2.setText("1234");
        edtCvv2.setText("1234");
        edt1.setText("7382");
        edt2.setText("7382");
        edt3.setText("7382");
        edt4.setText("7382");

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean userFound = false;
                cardNumber = et1.getText().toString()
                        +et2.getText().toString()
                        +et3.getText().toString()
                        +et4.getText().toString();

                Cursor bankCursor = dbAccess.getDb().rawQuery("SELECT * FROM Bank", null);
                bankCursor.moveToFirst();
                while (!bankCursor.isAfterLast()){
                    String thisCardNumber = bankCursor.getString(0);
                    Log.d("MoneyTransfer",cardNumber);
                    Log.d("MoneyTransfer",thisCardNumber);

                    if(thisCardNumber.equals(cardNumber)) {
                        Log.d("MoneyTransfer","isEqual");
                        break;
                    }
                    else
                        bankCursor.moveToNext();
                }
                ContentValues bankcv = new ContentValues();
                bankcv.put("card_number", cardNumber);
                bankcv.put("pass2", bankCursor.getInt(1));
                bankcv.put("pass2", "1234");
                bankcv.put("cvv2", bankCursor.getInt(2));
                bankcv.put("expire_year", bankCursor.getInt(3));
                bankcv.put("expire_month", bankCursor.getInt(4));
                amount = Double.parseDouble(edtAmount.getText().toString());
                bankcv.put("balance", bankCursor.getDouble(5) - amount);
                dbAccess.getDb().update("Bank", bankcv,"card_number = '"+cardNumber+"'", null);

                boolean foundCard = false;
                destCardNumber = edt1.getText().toString()+edt2.getText().toString()+edt3.getText().toString()+edt4.getText().toString();

                bankCursor.moveToFirst();
                while (!bankCursor.isAfterLast()){
                    String thisCardNumber = bankCursor.getString(0);
                    if(thisCardNumber.equals(destCardNumber)) {
                        Log.d("MoneyTransfer", "destcard equals");
                        foundCard = true;
                        break;
                    }
                    else
                        bankCursor.moveToNext();
                }
                if (foundCard) {
                    ContentValues bankcv2 = new ContentValues();
                    bankcv.put("card_number", destCardNumber);
                    bankcv.put("pass2", bankCursor.getInt(1));
                    bankcv.put("cvv2", bankCursor.getInt(2));
                    bankcv.put("expire_year", bankCursor.getInt(3));
                    bankcv.put("expire_month", bankCursor.getInt(4));
                    amount = Double.parseDouble(edtAmount.getText().toString());
                    bankcv.put("balance", bankCursor.getDouble(5) + amount);
                    dbAccess.getDb().update("Bank", bankcv, "card_number = '" + destCardNumber + "'", null);

                }
                else if (!foundCard){

                    Intent intent = new Intent(MoneyTransfer.this, MainActivity.class);
                    startActivity(intent);
                }
                Toast.makeText(getApplicationContext(),"تراکنش موفقیت آمیز بود", Toast.LENGTH_LONG).show();



            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_transfer);
        findViews();
        implementListeners();
    }
}
