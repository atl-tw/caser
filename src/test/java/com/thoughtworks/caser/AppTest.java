package com.thoughtworks.caser;

import com.google.common.base.Charsets;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.google.common.truth.Truth.assertThat;


public class AppTest
{
    private ByteArrayOutputStream outContent;
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        outContent = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testHelp(){
        App.main("--help");
        assertThat(outContent.toString()).contains("caser");
    }


    @Test
    public void testLowerHypten(){
        String s = "lower-hyphen-string";
        App.main("-i", "lower-hyphen", "-o", "UPPER-HYPHEN", s);
        assertThat(outContent.toString(Charsets.UTF_8)).isEqualTo("LOWER-HYPHEN-STRING"+System.lineSeparator());
        setUpStreams();

        App.main("-i", "lower-hyphen", "-o", "lower_snake", s);
        assertThat(outContent.toString(Charsets.UTF_8)).isEqualTo("lower_hyphen_string"+System.lineSeparator());
        setUpStreams();

        App.main("-i", "lower-hyphen", "-o", "lower.dotted", s);
        assertThat(outContent.toString(Charsets.UTF_8)).isEqualTo("lower.hyphen.string"+System.lineSeparator());
        setUpStreams();

        App.main("-i", "lower-hyphen", "-o", "java.Type", s);
        assertThat(outContent.toString(Charsets.UTF_8)).isEqualTo("lower.hyphen.String"+System.lineSeparator());
        setUpStreams();

        App.main("-i", "lower-hyphen", "-o", "UPPER-HYPHEN", s);
        assertThat(outContent.toString(Charsets.UTF_8)).isEqualTo("LOWER-HYPHEN-STRING"+System.lineSeparator());
        setUpStreams();

        App.main("-i", "lower-hyphen", "-o", "UPPER_SNAKE", s);
        assertThat(outContent.toString(Charsets.UTF_8)).isEqualTo("LOWER_HYPHEN_STRING"+System.lineSeparator());
        setUpStreams();

        App.main("-i", "lower-hyphen", "-o", "UPPER.DOTTED", s);
        assertThat(outContent.toString(Charsets.UTF_8)).isEqualTo("LOWER.HYPHEN.STRING"+System.lineSeparator());
        setUpStreams();

        App.main("-i", "lower-hyphen", "-o", "UpperCamel", s);
        assertThat(outContent.toString(Charsets.UTF_8)).isEqualTo("LowerHyphenString"+System.lineSeparator());
        setUpStreams();

        App.main("-i", "lower-hyphen", "-o", "lowerCamel", s);
        assertThat(outContent.toString(Charsets.UTF_8)).isEqualTo("lowerHyphenString"+System.lineSeparator());
        setUpStreams();
    }

    @Test
    public void testUpperHyphen(){
        String s = "UPPER-HYPHEN-STRING";
        App.main("-o", "lower-hyphen", "-i", "UPPER-HYPHEN", s);
        assertThat(outContent.toString(Charsets.UTF_8)).isEqualTo("upper-hyphen-string"+System.lineSeparator());
        setUpStreams();

        App.main("-i", "UPPER-HYPHEN", "-o", "lower_snake", s);
        assertThat(outContent.toString(Charsets.UTF_8)).isEqualTo("upper_hyphen_string"+System.lineSeparator());
        setUpStreams();

        App.main("-i", "UPPER-HYPHEN", "-o", "lower.dotted", s);
        assertThat(outContent.toString(Charsets.UTF_8)).isEqualTo("upper.hyphen.string"+System.lineSeparator());
        setUpStreams();

        App.main("-i", "UPPER-HYPHEN", "-o", "java.Type", s);
        assertThat(outContent.toString(Charsets.UTF_8)).isEqualTo("upper.hyphen.String"+System.lineSeparator());
        setUpStreams();

        App.main("-i", "UPPER-HYPHEN", "-o", "UPPER-HYPHEN", s);
        assertThat(outContent.toString(Charsets.UTF_8)).isEqualTo("UPPER-HYPHEN-STRING"+System.lineSeparator());
        setUpStreams();

        App.main("-i", "UPPER-HYPHEN", "-o", "UPPER_SNAKE", s);
        assertThat(outContent.toString(Charsets.UTF_8)).isEqualTo("UPPER_HYPHEN_STRING"+System.lineSeparator());
        setUpStreams();

        App.main("-i", "UPPER-HYPHEN", "-o", "UPPER.DOTTED", s);
        assertThat(outContent.toString(Charsets.UTF_8)).isEqualTo("UPPER.HYPHEN.STRING"+System.lineSeparator());
        setUpStreams();

        App.main("-i", "UPPER-HYPHEN", "-o", "UpperCamel", s);
        assertThat(outContent.toString(Charsets.UTF_8)).isEqualTo("UpperHyphenString"+System.lineSeparator());
        setUpStreams();

        App.main("-i", "UPPER-HYPHEN", "-o", "lowerCamel", s);
        assertThat(outContent.toString(Charsets.UTF_8)).isEqualTo("upperHyphenString"+System.lineSeparator());
        setUpStreams();
    }

    @Test
    public void testLowerSnake(){
        String s = "lower_snake_string";

        App.main("-i", "lower_snake", "-o", "UPPER-HYPHEN", s);
        assertThat(outContent.toString(Charsets.UTF_8)).isEqualTo("LOWER-SNAKE-STRING"+System.lineSeparator());
        setUpStreams();

        App.main("-i", "lower_snake", "-o", "lower.dotted", s);
        assertThat(outContent.toString(Charsets.UTF_8)).isEqualTo("lower.snake.string"+System.lineSeparator());
        setUpStreams();

        App.main("-i", "lower_snake", "-o", "java.Type", s);
        assertThat(outContent.toString(Charsets.UTF_8)).isEqualTo("lower.snake.String"+System.lineSeparator());
        setUpStreams();

        App.main("-i", "lower_snake", "-o", "UPPER-HYPHEN", s);
        assertThat(outContent.toString(Charsets.UTF_8)).isEqualTo("LOWER-SNAKE-STRING"+System.lineSeparator());
        setUpStreams();

        App.main("-i", "lower_snake", "-o", "UPPER_SNAKE", s);
        assertThat(outContent.toString(Charsets.UTF_8)).isEqualTo("LOWER_SNAKE_STRING"+System.lineSeparator());
        setUpStreams();

        App.main("-i", "lower_snake", "-o", "UPPER.DOTTED", s);
        assertThat(outContent.toString(Charsets.UTF_8)).isEqualTo("LOWER.SNAKE.STRING"+System.lineSeparator());
        setUpStreams();

        App.main("-i", "lower_snake", "-o", "UpperCamel", s);
        assertThat(outContent.toString(Charsets.UTF_8)).isEqualTo("LowerSnakeString"+System.lineSeparator());
        setUpStreams();

        App.main("-i", "lower_snake", "-o", "lowerCamel", s);
        assertThat(outContent.toString(Charsets.UTF_8)).isEqualTo("lowerSnakeString"+System.lineSeparator());
        setUpStreams();
    }


}
