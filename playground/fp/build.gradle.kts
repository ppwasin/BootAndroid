apply<plugin.Junit5Plugin>()
plugins {
    id("java-library")
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(Libs.kotlinStd)

    implementation(Arrowkt.core)
    implementation(Arrowkt.syntax)
}