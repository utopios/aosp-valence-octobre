package com.example.serviceapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.serviceapp.IMath;

public class MathService extends Service {

    private final IMath.Stub binder = new IMath.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int addition(int a, int b) throws RemoteException {
            return a + b;
        }
    };


    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}