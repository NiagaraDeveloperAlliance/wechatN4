package com.tridiumap.wechat.actions;

import javax.baja.nre.annotations.Facet;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BComponent;
import javax.baja.sys.BFacets;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

import com.tridiumap.wechat.WxMessage;
import com.tridiumap.wechat.req.BAbstractWxReq;

/*
 * Action Abstract Class
 *
 * @author Martin Huang
 * @creation 2019-04-28
 * @version 1.0
 * */

@NiagaraType
@NiagaraProperty(
  name = "response",
  type = "String",
  defaultValue = "...%PARA%...",
  facets = @Facet("BFacets.make(BFacets.MULTI_LINE,true)")
)
public abstract class BAbstractWxAction
  extends BComponent
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.tridiumap.wechat.actions.BAbstractWxAction(443703565)1.0$ @*/
/* Generated Sat Apr 27 18:13:32 CST 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "response"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code response} property.
   * @see #getResponse
   * @see #setResponse
   */
  public static final Property response = newProperty(0, "...%PARA%...", BFacets.make(BFacets.MULTI_LINE,true));
  
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
  public static final Type TYPE = Sys.loadType(BAbstractWxAction.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  public abstract String execute(WxMessage msg);

  public boolean isParentLegal(BComponent parent)
  {
    return (parent instanceof BAbstractWxReq);
  }

}
