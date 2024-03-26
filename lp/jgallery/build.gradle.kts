plugins {
    application
    id("org.openjfx.javafxplugin") version "0.1.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.guava)
    implementation("org.postgresql:postgresql:42.7.2")
    implementation("com.zaxxer:HikariCP:5.1.0")
    implementation("org.flywaydb:flyway-core:10.10.0")
    implementation("org.flywaydb:flyway-database-postgresql:10.1.0")
    implementation("software.amazon.awssdk:s3:2.25.16")

    testImplementation("org.testcontainers:testcontainers:1.19.7")
    testImplementation("org.testcontainers:postgresql:1.19.7")
    testImplementation("org.testcontainers:localstack:1.19.7")
    testImplementation(libs.junit.jupiter)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

application {
    mainClass.set("com.j0suetm.jgallery.Main")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

javafx {
    modules(
      "javafx.base",
      "javafx.graphics",
      "javafx.controls",
      "javafx.fxml",
      "javafx.web"
    )
}