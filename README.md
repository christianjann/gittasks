<div align="center">

<h1>
<picture>
  <source media="(prefers-color-scheme: dark)" srcset="assets/logo_wide_dark.svg">
  <source media="(prefers-color-scheme: light)" srcset="assets/logo_wide_light.svg">
  <img alt="GitTasks" width="50%" src="assets/logo_wide_light.svg">
</picture>
</h1>

</div>

<!-- [![GitHub release (latest SemVer)](https://img.shields.io/github/v/release/christianjann/gittasks.svg?logo=github&label=GitHub&cacheSeconds=3600)](https://github.com/christianjann/gittasks/releases/latest)
[![F-Droid](https://img.shields.io/f-droid/v/io.github.christianjann.gittasks?logo=f-droid&label=F-Droid&cacheSeconds=3600)](https://f-droid.org/packages/io.github.christianjann.gittasks)
[![IzzyOnDroid](https://img.shields.io/endpoint?url=https://apt.izzysoft.de/fdroid/api/v1/shield/io.github.christianjann.gittasks)](https://apt.izzysoft.de/fdroid/index/apk/io.github.christianjann.gittasks) -->

GitTasks is an Android note-taking and task management app with Git integration optimized for efficient markdown-based note management with advanced features like tag filtering, Git log viewing, and seamless synchronization.

## Download

[<img height="80" src="https://media.githubusercontent.com/media/christianjann/gittasks/refs/heads/master/doc/badges/badge_github.png"/>](https://github.com/christianjann/gittasks/releases/latest)
[<img height="80" src="https://media.githubusercontent.com/media/christianjann/gittasks/refs/heads/master/doc/badges/obtainium.png"/>](https://apps.obtainium.imranr.dev/redirect?r=obtainium://app/%7B%22id%22%3A%22io.github.christianjann.gittasks%22%2C%22url%22%3A%22https%3A%2F%2Fgithub.com%2Fchristianjann%2Fgittasks%22%2C%22author%22%3A%22christianjann%22%2C%22name%22%3A%22GitTasks%22%2C%22preferredApkIndex%22%3A0%2C%22additionalSettings%22%3A%22%7B%5C%22includePrereleases%5C%22%3Afalse%2C%5C%22fallbackToOlderReleases%5C%22%3Atrue%2C%5C%22filterReleaseTitlesByRegEx%5C%22%3A%5C%22%5C%22%2C%5C%22filterReleaseNotesByRegEx%5C%22%3A%5C%22%5C%22%2C%5C%22verifyLatestTag%5C%22%3Afalse%2C%5C%22sortMethodChoice%5C%22%3A%5C%22date%5C%22%2C%5C%22useLatestAssetDateAsReleaseDate%5C%22%3Afalse%2C%5C%22releaseTitleAsVersion%5C%22%3Afalse%2C%5C%22trackOnly%5C%22%3Afalse%2C%5C%22versionExtractionRegEx%5C%22%3A%5C%22%5C%22%2C%5C%22matchGroupToUse%5C%22%3A%5C%22%5C%22%2C%5C%22versionDetection%5C%22%3Atrue%2C%5C%22releaseDateAsVersion%5C%22%3Afalse%2C%5C%22useVersionCodeAsOSVersion%5C%22%3Afalse%2C%5C%22apkFilterRegEx%5C%22%3A%5C%22%5C%22%2C%5C%22invertAPKFilter%5C%22%3Afalse%2C%5C%22autoApkFilterByArch%5C%22%3Atrue%2C%5C%22appName%5C%22%3A%5C%22GitTasks%5C%22%2C%5C%22appAuthor%5C%22%3A%5C%22Christian%20Jann%5C%22%2C%5C%22shizukuPretendToBeGooglePlay%5C%22%3Afalse%2C%5C%22allowInsecure%5C%22%3Afalse%2C%5C%22exemptFromBackgroundUpdates%5C%22%3Afalse%2C%5C%22skipUpdateNotifications%5C%22%3Afalse%2C%5C%22about%5C%22%3A%5C%22Android%20note-taking%20and%20task%20management%20app%20with%20Git%20integration.%5C%22%2C%5C%22refreshBeforeDownload%5C%22%3Afalse%2C%5C%22includeZips%5C%22%3Afalse%2C%5C%22zippedApkFilterRegEx%5C%22%3A%5C%22%5C%22%2C%5C%22github-creds%5C%22%3A%5C%22%5C%22%2C%5C%22GHReqPrefix%5C%22%3A%5C%22%5C%22%7D%22%2C%22overrideSource%22%3A%22GitHub%22%7D)

### Verification

The APK files can be verified using [apksigner](https://developer.android.com/tools/apksigner#options-verify).

```
apksigner verify --print-certs -v gittasks-release-<VERSION>.apk 
```

The output should look like this:

```
Verifies
Verified using v1 scheme (JAR signing): false
Verified using v2 scheme (APK Signature Scheme v2): true
Verified using v3 scheme (APK Signature Scheme v3): false
Verified using v3.1 scheme (APK Signature Scheme v3.1): false
Verified using v4 scheme (APK Signature Scheme v4): false
Verified for SourceStamp: false
```

The certificate content and digests should look like this:

```
Signer #1 certificate DN: CN=GitNote, OU=Development, O=GitNote, L=Unknown, ST=Unknown, C=US
Signer #1 certificate SHA-256 digest: f0374ed8e7494da4a3ab7b726cc38cddc151dd28186accb17052096b0c9c69c3
Signer #1 certificate SHA-1 digest: 69130faa0af21cbd8c97f9953d3fbf24a781bd23
Signer #1 certificate MD5 digest: da57a50a1e6413077b0e6874d77b33da
```

## User Quick Start

1. **Set up a Git repository**: Create a private Git repository on GitHub, GitLab, or your own server to store your notes
2. **Install GitTasks**: Download and install the app from the [GitHub releases page](https://github.com/christianjann/gittasks/releases) or build it yourself following the developer instructions below
3. **Explore examples**: Check out the [examples](./doc/examples/) and copy them to your repository for inspiration
4. **Start taking notes**: Enjoy seamless markdown note-taking with Git synchronization!

## Developer Quick Start

1. **Install dependencies**: Rust, Android Studio, Android SDK, JDK
2. **Clone repository**: `git clone https://github.com/christianjann/gittasks.git`
3. **First Android Studio build**: Open the project in Android Studio and build it once to generate `.gradle/config.properties` with the correct JBR path
4. **Build Rust library**: `just rust-build`
5. **Build**: `just install` (builds and installs debug APK)
6. **Run**: Open the app and create/clone your first Git repository

For development outside Android Studio, a [justfile](justfile) provides convenient commands. See [just](https://github.com/casey/just) for usage details.

# Why

Because all apps which integrate with Git on Android either crash, are slow, are based on technologies I don't like (and are slow as hell) or don't have the features I need.

# Features

- [x] create/open/clone repositories
- [x] private repo support (SSH and HTTPS)
- [x] note and task items
- [x] due dates with dedicated view mode
- [x] search (global and from specific folder)
- [x] grid view with customizable widths
- [x] list view
- [x] markdown edit view and full markdown rendering
- [x] clickable check boxes in views and markdown rendering
- [x] remote sync
- [x] time based sort
- [x] multi-language support
- [x] flexible tag display in grid and list view
- [x] tag-based filtering and organization
- [x] frontmatter metadata support
- [x] git log viewer
- [x] automatic merge conflict resolution
- [x] asset management for images and files

<table align="center">
  <tr>
    <td align="center">
      <img src="assets/grid.png" width="180" alt="Grid view with flexible tag display"/>
    </td>
    <td align="center">
      <img src="assets/list.png" width="200" alt="List view"/>
    </td>
    <td align="center">
      <img src="assets/tags.png" width="180" alt="Tag filtering interface"/>
    </td>
    <td align="center">
      <img src="assets/drawer.png" width="180" alt="Drawer navigation"/>
    </td>
  </tr>
  <tr>
    <td align="center">
      <strong>Grid View</strong><br>
      Browse notes in a customizable grid layout with flexible tag display
    </td>
    <td align="center">
      <strong>List View</strong><br>
      Navigate through notes in a traditional list format
    </td>
    <td align="center">
      <strong>Tag Filtering</strong><br>
      Filter and organize notes using tags for better organization
    </td>
    <td align="center">
      <strong>Navigation Drawer</strong><br>
      Access repositories, settings, and navigation options
    </td>
  </tr>
  <tr>
    <td align="center">
      <img src="assets/edit.png" width="220" alt="Edit screen"/>
    </td>
    <td align="center">
      <img src="assets/rendered.png" width="220" alt="Markdown rendering"/>
    </td>
    <td align="center">
      <img src="assets/git_log.png" width="220" alt="Git log viewer"/>
    </td>
    <td align="center">
      <img src="assets/tag_editor.png" width="220" alt="Tag editor"/>
    </td>
  </tr>
  <tr>
    <td align="center">
      <strong>Edit Screen</strong><br>
      Full-featured markdown editor with syntax highlighting
    </td>
    <td align="center">
      <strong>Markdown Rendering</strong><br>
      Preview rendered markdown with full formatting support
    </td>
    <td align="center">
      <strong>Git Log Viewer</strong><br>
      View commit history and track changes to your notes
    </td>
    <td align="center">
      <strong>Tag Editor</strong><br>
      Edit and organize note tags with an intuitive interface
    </td>
  </tr>
  <tr>
    <td align="center">
      <img src="assets/asset_manager.png" width="220" alt="Asset manager"/>
    </td>
    <td align="center">
      <img src="assets/due.png" width="220" alt="Due dates view"/>
    </td>
    <td align="center">
    </td>
    <td align="center">
    </td>
  </tr>
  <tr>
    <td align="center">
      <strong>Asset Manager</strong><br>
      Import, export, and manage images and files attached to your notes
    </td>
    <td align="center">
      <strong>Due Dates</strong><br>
      Track deadlines with a dedicated view showing all notes with due dates
    </td>
    <td align="center">
    </td>
    <td align="center">
    </td>
  </tr>
</table>

_Supported Android versions: 11+_

_Supported Architecture: `arm64-v8a`, `x86_64`_

# Documentation

- [Detailed features documentation](./doc/features.md)
- [Building the app](./doc/building.md)
- [GitHub authentication](./doc/github_authentication.md)
- [Markdown header format](./doc/design/markdown_header.md)
- [App architecture](./doc/design/app_architecture.md)
- [Contributing](./CONTRIBUTING.md)
- [Changelog](./CHANGELOG.md)

# Build System

This project uses a custom build system based on [just](https://github.com/casey/just) for task automation. Key commands:

- `just build` - Build debug APK
- `just install` - Build and install debug APK
- `just rust-build` - Build Rust native library
- `just fmt` - Format all code (Kotlin + Rust)
- `just test` - Run Rust unit tests
- `just test-integration` - Run Git integration tests (requires git, located in `app/src/main/rust/tests/`)
- `just clean-build` - Clean and rebuild

See [building.md](doc/building.md) for detailed build instructions.

# Current limitation

- Android does not differentiate case for file name, so if you have a folder named `A` and another folder named `a`, `a` will not be displayed.
- Device memory storage is significantly slower than app memory (up to 200x). See [Storage Options and Performance](./doc/features.md#storage-options-and-performance) for details. Use app memory where possible.

# Future direction

- Create a `_conflict.md` file in conflict resolution, so the user can easily choose which one to kep or edit
- Move more of the core logic and data handling into the Rust part?
- Maybe get rid of the database caching entirely and use a more thin layer on top of the Git abstraction?

# Attribution

This project is a fork of the original [GitNote](https://github.com/wiiznokes/gitnote) Android app by [wiiznokes](https://github.com/wiiznokes). It's an opinionated edition optimized to better suit personal note-taking requirements and workflow preferences, with improvements to performance, UI responsiveness, and feature enhancements.
