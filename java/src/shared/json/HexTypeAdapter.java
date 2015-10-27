package shared.json;

import java.lang.reflect.Type;

import model.Hex;
import model.HexLocation;
import shared.definitions.ResourceType;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class HexTypeAdapter implements JsonSerializer<Hex>, JsonDeserializer<Hex> {

	@Override
	public JsonElement serialize(Hex hex, Type type,
			JsonSerializationContext jsc) {
		JsonObject jsonHex = new JsonObject();
		jsonHex.add("location", jsc.serialize(hex.getLocation()));
		if (hex.getResource() != null) {
			jsonHex.add("resource", jsc.serialize(hex.getResource()));
			jsonHex.add("number", jsc.serialize(hex.getChitNumber()));
		}
		return jsonHex;
	}

	@Override
	public Hex deserialize(JsonElement element, Type type,
			JsonDeserializationContext jdc) throws JsonParseException {
		JsonObject jsonHex = element.getAsJsonObject();
		Hex hex = new Hex();
		hex.setHexLocation((HexLocation) jdc.deserialize(jsonHex.get("location"), HexLocation.class));
		
		JsonElement resource = jsonHex.get("resource");
		if (resource != null) {
			hex.setResource(ResourceType.fromString(resource.getAsString()));
			hex.setChitNumber(jsonHex.get("number").getAsInt());
		}
		return hex;
	} 

}
