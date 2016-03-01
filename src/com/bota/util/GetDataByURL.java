package com.bota.util;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

  
public class GetDataByURL {  
	/**
	 * 根据code获取股票json数据
	 * 
	 * @author 周化益
	 * @param code 股票code
	 * @return
	 */
    public JSONObject cc(String code) {
//    	System.out.println("接口要查的代码名称："+code);
        StringBuffer temp = new StringBuffer();
        JSONObject searchJson = null;
        try {  
            String url = "http://htmmarket.fx678.com/list.php"
            		+ "?excode=SZPEC&time=1428999404&token=310242a4068f1cad5096aec31a774f6c"
            		+ "&key=9d5aedcbe0558ac731507197cc9965eb";
//            System.out.println("接口地址："+url);
            HttpURLConnection uc = (HttpURLConnection)new URL(url).openConnection();  
            uc.setConnectTimeout(10000);  
            uc.setDoOutput(true);  
            uc.setRequestMethod("POST");
            uc.setUseCaches(false);  
            DataOutputStream out = new DataOutputStream(uc.getOutputStream());  

            out.flush();  
            out.close();  
            InputStream in = new BufferedInputStream(uc.getInputStream());  
            Reader rd = new InputStreamReader(in, "UTF-8");  
            int c = 0;  
            while ((c = rd.read()) != -1) {  
                temp.append((char) c);  
            }  
            
            String[] jsonMap = temp.toString().replace("[","").replace("]","").split("},");
            int jsonLeng = jsonMap.length;
            
            for (int i = 0; i < jsonLeng; i++) {
            	String json = jsonMap[i] + "}";
            	String jsoncode = new JSONObject(json).get("Code").toString();
            	if(jsoncode.equals(code)){
            		searchJson = new JSONObject(json);
            		break;
            	}
			}
        
            in.close();  
  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
//        System.out.println("接口执行的结果："+searchJson);
        return searchJson;  
    }  
  
    public static void main(String[] a){  
        System.out.println(new GetDataByURL().cc("QHTZJ"));  
    }  
  
} 