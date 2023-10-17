package com.example.serviceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.serviceapp.service.MathService;

public class MainActivity extends AppCompatActivity {

    private IMath mathService;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mathService = IMath.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mathService = null;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
        //Intent intent = new Intent(this, MathService.class);
        Intent intent = new Intent("com.example.serviceapp.service.MATH_OPERATION");
        intent.setPackage("com.example.serviceapp");
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

        EditText editText1 = (EditText) findViewById(R.id.num1);
        EditText editText2 = (EditText) findViewById(R.id.num2);
        Button buttonValid = (Button)  findViewById(R.id.valid);
        TextView result = (TextView) findViewById(R.id.result);

        buttonValid.setOnClickListener((v) -> {
            int number1 = Integer.parseInt(editText1.getText().toString());
            int number2 = Integer.parseInt(editText2.getText().toString());
            try {
                int resultService = mathService.addition(number2, number1);
                result.setText(String.valueOf(resultService));
            }catch (RemoteException ex) {
                result.setText(ex.getMessage());
            }
        });
    }


    @Override
    protected void onStop() {
        super.onStop();
        unbindService(serviceConnection);
    }
}