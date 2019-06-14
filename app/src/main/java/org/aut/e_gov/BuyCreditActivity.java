package org.aut.e_gov;

import android.annotation.SuppressLint;
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
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.aut.e_gov.models.DatabaseAccess;

public class BuyCreditActivity extends AppCompatActivity {

    EditText et1,edtPass2,edtCvv2, edtYear, edtMonth;
    TextView txtOperator;
    RadioButton one, two, five, ten;
    Double amount = 0.0;
    ImageButton irancell, rightel, hamraheAvval;
    Button btnBuyCredit;
    String cardNumber;
    public void findViews(){
        one = findViewById(R.id.rbtn1);
        two = findViewById(R.id.rbtn2);
        five = findViewById(R.id.rbtn5);
        ten = findViewById(R.id.rbtn10);
        txtOperator = findViewById(R.id.txtOperator);
        irancell = findViewById(R.id.btnIrancell);
        hamraheAvval = findViewById(R.id.btnHamraheAvval);
        rightel = findViewById(R.id.btnRightel);
        btnBuyCredit = findViewById(R.id.btnDoneBuyCredit);
        et1 = findViewById(R.id.editTextBuyCredit);
        edtCvv2 = findViewById(R.id.edtCvv2BuyCredit);
        edtYear = findViewById(R.id.edtYearBuyCredit);
        edtMonth = findViewById(R.id.edtMonthBuyCredit);
        edtPass2 = findViewById(R.id.edtPass2BuyCredit);

    }
    public void implementListeners(){
        final DatabaseAccess dbAccess = new DatabaseAccess(this);

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                one.setChecked(true);
                two.setChecked(false);
                five.setChecked(false);
                ten.setChecked(false);
                amount = 1000.0;
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                two.setChecked(true);
                one.setChecked(false);
                five.setChecked(false);
                ten.setChecked(false);
                amount = 2000.0;
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                one.setChecked(false);
                two.setChecked(false);
                five.setChecked(true);
                ten.setChecked(false);
                amount = 5000.0;
            }
        });
        ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                one.setChecked(false);
                two.setChecked(false);
                five.setChecked(false);
                ten.setChecked(true);
                amount = 10000.0;
            }
        });

        irancell.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                txtOperator.setTextColor(R.color.irancell);
                txtOperator.setText("ایرانسل");
            }
        });

        rightel.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                txtOperator.setTextColor(R.color.rightel);
                txtOperator.setText("رایتل");
            }
        });

        hamraheAvval.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                txtOperator.setTextColor(R.color.hamraheAvval);
                txtOperator.setText("همراه اول");
            }
        });


        btnBuyCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

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

        btnBuyCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean userFound = false;
                cardNumber = et1.getText().toString();
                Cursor bankCursor = dbAccess.getDb().rawQuery("SELECT * FROM Bank", null);
                bankCursor.moveToFirst();
                while (!bankCursor.isAfterLast()){
                    String thisCardNumber = bankCursor.getString(0);
                    if(thisCardNumber.equals(cardNumber)) {
                        userFound = true;
                        Log.d("BuyCredit", "card equals");
                        break;
                    }
                    else
                        bankCursor.moveToNext();
                }
                if (userFound) {
                    ContentValues bankcv = new ContentValues();
                    bankcv.put("card_number", cardNumber);
                    bankcv.put("pass2", bankCursor.getInt(1));
                    bankcv.put("pass2", "1234");
                    bankcv.put("cvv2", bankCursor.getInt(2));
                    bankcv.put("expire_year", bankCursor.getInt(3));
                    bankcv.put("expire_month", bankCursor.getInt(4));
                    bankcv.put("balance", bankCursor.getDouble(5) - amount);
                    dbAccess.getDb().update("Bank", bankcv, "card_number = '" + cardNumber + "'", null);
                    Toast.makeText(getApplicationContext(), "خرید موفقیت آمیز بود", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(BuyCreditActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                if (userFound == false){
                    Toast.makeText(getApplicationContext(), "این کارت در سامانه ثبت نشده است",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_credit);
        findViews();
        implementListeners();
//        et1.setText("1234123412341234");
//        edtYear.setText("12");
//        edtMonth.setText("34");
//        edtPass2.setText("1234");
//        edtCvv2.setText("1234");
    }
}
