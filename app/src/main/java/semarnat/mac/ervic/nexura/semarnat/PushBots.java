package semarnat.mac.ervic.nexura.semarnat;

import android.app.Application;

import com.pushbots.push.Pushbots;

/**
 * Created by ervic on 30/10/17.
 */

public class PushBots extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Pushbots.sharedInstance().init(this);
        Pushbots.sharedInstance().setCustomHandler(customHandler.class);
    }

}
