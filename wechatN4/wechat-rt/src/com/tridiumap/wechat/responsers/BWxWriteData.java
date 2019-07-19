package com.tridiumap.wechat.responsers;

import javax.baja.control.BBooleanWritable;
import javax.baja.control.BEnumWritable;
import javax.baja.control.BNumericWritable;
import javax.baja.control.BStringWritable;
import javax.baja.naming.BOrd;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BBoolean;
import javax.baja.sys.BDouble;
import javax.baja.sys.BDynamicEnum;
import javax.baja.sys.BObject;
import javax.baja.sys.BString;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

import com.tridiumap.wechat.WxMessage;
import com.tridiumap.wechat.util.HUtil;

/*
 * Responser of WeChat with Write Data Action
 *
 * @author Martin Huang
 * @creation 2019-04-28
 * @version 1.0
 * */

@NiagaraType
@NiagaraProperty(
  name = "dataOrd",
  type = "BOrd",
  defaultValue = "BOrd.DEFAULT"
)
public class BWxWriteData
  extends BAbstractWxWriteResponser
{

/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.tridiumap.wechat.responsers.BWxWriteData(2653778162)1.0$ @*/
/* Generated Sat Apr 27 18:10:56 CST 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "dataOrd"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code dataOrd} property.
   * @see #getDataOrd
   * @see #setDataOrd
   */
  public static final Property dataOrd = newProperty(0, BOrd.DEFAULT, null);
  
  /**
   * Get the {@code dataOrd} property.
   * @see #dataOrd
   */
  public BOrd getDataOrd() { return (BOrd)get(dataOrd); }
  
  /**
   * Set the {@code dataOrd} property.
   * @see #dataOrd
   */
  public void setDataOrd(BOrd v) { set(dataOrd, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BWxWriteData.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  @Override
  public String getResponse(WxMessage msgReq)
  {
    String content = msgReq.getContent();
    String lowCmd = getCommand().toLowerCase();
    if (content != null && content.toLowerCase().startsWith(lowCmd))
    {
      String sVal = HUtil.getRightText(content, getCommand());
      if (sVal == null)
      { return "No value input."; }
      sVal = sVal.trim();
      return writeValue(sVal);
    }
    return null;
  }

  private String writeValue(String sVal)
  {
    BOrd ord = getDataOrd();
    if (ord == null) { return "data not configured."; }
    BObject obj = ord.resolve(this).get();
    if (obj instanceof BNumericWritable)
    {
      BNumericWritable point = (BNumericWritable) obj;
      return setDouble(point, sVal);
    }
    else if (obj instanceof BBooleanWritable)
    {
      BBooleanWritable point = (BBooleanWritable) obj;
      return setBoolean(point, sVal);
    }
    else if (obj instanceof BEnumWritable)
    {
      BEnumWritable point = (BEnumWritable) obj;
      return setEnum(point, sVal);
    }
    else if (obj instanceof BStringWritable)
    {
      BStringWritable point = (BStringWritable) obj;
      return setStr(point, sVal);
    }
    return "fail: not a BIWritablePoint point";
  }

  private String setStr(BStringWritable point, String sVal)
  {
    point.set(BString.make(sVal));
    return getResponse() + sVal;
  }

  private String setEnum(BEnumWritable point, String sVal)
  {
    BDynamicEnum val = null;
    try
    {
      if (HUtil.isNumeric(sVal))
      { val = BDynamicEnum.make(Integer.parseInt(sVal)); }

      if (val != null)
      {
        point.set(val);
        return getResponse() + sVal;
      }
      else
      { return "Fail: Not able to parse value " + sVal; }

    }
    catch (Exception e)
    {
    }
    return null;
  }

  private String setBoolean(BBooleanWritable point, String sVal)
  {
    try
    {
      if ("true".equalsIgnoreCase(sVal))
      {
        point.set(BBoolean.make(true));
        return getResponse() + sVal;
      }
      else if ("false".equalsIgnoreCase(sVal))
      {
        point.set(BBoolean.make(false));
        return getResponse() + sVal;
      }
      else
      {
        int val = Integer.parseInt(sVal);
        point.set(BBoolean.make(val != 0));
        return getResponse() + sVal;
      }
    }
    catch (Exception e)
    {
      return "Fail:" + e.getMessage();
    }
  }

  private String setDouble(BNumericWritable nw, String sVal)
  {
    try
    {
      double val = Double.parseDouble(sVal);
      nw.set(BDouble.make(val));
      return getResponse() + sVal;
    }
    catch (Exception e)
    {
      return "Fail:" + e.getMessage();
    }
  }
}
