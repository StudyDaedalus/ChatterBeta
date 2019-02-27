package com.blessingsoftware.chatterbeta.ServerRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class SRequest {
    public static String PostRequest(Map<String,String> data){
        byte [] sendData=getRequestData(data).toString().getBytes();
        try{
            URL url=new URL("http://www.futuredos.com/IM/IMServer.php");         //本URL不知道要不要换
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            httpURLConnection.setRequestProperty("Content--Length",String.valueOf(sendData.length));
            OutputStream outputStream=httpURLConnection.getOutputStream();
            outputStream.write(sendData);

            int response=httpURLConnection.getResponseCode();
            if(response==HttpURLConnection.HTTP_OK){
                InputStream inputStream=httpURLConnection.getInputStream();
                return dealResponseResult(inputStream);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }

    public static StringBuffer getRequestData(Map<String,String> data){
        StringBuffer stringBuffer=new StringBuffer();
        try{
            for (Map.Entry<String,String>entry:data.entrySet()){
                stringBuffer.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(),"utf-8")).append("&");
            }
            stringBuffer.deleteCharAt(stringBuffer.length()-1);              //去掉最后数据传来的“@”
        }catch (Exception e){
            e.printStackTrace();
        }
        return stringBuffer;
    }

    //处理服务器返回的消息
    public static String dealResponseResult(InputStream inputStream){
        String resultData=null;
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        byte[] data =new byte[1024];
        int len=0;
        try{
            while ((len=inputStream.read(data))!=-1){
                byteArrayOutputStream.write(data,0,len);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        resultData=new String(byteArrayOutputStream.toByteArray());
        return  resultData;
    }
}
