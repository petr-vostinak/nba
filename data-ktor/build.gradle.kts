import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.dependency.analysis)
}

android {
    namespace = "cz.vostinak.ktor"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures { buildConfig = true }

    //load the values from .properties file
    val keystoreFile = project.rootProject.file("local.properties")
    val properties = Properties()
    properties.load(keystoreFile.inputStream())
    //return empty key in case something goes wrong
    val apiKey = properties.getProperty("RAPID_API_KEY") ?: ""

    buildTypes {
        debug {
            isMinifyEnabled = false
            buildConfigField(
                type = "String",
                name = "RAPID_API_KEY",
                value = "\"$apiKey\""
            )
        }

        release {
            isMinifyEnabled = false
            buildConfigField(
                type = "String",
                name = "RAPID_API_KEY",
                value = "\"$apiKey\""
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
}

dependencies {
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Hilt
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    //ktor client
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.logging)
}