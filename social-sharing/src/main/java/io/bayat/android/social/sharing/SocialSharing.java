package io.bayat.android.social.sharing;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import androidx.core.app.ShareCompat;

import java.util.ArrayList;

/**
 * A simple social sharing API.
 */
@Keep
public class SocialSharing {

    @Keep
    public static class Builder {

        @Keep
        protected ShareCompat.IntentBuilder builder;

        @Keep
        public Builder(Context launchingContext) {
            this.builder = new ShareCompat.IntentBuilder(launchingContext);
        }

        /**
         * Sets the text to be shared.
         */
        @Keep
        public Builder setText(String text) {
            this.builder.setText(text);
            return this;
        }

        /**
         * Sets the HTML text to be shared.
         */
        @Keep
        public Builder setHtmlText(String htmlText) {
            this.builder.setHtmlText(htmlText);
            return this;
        }

        /**
         * Sets the stream URI to be shared.
         */
        @Keep
        public Builder setStream(Uri streamUri) {
            this.builder.setStream(streamUri);
            return this;
        }

        /**
         * Adds the stream URI to be shared.
         */
        @Keep
        public Builder addStream(Uri streamUri) {
            this.builder.addStream(streamUri);
            return this;
        }

        /**
         * Sets the chooser title to be shared.
         */
        @Keep
        public Builder setChooserTitle(String title) {
            this.builder.setChooserTitle(title);
            return this;
        }

        /**
         * Sets the mime type for this sharing (used for the URIs)
         */
        @Keep
        public Builder setType(String mimeType) {
            this.builder.setType(mimeType);
            return this;
        }

        @Keep
        public void startChooser() {
            this.builder.startChooser();
        }

        @Keep
        protected Intent buildIntent() {
            return this.builder.getIntent();
//            return this.builder.createChooserIntent();
        }

        @Keep
        public SocialSharing build() {
            return new SocialSharing(buildIntent());
        }

    }

    @Keep
    protected Intent intent;

    /**
     * Creates a new instance of SocialSharing class.
     * @param intent The share intent
     */
    @Keep
    public SocialSharing(Intent intent) {
        this.intent = intent;
    }

    /**
     * Sends the sharing intent using the given activity
     * @param activity The activity to use to send the intent with
     */
    @Keep
    public void send(Activity activity) {
        activity.startActivity(this.intent);
    }

}