package helloworld;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.apache.commons.io.IOUtils;

public class CopyProject2UTF8 {
	public static void main(String[] args) {
		String iFileName = "E:/java/project/store_WEB";
		String oFileName = "E:/java/project/store_WEB2";
		try {
			copy2UTF8(iFileName,oFileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void copy2UTF8(String iFileName, String oFileName) throws Exception {
		//��ȡԴ�ļ��к�Ŀ���ļ���
		File iFile = new File(iFileName);
		File oFile = new File(oFileName);
		if(iFile.exists()){
			if(!oFile.exists()){
				oFile.mkdirs();
			}
//			����Դ�ļ���
			File[] files = iFile.listFiles();
			for(File file:files){
				//������ļ�
				if(file.isFile()){
					InputStream is = new FileInputStream(file);
					File f = new File(oFileName+"/"+file.getName());
					OutputStream os = new FileOutputStream(f);
					
					if(file.getName().endsWith(".java")){
//						FileReader fileReader = new FileReader(file);
						InputStreamReader isr = new InputStreamReader(is, Charset.forName("GBK"));
//						FileWriter fileWriter = new FileWriter(f);
						OutputStreamWriter osw = new OutputStreamWriter(os, Charset.forName("UTF-8"));
						char[] cbuf = new char[2048];
						int len = 0;
						while((len =isr.read(cbuf))!=-1){
							System.out.println(cbuf.toString());
							
							osw.write(cbuf,0,len);
							osw.flush();
						}
					}else{
						IOUtils.copy(is, os);
					}
				}
				//������ļ���
				else{
					copy2UTF8(file.getAbsolutePath(),oFileName+"/"+file.getName());
				}
			}
		}
	}
}
