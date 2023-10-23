# Android Social Sharing Library

[![Publish](https://github.com/BayatGames/android-social-sharing/actions/workflows/publish.yml/badge.svg)](https://github.com/BayatGames/android-social-sharing/actions/workflows/publish.yml) [![Release](https://jitpack.io/v/BayatGames/android-social-sharing.svg)](https://jitpack.io/#BayatGames/android-social-sharing)

A social sharing library for Android.

Actively maintained.

## Features

- Uses Builder pattern
- Provides a convenient interface to be used in Unity as a native plugin or JNI
- Supports Sharesheet/Chooser
- Supports basic sharing
- Supports HTML text and literal text (prefilled text, some social platforms do not support it)
- Supports binary content (such as images)
- Supports custom mime type
- Aimed for JNI and native bridge usages
- Available on Maven Central, Jitpack and GitHub packages
- Uses [`ShareCompat.IntentBuilder`](https://developer.android.com/reference/androidx/core/app/ShareCompat.IntentBuilder)
- Provides custom methods for Unity

## Installation

Artifact is available on Maven Central, Jitpack and GitHub packages.

```groovy
io.bayat.android:social-sharing:1.1.0
io.bayat.android:social-sharing-unity:1.1.0
```

### Gradle Setup

- Add as a dependency:

```groovy
dependencies {
    implementation "io.bayat.android:social-sharing:1.1.0"

    ... // Other dependencies
}
```

### Unity Setup

1. Install [External Dependency Manager for Unity](https://github.com/googlesamples/unity-jar-resolver)
2. Add the dependency to your dependencies xml file to your Unity project:

    ```xml
    <dependencies>
        <androidPackages>
            <androidPackage spec="io.bayat.android:social-sharing-unity:1.1.0" />
        </androidPackages>
    </dependencies>
    ```

3. Use it through [Android JNI](https://docs.unity3d.com/Manual/android-plugins-java-code-from-c-sharp.html)

## Usage

### Android Usage

Use the `SocialSharing.Builder` to create a `SocialSharing` instance:

```java
String mimeType = "text/plain";
String message = "Hello World";
SocialSharing sharing = new SocialSharing.Builder(myActivity)
        .setText(message)
        .setType(mimeType)
        .build();
```

- **Sharesheet**: Call `setUseSharesheet(true)` to use Android's Sharesheet:

```java
String mimeType = "image/jpeg";
String message = "Hello World";
SocialSharing sharing = new SocialSharing.Builder(myActivity)
        .setText(message)
        .setStream(Uri.fromFile(new File("/sdcard/cats.jpg"))
        .setType(mimeType)
        .build();

// Call send method
sharing.send(myActivity);
```

- **Basic**: Call `setUseSharesheet(false)` to use basic sharing:

```java
String mimeType = "image/jpeg";
String message = "Hello World";
SocialSharing sharing = new SocialSharing.Builder(myActivity)
        .setText(message)
        .setStream(Uri.fromFile(new File("/sdcard/cats.jpg"))
        .setType(mimeType)
        .build();

// Call send method
sharing.send(myActivity);
```

### Unity Usage

The `SocialSharingUnity` extends from `SocialSharing` so it extends the `Builder` too, the only differences are the URI and the `send` method:

```java
String mimeType = "image/jpeg";
String message = "Hello World";
SocialSharingUnity sharing = new SocialSharingUnity.Builder()
        .setText(message)
        .setUnityStream("myscreenshot.jpg")
        .setType(mimeType)
        .build();

// Call send method, the activity parameter is not required as the SocialSharingUnity will use the current activity of UnityPlayer
sharing.send();
```

Also for JNI use cases, use this class and the methods of `setUnityStream` and `addUnityStream` for specifying files and content to be shared instead of `setUri` or `addUri`:

```csharp
const string BuilderClassName = "io.bayat.android.social.sharing.unity.SocialSharingUnity$UnityBuilder";

bool useSharesheet = true;
bool useRichPreview = true;
string text = "Hello World from Unity!";
string title = "My Custom Chooser Title";
string imagePath = Application.persistentDataPath + "/screenshot.jpg";
string mimeType = "image/jpg";

using (AndroidJavaObject builder = new AndroidJavaObject(BuilderClassName))
{
    builder.Call<AndroidJavaObject>("setText", text);
    builder.Call<AndroidJavaObject>("setChooserTitle", title);
    builder.Call<AndroidJavaObject>("setUnityStream", imagePath);
    builder.Call<AndroidJavaObject>("setType", data.MimeType);
    builder.Call("startChooser");
}
```

## API

### SocialSharing

- `send(Activity activity)`: Send the content to be shared using the given Activity

#### SocialSharing.Builder

- `setText(String text)`: Set the text to be shared
- `setHtmlText(String htmlText)`: Set the HTML text to be shared (the HTML text is parsed before sharing, useful for formatting)
- `setStream(Uri uri)`: Set the URI of the content to be shared
- `addStream(Uri uri)`: Add a URI to the content to be shared
- `setChoosterTitle(String title)`: Set the sharesheet/chooser title
- `setType(String type)`: Set the mime type of the content to be shared
- `build()`: Build the `SocialSharing` instance
- `startChooser`: Start the sharesheet/chooser activity

### SocialSharingUnity

- `send()`: Send the content to be shared (uses UnityPlayer.currentActivity as the Activity)

#### SocialSharingUnity.Builder

Extends from `SocialSharing.Builder` and adds the following methods:

- `setUnityStream(String path)`: Set the URI of the content to be shared from a Unity (resolves the file path to a content URI in Android)
- `addUnityStream(String path)`: Add a URI to the content to be shared from a Unity (resolves the file path to a content URI in Android)

## Notes

The `setText` method sets a prefilled text to be shared by the user, but some social platforms do not support it, mainly **Facebook** amd all of their other various social services including **Instagram**, **Messenger**, **WhatsApp** and **Facebook Lite**, but other platforms such as **Twitter**, **Discord**, **Slack** and others support it. (this won't have any issues on your app, it will just ignore the text and share the content without it)

[Learn more on Meta Platform Terms](https://developers.facebook.com/terms/) and [Platform Policy 2.3 Example and Explanation](https://developers.facebook.com/docs/apps/review/prefill)

## Changelog

Check the [CHANGELOG.md][changelog] file for more information.

## Resources

- [Android - Send simple data to other apps](https://developer.android.com/training/sharing/send)
- [Call Java and Kotlin plug-in code from C# scripts](https://docs.unity3d.com/Manual/android-plugins-java-code-from-c-sharp.html)
- [NicholasSheehan/Unity-Native-Sharing](https://github.com/NicholasSheehan/Unity-Native-Sharing)

## License

Check the [LICENSE][license] file for more information.

## Credits

- Developed and maintained by [Hasan Bayat][hasanbayat]

Made with ❤️ by [Bayat][bayat]

[changelog]: CHANGELOG.md
[license]: LICENSE
[bayat]: https://bayat.io
[hasanbayat]: https://github.com/hasanbayatme
[bayatgames]: https://github.com/BayatGames
