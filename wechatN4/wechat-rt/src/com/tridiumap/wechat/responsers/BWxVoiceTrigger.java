package com.tridiumap.wechat.responsers;

import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraTopic;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BAbsTime;
import javax.baja.sys.BBoolean;
import javax.baja.sys.Clock;
import javax.baja.sys.Flags;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Topic;
import javax.baja.sys.Type;

import com.tridiumap.wechat.WxMessage;

/*
 * Responser of WeChat Voice Command with A Trigger by Firing A Topic
 *
 * @author Martin Huang
 * @creation 2019-04-28
 * @version 1.0
 * */

@NiagaraType
@NiagaraProperty(
  name = "containString",
  type = "String",
  defaultValue = "\"\"",
  flags = Flags.SUMMARY
)
@NiagaraProperty(
  name = "value",
  type = "boolean",
  defaultValue = "false"
)
/**
 * The time of the last trigger or null is no trigger
 * has ever occurred.
 */
@NiagaraProperty(
  name = "lastTrigger",
  type = "BAbsTime",
  defaultValue = "BAbsTime.NULL",
  flags = Flags.READONLY
)
@NiagaraTopic(
  name = "out",
  eventType = "BBoolean",
  flags = Flags.SUMMARY
)
public class BWxVoiceTrigger
  extends BAbstractWxWriteResponser
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.tridiumap.wechat.responsers.BWxVoiceTrigger(2234280053)1.0$ @*/
/* Generated Fri Jun 28 17:11:32 CST 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "containString"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code containString} property.
   * @see #getContainString
   * @see #setContainString
   */
  public static final Property containString = newProperty(Flags.SUMMARY, "", null);
  
  /**
   * Get the {@code containString} property.
   * @see #containString
   */
  public String getContainString() { return getString(containString); }
  
  /**
   * Set the {@code containString} property.
   * @see #containString
   */
  public void setContainString(String v) { setString(containString, v, null); }

////////////////////////////////////////////////////////////////
// Property "value"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code value} property.
   * @see #getValue
   * @see #setValue
   */
  public static final Property value = newProperty(0, false, null);
  
  /**
   * Get the {@code value} property.
   * @see #value
   */
  public boolean getValue() { return getBoolean(value); }
  
  /**
   * Set the {@code value} property.
   * @see #value
   */
  public void setValue(boolean v) { setBoolean(value, v, null); }

////////////////////////////////////////////////////////////////
// Property "lastTrigger"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code lastTrigger} property.
   * The time of the last trigger or null is no trigger
   * has ever occurred.
   * @see #getLastTrigger
   * @see #setLastTrigger
   */
  public static final Property lastTrigger = newProperty(Flags.READONLY, BAbsTime.NULL, null);
  
  /**
   * Get the {@code lastTrigger} property.
   * The time of the last trigger or null is no trigger
   * has ever occurred.
   * @see #lastTrigger
   */
  public BAbsTime getLastTrigger() { return (BAbsTime)get(lastTrigger); }
  
  /**
   * Set the {@code lastTrigger} property.
   * The time of the last trigger or null is no trigger
   * has ever occurred.
   * @see #lastTrigger
   */
  public void setLastTrigger(BAbsTime v) { set(lastTrigger, v, null); }

////////////////////////////////////////////////////////////////
// Topic "out"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code out} topic.
   * @see #fireOut
   */
  public static final Topic out = newTopic(Flags.SUMMARY, null);
  
  /**
   * Fire an event for the {@code out} topic.
   * @see #out
   */
  public void fireOut(BBoolean event) { fire(out, event, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BWxVoiceTrigger.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  @Override
  public String getResponse(WxMessage msgReq)
  {
    if (getContainString().length() <= 0)
    { return null; }

    if ("voice".equals(msgReq.get("MsgType")))
    {
      String sRec = "";
      String recognition = msgReq.get("Recognition");
      if (recognition == null)
      {
        return "Voice is not supported now!";
      }
      if (recognition.contains(getContainString()) || sRec.contains(getContainString()))
      {
        fireOut(BBoolean.make(getValue()));
        setLastTrigger(Clock.time());
        return getResponse();
      }
    }
    return null;
  }
}
