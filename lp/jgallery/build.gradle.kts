plugins {
    application

    id("org.flywaydb.flyway") version "10.8.1"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.junit.jupiter)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation(libs.guava)
    implementation("org.postgresql:postgresql:42.7.2")
    implementation("com.zaxxer:HikariCP:5.1.0")
}

buildscript {
  repositories {
    mavenCentral()
  }

  dependencies {
    classpath("org.flywaydb:flyway-database-postgresql:10.1.0")
  }
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

flyway {
  driver = "org.postgresql.Driver"
  url = "jdbc:postgresql://localhost:8000/jgallery-db"
  user = "j0suetm"
  password = "password"
}