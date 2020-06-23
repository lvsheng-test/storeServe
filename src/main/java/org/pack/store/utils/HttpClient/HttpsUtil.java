package org.pack.store.utils.HttpClient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import sun.net.www.protocol.https.Handler;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/** * @Class：HttpsUtil.java * @Description： * @Author：@author 张洪斌 * @Date：2013-10-22 */
public class HttpsUtil {
    private static final Log log = LogFactory.getLog(HttpsUtil.class);

    private static class MyTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }
        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[] {};
        }
    }

    private static class MyHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    /** * * HTTP协议GET请求方法 */
    public static String httpMethodGet(String url) {
        StringBuffer sb = new StringBuffer();
        URL urls;
        HttpsURLConnection uc = null;
        BufferedReader in = null;
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[] { new MyTrustManager() },
                    new java.security.SecureRandom());
            urls = new URL(null,url,new Handler());
            uc = (HttpsURLConnection) urls.openConnection();
            uc.setSSLSocketFactory(sc.getSocketFactory());
            uc.setHostnameVerifier(new MyHostnameVerifier());
            uc.setRequestMethod("GET");
            uc.connect();
            in = new BufferedReader(new InputStreamReader(uc.getInputStream(),
                    "UTF-8"));
            String readLine = "";
            while ((readLine = in.readLine()) != null) {
                sb.append(readLine);
            }
            if (in != null) {
                in.close();
            }
            if (uc != null) {
                uc.disconnect();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (uc != null) {
                uc.disconnect();
            }
        }
        return sb.toString();
    }

    /** * * HTTP协议POST请求方法 */
    public static String httpMethodPost(String url, String params) {
        StringBuffer sb = new StringBuffer();
        URL urls;
        HttpsURLConnection uc = null;
        BufferedReader in = null;
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[] { new MyTrustManager() },
                    new java.security.SecureRandom());
            urls = new URL(url);
            uc = (HttpsURLConnection) urls.openConnection();
            uc.setSSLSocketFactory(sc.getSocketFactory());
            uc.setHostnameVerifier(new MyHostnameVerifier());
            uc.setRequestMethod("POST");
            uc.setDoOutput(true);
            uc.setDoInput(true);
            uc.setUseCaches(false);
            uc.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            uc.connect();
            DataOutputStream out = new DataOutputStream(uc.getOutputStream());
            out.write(params.getBytes("UTF-8"));
            out.flush();
            out.close();
            in = new BufferedReader(new InputStreamReader(uc.getInputStream(),
                    "UTF-8"));
            String readLine = "";
            while ((readLine = in.readLine()) != null) {
                sb.append(readLine);
            }
            if (in != null) {
                in.close();
            }
            if (uc != null) {
                uc.disconnect();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (uc != null) {
                uc.disconnect();
            }
        }
        return sb.toString();
    }

    /** * * HTTP协议POST请求方法 */
    public static String httpMethodPost(String url,
                                        TreeMap<String, String> paramsMap) {
        String params = null;
        if (null != paramsMap) {
            params = getParamStr(paramsMap);
        }

        StringBuffer sb = new StringBuffer();
        URL urls;
        HttpsURLConnection uc = null;
        BufferedReader in = null;
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[] { new MyTrustManager() },
                    new java.security.SecureRandom());
            urls = new URL(url);
            uc = (HttpsURLConnection) urls.openConnection();
            uc.setSSLSocketFactory(sc.getSocketFactory());
            uc.setHostnameVerifier(new MyHostnameVerifier());
            uc.setRequestMethod("POST");
            uc.setDoOutput(true);
            uc.setDoInput(true);
            uc.setUseCaches(false);
            uc.setRequestProperty("Content-Type",
                    "application/json");
            uc.connect();
            DataOutputStream out = new DataOutputStream(uc.getOutputStream());
            out.write(params.getBytes("UTF-8"));
            out.flush();
            out.close();
            in = new BufferedReader(new InputStreamReader(uc.getInputStream(),
                    "UTF-8"));
            String readLine = "";
            while ((readLine = in.readLine()) != null) {
                sb.append(readLine);
            }
            if (in != null) {
                in.close();
            }
            if (uc != null) {
                uc.disconnect();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (uc != null) {
                uc.disconnect();
            }
        }
        return sb.toString();
    }

    /** * * HTTP协议POST请求添加参数的封装方法 */
    private static String getParamStr(TreeMap<String, String> paramsMap) {
        StringBuilder param = new StringBuilder();
        for (Iterator<Map.Entry<String, String>> it = paramsMap.entrySet()
                .iterator(); it.hasNext();) {
            Map.Entry<String, String> e = it.next();
            param.append("&").append(e.getKey()).append("=")
                    .append(e.getValue());
        }
        return param.toString().substring(1);
    }
}