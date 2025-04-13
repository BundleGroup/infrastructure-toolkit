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
    implementation(project(":messagebus-common"))
    implementation("com.rabbitmq:amqp-client:5.25.0")
}