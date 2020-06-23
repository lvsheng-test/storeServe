package org.pack.store.utils.common;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Base64Util {

    /*编码
    * */
    public static String encode(String text) {
        final BASE64Encoder encoder = new BASE64Encoder();
        final byte[] textByte;
        try {
            textByte = text.getBytes("UTF-8");
            //编码
            final String encodedText = encoder.encode(textByte);

            return encodedText;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*解码
    * */
    public static String decode(String encodedText) {
        final BASE64Decoder decoder = new BASE64Decoder();
        try {
            String result = new String(decoder.decodeBuffer(encodedText), "UTF-8");
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args) {
        String text = "http://www.99bicycle.com/download/?b=500600000";
        String encodeText = encode(text);
        System.out.println("编码:" + encodeText);
        String decodeText = decode(encodeText);
        System.out.println("解码:" + decodeText.length());
    }
}
