package com.tridiumap.wechat.menu;

import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BIcon;
import javax.baja.sys.BValue;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

import com.tridiumap.wechat.BAsyncComponent;

/*
 * This component is used to create menu container in WeChat
 * Its major responsibility is to configure JSON of WeChat menu
 * Configuration Only
 *
 * @author Martin Huang
 * @creation 2019-04-28
 * @version 1.0
 * */

@NiagaraType
@NiagaraProperty(
  name = "menuName",
  type = "String",
  defaultValue = "input a name"
)
public class BMenuContainer
  extends BAsyncComponent
  implements BIMenu
{

/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.tridiumap.wechat.menu.BMenuContainer(311764378)1.0$ @*/
/* Generated Sat Apr 27 18:20:27 CST 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "menuName"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code menuName} property.
   * @see #getMenuName
   * @see #setMenuName
   */
  public static final Property menuName = newProperty(0, "input a name", null);
  
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
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BMenuContainer.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  private static final BIcon icon = BIcon.std("folder.png");

  public String getJson()
  {
    BIMenu[] subMenus = this.getChildren(BIMenu.class);
    if (subMenus.length == 0)
    { return ""; }

    StringBuffer sb = new StringBuffer();
    buildJsonHead(sb);
    for (int i = 0; i < subMenus.length; i++)
    {
      if (i != 0)
      { sb.append(","); }
      sb.append(subMenus[i].getJson());
    }
    sb.append("]}");
    return sb.toString();
  }

  protected void buildJsonHead(StringBuffer sb)
  {
    sb.append("{\"name\":\"").append(getMenuName().trim()).append("\",");
    sb.append("\"sub_button\":[");
  }

  public BIcon getIcon()
  {
    BValue dynamic = this.get("icon");
    return dynamic instanceof BIcon ? (BIcon) dynamic : icon;
  }
}
