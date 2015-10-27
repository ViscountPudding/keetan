package shared.definitions;

import com.google.gson.annotations.SerializedName;

public enum HexType {
	
	@SerializedName("wood")
	WOOD,

    @SerializedName("brick")
	BRICK,

    @SerializedName("sheep")
	SHEEP,

    @SerializedName("wheat")
	WHEAT,

    @SerializedName("ore")
	ORE,

    @SerializedName("DESERT")
	DESERT,

    @SerializedName("WATER")
	WATER
}

