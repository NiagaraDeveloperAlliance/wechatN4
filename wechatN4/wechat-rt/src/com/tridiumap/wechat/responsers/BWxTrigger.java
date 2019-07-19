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
 * Responser of WeChat with A Trigger by Firing A Topic
 *
 * @author Martin Huang
 * @creation 2019-04-28
 * @version 1.0
 * */

@NiagaraType
@NiagaraProperty(
  name = "value",
  type = "boolean",
  defaultValue = "BBoolean.FALSE"
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
public class BWxTrigger
  extends BAbstractWxWriteResponser
{

/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.tridiumap.wechat.responsers.BWxTrigger(2387847345)1.0$ @*/
/* Generated Fri Jun 28 16:53:54 CST 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "value"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code value} property.
   * @see #getValue
   * @see #setValue
   */
  public static final Property value = newProperty(0, BBoolean.FALSE, null);
  
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
  public static final Type TYPE = Sys.loadType(BWxTrigger.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  @Override
  public String getResponse(WxMessage msgReq)
  {
    String cmd = getCommand();
    String eventKey = getEventKey();
    if (cmd.isEmpty() || eventKey.isEmpty())
    { return null; }

    if (cmd.equals(msgReq.getContent()) || eventKey.equals(msgReq.get("EventKey")))
    {
      fireOut(BBoolean.make(getValue()));
      setLastTrigger(Clock.time());
      return getResponse();
    }

    return null;
  }
}
