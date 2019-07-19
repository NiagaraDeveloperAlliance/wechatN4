package com.tridiumap.wechat.responsers;

import javax.baja.nre.annotations.Facet;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BFacets;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

import com.tridiumap.wechat.WxMessage;

/*
 * Responer of WeChat
 *
 * @author Martin Huang
 * @creation 2019-04-28
 * @version 1.0
 * */

@NiagaraType
@NiagaraProperty(
  name = "defaultResponse",
  type = "String",
  defaultValue = "\"\"",
  facets = {@Facet(name = "BFacets.MULTI_LINE", value = "true")}
)

public class BWxResponseOnly
  extends BAbstractWxResponser
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.tridiumap.wechat.responsers.BWxResponseOnly(3343697077)1.0$ @*/
/* Generated Fri Apr 26 23:04:25 CST 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "defaultResponse"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code defaultResponse} property.
   * @see #getDefaultResponse
   * @see #setDefaultResponse
   */
  public static final Property defaultResponse = newProperty(0, "", BFacets.make(BFacets.MULTI_LINE, true));
  
  /**
   * Get the {@code defaultResponse} property.
   * @see #defaultResponse
   */
  public String getDefaultResponse() { return getString(defaultResponse); }
  
  /**
   * Set the {@code defaultResponse} property.
   * @see #defaultResponse
   */
  public void setDefaultResponse(String v) { setString(defaultResponse, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BWxResponseOnly.class);

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
      return getDefaultResponse();
    }
    return null;
  }
}
