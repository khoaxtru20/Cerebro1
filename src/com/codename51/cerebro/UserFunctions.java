// Important utility functions class. Contains functions to log in a user into the app and hence notify the server,
// logout from server, update user location, and all other such network related functions that make a service call
// to the server. Go through the use cases to gain clarity on how these functions are used

package com.codename51.cerebro;

import java.util.ArrayList;
import java.util.List;
import static com.codename51.cerebro.CommonUtilities.LOGIN_URL;
import static com.codename51.cerebro.CommonUtilities.LOGOUT_URL;
import static com.codename51.cerebro.CommonUtilities.CHAT_URL;
import static com.codename51.cerebro.CommonUtilities.GETUSERS_URL;
import static com.codename51.cerebro.CommonUtilities.UPDATEREGID_URL;
import static com.codename51.cerebro.CommonUtilities.GETLATLONG_URL;
import static com.codename51.cerebro.CommonUtilities.UPDATELOC_URL;
import static com.codename51.cerebro.CommonUtilities.PUBLICCHAT_URL;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;

public class UserFunctions {
	
	private JSONParser jsonParser;
	
	int bn = 0;
    
	// constructor
		public UserFunctions(){
			jsonParser = new JSONParser();
		}
		
		
		 /**
	     * Login user into the device.
	     *
	     */
	    
	    public JSONObject loginUser (String name, String password){
	    	
	    	List<NameValuePair> params = new ArrayList<NameValuePair>();
	    	
			params.add(new BasicNameValuePair("name", name));
			params.add(new BasicNameValuePair("password", password));
			
			JSONObject json = jsonParser.getJSONFromUrl(LOGIN_URL, params);		
			if(jsonParser.an == 1){
				bn = 1;
			}
			return json;
			
	    }
	    
	    /**
	     * Logout user from the device and server.
	     *
	     */
	    
	    public JSONObject logoutUserFromServer(String serverid){
	    	
	    	List<NameValuePair> params = new ArrayList<NameValuePair>();
	    	params.add(new BasicNameValuePair("serverid", serverid));
	    	
	    	JSONObject json = jsonParser.getJSONFromUrl(LOGOUT_URL, params);
	    	if(jsonParser.an == 1){
				bn = 1;
			}
			return json;
	    	
	    }
	    
	    /**
	     * Update USer location.
	     *
	     */
	    public JSONObject updateLocation(String serverid, String lat, String lon){
	    	List<NameValuePair> params = new ArrayList<NameValuePair>();
	    	params.add(new BasicNameValuePair("serverid", serverid));
	    	params.add(new BasicNameValuePair("latitude", lat));
	    	params.add(new BasicNameValuePair("longitude", lon));
	    	
	    	JSONObject json = jsonParser.getJSONFromUrl(UPDATELOC_URL, params);
	    	if(jsonParser.an == 1){
				bn = 1;
			}
			return json;
	    }
	    
	    
	    
	    /**
	     * Update User Registration ID
	     */
	    
	    public JSONObject updateRegid(String serverid, String regId){
	    	
	    	List<NameValuePair> params = new ArrayList<NameValuePair>();
	    	params.add(new BasicNameValuePair("serverid", serverid));
	    	params.add(new BasicNameValuePair("regId", regId));
	    	
	    	JSONObject json = jsonParser.getJSONFromUrl(UPDATEREGID_URL, params);
	    	if(jsonParser.an == 1){
				bn = 1;
			}
			return json;
	    }
	    
	    /**
	     * Get User Latitude Longitude
	     */
	    public JSONObject getUserLatLong(String userName){
	    	List<NameValuePair> params = new ArrayList<NameValuePair>();
	    	params.add(new BasicNameValuePair("name", userName));
	    	JSONObject json = jsonParser.getJSONFromUrl(GETLATLONG_URL, params);
	    	if(jsonParser.an == 1){
				bn = 1;
			}
			return json;
	    }
	    
	    
	    /**
	     * Get Online Users
	     */
	    
	    public JSONObject getUsers(String latitude, String longitude){
	    	List<NameValuePair> params = new ArrayList<NameValuePair>(); 
	    	params.add(new BasicNameValuePair("latitude", latitude));
	    	params.add(new BasicNameValuePair("longitude", longitude));
	    	JSONObject json = jsonParser.makeHttpRequest(GETUSERS_URL, "GET", params);
	    	if(jsonParser.an == 1){
				bn = 1;
			}
			return json;
	    }
	    
	    
	    /**
	     * Send a chat message
	     *
	     */
	    
	    public JSONObject sendChat(String regId, String message, String name, String sid){
	    	
	    	List<NameValuePair> params = new ArrayList<NameValuePair>();
	    	
	    	params.add(new BasicNameValuePair("regId", regId));
	    	params.add(new BasicNameValuePair("message", message));
	    	params.add(new BasicNameValuePair("name", name));
	    	params.add(new BasicNameValuePair("sid", sid));
	    	
	    	JSONObject json = jsonParser.getJSONFromUrl(CHAT_URL, params);		
			if(jsonParser.an == 1){
				bn = 1;
			}
			return json;
	    }
	    
	    /**
	     *
	     * Send a Public Chat
	     */
	    
	    public JSONObject sendPublicChat(String name, String message, String sid, String lat, String lon){
	    	List<NameValuePair> params = new ArrayList<NameValuePair>();
	    	
	    	params.add(new BasicNameValuePair("name", name));
	    	params.add(new BasicNameValuePair("message", message));
	    	params.add(new BasicNameValuePair("sid", sid));
	    	params.add(new BasicNameValuePair("latitude", lat));
	    	params.add(new BasicNameValuePair("longitude", lon));
	    	
	    	JSONObject json = jsonParser.getJSONFromUrl(PUBLICCHAT_URL, params);		
			if(jsonParser.an == 1){
				bn = 1;
			}
			return json;
	    }
	    
		/**
		 * Function get Login status
		 * */
		public boolean isUserLoggedIn(Context context){
			SqliteHandler db = new SqliteHandler(context);
			int count = db.getRowCount();
			if(count > 0){
				// user logged in
				return true;
			}
			return false;
		}
		
		
		/**
		 * Function to logout user
		 * Reset Database
		 * */
		public boolean logoutUser(Context context){
			SqliteHandler db = new SqliteHandler(context);
			db.resetTables();
			return true;
		}
}
