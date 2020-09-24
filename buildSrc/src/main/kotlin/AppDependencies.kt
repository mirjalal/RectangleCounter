object AppDependencies {
    private const val androidXCoreKtx = "androidx.core:core-ktx:${Versions.versionAndroidXCore}"
    private const val androidXAppCompat = "androidx.appcompat:appcompat:${Versions.versionAppCompat}"

    private const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.versionConstraintLayout}"
    private const val material = "com.google.android.material:material:${Versions.versionMaterial}"

    // Kotlin
    private const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.versionKotlin}"

    // Kotlin coroutines
    private const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.versionCoroutines}"
    private const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.versionCoroutines}"

    // Lifecycle Components
//    private const val lifecycleLiveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.versionArchLifecycle}"
    private const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.versionArchLifecycle}"

    // Koin Dependency Injection for ViewModels
    private const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.versionKoin}"
    private const val koinAndroidScope = "org.koin:koin-androidx-scope:${Versions.versionKoin}"

    //test libs
    private const val junit = "junit:junit:${Versions.versionJUnit}"
    private const val extJUnit = "androidx.test.ext:junit:${Versions.versionAndroidXJUnit}"
    private const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.versionEspresso}"

    val appLibraries = listOf(
        kotlin,
        coroutinesCore,
        coroutinesAndroid,
        androidXCoreKtx,
        androidXAppCompat,
        constraintLayout,
        material,
//        lifecycleLiveDataKtx,
        lifecycleRuntimeKtx,
        koinViewModel,
        koinAndroidScope
    )

    val testLibraries = listOf(junit)

    val androidTestLibraries = listOf(extJUnit, espressoCore)
}
