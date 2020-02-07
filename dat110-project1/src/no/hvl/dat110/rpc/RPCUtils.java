package no.hvl.dat110.rpc;

import java.math.BigInteger;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.Arrays;

import no.hvl.dat110.TODO;

public class RPCUtils {

	// Utility methods for marshalling and marshalling of parameters and return
	// values
	// in RPC request and RPC responses
	// data bytearrays and return byte arrays is according to the
	// RPC message syntax [rpcid,parameter/return value]

	public static byte[] marshallString(byte rpcid, String str) {

		byte[] en = str.getBytes();
		byte[] encoded = new byte[str.length() + 1];
		encoded[0] = rpcid;
		for (int i = 0; i < en.length; i++) {
			encoded[i + 1] = en[i];
		}


		return encoded;
	}

	public static String unmarshallString(byte[] data) {

		String decoded = new String(data, 1, data.length-1);
		
		return decoded;
	}

	public static byte[] marshallVoid(byte rpcid) {

		byte[] encoded = new byte[1];
		encoded[0] = rpcid;

		// TODO: marshall RPC identifier in case of void type

		return encoded;

	}

	public static void unmarshallVoid(byte[] data) {

		// TODO: unmarshall void type
	}

	public static byte[] marshallBoolean(byte rpcid, boolean b) {

		byte[] encoded = new byte[2];

		encoded[0] = rpcid;

		if (b) {
			encoded[1] = 1;
		} else {
			encoded[1] = 0;
		}

		return encoded;
	}

	public static boolean unmarshallBoolean(byte[] data) {

		return (data[1] > 0);

	}

	public static byte[] marshallInteger(byte rpcid, int x) {

		BigInteger bigInt = BigInteger.valueOf(x);
		byte[] array = bigInt.toByteArray();
		byte[] encoded = new byte[array.length+1];
		encoded[0] = rpcid;
		for (int i = 0; i < array.length; i++) {
			encoded[i + 1] = array[i];
		}

		// TODO: marshall RPC identifier and string into byte array

		return encoded;

	}

	public static int unmarshallInteger(byte[] data) {

		// TODO: unmarshall integer contained in data

		ByteBuffer byteBuffer = ByteBuffer.wrap(data,1,4);
		int decoded = byteBuffer.getInt();
		return decoded;

	}
}
