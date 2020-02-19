/**
 * Copyright (C) 2019 Yahoo Japan Corporation. All Rights Reserved.
 */
package jp.co.yahoo.adsdisplayapi.sample.util;

import jp.co.yahoo.adsdisplayapi.v1.model.ReportDefinitionServiceValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility method collection for Java Sample Program.
 */
public class ValuesHolder {

  private List<ReportDefinitionServiceValue> reportDefinitionValuesList = new ArrayList<>();

  /**
   * @return ReportDefinitionValueList
   */
  public List<ReportDefinitionServiceValue> getReportDefinitionValuesList()
  {
    return this.reportDefinitionValuesList;
  }

  /**
   * @param reportDefinitionValuesList ReportDefinitionValuesList
   */
  public void setReportDefinitionValuesList(List<ReportDefinitionServiceValue> reportDefinitionValuesList)
  {
    this.reportDefinitionValuesList.addAll(reportDefinitionValuesList);
  }


}
