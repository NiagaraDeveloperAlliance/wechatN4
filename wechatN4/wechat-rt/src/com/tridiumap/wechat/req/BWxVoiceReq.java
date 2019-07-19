package com.tridiumap.wechat.req;

import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

import com.tridiumap.wechat.WxMessage;

/*
 * WeChat Voice Request
 *
 * @author Martin Huang
 * @creation 2019-04-28
 * @version 1.0
 * */

@NiagaraType
@NiagaraProperty(
  name = "value",
  type = "String",
  defaultValue = "\"\""
)
public class BWxVoiceReq
  extends BAbstractWxReq
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.tridiumap.wechat.req.BWxVoiceReq(3273149361)1.0$ @*/
/* Generated Fri Jun 28 16:31:32 CST 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "value"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code value} property.
   * @see #getValue
   * @see #setValue
   */
  public static final Property value = newProperty(0, "", null);
  
  /**
   * Get the {@code value} property.
   * @see #value
   */
  public String getValue() { return getString(value); }
  
  /**
   * Set the {@code value} property.
   * @see #value
   */
  public void setValue(String v) { setString(value, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BWxVoiceReq.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  @Override
  public boolean isMyTurn(WxMessage msg)
  {
    return "voice".equals(msg.get("MsgType"));
  }

  public Object getParameter()
  {
    return getValue();
  }

}
