package com.tridiumap.wechat.actions;

import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

import com.tridiumap.wechat.WxMessage;

/*
 * Get Response Text Action
 *
 * @author Martin Huang
 * @creation 2019-04-28
 * @version 1.0
 * */

@NiagaraType
public class BActionTextResponse
  extends BAbstractWxAction
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.tridiumap.wechat.actions.BActionTextResponse(2979906276)1.0$ @*/
/* Generated Sat Apr 27 18:13:32 CST 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BActionTextResponse.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  public String execute(WxMessage msg)
  {
    String s = getResponse();
    return s;
  }

}
