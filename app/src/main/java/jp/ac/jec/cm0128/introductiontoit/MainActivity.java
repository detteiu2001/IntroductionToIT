package jp.ac.jec.cm0128.introductiontoit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.openSpeedCalc).setOnClickListener(v -> TransferCalcActivity.start(this));

        findViewById(R.id.openIpCalc).setOnClickListener(v -> IpCalcActivity.start(this));
    }
}