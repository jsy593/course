package com.bota.util;

import java.io.File;

public class FileUtil {
	/** 
     * 新建目录. 
     *  
     * @author bota
     * @param path 文件路径 
     * @throws Exception 
     */  
    public static void createDirectory(String path) throws Exception {  
        if (path == null || path.equals("")) {  
            return;  
        }  
        try {  
            // 获得文件对象  
            File f = new File(path);  
            if (!f.exists()) {  
                // 如果路径不存在,则创建  
                f.mkdirs();  
            }  
        } catch (Exception e) {  
            throw e;  
        }  
    } 
}
