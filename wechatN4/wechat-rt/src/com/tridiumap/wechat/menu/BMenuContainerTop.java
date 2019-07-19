package com.tridiumap.wechat.menu;

import javax.baja.nre.annotations.NiagaraAction;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.Action;
import javax.baja.sys.Flags;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

import com.tridiumap.wechat.BWxService;
import com.tridiumap.wechat.util.HUtil;
import com.tridiumap.wechat.util.HttpClient;

/*
 * This component is used to create WeChat menu
 * It's supposed to including other contents below this component
 *
 * WeChat Interfaces:
 * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141013
 * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141015
 *
 * @author Martin Huang
 * @creation 2019-04-28
 * @version 1.0
 * */

@NiagaraType
@NiagaraProperty(
  name = "menuName",
  type = "String",
  defaultValue = "input a name",
  flags = Flags.HIDDEN
)
@NiagaraProperty(
  name = "faultCause",
  type = "String",
  defaultValue = "",
  flags = Flags.READONLY
)
@NiagaraProperty(
  name = "errorMsg",
  type = "String",
  defaultValue = "",
  flags = Flags.READONLY
)
@NiagaraAction(
  name = "updateMenu",
  flags = Flags.ASYNC
)
@NiagaraAction(
  name = "deleteMenu",
  flags = Flags.ASYNC
)
public class BMenuContainerTop
  extends BMenuContainer
{

/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.tridiumap.wechat.menu.BMenuContainerTop(1727870037)1.0$ @*/
/* Generated Sun Apr 28 11:29:52 CST 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "menuName"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code menuName} property.
   * @see #getMenuName
   * @see #setMenuName
   */
  public static final Property menuName = newProperty(Flags.HIDDEN, "input a name", null);
  
  /**
   * Get the {@code menuName} property.
   * @see #menuName
   */
  public String getMenuName() { return getString(menuName); }
  
  /**
   * Set the {@code menuName} property.
   * @see #menuName
   */
  public void setMenuName(String v) { setString(menuName, v, null); }

////////////////////////////////////////////////////////////////
// Property "faultCause"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code faultCause} property.
   * @see #getFaultCause
   * @see #setFaultCause
   */
  public static final Property faultCause = newProperty(Flags.READONLY, "", null);
  
  /**
   * Get the {@code faultCause} property.
   * @see #faultCause
   */
  public String getFaultCause() { return getString(faultCause); }
  
  /**
   * Set the {@code faultCause} property.
   * @see #faultCause
   */
  public void setFaultCause(String v) { setString(faultCause, v, null); }

////////////////////////////////////////////////////////////////
// Property "errorMsg"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code errorMsg} property.
   * @see #getErrorMsg
   * @see #setErrorMsg
   */
  public static final Property errorMsg = newProperty(Flags.READONLY, "", null);
  
  /**
   * Get the {@code errorMsg} property.
   * @see #errorMsg
   */
  public String getErrorMsg() { return getString(errorMsg); }
  
  /**
   * Set the {@code errorMsg} property.
   * @see #errorMsg
   */
  public void setErrorMsg(String v) { setString(errorMsg, v, null); }

////////////////////////////////////////////////////////////////
// Action "updateMenu"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code updateMenu} action.
   * @see #updateMenu()
   */
  public static final Action updateMenu = newAction(Flags.ASYNC, null);
  
  /**
   * Invoke the {@code updateMenu} action.
   * @see #updateMenu
   */
  public void updateMenu() { invoke(updateMenu, null, null); }

////////////////////////////////////////////////////////////////
// Action "deleteMenu"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code deleteMenu} action.
   * @see #deleteMenu()
   */
  public static final Action deleteMenu = newAction(Flags.ASYNC, null);
  
  /**
   * Invoke the {@code deleteMenu} action.
   * @see #deleteMenu
   */
  public void deleteMenu() { invoke(deleteMenu, null, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BMenuContainerTop.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  private boolean isExecuting;

  public void doUpdateMenu()
  {
    if (isExecuting)
    { return; }

    isExecuting = true;
    setErrorMsg("");

    try
    {
      String sMenu = getJson();
      if (sMenu == null || sMenu.length() == 0)
      {
        return;
      }
      String sToken = ((BWxService) getParent()).getAccessToken().getAccessToken();
      String sUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + sToken;
      String sRes = HttpClient.Send(HttpClient.ACTION_POST, sUrl, sMenu, null, null);
      String sStatus = HUtil.getInnerText(sRes, "\"errmsg\":", "\"");

      this.setErrorMsg(sStatus);

    }
    catch (Exception e)
    {
    }
    finally
    {
      isExecuting = false;
    }
  }

  public void doDeleteMenu()
  {
    if (isExecuting)
    { return; }

    isExecuting = true;
    setErrorMsg("");

    try
    {
      String sToken = ((BWxService) getParent()).getAccessToken().getAccessToken();
      String sUrl = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=" + sToken;
      String sRes = HttpClient.Send(HttpClient.ACTION_GET, sUrl, "", null, null);
      String sStatus = HUtil.getInnerText(sRes, "\"errmsg\":", "\"");

      setErrorMsg(sStatus);

    }
    catch (Exception e)
    {
    }
    finally
    {
      isExecuting = false;
    }
  }

  protected void buildJsonHead(StringBuffer sb)
  {
    sb.append("{\"button\":[");
  }

}
