package jp.ac.jec.cm0128.introductiontoit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class PacketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packet);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, PacketActivity.class);
        context.startActivity(starter);
    }
}