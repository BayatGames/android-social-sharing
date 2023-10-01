package io.bayat.android.social.sharing.unity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.Keep;
import androidx.core.content.FileProvider;

import com.unity3d.player.UnityPlayer;

import java.io.File;

import io.bayat.android.social.sharing.SocialSharing;

/**
 * A simple social sharing API that includes convenience for Unity Android JNI.
 */
@Keep
public class SocialSharingUnity extends SocialSharing {

    @Keep
    private static final String ProviderSuffix  = ".bayat.social.sharing.provider";

    @Keep
    public static class Builder extends SocialSharing.Builder {

        @Keep
        public Builder setUnityUri(String path) {
            Uri uri = getUriFromPathUnity(path);
            setUri(uri);
            return this;
        }

        @Keep
        public Builder addUnityUri(String path) {
            Uri uri = getUriFromPathUnity(path);
            addUri(uri);
            return this;
        }

    }

    @Keep
    public SocialSharingUnity(Intent intent) {
        super(intent);
    }

    /**
     * Sends the intent using Unity Player's current activity.
     */
    @Keep
    public void send() {
        super.send(UnityPlayer.currentActivity);
    }

    /**
     * Gets the URI to the Unity file path.
     * @param path The file path
     * @return Returns the URI to the file path
     */
    @Keep
    public static Uri getUriFromPathUnity(String path) {
        Context unityContext = UnityPlayer.currentActivity.getApplicationContext();
        return FileProvider.getUriForFile(unityContext, unityContext.getPackageName() + ProviderSuffix, new File(path));
    }

}
