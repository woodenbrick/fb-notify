/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author wode
 */
public class Downloader {
    private static String picPath;
    
    public static String getPicPath(){
	return picPath;
    }
    
    public static void setPicPath(String path){
	//check if valid path
	picPath = path;
    }
    
    public static boolean DownloadImage(URL url) throws Exception{
	URLConnection conn = url.openConnection();
	String path = picPath + url.getFile();
	FileOutputStream out = null;
	InputStream in = conn.getInputStream();
	out = new FileOutputStream(path);
	int c;
	while ((c = in.read()) != -1){
	    out.write(c);
	}
	if (in != null){
	    in.close();
	}
	if (out != null){
	    out.close();
	}

	return true;
    }
}
