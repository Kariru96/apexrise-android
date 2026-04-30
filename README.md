# ApexRise Offline (Android)

Offline-first cow management app built with **Kotlin + Jetpack Compose + Room (SQLite)**.

## Open & run

1) Open `apexrise-android/` in Android Studio.
2) Let Android Studio sync Gradle.
3) Run the `app` configuration on an Android device/emulator.

## Data

All data is stored locally on the phone (Room/SQLite). No internet is required.

## GitHub APK delivery

This repository includes GitHub Actions workflows that build APKs automatically.

- Push to `main` or `develop` to produce APK artifacts from the `Android CI` workflow.
- Tag a commit with a version like `v1.0.0` to create a GitHub release and attach `app-release.apk`.

After the workflow runs, download the APK from the GitHub Actions artifacts or from the release page.

### Release signing

The release workflow will produce an installable APK even without a custom signing key by falling back to the debug signing key on GitHub Actions.

If you want a proper release key, set these repository secrets:

- `RELEASE_STORE_BASE64` — base64 encoded contents of your keystore file
- `RELEASE_STORE_PASSWORD`
- `RELEASE_KEY_ALIAS`
- `RELEASE_KEY_PASSWORD`

The release workflow will write the keystore as `release.keystore` during the build and use it for signing. If these secrets are not set, the workflow still builds a signed APK using the debug key fallback.

