package com.example.chitfundcalculator;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int auction;
    EditText auction_edit;
    int bit;
    TextView bit_edit;
    Button button_calc;
    Spinner chit_edit;
    Spinner mem_edit;
    int members;
    String report;
    String reportend;
    int temp_pay;
    int total_amount;
    Button historybut;

    //history variable history actovity

    public static ArrayList<String> history = new ArrayList<String>();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner chit_edit = (Spinner) findViewById(R.id.chit_amount);
        final EditText auction_edit = (EditText) findViewById(R.id.auction_amount);
        Spinner mem_edit = (Spinner) findViewById(R.id.members);
        final TextView bit_edit = (TextView) findViewById(R.id.bit_amount);
        Button button_calc = (Button) findViewById(R.id.calculate);
        Button historybut = (Button) findViewById(R.id.histoy_but);

        // spinner for chit amount

        ArrayAdapter<CharSequence> chit_Spinner = ArrayAdapter.createFromResource(this, R.array.chit_amount, android.R.layout.simple_spinner_item);
        chit_Spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chit_edit.setAdapter(chit_Spinner);
        chit_edit.setOnItemSelectedListener(this);


        // spinner for members
        ArrayAdapter<CharSequence> mem_Spinner = ArrayAdapter.createFromResource(this, R.array.members, android.R.layout.simple_spinner_item);
        mem_Spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mem_edit.setAdapter(mem_Spinner);
        mem_edit.setOnItemSelectedListener(this);

        // spinner item selector


        button_calc.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (auction_edit != null) {
                    int auction_amount;
                    auction_amount = Integer.valueOf(auction_edit.getText().toString()).intValue();
                    auction = auction_amount;
                    switch (total_amount) {
                        case 100000:
                            bit = 3000;
                            break;
                        case 50000:
                            bit = 1500;
                            break;
                        case 200000:
                            bit = 6000;
                            break;
                        case 20000:
                            bit = 600;
                            break;
                    }
                    bit_edit.setText("" + bit);
                    int payc = pay_calc();
                    summary(payc);
                } else {
                    Toast.makeText(this, "Please enter the Auction amount").show();
                }


            }
        });
        historybut.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),History.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.getItemIdAtPosition(position);
        if( parent.getId() == R.id.chit_amount){
            switch (position){
                case 0: total_amount = 100000;
                    break;
                case 1: total_amount = 50000;
                    break;
                case 2: total_amount = 200000;
                    break;
                case 3: total_amount = 20000;
                    break;
            }
        }
        else if( parent.getId() == R.id.members){
            switch (position){
                case 0: members = 20;
                    break;
                case 1: members = 10;
                    break;
            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }


    public int pay_calc() {
        int i = total_amount;
        int i2 = members;
        temp_pay = i / i2;
        int i3 = auction - bit;
        int i4 = i3 / members;
        int tot_pay = temp_pay - i4;
        return tot_pay;
    }

    public int summary_clear() {
        members = 0;
        total_amount = 0;
        bit = 0;
        auction = 0;
        temp_pay = 0;
        auction_edit.setText(null);
        return total_amount;
    }

    public void summary(int n) {
        TextView report_view = (TextView) findViewById(R.id.report);
        TextView reportend_view = (TextView) findViewById(R.id.reportend);
        String str = "";
        String str2 = "";
        if(n > 0) {
            str = "\nAmount:\t" + total_amount + "\nMonths:\t" + members + "\nAuction:\t" + auction + "\nBit\t" + bit;
            report = str;
            str2 = "\nPay:\t" + n;
            reportend = str2;
        }
        else {
            report = "" + str;
            reportend = str2;
        }
        report_view.setText(report);
        reportend_view.setText(reportend);
        history.add(str + str2);
    }


}
