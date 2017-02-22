package com.zqb.util;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by zqb on 2017/2/21.
 */
public class OfficeConvert {

    static String OpenOffice_HOME = "C:\\Program Files (x86)\\OpenOffice 4";
    public static String officeToPDF(String filePath) throws Exception
    {
        if (OpenOffice_HOME.charAt(OpenOffice_HOME.length() - 1) != '/')
        {
            OpenOffice_HOME += "\\";
        }
        String basePath=filePath.substring(0,filePath.lastIndexOf("\\"));
        basePath=basePath.substring(0,basePath.lastIndexOf("\\")+1);
        String pdfDir=basePath+"pdf\\";
        File docFile = new File(filePath);
        File pdfFile = new File(pdfDir+ docFile.getName().substring(0,docFile.getName().lastIndexOf("."))+ ".pdf");
        if (docFile.exists())
        {
            if (!pdfFile.exists())
            {
                Process pro = null;
                OpenOfficeConnection connection = null;
                try {
                    // 启动OpenOffice的服务
                    String command = OpenOffice_HOME
                            + "program\\soffice.exe -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\" -nofirststartwizard";
                    pro = Runtime.getRuntime().exec(command);
                    connection = new SocketOpenOfficeConnection("127.0.0.1",8100);
                    connection.connect();
                    DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
                    converter.convert(docFile, pdfFile);
                    // close the connection
                    if(connection != null)
                    {
                        connection.disconnect();
                        connection = null;
                    }
                    // 封闭OpenOffice服务的进程
                    pro.destroy();

                }  catch (IOException e) {
                    e.printStackTrace();
                    return "文件转换出错";
                } finally {
                    if(connection != null&&connection.isConnected())
                    {
                        connection.disconnect();
                    }
                    if(pro!=null)
                    {
                        pro.destroy();
                    }

                }
            }
            return pdfFile.toString();
        }
        else
        {
            return "文档不存在";
            //System.out.println("****需要转换的文档不存在，无法转换****");
        }
    }

}
