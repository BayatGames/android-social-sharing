# Android Social Sharing Library

A social sharing library for Android

## Features

- Uses Builder pattern
- Provides a convenient interface to be used in Unity as a native plugin or JNI
- Supports Sharesheet
- Supports basic sharing
- Supports text (prefilled text, some social platforms do not support it)
- Supports binary content (such as images)
- Supports custom mime type
- Aimed for JNI and native bridge usages

## Installation

### Gradle

```groovy
implementation "io.bayat.android:social-sharing-unity:1.0.0"
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

## Resources

- [Android - Send simple data to other apps](https://developer.android.com/training/sharing/send)
- [Call Java and Kotlin plug-in code from C# scripts](https://docs.unity3d.com/Manual/android-plugins-java-code-from-c-sharp.html)

## License

Check the [LICENSE](https://github.com/BayatGames/android-social-sharing/blob/main/LICENSE) file for more information.

## Credits

Made with ❤️ by Bayat