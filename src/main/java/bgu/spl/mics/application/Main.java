package bgu.spl.mics.application;

import bgu.spl.mics.MessageBusImpl;
import bgu.spl.mics.application.passiveObjects.Ewoks;
import bgu.spl.mics.application.passiveObjects.InputApp;
import bgu.spl.mics.application.passiveObjects.JsonInputReader;
import bgu.spl.mics.application.services.*;
import com.google.gson.Gson;

import java.io.IOException;

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
		Gson out = outGson();


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

	public static Gson outGson() {
		return null;
	}
}