package com.tridiumap.wechat.responsers;

import javax.baja.nre.annotations.Facet;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BComponent;
import javax.baja.sys.BFacets;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

/*
 * Container of WeChat Responser
 *
 * @author Martin Huang
 * @creation 2019-04-28
 * @version 1.0
 * */

@NiagaraType
@NiagaraProperty(
  name = "picResponse",
  type = "String",
  defaultValue = "\"\"",
  facets = {@Facet(name = "BFacets.MULTI_LINE", value = "true")}
)
@NiagaraProperty(
  name = "locationResponse",
  type = "String",
  defaultValue = "You are in %LOCATION%...",
  facets = {@Facet(name = "BFacets.MULTI_LINE", value = "true")}
)
@NiagaraProperty(
  name = "defaultResponse",
  type = "String",
  defaultValue = "\"\"",
  facets = {@Facet(name = "BFacets.MULTI_LINE", value = "true")}
)

public class BResponseContainer
  extends BComponent
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.tridiumap.wechat.responsers.BResponseContainer(148842077)1.0$ @*/
/* Generated Fri Apr 26 17:39:05 CST 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "picResponse"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code picResponse} property.
   * @see #getPicResponse
   * @see #setPicResponse
   */
  public static final Property picResponse = newProperty(0, "", BFacets.make(BFacets.MULTI_LINE, true));
  
  /**
   * Get the {@code picResponse} property.
   * @see #picResponse
   */
  public String getPicResponse() { return getString(picResponse); }
  
  /**
   * Set the {@code picResponse} property.
   * @see #picResponse
   */
  public void setPicResponse(String v) { setString(picResponse, v, null); }

////////////////////////////////////////////////////////////////
// Property "locationResponse"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code locationResponse} property.
   * @see #getLocationResponse
   * @see #setLocationResponse
   */
  public static final Property locationResponse = newProperty(0, "You are in %LOCATION%...", BFacets.make(BFacets.MULTI_LINE, true));
  
  /**
   * Get the {@code locationResponse} property.
   * @see #locationResponse
   */
  public String getLocationResponse() { return getString(locationResponse); }
  
  /**
   * Set the {@code locationResponse} property.
   * @see #locationResponse
   */
  public void setLocationResponse(String v) { setString(locationResponse, v, null); }

////////////////////////////////////////////////////////////////
// Property "defaultResponse"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code defaultResponse} property.
   * @see #getDefaultResponse
   * @see #setDefaultResponse
   */
  public static final Property defaultResponse = newProperty(0, "", BFacets.make(BFacets.MULTI_LINE, true));
  
  /**
   * Get the {@code defaultResponse} property.
   * @see #defaultResponse
   */
  public String getDefaultResponse() { return getString(defaultResponse); }
  
  /**
   * Set the {@code defaultResponse} property.
   * @see #defaultResponse
   */
  public void setDefaultResponse(String v) { setString(defaultResponse, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BResponseContainer.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/
}
