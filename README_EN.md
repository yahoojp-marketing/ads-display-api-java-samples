--------------------------------
[Version]
--------------------------------
v5

--------------------------------
[Overview]
--------------------------------
These code samples show how to use Java to call APIs.

--------------------------------
[Contents]
--------------------------------
src/main/
  - resources/
    - api_config.properties.dist    : Config files to specify Ids.
  - java/jp/co/yahoo/adsdisplayapi/sample
    - basic/                      : Examples of each services.
    - repository/                 : Utilities which help you use the code samples.
    - util/                       : Utilities which help you use the code samples.

download/                           : Directory where downloaded files stored when using download feature.

module/                           : Modules which need for starting the code samples.

--------------------------------
[Development environment]
--------------------------------
Install the software below to organize environment.

1. Java 1.8(Java SE Development Kit 8 or above
2. Apache Maven 3.5.3 or above
3. OpenAPI generator 4.x series (4.2.3 or above)
4. Rename src/main/resources/api_config.properties.dist to "api_config.properties".
5. Write the following each ID in src/main/resources/api_config.properties.
  - ACCOUNT_ID           : Account ID (required)
  - CLIENT_ID            : Client ID (required)
  - CLIENT_SECRET        : Client secret (required)
  - REFRESH_TOKEN        : Refresh token (required)

--------------------------------
[How to execute Sample Code]
--------------------------------
Execute OpenAPI Generator and generate model for Java.
```
openapi-generator generate -i https://raw.githubusercontent.com/yahoojp-marketing/ads-display-api-documents/master/design/v5/Route.yaml -g java --global-property=models --model-package jp.co.yahoo.adsdisplayapi.v5.model
```

Output the models to below directory by the OpenAPI generator.
```
src/main/java/jp/co/yahoo/adsdisplayapi/v5/model
```

Example
```
mvn exec:java -Dexec.mainClass=jp.co.yahoo.adsdisplayapi.sample.basic.report.ReportDefinitionServiceSample
```

--------------------------------
NOTICE：Yahoo! JAPAN Ads Display Ads API - For use of sample code
--------------------------------

The sample code of Yahoo! JAPAN Ads API is provided to API users only who concluded the contract of "Application to Use Yahoo! JAPAN Promotional Ads API" with Yahoo Japan Corporation.  
Additionally, please note that Yahoo Japan Corporation may change the contents and the specification of the sample code, and may discontinue providing the sample code without any notice.  
