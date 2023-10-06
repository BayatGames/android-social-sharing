package io.bayat.android.social.sharing;

import android.content.Context;
import android.content.Intent;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
//        assertEquals("io.bayat.android.social.sharing.test", appContext.getPackageName());

        String mimeType = "text/plain";
        String message = "Hello World";
        SocialSharing sharing = new SocialSharing.Builder(appContext)
                .setText(message)
                .setType(mimeType)
                .build();

        assertEquals(sharing.intent.getStringExtra(Intent.EXTRA_TEXT), message);
        assertEquals(sharing.intent.getFlags(), Intent.FLAG_GRANT_READ_URI_PERMISSION);
        assertEquals(sharing.intent.getType(), mimeType);
        assertEquals(sharing.intent.getAction(), Intent.ACTION_SEND);
    }
}