/**
 * 
 */
package org.pack.store.utils.common;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletResponse;
import java.awt.geom.Point2D;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class CommonUtils {

	public static boolean isEmptyString(String value) {
		if (value == null || value.length() == 0) {
			return true;
		}
		return false;
	}

	public static int parseInt(String string, int def) {
		if (isEmptyString(string))
			return def;
		int num = def;
		try {
			num = Integer.parseInt(string);
		} catch (Exception e) {
			num = def;
		}
		return num;
	}

	public static long parseLong(String string, long def) {
		if (isEmptyString(string))
			return def;
		long num;
		try {
			num = Long.parseLong(string);
		} catch (Exception e) {
			num = def;
		}
		return num;
	}

	public static double parseDouble(String string, double def) {
		if (isEmptyString(string))
			return def;
		double num;
		try {
			num = Double.parseDouble(string);
		} catch (Exception e) {
			num = def;
		}
		return num;
	}

	public static float parseFloat(String string, float def) {
		if (isEmptyString(string))
			return def;
		float num;
		try {
			num = Float.parseFloat(string);
		} catch (Exception e) {
			num = def;
		}
		return num;
	}

	public static String getCurrentTimeFormat(String format) {
		return getTimeFormat(new Date(), format);
	}

	public static String getCurrentAndroidTime(String time){
		Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);//date 换成已经已知的Date对象
        cal.add(Calendar.HOUR_OF_DAY, -8);// before 8 hour
        SimpleDateFormat format = new SimpleDateFormat(time);
        return format.format(cal.getTime());
        
	}
	
	/**
	 * @param date
	 * @param string
	 * @return
	 */
	public static String getTimeFormat(Date date, String string) {
		SimpleDateFormat sdFormat;
		if (isEmptyString(string)) {
			sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		} else {
			sdFormat = new SimpleDateFormat(string);
		}
		try {
			return sdFormat.format(date);
		} catch (Exception e) {
			return "";
		}
	}

	public static int hasNext(List<?> a) {
		if (a != null && a.size() > 0) {
			return 1;
		}
		return 0;
	}

	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			// 使用MD5创建MessageDigest对象
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte b = md[i];
				str[k++] = hexDigits[b >> 4 & 0xf];
				str[k++] = hexDigits[b & 0xf];
			}
			return new String(str).toUpperCase();
		} catch (Exception e) {
			return null;
		}
	}

	public static Date getDateFormat(String date, String format) {
		if (isEmptyString(date))
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void downloadExcel(String filepath, String downloadname, HttpServletResponse response) {
		try {
			// String file= filepath;
			File file = new File(filepath);
			@SuppressWarnings("resource")
			InputStream is = new FileInputStream(file);
			response.reset(); // 必要地清除response中的缓存信息
			response.setHeader("Content-Disposition", "attachment; filename=" + new String(downloadname.getBytes(), "ISO_8859_1") + CommonUtils.getTimeFormat(new Date(), "yyyyMMddhhmm") + ".xls");
			response.addHeader("Content-Length", "" + file.length());
			response.setContentType("applicationnd.ms-excel");// 根据个人这个是下载文件的类型
			javax.servlet.ServletOutputStream out = response.getOutputStream();
			byte[] content = new byte[1024];
			int length = 0;
			while ((length = is.read(content)) != -1) {
				out.write(content, 0, length);
			}
			out.write(content);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 删除单个文件
	 * 
	 * @param sPath
	 *            被删除文件的路径
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static void deleteFile(String sPath) {
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
		}
	}
	
	/**
	 * 随机字符串
	 * @return
	 */
	public static String randomString(){
		StringBuffer array = new StringBuffer();
		for (int i = 0; i <= 9; i++) {
			array.append(i);
		}
		//小写字母暂时不要
//		for (int i = (int) 'a'; i <= (int) 'z'; i++) {
//			array.append((char) i);
//		}
		for (int i = (int) 'A'; i <= (int) 'Z'; i++) {
			array.append((char) i);
		}
		int length = array.length();

		// 假设n现在为100
		int n = 30;
		// 存储最后生成的字符串
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < n; i++) {
			// 获得随机位置的字符
			char c = array.charAt((int) (Math.random() * length));
			str.append(c);
		}
		return str.toString();
	}
	
	public static String stringToXml(Map<String,Object> parameters){
		if(parameters!=null){
			StringBuffer xml = new StringBuffer();
			xml.append("<xml>");  
			  
			xml.append("<appid><![CDATA[");  
            xml.append(parameters.get("appid"));  
            xml.append("]]></appid>");  
            
            xml.append("<body><![CDATA[");  
            xml.append(parameters.get("body"));  
            xml.append("]]></body>");  
            
            xml.append("<fee_type><![CDATA[");  
            xml.append(parameters.get("fee_type"));  
            xml.append("]]></fee_type>");
  
            xml.append("<mch_id><![CDATA[");  
            xml.append(parameters.get("mch_id"));  
            xml.append("]]></mch_id>");  
  
            xml.append("<nonce_str><![CDATA[");  
            xml.append(parameters.get("nonce_str"));  
            xml.append("]]></nonce_str>");  
  
            xml.append("<notify_url><![CDATA[");  
            xml.append(parameters.get("notify_url"));  
            xml.append("]]></notify_url>");
            
            xml.append("<out_trade_no><![CDATA[");  
            xml.append(parameters.get("out_trade_no"));  
            xml.append("]]></out_trade_no>");  
            
            xml.append("<spbill_create_ip><![CDATA[");  
            xml.append(parameters.get("spbill_create_ip"));  
            xml.append("]]></spbill_create_ip>"); 
  
            xml.append("<total_fee><![CDATA[");  
            xml.append(parameters.get("total_fee"));  
            xml.append("]]></total_fee>");  
  
            xml.append("<trade_type><![CDATA[");  
            xml.append(parameters.get("trade_type"));  
            xml.append("]]></trade_type>");  
  
            xml.append("<sign><![CDATA[");  
            xml.append(parameters.get("sign"));  
            xml.append("]]></sign>");  
  
  
            xml.append("</xml>");  
            return xml.toString();  
		}else{
			return "";
		}
		
		
	}
	
	/**
	 * http传输获取数据
	 * @param requestUrl
	 * @param requestMethod
	 * @param output
	 * @return
	 */
	public static String httpsRequest(String requestUrl, String requestMethod, String output) {  
        try{  
            URL url = new URL(requestUrl);  
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();  
            connection.setDoOutput(true);  
            connection.setDoInput(true);  
            connection.setUseCaches(false);  
            connection.setRequestMethod(requestMethod);  
            if (null != output) {  
                OutputStream outputStream = connection.getOutputStream();  
                outputStream.write(output.getBytes("UTF-8"));  
                outputStream.close();  
            }  
            // 从输入流读取返回内容  
            InputStream inputStream = connection.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
            String str = null;  
            StringBuffer buffer = new StringBuffer();  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }  
            bufferedReader.close();  
            inputStreamReader.close();  
            inputStream.close();  
            inputStream = null;  
            connection.disconnect();  
            return buffer.toString();  
        }catch(Exception ex){  
            ex.printStackTrace();  
        }  
  
        return "";  
    }  
	
	public static  String dateDiff1(String startTime, String endTime) {
		//按照传入的格式生成一个simpledateformate对象
		String time = "";
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long nd = 1000*24*60*60;//一天的毫秒数
		long nh = 1000*60*60;//一小时的毫秒数
		long nm = 1000*60;//一分钟的毫秒数
		long ns = 1000;//一秒钟的毫秒数
		//获得两个时间的毫秒时间差异
		long diff;
		try {
			diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
			long day = diff/nd;//计算差多少天
			long hour = diff%nd/nh;//计算差多少小时
			long min = diff%nd%nh/nm;//计算差多少分钟
			long sec = diff%nd%nh%nm/ns;//计算差多少秒//输出结果
			if(day==0){
				if(hour==0){
					if(min < 10){
						time = "0" + min + ":";
					}else{
						time = String.valueOf(min) + ":";
					}
					if(sec < 10){
						time = time + "0" + sec;
					}else{
						time = time + sec;
					}
				}else{
					if(sec < 10){
						time=(hour*60 + min) +":" + "0" + sec;
					}else{
						time=(hour*60 + min) +":" + sec; 
					}
					
				}
			}else{
				if(sec < 10){
					time=(day*24 + hour*60 + min) +":" + "0" + sec;
				}else{
					time=(day*24 + hour*60 + min) +":" + sec;
				}
				
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return time;
	}	
	
	public static  String dateDiff(String startTime, String endTime) {
		//按照传入的格式生成一个simpledateformate对象
		String time = "";
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long nd = 1000*24*60*60;//一天的毫秒数
		long nh = 1000*60*60;//一小时的毫秒数
		long nm = 1000*60;//一分钟的毫秒数
//		long ns = 1000;//一秒钟的毫秒数
		//获得两个时间的毫秒时间差异
		long diff;
		try {
			diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
			long day = diff/nd;//计算差多少天
			long hour = diff%nd/nh;//计算差多少小时
			long min = diff%nd%nh/nm;//计算差多少分钟
//			long sec = diff%nd%nh%nm/ns;//计算差多少秒//输出结果
//			System.out.println("时间相差："+day+"天"+hour+"小时"+min+"分钟");
			if(day==0){
				if(hour==0){
					time=min+"分钟";
				}else{
					time=hour+"小时"+min+"分钟";
				}
			}else{
				time=day+"天"+hour+"小时"+min+"分钟";
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return time;
	}
	
	public static long dateDifferent(Date startTime,Date endTime) throws ParseException{
		long diffMinutes = 0;
		long diff = endTime.getTime() - startTime.getTime();
		diffMinutes = diff / (60 * 1000) % 60;
		return diffMinutes;
//		long diffSeconds = diff / 1000 % 60;
//      long diffMinutes = diff / (60 * 1000) % 60;
//      long diffHours = diff / (60 * 60 * 1000) % 24;
//      long diffDays = diff / (24 * 60 * 60 * 1000);
		 
	}

	public static long dateDiffForSec(Date startTime,Date endTime) throws ParseException{
		long diffSeconds = 0;
		long diff = endTime.getTime() - startTime.getTime();
		diffSeconds = diff / 1000;
		return diffSeconds;

	}

	 /**
     * 字符串转换为Ascii
     * @param value
     * @return
     */
    public static String stringToAscii(String value)  
    {  
        StringBuffer sbu = new StringBuffer();  
        char[] chars = value.toCharArray();   
        for (int i = 0; i < chars.length; i++) {  
            if(i != chars.length - 1)  
            {  
                sbu.append((int)chars[i]).append(",");  
            }  
            else {  
                sbu.append((int)chars[i]);  
            }  
        }  
        return sbu.toString();
    }
	/**
	 * @param POST_URL
	 * @param content
	 * @throws IOException
	 */
	public static String readContentFromPost(String POST_URL,String content) throws IOException {
        URL postUrl = new URL(POST_URL);
        // 打开连接
        HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
//        connection.setRequestProperty("Content-Type",
//                "application/x-www-form-urlencoded");
        connection.setRequestProperty("contentType", "utf-8");
        connection.connect();
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
//        out.writeBytes(content); 
        out.write(content.getBytes("utf-8"));
        out.flush();
        out.close(); // flush and close
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                connection.getInputStream(),"utf-8"));
        String line="";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            return line;
        }
               
        reader.close();
        connection.disconnect();
        
        return line;
    }	
	
	public static String readContentFromURL(String POST_URL) throws IOException {
		URL url = new URL(POST_URL);//要调用的url
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");//设置get方式获取数据
        conn.setConnectTimeout(10000);
        conn.setReadTimeout(10000);
//        conn.setRequestProperty("Host", SERVICE_HOST);       
        String lines = null;
        if (conn.getResponseCode() == 200) {//如果连接成功
            BufferedReader br = new BufferedReader(new InputStreamReader(conn
                    .getInputStream()));//创建流
            
            while ((lines = br.readLine()) != null) {
                System.out.println(lines.replaceAll("&[lg]t;",""));//将读取到的数据打印
                return lines;
            }
            br.close();
            
        }
        conn.disconnect();	
        return lines;
//		URL postUrl = new URL(POST_URL);
//		HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
//		connection.setDoOutput(true);
//        connection.setDoInput(true);
//        connection.setRequestMethod("POST");
//        connection.setUseCaches(false);
//        connection.setInstanceFollowRedirects(true);
//        connection.setRequestProperty("Content-Type",
//                "application/x-www-form-urlencoded");
//        connection.connect();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(
//                connection.getInputStream(),"utf-8"));
//        String line="";
//        while ((line = reader.readLine()) != null) {
//            System.out.println(line);
//            return line;
//        }
//               
//        reader.close();
//        connection.disconnect();
//        
//        return line;
//     }
	}	
	
	public static byte[] intToByteArray(int i) {   
		  byte[] result = new byte[4];   
		  //由高位到低位
		  result[0] = (byte)((i >> 24) & 0xFF);
		  result[1] = (byte)((i >> 16) & 0xFF);
		  result[2] = (byte)((i >> 8) & 0xFF); 
		  result[3] = (byte)(i & 0xFF);
		  return result;
	}
	
	public static byte GetCheckXor(byte[] data, int pos, int len) {
		byte A = 0;
		for (int i = pos; i < len; i++) {
			A ^= data[i];
		}
		return A;
	}
	
	public static String Date2TimeStamp(String dateStr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(dateStr).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
	
	 public static long getTimestamp(String time) {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
	        try {
	            return sdf.parse(time).getTime();
	        } catch (ParseException e) {
	            e.printStackTrace();
	            return -1;
	        }
	 }

	/**
	 * 判断点是否在多边形内
	 * @param point 检测点
	 * @param pts   多边形的顶点
	 * @return      点在多边形内返回true,否则返回false
	 */
	public static boolean IsPtInPoly(Point2D.Double point, List<Point2D.Double> pts){

		int N = pts.size();
		boolean boundOrVertex = true; //如果点位于多边形的顶点或边上，也算做点在多边形内，直接返回true
		int intersectCount = 0;//cross points count of x
		double precision = 2e-10; //浮点类型计算时候与0比较时候的容差
		Point2D.Double p1, p2;//neighbour bound vertices
		Point2D.Double p = point; //当前点

		p1 = pts.get(0);//left vertex
		for(int i = 1; i <= N; ++i){//check all rays
			if(p.equals(p1)){
				return boundOrVertex;//p is an vertex
			}

			p2 = pts.get(i % N);//right vertex
			if(p.x < Math.min(p1.x, p2.x) || p.x > Math.max(p1.x, p2.x)){//ray is outside of our interests
				p1 = p2;
				continue;//next ray left point
			}

			if(p.x > Math.min(p1.x, p2.x) && p.x < Math.max(p1.x, p2.x)){//ray is crossing over by the algorithm (common part of)
				if(p.y <= Math.max(p1.y, p2.y)){//x is before of ray
					if(p1.x == p2.x && p.y >= Math.min(p1.y, p2.y)){//overlies on a horizontal ray
						return boundOrVertex;
					}

					if(p1.y == p2.y){//ray is vertical
						if(p1.y == p.y){//overlies on a vertical ray
							return boundOrVertex;
						}else{//before ray
							++intersectCount;
						}
					}else{//cross point on the left side
						double xinters = (p.x - p1.x) * (p2.y - p1.y) / (p2.x - p1.x) + p1.y;//cross point of y
						if(Math.abs(p.y - xinters) < precision){//overlies on a ray
							return boundOrVertex;
						}

						if(p.y < xinters){//before ray
							++intersectCount;
						}
					}
				}
			}else{//special case when ray is crossing through the vertex
				if(p.x == p2.x && p.y <= p2.y){//p crossing over p2
					Point2D.Double p3 = pts.get((i+1) % N); //next vertex
					if(p.x >= Math.min(p1.x, p3.x) && p.x <= Math.max(p1.x, p3.x)){//p.x lies between p1.x & p3.x
						++intersectCount;
					}else{
						intersectCount += 2;
					}
				}
			}
			p1 = p2;//next ray left point
		}

		if(intersectCount % 2 == 0){//偶数在多边形外
			return false;
		} else { //奇数在多边形内
			return true;
		}

	}

	public static int getRandom(Integer x){
		Random random = new Random();
		return random.nextInt(x);
	}

	public static String getMemberCard(String memberName){
		String val = "";
		Random random = new Random();
		for (int i = 0; i < 16; i++) {
			// 输出字母还是数字
			val += String.valueOf(random.nextInt(10));
		}
		if (memberName.equals("消费卡")){
			return "XF"+val;
		}
		if (memberName.equals("会员卡")){
			return "HY"+val;
		}
		if (memberName.equals("押金卡")){
			return "YJ"+val;
		}
		return null;
	}

	/**
	 * 生成六位数邀请码
	 * @return
	 */
	public static int getInvitationCode(){
		for (int i = 0; i <= 200; i++)
		{
			int intFlag = (int)(Math.random() * 1000000);
			String flag = String.valueOf(intFlag);
			if (flag.length() == 6 && flag.substring(0, 1).equals("9"))
			{
				System.out.println(intFlag);
				return intFlag;
			}
			else
			{
				intFlag = intFlag + 100000;
				System.out.println(intFlag);
				return intFlag;
			}
		}
		return 0;
	}


	public static void main(String[] args) {
		//String time = getMemberCard("消费卡");
		int intFlag = getInvitationCode();
		System.out.println("邀请码:" + intFlag);
	}

}
