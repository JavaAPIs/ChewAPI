# ChewAPI for Java

The official Java API library for my API.

## Downloading / Installing

### Maven

First, you need my Maven repository
```xml
<repository>
    <id>chew-jenkins</id>
    <url>https://jenkins.chew.pw/plugin/repository/everything/</url>
</repository>
```
Then, you'll need to install the build you want. All are considered dev builds unless otherwise specified in a [Release](https://github.com/Chew/chewapi-java/releases).

Replace `<version>[this]</version>` with the latest build found on [the Jenkins page](https://jenkins.chew.pw/job/chewapi-java/lastSuccessfulBuild/). See ChewAPI-[version string].jar.
```xml
<!-- Memerator API -->
<dependency>
    <groupId>pro.chew.api</groupId>
    <artifactId>ChewAPI</artifactId>
    <version>[this]</version>
</dependency>
```

Builds remain there indefinitely, but it's always best to stay up to date.

Alternatively, on the same Jenkins link, you can manually download the JAR yourself for safe keeping, in case it does go down.

## Using

Using the API is simple. Here's an example to get you started!

```java
import pro.chew.api.ChewAPI;
import pro.chew.api.objects.*;

public class MyChewAPIProgram {
    public static void main(String[] args) {
        // Define the ChewAPI
        ChewAPI api = new MemeratorAPI();
        // Get a TRBMB Phrase
        String trbmb = api.getTRBMBPhrase();
    }
}
```

You can view the Javadocs [here](https://jenkins.chew.pw/job/chewapi-java/javadoc/overview-summary.html).