buildscript {
    repositories {
        jcenter()
        mavenCentral()
        google()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.0.2")
        classpath(kotlin("gradle-plugin", "1.4.10"))
    }
}

allprojects {
    repositories {
        jcenter()
        maven("https://maven.google.com")
        google()
    }
}

tasks.register<Delete>("clean").configure {
    delete(rootProject.buildDir)
 }
