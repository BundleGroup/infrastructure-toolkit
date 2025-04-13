rootProject.name = "infra-toolkit"

pluginManagement {
    plugins {
        kotlin("jvm") version "2.0.21"
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

include("messagebus-api")
include("messagebus-rabbitmq")
include("messagebus-common")
include("messagebus-paper")
include("messagebus-velocity")
include("orchestrator-api")
include("orchestrator-velocity")
include("orchestrator-common")
