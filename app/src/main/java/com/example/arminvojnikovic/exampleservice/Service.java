package com.example.arminvojnikovic.exampleservice;

import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by Armin Vojnikovic on 2017-03-28.
 */

public class Service extends android.app.Service {
//    @Nullable

    final class TheTread implements Runnable{
        int serviceID;
        TheTread(int serviceID){
            this.serviceID = serviceID;
        }

        @Override
        public void run() {
            synchronized (this){
                try{
                    wait(8000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            stopSelf(this.serviceID);
        }
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }

    //starts a service
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(Service.this,"The service is up and running",Toast.LENGTH_LONG).show();

        Thread thread = new Thread(new TheTread(startId));
        thread.start();

        /*If we put sync here we wont be able to interact with the UI after the service is started*/
//        synchronized (this){
//            try{
//                wait(8000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        return START_STICKY;


//        return super.onStartCommand(intent, flags, startId);
    }

    //destroys a service


    @Override
    public void onDestroy() {
        Toast.makeText(Service.this,"The service has shout down",Toast.LENGTH_LONG).show();

//        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
