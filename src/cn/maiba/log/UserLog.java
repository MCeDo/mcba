package cn.maiba.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserLog {

	public static String logFileName = "";
	
	public static synchronized void log(String content) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd");
		logFileName = "E:/Java/eclipse/worksplace/mcba/WebContent/log/"+format.format(new Date()) + ".txt";
		File file = new File(logFileName);
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(file, true));
			bw.write(new Date() + " : " + content + "\r\n");
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
