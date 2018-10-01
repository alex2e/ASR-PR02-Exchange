package com.example.santi.Exchange;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.santi.asrpr02exchange.R;

public class MainActivity extends AppCompatActivity {
    //region [variables]
    EditText txtAmount;
    RadioGroup rgFrom;
    RadioGroup rgTo;
    RadioButton rbFromDollar;
    RadioButton rbFromEuro;
    RadioButton rbFromPound;
    RadioButton rbToDollar;
    RadioButton rbToEuro;
    RadioButton rbToPound;
    ImageView imgFrom;
    ImageView imgTo;
    Button btnExchange;

    String fromCurrency = "€";
    String toCurrency = "$";
    String fromAmount = "";
    String toAmount = "";

    final double DOLLAR_EUR = 0.86;
    final double DOLLAR_POUND = 0.77;
    final double EUR_DOLLAR = 1.16;
    final double EUR_POUND = 0.89;
    final double POUND_DOLLAR = 1.30;
    final double POUND_EUR = 1.12;

    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initview();
        clicks();
        setupTxt();
        setupRadioGroups();
    }

    private void setupTxt() {
        txtAmount.setSelectAllOnFocus(true);
    }

    private void setupRadioGroups() {
        //RadioGroup FROM....
        rbFromDollar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                rbToDollar.setChecked(false);
                rbToDollar.setEnabled(!rbFromDollar.isChecked());

                if (rbFromDollar.isChecked()){
                    imgFrom.setImageResource(R.drawable.ic_dollar);
                }
            }
        });

        rbFromEuro.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                rbToEuro.setChecked(false);
                rbToEuro.setEnabled(!rbFromEuro.isChecked());

                if (rbFromEuro.isChecked()){
                    imgFrom.setImageResource(R.drawable.ic_euro);
                }
            }
        });

        rbFromPound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                rbToPound.setChecked(false);
                rbToPound.setEnabled(!rbFromPound.isChecked());

                if (rbFromPound.isChecked()){
                    imgFrom.setImageResource(R.drawable.ic_pound);
                }
            }
        });


        //RadioGroup TO....
        rbToDollar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                rbFromDollar.setChecked(false);
                rbFromDollar.setEnabled(!rbToDollar.isChecked());

                if (rbToDollar.isChecked()){
                    imgTo.setImageResource(R.drawable.ic_dollar);
                }
            }
        });

        rbToEuro.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                rbFromEuro.setChecked(false);
                rbFromEuro.setEnabled(!rbToEuro.isChecked());

                if (rbToEuro.isChecked()){
                    imgTo.setImageResource(R.drawable.ic_euro);
                }
            }
        });

        rbToPound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                rbFromPound.setChecked(false);
                rbFromPound.setEnabled(!rbToPound.isChecked());

                if (rbToPound.isChecked()){
                    imgTo.setImageResource(R.drawable.ic_pound);
                }
            }
        });




    }

    private void clicks() {
        btnExchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromAmount = txtAmount.getText().toString();

                if (!fromAmount.equals("0.00")){
                    calculateExchange();
                    Toast.makeText(getApplicationContext(),fromAmount + " " + fromCurrency + " = " + toAmount + " " + toCurrency, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void calculateExchange() {
        Double from, to;
        int checkedFromRg, checkedTorg;

        checkedFromRg = rgFrom.getCheckedRadioButtonId();
        checkedTorg = rgTo.getCheckedRadioButtonId();
        from = Double.parseDouble(txtAmount.getText().toString());
        to = 0.0;

        if (checkedFromRg != -1 || checkedTorg != -1){
            if (checkedFromRg == R.id.rbFromDollar && checkedTorg == R.id.rbToEuro){
                fromCurrency = "$";
                toCurrency = "€";
                to =  from * DOLLAR_EUR;
            }else if (checkedFromRg == R.id.rbFromDollar && checkedTorg == R.id.rbToPound){
                fromCurrency = "$";
                toCurrency = "\u00a3";
                to =  from * DOLLAR_POUND;
            } else if (checkedFromRg == R.id.rbFromEuro && checkedTorg == R.id.rbToDollar){
                fromCurrency = "€";
                toCurrency = "$";
                to =  from * EUR_DOLLAR;
            }else if (checkedFromRg == R.id.rbFromEuro && checkedTorg == R.id.rbToPound) {
                fromCurrency = "€";
                toCurrency = "\u00a3";
                to = from * EUR_POUND;
            }else if (checkedFromRg == R.id.rbFromPound && checkedTorg == R.id.rbToDollar) {
                fromCurrency = "\u00a3";
                toCurrency = "$";
                to = from * POUND_DOLLAR;
            }else if (checkedFromRg == R.id.rbFromPound && checkedTorg == R.id.rbToEuro) {
                fromCurrency = "\u00a3";
                toCurrency = "€";
                to = from * POUND_EUR;
            }
        }

        fromAmount = String.format("%.2f",from);
        toAmount = String.format("%.2f",to);
    }

    private void initview() {
        txtAmount = findViewById(R.id.txtAmount);
        rgFrom = findViewById(R.id.rgFrom);
        rgTo = findViewById(R.id.rgTo);
        rbFromDollar = findViewById(R.id.rbFromDollar);
        rbFromEuro = findViewById(R.id.rbFromEuro);
        rbFromPound = findViewById(R.id.rbFromPound);
        rbToDollar = findViewById(R.id.rbToDollar);
        rbToEuro = findViewById(R.id.rbToEuro);
        rbToPound = findViewById(R.id.rbToPound);
        imgFrom = findViewById(R.id.imgFrom);
        imgTo = findViewById(R.id.imgTo);
        btnExchange = findViewById(R.id.btnExchange);
    }
}
