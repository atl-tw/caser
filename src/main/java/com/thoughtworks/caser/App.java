package com.thoughtworks.caser;

import com.beust.jcommander.JCommander;

import java.util.Collections;

import static java.util.Optional.ofNullable;

public class App 
{
    public static void main(String... args )
    {
      Args arguments;
      arguments = new Args();
      var jcom = JCommander.newBuilder().programName("caser").addObject(arguments).build();
        try {
            jcom.parse(args);
        } catch (Exception e) {
            jcom.usage();
            System.err.println(e.getMessage());
            return;
        }
        if (arguments.isHelp()) {
            jcom.usage();
            return;
        }
        ofNullable(arguments.getValues()).orElse(Collections.emptyList())
            .stream()
            .map(s-> arguments.getInput().format.convert(arguments.getOutput().format, s))
            .forEach(System.out::println);
    }

}
