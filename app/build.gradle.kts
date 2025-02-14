import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.firebase.crashlytics")
    id("com.google.gms.google-services")
}

android {
    namespace = "cz.vostinak.nba"
    compileSdk = 35

    defaultConfig {
        applicationId = "cz.vostinak.nba"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures { buildConfig = true }

    //load the values from .properties file
    val keystoreFile = project.rootProject.file("local.properties")
    val properties = Properties()
    properties.load(keystoreFile.inputStream())
    //return empty key in case something goes wrong
    val apiKey = properties.getProperty("API_KEY") ?: ""

    buildTypes {
        debug {
            isMinifyEnabled = false
            buildConfigField(
                type = "String",
                name = "API_KEY",
                value = "\"$apiKey\""
            )
            buildConfigField(
                type = "String",
                name = "BUILD_TYPE",
                value = "\"Debug\""
            )
        }

        release {
            isMinifyEnabled = true
            buildConfigField(
                type = "String",
                name = "API_KEY",
                value = "\"$apiKey\""
            )
            buildConfigField(
                type = "String",
                name = "BUILD_TYPE",
                value = "\"Release\""
            )
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":core-ui"))

    // Core Android dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Hilt
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // OkHttp
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)

    // navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)

    // Glide
    implementation(libs.glide)

    // Import the Firebase BoM
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
}