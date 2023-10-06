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
        public Builder() {
            super(UnityPlayer.currentActivity);
        }

        /**
         * Sets the URI using Unity's FileProvider that resolves the path to URI.
         * @param path The path to be resolved
         */
        @Keep
        public Builder setUnityStream(String path) {
            Uri uri = getUriFromPathUnity(path);
            setStream(uri);
            return this;
        }

        /**
         * Adds a URI using Unity's FileProvider that resolves the path to URI.
         * @param path The path to be resolved
         */
        @Keep
        public Builder addUnityStream(String path) {
            Uri uri = getUriFromPathUnity(path);
            addStream(uri);
            return this;
        }

        @Override
        public SocialSharingUnity build() {
            return new SocialSharingUnity(buildIntent());
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
        return UnityFileProvider.getUriForFile(unityContext, unityContext.getPackageName() + ProviderSuffix, new File(path));
    }

}
