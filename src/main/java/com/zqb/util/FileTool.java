package com.zqb.util;

import org.apache.commons.fileupload.util.Streams;
import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by zqb on 2016/12/21.
 */
public class FileTool
{
    public static boolean fileUpload(MultipartFile files,File fileLocation)
    {
        //获取WEB-INF路径,用于存放上传的文件
        StringBuffer sb=null;
        try {
            String fileName = files.getOriginalFilename();
            String headPath = fileLocation +"/"+ fileName;
            //Streams.copy(files[i].getInputStream(),new FileOutputStream(headPath),true);
            files.transferTo(new File(headPath));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void fileDownload(String filePath,String isOnLine,HttpServletResponse response)throws IOException
    {
        File f = new File(filePath);
        if (!f.exists())
        {
            response.sendError(404, "File not found!");
        }
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
        byte[] buf = new byte[1024];
        int len = 0;
        response.reset(); // 非常重要
        // 在线打开方式
        if (isOnLine.equals("true"))
        {
            URL u = new URL("file:///" + filePath);
            response.setContentType(u.openConnection().getContentType());
            response.setHeader("Content-Disposition", "inline; filename=" + new String(f.getName().replaceAll(" ", "").getBytes("utf-8"),"iso8859-1"));
            // 文件名应该编码成UTF-8
            response.setCharacterEncoding("UTF-8");
        }
        // 纯下载方式
        else
        {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment; filename=" + new String(f.getName().replaceAll(" ", "").getBytes("utf-8"),"iso8859-1"));
            response.setHeader("Content-Length",f.length()+"");
        }
        BufferedOutputStream out=new BufferedOutputStream(response.getOutputStream());
        while ((len = br.read(buf)) > 0)
        {
            out.write(buf, 0, len);
        }
        br.close();
        out.close();
        out.flush();
    }

    public static boolean fileDelete(String path)
    {
        boolean flag = false;
        File file = new File(path);
        // 判断目录或文件是否存在
        if (!file.exists())
        {  // 不存在返回 false
            return flag;
        }
        else
        {
            // 判断是否为文件
            if (file.isFile())
            {
                // 为文件时调用删除文件方法
                return file.delete();
            }
            else
            {  // 为目录时调用删除目录方法
                return deleteDirectory(path);
            }
        }
    }
    public static boolean deleteDirectory(String sPath)
    {
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory())
        {
            return false;
        }
        boolean flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++)
        {
            //删除子文件
            if (files[i].isFile())
            {
                flag = fileDelete(files[i].getAbsolutePath());
                if (!flag)
                    break;
            } //删除子目录
            else
            {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag)
        {
            return false;
        }
        //删除当前目录
        if (dirFile.delete())
        {
            return true;
        } else {
            return false;
        }
    }
}
