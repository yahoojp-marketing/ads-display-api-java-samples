/**
 * Copyright (C) 2020 Yahoo Japan Corporation. All Rights Reserved.
 */
package jp.co.yahoo.adsdisplayapi.sample.basic.report;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import jp.co.yahoo.adsdisplayapi.sample.repository.ValuesRepositoryFacade;
import jp.co.yahoo.adsdisplayapi.sample.util.ApiUtils;
import jp.co.yahoo.adsdisplayapi.sample.util.ValuesHolder;
import jp.co.yahoo.adsdisplayapi.v4.model.ReportDefinition;
import jp.co.yahoo.adsdisplayapi.v4.model.ReportDefinitionServiceDownloadSelector;
import jp.co.yahoo.adsdisplayapi.v4.model.ReportDefinitionServiceFieldAttribute;
import jp.co.yahoo.adsdisplayapi.v4.model.ReportDefinitionServiceFrequencyRange;
import jp.co.yahoo.adsdisplayapi.v4.model.ReportDefinitionServiceFrequencyReportCondition;
import jp.co.yahoo.adsdisplayapi.v4.model.ReportDefinitionServiceGetReportFields;
import jp.co.yahoo.adsdisplayapi.v4.model.ReportDefinitionServiceGetReportFieldsResponse;
import jp.co.yahoo.adsdisplayapi.v4.model.ReportDefinitionServiceGetResponse;
import jp.co.yahoo.adsdisplayapi.v4.model.ReportDefinitionServiceMutateResponse;
import jp.co.yahoo.adsdisplayapi.v4.model.ReportDefinitionServiceOperation;
import jp.co.yahoo.adsdisplayapi.v4.model.ReportDefinitionServiceReportDateRangeType;
import jp.co.yahoo.adsdisplayapi.v4.model.ReportDefinitionServiceReportDownloadEncode;
import jp.co.yahoo.adsdisplayapi.v4.model.ReportDefinitionServiceReportDownloadFormat;
import jp.co.yahoo.adsdisplayapi.v4.model.ReportDefinitionServiceReportJobStatus;
import jp.co.yahoo.adsdisplayapi.v4.model.ReportDefinitionServiceReportLanguage;
import jp.co.yahoo.adsdisplayapi.v4.model.ReportDefinitionServiceReportSortField;
import jp.co.yahoo.adsdisplayapi.v4.model.ReportDefinitionServiceReportSortType;
import jp.co.yahoo.adsdisplayapi.v4.model.ReportDefinitionServiceReportTypeCondition;
import jp.co.yahoo.adsdisplayapi.v4.model.ReportDefinitionServiceSelector;
import jp.co.yahoo.adsdisplayapi.v4.model.ReportDefinitionServiceType;
import jp.co.yahoo.adsdisplayapi.v4.model.ReportDefinitionServiceValue;

/**
 * example ReportDefinitionService operation and Utility method collection.
 */
public class ReportDefinitionServiceSample {

  private static final String SERVICE_NAME = "ReportDefinitionService";

  /**
   * example ReportDefinitionService operation.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) throws Exception {
    try {
      // =================================================================
      // Setting
      // =================================================================
      ValuesHolder valuesHolder = new ValuesHolder();
      ValuesRepositoryFacade valuesRepositoryFacade = new ValuesRepositoryFacade(valuesHolder);
      long accountId = ApiUtils.ACCOUNT_ID;

      // =================================================================
      // ReportDefinitionService getReportFields
      // =================================================================
      // create request.
      ReportDefinitionServiceGetReportFields getReportFieldsRequest = new ReportDefinitionServiceGetReportFields();
      getReportFieldsRequest.setType(ReportDefinitionServiceType.AD);

      // run
      getReportFields(getReportFieldsRequest);

      // =================================================================
      // ReportDefinitionService ADD
      // =================================================================
      // create request.
      ReportDefinitionServiceOperation addRequest = buildExampleMutateRequest(accountId, Collections.singletonList(createExampleReportDefinition()));

      // run
      List<ReportDefinitionServiceValue> addResponse = mutate(addRequest, "add");
      valuesHolder.setReportDefinitionValuesList(addResponse);

      // =================================================================
      // ReportDefinitionService GET
      // =================================================================
      // check job status
      checkStatus(valuesRepositoryFacade.getReportDefinitionValuesRepository().getReportJobIds());

      // create request.
      ReportDefinitionServiceSelector getRequest = buildExampleGetRequest(accountId, valuesRepositoryFacade.getReportDefinitionValuesRepository().getReportJobIds());

      // run
      List<ReportDefinitionServiceValue> getResponse = get(getRequest);

      long getJobIds = 0;
      for (ReportDefinitionServiceValue reportValues : getResponse) {
        getJobIds = reportValues.getReportDefinition().getReportJobId();
      }

      // =================================================================
      // ReportService download (http request)
      // =================================================================
      ReportDefinitionServiceDownloadSelector downloadSelector = buildExampleDownloadRequest(accountId, getJobIds);
      ApiUtils.download(SERVICE_NAME, "download", downloadSelector, "reportDownloadSample.csv");;

      // =================================================================
      // ReportDefinitionService REMOVE
      // =================================================================
      // create request.
      ReportDefinitionServiceOperation removeRequest = buildExampleMutateRequest(accountId, valuesRepositoryFacade.getReportDefinitionValuesRepository().getReportDefinitions());

      // run
      mutate(removeRequest, "remove");

    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  /**
   * example get ReportDefinitions.
   *
   * @param selector ReportDefinitionSelector
   * @return ReportDefinitionValues
   */
  public static List<ReportDefinitionServiceValue> get(ReportDefinitionServiceSelector selector) throws Exception {

    ReportDefinitionServiceGetResponse reportDefinitionService = ApiUtils.execute(SERVICE_NAME, "get", selector, ReportDefinitionServiceGetResponse.class);

    // Response
    return reportDefinitionService.getRval().getValues();
  }

  /**
   * example getReportFields ReportDefinitions.
   *
   * @param reportFields ReportFields
   * @return ReportFieldAttribute
   */
  public static List<ReportDefinitionServiceFieldAttribute> getReportFields(ReportDefinitionServiceGetReportFields reportFields) throws Exception {

    ReportDefinitionServiceGetReportFieldsResponse reportDefinitionService = ApiUtils.execute(SERVICE_NAME, "getReportFields", reportFields, ReportDefinitionServiceGetReportFieldsResponse.class);

    // Response
    return reportDefinitionService.getRval().getFields();
  }

  /**
   * example mutate ReportDefinitions.
   *
   * @param operation ReportDefinitionOperation
   * @return ReportDefinitionValues
   */
  public static List<ReportDefinitionServiceValue> mutate(ReportDefinitionServiceOperation operation, String action) throws Exception {

    ReportDefinitionServiceMutateResponse reportDefinitionService = ApiUtils.execute(SERVICE_NAME, action, operation, ReportDefinitionServiceMutateResponse.class);

    // Response
    return reportDefinitionService.getRval().getValues();
  }

  /**
   * example get request.
   *
   * @param accountId long
   * @param reportJobIds List<Long>
   * @return ReportDefinitionSelector
   */
  public static ReportDefinitionServiceSelector buildExampleGetRequest(long accountId, List<Long> reportJobIds) {
    ReportDefinitionServiceSelector selector = new ReportDefinitionServiceSelector();
    selector.setAccountId(accountId);
    selector.setReportJobIds(reportJobIds);
    return selector;
  }

  private static ReportDefinitionServiceDownloadSelector buildExampleDownloadRequest(long accountId, long reportJobId) {
    ReportDefinitionServiceDownloadSelector selector = new ReportDefinitionServiceDownloadSelector();
    selector.setAccountId(accountId);
    selector.setReportJobId(reportJobId);
    return selector;
  }

  /**
   * example mutate request.
   *
   * @param accountId long
   * @param operand   List<ReportDefinition>
   * @return ReportDefinitionOperation
   */
  public static ReportDefinitionServiceOperation buildExampleMutateRequest(long accountId, List<ReportDefinition> operand) {
    ReportDefinitionServiceOperation operation = new ReportDefinitionServiceOperation();
    operation.setAccountId(accountId);
    operation.getOperand().addAll(operand);
    return operation;
  }

  /**
   * example ReportDefinition request.
   *
   * @return ReportDefinition
   */
  public static ReportDefinition createExampleReportDefinition() {
    ReportDefinition operand = new ReportDefinition();
    operand.setReportName("REACH-FREQUENCY-REPORT");
    operand.setReportDateRangeType(ReportDefinitionServiceReportDateRangeType.YESTERDAY);
    ReportDefinitionServiceReportSortField sortField = new ReportDefinitionServiceReportSortField();
    sortField.setReportSortType(ReportDefinitionServiceReportSortType.ASC);
    sortField.field("FREQUENCY");
    operand.setSortFields(Arrays.asList(sortField));
    operand.setFields(Arrays.asList( //
      "ACCOUNT_ID", //
      "ACCOUNT_NAME", //
      "CAMPAIGN_NAME", //
      "DAY", //
      "FREQUENCY", //
      "IMPS", //
      "CLICK", //
      "UNIQUE_USERS" //
    ));
    operand.setReportDownloadFormat(ReportDefinitionServiceReportDownloadFormat.CSV);
    operand.setReportDownloadEncode(ReportDefinitionServiceReportDownloadEncode.UTF_8);
    operand.setReportLanguage(ReportDefinitionServiceReportLanguage.EN);
    ReportDefinitionServiceReportTypeCondition condition = new ReportDefinitionServiceReportTypeCondition();
    ReportDefinitionServiceFrequencyReportCondition reportCondition = new ReportDefinitionServiceFrequencyReportCondition();
    reportCondition.setFrequencyRange(ReportDefinitionServiceFrequencyRange.DAILY);
    condition.setFrequencyReportCondition(reportCondition);
    condition.setReportType(ReportDefinitionServiceType.FREQUENCY);
    operand.setReportTypeCondition(condition);
    return operand;
  }

  /**
   * example check Report job status.
   *
   * @param jobIds List<Long>
   */
  public static void checkStatus(List<Long> jobIds) throws Exception {

    // call 30sec sleep * 30 = 15minute
    for (int i = 0; i < 30; i++) {

      // sleep 30 second.
      System.out.println("\n***** sleep 30 seconds for Report Download Job *****\n");
      Thread.sleep(30000);

      // get
      ReportDefinitionServiceSelector getRequest = buildExampleGetRequest(ApiUtils.ACCOUNT_ID, jobIds);
      List<ReportDefinitionServiceValue> getResponse = get(getRequest);

      int completedCount = 0;

      // check status
      for (ReportDefinitionServiceValue reportValues : getResponse) {
        ReportDefinitionServiceReportJobStatus status = reportValues.getReportDefinition().getReportJobStatus();
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
            completedCount++;
            continue;
        }
      }

      if (getResponse.size() == completedCount) {
        return;
      }
    }

    throw new Exception("Fail to get Report.");
  }
}
