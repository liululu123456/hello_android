import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("kapt")
}

android {
    namespace = "com.thoughtworks.androidtrain"
    compileSdk = 34
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }

    testOptions{
        unitTests.isIncludeAndroidResources = true
    }

    defaultConfig {
        applicationId = "com.thoughtworks.androidtrain"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    signingConfigs {
        val keystorePropertiesFile = rootProject.file("keystore.properties")
        val keystoreProperties = Properties()
        keystoreProperties.load(FileInputStream(keystorePropertiesFile))
        create("debug_sign") {
            keyAlias = keystoreProperties["debugKeyAlias"] as String
            keyPassword = keystoreProperties["debugKeyPassword"] as String
            storeFile = rootProject.file(keystoreProperties["debugStoreFile"] as String)
            storePassword = keystoreProperties["debugStorePassword"] as String
        }

        create("release_sign") {
            keyAlias = keystoreProperties["releaseKeyAlias"] as String
            keyPassword = keystoreProperties["releaseKeyPassword"] as String
            storeFile = rootProject.file(keystoreProperties["releaseStoreFile"] as String)
            storePassword = keystoreProperties["releaseStorePassword"] as String
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs["debug_sign"]
            isMinifyEnabled = false
        }

        getByName("release") {
            signingConfig = signingConfigs["release_sign"]
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "$project.rootDir/tools/proguard-rules.pro"
            )
        }
    }

}

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2024.04.00")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    // Choose one of the following:
    // Material Design 3
    //noinspection UseTomlInstead
    implementation("androidx.compose.material3:material3")
    // or Material Design 2
    //noinspection UseTomlInstead
    implementation("androidx.compose.material:material")
    // or skip Material Design and build directly on top of foundational components
    //noinspection UseTomlInstead
    implementation("androidx.compose.foundation:foundation")
    // or only import the main APIs for the underlying toolkit systems,
    // such as input and measurement/layout
    //noinspection UseTomlInstead
    implementation("androidx.compose.ui:ui")

    // Android Studio Preview support
    //noinspection UseTomlInstead
    implementation("androidx.compose.ui:ui-tooling-preview")
    //noinspection UseTomlInstead
    debugImplementation("androidx.compose.ui:ui-tooling")

    // UI Tests
    //noinspection UseTomlInstead
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    //noinspection UseTomlInstead
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Optional - Included automatically by material, only add when you need
    // the icons but not the material library (e.g. when using Material3 or a
    // custom design system based on Foundation)
    //noinspection UseTomlInstead
    implementation("androidx.compose.material:material-icons-core")
    // Optional - Add full set of material icons
    //noinspection UseTomlInstead
    implementation("androidx.compose.material:material-icons-extended")
    // Optional - Add window size utils
    //noinspection UseTomlInstead
    implementation("androidx.compose.material3:material3-window-size-class")

    // Optional - Integration with activities
    implementation(libs.androidx.activity.compose)
    // Optional - Integration with ViewModels
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    // Optional - Integration with LiveData
    //noinspection UseTomlInstead
    implementation("androidx.compose.runtime:runtime-livedata")
    // Optional - Integration with RxJava
    //noinspection UseTomlInstead
    implementation("androidx.compose.runtime:runtime-rxjava2")
    implementation (libs.coil.compose)

    implementation(libs.converter.gson)
    implementation(libs.retrofit)
    implementation(libs.converter.scalars)
    implementation(libs.rxandroid)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.junit.ktx)
    implementation(libs.core)
    annotationProcessor(libs.androidx.room.compiler)
    kapt("androidx.room:room-compiler:2.6.1")
    implementation(libs.androidx.room.rxjava2)
    implementation(libs.androidx.room.rxjava3)
    implementation(libs.androidx.datastore.preferences)
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.kotlinx.coroutines.android)
    implementation(libs.gson)
    implementation(libs.coil)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    testImplementation(libs.androidx.core)
    testImplementation(libs.mockito.core)
    testImplementation (libs.robolectric)
    testImplementation (libs.androidx.core.testing)
    testImplementation(libs.androidx.espresso.core.v340)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.espresso.core.v340)
    androidTestImplementation(libs.androidx.junit.v113)
}