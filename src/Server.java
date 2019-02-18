import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {

	public static void main(String[] args) throws Exception {
		ServerSocket server = new ServerSocket(8000);
		System.out.println("Listening for connection on port 8000 ...");
		
		while (true) {
			try (Socket socket = server.accept()) {
				Date today = new Date();
				String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + today;
				socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
				
			}
			
			Socket clientSocket = server.accept();
			InputStreamReader inputReader = new InputStreamReader(clientSocket.getInputStream());
			BufferedReader bufferReader = new BufferedReader(inputReader);
			
			String line = bufferReader.readLine();
			while(!line.isEmpty()) {
				System.out.println(line);
				line = bufferReader.readLine();
			}
		}
	}
	
}