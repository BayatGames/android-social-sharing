package io.bayat.android.social.sharing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import java.io.File;
import java.util.ArrayList;

/**
 * A simple social sharing API.
 */
@Keep
public class SocialSharing {

    @Keep
    public static class Builder {

        @Keep
        @Nullable
        protected Boolean isMultiple = null;
        @Keep
        @Nullable
        protected Boolean useSharesheet = null;
        @Keep
        protected Boolean useRichPreview = false;

        @Keep
        protected String mimeType = "*/*";
        @Keep
        protected String title = null;
        @Keep
        protected String text = null;
        @Keep
        protected Uri uri = null;
        @Keep
        protected ArrayList<Uri> uris = new ArrayList<>();

        @Keep
        protected void throwIfUnspecified() throws IllegalStateException {
            if (this.isMultiple == null) {
                throw new IllegalStateException("Call useSingle() or useMultiple() first.");
            }
        }

        /**
         * Uses single send action.
         */
        @Keep
        public Builder useSingle() {
            this.isMultiple = false;
            return this;
        }

        /**
         * Uses multi send action.
         */
        @Keep
        public Builder useMultiple() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
                this.isMultiple = true;
            }
            return this;
        }

        /**
         * Sets the text to be shared.
         */
        @Keep
        public Builder setText(String text) {
            throwIfUnspecified();
            this.text = text;
            return this;
        }

        /**
         * Sets the URI to the file or image.
         */
        @Keep
        public Builder setUri(Uri uri) {
            throwIfUnspecified();
            this.uri = uri;
            return this;
        }

        /**
         * Adds the URI for the file or image if using multi send action
         */
        @Keep
        public Builder addUri(Uri uri) {
            throwIfUnspecified();
            this.uris.add(uri);
            return this;
        }

        /**
         * Sets the mime type for this sharing (used for the URIs)
         */
        @Keep
        public Builder setType(String mimeType) {
            throwIfUnspecified();
            this.mimeType = mimeType;
            return this;
        }

        /**
         * Sets whether to use rich preview or not.
         */
        @Keep
        public Builder setUseRichPreview(Boolean value) {
            throwIfUnspecified();
            this.useRichPreview = value;
            return this;
        }

        /**
         * Sets whether to use sharesheet or not.
         */
        @Keep
        public Builder setUseSharesheet(@Nullable Boolean value) {
            this.useSharesheet = value;
            return this;
        }

        /**
         * Sets the sharesheet title.
         */
        @Keep
        public Builder setSharesheetTitle(String title) {
            this.title = title;
            return this;
        }

        @Keep
        public SocialSharing build() {
            throwIfUnspecified();

            Intent intent = new Intent();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT && this.isMultiple != null && this.isMultiple) {
                intent.setAction(Intent.ACTION_SEND_MULTIPLE);
                if (this.uris != null && this.uris.size() > 0) {
                    intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, this.uris);
                }
            } else {
                intent.setAction(Intent.ACTION_SEND);
                if (this.uri != null) {
                    intent.putExtra(Intent.EXTRA_STREAM, this.uri);
                }
            }
            if (this.useRichPreview) {
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }
            if (this.text != null) {
                intent.putExtra(Intent.EXTRA_TEXT, this.text);
            }
            intent.setType(this.mimeType);

            if (this.useSharesheet == null || this.useSharesheet) {
                intent = Intent.createChooser(intent, this.title);
            }

            return new SocialSharing(intent);
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