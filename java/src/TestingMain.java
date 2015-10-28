import shared.transferClasses.UserCredentials;
import client.exceptions.ServerException;
import client.server.ServerProxy;




public class TestingMain {
	public static void main(String[] args) {
		ServerProxy.initialize("localhost:8081");
		
		UserCredentials fox = new UserCredentials("StarFox", "nintendo64");
		UserCredentials peppy = new UserCredentials("Harey_Mentor", "DoABarrelRoll");
		UserCredentials falco = new UserCredentials("Bird_Rival", "GeezeFox");
		UserCredentials slippy = new UserCredentials("Frog_Mechanic", "WhatGenderAmI");
		
		try {
			ServerProxy.register(fox);
			ServerProxy.register(peppy);
			ServerProxy.register(falco);
			ServerProxy.register(slippy);
		}
		catch (ServerException e) {
			System.out.println("The squad is already registered.");
		}
		
		try {
			ServerProxy.login(fox);
			ServerProxy.login(peppy);
			ServerProxy.login(falco);
			ServerProxy.login(slippy);
		}
		catch (ServerException e) {
			System.err.println("Bad stuff: " + e.getReason());
		}
		
	}
}
