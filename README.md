# Android Social Sharing Library

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
- Available on Maven Central, Jitpack and GitHub packages

## Installation

### Gradle

```groovy
dependencies {
    implementation "io.bayat.android:social-sharing-unity:1.0.0"

    ... // Other dependencies
}
```

### Unity

1. Install [External Dependency Manager for Unity](https://github.com/googlesamples/unity-jar-resolver)
2. Add the dependency to your dependencies xml file to your Unity project:

    ```xml
    <dependencies>
    <androidPackages>
        <androidPackage spec="io.bayat.android:social-sharing-unity:1.0.0" />
    </androidPackages>
    </dependencies>
    ```

3. Use it through [Android JNI](https://docs.unity3d.com/Manual/android-plugins-java-code-from-c-sharp.html)

## Usage

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
String mimeType = "text/plain";
String message = "Hello World";
SocialSharing sharing = new SocialSharing.Builder()
        .useSingle()
        .setText(message)
        .setUseRichPreview(true)
        .setUseSharesheet(true)
        .setType(mimeType)
        .build();
```

- **Basic**: Call `setUseSharesheet(false)` to use basic sharing:

```java
String mimeType = "text/plain";
String message = "Hello World";
SocialSharing sharing = new SocialSharing.Builder()
        .useSingle()
        .setText(message)
        .setUseRichPreview(true)
        .setUseSharesheet(false)
        .setType(mimeType)
        .build();
```

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
