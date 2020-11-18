plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "com.jeluchu.githubapi"
        minSdkVersion(19)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        named("release"){
            isMinifyEnabled = false
            setProguardFiles(listOf(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro"))
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

androidExtensions {
    isExperimental = true
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.2.1")

    implementation("com.squareup.okhttp3:logging-interceptor:4.2.2")
    implementation("com.squareup.retrofit2:adapter-rxjava:2.0.1")
    implementation("com.squareup.retrofit2:retrofit:2.6.2")
    implementation("com.squareup.retrofit2:converter-moshi:2.1.0")

    implementation("io.reactivex:rxandroid:1.2.1")
    implementation(kotlin("stdlib", "1.4.10"))

    testImplementation("junit:junit:4.12")
}
