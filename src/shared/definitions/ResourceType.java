package shared.definitions;

public enum ResourceType
{
	wood, brick, sheep, wheat, ore;

	public static ResourceType fromString(String string) {
		if (string == null) {
			return null;
		}
		switch (string) {
		case "wood":
			return ResourceType.wood;
		case "brick":
			return ResourceType.brick;
		case "sheep":
			return ResourceType.sheep;
		case "wheat":
			return ResourceType.wheat;
		case "ore":
			return ResourceType.ore;
		default:
			return null;
		}
	}
}

