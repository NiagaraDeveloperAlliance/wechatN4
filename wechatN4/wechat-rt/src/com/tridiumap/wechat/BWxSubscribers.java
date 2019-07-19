package com.tridiumap.wechat;

import java.util.logging.Level;
import javax.baja.naming.SlotPath;
import javax.baja.nre.annotations.NiagaraAction;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.Action;
import javax.baja.sys.BAbsTime;
import javax.baja.sys.BString;
import javax.baja.sys.BValue;
import javax.baja.sys.Flags;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

import com.tridiumap.wechat.util.HttpClient;
import com.tridium.json.JSONArray;
import com.tridium.json.JSONObject;

/*
 * Component to Get WeChat Subscriber List
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
  name = "statusString",
  type = "String",
  defaultValue = "\"\"",
  flags = Flags.READONLY
)
@NiagaraAction(
  name = "execute",
  flags = Flags.ASYNC
)
@NiagaraAction(
  name = "handleOne",
  parameterType = "BString",
  defaultValue = "BString.make(\"\")",
  flags = Flags.ASYNC
)
public class BWxSubscribers
  extends BAsyncComponent
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.tridiumap.wechat.BWxSubscribers(2465116786)1.0$ @*/
/* Generated Fri Apr 26 17:16:15 CST 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

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
// Property "statusString"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code statusString} property.
   * @see #getStatusString
   * @see #setStatusString
   */
  public static final Property statusString = newProperty(Flags.READONLY, "", null);
  
  /**
   * Get the {@code statusString} property.
   * @see #statusString
   */
  public String getStatusString() { return getString(statusString); }
  
  /**
   * Set the {@code statusString} property.
   * @see #statusString
   */
  public void setStatusString(String v) { setString(statusString, v, null); }

////////////////////////////////////////////////////////////////
// Action "execute"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code execute} action.
   * @see #execute()
   */
  public static final Action execute = newAction(Flags.ASYNC, null);
  
  /**
   * Invoke the {@code execute} action.
   * @see #execute
   */
  public void execute() { invoke(execute, null, null); }

////////////////////////////////////////////////////////////////
// Action "handleOne"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code handleOne} action.
   * @see #handleOne(BString parameter)
   */
  public static final Action handleOne = newAction(Flags.ASYNC, BString.make(""), null);
  
  /**
   * Invoke the {@code handleOne} action.
   * @see #handleOne
   */
  public void handleOne(BString parameter) { invoke(handleOne, parameter, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BWxSubscribers.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  private boolean isExecuting;

  public void doExecute()
  {
    int number = 0;
    String next_openid = "";
    if (isExecuting)
    { return; }
    isExecuting = true;
    try
    {
      while (true)
      {
        String sUrl = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=" + getAccessToken() + "&next_openid=" + next_openid;
        String sRes = HttpClient.Send(HttpClient.ACTION_GET, sUrl, "", null, null);

        JSONObject obj = new JSONObject(sRes);
        if (!obj.isNull("errmsg"))
        {
          setStatusString(obj.getString("errmsg"));
          break;
        }
        int total = obj.getInt("total");
        int count = obj.getInt("count");
        next_openid = obj.getString("next_openid");
        if (count <= 0) { break; }
        JSONArray jsonOpenids = obj.getJSONObject("data").getJSONArray("openid");

        for (int i = 0; i < jsonOpenids.length(); i++)
        {
          handleOpenid(jsonOpenids.getString(i));
          setStatusString("Processing... " + (++number) + "/" + total);
        }
      }
    }
    catch (Exception e)
    {
      BWxService.logger.log(Level.WARNING, "Fail to get user list.", e);
    }
    if (number > 0)
    { setStatusString("Completed " + number + " at " + BAbsTime.now()); }
    isExecuting = false;

  }

  public void doHandleOne(BString openId)
  {
    handleOpenid(openId.getString());
  }

  private void handleOpenid(String openid)
  {
    String slotName = SlotPath.escape(openid);
    BValue slot = get(slotName);
    if (slot != null && !(slot instanceof BWxSubscriber))
    {
      //delete it
      remove(slotName);
      slot = null;
    }
    if (slot == null)
    {
      //create it
      BWxSubscriber ws = new BWxSubscriber();
      ws.setOpenid(openid);
      add(slotName, ws);
      ws.getDetail(getAccessToken());
    }
    else
    {
      ((BWxSubscriber) slot).getDetail(getAccessToken());
    }
  }

  public BWxSubscriber find(String openid)
  {
    String slotName = SlotPath.escape(openid);
    BValue slot = get(slotName);
    if (slot != null && (slot instanceof BWxSubscriber))
    {
      return (BWxSubscriber) slot;
    }
    return null;
  }

}
