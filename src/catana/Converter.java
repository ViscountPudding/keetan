package catana;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import com.google.gson.Gson;

import model.ClientModel;

/**
 * @author djoshuac
 * 
 * This class will be used to convert the Game model to and from JSON.
 */
public class Converter {
	/**
	 * @pre json must be a valid JSON string for a ClientModel object
	 * @param json - a JSON String for a ClientModel object
	 * @return the ClientModel object specified by the JSON String
	 * @post A ClientModel object specified by a JSON String is created
	 */
	public ClientModel createGameModelFromJSON(String json) {
		return null;
	}
	
	/**
	 * @pre model must not be null
	 * @param model - the model of a Settlers of Catan game
	 * @return a JSON String of the ClientModel
	 * @post A JSON String of model is created
	 */
	public String createJSONString(ClientModel model) {
		return null;	
	}
	
	public static void main(String[] args)  {
			File file = new File("Utilities/jsonExample.txt");
			Scanner reader;
			try {
				reader = new Scanner(new BufferedReader(new FileReader(file)));
			} catch (FileNotFoundException e) {
				System.out.println("Could not find file");
				return;
			}
			StringBuilder sb = new StringBuilder();
			while (reader.hasNext()) {
				sb.append(reader.nextLine());
			}
			reader.close();
		
		Gson gson = new Gson();
	}
}
