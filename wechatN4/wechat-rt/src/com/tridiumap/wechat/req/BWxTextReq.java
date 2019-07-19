package com.tridiumap.wechat.req;

import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BDouble;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

import com.tridiumap.wechat.WxMessage;
import com.tridiumap.wechat.util.HUtil;

/*
 * WeChat Text Request
 *
 * @author Martin Huang
 * @creation 2019-04-28
 * @version 1.0
 * */

@NiagaraType
@NiagaraProperty(
  name = "command",
  type = "String",
  defaultValue = "\"\""
)
@NiagaraProperty(
  name = "exactEqualsCommand",
  type = "boolean",
  defaultValue = "false"
)
@NiagaraProperty(
  name = "defaultParameter",
  type = "double",
  defaultValue = "0d"
)
public class BWxTextReq
  extends BAbstractWxReq
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.tridiumap.wechat.req.BWxTextReq(3697721048)1.0$ @*/
/* Generated Fri Jun 28 16:34:06 CST 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "command"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code command} property.
   * @see #getCommand
   * @see #setCommand
   */
  public static final Property command = newProperty(0, "", null);
  
  /**
   * Get the {@code command} property.
   * @see #command
   */
  public String getCommand() { return getString(command); }
  
  /**
   * Set the {@code command} property.
   * @see #command
   */
  public void setCommand(String v) { setString(command, v, null); }

////////////////////////////////////////////////////////////////
// Property "exactEqualsCommand"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code exactEqualsCommand} property.
   * @see #getExactEqualsCommand
   * @see #setExactEqualsCommand
   */
  public static final Property exactEqualsCommand = newProperty(0, false, null);
  
  /**
   * Get the {@code exactEqualsCommand} property.
   * @see #exactEqualsCommand
   */
  public boolean getExactEqualsCommand() { return getBoolean(exactEqualsCommand); }
  
  /**
   * Set the {@code exactEqualsCommand} property.
   * @see #exactEqualsCommand
   */
  public void setExactEqualsCommand(boolean v) { setBoolean(exactEqualsCommand, v, null); }

////////////////////////////////////////////////////////////////
// Property "defaultParameter"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code defaultParameter} property.
   * @see #getDefaultParameter
   * @see #setDefaultParameter
   */
  public static final Property defaultParameter = newProperty(0, 0d, null);
  
  /**
   * Get the {@code defaultParameter} property.
   * @see #defaultParameter
   */
  public double getDefaultParameter() { return getDouble(defaultParameter); }
  
  /**
   * Set the {@code defaultParameter} property.
   * @see #defaultParameter
   */
  public void setDefaultParameter(double v) { setDouble(defaultParameter, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BWxTextReq.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  BDouble para;

  @Override
  public boolean isMyTurn(WxMessage msg)
  {
    String cmd = getCommand();
    String content = msg.getContent();

    if (content == null || cmd.isEmpty())
    { return false; }

    if ("text".equals(msg.get("MsgType")))
    {
      if (getExactEqualsCommand())
      {
        if (cmd.equals(content))
        { return true; }
      }
      else
      {
        if (cmd.contains(content))
        {
          String s = HUtil.getRightText(msg.getContent(), cmd);
          try
          {
            if (s != null && !s.isEmpty())
            {
              double d = Double.parseDouble(s);
              para = BDouble.make(d);
            }
          }
          catch (Exception e)
          {
          }
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public Object getParameter()
  {
    if (para != null)
    { return para; }
    return getDefaultParameter();
  }

}
