package org.example.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {

	// 源文件夹   
    static String url1 = "e:\\1\\1";  
    // 目标文件夹   
    static String url2 = "e:\\2\\2";  
    public static void main(String args[]) throws IOException {  

//    	copy(url1,url2);
//    	String a = "D:\\My Documents\\Visual Studio 2008\\Projects";
//    	System.out.println(a);
//    	a=a.replace("\\", "\\\\");
//    		System.out.println(a);
    }  
    
    /**
     * 
     * @param urlSouce e:\\1\\1
     * @param urlDirec e:\\2\\2
     * @throws IOException
     */
    public static void copy(String urlSouce,String urlDirec)throws IOException{
    	// 创建目标文件夹   
        (new File(urlDirec)).mkdirs();  
        // 获取源文件夹当前下的文件或目录   
        File[] file = (new File(urlSouce)).listFiles();  
        for (int i = 0; i < file.length; i++) {  
            if (file[i].isFile()) {  
                // 复制文件   
                copyFile(file[i],new File(urlDirec+"\\\\"+file[i].getName()));  
            }  
            if (file[i].isDirectory()) {  
                // 复制目录   
                String sourceDir=url1+File.separator+file[i].getName();  
                String targetDir=url2+File.separator+file[i].getName();  
                copyDirectiory(sourceDir, targetDir);  
            }  
        } 
    }
    
	// 复制文件   
	public static void copyFile(File sourceFile,File targetFile)throws IOException{  
        // 新建文件输入流并对它进行缓冲   
        FileInputStream input = new FileInputStream(sourceFile);  
        BufferedInputStream inBuff=new BufferedInputStream(input);  
  
        // 新建文件输出流并对它进行缓冲   
        FileOutputStream output = new FileOutputStream(targetFile);  
        BufferedOutputStream outBuff=new BufferedOutputStream(output);  
          
        // 缓冲数组   
        byte[] b = new byte[1024 * 5];  
        int len;  
        while ((len =inBuff.read(b)) != -1) {  
            outBuff.write(b, 0, len);  
        }  
        // 刷新此缓冲的输出流   
        outBuff.flush();  
          
        //关闭流   
        inBuff.close();  
        outBuff.close();  
        output.close();  
        input.close();  
    }  
    // 复制文件夹   
    public static void copyDirectiory(String sourceDir, String targetDir)  
            throws IOException {  
        // 新建目标目录   
        (new File(targetDir)).mkdirs();  
        // 获取源文件夹当前下的文件或目录   
        File[] file = (new File(sourceDir)).listFiles();  
        for (int i = 0; i < file.length; i++) {  
            if (file[i].isFile()) {  
            	// 源文件   
                File sourceFile=file[i];  
                // 目标文件   
                File targetFile=new File(new File(targetDir).getAbsolutePath()+File.separator+file[i].getName());  
                copyFile(sourceFile,targetFile);  
            }  
            if (file[i].isDirectory()) {  
                // 准备复制的源文件夹   
                String dir1=sourceDir + "/" + file[i].getName();  
                // 准备复制的目标文件夹   
                String dir2=targetDir + "/"+ file[i].getName();  
                copyDirectiory(dir1, dir2);  
            }  
        }  
    }  
}
