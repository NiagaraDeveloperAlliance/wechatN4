package com.tridiumap.wechat.menu;

import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

/*
 * This Component is used to create menus with event in WeChat
 * It will pass the key (ID) of the configured JSON event to Niagara
 * Configuration Only
 *
 * @author Martin Huang
 * @creation 2019-04-28
 * @version 1.0
 * */

@NiagaraType
@NiagaraProperty(
  name = "menuType",
  type = "String",
  defaultValue = "click"
)
@NiagaraProperty(
  name = "key",
  type = "String",
  defaultValue = "C0001"
)
public class BMenuClick
  extends BAbstractMenu
{
  
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.tridiumap.wechat.menu.BMenuClick(1016571202)1.0$ @*/
/* Generated Sat Apr 27 18:20:27 CST 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "menuType"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code menuType} property.
   * @see #getMenuType
   * @see #setMenuType
   */
  public static final Property menuType = newProperty(0, "click", null);
  
  /**
   * Get the {@code menuType} property.
   * @see #menuType
   */
  public String getMenuType() { return getString(menuType); }
  
  /**
   * Set the {@code menuType} property.
   * @see #menuType
   */
  public void setMenuType(String v) { setString(menuType, v, null); }

////////////////////////////////////////////////////////////////
// Property "key"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code key} property.
   * @see #getKey
   * @see #setKey
   */
  public static final Property key = newProperty(0, "C0001", null);
  
  /**
   * Get the {@code key} property.
   * @see #key
   */
  public String getKey() { return getString(key); }
  
  /**
   * Set the {@code key} property.
   * @see #key
   */
  public void setKey(String v) { setString(key, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BMenuClick.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  @Override
  public String getJson()
  {
    StringBuffer sb = new StringBuffer();
    sb.append("{\"type\":\"").append(getMenuType()).append("\",");
    sb.append("\"name\":\"").append(getMenuName().trim()).append("\",");
    sb.append("\"key\":\"").append(getKey().trim()).append("\"}");

    return sb.toString();
  }

}
