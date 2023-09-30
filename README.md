# Android Social Sharing Library

A social sharing library for Android

[Learn more](https://developer.android.com/training/sharing/send)

## Features

- Provides a convenient interface to be used in Unity as a native plugin or JNI
- Supports Sharesheet
- Supports basic sharing
- Supports text (prefilled text, some social platforms do not support it)
- Supports binary content (such as images)
- Supports custom mime type

## Installation

### Gradle

Add this line to dependencies:

```gradle
implementation "io.bayat.android.social.sharing"
```

## Usage

- **Sharesheet**: Call `SocialSharing.sharesheet` to open Android's sharesheet:

```java
SocialSharing.sharesheet();
```

-- **Basic**: Call `SocialSharing.share` to open basic sharing:

```java
SocialSharing.share();
```

## Resources

- [Android - Send simple data to other apps](https://developer.android.com/training/sharing/send)