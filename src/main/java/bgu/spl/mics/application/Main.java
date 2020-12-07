package bgu.spl.mics.application;

import bgu.spl.mics.MessageBusImpl;
import bgu.spl.mics.application.passiveObjects.*;
import bgu.spl.mics.application.services.*;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/** This is the Main class of the application. You should parse the input file,
 * create the different components of the application, and run the system.
 * In the end, you should output a JSON.
 */
public class Main {
	private static Ewoks ewoks;
	private static Thread leia,hanSolo,c3po,lando,r2d2;


	public static void main(String[] args) throws IOException {


		Init("/home/spl211/IdeaProjects/StarWars/src/main/input.json");
		ewoks.PrintEwoks();



//		Simulate();

		outGson();


	}


	public static void Init(String path) throws IOException {
		InputApp input= JsonInputReader.getInputFromJson(path);
		leia=new Thread(new LeiaMicroservice(input.getAttacks()));
		hanSolo=new Thread(new HanSoloMicroservice());
		c3po=new Thread(new C3POMicroservice());
		lando=new Thread(new LandoMicroservice(input.getLando()));
		r2d2=new Thread(new R2D2Microservice(input.getR2D2()));
		ewoks=Ewoks.getInstance(input.getEwoks());

	}

	public static void Simulate() {
		leia.start();
		hanSolo.start();
		c3po.start();
		lando.start();
		r2d2.start();

	}

	public static void outGson() {


		try {
			Gson outGson=new Gson();
			Diary diary=Diary.getInstance();
			Writer writer=new FileWriter("/home/spl211/IdeaProjects/StarWars/src/main/output.json");
			outGson.toJson(diary,writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}