package ua.edu.sumdu.powerreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {
    private static final String ACTION_CUSTOM_BROADCAST =
            BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";

    public static final String EXTRA_INT = "EXTRA_INT_NUMBER";

    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();

        if (intentAction != null) {
            String message = context.getString(R.string.unknown_action);
            switch (intentAction) {
                case Intent.ACTION_POWER_CONNECTED:
                    message = context.getString(R.string.power_connected);
                    break;
                case Intent.ACTION_POWER_DISCONNECTED:
                    message = context.getString(R.string.power_disconnected);
                    break;
                case ACTION_CUSTOM_BROADCAST:
                    if (intent.hasExtra(EXTRA_INT)) {
                        int number = intent.getIntExtra(EXTRA_INT, 0);
                        message = context.getString(
                                R.string.custom_broadcast_toast,
                                (int) Math.pow(number, 2)
                        );
                    }
                    break;
            }

            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }
}
