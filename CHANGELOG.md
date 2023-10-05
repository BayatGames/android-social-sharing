# Changelog

This changelog follows [keep a changelog](https://keepachangelog.com/) guidelines and uses [semantic versioning](https://semver.org/).

The date format is `dd/mm/yyyy`.

# 10/5/2023 - 1.0.4

## social-sharing

### 🐞 Fixed

- Fixed Sharesheet title

### ⬆️ Improved

- Changed `SocialSharing.Builder` so that it is no longer necessary to call `useSingle()` or `useMultiple()` methods, it uses `useSingle()` by default and when you need multiple URIs, call `useMultiple()`
- Updated `minSdk` to `22` for compliance with Unity

### ✅ Added

- Added thumbnail URI support for rich content previews

## social-sharing-unity

### ✅ Added

- Added `setUnityThumbnailUri()` method