package saurabh.boundservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button btn_first,btn_second;
    MyService myService;
    boolean isBind = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        btn_first = (Button) findViewById(R.id.button);
        btn_second = (Button) findViewById(R.id.button2);

        btn_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg;
                msg = myService.getFirstMessage();
                textView.setText(msg);
            }
        });

        btn_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg;
                msg = myService.getSecondMessage();
                textView.setText(msg);

            }
        });

        Intent intent = new Intent(this,MyService.class);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.LocalService localService = (MyService.LocalService) service;
            Toast.makeText(MainActivity.this,"In serviceConnected",Toast.LENGTH_SHORT).show();
            myService = localService.getService();
            isBind = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(MainActivity.this,"In serviceDisConnected",Toast.LENGTH_SHORT).show();
            isBind = false;
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        if(isBind){
            unbindService(serviceConnection);
            Toast.makeText(MainActivity.this,"In serviceDisConnected in onStop",Toast.LENGTH_SHORT).show();
            isBind = false;
        }
    }
}
