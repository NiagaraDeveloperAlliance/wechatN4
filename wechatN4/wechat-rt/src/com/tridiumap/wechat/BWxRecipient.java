package com.tridiumap.wechat;

import java.util.ArrayList;
import javax.baja.alarm.BAlarmRecipient;
import javax.baja.alarm.BAlarmRecord;
import javax.baja.collection.BITable;
import javax.baja.collection.TableCursor;
import javax.baja.naming.BOrd;
import javax.baja.nre.annotations.Facet;
import javax.baja.nre.annotations.NiagaraAction;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.Action;
import javax.baja.sys.BFacets;
import javax.baja.sys.BValue;
import javax.baja.sys.Context;
import javax.baja.sys.Flags;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.util.BFormat;
import javax.baja.util.IFuture;
import javax.baja.util.Invocation;

import com.tridiumap.wechat.util.HttpClient;

/*
 * Alarm Recipient to Route Alarm to WeChat
 *
 * @author Martin Huang
 * @creation 2019-04-28
 * @version 1.0
 * */

@NiagaraType
@NiagaraProperty(
  name = "accessToken",
  type = "String",
  defaultValue = "\"\""
)
@NiagaraProperty(
  name = "subscribersQuery",
  type = "BOrd",
  defaultValue = "BOrd.make(\"slot:/|bql:select * from wechat:WxSubscriber where remark='1'\")"
)
@NiagaraProperty(
  name = "message",
  type = "BFormat",
  defaultValue = "BFormat.make(\"{\\r\\n}\")",
  facets = {@Facet(name = "BFacets.MULTI_LINE", value = "true")}
)
@NiagaraProperty(
  name = "response",
  type = "String",
  flags = Flags.READONLY,
  defaultValue = "\"\"",
  facets = {@Facet(name = "BFacets.MULTI_LINE", value = "true")}
)
@NiagaraAction(
  name = "updateSubscribers",
  flags = Flags.ASYNC
)
@NiagaraAction(
  name = "asyncRoute",
  parameterType = "BAlarmRecord",
  defaultValue = "new BAlarmRecord()",
  flags = Flags.ASYNC
)
public class BWxRecipient
  extends BAlarmRecipient
{

/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.tridiumap.wechat.BWxRecipient(1002967873)1.0$ @*/
/* Generated Fri Jun 28 17:11:32 CST 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "accessToken"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code accessToken} property.
   * @see #getAccessToken
   * @see #setAccessToken
   */
  public static final Property accessToken = newProperty(0, "", null);
  
  /**
   * Get the {@code accessToken} property.
   * @see #accessToken
   */
  public String getAccessToken() { return getString(accessToken); }
  
  /**
   * Set the {@code accessToken} property.
   * @see #accessToken
   */
  public void setAccessToken(String v) { setString(accessToken, v, null); }

////////////////////////////////////////////////////////////////
// Property "subscribersQuery"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code subscribersQuery} property.
   * @see #getSubscribersQuery
   * @see #setSubscribersQuery
   */
  public static final Property subscribersQuery = newProperty(0, BOrd.make("slot:/|bql:select * from wechat:WxSubscriber where remark='1'"), null);
  
  /**
   * Get the {@code subscribersQuery} property.
   * @see #subscribersQuery
   */
  public BOrd getSubscribersQuery() { return (BOrd)get(subscribersQuery); }
  
  /**
   * Set the {@code subscribersQuery} property.
   * @see #subscribersQuery
   */
  public void setSubscribersQuery(BOrd v) { set(subscribersQuery, v, null); }

////////////////////////////////////////////////////////////////
// Property "message"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code message} property.
   * @see #getMessage
   * @see #setMessage
   */
  public static final Property message = newProperty(0, BFormat.make("{\r\n}"), BFacets.make(BFacets.MULTI_LINE, true));
  
  /**
   * Get the {@code message} property.
   * @see #message
   */
  public BFormat getMessage() { return (BFormat)get(message); }
  
  /**
   * Set the {@code message} property.
   * @see #message
   */
  public void setMessage(BFormat v) { set(message, v, null); }

////////////////////////////////////////////////////////////////
// Property "response"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code response} property.
   * @see #getResponse
   * @see #setResponse
   */
  public static final Property response = newProperty(Flags.READONLY, "", BFacets.make(BFacets.MULTI_LINE, true));
  
  /**
   * Get the {@code response} property.
   * @see #response
   */
  public String getResponse() { return getString(response); }
  
  /**
   * Set the {@code response} property.
   * @see #response
   */
  public void setResponse(String v) { setString(response, v, null); }

////////////////////////////////////////////////////////////////
// Action "updateSubscribers"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code updateSubscribers} action.
   * @see #updateSubscribers()
   */
  public static final Action updateSubscribers = newAction(Flags.ASYNC, null);
  
  /**
   * Invoke the {@code updateSubscribers} action.
   * @see #updateSubscribers
   */
  public void updateSubscribers() { invoke(updateSubscribers, null, null); }

////////////////////////////////////////////////////////////////
// Action "asyncRoute"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code asyncRoute} action.
   * @see #asyncRoute(BAlarmRecord parameter)
   */
  public static final Action asyncRoute = newAction(Flags.ASYNC, new BAlarmRecord(), null);
  
  /**
   * Invoke the {@code asyncRoute} action.
   * @see #asyncRoute
   */
  public void asyncRoute(BAlarmRecord parameter) { invoke(asyncRoute, parameter, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BWxRecipient.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  BWxSubscriber[] toArray;

  @Override
  public void handleAlarm(BAlarmRecord bAlarmRecord)
  {
    asyncRoute(bAlarmRecord);
  }

  @Override
  public void changed(Property property, Context cx)
  {
    if (!isRunning()) { return; }
    if (property == subscribersQuery)
    { updateSubscribers(); }
  }

  @Override
  public IFuture post(Action action, BValue arg, Context cx)
  {
    if (isRunning() && Sys.atSteadyState() && (getFlags(action) & Flags.ASYNC) == Flags.ASYNC)
    {
      BWxWorker worker = getWorker();
      if (worker != null) // if service installed, use service's thread pool
      { worker.postAsync(new Invocation(this, action, arg, cx)); }
      else // if service not installed, use single thread, occupy more resource and out of control
      { (new Thread(new Invocation(this, action, arg, cx))).start(); }
      return null;
    }
    return super.post(action, arg, cx);
  }


  public void doAsyncRoute(BAlarmRecord balarmrecord)
  {
    String sMessage;
    sMessage = getMessage()
      .format(balarmrecord); // support BFormat here so that it can include the information of alarm data in the JSON message

    BWxSubscriber[] array = getToArray();
    setResponse("");
    String sRes = "";
    // it's required the access_token with the request
    String sUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + getAccessToken();
    for (int i = 0; i < array.length; i++)
    {
      String sSend = sMessage.replace("{touser}", array[i].getOpenid());
      sSend = sSend.replace("{ID}", "" + (balarmrecord.getUuid().hashCode() & 0x7FFFF | 0x80000));
      sRes += HttpClient.Send(HttpClient.ACTION_POST, sUrl, sSend, null, null);
    }
    setResponse(sRes);
  }

  private BWxWorker getWorker()
  {
    BWxService ws = (BWxService) Sys.getService(BWxService.TYPE);
    if (ws == null) { return null; }
    return ws.getWorker();
  }

  public void doUpdateSubscribers()
  {
    if (isRunning() && Sys.atSteadyState())
    {
      toArray = null;
      getToArray();
    }
  }

  BWxSubscriber[] getToArray()
  {
    if (toArray == null)
    {
      ArrayList<BWxSubscriber> list = new ArrayList<BWxSubscriber>();
      BOrd ord = getSubscribersQuery();

      BITable result = (BITable) ord.get(this);
      TableCursor c = result.cursor();
      while (c.next())
      {
        Object o = c.get();
        if (o instanceof BWxSubscriber)
        { list.add((BWxSubscriber) o); }
      }
      toArray = new BWxSubscriber[list.size()];
      for (int i = 0; i < toArray.length; i++)
      {
        toArray[i] = list.get(i);
      }
    }
    return toArray;
  }

}
