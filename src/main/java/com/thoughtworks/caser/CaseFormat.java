package com.thoughtworks.caser;

import com.google.common.base.Ascii;
import com.google.common.base.CharMatcher;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.Function;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

/**
 * Utility class for converting between various ASCII case formats. Behavior is undefined for
 * non-ASCII input.
 *
 * @author Mike Bostock
 * @since 1.0
 */
public enum CaseFormat {

  /** Hyphenated variable naming convention, e.g., "lower-hyphen". */
  LOWER_HYPHEN(CharMatcher.is('-'), "-") {
    @Override
    String normalizeWord(String word) {
      return Ascii.toLowerCase(word);
    }
  },

  UPPER_HYPHEN(CharMatcher.is('-'), "-") {
    @Override
    String normalizeWord(String word) {
      return Ascii.toUpperCase(word);
    }
  },

  /** C++ variable naming convention, e.g., "lower_underscore". */
  LOWER_UNDERSCORE(CharMatcher.is('_'), "_") {
    @Override
    String normalizeWord(String word) {
      return Ascii.toLowerCase(word);
    }
  },

  /** Java variable naming convention, e.g., "lowerCamel". */
  LOWER_CAMEL(CharMatcher.inRange('A', 'Z'), "") {
    @Override
    String normalizeWord(String word) {
      return firstCharOnlyToUpper(word);
    }

    @Override
    String normalizeFirstWord(String word) {
      return Ascii.toLowerCase(word);
    }
  },

  /** Java and C++ class naming convention, e.g., "UpperCamel". */
  UPPER_CAMEL(CharMatcher.inRange('A', 'Z'), "") {
    @Override
    String normalizeWord(String word) {
      return firstCharOnlyToUpper(word);
    }
  },

  /** Java and C++ constant naming convention, e.g., "UPPER_UNDERSCORE". */
  UPPER_UNDERSCORE(CharMatcher.is('_'), "_") {
    @Override
    String normalizeWord(String word) {
      return Ascii.toUpperCase(word);
    }
  },

  JAVA_TYPE(CharMatcher.is('.'), "."){
    @Override
    String normalizeWord(String word) {
      return Ascii.toLowerCase(word);
    }

    @Override
    String normalizeLastWord(String word) {
      return CaseFormat.firstCharOnlyToUpper(word);
    }
  },

  LOWER_DOTTED(CharMatcher.is('.'), "."){
    @Override
    String normalizeWord(String word) {
      return Ascii.toLowerCase(word);
    }
  },

  UPPER_DOTTED(CharMatcher.is('.'), "."){
    @Override
    String normalizeWord(String word) {
      return Ascii.toUpperCase(word);
    }
  };

  private static final IdentityHashMap<CaseFormat, IdentityHashMap<CaseFormat, Function<String, String>>> FAST_CONVERT = new IdentityHashMap<>();
  private final CharMatcher wordBoundary;
  private final String wordSeparator;

  static {
    FAST_CONVERT.put(LOWER_HYPHEN, new IdentityHashMap<>(
        Map.of(UPPER_HYPHEN, Ascii::toUpperCase,
            LOWER_DOTTED, s->s.replace("-", "."),
            LOWER_UNDERSCORE, s->s.replace("-", "_"))));
    FAST_CONVERT.put(UPPER_HYPHEN, new IdentityHashMap<>(
        Map.of(LOWER_HYPHEN, Ascii::toLowerCase,
            UPPER_DOTTED, s->s.replace("-", "."),
            UPPER_UNDERSCORE, s->s.replace("-", "_"))));
  }

  CaseFormat(CharMatcher wordBoundary, String wordSeparator) {
    this.wordBoundary = wordBoundary;
    this.wordSeparator = wordSeparator;
  }


  /**
   * Converts the specified {@code String str} from this format to the specified {@code format}. A
   * "best effort" approach is taken; if {@code str} does not conform to the assumed format, then
   * the behavior of this method is undefined but we make a reasonable effort at converting anyway.
   */
  public final String to(CaseFormat format, String str) {
    checkNotNull(format);
    checkNotNull(str);
    return (format == this) ? str : convert(format, str);
  }

  /** Enum values can override for performance reasons. */
  String convert(CaseFormat format, String s) {

    var fast = ofNullable(FAST_CONVERT.get(this))
        .map(m->m.get(format));
    if(fast.isPresent()){
      return fast.get().apply(s);
    }


    // deal with camel conversion
    StringBuilder out = null;
    int i = 0;
    int j = -1;
    while ((j = wordBoundary.indexIn(s, ++j)) != -1) {
      if (i == 0) {
        // include some extra space for separators
        out = new StringBuilder(s.length() + 4 * format.wordSeparator.length());
        out.append(format.normalizeFirstWord(s.substring(i, j)));
      } else {
        requireNonNull(out).append(format.normalizeWord(s.substring(i, j)));
      }
      out.append(format.wordSeparator);
      i = j + wordSeparator.length();
    }
    return (i == 0)
        ? format.normalizeFirstWord(s)
        : requireNonNull(out).append(format.normalizeLastWord(s.substring(i))).toString();
  }

  String normalizeLastWord(String word){
    return normalizeWord(word);
  }

  abstract String normalizeWord(String word);

  String normalizeFirstWord(String word) {
    return normalizeWord(word);
  }

  private static String firstCharOnlyToUpper(String word) {
    return word.isEmpty()
        ? word
        : Ascii.toUpperCase(word.charAt(0)) + Ascii.toLowerCase(word.substring(1));
  }
}