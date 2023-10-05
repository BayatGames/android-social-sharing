package io.bayat.android.social.sharing;

import static android.content.Intent.EXTRA_CHOSEN_COMPONENT;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

public class SocialSharingBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "SocialSharingBroadcastReceiver";

    protected SocialSharingCallback callback;

    public SocialSharingBroadcastReceiver(SocialSharingCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ComponentName clickedComponent = intent.getParcelableExtra(EXTRA_CHOSEN_COMPONENT);
    }

}