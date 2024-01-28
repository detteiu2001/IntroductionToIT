package jp.ac.jec.cm0128.introductiontoit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TransferCalcActivity extends AppCompatActivity {
    private static final String TAG = "TransferCalcActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_calc);

        EditText transferTime = findViewById(R.id.transferTime);
        EditText bitAmountTime = findViewById(R.id.bitAmountTime);
        EditText byteAmountTime = findViewById(R.id.byteAmountTime);
        EditText lineSpeedTime = findViewById(R.id.lineSpeedTime);
        EditText transmissionTime = findViewById(R.id.transmissionTime);


        EditText lineUtilizationRate = findViewById(R.id.lineUtilizationRate);
        EditText bitAmountRate = findViewById(R.id.bitAmountRate);
        EditText byteAmountRate = findViewById(R.id.byteAmountRate);
        EditText lineSpeedRate = findViewById(R.id.lineSpeedRate);
        EditText transferTimeRate = findViewById(R.id.transferTimeRate);

        findViewById(R.id.calcTransferTime).setOnClickListener(v -> {
            if(!convertDataAmountTime()){
                Toast.makeText(this, "byte ↔ bit の変換に失敗", Toast.LENGTH_SHORT).show();
                return;
            }

            double bitAmount;
            double lineSpeed;
            double transmission;

            try {
                bitAmount = Double.parseDouble(bitAmountTime.getText().toString());
                lineSpeed = Double.parseDouble(lineSpeedTime.getText().toString());
                transmission = Double.parseDouble(transmissionTime.getText().toString());
            } catch (NumberFormatException ne){
                Log.e(TAG, "onCreate: 数字変換エラー, ", ne);
                Toast.makeText(this, "数字への変換に失敗しました", Toast.LENGTH_SHORT).show();
                return;
            }

            double byteAmount = bitAmount * 8;

            double transfer = byteAmount / (lineSpeed * transmission);

            transferTime.setText(String.valueOf(transfer));

            if (transferTimeRate.getText().toString().isEmpty()){
                transferTimeRate.setText(String.valueOf(transfer));
            }
        });

        findViewById(R.id.calcBitAmountTime).setOnClickListener(v -> {
            if(!convertDataAmountTime()){
                Toast.makeText(this, "byte ↔ bit の変換に失敗", Toast.LENGTH_SHORT).show();
                return;
            }
            double transfer;
            double lineSpeed;
            double transmission;

            try{
                transfer = Double.parseDouble(transferTime.getText().toString());
                lineSpeed = Double.parseDouble(lineSpeedTime.getText().toString());
                transmission = Double.parseDouble(transmissionTime.getText().toString());
            } catch (NumberFormatException ne){
                Log.e(TAG, "onCreate: 数字変換エラー, ", ne);
                Toast.makeText(this, "数字への変換に失敗しました", Toast.LENGTH_SHORT).show();
                return;
            }

            double byteAmount = transfer * lineSpeed * transmission;

            bitAmountTime.setText(String.valueOf((byteAmount / 8)));
            bitAmountTime.setText(String.valueOf(byteAmount));

            if (bitAmountRate.getText().toString().isEmpty()){
                byteAmountRate.setText(String.valueOf(byteAmount));
                bitAmountRate.setText(String.valueOf((byteAmount / 8)));
            }
        });

        findViewById(R.id.calcLineSpeedTime).setOnClickListener(v -> {
            if(!convertDataAmountTime()){
                Toast.makeText(this, "byte ↔ bit の変換に失敗", Toast.LENGTH_SHORT).show();
                return;
            }
            double transfer;
            double bitAmount;
            double transmission;

            try{
                transfer = Double.parseDouble(transferTime.getText().toString());
                bitAmount = Double.parseDouble(bitAmountTime.getText().toString());
                transmission = Double.parseDouble(transmissionTime.getText().toString());
            } catch (NumberFormatException ne){
                Log.e(TAG, "onCreate: 数字変換エラー, ", ne);
                Toast.makeText(this, "数字への変換に失敗しました", Toast.LENGTH_SHORT).show();
                return;
            }

            double byteAmount = bitAmount * 8;

            double lineSpeed = (byteAmount) / (transfer * transmission);

            lineSpeedTime.setText(String.valueOf(lineSpeed));

            if (lineSpeedRate.getText().toString().isEmpty()){
                lineSpeedRate.setText(String.valueOf(lineSpeed));
            }
        });

        findViewById(R.id.calcTransmissionTime).setOnClickListener(v -> {
            if(!convertDataAmountTime()){
                Toast.makeText(this, "byte ↔ bit の変換に失敗", Toast.LENGTH_SHORT).show();
                return;
            }
            double transfer;
            double bitAmount;
            double lineSpeed;

            try{
                transfer = Double.parseDouble(transferTime.getText().toString());
                bitAmount = Double.parseDouble(bitAmountTime.getText().toString());
                lineSpeed = Double.parseDouble(lineSpeedTime.getText().toString());
            } catch (NumberFormatException ne){
                Log.e(TAG, "onCreate: 数字変換エラー, ", ne);
                Toast.makeText(this, "数字への変換に失敗しました", Toast.LENGTH_SHORT).show();
                return;
            }

            double byteAmount = bitAmount * 8;

            double transmission = byteAmount / (transfer * lineSpeed);

            transmissionTime.setText(String.valueOf(transmission));
        });

        findViewById(R.id.calcLineUtilizationRate).setOnClickListener(v -> {
            if(!convertDataAmountRate()){
                Toast.makeText(this, "byte ↔ bit の変換に失敗", Toast.LENGTH_SHORT).show();
                return;
            }
            double transfer;
            double bitAmount;
            double lineSpeed;

            try{
                transfer = Double.parseDouble(transferTimeRate.getText().toString());
                bitAmount = Double.parseDouble(bitAmountRate.getText().toString());
                lineSpeed = Double.parseDouble(lineSpeedRate.getText().toString());
            } catch (NumberFormatException ne){
                Log.e(TAG, "onCreate: 数字変換エラー, ", ne);
                Toast.makeText(this, "数字への変換に失敗しました", Toast.LENGTH_SHORT).show();
                return;
            }

            double byteAmount = bitAmount * 8;

            double lineUtilization = byteAmount / (lineSpeed * transfer);

            lineUtilizationRate.setText(String.valueOf(lineUtilization));
        });

        findViewById(R.id.calcBitAmountRate).setOnClickListener(v -> {
            if(!convertDataAmountRate()){
                Toast.makeText(this, "byte ↔ bit の変換に失敗", Toast.LENGTH_SHORT).show();
                return;
            }
            double transfer;
            double lineUtilization;
            double lineSpeed;

            try{
                transfer = Double.parseDouble(transferTimeRate.getText().toString());
                lineUtilization = Double.parseDouble(lineUtilizationRate.getText().toString());
                lineSpeed = Double.parseDouble(lineSpeedRate.getText().toString());
            } catch (NumberFormatException ne){
                Log.e(TAG, "onCreate: 数字変換エラー, ", ne);
                Toast.makeText(this, "数字への変換に失敗しました", Toast.LENGTH_SHORT).show();
                return;
            }

            double byteAmount = transfer * lineUtilization * lineSpeed;

            bitAmountRate.setText(String.valueOf(byteAmount / 8));
            byteAmountRate.setText(String.valueOf(byteAmount));

            if(bitAmountTime.getText().toString().isEmpty()){
                bitAmountTime.setText(String.valueOf(byteAmount / 8));
                byteAmountTime.setText(String.valueOf(byteAmount));
            }
        });

        findViewById(R.id.calcLineSpeedTimeRate).setOnClickListener(v -> {
            if(!convertDataAmountRate()){
                Toast.makeText(this, "byte ↔ bit の変換に失敗", Toast.LENGTH_SHORT).show();
                return;
            }
            double lineUtilization;
            double bitAmount;
            double transfer;

            try{
                lineUtilization = Double.parseDouble(lineUtilizationRate.getText().toString());
                bitAmount = Double.parseDouble(bitAmountRate.getText().toString());
                transfer = Double.parseDouble(transferTimeRate.getText().toString());
            } catch (NumberFormatException ne){
                Log.e(TAG, "onCreate: 数字変換エラー, ", ne);
                Toast.makeText(this, "数字への変換に失敗しました", Toast.LENGTH_SHORT).show();
                return;
            }

            double byteAmount = bitAmount * 8;

            double lineSpeed = (byteAmount) / (transfer * lineUtilization);

            lineSpeedRate.setText(String.valueOf(lineSpeed));

            if (lineSpeedTime.getText().toString().isEmpty()){
                lineSpeedTime.setText(String.valueOf(lineSpeed));
            }
        });

        findViewById(R.id.calcTransferRate).setOnClickListener(v -> {
            if(!convertDataAmountRate()){
                Toast.makeText(this, "byte ↔ bit の変換に失敗", Toast.LENGTH_SHORT).show();
                return;
            }
            double lineUtilization;
            double bitAmount;
            double lineSpeed;

            try{
                lineUtilization = Double.parseDouble(lineUtilizationRate.getText().toString());
                bitAmount = Double.parseDouble(bitAmountRate.getText().toString());
                lineSpeed = Double.parseDouble(lineSpeedRate.getText().toString());
            } catch (NumberFormatException ne){
                Log.e(TAG, "onCreate: 数字変換エラー, ", ne);
                Toast.makeText(this, "数字への変換に失敗しました", Toast.LENGTH_SHORT).show();
                return;
            }

            double byteAmount = bitAmount * 8;

            double transfer = (byteAmount) / (lineSpeed * lineUtilization);

            transferTimeRate.setText(String.valueOf(transfer));

            if (transferTime.getText().toString().isEmpty()){
                transferTime.setText(String.valueOf(transfer));
            }
        });
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, TransferCalcActivity.class);
        context.startActivity(starter);
    }

    private boolean convertDataAmountTime(){
        EditText bitAmount = findViewById(R.id.bitAmountTime);
        EditText byteAmount = findViewById(R.id.byteAmountTime);

        try {
            if (bitAmount.getText().toString().isEmpty() && byteAmount.getText().toString().isEmpty()) {
                return false;
            } else if (byteAmount.getText().toString().isEmpty()) {
                double bit = Double.parseDouble(bitAmount.getText().toString());
                byteAmount.setText(String.valueOf(bit * 8));
                return true;
            } else if (bitAmount.getText().toString().isEmpty()) {
                double _byte = Double.parseDouble(byteAmount.getText().toString());
                bitAmount.setText(String.valueOf(_byte / 8));
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException numberFormatException){
            Log.e(TAG, "convertDataAmountTime: ", numberFormatException);
            return false;
        }
    }

    private boolean convertDataAmountRate(){
        EditText bitAmount = findViewById(R.id.bitAmountRate);
        EditText byteAmount = findViewById(R.id.byteAmountRate);

        try {
            if (bitAmount.getText().toString().isEmpty() && byteAmount.getText().toString().isEmpty()) {
                return false;
            } else if (byteAmount.getText().toString().isEmpty()) {
                double bit = Double.parseDouble(bitAmount.getText().toString());
                byteAmount.setText(String.valueOf(bit * 8));
                return true;
            } else if (bitAmount.getText().toString().isEmpty()) {
                double _byte = Double.parseDouble(byteAmount.getText().toString());
                bitAmount.setText(String.valueOf(_byte / 8));
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException numberFormatException){
            Log.e(TAG, "convertDataAmountTime: ", numberFormatException);
            return false;
        }
    }
}