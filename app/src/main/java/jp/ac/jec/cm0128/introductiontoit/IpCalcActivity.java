package jp.ac.jec.cm0128.introductiontoit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class IpCalcActivity extends AppCompatActivity {

    private static final String TAG = "IpCalcActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip_calc);

        EditText subnetEditor = findViewById(R.id.subnetMaskEditor);
        EditText prefixEditor = findViewById(R.id.prefixLengthEditor);

        TextView networkAddress = findViewById(R.id.networkAddressView);
        TextView broadcastAddress = findViewById(R.id.broadcastAddressView);
        TextView availableHostAmount = findViewById(R.id.availableHostAmountView);

        findViewById(R.id.subnetMaskToPrefix).setOnClickListener(v -> {
            String input = subnetEditor.getText().toString();
            String[] inputs = input.split("\\.");
            if (inputs.length != 4){
                Toast.makeText(this, "ドットの数が不正", Toast.LENGTH_SHORT).show();
                return;
            }
            int[] subnetMask = new int[4];

            for (int i = 0; i < 4; i++) {
                try {
                    subnetMask[i] = Integer.parseInt(inputs[i]);
                } catch (NumberFormatException ne){
                    Toast.makeText(this, "第" + (i + 1) + "オクテットの数字が不正", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            int prefix = 0;

            for (int bit:
                 subnetMask) {
                for(int div = 0b01111111; true; div /= 2){
                    if (bit == 0){
                        break;
                    }

                    if (bit >= div){
                        prefix++;
                        bit -= div + 1;
                        Log.i(TAG, "bit: " + bit + ", div: " + div + ", true");
                    } else {
                        Log.i(TAG, "bit: " + bit + ", div: " + div + ", false");
                    }

                    if (div == 0){
                        break;
                    }
                }
            }

            prefixEditor.setText(String.valueOf(prefix));


        });

        findViewById(R.id.prefixToSubnetMask).setOnClickListener(v -> {
            int prefix;
            try {
                prefix = Integer.parseInt(prefixEditor.getText().toString());
            } catch (NumberFormatException ne){
                Toast.makeText(this, "プレフィックス長の数字変換にエラー", Toast.LENGTH_SHORT).show();
                return;
            }
            if (prefix > 32){
                Toast.makeText(this, "プレフィックス長が不正", Toast.LENGTH_SHORT).show();
                return;
            }

            StringBuilder subnetMask = new StringBuilder();

            while(prefix > 0){
                if(prefix >= 8){
                    prefix -= 8;
                    subnetMask.append("255.");
                    continue;
                }
                if (prefix == 0){
                    subnetMask.append("0");
                    break;
                }
                for(int i = 0; i < 8; i++){
                    if(prefix > i ){
                        continue;
                    }
                    subnetMask.append(Math.pow(2, i) - 1);
                    break;
                }
                break;
            }

            subnetEditor.setText(subnetMask.toString());
        });

        EditText fullIPv6 = findViewById(R.id.ipv6FullEditor);
        EditText shortIPv6 = findViewById(R.id.ipv6ShortEditor);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, IpCalcActivity.class);
        context.startActivity(starter);
    }

    public int countChars(String input, char target) {
        int count = 0;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == target) {
                count++;
            }
        }

        return count;
    }
}