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
        protected Boolean isMultiple = false;
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
        protected Uri mainUri = null;
        protected Uri thumbnailUri = null;
        @Keep
        protected ArrayList<Uri> uris = new ArrayList<>();

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
            this.text = text;
            return this;
        }

        /**
         * Sets the thumbnail URI to the file or image for rich preview.
         */
        @Keep
        public Builder setThumbnailUri(Uri uri) {
            this.thumbnailUri = uri;
            return this;
        }

        /**
         * Sets the thumbnail URI from string.
         * @param uriString The URI string to be parsed
         */
        public Builder setThumbnailUriFromString(String uriString) {
            return setThumbnailUri(Uri.parse(uriString));
        }

        /**
         * Sets the URI to the file or image.
         */
        @Keep
        public Builder setUri(Uri uri) {
            this.mainUri = uri;
            return this;
        }

        /**
         * Sets the URI from string.
         * @param uriString The URI string to be parsed
         */
        public Builder setUriFromString(String uriString) {
            return setUri(Uri.parse(uriString));
        }

        /**
         * Adds the URI for the file or image if using multi send action
         */
        @Keep
        public Builder addUri(Uri uri) {
            this.uris.add(uri);
            return this;
        }

        /**
         * Adds a URI from string.
         * @param uriString The URI string to be parsed
         */
        public Builder addUriFromString(String uriString) {
            return addUri(Uri.parse(uriString));
        }

        /**
         * Sets the mime type for this sharing (used for the URIs)
         */
        @Keep
        public Builder setType(String mimeType) {
            this.mimeType = mimeType;
            return this;
        }

        /**
         * Sets whether to use rich preview or not.
         */
        @Keep
        public Builder setUseRichPreview(boolean value) {
            this.useRichPreview = value;
            return this;
        }

        /**
         * Sets whether to use sharesheet or not.
         */
        @Keep
        public Builder setUseSharesheet(boolean value) {
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
        protected Intent buildIntent() {
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT && this.isMultiple != null && this.isMultiple) {
                intent.setAction(Intent.ACTION_SEND_MULTIPLE);
                if (this.uris != null && this.uris.size() > 0) {
                    intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, this.uris);
                }
            } else {
                intent.setAction(Intent.ACTION_SEND);
                if (this.mainUri != null) {
                    intent.putExtra(Intent.EXTRA_STREAM, this.mainUri);
                }
            }
            if (this.text != null) {
                intent.putExtra(Intent.EXTRA_TEXT, this.text);
            }
            if (this.useRichPreview) {
                if (this.title != null) {
                    intent.putExtra(Intent.EXTRA_TITLE, this.title);
                }

                if (this.thumbnailUri != null) {
                    intent.setData(this.thumbnailUri);
                }
            }
            intent.setType(this.mimeType);

            if (this.useSharesheet == null || this.useSharesheet) {
                intent = Intent.createChooser(intent, this.title);
            }

            return intent;
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