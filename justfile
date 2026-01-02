set windows-powershell := true

# You need to open the project once in Android studio, then it should be there
export JAVA_HOME := `grep '^java.home=' .gradle/config.properties | cut -d'=' -f2`

main:
    just --list

fix:
    ./gradlew lintFix

fmt-just:
    just --fmt --unstable

fmt-prettier:
    # install on Debian: sudo snap install node --classic
    # npx is the command to run npm package, node is the runtime
    npx prettier -w . --ignore-path ./.gitignore --ignore-path ./.prettierignore

fmt-kotlin:
    # Format Kotlin files using Android lint fix
    ./gradlew lintFix

fmt-rust:
    # Format all Rust files using cargo fmt
    cd app/src/main/rust && cargo fmt

# Format all code files
fmt: fmt-kotlin fmt-rust fmt-just fmt-prettier

sort-supported-extension:
    #!/usr/bin/env bash
    extension_dir=app/src/main/rust/supported_extensions
    for f in $(ls $extension_dir 2>/dev/null); do
    	sort $extension_dir/$f -o $extension_dir/$f
        echo sorted $f
    done

rust-build:
    #!/usr/bin/env bash
    cd app/src/main/rust
    make DEBUG=0 build_install

build:
    ./gradlew :app:assembleDebug

install:
    ./gradlew :app:assembleDebug :app:installDebug

clean:
    ./gradlew clean

clean-build:
    ./gradlew clean :app:assembleDebug

fix-wrapper:
    #curl -L -o gradle/wrapper/gradle-wrapper.jar https://github.com/gradle/gradle/raw/v8.13.0/gradle/wrapper/gradle-wrapper.jar
    git lfs pull

generate-release-keys:
    ./generate-release-keys.sh

setup-release-env:
    ./setup-release-env.sh

release-build:
    #!/usr/bin/env bash
    echo "Setting up release environment..."
    source ./setup-release-env.sh
    echo "Building release APK..."
    ./gradlew :app:assembleRelease

release-install:
    #!/usr/bin/env bash
    echo "Setting up release environment..."
    source ./setup-release-env.sh
    echo "Building and installing release APK..."
    ./gradlew assembleRelease
    echo "Checking for connected device..."
    if ! adb devices | grep -q "device$"; then
        echo "❌ No device connected. Connect a device or start an emulator first."
        echo "📱 To install manually: adb install app/build/outputs/apk/release/app-release.apk"
        exit 1
    fi
    echo "Installing release APK to device..."
    adb install -r app/build/outputs/apk/release/app-release.apk
    echo ""
    echo "✅ Release APK built and installed successfully!"
