## Version

v16

## Overview

These code samples show how to use Java to call APIs.

## Contents

src/main/
  - resources/
    - api_config.properties.dist    : Config files to specify Ids. Rename this file to "api_config.properties"
  - java/jp/co/yahoo/adsdisplayapi/sample
    - basic/                      : Examples of each service.
    - util/                       : Utilities which help you use the code samples.

## Development environment

Install the software below to organize environment.

1. Java 17 or above
2. Apache Maven 3.5.3 or above
3. Set the following environment variables.
  - BASE_ACCOUNT_ID      : Account ID that should be specified in 'x-z-base-account-id' header. (required)
  - ACCOUNT_ID           : Account ID (required)
  - CLIENT_ID            : Client ID (required)
  - CLIENT_SECRET        : Client secret (required)
  - REFRESH_TOKEN        : Refresh token (required)

## How to execute Sample Code

Example
```
mvn exec:java -Dexec.mainClass=jp.co.yahoo.adsdisplayapi.sample.basic.report.ReportDefinitionServiceSample
```

## NOTICE：Yahoo! JAPAN Ads Display Ads API - For use of sample code

The sample code of Yahoo! JAPAN Ads API is provided to API users only who concluded the contract of "Application to Use Yahoo! JAPAN Ads API" with LY Corporation.  
Additionally, please note that LY Corporation may change the contents and the specification of the sample code, and may discontinue providing the sample code without any notice.  
