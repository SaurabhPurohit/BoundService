package saurabh.boundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;


/**
 * Created by Saurabh on 16-Jan-17.
 */

public class MyService extends Service {

    private final IBinder mBinder = new LocalService();

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class LocalService extends Binder{
        MyService getService(){
            return MyService.this;
        }
    }

    public String getFirstMessage(){
        return "Hello welcome All";
    }

    public String getSecondMessage(){
        return "This is Bound Service example..";
    }
}
