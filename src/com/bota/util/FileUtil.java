package com.bota.util;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

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
    
    /**
     * Spring MVC文件上传,返回的是经过处理的path+fileName
     * @param request    request
     * @param folder    上传文件夹
     * @param userid    用户名
     * @return
     */
    public static String upload(HttpServletRequest request, String folder, String userid){
        String file_url = "";
        //创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        
        //判断 request 是否有文件上传,即多部分请求
        if(multipartResolver.isMultipart(request)){
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            //取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while(iter.hasNext()){
                //取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                String prefix = getFilePrefix(file);
                if(file != null){
                    //取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    //如果名称不为"",说明该文件存在，否则说明该文件不存在
                    if(myFileName.trim() !=""){
                        System.out.println(myFileName);
                        //重命名上传后的文件名
                        String fileName =  userid + "." + prefix;
                        //定义上传路径,格式为 upload/bota/bota.jpg
                        String path = request.getServletContext().getRealPath("/") + folder + "/" + userid;
                        System.out.println("request.getServletContext().getRealPath:"+request.getServletContext().getRealPath("/"));
                        System.out.println(folder);
                        System.out.println(userid);
                        File localFile = new File(path, fileName);
                        if(!localFile.exists()){
                            localFile.mkdirs();
                        }
                        try {
                            file.transferTo(localFile);
                            file_url = folder + "/" + userid + "/" + fileName;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return file_url;
    }
    
    /**
     * 获取图片后缀
     * @param file
     * @return
     */
    public static String getFilePrefix(MultipartFile file){
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
        return suffix;
    }
    
}
