/**
 * Copyright (C) 2020 Yahoo Japan Corporation. All Rights Reserved.
 */
package jp.co.yahoo.adsdisplayapi.sample.repository;

import java.util.ArrayList;
import java.util.List;
import jp.co.yahoo.adsdisplayapi.sample.util.ValuesHolder;
import jp.co.yahoo.adsdisplayapi.v4.model.ReportDefinition;
import jp.co.yahoo.adsdisplayapi.v4.model.ReportDefinitionServiceValue;

/**
 * Utility method collection for Java Sample Program.
 */
public class ReportDefinitionValuesRepository {

  private ValuesHolder valuesHolder;

  /**
   * ReportDefinitionValuesRepository constructor.
   *
   * @param valuesHolder ValuesHolder
   */
  public ReportDefinitionValuesRepository(ValuesHolder valuesHolder) {
    this.valuesHolder = valuesHolder;
  }

  /**
   * @return ReportDefinitions
   */
  public List<ReportDefinition> getReportDefinitions() {
    List<ReportDefinition> reportDefinitions = new ArrayList<>();
    if (this.valuesHolder.getReportDefinitionValuesList().size() == 0) {
      return reportDefinitions;
    }
    for (ReportDefinitionServiceValue reportDefinitionValues : this.valuesHolder.getReportDefinitionValuesList()) {
      reportDefinitions.add(reportDefinitionValues.getReportDefinition());
    }
    return reportDefinitions;
  }

  /**
   * @return ReportIds
   */
  public List<Long> getReportJobIds() {
    List<Long> reportJobIds = new ArrayList<>();
    if (this.valuesHolder.getReportDefinitionValuesList().size() == 0) {
      return reportJobIds;
    }
    for (ReportDefinitionServiceValue reportDefinitionValues : this.valuesHolder.getReportDefinitionValuesList()) {
      reportJobIds.add(reportDefinitionValues.getReportDefinition().getReportJobId());
    }
    return reportJobIds;
  }
}
