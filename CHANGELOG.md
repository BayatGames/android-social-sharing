# Changelog

This changelog follows [keep a changelog](https://keepachangelog.com/) guidelines and uses [semantic versioning](https://semver.org/).

The date format is `mm/dd/yyyy`.

# 10/6/2023 - 1.1.0

## social-sharing

### ⬆️ Improved

- Now uses `ShareCompat.IntentBuilder` instead of manually creating the intent (Learn more about [ShareCompat.IntentBuilder](https://developer.android.com/reference/androidx/core/app/ShareCompat.IntentBuilder))
- Added `@Keep` to the `UnityFileProvider` class to prevent it from being removed by optimizations

### ⚠️ Changed

- Renamed `setUri()` to `setStream()`
- Renamed `addUri()` to `addStream()`
- Renamed `setUri()` to `setStream()`
- Renamed `setSharesheetTitle()` to `setChooserTitle()`

### ❌ Removed

- Removed `useSingle()`
- Removed `useMultiple()`
- Removed `setThumbnailUri()`
- Removed `setThumbnailUriFromString()`
- Removed `setUriFromString()`
- Removed `addUriFromString()`
- Removed `setUseRichPreview()`
- Removed `setUseSharesheet()`

## social-sharing-unity

### 🐞 Fixed

- Replaced the call to the `FileProvider` in the `getUriFromPathUnity()` method of `SocialSharingUnity` class with `UnityFileProvider`

### ⚠️ Changed

- Renamed `setUnityUri()` to `setUnityStream()`
- Renamed `addUnityUri()` to `addUnityStream()`

### ❌ Removed

- Removed `setUnityThumbnailUri()`

# 10/5/2023 - 1.0.6

## social-sharing

### 🐞 Fixed

- Fixed Sharesheet title
- Fixed `SocialSharingUnity.Builder`'s `build()` method to return an instance of `SocialSharingUnity` instead of `SocialSharing`
- Removed `@Nullable` from `setUseSharesheet()` method of `SocialSharing.Builder` class for compliance with Unity

### ⬆️ Improved

- Changed `SocialSharing.Builder` so that it is no longer necessary to call `useSingle()` or `useMultiple()` methods, it uses `useSingle()` by default and when you need multiple URIs, call `useMultiple()`
- Updated `minSdk` to `22` for compliance with Unity
- Updated `SocialSharing.Builder.setUseSharesheet()` to use primitive boolean type instead of the wrapper
- Updated `SocialSharing.Builder.setUseRichPreview()` to use primitive boolean type instead of the wrapper

### ✅ Added

- Added thumbnail URI support for rich content previews
- Added an internal method for `SocialSharing.Builder` called `buildIntent()` to be used by implementors

## social-sharing-unity

### ✅ Added

- Added `setUnityThumbnailUri()` method