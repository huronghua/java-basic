package com.github.java;


import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Eric-hu
 * @Description: 输入流转字符串
 * @create 2017-12-28 11:21
 * @Copyright: 2017 www.banmatrip.com All rights reserved.
 **/
public class InputStreamToString {


    public static void main(String args[]) throws IOException {
        String str = null;
        //文件的根路径是resources文件夹,和当前类的路径同级
        File file = new File(InputStreamToString.class.getClassLoader().getResource("test.txt").getPath());
        System.out.println(file.getAbsoluteFile());
        InputStream inputStream = null;
        inputStream = new FileInputStream(file);
        /*这行是关键代码*/
        str = IOUtils.toString(inputStream,"UTF-8");
        System.out.println(str);
    }
}