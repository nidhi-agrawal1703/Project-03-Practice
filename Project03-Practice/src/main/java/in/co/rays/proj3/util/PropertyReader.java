package in.co.rays.proj3.util;

import java.util.ResourceBundle;

public class PropertyReader {
	
	private static ResourceBundle rb=ResourceBundle.getBundle("in.co.rays.proj3.bundle.system");
	
	public static String getValue(String key) {		
		String val=null;
		try {
			val=rb.getString(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return val;
	}
	
	public static String getValue(String key,String param) {
		String msg=getValue(key);
		msg=msg.replace("{0}", param);
		return msg;
	}
	
	public static String getvalue(String key,String[] param) {
		String msg=getValue(key);
		for(int i=0;i<param.length;i++) {
			msg=msg.replace("{"+i+"}", param[i]);
		}
		return msg;
	}
	
public static void main(String[] args) {
		
		System.out.println("Single key example:");
		System.out.println(PropertyReader.getValue("error.require"));
		
		System.out.println("\nSingle parameter replacement example");
		System.out.println(PropertyReader.getValue("error.require","login Id"));
		
		System.out.println("Multiple parameter replacement example");
		String[] params= {"Roll no","Student name"};
		//System.out.println(PropertyReader.getValue("error.multipleFields", params));
		
	}
}
