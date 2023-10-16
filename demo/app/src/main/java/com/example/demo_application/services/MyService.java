package com.example.demo_application.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.demo_application.IMyService;

import java.io.FileDescriptor;

public class MyService extends Service implements IMyService{

    private IBinder binder = new MyBinder();

    private final IMyService.Stub mBinder= new IMyService.Stub() {

        @Override
        public String getBinderMessage() {
            return "Message from Binder !";
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public String getBinderMessage() throws RemoteException {
        return "get Message";
    }

    @Override
    public IBinder asBinder() {
        return null;
    }

    public class MyBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }

}