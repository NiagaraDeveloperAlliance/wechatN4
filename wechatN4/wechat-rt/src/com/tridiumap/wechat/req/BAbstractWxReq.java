package com.tridiumap.wechat.req;

import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BComponent;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

import com.tridiumap.wechat.WxMessage;
import com.tridiumap.wechat.actions.BAbstractWxAction;

/*
 * Abstract Class of WeChat Request
 *
 * @author Martin Huang
 * @creation 2019-04-28
 * @version 1.0
 * */

@NiagaraType
public abstract class BAbstractWxReq
  extends BComponent
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.tridiumap.wechat.req.BAbstractWxReq(2979906276)1.0$ @*/
/* Generated Sat Apr 27 18:12:56 CST 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BAbstractWxReq.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  public boolean isParentLegal(BComponent parent)
  {
    return (parent instanceof BRequestContainer);
  }

  public String execute(WxMessage msg)
  {
    if (isMyTurn(msg)) { return null; }

    StringBuilder sb = new StringBuilder();
    String tmp;
    BAbstractWxAction[] actions = getChildren(BAbstractWxAction.class);
    for (int i = 0; i < actions.length; i++)
    {
      tmp = actions[i].execute(msg);
      if (tmp != null)
      {
        sb.append(tmp);
      }
    }
    return sb.toString();
  }

  // if need parameter, must setParameter before return
  public abstract boolean isMyTurn(WxMessage msg);

  // for WxActions
  public Object getParameter()
  {
    return null;
  }
}
