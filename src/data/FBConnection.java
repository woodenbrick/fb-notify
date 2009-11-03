/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;
import com.google.code.facebookapi.*;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;
/**
 *
 * @author wode
 */
public class FBConnection {
    private static String apiKey = "26d3226223a91268db2a4915cd7e0b69";
    private static String secret = "d5077b8ef8ca5b1e948ec16ed3fc7e0c";
    private String token;
    public FacebookXmlRestClient client;
    
    public void FBConnection() throws URISyntaxException {
      System.out.println("FUck");
      try {
      client = new FacebookXmlRestClient(apiKey, secret);
      token = client.auth_createToken();
      System.out.println(token);
      }
      catch (FacebookException e){
	  e.printStackTrace();
      }
      URI authUrl = new URI("http://www.facebook.com/login.php?api_key=" 
                + apiKey + "&v=1.0" 
                + "&auth_token=" + token);
	  useBrowser(authUrl);
      
    }
    
    private void useBrowser(URI uri){
	try {
	    Desktop.getDesktop().browse(uri);
	    System.out.println("Any key to continue");
	    System.in.read();
	}
	catch (Exception e){
	    System.out.println(e.getMessage());
	}
    }
    
    private boolean checkPermissions() throws FacebookException, URISyntaxException{
	if (! client.users_hasAppPermission(Permission.READ_STREAM)){
	    String s = String.format("http://www.facebook.com/connect/prompt_permissions.php?api_key=%s&v=1.0&next=http://www.facebook.com/connect/login_success.html?xxRESULTTOKENxx&display=popup&ext_perm=read_stream,publish_stream&enable_profile_selector=1", apiKey);
	    useBrowser(new URI(s));
	}
	return true;
	
    }
    
}
