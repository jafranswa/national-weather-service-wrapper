plugins {
	java
	id("org.springframework.boot") version "3.2.5"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.jacobFrancois"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.json:json:20210307")
	implementation ("javax.json:javax.json-api:1.1.4")
	testImplementation("org.springframework.boot:spring-boot-starter-test"){
		exclude(module = "junit")
	}
	testImplementation("io.projectreactor:reactor-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	testImplementation("org.testng:testng:7.4.0")
	testImplementation("org.mockito:mockito-core:3.12.4")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
