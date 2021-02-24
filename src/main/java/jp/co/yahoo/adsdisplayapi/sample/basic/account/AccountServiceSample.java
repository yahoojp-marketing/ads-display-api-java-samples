/**
 * Copyright (C) 2020 Yahoo Japan Corporation. All Rights Reserved.
 */
package jp.co.yahoo.adsdisplayapi.sample.basic.account;

import java.util.List;
import jp.co.yahoo.adsdisplayapi.sample.util.ApiUtils;
import jp.co.yahoo.adsdisplayapi.v4.model.Account;
import jp.co.yahoo.adsdisplayapi.v4.model.AccountServiceAutoTaggingEnabled;
import jp.co.yahoo.adsdisplayapi.v4.model.AccountServiceDeliveryStatus;
import jp.co.yahoo.adsdisplayapi.v4.model.AccountServiceGetResponse;
import jp.co.yahoo.adsdisplayapi.v4.model.AccountServiceMutateResponse;
import jp.co.yahoo.adsdisplayapi.v4.model.AccountServiceOperation;
import jp.co.yahoo.adsdisplayapi.v4.model.AccountServiceSelector;
import jp.co.yahoo.adsdisplayapi.v4.model.AccountServiceStatus;
import jp.co.yahoo.adsdisplayapi.v4.model.AccountServiceType;
import jp.co.yahoo.adsdisplayapi.v4.model.AccountServiceValue;

/**
 * example AccountService operation and Utility method collection.
 */
public class AccountServiceSample {

  private static final String SERVICE_NAME = "AccountService";

  /**
   * example AccountService operation.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) throws Exception {
    try {
      // =================================================================
      // Setting
      // =================================================================
      long accountId = ApiUtils.ACCOUNT_ID;

      // =================================================================
      // AccountService GET
      // =================================================================
      // create request.
      AccountServiceSelector selector = new AccountServiceSelector();
      selector.addAccountStatusesItem(AccountServiceStatus.SERVING);
      selector.addAccountStatusesItem(AccountServiceStatus.ENDED);
      selector.addAccountTypesItem(AccountServiceType.INVOICE);
      selector.addAccountTypesItem(AccountServiceType.PREPAY);

      // run
      get(selector);

      // =================================================================
      // AccountService SET
      // =================================================================
      // create request.
      Account operand = new Account();
      operand.setDeliveryStatus(AccountServiceDeliveryStatus.PAUSED);
      operand.setAutoTaggingEnabled(AccountServiceAutoTaggingEnabled.TRUE);

      AccountServiceOperation operation = new AccountServiceOperation();
      operation.getOperand().add(operand);
      operation.setAccountId(accountId);

      // run
      mutate(operation, "set");

    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  /**
   * example mutate accounts.
   *
   * @param operation AccountOperation
   * @return List<AccountValues>
   */
  public static List<AccountServiceValue> mutate(AccountServiceOperation operation, String action) throws Exception {

    AccountServiceMutateResponse response = ApiUtils.execute(SERVICE_NAME, action, operation, AccountServiceMutateResponse.class);
    System.out.println(operation);

    // Response
    return response.getRval().getValues();
  }

  /**
   * example get accounts.
   *
   * @param selector AccountSelector
   * @return AccountValues
   */
  public static List<AccountServiceValue> get(AccountServiceSelector selector) throws Exception {

    AccountServiceGetResponse response = ApiUtils.execute(SERVICE_NAME, "get", selector, AccountServiceGetResponse.class);

    // Response
    return response.getRval().getValues();
  }
}
