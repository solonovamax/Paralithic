import ca.solostudios.nyx.util.codeMC
import java.io.ByteArrayOutputStream

plugins {
    `java-library`
    `maven-publish`

    alias(libs.plugins.nyx)
    alias(libs.plugins.jmh)
}

val versionObj = Version("0", "8", "0", branch = "fma-merged-dev", preRelease = false)

nyx {
    info {
        name = "Paralithic"
        group = "com.dfsek"
        module = "paralithic"
        version = "$versionObj"
        description = """
            Paralithic is a super fast library for parsing and evaluating mathematical expressions.
        """.trimIndent()

        organizationName = "Polyhedral Development"
        organizationUrl = "https://github.com/PolyhedralDev/"

        repository.fromGithub("PolyhedralDev", "Paralithic")
        license.useMIT()
    }

    compile {
        javadocJar = true
        sourcesJar = true

        allWarnings = true
        warningsAsErrors = true
        distributeLicense = true
        buildDependsOnJar = true

        jvmTarget = 17

        java {
            allJavadocWarnings = true
            noMissingJavadocWarnings = true
            javadocWarningsAsErrors = true
        }
    }

    publishing {
        withPublish()

        repositories {
            maven("https://repo.codemc.io/repository/maven-releases/") {
                name = "CodeMC"
                credentials(PasswordCredentials::class)
            }

            maven("https://maven.solo-studios.ca/releases/") {
                name = "SoloStudios"
                credentials(PasswordCredentials::class)
                authentication { // publishing doesn't work without this for some reason
                    create<BasicAuthentication>("basic")
                }
            }
        }
    }
}

repositories {
    mavenCentral()
    codeMC()
}

dependencies {
    implementation(libs.jetbrains.annotations)

    api(libs.asm)

    testImplementation(libs.bundles.junit)
    jmh(libs.parsii)
    jmh(libs.exp4j)
}

tasks {
    test {
        useJUnitPlatform()
    }
}

/**
 * Version class that does version stuff.
 */
@Suppress("MemberVisibilityCanBePrivate")
class Version(val major: String, val minor: String, val revision: String, val branch: String? = null, val preRelease: Boolean = false) {

    override fun toString(): String {
        return buildString {
            append(major)
            append(".")
            append(minor)
            append(".")
            append(revision)

            if (branch != null)
                append("-").append(branch)

            if (preRelease)
                append("+").append(getGitHash())
        }
    }
}

fun getGitHash(): String {
    val stdout = ByteArrayOutputStream()
    exec {
        commandLine = mutableListOf("git", "rev-parse", "--short", "HEAD")
        standardOutput = stdout
    }
    return stdout.toString().trim()
}
