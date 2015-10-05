package Sample_Socket;
import java.net.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.HashMap;
import java.io.*;

public class serverSocket {

	private static Socket socket;
    private static ServerSocket server = null;
	private static final int PORT = 35000;
	private static String clientSentence;
	private static String protocolVersion;
	private static String protocolFormat;
    
  @SuppressWarnings("resource")
public void ServerSocketDemo() throws IOException {
	  
	  HashMap<String, String> map = new HashMap<String, String>();
	  Request requestObj = new Request();
      server = new ServerSocket(PORT);
	  System.out.println("Server Started and listening at the port 35000");

     //Server is running always. This is done using this while(true) loop
      while(true)
      {
          //Reading the message from the client
      	Socket client = server.accept();
      	DataInputStream dis = new DataInputStream(client.getInputStream());

      	clientSentence = dis.readUTF();
  		protocolVersion = dis.readUTF();
  		protocolFormat = dis.readUTF();

  		JSONParser parser = new JSONParser();
  		JSONObject jsonObj = (JSONObject) parser.parse(clientSentence);
  		
  		JSONObject request = (JSONObject) jsonObj.get("request");
  		String reqType = (String) request.get("req-type");
  		String reqOptional = (String) request.get("req-optional");
  		JSONObject reqParam =  (JSONObject) request.get("req-param");
  		
  		map.putAll(reqParam);
  		for (Iterator<String> iterator = map.keySet().iterator();
		  iterator.hasNext();) {
		    String key = (String) iterator.next();
		    System.out.println("Key : " + key + " Value : " + map.get(key));
		 }
  		requestObj.setRequestType(reqType);
  		requestObj.setRequestOptionalParam(reqOptional);
  		requestObj.setRequestParams(map);
  		requestObj.setProtocolVersion(protocolVersion);
  		requestObj.setProtocolFormat(protocolFormat);
         
      }
  }
  
  public static void main(String arg[]) {
    
    try {
      serverSocket server = new serverSocket();
      server.ServerSocketDemo();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}

