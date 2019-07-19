package com.tridiumap.wechat.responsers;

import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

/*
 * Abstract Class of WeChat Write Responser
 *
 * @author Martin Huang
 * @creation 2019-04-28
 * @version 1.0
 * */

@NiagaraType
@NiagaraProperty(
  name = "response",
  type = "String",
  defaultValue = "OK"
)

public abstract class BAbstractWxWriteResponser
  extends BAbstractWxResponser
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.tridiumap.wechat.responsers.BAbstractWxWriteResponser(2967740946)1.0$ @*/
/* Generated Fri Apr 26 12:44:15 CST 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "response"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code response} property.
   * @see #getResponse
   * @see #setResponse
   */
  public static final Property response = newProperty(0, "OK", null);
  
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
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BAbstractWxWriteResponser.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

}
