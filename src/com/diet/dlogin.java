package com.diet;

import java.util.ArrayList;
import java.util.HashMap;
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

public class dlogin extends Activity 
{
	private EditText id;
	private EditText pwd;
    public ProgressDialog myDialog = null;
	private ArrayList<HashMap<String, Object>> menu;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dlogin);
        
        id = (EditText)findViewById(R.id.id);
        pwd = (EditText)findViewById(R.id.pwd);

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
				Intent intent = new Intent();
				intent.setClass(dlogin.this, main.class);
		
				startActivity(intent);   
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
	    .setTitle("about me")
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
