package org.pack.store.utils;

import org.apache.commons.lang.StringUtils;
import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class LocationUtils {
    // 平均半径,单位：m
    private static final double EARTH_RADIUS = 6371393;

    public static void main(String[] args) {
        //北京 将宅十字路口东门116.417304,39.9652
        Point2D pointDD = new Point2D.Double(121.26778,31.199726);
        // 北京 将宅口  121.1666,31.164293
        Point2D pointXD = new Point2D.Double(121.1666,31.164293);

        /*double distanceDouble = getDistanceDouble(pointDD, pointXD);
        System.out.println(distanceDouble);
        System.out.println(distanceDouble<200);
        System.out.println();

        double distanceDouble2 = getDistanceDoubleBy2(pointDD, pointXD);
        System.out.println(distanceDouble2);
        System.out.println(distanceDouble2<200);
        System.out.println();*/

        Long distance = getDistance(pointDD, pointXD);
        System.out.println(distance);
        System.out.println(distance<200);
        System.out.println(Math.round( distance / 100d) / 10d );
    }

    /**
     * 获取当前位置距离商家位置的公里数
     * @param lng  经度
     * @param lat  纬度
     * @param address  目的地地址
     * @return
     */
    public static String getDistance(double lng,double lat,String address){
        try{
            String coordinate = EntCoordSyncJob.getCoordinate(address);
            if (StringUtils.isNotEmpty(coordinate)){
                String [] arg = coordinate.split(",");
                System.out.println("经度" + arg[0] + "-纬度：" + arg[1]);
                Point2D pointDD = new Point2D.Double(lng,lat);
                double longitude =Double.parseDouble(arg[0]);
                double latitude = Double.parseDouble(arg[1]);
                Point2D pointXD = new Point2D.Double(longitude,latitude);
                Long distance = getDistance(pointDD, pointXD);
                return Math.round( distance / 100d) / 10d+"km";
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过AB点经纬度获取距离 整数
     * @param pointA A点(经，纬)
     * @param pointB B点(经，纬)
     * @return 距离(单位：米)
     */
    public static long getDistance(Point2D pointA, Point2D pointB) {
        // 经纬度（角度）转弧度。弧度用作参数，以调用Math.cos和Math.sin
        // A经弧度
        double radiansAX = Math.toRadians(pointA.getX());
        // A纬弧度
        double radiansAY = Math.toRadians(pointA.getY());
        // B经弧度
        double radiansBX = Math.toRadians(pointB.getX());
        // B纬弧度
        double radiansBY = Math.toRadians(pointB.getY());
        // 公式中“cosβ1cosβ2cos（α1-α2）+sinβ1sinβ2”的部分，得到∠AOB的cos值
        double cos = Math.cos(radiansAY) * Math.cos(radiansBY) * Math.cos(radiansAX - radiansBX)
                + Math.sin(radiansAY) * Math.sin(radiansBY);
        // 反余弦值
        double acos = Math.acos(cos);
        // 最终结果
        double h = EARTH_RADIUS * acos;

        //四舍五入
        long f1 = Math.round(h);
        //保留小数后两位
       /* BigDecimal b = new BigDecimal(h);
        double f1 = b.setScale(2, RoundingMode.HALF_UP).doubleValue();*/
        return f1;
    }


    /**
     * 通过AB点经纬度获取距离  真实距离
     * @param pointA A点(经，纬)
     * @param pointB B点(经，纬)
     * @return 距离(单位：米)
     */
    public static double  getDistanceDouble(Point2D pointA, Point2D pointB) {
        // 经纬度（角度）转弧度。弧度用作参数，以调用Math.cos和Math.sin
        // A经弧度
        double radiansAX = Math.toRadians(pointA.getX());
        // A纬弧度
        double radiansAY = Math.toRadians(pointA.getY());
        // B经弧度
        double radiansBX = Math.toRadians(pointB.getX());
        // B纬弧度
        double radiansBY = Math.toRadians(pointB.getY());
        // 公式中“cosβ1cosβ2cos（α1-α2）+sinβ1sinβ2”的部分，得到∠AOB的cos值
        double cos = Math.cos(radiansAY) * Math.cos(radiansBY) * Math.cos(radiansAX - radiansBX)
                + Math.sin(radiansAY) * Math.sin(radiansBY);
        // 反余弦值
        double acos = Math.acos(cos);
        // 最终结果
        double h = EARTH_RADIUS * acos;
        return h;
    }
    /**
     * 通过AB点经纬度获取距离  小数后俩位
     * @param pointA A点(经，纬)
     * @param pointB B点(经，纬)
     * @return 距离(单位：米)
     */
    public static double  getDistanceDoubleBy2(Point2D pointA, Point2D pointB) {
        // 经纬度（角度）转弧度。弧度用作参数，以调用Math.cos和Math.sin
        // A经弧度
        double radiansAX = Math.toRadians(pointA.getX());
        // A纬弧度
        double radiansAY = Math.toRadians(pointA.getY());
        // B经弧度
        double radiansBX = Math.toRadians(pointB.getX());
        // B纬弧度
        double radiansBY = Math.toRadians(pointB.getY());
        // 公式中“cosβ1cosβ2cos（α1-α2）+sinβ1sinβ2”的部分，得到∠AOB的cos值
        double cos = Math.cos(radiansAY) * Math.cos(radiansBY) * Math.cos(radiansAX - radiansBX)
                + Math.sin(radiansAY) * Math.sin(radiansBY);
        // 反余弦值
        double acos = Math.acos(cos);
        // 最终结果
        double h = EARTH_RADIUS * acos;
        //保留小数后两位
        BigDecimal b = new BigDecimal(h);
        double f1 = b.setScale(2, RoundingMode.HALF_UP).doubleValue();
        return f1;
    }

}
