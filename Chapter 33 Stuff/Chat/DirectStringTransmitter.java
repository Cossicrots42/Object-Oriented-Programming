package Chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class DirectStringTransmitter {
	
	private DataInputStream in;
	private DataOutputStream out;
	
	public DirectStringTransmitter() {
		
		
		
	} //public DirectStringTransmitter()
	
	public DirectStringTransmitter(Socket s) {
		
		try {
		
			this.in = new DataInputStream(s.getInputStream());
			this.out = new DataOutputStream(s.getOutputStream());
		
		} catch (IOException e) {
			
			e.printStackTrace();
		
		} //try {...} catch (IOException e)
		
	} //public directStringTransmitter()
	
	public void send (String message) {
		
		char[] contents = message.toCharArray();
		
		try {
			
			out.writeDouble(0.1); //Magic value that begins a sequence
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		} //try {...} catch (IOException e)
		
		for (char c : contents) {
			
			int unicode = (int) c;
		
			try {
			
				out.writeDouble(unicode);
				out.flush();
			
			} catch (IOException e) {
			
				e.printStackTrace();
			
			} //try {...} catch (IOException e)
		
		} //for (char c : contents)
		
		try {
			
			out.writeDouble(0.9); //Magic value which ends it
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		} //try {...} catch (IOException e)
		
	} //public void send (String message)
	
	public String receive() {
		
		boolean receiving = true;
		
		String message = null;
		
		while (receiving) {
		
			try {
			
				double unicode = in.readDouble();
				
				if (unicode == 0.1) {
					
					message = "";
					
				} else if (unicode == 0.9) {
					
					receiving = false;
					
				} else {
					
					char c = (char) unicode;
					message = message + c;
					
				} // if () {...} else if () {...} else
			
			} catch (IOException e) {
			
				e.printStackTrace();
			
			} //try {...} catch (IOException e)
		
		} //while (receiving)
		
		return message;
				
	} //public void receive()

} //public class directStringTransmitter
