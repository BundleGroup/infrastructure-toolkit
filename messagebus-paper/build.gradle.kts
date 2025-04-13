plugins {
    `java-library`
    id("de.eldoria.plugin-yml.paper") version "0.7.1"
}

group = "gg.bundlegroup"
version = property("version")!!

repositories {
    mavenCentral()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    implementation(project(":messagebus-common"))
    compileOnly("io.papermc.paper:paper-api:1.21.5-R0.1-SNAPSHOT")
}

paper {
    name = "MessageBus"
    main = "gg.bundlegroup.messagebus.paper.Main"
    apiVersion = "1.19"
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}