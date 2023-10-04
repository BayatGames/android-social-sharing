package io.bayat.android.social.sharing;

import org.junit.Test;

import static org.junit.Assert.*;

import android.content.Intent;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        String mimeType = "text/plain";
        String message = "Hello World";
        SocialSharing sharing = new SocialSharing.Builder()
                .useSingle()
                .setText(message)
                .setUseRichPreview(true)
                .setUseSharesheet(true)
                .setType(mimeType)
                .build();

        assertEquals(sharing.intent.getStringExtra(Intent.EXTRA_TEXT), message);
        assertEquals(sharing.intent.getFlags(), Intent.FLAG_GRANT_READ_URI_PERMISSION);
        assertEquals(sharing.intent.getType(), mimeType);
        assertEquals(sharing.intent.getAction(), Intent.ACTION_SEND);
    }
}