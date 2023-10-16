package com.example.demo_application.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.demo_application.IMyService;

public class MyService extends Service{

    private final IMyService.Stub mBinder= new IMyService.Stub() {
        @Override
        public String getBinderMessage() {
            return "Message from Binder !";
        }
    };
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    /*@Override
    public String getBinderMessage() throws RemoteException {
        return "Hello From our service";
    }

    @Override
    public IBinder asBinder() {
        return mBinder;
    }*/
}