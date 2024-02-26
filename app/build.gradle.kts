plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("kotlin-parcelize")
    alias(libs.plugins.google.dagger.hilt)
}

android {
    namespace = "com.master.machines.allMovies"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.master.machines.allMovies"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("String", "APP_NAME", "\"AllMovies\"")
        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/\"")
        buildConfigField("String", "ALL_MOVIES", "\"3/movie/upcoming\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)//1
    implementation(libs.androidx.lifecycle.runtime.ktx)//2
    implementation(libs.androidx.activity.compose)//3
    implementation(platform(libs.androidx.compose.bom))//4
    implementation(libs.androidx.ui)//5
    implementation(libs.androidx.ui.graphics)//6
    implementation(libs.androidx.ui.tooling.preview)//7
    implementation(libs.androidx.material3)//8
    //android test
    androidTestImplementation(libs.androidx.junit)//10
    androidTestImplementation(libs.androidx.espresso.core)//11
    androidTestImplementation(platform(libs.androidx.compose.bom))//12
    androidTestImplementation(libs.androidx.ui.test.junit4)//13
    //test
    implementation(libs.kotlin.test.junit)
    testImplementation(libs.junit)//9
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.inline)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.androidx.core.testing)
    //debug
    debugImplementation(libs.androidx.ui.tooling)//14
    debugImplementation(libs.androidx.ui.test.manifest)//15
    //navigation
    implementation(libs.navigation.compose)
    //hilt injeccion
    implementation(libs.hilt.android)
    implementation(libs.hilt.compose)
    kapt(libs.hilt.compiler)
    //iconos
    implementation(libs.material.icons.extended)
    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    implementation(libs.adapter.rxjava2)
    //coil
    implementation(libs.coil.compose)
    implementation(libs.coil.svg)
    //drawable
    implementation(libs.accompanist.drawablepainter)
    // Room components
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.room.compiler)
    androidTestImplementation(libs.androidx.room.testing)
    //paging
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.room.paging)
    implementation(libs.androidx.paging.compose)
}