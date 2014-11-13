package com.example.rhodyguide;

import java.sql.SQLException;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class Login extends Activity {
	
	public final static String FIRST = "com.example.rhodyguide.FIRST";
	public final static String LAST = "com.example.rhodyguide.LAST";

	private String login, password;
	
	private Server server;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        server = new Server(Login.this);
        
        getActionBar().hide();
    }
    
    /** Called when the user clicks the Submit button */
    public void submitUser(View v) {
        
    	login = getUser();
    	password = getPassword();
    	
    	String[] name = new String[2];
    	    	
    	try {
    		if(server.checkUser(login, password)){
    			System.out.println("User valid");
    			name = server.getName(login, password);
    			
    	    	Intent intent = new Intent(this, MapActivity.class);
    	    	intent.putExtra("FIRST", name[0]);
    	    	intent.putExtra("LAST", name[1]);
    	    	startActivity(intent);
    		}
    		else {
    			System.out.println("Not a user");
    			name[0] = "Guest";
    			name[1] = "User";
    		}
    	} catch(SQLException e){
    		e.printStackTrace();
    	}
  
    	Log.e("Clicked", "Submit Clicked");
    }    
    
    /** Called when the user clicks the Register button */
    public void registerUser(View v) {
    	
    	login = getUser();
    	password = getPassword();
    	
    	server.newUser(login, password);
    	
    	Log.e("Clicked", "Register Clicked");
    }    
    
    /** Called when the user clicks the Guest button */
    public void guestUser(View v) {
    	
    	Log.e("Clicked", "Guest Clicked");
    	
    	Intent intent = new Intent(this, MapActivity.class);
    	startActivity(intent);
    	
    }    
    
    private String getUser() {
    	return ((EditText)findViewById(R.id.user)).getText().toString();
    }
    
    private String getPassword() {
    	return ((EditText)findViewById(R.id.pass)).getText().toString();
    }
    
}
