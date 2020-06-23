package org.pack.store.utils.HttpClient;

import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class HttpRequestProxy {
	/**
	 * ���ӳ�ʱ
	 */
	private static int connectTimeOut = 5000;

	/**
	 * ��ȡ��ݳ�ʱ
	 */
	private static int readTimeOut = 10000;

	/**
	 * �������
	 */
	private static String requestEncoding = "UTF-8";

	/**
	 * <pre>
	 * ���ʹ�����GET��HTTP����
	 * </pre>
	 * 
	 * @param reqUrl
	 *            HTTP����URL
	 * @param parameters
	 *            ����ӳ���
	 * @return HTTP��Ӧ���ַ�
	 * @throws UnsupportedEncodingException 
	 */
	 
	public static String doGet(String reqUrl, Map parameters,String recvEncoding)  {
		StringBuffer params = new StringBuffer();
		for (Iterator iter = parameters.entrySet().iterator(); iter.hasNext();) {
			Entry element = (Entry) iter.next();
			if(element!=null && element.getValue()!=null && !"".equals(element.getValue())){
				params.append(element.getKey().toString());
				params.append("=");
				try {
					params.append(URLEncoder.encode(element.getValue().toString(),HttpRequestProxy.requestEncoding));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				params.append("&");
			}
		}
		if (params.length() > 0) {
			params = params.deleteCharAt(params.length() - 1);
		}
		if(StringUtils.isNotBlank(params.toString())){
			reqUrl += "?" + params.toString();
		}

		
		return doGet(reqUrl, recvEncoding);
	}

	/**
	 * <pre>
	 * ���Ͳ�������GET��HTTP����
	 * </pre>
	 * 
	 * @param reqUrl
	 *            HTTP����URL
	 * @return HTTP��Ӧ���ַ�
	 */
	public static String doGet(String reqUrl, String recvEncoding) {
		HttpURLConnection url_con = null;
		String responseContent = null;
		try {

			URL url = new URL(reqUrl);
			url_con = (HttpURLConnection) url.openConnection();
			url_con.setRequestMethod("GET");
			url_con.setConnectTimeout(HttpRequestProxy.connectTimeOut);
			url_con.setReadTimeout(HttpRequestProxy.readTimeOut);
			url_con.setDoOutput(true);
			InputStream in = url_con.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(in,recvEncoding));
			String tempLine = rd.readLine();
			StringBuffer temp = new StringBuffer();
			String crlf = System.getProperty("line.separator");
			while (tempLine != null) {
				temp.append(tempLine);
				temp.append(crlf);
				tempLine = rd.readLine();
			}
			responseContent = temp.toString();
			rd.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (url_con != null) {
				url_con.disconnect();
			}
		}
		return decodeUnicode(responseContent);
	}
	/**
	 * ���ʹ�����POST��HTTP����
	 * 
	 * @param reqUrl
	 *            HTTP����URL
	 * @param parameters
	 *            ����ӳ���
	 * @return HTTP��Ӧ���ַ�
	 */
	public static String doPost(String reqUrl, Map parameters,String recvEncoding) {
		HttpURLConnection url_con = null;
		String responseContent = null;
		try {
			StringBuffer params = new StringBuffer();
			for (Iterator iter = parameters.entrySet().iterator(); iter.hasNext();) {
				Entry element = (Entry) iter.next();

				if(element!=null && element.getValue()!=null && StringUtils.isNotBlank(element.getValue().toString())){
					params.append(element.getKey().toString());
					params.append("=");
					params.append(URLEncoder.encode(element.getValue().toString(),HttpRequestProxy.requestEncoding));
					params.append("&");
				}
			}
			if (params.length() > 0) {
				params = params.deleteCharAt(params.length() - 1);
			}
			System.out.println(params);
			URL url = new URL(reqUrl);

			url_con = (HttpURLConnection) url.openConnection();
			url_con.setRequestMethod("POST");
			url_con.setConnectTimeout(HttpRequestProxy.connectTimeOut);
			url_con.setReadTimeout(HttpRequestProxy.readTimeOut);

			url_con.setDoOutput(true);
			byte[] b = params.toString().getBytes();
			url_con.getOutputStream().write(b, 0, b.length);

			url_con.getOutputStream().flush();
			url_con.getOutputStream().close();

			InputStream in = url_con.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(in,recvEncoding));
			String tempLine = rd.readLine();
			StringBuffer tempStr = new StringBuffer();
			String crlf = System.getProperty("line.separator");
			while (tempLine != null) {
				tempStr.append(tempLine);
				tempStr.append(crlf);
				tempLine = rd.readLine();
			}
			responseContent = tempStr.toString();
			rd.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (url_con != null) {
				url_con.disconnect();
			}
		}
		return decodeUnicode(responseContent);
	}
	/**
	 * ���ʹ�����POST��HTTP����
	 *
	 * @param reqUrl
	 *            请求的url
	 * @param json
	 *            Json类型的数据
	 * @return HTTP��Ӧ���ַ�
	 */
	public static String doPost(String reqUrl, JsonObject json, String recvEncoding) {
		return doPost(reqUrl,json.toString(),recvEncoding);
	}

	public static String doPost(String reqUrl, String json,String recvEncoding) {
		HttpURLConnection url_con = null;
		String responseContent = null;
		try{
			URL url = new URL(reqUrl);
			url_con = (HttpURLConnection) url.openConnection();
			//设置连接超时时间
			url_con.setConnectTimeout(HttpRequestProxy.connectTimeOut);
			//设置读取超时时间
			url_con.setReadTimeout(HttpRequestProxy.readTimeOut);
			// 设置允许输出
			url_con.setDoOutput(true);
			// 设置不用缓存
			url_con.setUseCaches(false);
			// 设置传递方式
			url_con.setRequestMethod("POST");
			// 设置维持长连接
			url_con.setRequestProperty("Connection", "Keep-Alive");
			// 设置文件类型:
			url_con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			//转换为字节数组
			byte[] b = json.getBytes();

			url_con.getOutputStream().write(b, 0, b.length);

			url_con.getOutputStream().flush();

			url_con.getOutputStream().close();

			InputStream in = url_con.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(in,recvEncoding));
			String tempLine = rd.readLine();
			StringBuffer tempStr = new StringBuffer();
			String crlf = System.getProperty("line.separator");
			while (tempLine != null) {
				tempStr.append(tempLine);
				tempStr.append(crlf);
				tempLine = rd.readLine();
			}
			responseContent = tempStr.toString();
			rd.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (url_con != null) {
				url_con.disconnect();
			}
		}
		return decodeUnicode(responseContent);
	}
	/**
	 * @return ���ӳ�ʱ(����)
	 */
	public static int getConnectTimeOut() {
		return HttpRequestProxy.connectTimeOut;
	}

	/**
	 * @param connectTimeOut
	 *            ���ӳ�ʱ(����)
	 */
	public static void setConnectTimeOut(int connectTimeOut) {
		HttpRequestProxy.connectTimeOut = connectTimeOut;
	}
	
	/**
	 * @return ��ȡ��ݳ�ʱ(����)
	 */
	public static int getReadTimeOut() {
		return HttpRequestProxy.readTimeOut;
	}


	/**
	 * @param readTimeOut
	 *            ��ȡ��ݳ�ʱ(����)
	 */
	public static void setReadTimeOut(int readTimeOut) {
		HttpRequestProxy.readTimeOut = readTimeOut;
	}

	/**
	 * @return �������
	 */
	public static String getRequestEncoding() {
		return requestEncoding;
	}
	
	/**
	 * @param requestEncoding
	 *            �������
	 */
	public static void setRequestEncoding(String requestEncoding) {
		HttpRequestProxy.requestEncoding = requestEncoding;
	}
	/**
     * 
     * unicode ת���� ����
     * @param theString
     * @return
     */
    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                            value = (value << 4) + aChar - '0';
                            break;
                        case 'a':
                        case 'b':
                        case 'c':
                        case 'd':
                        case 'e':
                        case 'f':
                            value = (value << 4) + 10 + aChar - 'a';
                            break;
                        case 'A':
                        case 'B':
                        case 'C':
                        case 'D':
                        case 'E':
                        case 'F':
                            value = (value << 4) + 10 + aChar - 'A';
                            break;
                        default:
                            throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't'){
						aChar = '\t';
					}else if (aChar == 'r'){
						aChar = '\r';
					}else if (aChar == 'n'){
						aChar = '\n';
					}else if (aChar == 'f'){
						aChar = '\f';
					}
                    outBuffer.append(aChar);
                }
            } else{
				outBuffer.append(aChar);
			}

        }
        return outBuffer.toString();
    }
}