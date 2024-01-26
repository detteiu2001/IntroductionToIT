package jp.ac.jec.cm0128.introductiontoit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class TcpAndIpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcp_and_ip);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, TcpAndIpActivity.class);
        context.startActivity(starter);
    }
}