package com.tridiumap.wechat.responsers;

import javax.baja.nre.annotations.Facet;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BFacets;
import javax.baja.sys.Flags;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

import com.tridiumap.wechat.WxMessage;

/*
 * Responer of WeChat for New User Subscription
 *
 * @author Martin Huang
 * @creation 2019-04-28
 * @version 1.0
 * */

@NiagaraType
@NiagaraProperty(
  name = "command",
  type = "String",
  defaultValue = "\"\"",
  flags = Flags.READONLY | Flags.HIDDEN
)
@NiagaraProperty(
  name = "menu",
  type = "String",
  defaultValue = "\"\"",
  flags = Flags.READONLY | Flags.HIDDEN
)
@NiagaraProperty(
  name = "welcomeMessage",
  type = "String",
  defaultValue = "Welcome!",
  facets = @Facet("BFacets.make(BFacets.MULTI_LINE,true)")
)
public class BWxSubscribeEvent
  extends BAbstractWxResponser
{

/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.tridiumap.wechat.responsers.BWxSubscribeEvent(2415239098)1.0$ @*/
/* Generated Fri Jun 28 16:53:54 CST 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "command"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code command} property.
   * @see #getCommand
   * @see #setCommand
   */
  public static final Property command = newProperty(Flags.READONLY | Flags.HIDDEN, "", null);
  
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
// Property "menu"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code menu} property.
   * @see #getMenu
   * @see #setMenu
   */
  public static final Property menu = newProperty(Flags.READONLY | Flags.HIDDEN, "", null);
  
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
// Property "welcomeMessage"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code welcomeMessage} property.
   * @see #getWelcomeMessage
   * @see #setWelcomeMessage
   */
  public static final Property welcomeMessage = newProperty(0, "Welcome!", BFacets.make(BFacets.MULTI_LINE,true));
  
  /**
   * Get the {@code welcomeMessage} property.
   * @see #welcomeMessage
   */
  public String getWelcomeMessage() { return getString(welcomeMessage); }
  
  /**
   * Set the {@code welcomeMessage} property.
   * @see #welcomeMessage
   */
  public void setWelcomeMessage(String v) { setString(welcomeMessage, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BWxSubscribeEvent.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  @Override
  public String getResponse(WxMessage msgReq)
  {
    String event = msgReq.get("Event");
    if ("subscribe".equals(event))
    {
      return getWelcomeMessage();
    }
    return null;
  }

}
