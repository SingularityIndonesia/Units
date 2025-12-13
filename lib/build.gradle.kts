import com.android.build.api.dsl.androidLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    //alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.vanniktech.mavenPublish)
    alias(libs.plugins.kotlinx.serialization)
}

group = "com.singularityuniverse.lib"
version = "1.0.0"

kotlin {
    jvm()
    //androidLibrary {
    //    namespace = "com.singularityuniverse.lib.units"
    //    compileSdk = libs.versions.android.compileSdk.get().toInt()
    //    minSdk = libs.versions.android.minSdk.get().toInt()

    //    withJava() // enable java compilation support
    //    withHostTestBuilder {}.configure {}
    //    withDeviceTestBuilder {
    //        sourceSetTreeName = "test"
    //    }

    //    compilations.configureEach {
    //        compilerOptions.configure {
    //            jvmTarget.set(
    //                JvmTarget.JVM_11
    //            )
    //        }
    //    }
    //}
    // iosX64()
    // iosArm64()
    // iosSimulatorArm64()
    // linuxX64()

    sourceSets {
        commonMain {
            kotlin.srcDirs("compose")
            kotlin.srcDirs("retrofit")
            kotlin.srcDirs("ktor")
            kotlin.srcDirs("locale")
            kotlin.srcDirs("entity")
        }

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.ktor.client)
            implementation(libs.kotlinx.serialization)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)
        }
    }
}

mavenPublishing {
    publishToMavenCentral()

    signAllPublications()

    coordinates(group.toString(), "library", version.toString())

    pom {
        name = "Units"
        description = ""
        inceptionYear = "2025"
        url = "https://github.com/kotlin/multiplatform-library-template/"
        licenses {
            license {
                name = "XXX"
                url = "YYY"
                distribution = "ZZZ"
            }
        }
        developers {
            developer {
                id = "XXX"
                name = "YYY"
                url = "ZZZ"
            }
        }
        scm {
            url = "XXX"
            connection = "YYY"
            developerConnection = "ZZZ"
        }
    }
}
