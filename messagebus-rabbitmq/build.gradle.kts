plugins {
    `java-library`
    `maven-publish`
    id("com.github.harbby.gradle.serviceloader") version "1.1.9"

}

serviceLoader {
    serviceInterface("gg.bundlegroup.messagebus.common.MessageBusProvider")
}

group = "gg.bundlegroup"
version = property("version")!!

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":messagebus-common"))
    implementation("com.rabbitmq:amqp-client:5.25.0")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}