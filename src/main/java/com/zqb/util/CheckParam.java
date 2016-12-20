package com.zqb.util;

/**
 * Created by zqb on 2016/12/19.
 */
public  class CheckParam {
    //防sql注入
    public static boolean CheckParams(String res)
    {
        String[] str = new String[35];
        str[0] = "net user";
        str[1] = "xp_cmdshell";
        str[2] = "/add";
        str[3] = "exec%20master.dbo.xp_cmdshell";
        str[4] = "net localgroup administrators";
        str[5] = " select ";
        str[6] = " count ";
        str[7] = " asc ";
        str[8] = " char ";
        str[9] = " mid ";
        str[10] = "''";
        str[11] = "'";
        str[12] = "'";
        str[13] = "insert ";
        str[14] = "delete ";
        str[15] = "drop ";
        str[16] = "truncate";
        str[17] = "from ";
        str[18] = "%";
        str[19] = " and ";
        str[20] = "script";
        str[21] = "alert";
        str[22] = "javascript";
        str[23] = "VBscript";
        str[24] = "document";
        str[25] = "window.location.href";
        str[26] = "window.open";
        str[27] = "getElementById";
        str[28] = "getElementsByName";
        str[29] = "getElementsByTagName";
        str[30]="<";
        str[31]=">";
        str[32]="*";
        str[33] = "\\";
        str[34] = "&";

        res = res.toLowerCase();
        int no = 0;
        for (int i = 0; i < str.length; i++)
        {
            if (res.contains(str[i]))
            {
                no = 1;
                break;
            }

        }
        if (no == 1)
        {
            return true;//有问题
        }
        else
            return false;

    }
}
