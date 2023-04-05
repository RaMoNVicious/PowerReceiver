package ua.edu.sumdu.powerreceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private final CustomReceiver mReceiver = new CustomReceiver();

    private TextView mShowNumber;

    private static final String ACTION_CUSTOM_BROADCAST =
            BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mShowNumber = findViewById(R.id.showNumber);

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);

        this.registerReceiver(mReceiver, filter);

        LocalBroadcastManager
                .getInstance(this)
                .registerReceiver(
                        mReceiver,
                        new IntentFilter(ACTION_CUSTOM_BROADCAST)
                );
    }

    public void sendCustomBroadcast(View view) {
        Intent customBroadcastIntent = new Intent(ACTION_CUSTOM_BROADCAST);

        int number = new Random().nextInt(20);
        customBroadcastIntent.putExtra(CustomReceiver.EXTRA_INT, number);

        mShowNumber.setText(getString(R.string.custom_broadcast_extra, number));

        LocalBroadcastManager
                .getInstance(this)
                .sendBroadcast(customBroadcastIntent);
    }

    @Override
    protected void onDestroy() {
        this.unregisterReceiver(mReceiver);
        LocalBroadcastManager
                .getInstance(this)
                .unregisterReceiver(mReceiver);
        super.onDestroy();
    }
}
