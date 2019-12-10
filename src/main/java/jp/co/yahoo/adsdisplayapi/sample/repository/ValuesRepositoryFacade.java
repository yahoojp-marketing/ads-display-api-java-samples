/**
 * Copyright (C) 2019 Yahoo Japan Corporation. All Rights Reserved.
 */
package jp.co.yahoo.adsdisplayapi.sample.repository;

import jp.co.yahoo.adsdisplayapi.sample.util.ValuesHolder;

/**
 * Utility method collection for Java Sample Program.
 */
public class ValuesRepositoryFacade {

  private ValuesHolder valuesHolder;
  private ReportDefinitionValuesRepository reportDefinitionValuesRepository;

  /**
   * ValuesRepositoryFacade constructor.
   *
   * @param valuesHolder ValuesHolder
   */
  public ValuesRepositoryFacade(ValuesHolder valuesHolder) {
    this.valuesHolder = valuesHolder;
    this.reportDefinitionValuesRepository = new ReportDefinitionValuesRepository(this.valuesHolder);
  }

  /**
   * @return ValuesHolder
   */
  public ValuesHolder getValuesHolder() {
    return valuesHolder;
  }

  /**
   * @return ReportDefinitionValuesRepository
   */
  public ReportDefinitionValuesRepository getReportDefinitionValuesRepository() {
    return reportDefinitionValuesRepository;
  }

}
