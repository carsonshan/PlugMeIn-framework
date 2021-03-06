package falcons.server.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import falcons.client.network.ClientDataInterpreter;
import falcons.server.model.ServerPreferencesLogic;
import falcons.server.network.model.ConnectionModel;
import falcons.server.network.model.ConnectionThread;
import falcons.server.network.model.ServerThread;

public class ServerCommunicationCenter implements Runnable {

	private ServerSocket socket = null;
	private boolean listening = true;
	private ServerThread serverThread;

	/**
	 * Contructor for the CommunicationCenter.
	 *
	 * @param interpreter
	 *            The DataInterpreter that's being used by the client.
	 * @param ip
	 *            The IP-address of the server
	 * @param port
	 *            The port that the server uses to accept connections.
	 * @throws IOException
	 *             If an unhandled IOException is thrown then it could not find
	 *             the I/O Connection for the socket.
	 */
	public ServerCommunicationCenter() throws IOException {
		try {
			// Create a new socket
			this.socket = new ServerSocket(ServerPreferencesLogic.getPort());
		} catch (IOException e) {
			System.err
					.println("Couldn't get I/O for the connection to the client");
		}
	}

	/**
	 * The method that actually starts the server.
	 * @throws IOException
	 * 						if there was any problem with the socket.
	 */
	public void server() throws IOException {
		System.out.println("SERVER STARTED");
		System.out.println(ServerPreferencesLogic.getPort());
		serverThread = new ServerThread(socket);
		serverThread.run(listening);
		}
	
	public void shutdown() {
		try {
			listening = false;
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.socket = null;
	}
	
	@Override
	public void run() {
		try {
			server();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}