plugins {
	java
	id("org.springframework.boot") version "3.4.4"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.fabiomassaretto"
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
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.modelmapper:modelmapper:3.2.2")
	implementation("org.mapstruct:mapstruct:1.6.3")
	implementation("io.swagger.core.v3:swagger-jaxrs2:2.2.30")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
	runtimeOnly("org.postgresql:postgresql")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
