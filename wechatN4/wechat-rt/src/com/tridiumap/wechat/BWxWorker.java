package com.tridiumap.wechat;

import javax.baja.nre.annotations.Facet;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.spy.SpyWriter;
import javax.baja.sys.BFacets;
import javax.baja.sys.Context;
import javax.baja.sys.NotRunningException;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.util.BThreadPoolWorker;
import javax.baja.util.CoalesceQueue;
import javax.baja.util.IFuture;
import javax.baja.util.ThreadPoolWorker;
import javax.baja.util.Worker;

@NiagaraType
@NiagaraProperty(
  name = "maxQueueSize",
  type = "int",
  defaultValue = "1000",
  facets = {@Facet(name = "BFacets.MIN", value = "1")}
)
public class BWxWorker
  extends BThreadPoolWorker
{

/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.tridiumap.wechat.BWxWorker(3102148003)1.0$ @*/
/* Generated Fri Apr 26 14:43:58 CST 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "maxQueueSize"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code maxQueueSize} property.
   * @see #getMaxQueueSize
   * @see #setMaxQueueSize
   */
  public static final Property maxQueueSize = newProperty(0, 1000, BFacets.make(BFacets.MIN, 1));
  
  /**
   * Get the {@code maxQueueSize} property.
   * @see #maxQueueSize
   */
  public int getMaxQueueSize() { return getInt(maxQueueSize); }
  
  /**
   * Set the {@code maxQueueSize} property.
   * @see #maxQueueSize
   */
  public void setMaxQueueSize(int v) { setInt(maxQueueSize, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BWxWorker.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  public BWxWorker()
  {
    super();
  }

  public BWxWorker(int maxThreads)
  {
    super(maxThreads);
  }

  ////////////////////////////////////////////////////////////////
  // BWorker
  ////////////////////////////////////////////////////////////////

  /**
   * Post an action to be run asynchronously.
   */
  public IFuture postAsync(Runnable r)
  {
    if (!isRunning() || queue == null)
    { throw new NotRunningException(); }
    queue.enqueue(r);
    return null;
  }

  /**
   * Start running this task.
   */
  public Worker getWorker()
  {
    if (worker == null)
    {
      queue = new CoalesceQueue(getMaxQueueSize());
      worker = new ThreadPoolWorker(queue);
    }
    return worker;
  }

  protected String getWorkerThreadName()
  {
    return "WxWorker:" + getParent().getName();
  }

  ////////////////////////////////////////////////////////////////
  // BComponent Overrides
  ////////////////////////////////////////////////////////////////

  /**
   * Callback when a property (or possibly a ancestor of
   * that property) is modified on this component.
   */
  public void changed(Property property, Context context)
  {
    super.changed(property, context);
    if (isRunning() && property.equals(maxQueueSize) && (queue != null))
    {
      stopWorker();
      queue = null;
      worker = null;
      getWorker();
      startWorker();
    }
  }

  ////////////////////////////////////////////////////////////////
  // Spy
  ////////////////////////////////////////////////////////////////

  public void spy(SpyWriter out) throws Exception
  {
    getWorker().spy(out);
  }

  ////////////////////////////////////////////////////////////////
  // Attributes
  ////////////////////////////////////////////////////////////////

  CoalesceQueue queue;
  ThreadPoolWorker worker;
}
