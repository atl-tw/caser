package com.thoughtworks.caser;

import java.util.Arrays;

public enum Format {
  LOWER_HYPHEN("lower-hyphen", CaseFormat.LOWER_HYPHEN),
  LOWER_CAMEL("lowerCamel", CaseFormat.LOWER_CAMEL),
  UPPER_CAMEL("UpperCamel", CaseFormat.UPPER_CAMEL),
  UPPER_HYPHEN("UPPER-HYPHEN", CaseFormat.UPPER_HYPHEN),
  LOWER_SNAKE("lower_snake", CaseFormat.LOWER_UNDERSCORE),
  UPPER_SNAKE("UPPER_SNAKE", CaseFormat.UPPER_UNDERSCORE),
  LOWER_DOTTED( "lower.dotted", CaseFormat.LOWER_DOTTED),
  UPPER_DOTTED( "UPPER.DOTTED", CaseFormat.UPPER_DOTTED),
  JAVA_TYPE( "java.Type", CaseFormat.JAVA_TYPE);

  final String name;
  final CaseFormat format;

  Format(String name, CaseFormat format) {
    this.name = name;
    this.format = format;
  }

  public static Format fromString(String name) {
    return Arrays.stream(Format.values()).filter(f -> f.name.equals(name))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Unknown format: " + name));
  }

  public String toString() {
    return this.name;
  }
}
