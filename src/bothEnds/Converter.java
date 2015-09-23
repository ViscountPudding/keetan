package bothEnds;

import java.lang.reflect.Type;
import java.util.ArrayList;

import bothEnds.model.gamemap.HexLocation;

import com.google.gson.Gson;

/**
 * The Converter is a library class that provides methods to serialize and deserialize
 * an instance of a class to and from a JSON String.
 * @author djoshuac
 */
public final class Converter {
	private static final Gson gson = new Gson();
	
	/**
	 * Deserializes JSON into an instance of a class of the given type.
	 * @pre json must be a valid JSON string for an object of Type type
	 * @param json - a JSON String for an object
	 * @param type - a java.lang.reflect.Type for a specific class. Usage example: "ArrayList.class"
	 * @return an instance of the given class type specified by the JSON String
	 * @post An instance of the class type specified by a JSON String is created
	 */
	public static <T> T fromJson(String json, Type type) {
		return gson.fromJson(json, type);
	}
	
	/**
	 * Serializes an Object to JSON.
	 * @pre none
	 * @param model - the model of a Settlers of Catan game
	 * @return a JSON String of the ClientModel
	 * @post A JSON String of model is created
	 */
	public static String toJson(Object object) {
		return gson.toJson(object);
	}
	
	/**
	 * This main provides an example for how the Converter works
	 * @param args - we do not use this here
	 */
	public static void main(String[] args)  {
		HexLocation hex = new HexLocation(10, 15);
		String jsonHex = Converter.toJson(hex);
		System.out.println(jsonHex);
		HexLocation hex2 = Converter.fromJson(jsonHex, HexLocation.class);
		System.out.println("Hex: x=" + hex.getX() + " y=" + hex.getY());
		System.out.println("Hex2: x=" + hex2.getX() + " y=" + hex2.getY());
		
		ArrayList<Integer> al = new ArrayList<Integer>();
		al.add(new Integer(5));
		al.add(new Integer(7));
		al.add(new Integer(-3));
		String jsonList = Converter.toJson(al);
		System.out.println(jsonList);
	}
}
