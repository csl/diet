package com.diet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class register extends Activity 
{
    private ListView listview;
    private String abouttp;
    
    public ProgressDialog myDialog = null;
	private ArrayList<HashMap<String, Object>> menu;
	private EditText username;
	private EditText pwd;
	private EditText name;
	private EditText tall;
	private EditText weight;
	
	private String loginurl;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
    	loginurl  = (String) this.getResources().getText(R.string.url);
    	
        username = (EditText)findViewById(R.id.username);
        pwd = (EditText)findViewById(R.id.pwd);
        name = (EditText)findViewById(R.id.name);
        tall = (EditText)findViewById(R.id.tall);
        weight = (EditText)findViewById(R.id.weight);

    	
        Button register = (Button)findViewById(R.id.register); 
        register.setOnClickListener(new Button.OnClickListener() 
        { 
          public void onClick(View v) 
          { 
          	  String daccount = username.getText().toString();
          	  String dpwd = pwd.getText().toString();
        	  String dname = name.getText().toString();
        	  String dtall = tall.getText().toString();
        	  String dweight = weight.getText().toString();
        	  
				int rep = toweb(loginurl + "register.php?username=" + username + 
										   "&pwd=" + pwd + "&name=" + name + "&tall=" + tall + "&weight=" + weight); 
				if (rep == 0)
					openOptionsDialog("register ok");
				else
					openOptionsDialog("register fail");
          } 
        }); 

        Button cancel = (Button)findViewById(R.id.cancel); 
        cancel.setOnClickListener(new Button.OnClickListener() 
        { 
          public void onClick(View v) 
          { 
        	  finish();
          } 
        }); 

        
        
        
    }
    
	public int toweb(String uriAPI)
	{
		int error = 0;
       HttpGet httpRequest = new HttpGet(uriAPI);
         
       try
        {
            HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);
            if(httpResponse.getStatusLine().getStatusCode() == 200)
            {
            	String strResult = EntityUtils.toString(httpResponse.getEntity());
            }
            else
            {
              //mTextView1.setText("Error Response: "+httpResponse.getStatusLine().toString());
            }
          }
          catch (ClientProtocolException e)
          {
            //mTextView1.setText(e.getMessage().toString());
            e.printStackTrace();
            error = 1;
          }
          catch (IOException e)
          {
            //mTextView1.setText(e.getMessage().toString());
            e.printStackTrace();
            error = 1;
          }
          catch (Exception e)
          {
            //mTextView1.setText(e.getMessage().toString());
            e.printStackTrace();
            error = 1;
          }

         return error;
	}
    
    
    private void openOptionsDialog(String info)
	{
	    new AlertDialog.Builder(this)
	    .setTitle("info")
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
