package com.thoughtworks.caser;

import com.google.common.annotations.VisibleForTesting;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class CaseFormatTest  {

  @Test
  public void testJavaType(){

    String upperSnake= "UPPER_SNAKE_CASE";
    assertThat(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.JAVA_TYPE, upperSnake)).isEqualTo("upper.snake.Case");

    assertThat(CaseFormat.JAVA_TYPE.to(CaseFormat.LOWER_UNDERSCORE, CaseFormatTest.class.getCanonicalName()))
        .isEqualTo("com_thoughtworks_caser_CaseformatTest");
  }

}