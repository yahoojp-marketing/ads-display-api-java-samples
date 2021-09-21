/**
 * Copyright (C) 2020 Yahoo Japan Corporation. All Rights Reserved.
 */
package jp.co.yahoo.adsdisplayapi.sample.util;

import java.util.ArrayList;
import java.util.List;
import jp.co.yahoo.adsdisplayapi.v6.model.ReportDefinitionServiceValue;

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
