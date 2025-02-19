/**
 * Copyright (C) 2023 LY Corporation. All Rights Reserved.
 */
package jp.co.yahoo.adsdisplayapi.sample.basic.account;

import jp.co.yahoo.adsdisplayapi.sample.util.ApiUtils;
import jp.co.yahoo.adsdisplayapi.v16.api.AccountServiceApi;
import jp.co.yahoo.adsdisplayapi.v16.model.Account;
import jp.co.yahoo.adsdisplayapi.v16.model.AccountServiceAutoTaggingEnabled;
import jp.co.yahoo.adsdisplayapi.v16.model.AccountServiceDeliveryStatus;
import jp.co.yahoo.adsdisplayapi.v16.model.AccountServiceOperation;
import jp.co.yahoo.adsdisplayapi.v16.model.AccountServiceSelector;
import jp.co.yahoo.adsdisplayapi.v16.model.AccountServiceStatus;
import jp.co.yahoo.adsdisplayapi.v16.model.AccountServiceType;

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
    accountService.accountServiceGetPost(ApiUtils.BASE_ACCOUNT_ID, selector);
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
    accountService.accountServiceSetPost(ApiUtils.BASE_ACCOUNT_ID, operation);
  }

}
