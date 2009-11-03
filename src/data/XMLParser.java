/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;
/**
 *
 * @author wode
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser {

	//No generics
	List posts;
	Document dom;


	public XMLParser(){
		//create a list to hold the employee objects
		posts = new ArrayList();
	}
	

	public void ParseXML(String url){
	    createXmlFile(url);
	    parseDocument();
	}
	
	public void ParseXML(Document document){
	    dom = document;
	    parseDocument();
	}
	
	
     /** 
     * @return          An ArrayList of FBPost objects
     * @see             FBPost
     */
	public List returnPosts(){
	    return posts;
	}
	
	private void createXmlFile(String url){
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();		
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			dom = db.parse(url);
		}
		catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}
		catch(SAXException se) {
			se.printStackTrace();
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	
	private void parseDocument(){
		//get the root elememt
		Element docEle = dom.getDocumentElement();
		NodeList nl = docEle.getElementsByTagName("stream_post");
		if(nl != null && nl.getLength() > 0) {
			for(int i = 0 ; i < nl.getLength();i++) {
				
				Element el = (Element)nl.item(i);
				//get the Employee object
				String pid = getTextValue(el, "post_id");
				String mess = getTextValue(el, "message");
				int type = getIntValue(el, "type");
				int actor = getIntValue(el, "actor_id");
				int updated = getIntValue(el, "updated_time");
				int created = getIntValue(el, "created_time"); 
				String perma = getTextValue(el, "permalink");
				FBPost p = new FBPost(pid, mess, type, actor, updated, created, perma);
				posts.add(p);
				System.out.println("Added");
			}
		}
	}


	private String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
		    try{
			Element el = (Element)nl.item(0);
		    textVal = el.getFirstChild().getNodeValue();
		    }
		    catch(NullPointerException e){
			textVal = "";
		    }
			
		}
		return textVal;
	}

	private int getIntValue(Element ele, String tagName) {
		//in production application you would catch the exception
		return Integer.parseInt(getTextValue(ele,tagName));
	}
	

	private void printData(){
		Iterator it = posts.iterator();
		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}

	
}