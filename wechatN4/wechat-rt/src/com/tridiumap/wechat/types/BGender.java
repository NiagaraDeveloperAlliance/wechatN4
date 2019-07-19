package com.tridiumap.wechat.types;

import javax.baja.nre.annotations.NiagaraEnum;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.nre.annotations.Range;
import javax.baja.sys.BFrozenEnum;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

@NiagaraType
@NiagaraEnum(
  range = {
    @Range(value = "unknown", ordinal = 0),
    @Range(value = "male", ordinal = 1),
    @Range(value = "female", ordinal = 2),
  },
  defaultValue = "unknown"
)

public final class BGender
  extends BFrozenEnum
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.tridiumap.wechat.types.BGender(3809695660)1.0$ @*/
/* Generated Fri Apr 26 12:48:58 CST 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */
  
  /** Ordinal value for unknown. */
  public static final int UNKNOWN = 0;
  /** Ordinal value for male. */
  public static final int MALE = 1;
  /** Ordinal value for female. */
  public static final int FEMALE = 2;
  
  /** BGender constant for unknown. */
  public static final BGender unknown = new BGender(UNKNOWN);
  /** BGender constant for male. */
  public static final BGender male = new BGender(MALE);
  /** BGender constant for female. */
  public static final BGender female = new BGender(FEMALE);
  
  /** Factory method with ordinal. */
  public static BGender make(int ordinal)
  {
    return (BGender)unknown.getRange().get(ordinal, false);
  }
  
  /** Factory method with tag. */
  public static BGender make(String tag)
  {
    return (BGender)unknown.getRange().get(tag);
  }
  
  /** Private constructor. */
  private BGender(int ordinal)
  {
    super(ordinal);
  }
  
  public static final BGender DEFAULT = unknown;

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BGender.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/
}
