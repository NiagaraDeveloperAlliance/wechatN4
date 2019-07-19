package com.tridiumap.wechat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.baja.sys.BString;
import javax.baja.sys.Sys;
import javax.baja.web.servlets.UnauthenticatedServlet;
import javax.baja.xml.XElem;
import javax.baja.xml.XParser;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tridiumap.wechat.responsers.BAbstractWxResponser;
import com.tridiumap.wechat.responsers.BResponseContainer;
import com.tridiumap.wechat.util.Encrypt;

public class WxServlet extends UnauthenticatedServlet
{
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException
  {
    //
    BWxService wechatService = getService();
    if (wechatService == null || wechatService.isFault())
    {
      BWxService.logger.warning("No WechatService or it's fault.");
      res.getWriter().write("error: No WechatService or it's fault.");
      res.getWriter().flush();
      return;
    }

    req.setCharacterEncoding("UTF-8");
    res.setCharacterEncoding("UTF-8");
    String signature = req.getParameter("signature");
    String timestamp = req.getParameter("timestamp");
    String nonce = req.getParameter("nonce");
    String echostr = req.getParameter("echostr");
    if (signature == null) { signature = "1"; }
    if (timestamp == null) { timestamp = "3"; }
    if (nonce == null) { nonce = "2"; }
    if (echostr == null) { echostr = "4"; }

/*
    // It's supposed to checking time here:
    Long lT = Long.parseLong(timestamp);
    if(Math.abs(lT-(BAbsTime.now().getMillis()/1000))>60*60){
      resp.getWriter().write("error");
      resp.getWriter().flush();
      return;
    }
*/

    List<String> list = new ArrayList<>();
    list.add(wechatService.getToken());// token
    list.add(timestamp);
    list.add(nonce);
    Collections.sort(list);// resort

    StringBuffer buffer = new StringBuffer();
    String str = null;
    for (int i = 0; i < list.size(); i++)
    {
      buffer.append(list.get(i));
    }

    String sha1Str = Encrypt.SHA1(buffer.toString());
    if (sha1Str.equals(signature))
    {
      BWxService.logger.info("Congratulations, connect successfully!");
      res.getWriter().write(echostr);
    }
    else
    { res.getWriter().write("error"); }
    res.getWriter().flush();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException
  {

    Logger log = BWxService.logger;
    req.setCharacterEncoding("UTF-8");
    res.setCharacterEncoding("UTF-8");
    BWxService wxService = getService();

    if (wxService == null)
    {
      res.getWriter().write("BWxService is not found!");
      res.getWriter().flush();
      return;
    }

    try
    {
      XParser xml = XParser.make(req.getInputStream());
      XElem root = xml.parse();
      if (log.isLoggable(Level.FINE))
      {
        log.fine("++++++++");
        root.dump();
      }

      WxMessage msg = new WxMessage(root);
      String msgType = msg.get("MsgType");
      if ("subscribe".equals(msg.get("Event")))
      {
        wxService.getSubscribersList().handleOne(BString.make(msg.get("FromUserName")));
      }

      String sRes = null;//
      BResponseContainer rc = wxService.getResponses();
      if ("image".equals(msgType))
      {
        sRes = rc.getPicResponse();
      }
      else if ("location".equals(msgType))
      {
        sRes = rc.getLocationResponse();
        sRes = sRes.replaceAll("%LOCATION%", msg.get("Label"));
      }
      else
      {
        BAbstractWxResponser[] resps = rc.getChildren(BAbstractWxResponser.class);
        for (int i = 0; i < resps.length; i++)
        {
          String s = resps[i].getResponse(msg);
          if (s != null && s.length() > 0)
          {
            sRes = s;
            break;
          }
        }

        if (sRes == null)
        { sRes = getMenu(resps) + rc.getDefaultResponse(); }
      }
      if (sRes == null)
      { sRes = rc.getDefaultResponse(); }
      String sResXml = msg.toResponseXmlMessage(sRes);

      log.fine(sResXml);

      res.getWriter().write(sResXml);
      res.getWriter().flush();
    }
    catch (Exception e)
    {
      BWxService.logger.log(Level.WARNING, e.getMessage(), e);
    }
  }

  private String getMenu(BAbstractWxResponser[] resps)
  {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < resps.length; i++)
    {
      String menu = resps[i].getMenu();
      if (menu.length() > 0)
      { sb.append(menu).append("\n"); }
    }
    if (sb.length() > 0)
    { return sb.toString(); }
    return "";
  }

  private BWxService getService()
  {
    return (BWxService) Sys.getService(BWxService.TYPE);
  }
}
