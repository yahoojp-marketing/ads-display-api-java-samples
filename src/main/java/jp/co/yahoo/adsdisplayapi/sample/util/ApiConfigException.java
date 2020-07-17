/**
 * Copyright (C) 2020 Yahoo Japan Corporation. All Rights Reserved.
 */
package jp.co.yahoo.adsdisplayapi.sample.util;

/**
 * Utility method collection for Java Sample Program.
 */
public class ApiConfigException extends RuntimeException {

  public ApiConfigException(String message) {
    super(message);
  }

  public ApiConfigException(String message, Throwable cause) {
    super(message, cause);
  }
}
