/*
  Copyright (C) 2022 Yahoo Japan Corporation. All Rights Reserved.
 */
package jp.co.yahoo.adsdisplayapi.sample.util;

import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import jp.co.yahoo.adsdisplayapi.v9.YahooJapanAdsApiClient;

/**
 * Utility method collection for Java Sample Program.
 */
public class ApiUtils {

  public static final long ACCOUNT_ID;

  private static final String CLIENT_ID;

  private static final String CLIENT_SECRET;

  private static final String REFRESH_TOKEN;

  /*
   * static initializer
   */
  static {
    ResourceBundle bundle = ResourceBundle.getBundle("api_config");

    ACCOUNT_ID = Long.parseLong(Optional.ofNullable(System.getenv("ACCOUNT_ID")).orElseGet(() ->
        bundle.getString("ACCOUNT_ID")));
    CLIENT_ID = Optional.ofNullable(System.getenv("CLIENT_ID")).orElseGet(() ->
        bundle.getString("CLIENT_ID"));
    CLIENT_SECRET = Optional.ofNullable(System.getenv("CLIENT_SECRET")).orElseGet(() ->
        bundle.getString("CLIENT_SECRET"));
    REFRESH_TOKEN = Optional.ofNullable(System.getenv("REFRESH_TOKEN")).orElseGet(() ->
        bundle.getString("REFRESH_TOKEN"));
  }

  public static YahooJapanAdsApiClient getYahooJapanAdsApiClient() {
    YahooJapanAdsApiClient yahooJapanAdsApiClient = new YahooJapanAdsApiClient(CLIENT_ID, CLIENT_SECRET, REFRESH_TOKEN);
    yahooJapanAdsApiClient.setDebugging(true);
    return yahooJapanAdsApiClient;
  }

}
