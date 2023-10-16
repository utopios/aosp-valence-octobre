package com.example.demo_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Button;
import android.widget.Toast;

import com.example.demo_application.services.MyService;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private IMyService mService;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            mService = (IMyService) ((MyService.MyBinder)service).getService();
            try {
                String message = mService.getBinderMessage();
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mService = null;
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

        Intent intent = new Intent(this, MyService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        FetchDataTask fetchDataTask = (FetchDataTask) new FetchDataTask().execute("https://utopios.net");

        Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener((v) -> {
            Toast.makeText(MainActivity.this, "click bouton", Toast.LENGTH_LONG).show();
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(mConnection);
    }


    private class FetchDataTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(connection.getInputStream());
                return readStream(in);
            } catch (Exception e) {
                e.printStackTrace();
                return "Error!";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
        }

        private String readStream(InputStream in) throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder out = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }
            reader.close();
            return out.toString();
        }
    }
}