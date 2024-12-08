plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.kotlin.serialization)
    id("kotlin-parcelize")

}

android {
    namespace = "com.newyorktimes"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.newyorktimes"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "API_KEY", "\"${project.findProperty("API_KEY").toString()}\"")
        buildConfigField("String", "BASE_URL", "\"${project.findProperty("BASE_URL").toString()}\"")

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
        compose = true
        buildConfig = true
    }
}

dependencies {

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
    //Dagger Hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.android.compose)
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    //Room
    implementation(libs.androidx.room.runtime)
    kapt("androidx.room:room-compiler:2.6.1")
    implementation(libs.androidx.room.ktx)
    //Coil
    implementation(libs.coil.compose)
    //Navigation
    implementation(libs.androidx.navigation.compose)
    //Timber
    implementation(libs.timber)
    //Serialization
    implementation(libs.kotlinx.serialization.json)
    //Icons
    implementation(libs.androidx.material.icons.extended)
}


// Allow references to generated code
kapt {
    correctErrorTypes = true
}