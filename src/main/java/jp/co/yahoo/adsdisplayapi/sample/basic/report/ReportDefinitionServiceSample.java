/**
 * Copyright (C) 2022 Yahoo Japan Corporation. All Rights Reserved.
 */
package jp.co.yahoo.adsdisplayapi.sample.basic.report;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import jp.co.yahoo.adsdisplayapi.sample.util.ApiUtils;
import jp.co.yahoo.adsdisplayapi.v8.api.ReportDefinitionServiceApi;
import jp.co.yahoo.adsdisplayapi.v8.model.ReportDefinition;
import jp.co.yahoo.adsdisplayapi.v8.model.ReportDefinitionServiceDownloadSelector;
import jp.co.yahoo.adsdisplayapi.v8.model.ReportDefinitionServiceGetReportFields;
import jp.co.yahoo.adsdisplayapi.v8.model.ReportDefinitionServiceGetResponse;
import jp.co.yahoo.adsdisplayapi.v8.model.ReportDefinitionServiceMutateResponse;
import jp.co.yahoo.adsdisplayapi.v8.model.ReportDefinitionServiceOperation;
import jp.co.yahoo.adsdisplayapi.v8.model.ReportDefinitionServiceReportDateRangeType;
import jp.co.yahoo.adsdisplayapi.v8.model.ReportDefinitionServiceReportDownloadEncode;
import jp.co.yahoo.adsdisplayapi.v8.model.ReportDefinitionServiceReportDownloadFormat;
import jp.co.yahoo.adsdisplayapi.v8.model.ReportDefinitionServiceReportJobStatus;
import jp.co.yahoo.adsdisplayapi.v8.model.ReportDefinitionServiceReportLanguage;
import jp.co.yahoo.adsdisplayapi.v8.model.ReportDefinitionServiceReportSortField;
import jp.co.yahoo.adsdisplayapi.v8.model.ReportDefinitionServiceReportSortType;
import jp.co.yahoo.adsdisplayapi.v8.model.ReportDefinitionServiceSelector;
import jp.co.yahoo.adsdisplayapi.v8.model.ReportDefinitionServiceType;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;

/**
 * example ReportDefinitionService operation.
 */
public class ReportDefinitionServiceSample {

  private static final ReportDefinitionServiceApi reportDefinitionService = new ReportDefinitionServiceApi(ApiUtils.getYahooJapanAdsApiClient());

  /**
   * example ReportDefinitionService operation.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) throws Exception {
    try {
      getReportFields(); // ReportDefinitionService/getReportFields

      ReportDefinitionServiceMutateResponse mutateResponse = add(); // ReportDefinitionService/add

      Long reportJobId = mutateResponse.getRval().getValues().get(0).getReportDefinition().getReportJobId(); // extract reportJobId
      get(reportJobId); // ReportDefinitionService/get

      checkReportJobStatus(reportJobId); // check job status

      download(reportJobId); // ReportDefinitionService/download

      remove(reportJobId); //ReportDefinitionService/remove
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  /**
   * example get reportFields.
   */
  private static void getReportFields() {
    // Create the getReportFields.
    ReportDefinitionServiceGetReportFields getReportFields = new ReportDefinitionServiceGetReportFields();
    getReportFields.setType(ReportDefinitionServiceType.AD);

    // Get the reportFields.
    reportDefinitionService.reportDefinitionServiceGetReportFieldsPost(getReportFields);
  }

  /**
   * example add report.
   */
  private static ReportDefinitionServiceMutateResponse add() {
    // Create the operation.
    ReportDefinitionServiceReportSortField sortField = new ReportDefinitionServiceReportSortField();
    sortField.setReportSortType(ReportDefinitionServiceReportSortType.ASC);
    sortField.setField("ACCOUNT_ID");
    ReportDefinition operand = new ReportDefinition();
    operand.setReportName("REACH-FREQUENCY-REPORT");
    operand.setReportDateRangeType(ReportDefinitionServiceReportDateRangeType.YESTERDAY);
    operand.addSortFieldsItem(sortField);
    operand.addFieldsItem("ACCOUNT_ID");
    operand.addFieldsItem("ACCOUNT_NAME");
    operand.setReportDownloadFormat(ReportDefinitionServiceReportDownloadFormat.CSV);
    operand.setReportDownloadEncode(ReportDefinitionServiceReportDownloadEncode.UTF8);
    operand.setReportLanguage(ReportDefinitionServiceReportLanguage.EN);
    ReportDefinitionServiceOperation operation = new ReportDefinitionServiceOperation();
    operation.setAccountId(ApiUtils.ACCOUNT_ID);
    operation.addOperandItem(operand);

    // Add the report.
    return reportDefinitionService.reportDefinitionServiceAddPost(operation);
  }

  /**
   * example get report.
   *
   * @param reportJobId
   */
  private static ReportDefinitionServiceGetResponse get(Long reportJobId) {
    // Create the selector.
    ReportDefinitionServiceSelector selector = new ReportDefinitionServiceSelector();
    selector.setAccountId(ApiUtils.ACCOUNT_ID);
    selector.addReportJobIdsItem(reportJobId);

    // Get the report.
    return reportDefinitionService.reportDefinitionServiceGetPost(selector);
  }

  /**
   * example check Report job status.
   *
   * @param reportJobId
   * @throws Exception
   */
  private static void checkReportJobStatus(Long reportJobId) throws Exception {
    // call 30sec sleep * 30 = 15minute
    for (int i = 0; i < 30; i++) {
      // sleep 30 second.
      System.out.println("\n***** sleep 30 seconds for Report Download Job *****\n");
      Thread.sleep(30000);

      ReportDefinitionServiceGetResponse response = get(reportJobId);

      ReportDefinitionServiceReportJobStatus status = response.getRval().getValues().get(0).getReportDefinition().getReportJobStatus();
      if (status == null) {
        throw new Exception("Fail to get Report.");
      }
      switch (status) {
        default:
        case WAIT:
        case IN_PROGRESS:
          continue;
        case CANCELED:
        case FAILED:
          throw new Exception("Report Job Status failed.");
        case COMPLETED:
          return;
      } // switch
    } // for
  }

  /**
   * example download report.
   *
   * @param reportJobId
   * @throws IOException
   */
  private static void download(Long reportJobId) throws IOException {
    // Create the selector.
    ReportDefinitionServiceDownloadSelector selector = new ReportDefinitionServiceDownloadSelector();
    selector.setAccountId(ApiUtils.ACCOUNT_ID);
    selector.setReportJobId(reportJobId);

    // Download the report.
    Resource report = reportDefinitionService.reportDefinitionServiceDownloadPost(selector);
    System.out.println("### reportString=\n" + StreamUtils.copyToString(report.getInputStream(), StandardCharsets.UTF_8));
  }

  /**
   * example remove report.
   *
   * @param reportJobId
   */
  private static void remove(Long reportJobId) {
    // Create the operation.
    ReportDefinition operand = new ReportDefinition();
    operand.setReportJobId(reportJobId);
    ReportDefinitionServiceOperation operation = new ReportDefinitionServiceOperation();
    operation.setAccountId(ApiUtils.ACCOUNT_ID);
    operation.addOperandItem(operand);

    // Remove the report.
    reportDefinitionService.reportDefinitionServiceRemovePost(operation);
  }

}
