plugins {
    id("org.springframework.boot") version "2.3.1.RELEASE"
    java
}

apply { plugin("io.spring.dependency-management") }

group = "pl.korman"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks {
    named<Test>("test") {
        useJUnitPlatform()
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

