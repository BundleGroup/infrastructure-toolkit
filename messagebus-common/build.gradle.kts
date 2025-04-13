import com.github.jengelman.gradle.plugins.shadow.transformers.ServiceFileTransformer

plugins {
    `java-library`
    `maven-publish`
    id("com.gradleup.shadow") version "9.0.0-beta12"
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
    api("org.spongepowered:configurate-yaml:4.1.2")
    api(project(":messagebus-api"))
    runtimeOnly(project(":messagebus-rabbitmq"))
    runtimeOnly(project(":messagebus-paper"))
    runtimeOnly(project(":messagebus-velocity"))
}

tasks {
    assemble {
        dependsOn(shadowJar)
    }

    shadowJar {
        val prefix = "gg.bundlegroup.messagebus.lib"
        fun relocate(pkg: String) {
            relocate(pkg, "$prefix.$pkg")
        }
        relocate("net.i2p.crypto")
        relocate("io.leangen.geantyref")
        relocate("io.nats.client")
        relocate("org.spongepowered.configurate")
        relocate("org.yaml.snakeyaml")
        mergeServiceFiles()
        archiveBaseName.set("MessageBus")
        archiveClassifier.set("")
    }

    withType<AbstractArchiveTask> {
        isReproducibleFileOrder = true
        isPreserveFileTimestamps = false
    }
}
