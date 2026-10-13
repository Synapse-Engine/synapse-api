plugins {
    `java-library`
    `maven-publish`
    id("com.diffplug.spotless") version "8.10.4"
}

group = "io.synapseproject"
version = "0.1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
    withSourcesJar()
    withJavadocJar()
}

dependencies {
    compileOnlyApi("org.jspecify:jspecify:1.0.0")

    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

spotless {
    java {
        googleJavaFormat("1.36.1")
        formatAnnotations()
        removeUnusedImports()
    }
}

tasks.check {
    dependsOn(tasks.spotlessCheck)
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/Synapse-Engine/synapse-api")
            credentials {
                username =
                    providers.gradleProperty("gpr.user")
                        .orElse(providers.environmentVariable("GITHUB_ACTOR"))
                        .orNull
                password =
                    providers.gradleProperty("gpr.key")
                        .orElse(providers.environmentVariable("GITHUB_TOKEN"))
                        .orNull
            }
        }
    }
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            pom {
                name = "Synapse API"
                description = "Public Java contract for the Synapse engine"
                url = "https://github.com/Synapse-Engine/synapse-api"
                licenses {
                    license {
                        name = "MIT License"
                        url = "https://opensource.org/licenses/MIT"
                    }
                }
                scm {
                    connection = "scm:git:https://github.com/Synapse-Engine/synapse-api.git"
                    developerConnection = "scm:git:ssh://git@github.com/Synapse-Engine/synapse-api.git"
                    url = "https://github.com/Synapse-Engine/synapse-api"
                }
            }
        }
    }
}