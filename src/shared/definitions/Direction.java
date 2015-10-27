package shared.definitions;

import model.EdgeDirection;

public enum Direction {
	N, NW, W, SW, S, SE, E, NE;

	public Direction getOppositeDirection() {
		switch (this) {
		case N:
			return S;
		case NW:
			return SE;
		case W:
			return E;
		case SW:
			return NE;
		case S:
			return N;
		case SE:
			return NW;
		case E:
			return W;
		case NE:
			return SW;
		default:
			return null;
		}
	}
	
	public VertexDirection getVertexDirection() {
		switch (this) {
		case NW:
			return VertexDirection.NorthWest;
		case W:
			return VertexDirection.West;
		case SW:
			return VertexDirection.SouthWest;
		case SE:
			return VertexDirection.SouthEast;
		case E:
			return VertexDirection.East;
		case NE:
			return VertexDirection.NorthEast;
		default:
			return null;
		}
	}

	public static Direction fromEdgeDirection(EdgeDirection edgeDirection) {
		switch (edgeDirection) {
		case North:
			return N;
		case NorthEast:
			return NE;
		case SouthEast:
			return SE;
		case South:
			return S;
		case SouthWest:
			return SW;
		case NorthWest:
			return NW;
		default:
			return null;
		}
	}
	
	public static Direction fromVertexDirection(VertexDirection vertexDirection) {
		switch (vertexDirection) {
		case NorthEast:
			return NE;
		case East:
			return E;
		case SouthEast:
			return SE;
		case SouthWest:
			return SW;
		case West:
			return W;
		case NorthWest:
			return NW;
		default:
			return null;
		}
	}
}
