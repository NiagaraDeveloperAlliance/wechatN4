package com.tridiumap.wechat.req;

import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BBoolean;
import javax.baja.sys.Flags;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

import com.tridiumap.wechat.WxMessage;

/*
 * WeChat Voice Trigger Request
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
public class BWxVoiceTriggerReq
  extends BAbstractWxReq
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.tridiumap.wechat.req.BWxVoiceTriggerReq(2227237691)1.0$ @*/
/* Generated Fri Jun 28 16:31:03 CST 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

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
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BWxVoiceTriggerReq.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  @Override
  public boolean isMyTurn(WxMessage msg)
  {
    if ("voice".equals(msg.get("MsgType")))
    {
      String recognition = msg.get("Recognition");
      if (recognition != null && recognition.contains(getContainString()))
      {
        return true;
      }
    }
    return false;
  }

  public Object getParameter()
  {
    return BBoolean.make(getValue());
  }

}
