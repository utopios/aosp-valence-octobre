package com.example.serviceapp.service;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
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
           if(checkCallingOrSelfPermission("com.example.serviceapp.service.PERMISSION_ACCESS_SERVICE") != PackageManager.PERMISSION_GRANTED) {
                throw new SecurityException("Permission not granted to access service");
            }
            return a + b;
        }
    };


    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}