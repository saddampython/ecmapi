package Sample_Socket;

import java.net.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.io.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class clientSocket {

   private static Socket client = null;
   private static final int PORT = 35000;
	
  public void ClientSocketDemo() throws UnknownHostException, IOException {
	  client = new Socket("localhost", PORT);
		DataOutputStream out = new DataOutputStream(client.getOutputStream());
		
		String tdsProtocolVersion = "1.0";
		String tdsProtocolFormat = "json";
		
		JSONObject obj1 = new JSONObject();
		JSONObject obj2 = new JSONObject();		

		Map<String, String> map  = new LinkedHashMap<String, String>();
		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("key3", "value3");
		
		
	
		
		obj1.put("req-type", "value");
		obj1.put("req-optional", "reserved");
		obj1.put("req-param", map);
		
		obj2.put("request", obj1);
		
		out.writeUTF(obj2.toJSONString());
		out.writeUTF(tdsProtocolVersion);
		out.writeUTF(tdsProtocolFormat);
  } 
  
  public static void main(String arg[]) {
    clientSocket client = new clientSocket();
    try {
      client.ClientSocketDemo();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
