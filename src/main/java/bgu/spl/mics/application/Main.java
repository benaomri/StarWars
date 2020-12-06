package bgu.spl.mics.application;

import bgu.spl.mics.MessageBusImpl;
import bgu.spl.mics.application.passiveObjects.InputApp;
import bgu.spl.mics.application.passiveObjects.JsonInputReader;
import bgu.spl.mics.application.services.C3POMicroservice;
import bgu.spl.mics.application.services.HanSoloMicroservice;
import bgu.spl.mics.application.services.LandoMicroservice;
import bgu.spl.mics.application.services.LeiaMicroservice;
import com.google.gson.Gson;

import java.io.IOException;

/** This is the Main class of the application. You should parse the input file,
 * create the different components of the application, and run the system.
 * In the end, you should output a JSON.
 */
public class Main {

	public static void main(String[] args) throws IOException {


		Init("/home/spl211/IdeaProjects/StarWars/src/main/input.json");
		Simulate();
		Gson out = outGson();


	}


	public static void Init(String path) throws IOException {
		InputApp input= JsonInputReader.getInputFromJson(path);
		System.out.println(input.getAttacks().);

	}

	public static void Simulate() {


	}

	public static Gson outGson() {
		return null;
	}
}