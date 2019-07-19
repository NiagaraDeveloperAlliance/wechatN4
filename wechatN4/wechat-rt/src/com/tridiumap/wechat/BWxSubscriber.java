package com.tridiumap.wechat;

import javax.baja.nre.annotations.NiagaraAction;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.Action;
import javax.baja.sys.BAbsTime;
import javax.baja.sys.BComponent;
import javax.baja.sys.BString;
import javax.baja.sys.Clock;
import javax.baja.sys.Flags;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.util.BFormat;

import com.tridiumap.wechat.types.BGender;
import com.tridiumap.wechat.util.HttpClient;
import com.tridium.json.JSONObject;

/*
 * Component to Get Detailed Data of A WeChat Subscriber
 *
 * @author Martin Huang
 * @creation 2019-04-28
 * @version 1.0
 * */

@NiagaraType
@NiagaraProperty(
  name = "openid",
  type = "String",
  defaultValue = "\"\"",
  flags = Flags.READONLY
)
@NiagaraProperty(
  name = "nickName",
  type = "String",
  defaultValue = "\"\"",
  flags = Flags.READONLY
)
@NiagaraProperty(
  name = "gender",
  type = "BGender",
  defaultValue = "BGender.DEFAULT",
  flags = Flags.READONLY
)
@NiagaraProperty(
  name = "remark",
  type = "String",
  defaultValue = "\"\"",
  flags = Flags.READONLY
)
@NiagaraProperty(
  name = "groupId",
  type = "int",
  defaultValue = "0",
  flags = Flags.READONLY
)
@NiagaraProperty(
  name = "lastUpdate",
  type = "BAbsTime",
  defaultValue = "BAbsTime.DEFAULT",
  flags = Flags.READONLY
)
@NiagaraProperty(
  name = "statusString",
  type = "String",
  defaultValue = "\"\"",
  flags = Flags.READONLY
)
@NiagaraAction(
  name = "update",
  flags = Flags.ASYNC
)
@NiagaraAction(
  name = "modifyRemark",
  parameterType = "BString",
  defaultValue = "BString.make(  \"\")",
  flags = Flags.ASYNC
)
public class BWxSubscriber extends BAsyncComponent {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.tridiumap.wechat.BWxSubscriber(3847894108)1.0$ @*/
/* Generated Fri Jul 19 14:59:06 CST 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "openid"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code openid} property.
   * @see #getOpenid
   * @see #setOpenid
   */
  public static final Property openid = newProperty(Flags.READONLY, "", null);
  
  /**
   * Get the {@code openid} property.
   * @see #openid
   */
  public String getOpenid() { return getString(openid); }
  
  /**
   * Set the {@code openid} property.
   * @see #openid
   */
  public void setOpenid(String v) { setString(openid, v, null); }

////////////////////////////////////////////////////////////////
// Property "nickName"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code nickName} property.
   * @see #getNickName
   * @see #setNickName
   */
  public static final Property nickName = newProperty(Flags.READONLY, "", null);
  
  /**
   * Get the {@code nickName} property.
   * @see #nickName
   */
  public String getNickName() { return getString(nickName); }
  
  /**
   * Set the {@code nickName} property.
   * @see #nickName
   */
  public void setNickName(String v) { setString(nickName, v, null); }

////////////////////////////////////////////////////////////////
// Property "gender"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code gender} property.
   * @see #getGender
   * @see #setGender
   */
  public static final Property gender = newProperty(Flags.READONLY, BGender.DEFAULT, null);
  
  /**
   * Get the {@code gender} property.
   * @see #gender
   */
  public BGender getGender() { return (BGender)get(gender); }
  
  /**
   * Set the {@code gender} property.
   * @see #gender
   */
  public void setGender(BGender v) { set(gender, v, null); }

////////////////////////////////////////////////////////////////
// Property "remark"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code remark} property.
   * @see #getRemark
   * @see #setRemark
   */
  public static final Property remark = newProperty(Flags.READONLY, "", null);
  
  /**
   * Get the {@code remark} property.
   * @see #remark
   */
  public String getRemark() { return getString(remark); }
  
  /**
   * Set the {@code remark} property.
   * @see #remark
   */
  public void setRemark(String v) { setString(remark, v, null); }

////////////////////////////////////////////////////////////////
// Property "groupId"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code groupId} property.
   * @see #getGroupId
   * @see #setGroupId
   */
  public static final Property groupId = newProperty(Flags.READONLY, 0, null);
  
  /**
   * Get the {@code groupId} property.
   * @see #groupId
   */
  public int getGroupId() { return getInt(groupId); }
  
  /**
   * Set the {@code groupId} property.
   * @see #groupId
   */
  public void setGroupId(int v) { setInt(groupId, v, null); }

////////////////////////////////////////////////////////////////
// Property "lastUpdate"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code lastUpdate} property.
   * @see #getLastUpdate
   * @see #setLastUpdate
   */
  public static final Property lastUpdate = newProperty(Flags.READONLY, BAbsTime.DEFAULT, null);
  
  /**
   * Get the {@code lastUpdate} property.
   * @see #lastUpdate
   */
  public BAbsTime getLastUpdate() { return (BAbsTime)get(lastUpdate); }
  
  /**
   * Set the {@code lastUpdate} property.
   * @see #lastUpdate
   */
  public void setLastUpdate(BAbsTime v) { set(lastUpdate, v, null); }

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
// Action "update"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code update} action.
   * @see #update()
   */
  public static final Action update = newAction(Flags.ASYNC, null);
  
  /**
   * Invoke the {@code update} action.
   * @see #update
   */
  public void update() { invoke(update, null, null); }

////////////////////////////////////////////////////////////////
// Action "modifyRemark"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code modifyRemark} action.
   * @see #modifyRemark(BString parameter)
   */
  public static final Action modifyRemark = newAction(Flags.ASYNC, BString.make(  ""), null);
  
  /**
   * Invoke the {@code modifyRemark} action.
   * @see #modifyRemark
   */
  public void modifyRemark(BString parameter) { invoke(modifyRemark, parameter, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BWxSubscriber.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  public void getDetail(String accessToken)
  {
    String sUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + accessToken + "&openid=" + getOpenid();
    String sRes = HttpClient.Send(HttpClient.ACTION_GET, sUrl, "", null, null);
    JSONObject jo = new JSONObject(sRes);
    setNickName(jo.getString("nickname"));
    setGender(BGender.make(jo.getInt("sex")));
    setRemark(jo.getString("remark"));
    setGroupId(jo.getInt("groupid"));
    String displayName = getNickName();
    if (getRemark().length() > 0) { displayName += "-(" + getRemark() + ")"; }
    ((BComponent) getParent()).setDisplayName(getPropertyInParent(), BFormat.make(displayName), null);
    setLastUpdate(Clock.time(1000));
  }

  public void doUpdate()
  {
    if (!isRunning()) { return; }

    BWxSubscribers parent = (BWxSubscribers) getParent();
    getDetail(parent.getAccessToken());
  }

  public void doModifyRemark(BString arg)
  {
    if (!isRunning()) { return; }

    BWxSubscribers parent = (BWxSubscribers) getParent();
    String sPost;
    String sUrl = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=" + parent.getAccessToken();
    sPost = "{\"openid\":\"" + getOpenid() + "\",\"remark\":\"" + arg.getString() + "\"}";
    String sRes = HttpClient.Send(HttpClient.ACTION_POST, sUrl, sPost, null, null);
    JSONObject obj = new JSONObject(sRes);
    setStatusString(obj.getString("errmsg"));
    if ("ok".equals(getStatusString()))
    {
      getDetail(parent.getAccessToken());
    }
  }
}
