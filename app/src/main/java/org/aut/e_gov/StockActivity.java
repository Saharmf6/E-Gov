package org.aut.e_gov;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class StockActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{
    String[] stockNames ={"کگهر","حتايد","گكوثر","غصینو","خاذین","شفن","سمایه","خساپا","فسپا","دفارا"};
    String choosed = "";
    EditText edtCount;
    String destCard = "9382-9837-9283-7483";
    Button btnDone;
    public void findViews(){
        edtCount = findViewById(R.id.edtCountStock);
        btnDone = findViewById(R.id.btnDoneStock);
    }
    public void implementListeners(){
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StockActivity.this, MoneyTransfer.class);
                intent.putExtra("destCard", destCard);
                intent.putExtra("count", Integer.parseInt(edtCount.getText().toString()));
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);

        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = (Spinner) findViewById(R.id.simpleSpinnerStock);
        spin.setOnItemSelectedListener(this);
        //Creating the ArrayAdapter instance having the bank name list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item, stockNames);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

        findViews();
        implementListeners();


    }

    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(), stockNames[position], Toast.LENGTH_LONG).show();
        choosed = stockNames[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
}
