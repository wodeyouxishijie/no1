package com.doorcii.ad.utils.upload.files;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;

/**
 * 图片上传
 * @author Jacky
 */
public class ImgUploader {
	
	private static final int MAX_FOLDER = 256;
	
	private static final String SPLIT = "/";
	
	/**
	 * 允许的文件类型
	 */
	private static final List<String> allowedExt = Arrays.asList(new String[]{"jpg","jpeg",
		"gif","txt","doc","docx","mp3","wma","m4a","flv","swf"});
	
	public static String fileRoot = "d:/upload/";
	
	public static UploadResult uploadImg(InputStream is ,String ext) throws Exception {
		UploadResult result = new UploadResult();
        if (!allowedExt.contains(ext.toLowerCase())) {
        	result.setSuccess(false);
        	result.setMsg("上传文件类型不符！");
        	return result;
        } 
        
        String fileName = UUID.randomUUID().toString();  
        String folder = subFolderPath(fileName);
        StringBuilder rootPath = new StringBuilder(folder);
        rootPath.append(fileName).append(".").append(ext);
        String u_name = fileRoot + rootPath.toString();  
        try {  
            File file = new File(u_name);  
            FileUtils.copyInputStreamToFile(is, file);
            result.setSuccess(true);
            result.setUrl(rootPath.toString());
            return result;
        } catch (Exception e) {  
        	result.setSuccess(false);
        	result.setMsg(e.getMessage());
        	return result;
        }  
	}
	
	/**
	 * xxx/xxx/xxx/
	 * 三级目录
	 * @return
	 */
	private static String subFolderPath(String fileName) {
		int hash = fileName.hashCode();
		StringBuilder sb = new StringBuilder();
		// 第一级52个字母做目录
		int index1 = indexFor(hash,52);
		sb.append(String.valueOf((char)(index1+65))).append(SPLIT);
		// 第二级用file_index做目录,默认做成300个
		int index2 = indexFor(DigestUtils.md5(String.valueOf(hash)).hashCode(),MAX_FOLDER);
		sb.append(index2).append(SPLIT);
		// 第三级用data_index做目录,默认也做成300个
		Random random = new Random();
		int index3 = indexFor(random.nextInt(),MAX_FOLDER);
		sb.append(index3).append(SPLIT);
		return sb.toString();
	}
	
	/**
	 * 求余的高性能方法
	 * @param h
	 * @param length
	 * @return
	 */
	private static int indexFor(int h, int length) {
		return h & (length-1);
	}
	
	public static void main(String[] args) throws Exception {
		for(int i=0;i<100;i++)
			System.out.println(uploadImg(new ByteArrayInputStream("".getBytes()),"jpg"));
	}
}
