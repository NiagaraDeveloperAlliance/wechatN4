package com.tridiumap.wechat.responsers;

import javax.baja.naming.BOrd;
import javax.baja.naming.BOrdList;
import javax.baja.nre.annotations.Facet;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BComponent;
import javax.baja.sys.BFacets;
import javax.baja.sys.BString;
import javax.baja.sys.Context;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

import com.tridiumap.wechat.WxMessage;

/*
 * Responer of WeChat Real-time Data Command
 *
 * @author Martin Huang
 * @creation 2019-04-28
 * @version 1.0
 * */

@NiagaraType
@NiagaraProperty(
  name = "dataOrdList",
  type = "BOrdList",
  defaultValue = "BOrdList.DEFAULT",
  facets = {
    @Facet(name = "BFacets.FIELD_EDITOR", value = "BString.make(\"workbench:OrdListFE\")"),
    @Facet(name = "BFacets.MULTI_LINE", value = "true")
  }
)

public class BWxRealtimeData
  extends BAbstractWxResponser
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.tridiumap.wechat.responsers.BWxRealtimeData(1356193165)1.0$ @*/
/* Generated Fri Jun 28 16:46:05 CST 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "dataOrdList"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code dataOrdList} property.
   * @see #getDataOrdList
   * @see #setDataOrdList
   */
  public static final Property dataOrdList = newProperty(0, BOrdList.DEFAULT, BFacets.make(BFacets.make(BFacets.FIELD_EDITOR, BString.make("workbench:OrdListFE")), BFacets.make(BFacets.MULTI_LINE, true)));
  
  /**
   * Get the {@code dataOrdList} property.
   * @see #dataOrdList
   */
  public BOrdList getDataOrdList() { return (BOrdList)get(dataOrdList); }
  
  /**
   * Set the {@code dataOrdList} property.
   * @see #dataOrdList
   */
  public void setDataOrdList(BOrdList v) { set(dataOrdList, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BWxRealtimeData.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  @Override
  public String getResponse(WxMessage msgReq)
  {
    String cmd = getCommand();
    String eventKey = getEventKey();
    if (cmd.length() == 0 || eventKey.length() == 0)
    { return null; }

    if (cmd.equals(msgReq.getContent()) || eventKey.equals(msgReq.get("EventKey")))
    {
      return buildOutput();
    }
    return null;
  }

  private String buildOutput()
  {
    StringBuffer sb = new StringBuffer();

    BOrdList list = getDataOrdList();
    Context context = getComponentSpace().getSession().getSessionContext();

    for (int i = 0; i < list.size(); i++)
    {
      BOrd ord = list.get(i);
      if (ord == null) { continue; }
      Object obj = ord.resolve(this).get();
      if (obj instanceof BComponent)
      {
        BComponent comp = (BComponent) obj;
        // It's required to subscribe the component to keep the data changing
        if (!comp.isSubscribed())
        { comp.lease(); }
        sb.append(comp.getDisplayName(context));
        sb.append(":");
        sb.append(comp.toString());
        sb.append("\n");
      }
    }

    if (sb.length() == 0)
    { return "No data configured."; }

    return sb.toString();
  }
}
