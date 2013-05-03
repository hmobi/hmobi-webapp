package com.hmobi.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: manusrivastava
 * Date: 02/05/13
 * Time: 7:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class HttpClient
{
    private static final Logger logger = LoggerFactory.getLogger(HttpClient.class.getCanonicalName());


    // Create a trust manager that does not validate certificate chains
    static TrustManager[] trustAllCerts = new TrustManager[]{
    new X509TrustManager()
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
    }
    };

    private static HttpURLConnection getHttpConnection(String destinationURL, String username, String password,
                                                       boolean ssl, String requestMethod) throws Exception
    {
        HttpURLConnection httpConnection = null;
        URL testUrl = new URL(destinationURL);
        logger.info("Connecting ..." + destinationURL + ". SSL Enabled : " + ssl);
        httpConnection = (HttpURLConnection) testUrl.openConnection();

        if (ssl)
        {
            SSLContext sc = SSLContext.getInstance("SSLv3");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            SSLSocketFactory sslsocketfactory = sc.getSocketFactory();
            ((HttpsURLConnection) httpConnection).setSSLSocketFactory(sslsocketfactory);
            ((HttpsURLConnection) httpConnection).setHostnameVerifier(hostnameVerifier);
        }
        httpConnection.setRequestMethod(requestMethod);
        httpConnection.setInstanceFollowRedirects(false);
        httpConnection.setDoInput(true);
        httpConnection.setDoOutput(true);
        httpConnection.setConnectTimeout(10000);

        logger.debug("Created HTTP Connection with : " + destinationURL);

        //add basic auth header if provided.
        if (username != null && !username.isEmpty())
        {
            String authString = username + ":" + password;
            String encoding = new sun.misc.BASE64Encoder().encode(authString.getBytes());
            encoding = encoding.replaceAll("\\n","");
            httpConnection.setRequestProperty("Authorization", "Basic " + encoding);
        }
        return httpConnection;
    }

    private static String getResponse(HttpURLConnection httpConnection) throws IOException
    {
        int responseCode = httpConnection.getResponseCode();
        logger.debug("Resp Code:" + responseCode);
        logger.debug("Resp Message:" + httpConnection.getResponseMessage());

        if (responseCode >= 400)
        {
            InputStream errorStream = httpConnection.getErrorStream();
            String errorMessage = readData(errorStream, true);

            throw new RuntimeException("Error posting data. " + responseCode + "-" + httpConnection.getResponseMessage()
            + ". Error : " + errorMessage);
        }

        return readData(httpConnection.getInputStream(), true);
    }

    /**
     * Java internally maintains a pool of HTTPConnections to a destination.
     * This is controlled by System property "http.maxConnections"
     *
     * @param destinationURL
     * @param username
     * @param password
     * @param ssl
     * @param contentType
     * @param dataXml
     */
    public static String postData(String destinationURL, String username, String password,
                                  boolean ssl, String contentType, String dataXml)
    {
        long sTime = System.currentTimeMillis();

        try
        {
            HttpURLConnection httpConnection = getHttpConnection(destinationURL,username,password,ssl,"POST");
            //httpsConnection.setRequestProperty("Content-Type", "text/xml");
            httpConnection.setRequestProperty("Content-Type", contentType);

            //httpsConnection.connect();
            long eTime = System.currentTimeMillis();
            logger.debug("HTTP Request Time :  " + (eTime - sTime));

            DataOutputStream output = new DataOutputStream(httpConnection.getOutputStream());
            output.writeBytes(dataXml);
            output.flush();

            return getResponse(httpConnection);
        }
        catch (Throwable thr)
        {
            throw new RuntimeException("Error posting data. Error : " + thr.getMessage(), thr);
        }
    }

    public static String getData(String destinationURL, String username, String password, boolean ssl)
    {
        long sTime = System.currentTimeMillis();
        try
        {
            HttpURLConnection httpConnection = getHttpConnection(destinationURL,username,password,ssl,"GET");
            //httpsConnection.connect();
            long eTime = System.currentTimeMillis();
            logger.debug("HTTP Request Time :  " + (eTime - sTime));

            return getResponse(httpConnection);
        }
        catch (Throwable thr)
        {
            throw new RuntimeException("Error getting data. Error : " + thr.getMessage(), thr);
        }
    }

    static HostnameVerifier hostnameVerifier = new HostnameVerifier()
    {
        public boolean verify(String urlHostName, SSLSession session)
        {
            System.out.println("Warning: URL Host: " + urlHostName + " vs. "
            + session.getPeerHost());
            return true;
        }
    };

    public static String readData(InputStream inputstream, boolean closeStream)
    throws IOException
    {
        try
        {
            InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
            BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
            StringBuilder strBuff = new StringBuilder();
            String responseStr = null;
            while ((responseStr = bufferedreader.readLine()) != null)
            {
                strBuff.append(responseStr);
            }
            return strBuff.toString();
        }
        finally
        {
            if (closeStream)
            {
                inputstream.close();
            }
        }
    }
}

