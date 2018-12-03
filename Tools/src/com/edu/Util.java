package com.edu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.show.api.ShowApiRequest;

public class Util {
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = null;;
			if (param != null) {
				urlNameString = url + "?" + param;
			}else {
				urlNameString = url;
			}
			URL realUrl = new URL(urlNameString);
			// �򿪺�URL֮�������
			URLConnection connection = realUrl.openConnection();
			// ����ͨ�õ���������
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// ����ʵ�ʵ�����
			connection.connect();
			// ��ȡ������Ӧͷ�ֶ�
			Map<String, List<String>> map = connection.getHeaderFields();
			// �������е���Ӧͷ�ֶ�
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// ���� BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("����GET��������쳣��" + e);
			e.printStackTrace();
		}
		// ʹ��finally�����ر�������
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	static Map<String, String> readFromFile() {
		Map<String, String> map = new HashMap<>();
		try {
			// read file content from file
			StringBuffer sb= new StringBuffer("");

			FileReader reader = new FileReader("citycode.txt");
			BufferedReader br = new BufferedReader(reader);

			String str = null;
			
			while((str = br.readLine()) != null) {
				str = str.replace("\t", ",");
//				System.out.println(str.split(",")[0]);;
				map.put(str.split(",")[0], str.split(",")[1]);
			}

			br.close();
			reader.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	public static void main(String[] args) {
		//		System.out.println(sendGet("http://www.baidu.com", null));
		//		String res=new ShowApiRequest("http://route.showapi.com/64-19","78438","613433a8eea743a389409c8ac0c9b149")
		//				.addTextPara("com","auto")
		//				.addTextPara("nu","632671345422")
		//			  .post();
		//			System.out.println(res);
		readFromFile();
	}
}
