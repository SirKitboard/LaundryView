package io.github.adibalwani03.laundryview;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.telephony.gsm.SmsManager;
import android.widget.Toast;

/**
 * Created by Aditya on 12/14/2014.
 */
public class AlarmReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        boolean washer = intent.getBooleanExtra("washer",true);
        int machineNo = intent.getIntExtra("machineNo", 0);
        final Intent emptyIntent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, emptyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon((washer)?R.drawable.washer_available:R.drawable.drier_available)
                        .setContentTitle("Cycle Completed")
                        .setContentText((washer)?"You clothes have been washed in Machine number " + (machineNo + 1):"You clothes have been dried in Machine number " + (machineNo + 1))
                        .setContentIntent(pendingIntent);
        mBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM));
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(123123123, mBuilder.build());
    }

}

