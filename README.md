# Android Social Sharing Library

[![Publish](https://github.com/BayatGames/android-social-sharing/actions/workflows/publish.yml/badge.svg)](https://github.com/BayatGames/android-social-sharing/actions/workflows/publish.yml)

A social sharing library for Android, maintained by [Bayat][bayat]

## Features

- Uses Builder pattern
- Provides a convenient interface to be used in Unity as a native plugin or JNI
- Supports Sharesheet
- Supports basic sharing
- Supports text (prefilled text, some social platforms do not support it)
- Supports binary content (such as images)
- Supports custom mime type
- Aimed for JNI and native bridge usages
- Available on Maven Central and GitHub packages

## Installation

Artifact is available on Maven Central, Jitpack and GitHub packages.

```groovy
io.bayat.android:social-sharing:1.0.0
io.bayat.android:social-sharing-unity:1.0.2
```

### Gradle Setup

- Add as a dependency:

```groovy
dependencies {
    implementation "io.bayat.android:social-sharing:1.0.0"

    ... // Other dependencies
}
```

### Unity Setup

1. Install [External Dependency Manager for Unity](https://github.com/googlesamples/unity-jar-resolver)
2. Add the dependency to your dependencies xml file to your Unity project:

    ```xml
    <dependencies>
        <androidPackages>
            <androidPackage spec="io.bayat.android:social-sharing-unity:1.0.2" />
        </androidPackages>
    </dependencies>
    ```

3. Use it through [Android JNI](https://docs.unity3d.com/Manual/android-plugins-java-code-from-c-sharp.html)

## Usage

### Android Usage

Use the `SocialSharing.Builder` to create a `SocialSharing` instance:

```java
SocialSharing sharing = new SocialSharing.Builder()
        .useSingle()
        .setText(message)
        .setUseRichPreview(true)
        .setUseSharesheet(true)
        .setType(mimeType)
        .build();
```

- **Sharesheet**: Call `setUseSharesheet(true)` to use Android's Sharesheet:

```java
String mimeType = "image/jpeg";
String message = "Hello World";
SocialSharing sharing = new SocialSharing.Builder()
        .useSingle()
        .setText(message)
        .setUseRichPreview(true)
        .setUseSharesheet(true)
        .setUri(Uri.fromFile(new File("/sdcard/cats.jpg"))
        .setType(mimeType)
        .build();

// Call send method
sharing.send(myActivity);
```

- **Basic**: Call `setUseSharesheet(false)` to use basic sharing:

```java
String mimeType = "image/jpeg";
String message = "Hello World";
SocialSharing sharing = new SocialSharing.Builder()
        .useSingle()
        .setText(message)
        .setUseRichPreview(true)
        .setUseSharesheet(false)
        .setUri(Uri.fromFile(new File("/sdcard/cats.jpg"))
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
        .useSingle()
        .setText(message)
        .setUseRichPreview(true)
        .setUseSharesheet(false)
        .setUnityUri("myscreenshot.jpg")
        .setType(mimeType)
        .build();

// Call send method, the activity parameter is not required as the SocialSharingUnity will use the current activity of UnityPlayer
sharing.send();
```

Also for JNI use cases, use this class and the methods of `setUnityUri` and `addUnityUri` for specifying files and content to be shared instead of `setUri` or `addUri`.

## API

### SocialSharing

- `send(Activity activity)`: Send the content to be shared using the given Activity

#### SocialSharing.Builder

- `useSingle()`: Use single sharing
- `useMultiple()`: Use multiple sharing
- `setText(String text)`: Set the text to be shared
- `setUri(Uri uri)`: Set the URI of the content to be shared
- `setUriFromString(String uriString)`: Set the URI of the content to be shared from a string
- `addUri(Uri uri)`: Add a URI to the content to be shared
- `addUriFromString(String uriString)`: Add a URI to the content to be shared from a string
- `setUseRichPreview(boolean useRichPreview)`: Set whether to use rich preview or not
- `setUseSharesheet(boolean useSharesheet)`: Set whether to use sharesheet or not
- `setSharesheetTitle(String title)`: Set the sharesheet title
- `setType(String type)`: Set the mime type of the content to be shared
- `build()`: Build the `SocialSharing` instance

### SocialSharingUnity

- `send()`: Send the content to be shared (uses UnityPlayer.currentActivity as the Activity)

#### SocialSharingUnity.Builder

Extends from `SocialSharing.Builder` and adds the following methods:

- `setUnityUri(String unityUri)`: Set the URI of the content to be shared from a Unity (resolves the file path to a content URI in Android)
- `addUnityUri(String unityUri)`: Add a URI to the content to be shared from a Unity (resolves the file path to a content URI in Android)

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

Made with ❤️ by [Bayat](bayat)

[changelog]: ../blob/main/CHANGELOG.md
[license]: ../blob/main/LICENSE
[bayat]: https://bayat.io
[bayatgames]: https://github.com/BayatGames
