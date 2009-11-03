/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

/**
 *
 * @author wode
 */

import java.net.MalformedURLException;
import java.net.URL;
import java.security.Timestamp;
import java.util.Date;

/**
 *
 * @author wode
 */
public class FBPost {
    private String post_id;
    private String message;
    private int type;
    private int actor_id; //maybee should pe a FBUser object
    private Date updated_time = new Date();
    private Date created_time = new Date();
    private URL permalink;
    
    public FBPost(String post_id, String message, int type, int actor_id, int updated_time, 
	    int created_time, String permalink){
	System.out.println("Creating new post" + post_id);
	System.out.println(post_id + "\n"+ message +"\n"+ type +"\n"+ actor_id +"\n" + updated_time + "\n"+ created_time + "\n" +permalink);
	this.post_id = post_id;
	this.message = message;
	this.type = type;
	this.actor_id = actor_id;
	this.updated_time.setTime(updated_time);
	this.created_time.setTime(created_time);
	try {
	    this.permalink = new URL("http://facebook.com" + permalink);
	}
	catch(MalformedURLException e){
	    e.printStackTrace();
	    System.out.println(permalink);
	}
	System.out.println("Done.");
	
    }

    
    public String get_post_id(){
	return post_id;
    }
    
    public String get_message(){
	return message;
    }
    
    public int get_type(){
	return type;
    }
    
    public int get_actor_id(){
	return actor_id;
    }
    
    public Date get_updated_time(){
	return updated_time;
    }
    
    public Date get_created_time(){
	return created_time;
    }
    
    private String date_to_string(Date date){
	return date.toString();
    }
    
    public URL get_permalink(){
	return permalink;
    }
    
    @Override
    public String toString(){
	String s = new String("");
	s.concat(message + "\n");
	s.concat(date_to_string(updated_time) + "\n");
	return s;
    }
}

