import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class HttpClientDemo {

	public static void main(String[] args) {
		try{
			Client client = Client.create();
			WebResource webResource = client.resource("http://httpbin.org/user-agent");
			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
			System.out.println("Get Reponse -------------");
			displayOutput(response);
			System.out.println("-----------------------");
			
			JSONObject jsonInput = new JSONObject().put("hello", "world");
			webResource = client.resource("http://httpbin.org/post");
			response = webResource.type("application/json")
	                .post(ClientResponse.class, jsonInput.toString());
			System.out.println("POST RESPONSE --------------");
			displayOutput(response);
			System.out.println("--------------");
		
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}

	private static void displayOutput(ClientResponse response) {
		if(response.getStatus() != 200)
			throw new RuntimeException("Failed - Error Code "+ response.getStatus());
		
		String output = response.getEntity(String.class);
		System.out.println("Response Body === ");
		System.out.println(output);
		System.out.println("Status Code == "+ response.getStatus());
		Map headers = response.getHeaders();
		Set<String> keys = headers.keySet();
		System.out.println("Headers === ");
		for (String key : keys){
			System.out.println("Key : "+key+" Value : "+headers.get(key));
		}
		
	}

}
