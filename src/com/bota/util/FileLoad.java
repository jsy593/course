package com.bota.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

public class FileLoad {
	
	public final static String TEMP_FILE_SUFFIX = ".tmp";//临时文件后缀
	
	public final static String TEMP_FOLDER_NAME = "temp";//临时文件夹名称
	
	public final static String CONFIG_FILE_NAME = "com/liveRoom/config/file.properties";
	
	public final static String DEFAULT_VIDEO_PHOTO_RESOLUTION = "100*100";
	
	
    public static void main(String[] args) {
		//String cmd = "cmd /c "
		//		+ "E:\\mywork\\liveRoom\\src\\resources\\ffmpeg.exe -i "
		//		+ "F:\\images\\Video\\4\\4.1.ContentObserver.avi "
		//		+ " -vf scale=400:-1 -vol 200 -ab 56 -ac 2 -ar 22050 -b 230 -r 29.97 -y "
		//		+ "F:\\images\\Video\\4\\4.1.ContentObserver.swf"
		//		+ " -ss 8 -t 0.001 -s 350x240 F:\\test.jpg";
		
		String tmp="E:\\mywork\\liveRoom\\src\\resources\\ffmpeg.exe -i  F:\\images\\Video\\4\\4.1.ContentObserver.avi &> _test_$tmp;grep Duration _test_$tmp | awk -F'[, ]'  '{print $4}' ;rm _test_$tmp";
//		System.out.println(cmd);
		execCommand(tmp, "所用时间：");
		
		
//		videoToFlv(Video.class, 2, "Wildlife.wmv", ".swf");
	}
	
	/**
     * 视频文件格式转换
     *
     * @author 周化益
     * @param entity 实体Class
     * @param entityId 实体主键值
     * @param fileName 文件名
     * @param type 转换后的类型
     */
	public synchronized static <T> void videoToFlv(Class<T> entity, long entityId,String fileName,
			String type ){
		Properties props = initConfigFile();
		if (null != props){
			File file = new File(CommonUtil.uploadPath());;
			if (file.exists()){
				Process process = null;
				StringBuffer sbf = new StringBuffer();
				String ffmpegPath = "ffmpeg.exe";
				String inFilePath = file.getPath() + "\\upload\\" + entity.getSimpleName() + "\\" + entityId + "\\" + fileName;
				File fs = new File(inFilePath);
				if (!fs.exists()){
					fs.mkdirs();	
				}				
				String path = FileLoad.class.getResource("").getPath();
				int end = path.lastIndexOf("com"); 
				String outFilePath = fs.getPath();
				sbf.append("cmd /c "+path.substring(1,end) + "resources/" + ffmpegPath+" -i ")
				   .append(inFilePath)
				   .append(" -vf scale=400:-1 -vol 200 -ab 56 -ac 2 -ar 22050 -b 230 -r 15 -y ")
				   .append(outFilePath.substring(0,outFilePath.length() - 4) + type)
				   .append(" -ss 8 -t 0.001 -s 350x240 ")
				   .append(outFilePath.substring(0,outFilePath.length() - 4) + ".jpg");
				try {
					System.out.println(sbf);
					String systemOutText = "视频转换时间为：";
					process  = execCommand(sbf.toString(),systemOutText);
				} catch (Exception e) {
					e.printStackTrace();
				} finally{
					if (null != process){
						process.destroy();
					}
				}
			}
		}
	} 
	
	/**
	 * 删除单个文件夹
	 * 
	 * @param entityClass
	 * @param deleteId
	 * @return
	 */
	public static <T> boolean deleteFile(Class<T> entityClass, long deleteId) {
		String uploadPath = getPropertyByKey("upLoadPath", null);
		String path = uploadPath + "\\" + entityClass.getSimpleName() + "\\" + deleteId;
		return deleteDirectory(path);
	}
	
	/**
	 * 删除多个文件夹
	 * 
	 * @param entityClass
	 * @param deleteId
	 * @return
	 */
	public static <T> boolean deleteFile(Class<T> entityClass, String deleteIds) {
		String uploadPath = getPropertyByKey("upLoadPath", null);
		List<String> ids = CommonUtil.strToList(String.class, deleteIds);
		for (String str : ids) {
			String path = uploadPath + "\\" + entityClass.getSimpleName() + "\\" + str;
			deleteDirectory(path);
		}
		return true;
	}
	
	/**
	 * 获取Properties
	 *
	 * @author 周化益
	 * @return Properties
	 */
	public static Properties initConfigFile(){
		Properties props = new Properties();
        try {
			props.load(FileLoad.class.getClassLoader().getResourceAsStream(CONFIG_FILE_NAME));
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return props;
	}
	
	/**
	 * 根据配置文件里面的key值获取对应的值
	 *
	 * @author 张博
	 * @param propertyKey
	 * @param props
	 * @return String
	 */
	public static String getPropertyByKey(String propertyKey,Properties props){
		if (null == props){
			props = initConfigFile();
		}
		String propertyKeyVal = props.getProperty(propertyKey);
		return propertyKeyVal;
	}
	
    /** 
     * 删除目录（文件夹）以及目录下的文件 
     * 
     * @author 周化益
     * @param   sPath 被删除目录的文件路径 
     * @return  目录删除成功返回true，否则返回false 
     */  
    public static boolean deleteDirectory(String sPath) {
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符  
        if (!sPath.endsWith(File.separator)) {  
            sPath = sPath + File.separator;  
        }  
        File dirFile = new File(sPath);  
        //如果dir对应的文件不存在，或者不是一个目录，则退出  
        if (!dirFile.exists() || !dirFile.isDirectory()) {  
            return false;  
        }  
        boolean flag = true;  
        //删除文件夹下的所有文件(包括子目录)  
        File[] files = dirFile.listFiles();  
        for (int i = 0; i < files.length; i++) {  
            //删除子文件  
            if (files[i].isFile()) {  
                flag = deleteFile(files[i].getAbsolutePath());  
                if (!flag) break;  
            } //删除子目录  
            else {  
                flag = deleteDirectory(files[i].getAbsolutePath());  
                if (!flag) break;  
            }  
        }  
        if (!flag) return false;  
        //删除当前目录  
        if (dirFile.delete()) {
            return true;  
        } else {  
            return false;  
        }  
    }  
    
    /** 
     * 删除单个文件 
     * 
     * @author 周化益
     * @param   sPath    被删除文件的文件名 
     * @return 单个文件删除成功返回true，否则返回false 
     */ 
    public static boolean deleteFile(String sPath) {  
        boolean flag = false;  
        File file = new File(sPath);  
        // 路径为文件且不为空则进行删除  
        if (file.isFile() && file.exists()) {  
            file.delete();  
            flag = true;  
        }  
        return flag;  
    }
	
    public FileLoad() {
    	
    }
    
    public static String getFfrmpegPath() {
    	File f = new File(FileLoad.class.getResource("/").getPath());
    	return f.getPath();
    }
    
	/**
     * 截取视频某些帧转换为图片
     *
     * @author 周化益
     * @param entity 实体Class
     * @param entityId 记录id
     * @param fileName 文件名
     * @param fileSuffix 文件后缀
     */
	public synchronized static <T> void capturePhotoFromVideo(Class<T> entity, long entityId,
			String fileName){
		Properties props = initConfigFile();
		if (null != props){
			String inFilePath = null;//本地文件
			String outFilePath = null;//视频截取的图片
			inFilePath = CommonUtil.uploadPath() + "upload/" + entity.getSimpleName() + "/" + entityId + "/" + fileName;
			File file = new File(inFilePath);
			if (file.exists()) {
				Process process = null;
			    int duration = CommonUtil.getMediaDuration(file.getPath());
			    duration = duration/120000;//视频总时间的三分之一处截取
				StringBuffer command = new StringBuffer();				
				File thumbFile = new File(CommonUtil.uploadPath()+ "upload/",entity.getSimpleName() + "/" + entityId);
				if (!thumbFile.exists()){
					thumbFile.mkdirs();
				}
				String path = FileLoad.class.getResource("").getPath();
				int end = path.lastIndexOf("com"); 
				int dot = fileName.lastIndexOf('.'); 
				outFilePath = thumbFile + "/" + fileName.substring(0,dot) + ".jpg";
				command.append("cmd /c ").append(path.substring(1, end)+"resources/")
				.append("ffmpeg.exe").append(" -i ").append(inFilePath)
				.append(" -y -f image2 -ss ").append(duration).append(" -vframes 1 -s ")
				.append(DEFAULT_VIDEO_PHOTO_RESOLUTION).append(" ").append(outFilePath);	
				try {
					System.out.println(command);
					String systemOutText = "抓取视频图片所用时间为：";
					process  = execCommand(command.toString(),systemOutText);
				} catch (Exception e) {
					e.printStackTrace();
				} finally{
					if (null != process){
						process.destroy();
					}
				}
			}
		}
	}
	
    /**
     * 生成缩略图
     *
     * @author 周化益
     * @param inFilePath 源文件
     * @param outFilePath 生成的缩略图文件
     */
    public synchronized static void makeSmallImage(String inFilePath,String outFilePath){
    	try {
    		String convertPath = " convert.exe";
    		String command = "cmd /c "+getFfrmpegPath()+convertPath+" -resize \"100x100>\" +profile \"*\" "+inFilePath+" "+ outFilePath;
    		String systemOutText = inFilePath+"文件的缩略图获取时间:";
    		execCommand(command,systemOutText);
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 执行命令行语句
     *
     * @author 周化益
     * @param command 命令行语句
     * @param systemOutText 执行后的输出语句
     */
    public static Process execCommand(String command,String systemOutText){
    	Process process = null;
    	try {
    		long startTime = System.currentTimeMillis();
    		String[] cmdArry = command.split(" ");
    		ProcessBuilder builder = new ProcessBuilder(cmdArry);
    		builder.redirectErrorStream(true);    
    	    process = builder.redirectErrorStream(true).start();
    	    
    	    //防止线程堵塞
    	    new PrintStream(process.getInputStream()).start();
    	    
    		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        	process.waitFor();
        	br.close();
    		long endTime = System.currentTimeMillis();
    		if (null!=systemOutText && !systemOutText.equals("")){
    			System.out.println(systemOutText+(endTime-startTime)+"毫秒");
    		}    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return process;
    }
    
    /**
	 * 获取临时文件
	 *
	 * @author 周化益
	 * @return String
	 */
	public static File getTempupLoadPath(){
		File file = new File(getPropertyByKey("upLoadPath", null));
		String loadPath = file.getPath();
		File tempFolderPath = new File(loadPath,TEMP_FOLDER_NAME);
		if (!tempFolderPath.exists()) {
			tempFolderPath.mkdirs();
		}
		File tempFile = null;
		try {
			tempFile = File.createTempFile(Math.random()+"", null, tempFolderPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tempFile;
	}
	
	/**
	 * 删除临时文件
	 *
	 * @author 周化益
	 * @return String
	 */
	public static void delTempupLoadPath(String fileLoadRootPath,String tempFileName){
		File tempFolderPath = new File(fileLoadRootPath,TEMP_FOLDER_NAME);
		tempFileName = tempFileName.substring(0, tempFileName.lastIndexOf("."));
		File tempFilePath = new File(tempFolderPath,tempFileName+TEMP_FILE_SUFFIX);
		if (tempFilePath.exists()) {
			tempFilePath.delete();
		}
		tempFolderPath.delete();
	}
	
	/**
	 * 文件下载
	 * 
	 * @author 周化益
	 * @param entityClass 实体Class
	 * @param entityId 实体主键
	 * @param fileName 文件名
	 * @param response
	 * @return
	 */
	public static <T> boolean downLoad(Class<T> entityClass, long entityId, 
			String fileName, HttpServletResponse response) {
		boolean bool = true;
		String path = getPropertyByKey("upLoadPath",null) + "/" + entityClass.getSimpleName() + 
				"/" + entityId + "/" + fileName;
		
		File file = new File(path);
		byte buffer[] = new byte[1024];
		OutputStream os = null;
		int len = 0;
		InputStream is = null;
		try {
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"");
			os = new BufferedOutputStream(response.getOutputStream());
			is = new FileInputStream(file);
			while ((len = is.read(buffer)) != -1) {
				os.write(buffer, 0, len);
			}
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != is) {
					is.close();
				}
				if (null != os) {
					os.close();
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		response.addHeader("Content-Length", "" + buffer.length);
		response.setContentType("application/octet-stream;charset=UTF-8");
		return bool;
	}
}
