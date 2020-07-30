package org.pack.store.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.pack.store.utils.common.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

@Service
public class UploadUtil {
	
	private Logger logger = LoggerFactory.getLogger(UploadUtil.class);


	// 读取图片流，返回图片路径
	public String savePic(String string) {
		BASE64Decoder decoder = new BASE64Decoder();
		StringBuffer path = new StringBuffer();
		OutputStream out = null;
		String result = "";
		try {
			byte[] srtbyte = decoder.decodeBuffer(string);
			for (int i = 0; i < srtbyte.length; i++) {
				if (srtbyte[i] < 0) {
					srtbyte[i] += 256;
				}
			}
			String photoPath = "/root/image";
			String time = new SimpleDateFormat("yyyyMMdd").format(new Date());
			path.append(photoPath);
			String randomString = UuidUtil.getUuid();
			result =
					(System.getProperty("file.separator") + time + System.getProperty("file.separator") + randomString + ".png")
							.replace("\\", "/");
			path.append(result).append(".png");
			File file = new File(path.toString());
			if (!file.exists()) {
				UploadUtil.createNewFile(file);
			}
			out = new FileOutputStream(path.toString());
			out.write(srtbyte);
		}
		catch (Exception e) {
			logger.error("文件上传失败", e);
		}
		finally {
			try {
				out.flush();
				out.close();
			}
			catch (IOException e) {
				logger.error("文件上传失败", e);
			}
		}
		return result;
	}

	public static boolean createNewFile(File f) throws IOException {
		if (null == f || f.exists())
			return false;
		makeDir(f.getParentFile());
		return f.createNewFile();
	}

	public static boolean makeDir(File dir) {
		if (null == dir || dir.exists())
			return false;
		return dir.mkdirs();
	}

	public String UploadFile(MultipartFile meFile){
		StringBuffer path = new StringBuffer();
		//String photoPath = "/root/image";
		String photoPath = "D:/AAA/photo";
		String time = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String result = "";
		if (meFile != null) {
			try {
				path.append(photoPath);
				result =
						(System.getProperty("file.separator") + time + System.getProperty("file.separator") + UuidUtil.getUuid() + ".png")
								.replace("\\", "/");
				path.append(result);
				File file = new File(path.toString());
				if (!file.exists()) {
					UploadUtil.createNewFile(file);
				}
				meFile.transferTo(file);
			} catch (IllegalStateException e) {
				logger.error("==>上传图片异常",e);
			} catch (IOException e) {
				logger.error("==>上传图片IO异常",e);
			}finally{
			}
		}
		return result;
	}
}
