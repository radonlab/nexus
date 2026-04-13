plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "org.radonlab.nexus"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "org.radonlab.nexus"
        minSdk = 33
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    // Lynx dependencies
    implementation("org.lynxsdk.lynx:lynx:3.6.0")
    implementation("org.lynxsdk.lynx:lynx-jssdk:3.6.0")
    implementation("org.lynxsdk.lynx:lynx-trace:3.6.0")
    implementation("org.lynxsdk.lynx:primjs:3.6.1")
    implementation("org.lynxsdk.lynx:lynx-service-image:3.6.0")
    implementation("com.facebook.fresco:fresco:2.3.0")
    implementation("com.facebook.fresco:animated-gif:2.3.0")
    implementation("com.facebook.fresco:animated-webp:2.3.0")
    implementation("com.facebook.fresco:webpsupport:2.3.0")
    implementation("com.facebook.fresco:animated-base:2.3.0")
    implementation("org.lynxsdk.lynx:lynx-service-log:3.6.0")
    implementation("org.lynxsdk.lynx:lynx-service-http:3.6.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.0")
    implementation("org.lynxsdk.lynx:xelement:3.6.0")
    implementation("org.lynxsdk.lynx:xelement-input:3.6.0")
    // Testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}

// Transitive deps (e.g. Fresco) can resolve vectordrawable 1.0.0, whose manifests
// collide under AGP 8+; force a newer line.
configurations.all {
    resolutionStrategy {
        force(
            "androidx.vectordrawable:vectordrawable:1.2.0",
            "androidx.vectordrawable:vectordrawable-animated:1.2.0",
        )
    }
}
