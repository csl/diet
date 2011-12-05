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
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

public class food extends Activity implements OnClickListener
{

	static public int kind_id = 0;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food);
        
        Button A = (Button)findViewById(R.id.cal);
        A.setOnClickListener(this);
        Button B = (Button)findViewById(R.id.exit);
        B.setOnClickListener(this);
        Button C = (Button)findViewById(R.id.foodchoice);
        C.setOnClickListener(this);
        
        Spinner spinner = (Spinner) findViewById(R.id.foodkind);
        
        String strlist [] = "";

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, strlist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView adapterView, View view, int position, long id){
            }
            public void onNothingSelected(AdapterView arg0) {
            }
        });
        
    }
    
    public void onClick(View v) 
    {
    	switch (v.getId()) {
	        case R.id.cal:
	        	break;
	        case R.id.exit:
	        	break;
	        case R.id.foodchoice:
	        	break;
    	}
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
