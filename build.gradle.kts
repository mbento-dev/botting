import com.runemate.game.api.bot.data.Category

plugins {
    // Apply the Java plugin to your Gradle project
    java

    // Apply the RuneMate plugin to your Gradle project
    // The latest version of the Gradle plugin can be found here: https://plugins.gradle.org/plugin/com.runemate
    id("com.runemate") version "1.3.0"
}

//Used to configure various aspects of the RuneMate plugin
runemate {
    devMode.set(true)

    manifests {
        create("Fenris Bone Runner") {
        //This is the fully qualified name of your main class
        mainClass = "com.runemate.fenrisfeng.bonerunner.BoneRunner"

        //A short description that is shown under the bot name on the bot store
        tagline = "a test bot :)"

        //Shown in the bot description in the client
        description = "Tests stuff?"

        //The unique internal ID of the bot
        internalId = "test-bot"

        //The version of the bot
        version = "0.0.1"

        //The store supports multiple categories, the first will be the "main" category
        categories(Category.OTHER)

        //This is where you declare the price(s) of the bot
        variants {
            variant(name = "Variant name", price = 0.00)
        }

        //For premium bots, you can declare a "trial" which is a period for which a user can use the bot for free
//            trial {
//                window = Duration.ofDays(7)
//                allowance = Duration.ofHours(3)
//            }
    }
    }

}

//Sets the project language level to 17
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}