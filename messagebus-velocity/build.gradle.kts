plugins {
    `java-library`
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

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

dependencies {
    compileOnly(project(":messagebus-common"))
    compileOnly("com.velocitypowered:velocity-api:3.4.0-SNAPSHOT")
    annotationProcessor("com.velocitypowered:velocity-api:3.4.0-SNAPSHOT")
}

val generateBuildConstants by tasks.registering {
    val directory = layout.buildDirectory.dir("generated/build-constants")
    inputs.property("version", version.toString())
    outputs.dir(directory)

    doLast {
        val pkg = directory.get().dir("gg/bundlegroup/messagebus/velocity")
        pkg.asFile.mkdirs()
        pkg.file("BuildConstants.java").asFile.writeText(
            """
            package gg.bundlegroup.messagebus.velocity;
            
            public final class BuildConstants {
                public static final String VERSION = "$version";

                private BuildConstants() {
                }
            }
        """.trimIndent()
        )
    }
}

sourceSets {
    main {
        java {
            srcDir(generateBuildConstants)
        }
    }
}
