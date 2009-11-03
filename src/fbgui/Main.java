/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fbgui;

import com.google.code.facebookapi.ExtensibleClient;
import com.google.code.facebookapi.FacebookException;
import com.google.code.facebookapi.FacebookJsonRestClient;
import com.google.code.facebookapi.FacebookXmlRestClient;
import com.google.code.facebookapi.Permission;
import com.google.code.facebookapi.schema.FriendsGetResponse;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import data.Downloader;
import data.FBConnection;
import data.FBPost;
import data.XMLParser;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.*;

/**
 *
 * @author wode
 */
public class Main {
    
    
    
    public static void main(String[] args) throws Exception {
	Downloader.setPicPath("/home/wode/Desktop/pics");
	FBConnection fbconn = new FBConnection();
	if (fbconn.client == null){
	    System.exit(1);
	}
	long userid = fbconn.client.users_getLoggedInUser();
	Document stream = fbconn.client.stream_get(userid, null, null, null, null, null, null);
	XMLParser parser = new XMLParser();
	parser.ParseXML(stream);
	List<FBPost> posts = parser.returnPosts();
	Iterator<FBPost> iter = posts.iterator();
	while(iter.hasNext()){
	    System.out.print(iter.next().get_message());
	}
	//Downloader.DownloadImage(new URL("file:///home/wode/lfm.png"));
	//	
//	try{	
//      Desktop.getDesktop().browse(new URI(url));
//	System.out.println("Any key to cont");
//      System.in.read();
//	}
//	catch(Exception e){
//	    System.out.println("Problem starting browser");
//	    System.exit(0);
//	}
//      
//      String session = client.auth_getSession(token, false);
//      Long userid = client.users_getLoggedInUser();
//      //System.out.println("Fetching friends for" + userid);
//      if (client.users_hasAppPermission(Permission.READ_STREAM)){
//	  System.out.println("Has perm");
//      }
//      else {
//	  String s = String.format("http://www.facebook.com/connect/prompt_permissions.php?api_key=%s&v=1.0&next=http://www.facebook.com/connect/login_success.html?xxRESULTTOKENxx&display=popup&ext_perm=read_stream,publish_stream&enable_profile_selector=1", apiKey);
//	  Desktop.getDesktop().browse(new URI(s));
//	  System.out.println("No perm");
//	  
//      }
//      
//      //System.exit(0);
//      Document friends = client.stream_get(userid, null, null, null, null, null, null);
//      //NodeList uids = friends.getElementsByTagName("uid");
//      //Node node = uids.item(1);
//      //String s = node.getTextContent();
//      //System.out.println(s);
//      OutputFormat format = new OutputFormat(friends);
//      try {
//      BufferedWriter out = new BufferedWriter(new FileWriter(new File("cunt")));
//      XMLSerializer serial = new XMLSerializer(out, format);
//      serial.serialize(friends);
//      System.out.println(friends.toString());
//      }
//      catch(IOException e){
//	  System.out.println("Problem opening cunt");
//      }
//      
//} 
    }
}
