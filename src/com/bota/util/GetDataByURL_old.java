package com.bota.util;
import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

  
public class GetDataByURL_old {  
	/**
	 * 根据code获取股票json数据
	 * 
	 * @author 周化益
	 * @param code 股票code
	 * @return
	 */
    public static JSONObject cc(String code) {  
        StringBuffer temp = new StringBuffer();
        JSONObject searchJson = null;
        try {  
//            String url = "http://www.baidu.com/jiaojing/ser.php";  
            String url = "http://htmmarket.fx678.com/list.php"
            		+ "?excode=SZPEC&time=1428999404&token=310242a4068f1cad5096aec31a774f6c"
            		+ "&key=9d5aedcbe0558ac731507197cc9965eb";
            System.out.println(url);
            HttpURLConnection uc = (HttpURLConnection)new URL(url).openConnection();  
            uc.setConnectTimeout(10000);  
            uc.setDoOutput(true);  
            uc.setRequestMethod("POST");
            uc.setUseCaches(false);  
            DataOutputStream out = new DataOutputStream(uc.getOutputStream());  
  
/*            // Ҫ���Ĳ���  
            String s = URLEncoder.encode("ra", "UTF-8") + "=" +  
                       URLEncoder.encode(leibie, "UTF-8");  
            s += "&" + URLEncoder.encode("keyword", "UTF-8") + "=" +  
                    URLEncoder.encode(num, "UTF-8");  */
            
            //out.writeBytes(s);  
            out.flush();  
            out.close();  
            InputStream in = new BufferedInputStream(uc.getInputStream());  
            Reader rd = new InputStreamReader(in, "UTF-8");  
            int c = 0;  
            while ((c = rd.read()) != -1) {  
                temp.append((char) c);  
            }  
            
           // System.out.println(temp.toString());
            String[] jsonMap = temp.toString().split("},");
            int jsonLeng = jsonMap.length;
            List<JSONObject> jsonStrList = new ArrayList<JSONObject>();
            
            for (int i = 0; i < jsonLeng; i++) {
            	String json;
            	if(i == 0) {
            		continue;
            	} else if(i == jsonLeng - 1) {
            		json = (jsonMap[i].substring(0,jsonMap[i].length() - 1));
            	} else {
            		json = jsonMap[i] + "}";
            	}
            	jsonStrList.add(new JSONObject(json));
			}
            
            for (JSONObject jsonObject : jsonStrList) {
            	if(jsonObject.get("Code").equals(code)) {
            		searchJson = jsonObject;
            	}
            	//Map<String, Object> map = new HashMap<String, Object>();
/*            	Iterator<?> str = jsonObject.keys();
            	while (str.hasNext()) {
					String key =  str.next().toString();
					Object value = jsonObject.get(key);
					System.out.println(value);
				}
				System.out.println(jsonObject.get("Code"));*/
			}
            in.close();  
  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return searchJson;  
    }  
  
    public static void main(String[] a){  
        System.out.println(GetDataByURL_old.cc("QHG93S"));  
    }  
  
} 