package com.tridiumap.wechat.responsers;

import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BComponent;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

import com.tridiumap.wechat.WxMessage;

/*
 * Abstract Class of WeChat Responser
 *
 * @author Martin Huang
 * @creation 2019-04-28
 * @version 1.0
 * */

@NiagaraType
@NiagaraProperty(
  name = "command",
  type = "String",
  defaultValue = "\"\""
)
@NiagaraProperty(
  name = "eventKey",
  type = "String",
  defaultValue = "\"\""
)
@NiagaraProperty(
  name = "menu",
  type = "String",
  defaultValue = "\"\""
)
public abstract class BAbstractWxResponser
  extends BComponent
{

/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.tridiumap.wechat.responsers.BAbstractWxResponser(444194625)1.0$ @*/
/* Generated Fri Jun 28 16:35:44 CST 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "command"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code command} property.
   * @see #getCommand
   * @see #setCommand
   */
  public static final Property command = newProperty(0, "", null);
  
  /**
   * Get the {@code command} property.
   * @see #command
   */
  public String getCommand() { return getString(command); }
  
  /**
   * Set the {@code command} property.
   * @see #command
   */
  public void setCommand(String v) { setString(command, v, null); }

////////////////////////////////////////////////////////////////
// Property "eventKey"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code eventKey} property.
   * @see #getEventKey
   * @see #setEventKey
   */
  public static final Property eventKey = newProperty(0, "", null);
  
  /**
   * Get the {@code eventKey} property.
   * @see #eventKey
   */
  public String getEventKey() { return getString(eventKey); }
  
  /**
   * Set the {@code eventKey} property.
   * @see #eventKey
   */
  public void setEventKey(String v) { setString(eventKey, v, null); }

////////////////////////////////////////////////////////////////
// Property "menu"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code menu} property.
   * @see #getMenu
   * @see #setMenu
   */
  public static final Property menu = newProperty(0, "", null);
  
  /**
   * Get the {@code menu} property.
   * @see #menu
   */
  public String getMenu() { return getString(menu); }
  
  /**
   * Set the {@code menu} property.
   * @see #menu
   */
  public void setMenu(String v) { setString(menu, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BAbstractWxResponser.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  /**
   * getResponse
   *
   * @param msgReq
   * @return String
   * return null if you can't recognize.
   * return "" if you no need to response
   */
  public abstract String getResponse(WxMessage msgReq);

}
