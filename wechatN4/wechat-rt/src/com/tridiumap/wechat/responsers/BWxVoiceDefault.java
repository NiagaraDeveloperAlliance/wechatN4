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
 * Default Responser of WeChat Voice Command
 *
 * @author Martin Huang
 * @creation 2019-04-28
 * @version 1.0
 * */

@NiagaraType
@NiagaraProperty(
  name = "defaultResponse",
  type = "String",
  defaultValue = "You said %SAY%, right?",
  facets = @Facet("BFacets.make(BFacets.MULTI_LINE,true)")
)
public class BWxVoiceDefault
  extends BAbstractWxResponser
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.tridiumap.wechat.responsers.BWxVoiceDefault(3857422608)1.0$ @*/
/* Generated Sat Apr 27 17:53:21 CST 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "defaultResponse"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code defaultResponse} property.
   * @see #getDefaultResponse
   * @see #setDefaultResponse
   */
  public static final Property defaultResponse = newProperty(0, "You said %SAY%, right?", BFacets.make(BFacets.MULTI_LINE,true));
  
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
  public static final Type TYPE = Sys.loadType(BWxVoiceDefault.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  @Override
  public String getResponse(WxMessage msgReq)
  {
    if ("voice".equals(msgReq.get("MsgType")))
    {
      String sRes = getDefaultResponse();
      sRes = sRes.replaceAll("%SAY%", msgReq.get("Recognition"));
      return sRes;
    }
    return null;
  }

}
