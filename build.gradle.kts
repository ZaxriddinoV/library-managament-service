plugins {
	java
	id("org.springframework.boot") version "3.4.0"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "uz.asaka"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	compileOnly("org.projectlombok:lombok:1.18.34")
	annotationProcessor("org.projectlombok:lombok:1.18.34")
	// Logging
	implementation("org.slf4j:slf4j-api:2.0.16")
	implementation("org.springframework.boot:spring-boot-starter-logging")
	// Database
	implementation("mysql:mysql-connector-java:8.0.33")
	// Flyway
	implementation("org.flywaydb:flyway-core:11.1.0")
	implementation("org.flywaydb:flyway-mysql:11.1.0")
	// Security and JWT
	implementation("org.springframework.boot:spring-boot-starter-security:3.4.0")
	testImplementation("org.springframework.security:spring-security-test:6.4.2")
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
	implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")
	// Validation
	implementation("org.springframework.boot:spring-boot-starter-validation")
	// Testing
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}


tasks.withType<Test> {
	useJUnitPlatform()
}
