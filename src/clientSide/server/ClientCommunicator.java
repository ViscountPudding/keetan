package clientSide.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import shared.Converter;

/**
 * This class sends commands to a server specified by the server
 * url.
 * @author djoshuac
 *
 */
public class ClientCommunicator {
	private String serverUrl;
	
	/**
	 * Constructs a ClientCommunicator object
	 * @pre severUrl must be a valid url for a server.
	 * @param serverUrl - the url of the server to send commands to
	 * @post a functioning ClientCommuicator is constructed.
	 */
	public ClientCommunicator(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	 /**
	  * Sends an Http Post request to the specified server.
	  * @pre Command must be a valid command for the server. The serverUrl must be valid.
	  * Data must be an object that can be serialized to json and is appropriate for the specified
	  * command.
	  * @param command - the command to be executed on the server
	  * @param data - an object to be serialized to json to be sent with the request
	  * @post The server command is successfully executed.
	  * @throws IOException when the url is invalid for desired server or a server error occurs.
	  */
	public void send(String command, Object data) throws IOException {
		URL url = new URL(serverUrl + command);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.connect();
		OutputStream requestBody = connection.getOutputStream();
		
		String json = Converter.toJson(data);
		requestBody.write(json.getBytes());
		requestBody.close();
		
		if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			System.out.println("IT WORKED");
			InputStream responseBody = connection.getInputStream();
			InputStreamReader reader = new InputStreamReader(responseBody);
			Scanner scan = new Scanner(reader);
			while (scan.hasNext()) {
				System.out.println(scan.nextLine());
			}
		}
		else {
			try {
				System.out.println("NONONONONONONONONONONO");
				InputStream responseBody = connection.getErrorStream();
				InputStreamReader reader = new InputStreamReader(responseBody);
				Scanner scan = new Scanner(reader);
				while (scan.hasNext()) {
					System.out.println(scan.nextLine());
				}
				scan.close();
				//String objectResult = responseBody.;//Converter.fromJson(responseBody, Object.class);
				//System.out.println(objectResult);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
//		URL url = new URL(serverUrl + command);
//		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//		connection.setRequestMethod("POST");
//		connection.setDoOutput(true);
//
//		System.out.println("Response Code : " + connection.getResponseCode()); //deleteme
//		OutputStream outputStream = connection.getOutputStream();
//		outputStream.write(Converter.toJson(data).getBytes());
//		outputStream.close();
//		System.out.println("Response Code : " + connection.getResponseCode()); //deleteme
//		if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//			List<String> cookies = connection.getHeaderFields().get("Set-Cookie");
//			return;
//		}
//		else {
//			throw new IOException();
//		}
	}

	 /**
	  * Sends an Http Post request to the specified server returning the data sent back
	  * from the server.
	  * @pre Command must be a valid command for the server. The serverUrl must be valid.
	  * Data must be an object that can be serialized to json and is appropriate for the specified
	  * command. The classOfReturnObject must be correspond to the data returned by the server.
	  * @param command - the command to be executed on the server
	  * @param data - an object to be serialized to json to be sent with the request
	  * @param classOfReturnObject - used to deserialize the data sent back from the server
	  * to the desired class using reflection
	  * @post The command is successfully executed on the server. An Object that can be cast to
	  * the given classOfReturnObject is returned.
	  * @throws IOException when the url is invalid for desired server or a server error occurs.
	  */
	public <T> T send(String command, Object data, Type classOfReturnObject) throws IOException {
		URL url = new URL(serverUrl + command);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);

		connection.connect();
		OutputStream outputStream = connection.getOutputStream();
		outputStream.write(Converter.toJson(data).getBytes());
		outputStream.close();

		System.out.println("Response Code : " + connection.getResponseCode()); //deleteme
		if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(connection.getInputStream()));
			reader.close();
			return Converter.fromJson(reader, classOfReturnObject);
		}
		else {
			throw new IOException();
		}
	}
}
