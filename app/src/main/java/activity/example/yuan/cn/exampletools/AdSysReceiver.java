package activity.example.yuan.cn.exampletools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * @author Angus
 * @company 9zhitx.com
 */
public class AdSysReceiver extends BroadcastReceiver{


    // 防止多次同时调用
    @Override
    public void onReceive(Context context, Intent intent) {


        Intent i = new Intent(context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

        if (intent == null || intent.getAction() == null) {
            return;
        }
        String action = intent.getAction();

        if (Intent.ACTION_SCREEN_OFF.equals(action)) {
            // 屏幕关
            Toast.makeText(context,"屏幕关",Toast.LENGTH_SHORT).show();
        } else if (Intent.ACTION_USER_PRESENT.equals(action)) {
            // 屏幕解锁
            Toast.makeText(context,"屏幕解锁.",Toast.LENGTH_SHORT).show();
        } else if (Intent.ACTION_BOOT_COMPLETED.equals(action)) {
            Toast.makeText(context,"开机",Toast.LENGTH_SHORT).show();
            Intent ootStartIntent = new Intent(context, MainActivity.class);
            ootStartIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(ootStartIntent);
        }
    }

}
