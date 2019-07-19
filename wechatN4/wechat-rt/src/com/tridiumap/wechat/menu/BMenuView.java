package com.tridiumap.wechat.menu;

import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

/*
 * This component is used to create hyperlink in WeChat menu
 * Configuration Only (not related to communication)
 *
 * @author Martin Huang
 * @creation 2019-04-28
 * @version 1.0
 * */

@NiagaraType
@NiagaraProperty(
  name = "url",
  type = "String",
  defaultValue = "http://www.tridium.cn"
)
public class BMenuView
  extends BAbstractMenu
{

/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.tridiumap.wechat.menu.BMenuView(96917290)1.0$ @*/
/* Generated Sat Apr 27 18:20:27 CST 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "url"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code url} property.
   * @see #getUrl
   * @see #setUrl
   */
  public static final Property url = newProperty(0, "http://www.tridium.cn", null);
  
  /**
   * Get the {@code url} property.
   * @see #url
   */
  public String getUrl() { return getString(url); }
  
  /**
   * Set the {@code url} property.
   * @see #url
   */
  public void setUrl(String v) { setString(url, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BMenuView.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  @Override
  public String getJson()
  {
    StringBuilder sb = new StringBuilder();
    sb.append("{\"type\":\"view\",");
    sb.append("\"name\":\"").append(getMenuName().trim()).append("\",");
    sb.append("\"url\":\"").append(getUrl().trim()).append("\"}");

    return sb.toString();
  }

}
