package com.tridiumap.wechat.actions;

import javax.baja.nre.annotations.Facet;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraTopic;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BBoolean;
import javax.baja.sys.BFacets;
import javax.baja.sys.Flags;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Topic;
import javax.baja.sys.Type;

import com.tridiumap.wechat.WxMessage;

/*
 * Set Boolean Data Action
 *
 * @author Martin Huang
 * @creation 2019-04-28
 * @version 1.0
 * */

@NiagaraType
@NiagaraProperty(
  name = "value",
  type = "boolean",
  defaultValue = "false",
  flags = Flags.SUMMARY
)
@NiagaraProperty(
  name = "response",
  type = "String",
  defaultValue = "",
  facets = @Facet("BFacets.make(BFacets.MULTI_LINE,true)")
)
@NiagaraTopic(
  name = "out",
  eventType = "BBoolean",
  flags = Flags.SUMMARY
)
public class BActionSetBoolean
  extends BAbstractWxAction
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.tridiumap.wechat.actions.BActionSetBoolean(2544875160)1.0$ @*/
/* Generated Sat Apr 27 18:13:32 CST 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "value"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code value} property.
   * @see #getValue
   * @see #setValue
   */
  public static final Property value = newProperty(Flags.SUMMARY, false, null);
  
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
// Property "response"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code response} property.
   * @see #getResponse
   * @see #setResponse
   */
  public static final Property response = newProperty(0, "", BFacets.make(BFacets.MULTI_LINE,true));
  
  /**
   * Get the {@code response} property.
   * @see #response
   */
  public String getResponse() { return getString(response); }
  
  /**
   * Set the {@code response} property.
   * @see #response
   */
  public void setResponse(String v) { setString(response, v, null); }

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
  public static final Type TYPE = Sys.loadType(BActionSetBoolean.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  @Override
  public String execute(WxMessage msg)
  {
    boolean val = getValue();
    Object para = msg.getTag();
    if (para instanceof Boolean)
    {
      val = ((Boolean) para).booleanValue();
    }
    fireOut(BBoolean.make(val));

    return getResponse();
  }

}