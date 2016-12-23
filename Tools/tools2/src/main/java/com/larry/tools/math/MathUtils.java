package com.larry.tools.math;

import java.math.BigDecimal;

/**
 * <p>Title: java中的加减乘除</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: </p>
 * @author: zhaogang
 * @version 2.0
 */
public class MathUtils {

  /**
   * 实现四舍五入,返回指定小数位数的string型
   */
  public static String round(BigDecimal dSource, int scale) {
    BigDecimal one = new BigDecimal("1");
    return String.valueOf(dSource.divide(one, scale, BigDecimal.ROUND_HALF_UP));
  }
  
  /**
   * 两个数相加
   */
  public static String add(String v1, String v2, int scale) {
    v1 = toZero(v1);
    v2 = toZero(v2);
    BigDecimal b1 = new BigDecimal(v1);
    BigDecimal b2 = new BigDecimal(v2);
    return round(b1.add(b2), scale);
  }
  
  /**
   * 两个数相减
   */
  public static String sub(String v1, String v2, int scale) {
    v1 = toZero(v1);
    v2 = toZero(v2);
    BigDecimal b1 = new BigDecimal(v1);
    BigDecimal b2 = new BigDecimal(v2);
    return round(b1.subtract(b2), scale);
  }

  /**
   * 两个数相乘
   */
  public static String mul(String v1, String v2, int scale) {
    v1 = toZero(v1);
    v2 = toZero(v2);
    BigDecimal b1 = new BigDecimal(v1);
    BigDecimal b2 = new BigDecimal(v2);
    return round(b1.multiply(b2), scale);
  }
  
  /**
   * 三个数相乘
   */
  public static String mul(String v1, String v2, String v3, int scale) {
    v1 = toZero(v1);
    v2 = toZero(v2);
    v3 = toZero(v3);
    BigDecimal b1 = new BigDecimal(v1);
    BigDecimal b2 = new BigDecimal(v2);
    BigDecimal b3 = new BigDecimal(v3);
    return round(b1.multiply(b2).multiply(b3), scale);
  }
  
  /**
   * 四个数相乘
   */
  public static String mul(String v1, String v2, String v3, String v4, int scale) {
    v1 = toZero(v1);
    v2 = toZero(v2);
    v3 = toZero(v3);
    v4 = toZero(v4);
    BigDecimal b1 = new BigDecimal(v1);
    BigDecimal b2 = new BigDecimal(v2);
    BigDecimal b3 = new BigDecimal(v3);
    BigDecimal b4 = new BigDecimal(v4);
    return round(b1.multiply(b2).multiply(b3).multiply(b4), scale);
  }
  
  /**
   * 两个数相除
   */
  public static String div(String v1, String v2, int scale) {
    v1 = toZero(v1);
    v2 = toZero(v2);
    
    BigDecimal b1 = new BigDecimal(v1);
    BigDecimal b2 = new BigDecimal(v2);
    if (b2.doubleValue() == 0) {
      b1 = new BigDecimal("0.0");
      b2 = new BigDecimal("1.0");
    }
    return round(b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP), scale);
  }
  
  public static String toZero(String v) {
    if (v == null || v.trim().length() <= 0) return "0";
    else return v;
  }
}
