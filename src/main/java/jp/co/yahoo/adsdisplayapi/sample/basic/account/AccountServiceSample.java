/**
 * Copyright (C) 2022 Yahoo Japan Corporation. All Rights Reserved.
 */
package jp.co.yahoo.adsdisplayapi.sample.basic.account;

import jp.co.yahoo.adsdisplayapi.sample.util.ApiUtils;
import jp.co.yahoo.adsdisplayapi.v9.api.AccountServiceApi;
import jp.co.yahoo.adsdisplayapi.v9.model.Account;
import jp.co.yahoo.adsdisplayapi.v9.model.AccountServiceAutoTaggingEnabled;
import jp.co.yahoo.adsdisplayapi.v9.model.AccountServiceDeliveryStatus;
import jp.co.yahoo.adsdisplayapi.v9.model.AccountServiceOperation;
import jp.co.yahoo.adsdisplayapi.v9.model.AccountServiceSelector;
import jp.co.yahoo.adsdisplayapi.v9.model.AccountServiceStatus;
import jp.co.yahoo.adsdisplayapi.v9.model.AccountServiceType;

/**
 * example AccountService operation.
 */
public class AccountServiceSample {

  private static final AccountServiceApi accountService = new AccountServiceApi(ApiUtils.getYahooJapanAdsApiClient());

  /**
   * example AccountService operation.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) throws Exception {
    try {
      get(); // AccountService/get
      set(); // AccountService/set
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  /**
   * example get accounts.
   */
  private static void get() {
    // Create the selector.
    AccountServiceSelector selector = new AccountServiceSelector();
    selector.addAccountStatusesItem(AccountServiceStatus.SERVING);
    selector.addAccountStatusesItem(AccountServiceStatus.ENDED);
    selector.addAccountTypesItem(AccountServiceType.INVOICE);
    selector.addAccountTypesItem(AccountServiceType.PREPAY);

    // Get the account.
    accountService.accountServiceGetPost(selector);
  }

  /**
   * example set account.
   */
  private static void set() {
    // Create the operation.
    Account operand = new Account();
    operand.setDeliveryStatus(AccountServiceDeliveryStatus.PAUSED);
    operand.setAutoTaggingEnabled(AccountServiceAutoTaggingEnabled.TRUE);
    AccountServiceOperation operation = new AccountServiceOperation();
    operation.setAccountId(ApiUtils.ACCOUNT_ID);
    operation.addOperandItem(operand);

    // Set the account.
    accountService.accountServiceSetPost(operation);
  }

}
