plugins {
  application
}

repositories {
  mavenCentral()
}

dependencies {
  implementation(libs.guava)
  implementation("org.postgresql:postgresql:42.7.2")
  implementation("com.zaxxer:HikariCP:5.1.0")
}

testing {
  suites {
    val test by getting(JvmTestSuite::class) {
      useJUnitJupiter("5.10.0")
    }
  }
}

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(21)
  }
}

application {
  mainClass = "com.j0suetm.ums.Ums"
}
