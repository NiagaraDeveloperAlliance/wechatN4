package com.tridiumap.wechat.menu;

import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BComponent;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

/*
 * Menu Abstract Class
 * To Define Menu Name
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
public abstract class BAbstractMenu
  extends BComponent
  implements BIMenu
{
  
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.tridiumap.wechat.menu.BAbstractMenu(311764378)1.0$ @*/
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
  public static final Type TYPE = Sys.loadType(BAbstractMenu.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

}
