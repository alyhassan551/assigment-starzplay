plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.navigation.safeargs)
    id("kotlin-kapt")
}

android {
    namespace = "com.ali.starzplay"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ali.starzplay"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunner = "com.ali.starzplay.MyTestRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(":core"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)

    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp.logging)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    implementation (libs.androidx.navigation.fragment.ktx)
    implementation (libs.androidx.navigation.ui.ktx)

    implementation (libs.glide)
    implementation(libs.exoplayer)

    testImplementation(libs.junit)
    testImplementation (libs.mockito.core)
    testImplementation (libs.mockito.inline)
    testImplementation (libs.androidx.core.testing)
    testImplementation (libs.kotlinx.coroutines.test)

    testImplementation (libs.kotlinx.coroutines.test)


    androidTestImplementation (libs.androidx.espresso.contrib)
    androidTestImplementation (libs.dagger.hilt.android.testing)
    androidTestImplementation (libs.androidx.rules)
    kaptAndroidTest (libs.hilt.android.compiler)
}

kapt {
    correctErrorTypes = true
}