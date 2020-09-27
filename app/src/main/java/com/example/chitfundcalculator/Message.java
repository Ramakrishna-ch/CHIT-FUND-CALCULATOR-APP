 package com.example.chitfundcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

 public class Message extends AppCompatActivity  {

    private EditText number;
    private TextView subj;
    private Button sendbut;
    private String sendingMessage;
    private String number2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Intent intent = getIntent();
        sendingMessage = "CHIT BILL" + "\n" + intent.getStringExtra("message-key");
         final EditText number = (EditText) findViewById(R.id.phNum);
         TextView subj = (TextView) findViewById(R.id.subject);
         Button sendbut = (Button) findViewById(R.id.send1);

         subj.setText(sendingMessage);



        sendbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number2 = number.getText().toString();
                if (!number2.isEmpty()) {
                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(number2, null, sendingMessage, null, null);
                    Toast.makeText(getApplicationContext(), "Message Sent successfully!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Please Enter Number!", Toast.LENGTH_LONG).show();
                }
            }

        });


    }


 }
