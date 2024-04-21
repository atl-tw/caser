Caser
=====

Purpose
-------

This is a small command line utility that converts between different string formats.

```bash 
caser
```
```
Usage: caser [options] String values to convert
  Options:
    /?, --help
      Show help
      Default: false
  * -i, --input-format
      Input format.
      Possible Values: [lower-hyphen, lowerCamel, UpperCamel, UPPER-HYPHEN, lower_snake, UPPER_SNAKE, lower.dotted, UPPER.DOTTED, java.Type]
  * -o, --output-format
      Output format.
      Possible Values: [lower-hyphen, lowerCamel, UpperCamel, UPPER-HYPHEN, lower_snake, UPPER_SNAKE, lower.dotted, UPPER.DOTTED, java.Type]

```

```bash
caser -i lower.dotted -o UPPER_SNAKE  this.is.an.example 
```
```
THIS_IS_AN_EXAMPLE
```

More than that, this is a study in various ways to package a Java project for distribution with maven.

The outputs of this build include:

 * A shaded JAR file that can be run with ``java -jar``
 * Zip and TGZ assembly of a distribution suitable for any OS.
 * A Windows installer built with Nullsoft Scriptable Install System
 * A native binary build with GraalVM's native-image
 * A Homebrew formula for Mac (Published to a Tap with GHA)
 * The usual Maven repository suspects (Published to a repo with GHA)


Installation:
-------------

You can find binaries for Windows, Mac, and Linux (for Intel) on the Releases page. 

For Mac you can also install via Homebrew:

```bash
brew tap atl-tw/tap
brew insall caser
```



Build Tools:
------------

Mac:

```bash
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdkman env
brew install makensis 
```

Build
-----

Build platform agnostic:
```bash
./mvnw install
```

Build native image:
```bash
./mvnw -Pnative -Dagent=true install
```

