package com.socket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyNewSocket {

	public static void main(String[] args) {
		try {
			Socket socket1 = new Socket();
			Socket socket2 = new Socket();
			
			System.out.println(socket1.equals(socket2));
			System.out.println(socket1.toString()+"               "+socket2.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
