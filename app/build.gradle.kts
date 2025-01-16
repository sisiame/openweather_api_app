val weatherApiKey: String? by project
if (weatherApiKey.isNullOrEmpty()) {
    throw GradleException("API key is missing in local.properties")
}

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.sisiame.openweatherapiapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sisiame.openweatherapiapp"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "WEATHER_API_KEY", "\"${weatherApiKey}\"")
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.text)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.play.services.location)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    // Hilt
    implementation(libs.hilt.android.v2511)
    kapt(libs.hilt.android.compiler.v2511)
    // Coroutines
    implementation(libs.kotlinx.coroutines.android)
    // DataStore
    implementation(libs.androidx.datastore.preferences)
    // for images with compose
    implementation(libs.coil.compose)
    // ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    // Mockito
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    // Retrofit Mocking
    testImplementation(libs.retrofit.mock)
    // Coroutine Testing
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.kotlin.test)
    // Compose
    implementation(libs.androidx.ui.v132)
    implementation(libs.material3)
    implementation(libs.androidx.foundation)
    implementation(libs.ui.tooling.preview)
}

kapt {
    correctErrorTypes = true
}