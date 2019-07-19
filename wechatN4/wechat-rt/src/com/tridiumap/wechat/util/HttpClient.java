package com.tridiumap.wechat.util;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpClient
{

  public static final String ACTION_GET = "GET";
  public static final String ACTION_POST = "POST";
  public static final String ACTION_PUT = "PUT";

/////////////////////////////////////////////////////////////////
//Construction
/////////////////////////////////////////////////////////////////

  public HttpClient(URL url)
  {
    this.url = url;
  }

  private HttpURLConnection CreateConnection(String connectMethod) throws Exception
  {
    //
    // Create the HTTP connection
    //

    //System.out.println("Attempting request");

    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

    if (conn instanceof HttpsURLConnection)
    {
      //
      // For HTTPS we want to accept an anonymous SSL connection without having to import any certificates
      //

      // Create an empty host name verifier
      HttpsURLConnection https = (HttpsURLConnection) conn;

      https.setHostnameVerifier(new HostnameVerifier()
      {
        public boolean verify(String name, SSLSession s)
        {
          return true;
        }
      });

    }

    conn.setInstanceFollowRedirects(false);
    conn.setDoOutput(true);
    conn.setDoInput(true);
    conn.setUseCaches(false);
    conn.setAllowUserInteraction(true);
    conn.setRequestMethod(connectMethod);

    if (authorization != null)
    { conn.setRequestProperty("Authorization", "Basic " + authorization); }

    conn.setRequestProperty("Accept-Language", "zh-CN");
    conn.setRequestProperty("Referer", url.toString());
    conn.setRequestProperty("Charset", "UTF-8");
    conn.setRequestProperty("Connection", "Keep-Alive");

    if (connectMethod.equals(ACTION_GET))
    {
      conn.connect();
    }

    return conn;
  }

/////////////////////////////////////////////////////////////////
//Make the HTTP Request
/////////////////////////////////////////////////////////////////

  /**
   * Opens the given url and send the contents of the input stream.
   */
  public String run(String outStr, String connectMethod) throws Exception
  {
    HttpURLConnection conn = CreateConnection(connectMethod);

    //
    // Write the data out (if post)
    //

    if (connectMethod.equals(ACTION_POST) || connectMethod.equals(ACTION_PUT))
    {
      OutputStream out = conn.getOutputStream();
      byte[] btSend = outStr.getBytes(encoding);
      out.write(btSend, 0, btSend.length);
      out.flush();

      try
      {
        out.close();
      }
      catch (Exception e)
      {
      }
    }

    ByteArrayOutputStream buf = new ByteArrayOutputStream(256);

    // Note when we get the input stream here, the write is made to the server if we are posting
    InputStream in = conn.getInputStream();
    try
    {
      int r;
      while ((r = in.read()) >= 0)
      {
        buf.write(r);
      }
    }
    catch (Exception x)
    {
      x.printStackTrace();
      if ((conn.getResponseCode() / 100) != 2)
      { throw new IOException(conn.getResponseMessage()); }
      throw x;
    }

    try
    {
      in.close();
    }
    catch (Exception e)
    {
    }
    return new String(buf.toByteArray(), encoding);
  }


  public static boolean httpDownload(String httpUrl, String saveFile)
  {
    // download file
    int bytesum = 0;
    int byteread = 0;

    URL url;
    try
    {
      url = new URL(httpUrl);
    }
    catch (MalformedURLException e1)
    {
      e1.printStackTrace();
      return false;
    }

    try
    {
      URLConnection conn = url.openConnection();
      InputStream inStream = conn.getInputStream();
      FileOutputStream fs = new FileOutputStream(saveFile);

      byte[] buffer = new byte[1204];
      while ((byteread = inStream.read(buffer)) != -1)
      {
        bytesum += byteread;
        System.out.println(bytesum);
        fs.write(buffer, 0, byteread);
      }
      return true;
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
      return false;
    }
    catch (IOException e)
    {
      e.printStackTrace();
      return false;
    }
  }
////////////////////////////////////////////////////////////////
//Authorization
////////////////////////////////////////////////////////////////

  /**
   * A null user name disables authorization.
   */
  public void setAuthorization(String user, String pass)
  {
    if (user == null)
    {
      authorization = null;
      return;
    }
    authorization = user + ":" + ((pass == null) ? "" : pass);

    authorization = new String(Base64.getEncoder().encode(authorization.getBytes()));
  }

////////////////////////////////////////////////////////////////
//Static set up of trust manager
////////////////////////////////////////////////////////////////

  static
  {
    try
    {
      // Create a trust manager that does not validate certificate chains
      TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager()
      {
        public java.security.cert.X509Certificate[] getAcceptedIssuers()
        {
          return null;
        }

        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
        {
        }

        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
        {
        }
      }};

      SSLContext sc = SSLContext.getInstance("SSL");

      sc.init(null, trustAllCerts, new java.security.SecureRandom());
      SSLSocketFactory sslSocketFactory = sc.getSocketFactory();

      HttpsURLConnection.setDefaultSSLSocketFactory(sslSocketFactory);
    }
    catch (Exception e)
    {
    }
  }

  // Get file length
  public static long getFileSize(String sURL)
  {
    int nFileLength = -1;
    try
    {
      URL url = new URL(sURL);
      HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
      httpConnection.setRequestProperty("User-Agent", "Internet Explorer");
      int responseCode = httpConnection.getResponseCode();
      if (responseCode >= 400)
      {
        System.err.println("Error Code : " + responseCode);
        return -2; // -2 represent access is error
      }
      String sHeader;
      for (int i = 1; ; i++)
      {
        sHeader = httpConnection.getHeaderFieldKey(i);
        if (sHeader != null)
        {
          if (sHeader.equals("Content-Length"))
          {
            nFileLength = Integer.parseInt(httpConnection.getHeaderField(sHeader));
            break;
          }
        }
        else
        { break; }
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    System.out.println(nFileLength);
    return nFileLength;
  }

////////////////////////////////////////////////////////////////
//Static method
////////////////////////////////////////////////////////////////

  public static String Send(String sMethod, String sUrl, String sPara, String sUser, String sPassword)
  {
    String sRet = "";
    try
    {
      URL url = new URL(sUrl);

      HttpClient client = new HttpClient(url);

      if (sUser != null && sUser.length() > 0)
      { client.setAuthorization(sUser, sPassword); }

      sRet = client.run(sPara, sMethod);

    }
    catch (Exception e)
    {
      System.out.print(e.getMessage());
    }
    return sRet;
  }

  public void setEncoding(String v)
  {
    encoding = v;
  }

////////////////////////////////////////////////////////////////
//Fields
////////////////////////////////////////////////////////////////

  private String authorization = null;
  private URL url;
  private String encoding = "UTF-8";

  public static void main(String[] args)
  {
    httpDownload("http://g.hiphotos.baidu.com/baike/s%3D220/sign=27d79081d0a20cf44290f9dd46094b0c/5fdf8db1cb1349547776ff62564e9258d1094a4f.jpg", "1.jpg");
  }
}
