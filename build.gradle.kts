import com.runemate.game.api.bot.data.Category
import java.time.Duration
import java.net.URL

plugins {
  // Apply the Java plugin to your Gradle project
  java

  // Apply the RuneMate plugin to your Gradle project
  // The latest version of the Gradle plugin can be found here: https://plugins.gradle.org/plugin/com.runemate
  id("com.runemate") version "1.3.0"
}


fun getLatestApiVersion(): String {
  val url = "https://gitlab.com/api/v4/projects/32972353/packages/maven/com/runemate/runemate-game-api/maven-metadata.xml"
  val content = URL(url).readText()
  return content.substringAfter("<latest>").substringBefore("</latest>")
}

fun getLatestClientVersion(): String {
  val url = "https://gitlab.com/api/v4/projects/10471880/packages/maven/com/runemate/runemate-client/maven-metadata.xml"
  val content = URL(url).readText()
  return content.substringAfter("<latest>").substringBefore("</latest>")
}

//Used to configure various aspects of the RuneMate plugin
runemate {
  devMode.set(true)
  apiVersion = getLatestApiVersion()
  clientVersion = getLatestClientVersion()

  manifests {
    create("Fenris Bone Runner") {
      //This is the fully qualified name of your main class
      mainClass = "com.runemate.fenrisfeng.bonerunner.BoneRunner"

      //A short description that is shown under the bot name on the bot store
      tagline = "Support another account by running bones for them"

      //Shown in the bot description in the client
      description = "Start inside the desired POH in rimmington and set the desired bones and player name."

      //The version of the bot
      version = "1.0.0"

      //The unique internal ID of the bot
      internalId = "fenris-bone-runner-public"

      tags("PRAYER", "RUN", "RUNNER", "FENRIS", "POH")

      //The store supports multiple categories, the first will be the "main" category
      categories(Category.PRAYER, Category.OTHER)

      //This is where you declare the price(s) of the bot
      variants {
        variant(name = "Premium", price = 0.05)
      }

      //For premium bots, you can declare a "trial" which is a period for which a user can use the bot for free
      trial {
        window = Duration.ofDays(7)
        allowance = Duration.ofHours(3)
      }
    }
  }

}

//Sets the project language level to 17
java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(17))
  }
}