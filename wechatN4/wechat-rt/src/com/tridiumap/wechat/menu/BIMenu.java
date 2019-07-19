package com.tridiumap.wechat.menu;

import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BInterface;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

/*
 * Menu Interface
 *
 * @author Martin Huang
 * @creation 2019-04-28
 * @version 1.0
 * */

@NiagaraType
public interface BIMenu extends BInterface
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.tridiumap.wechat.menu.BIMenu(2979906276)1.0$ @*/
/* Generated Mon Jun 24 16:37:13 CST 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  Type TYPE = Sys.loadType(BIMenu.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  String getJson();

}
