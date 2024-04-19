package com.thoughtworks.caser;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.Parameter;
import lombok.Data;

import java.util.List;

@Data
public class Args {
  @Parameter(
      names = {"-i", "--input-format"},
      description = "Input format.",
      converter = FormatConverter.class,
      required = true)
  private Format input;

  @Parameter(
      names = {"-o", "--output-format"},
      description = "Output format.",
      converter = FormatConverter.class,
      required = true)
  private Format output;

  @Parameter(description = "String values to convert", required = true)
  private List<String> values;


  @Parameter(
      names = {"/?", "--help"},
      description = "Show help",
      help = true)
  private boolean help = false;

  static class FormatConverter implements IStringConverter<Format> {

    @Override
    public Format convert(String s) {
      return Format.fromString(s);
    }
  }
}
