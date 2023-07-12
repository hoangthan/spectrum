pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Spectrum"
include(":app")
include(":features:core")
include(":libraries:core")
include(":libraries:network")
include(":libraries:movie")
include(":features:movie")
