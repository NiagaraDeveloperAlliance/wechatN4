package com.tridiumap.wechat.util;

public class HUtil
{

  public static String getInnerText(String s, String sLeft, String sRight)
  {
    int sequenceBegin = s.indexOf(sLeft);
    int sequenceEnd = s.indexOf(sRight, sequenceBegin + sLeft.length());
    if (sequenceBegin < 0 || sequenceEnd < sequenceBegin)
    { return ""; }
    return s.substring(sequenceBegin + sLeft.length(), sequenceEnd);
  }

  public static String getRightText(String s, String sLeft)
  {
    int sequenceBegin = s.indexOf(sLeft);
    if (sequenceBegin < 0)
    { return ""; }
    return s.substring(sequenceBegin + sLeft.length());
  }

  public static boolean isNumeric(String str)
  {
    for (int i = 0; i < str.length(); i++)
    {
      System.out.println(str.charAt(i));
      if (!Character.isDigit(str.charAt(i)))
      {
        return false;
      }
    }
    return true;
  }
}
