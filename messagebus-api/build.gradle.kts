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
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }

    repositories {
        mavenLocal()
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/BundleGroup/infrastructure-toolkit")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
            }
        }
    }
}