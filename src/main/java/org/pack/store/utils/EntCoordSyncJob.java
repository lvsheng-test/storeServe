package org.pack.store.utils;

import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;


public class EntCoordSyncJob {

    static String AK = "P2LmBs6Z7aFPGSvzlnRmXoZUKKfLHcf9"; // 百度地图密钥

    public static void main(String[] args) {
        String dom = "上海市青浦区徐泾镇徐祥路东316号";
        String coordinate = getCoordinate(dom);
        //121.26778,31.199726
       System.out.println("'" + dom + "'的经纬度为：" + coordinate);
        // System.err.println("######同步坐标已达到日配额6000限制，请明天再试！#####");
    }

    // 调用百度地图API根据地址，获取坐标
    public static String getCoordinate(String address) {
        if (address != null && !"".equals(address)) {
            address = address.replaceAll("\\s*", "").replace("#", "栋");
            //String url = "http://api.map.baidu.com/geocoder/v3/?address=" + address + "&output=json&ak=" + AK;
            String url = "http://api.map.baidu.com/geocoding/v3/?address="+address+"&output=json&ak="+AK+"&callback=showLocation";
            String json = loadJSON(url);
            String newJSON = json.substring(json.indexOf("(")+1,json.length()-1);

            if (json != null && !"".equals(json)) {
                JSONObject obj = JSONObject.fromObject(newJSON);
                if ("0".equals(obj.getString("status"))) {
                    double lng = obj.getJSONObject("result").getJSONObject("location").getDouble("lng"); // 经度
                    double lat = obj.getJSONObject("result").getJSONObject("location").getDouble("lat"); // 纬度
                    DecimalFormat df = new DecimalFormat("#.######");
                    return df.format(lng) + "," + df.format(lat);
                }
            }
        }
        return null;
    }

    public static String loadJSON(String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {} catch (IOException e) {}
        return json.toString();
    }
}
