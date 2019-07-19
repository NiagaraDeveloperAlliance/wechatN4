package com.tridiumap.wechat;

import javax.baja.nre.annotations.NiagaraAction;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.Action;
import javax.baja.sys.BAbsTime;
import javax.baja.sys.BRelTime;
import javax.baja.sys.Clock;
import javax.baja.sys.Flags;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

import com.tridiumap.wechat.util.HUtil;
import com.tridiumap.wechat.util.HttpClient;

/*
 * Component to Get Token from WeChat Periodically
 *
 * @author Martin Huang
 * @creation 2019-04-28
 * @version 1.0
 * */

@NiagaraType
@NiagaraProperty(
  name = "enabled",
  type = "boolean",
  defaultValue = "true"
)
@NiagaraProperty(
  name = "refreshPeriod",
  type = "BRelTime",
  defaultValue = "BRelTime.makeHours(1)"
)
@NiagaraProperty(
  name = "appid",
  type = "String",
  defaultValue = "\"\""
)
@NiagaraProperty(
  name = "secret",
  type = "String",
  defaultValue = "\"\""
)
@NiagaraProperty(
  name = "response",
  type = "String",
  defaultValue = "\"\"",
  flags = Flags.READONLY
)
@NiagaraProperty(
  name = "accessToken",
  type = "String",
  defaultValue = "\"\"",
  flags = Flags.READONLY
)
@NiagaraProperty(
  name = "lastSuccess",
  type = "BAbsTime",
  defaultValue = "BAbsTime.DEFAULT",
  flags = Flags.READONLY
)
@NiagaraAction(
  name = "execute",
  flags = Flags.ASYNC
)
public class BWxAccessToken
  extends BAsyncComponent
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.tridiumap.wechat.BWxAccessToken(1930780739)1.0$ @*/
/* Generated Fri Apr 26 15:03:17 CST 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "enabled"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code enabled} property.
   * @see #getEnabled
   * @see #setEnabled
   */
  public static final Property enabled = newProperty(0, true, null);
  
  /**
   * Get the {@code enabled} property.
   * @see #enabled
   */
  public boolean getEnabled() { return getBoolean(enabled); }
  
  /**
   * Set the {@code enabled} property.
   * @see #enabled
   */
  public void setEnabled(boolean v) { setBoolean(enabled, v, null); }

////////////////////////////////////////////////////////////////
// Property "refreshPeriod"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code refreshPeriod} property.
   * @see #getRefreshPeriod
   * @see #setRefreshPeriod
   */
  public static final Property refreshPeriod = newProperty(0, BRelTime.makeHours(1), null);
  
  /**
   * Get the {@code refreshPeriod} property.
   * @see #refreshPeriod
   */
  public BRelTime getRefreshPeriod() { return (BRelTime)get(refreshPeriod); }
  
  /**
   * Set the {@code refreshPeriod} property.
   * @see #refreshPeriod
   */
  public void setRefreshPeriod(BRelTime v) { set(refreshPeriod, v, null); }

////////////////////////////////////////////////////////////////
// Property "appid"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code appid} property.
   * @see #getAppid
   * @see #setAppid
   */
  public static final Property appid = newProperty(0, "", null);
  
  /**
   * Get the {@code appid} property.
   * @see #appid
   */
  public String getAppid() { return getString(appid); }
  
  /**
   * Set the {@code appid} property.
   * @see #appid
   */
  public void setAppid(String v) { setString(appid, v, null); }

////////////////////////////////////////////////////////////////
// Property "secret"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code secret} property.
   * @see #getSecret
   * @see #setSecret
   */
  public static final Property secret = newProperty(0, "", null);
  
  /**
   * Get the {@code secret} property.
   * @see #secret
   */
  public String getSecret() { return getString(secret); }
  
  /**
   * Set the {@code secret} property.
   * @see #secret
   */
  public void setSecret(String v) { setString(secret, v, null); }

////////////////////////////////////////////////////////////////
// Property "response"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code response} property.
   * @see #getResponse
   * @see #setResponse
   */
  public static final Property response = newProperty(Flags.READONLY, "", null);
  
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
// Property "accessToken"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code accessToken} property.
   * @see #getAccessToken
   * @see #setAccessToken
   */
  public static final Property accessToken = newProperty(Flags.READONLY, "", null);
  
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
// Property "lastSuccess"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code lastSuccess} property.
   * @see #getLastSuccess
   * @see #setLastSuccess
   */
  public static final Property lastSuccess = newProperty(Flags.READONLY, BAbsTime.DEFAULT, null);
  
  /**
   * Get the {@code lastSuccess} property.
   * @see #lastSuccess
   */
  public BAbsTime getLastSuccess() { return (BAbsTime)get(lastSuccess); }
  
  /**
   * Set the {@code lastSuccess} property.
   * @see #lastSuccess
   */
  public void setLastSuccess(BAbsTime v) { set(lastSuccess, v, null); }

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
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BWxAccessToken.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  private boolean isExecuting;
  Clock.Ticket ticket;

  public void doExecute()
  {
    if (!getEnabled())
    { return; }

    if (isExecuting)
    { return; }

    isExecuting = true;

    try
    {
      String sUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" +
        getAppid() +
        "&secret=" + getSecret();
      String sRes = HttpClient.Send(HttpClient.ACTION_GET, sUrl, "", null, null);
      setResponse(sRes);

      parse(sRes);
    }
    catch (Exception e)
    {
    }

    isExecuting = false;
  }

  @Override
  public void started()
  {
    if (!isRunning()) { return; }
    ticket = Clock.schedulePeriodically(this, getRefreshPeriod(), execute, null);
    execute();
  }

  @Override
  public void stopped()
  {
    if (ticket != null)
    {
      ticket.cancel();
      ticket = null;
    }
  }

  private void parse(String sRes)
  {
    String access_token = HUtil.getInnerText(sRes, "access_token\":\"", "\",");
    setAccessToken(access_token);
    if (!"".equals(access_token))
    {
      setLastSuccess(Clock.time());
    }
  }

}
