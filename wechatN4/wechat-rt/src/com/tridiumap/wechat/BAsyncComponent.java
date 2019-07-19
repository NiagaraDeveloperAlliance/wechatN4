package com.tridiumap.wechat;

import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.Action;
import javax.baja.sys.BComponent;
import javax.baja.sys.BValue;
import javax.baja.sys.Context;
import javax.baja.sys.Flags;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.util.IFuture;
import javax.baja.util.Invocation;

/*
 * Component to Support Post Async Action
 *
 * @author Martin Huang
 * @creation 2019-04-28
 * @version 1.0
 * */

@NiagaraType
public class BAsyncComponent
  extends BComponent
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.tridiumap.wechat.BAsyncComponent(2979906276)1.0$ @*/
/* Generated Fri Apr 26 14:56:23 CST 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BAsyncComponent.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  public IFuture post(Action action, BValue arg, Context cx)
  {
    BWxWorker worker = getWorker();
    if (isRunning() && Sys.atSteadyState() && (getFlags(action) & Flags.ASYNC) == Flags.ASYNC)
    {
      if (worker != null) // if service installed, use service's thread pool
      { worker.postAsync(new Invocation(this, action, arg, cx)); }
      else // if service not installed, use single thread, occupy more resource and out of control
      { (new Thread(new Invocation(this, action, arg, cx))).start(); }
      return null;
    }
    return super.post(action, arg, cx);
  }

  private BWxWorker getWorker()
  {
    BWxService ws = (BWxService) Sys.getService(BWxService.TYPE);
    if (ws == null) { return null; }
    return ws.getWorker();
  }

}
