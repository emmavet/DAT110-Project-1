package no.hvl.dat110.rpc;

import java.io.IOException;

import no.hvl.dat110.messaging.*;

public class RPCClient {

	private MessagingClient msgclient;
	private Connection connection;

	public RPCClient(String server, int port) {

		msgclient = new MessagingClient(server, port);
	}

	public void register(RPCStub remote) {
		remote.register(this);
	}

	public void connect()  {

		// TODO: connect using the underlying messaging layer connection
		connection = msgclient.connect();

	}

	public void disconnect() {

		// TODO: disconnect/close the underlying messaging connection
		connection.close();
	}

	public byte[] call(byte[] rpcrequest){

		if (connection == null) {
			connect();
		}
		Message message = new Message(rpcrequest);
		connection.send(message);
		message = connection.receive();
		byte[] rpcreply = message.getData();

		return rpcreply;

	}

}
