package bgu.spl.mics.application;

import com.google.gson.Gson;
import com.sun.org.apache.xml.internal.security.Init;

/** This is the Main class of the application. You should parse the input file,
 * create the different components of the application, and run the system.
 * In the end, you should output a JSON.
 */
public class Main {
	public static void main(String[] args) {
		Init(null);
		Simulate();
		Gson out=outGson();
		
	}


	public static  void Init(Gson gson)
	{

	}
	public  static void Simulate()
	{

	}
	public static Gson outGson()
	{
		return  null;
	}
}
