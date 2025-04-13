plugins {
    `java-library`
    `maven-publish`
}

group = "gg.bundlegroup"
version = property("version")!!

repositories {
    mavenCentral()
}

java {
    withSourcesJar()
    withJavadocJar()
}

dependencies {
    compileOnlyApi("org.jetbrains:annotations:23.1.0")
    compileOnlyApi("org.slf4j:slf4j-api:2.0.5")
    api(project(":messagebus-api"))
    api("org.spongepowered:configurate-yaml:4.1.2")
}