plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.safeargs)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.pmgmt.mainapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.pmgmt.mainapp"
        minSdk = 24
        targetSdk = 34
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

    val composeBom = platform(libs.compose.bom)
    implementation(composeBom)
    implementation(libs.compose.foundation)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
    implementation(libs.gson)
    implementation(libs.compose.navigation)
    implementation(libs.coil.compose)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.glide)
    ksp(libs.glide.compiler.ksp)

    // Lifecycle dependencies
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.livedata)

    testImplementation(libs.junit)
    testImplementation(composeBom)
    testImplementation(libs.compose.ui.test.junit4)

    androidTestImplementation(composeBom)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.compose.ui.test)
}