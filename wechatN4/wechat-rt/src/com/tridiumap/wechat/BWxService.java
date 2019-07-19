package com.tridiumap.wechat;

import java.util.logging.Logger;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BAbstractService;
import javax.baja.sys.Flags;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

import com.tridiumap.wechat.menu.BMenuContainerTop;
import com.tridiumap.wechat.req.BRequestContainer;
import com.tridiumap.wechat.responsers.BResponseContainer;

@NiagaraType
@NiagaraProperty(
  name = "token",
  type = "String",
  defaultValue = "Niagara"
)
@NiagaraProperty(
  name = "worker",
  type = "BWxWorker",
  defaultValue = "new BWxWorker(4)",
  flags = Flags.HIDDEN
)
@NiagaraProperty(
  name = "accessToken",
  type = "BWxAccessToken",
  defaultValue = "new BWxAccessToken()"
)
@NiagaraProperty(
  name = "subscribersList",
  type = "BWxSubscribers",
  defaultValue = "new BWxSubscribers()"
)
@NiagaraProperty(
  name = "menus",
  type = "BMenuContainerTop",
  defaultValue = "new BMenuContainerTop()"
)
@NiagaraProperty(
  name = "requests",
  type = "BRequestContainer",
  defaultValue = "new BRequestContainer()"
)
@NiagaraProperty(
  name = "responses",
  type = "BResponseContainer",
  defaultValue = "new BResponseContainer()"
)
public class BWxService
  extends BAbstractService
{

/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.tridiumap.wechat.BWxService(3165890773)1.0$ @*/
/* Generated Sat Apr 27 18:21:06 CST 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "token"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code token} property.
   * @see #getToken
   * @see #setToken
   */
  public static final Property token = newProperty(0, "Niagara", null);
  
  /**
   * Get the {@code token} property.
   * @see #token
   */
  public String getToken() { return getString(token); }
  
  /**
   * Set the {@code token} property.
   * @see #token
   */
  public void setToken(String v) { setString(token, v, null); }

////////////////////////////////////////////////////////////////
// Property "worker"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code worker} property.
   * @see #getWorker
   * @see #setWorker
   */
  public static final Property worker = newProperty(Flags.HIDDEN, new BWxWorker(4), null);
  
  /**
   * Get the {@code worker} property.
   * @see #worker
   */
  public BWxWorker getWorker() { return (BWxWorker)get(worker); }
  
  /**
   * Set the {@code worker} property.
   * @see #worker
   */
  public void setWorker(BWxWorker v) { set(worker, v, null); }

////////////////////////////////////////////////////////////////
// Property "accessToken"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code accessToken} property.
   * @see #getAccessToken
   * @see #setAccessToken
   */
  public static final Property accessToken = newProperty(0, new BWxAccessToken(), null);
  
  /**
   * Get the {@code accessToken} property.
   * @see #accessToken
   */
  public BWxAccessToken getAccessToken() { return (BWxAccessToken)get(accessToken); }
  
  /**
   * Set the {@code accessToken} property.
   * @see #accessToken
   */
  public void setAccessToken(BWxAccessToken v) { set(accessToken, v, null); }

////////////////////////////////////////////////////////////////
// Property "subscribersList"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code subscribersList} property.
   * @see #getSubscribersList
   * @see #setSubscribersList
   */
  public static final Property subscribersList = newProperty(0, new BWxSubscribers(), null);
  
  /**
   * Get the {@code subscribersList} property.
   * @see #subscribersList
   */
  public BWxSubscribers getSubscribersList() { return (BWxSubscribers)get(subscribersList); }
  
  /**
   * Set the {@code subscribersList} property.
   * @see #subscribersList
   */
  public void setSubscribersList(BWxSubscribers v) { set(subscribersList, v, null); }

////////////////////////////////////////////////////////////////
// Property "menus"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code menus} property.
   * @see #getMenus
   * @see #setMenus
   */
  public static final Property menus = newProperty(0, new BMenuContainerTop(), null);
  
  /**
   * Get the {@code menus} property.
   * @see #menus
   */
  public BMenuContainerTop getMenus() { return (BMenuContainerTop)get(menus); }
  
  /**
   * Set the {@code menus} property.
   * @see #menus
   */
  public void setMenus(BMenuContainerTop v) { set(menus, v, null); }

////////////////////////////////////////////////////////////////
// Property "requests"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code requests} property.
   * @see #getRequests
   * @see #setRequests
   */
  public static final Property requests = newProperty(0, new BRequestContainer(), null);
  
  /**
   * Get the {@code requests} property.
   * @see #requests
   */
  public BRequestContainer getRequests() { return (BRequestContainer)get(requests); }
  
  /**
   * Set the {@code requests} property.
   * @see #requests
   */
  public void setRequests(BRequestContainer v) { set(requests, v, null); }

////////////////////////////////////////////////////////////////
// Property "responses"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code responses} property.
   * @see #getResponses
   * @see #setResponses
   */
  public static final Property responses = newProperty(0, new BResponseContainer(), null);
  
  /**
   * Get the {@code responses} property.
   * @see #responses
   */
  public BResponseContainer getResponses() { return (BResponseContainer)get(responses); }
  
  /**
   * Set the {@code responses} property.
   * @see #responses
   */
  public void setResponses(BResponseContainer v) { set(responses, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BWxService.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  @Override
  public Type[] getServiceTypes()
  {
    return serviceTypes;
  }

  private static Type[] serviceTypes = new Type[]{TYPE};
  public static Logger logger = Logger.getLogger("wechat");
}
