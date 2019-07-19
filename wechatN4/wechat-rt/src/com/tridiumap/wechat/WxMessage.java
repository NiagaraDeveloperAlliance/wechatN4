package com.tridiumap.wechat;

import java.util.HashMap;
import javax.baja.xml.XElem;

public class WxMessage
{
  private Object tag;
  HashMap<String, String> mapItems = new HashMap<>();

  public WxMessage(XElem root)
  {
    parse(root);
  }

  private void parse(XElem root)
  {
    int i;
    XElem[] subElems = root.elems();
    for (i = 0; i < subElems.length; i++)
    {
      mapItems.put(subElems[i].name(), subElems[i].string());
    }
  }

  public String get(String name)
  {
    if (mapItems.containsKey(name))
    { return mapItems.get(name); }
    return null;
  }

  public String getContent()
  {
    return get("Content");
  }

  public String toResponseXmlMessage(String content)
  {
    if (content == null || content.length() <= 0)
    { return ""; }

    return getResponseXml(content);
  }

  private String getResponseXml(String content)
  {
    StringBuffer sb = new StringBuffer();
    sb.append("<xml>\n");

    addNode(sb, "ToUserName", get("FromUserName"), true);
    addNode(sb, "FromUserName", get("ToUserName"), true);
    addNode(sb, "CreateTime", Long.toString(System.currentTimeMillis()), false);
    addNode(sb, "MsgType", "text", true);
    addNode(sb, "Content", content, true);
    if (null != ("MsgId"))
    { addNode(sb, "MsgId", get("MsgId"), false); }
    sb.append("</xml>");
    return sb.toString();
  }

  public static void addNode(StringBuffer sb, String subName, String text, boolean cdata)
  {
    sb.append(" <");

    sb.append(subName);

    if (cdata)
    { sb.append("><![CDATA["); }
    else
    { sb.append(">"); }

    sb.append(text);

    if (cdata)
    { sb.append("]]></"); }
    else
    { sb.append("</"); }

    sb.append(subName);

    sb.append(">\n");
  }

  public Object getTag()
  {
    return tag;
  }

  public void setTag(Object v)
  {
    tag = v;
  }

}
