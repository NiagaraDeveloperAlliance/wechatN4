package com.tridiumap.wechat.actions;

import javax.baja.data.BIDataValue;
import javax.baja.naming.BOrd;
import javax.baja.naming.BOrdList;
import javax.baja.nre.annotations.Facet;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BBoolean;
import javax.baja.sys.BComponent;
import javax.baja.sys.BFacets;
import javax.baja.sys.BString;
import javax.baja.sys.Context;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

import com.tridiumap.wechat.WxMessage;

/*
 * Read Data Action
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
  facets = @Facet("BFacets.make(new String[]{\"fieldEditor\",\"multiLine\"},new BIDataValue[]{BString.make(\"workbench:OrdListFE\"), BBoolean.make(true)})")
)
public class BActionReadData
  extends BAbstractWxAction
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.tridiumap.wechat.actions.BActionReadData(936668432)1.0$ @*/
/* Generated Sun Apr 28 11:45:43 CST 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "dataOrdList"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code dataOrdList} property.
   * @see #getDataOrdList
   * @see #setDataOrdList
   */
  public static final Property dataOrdList = newProperty(0, BOrdList.DEFAULT, BFacets.make(new String[]{"fieldEditor","multiLine"},new BIDataValue[]{BString.make("workbench:OrdListFE"), BBoolean.make(true)}));
  
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
  public static final Type TYPE = Sys.loadType(BActionReadData.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  @Override
  public String execute(WxMessage msg)
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
        if (!comp.isSubscribed()) { comp.lease(); }
        sb.append(comp.getDisplayName(context));
        sb.append(":");
        sb.append(comp.toString());
        sb.append("\n");
      }
    }

    return sb.toString();
  }

}
