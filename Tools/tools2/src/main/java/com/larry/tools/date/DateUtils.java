package com.larry.tools.date;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DateUtils {
  public static List<Object[]> getYearList(String beginDate, String endDate) {
    List<Object[]> list = new ArrayList<Object[]>();
    int begin = Integer.parseInt(beginDate.substring(0, 4));
    int end = Integer.parseInt(endDate.substring(0, 4));
    while (begin <= end) {
      Object[] obj = new Object[2];
      obj[0] = String.valueOf(begin);
      obj[1] = String.valueOf(begin);
      list.add(obj);
      begin++;
    }
    return list;
  }

  public static List<Object[]> getMonthList(String beginDate, String endDate) {
    List<Object[]> list = new ArrayList<Object[]>();
    Calendar start = Calendar.getInstance();
    Calendar end = Calendar.getInstance();
    start.set(Integer.parseInt(beginDate.substring(0, 4)), Integer.parseInt(beginDate.substring(5, 7)) - 1, 1);
    end.set(Integer.parseInt(endDate.substring(0, 4)), Integer.parseInt(endDate.substring(5, 7)) - 1, 1);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
    while (start.compareTo(end) <= 0) {
      String m = sdf.format(start.getTime());
      Object[] obj = new Object[2];
      obj[0] = m;
      obj[1] = m;
      list.add(obj);
      start.set(Calendar.MONTH, start.get(Calendar.MONTH) + 1);
    }

    return list;
  }

  public static List<Object[]> getDayList(String beginDate, String endDate) {
    List<Object[]> list = new ArrayList<Object[]>();
    Calendar start = Calendar.getInstance();
    Calendar end = Calendar.getInstance();
    start.set(Integer.parseInt(beginDate.substring(0, 4)), Integer.parseInt(beginDate.substring(5, 7)) - 1, Integer.parseInt(beginDate.substring(8, 10)));
    end.set(Integer.parseInt(endDate.substring(0, 4)), Integer.parseInt(endDate.substring(5, 7)) - 1, Integer.parseInt(endDate.substring(8, 10)));
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    while (start.compareTo(end) <= 0) {
      String m = sdf.format(start.getTime());
      Object[] obj = new Object[2];
      obj[0] = m;
      obj[1] = m;
      list.add(obj);
      start.set(Calendar.DATE, start.get(Calendar.DATE) + 1);
    }

    return list;
  }
}
