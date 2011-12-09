package com.diet;

import java.util.ArrayList;import java.util.HashMap;

import java.io.IOException;
import java.net.URL;
import javax.xml.parsers.*;

import org.xml.sax.XMLReader;
import org.xml.sax.InputSource;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class dlogin extends Activity 
{
	private EditText id;
	private EditText pwd;
    public ProgressDialog myDialog = null;
	private ArrayList<HashMap<String, Object>> menu;
    private LoginXMLStruct data;
    private String account;
	private String password;
	private String murl;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dlogin);
        
        murl = (String) this.getResources().getText(R.string.url);
        
        id = (EditText)findViewById(R.id.id);
        pwd = (EditText)findViewById(R.id.pwd);

        data = new LoginXMLStruct();

        Button register = (Button)findViewById(R.id.register); 
        register.setOnClickListener(new Button.OnClickListener() 
        { 
          public void onClick(View v) 
          { 
 				Intent intent = new Intent();
				intent.setClass(dlogin.this, register.class);
		
				startActivity(intent);
          } 
        }); 

        Button mButton02 = (Button)findViewById(R.id.login); 
        mButton02.setOnClickListener(new Button.OnClickListener() 
        { 
          public void onClick(View v) 
          { 
        	  account = id.getText().toString();
        	  password = pwd.getText().toString();
        	  	
        	  //account or password is NULL
              if (account.equals("") || password.equals(""))
              {
            	  openOptionsDialog("accout or pwd is null");
                  
                  return;
              }
    	                   
    	      //Progress
    	      myDialog = ProgressDialog.show
    	                 (
    	                   dlogin.this,
    	                   "µn¤J¤¤",
    	                   "...", 
    	                   true
    	                 );
    	      
    	      new Thread()
    	      { 
    	        public void run()
    	        { 
    	          try
    	          { 
    	        	  
    	        		//Create url
    	                String uriAPI = murl + "login.php?username=" + account + "&pwd=" + password;
    	                
    	                URL url = null;
    	                try{
    		                url = new URL(uriAPI);
    		                
    		                SAXParserFactory spf = SAXParserFactory.newInstance();
    		                SAXParser sp = spf.newSAXParser();
    		                XMLReader xr = sp.getXMLReader();
    		                //Using login handler for xml
    		                LoginXMLHandler myHandler = new LoginXMLHandler();
    		                xr.setContentHandler(myHandler);
    		                //open connection
    		                xr.parse(new InputSource(url.openStream()));
    		        		//verify OK
    		      	        data = myHandler.getParsedData();
    	                }
    	                catch(Exception e){
    	     	            e.printStackTrace();
    	    	            return;
    	                }
    	                
        	}
    	    catch (Exception e)
    	    {
    	            e.printStackTrace();
    	    }
    	    finally
    	    {
    	        	 try
    	        	  {
	    	              if (data.getscuess() != -1)
	    	              {
	    	            	
	    	  				Intent intent = new Intent();
	    	  				intent.setClass(dlogin.this, main.class);
	    	  	            Bundle bData = new Bundle();
	    	  	            bData.putString("account", account);
	    	            	bmi.bmi = data.getscuess();
	    	            	intent.putExtras( bData );
	    	  				startActivity(intent);   
	    	              }
	    	              else
	    	              {
	    	            	Log.i("ERROR", "LOGINFAIL");
	    	              }
    	        	  }
    	        	 catch (Exception err)
    	        	 {
    	    	            err.printStackTrace();
    	        	 }
    	        	  
    	        	 myDialog.dismiss();
    	          }      	          
    	        }
    	      }.start();        		
         	}
        });
        	  
        Button mButton03 = (Button)findViewById(R.id.fb); 
        mButton03.setOnClickListener(new Button.OnClickListener() 
        { 
          public void onClick(View v) 
          { 
        	  finish();
          } 
        }); 
        
    }
    
    private void openOptionsDialog(String info)
	{
	    new AlertDialog.Builder(this)
	    .setTitle("msg")
	    .setMessage(info)
	    .setPositiveButton("OK",
	        new DialogInterface.OnClickListener()
	        {
	         public void onClick(DialogInterface dialoginterface, int i)
	         {
	        	 
	         }
	         }
	        )
	    .show();
	  }
}
