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
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class main extends Activity 
{
    private ListView listview;
    private String abouttp;
    
    public ProgressDialog myDialog = null;
	private ArrayList<HashMap<String, Object>> menu;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        listview = (ListView)findViewById(R.id.listview);
        
        menu = new ArrayList<HashMap<String, Object>>();
        
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("ItemTitle", "BMI���˴�" );
		map.put("ItemText", "BMI checking");
		menu.add(map);

		map = new HashMap<String, Object>();
		map.put("ItemTitle", "�������" );
		map.put("ItemText", "eat food");
		menu.add(map);

		map = new HashMap<String, Object>();
		map.put("ItemTitle", "�B�ʯӶq" );
		map.put("ItemText", "sport");
		menu.add(map);

		map = new HashMap<String, Object>();
		map.put("ItemTitle", "���d��T" );
		map.put("ItemText", "health information");
		menu.add(map);

		map = new HashMap<String, Object>();
		map.put("ItemTitle", "��J�������" );
		map.put("ItemText", "input data/record");
		menu.add(map);
		
		map = new HashMap<String, Object>();
		map.put("ItemTitle", "��ƾ�&�Ƨѿ�" );
		map.put("ItemText", "");
		menu.add(map);

		map = new HashMap<String, Object>();
		map.put("ItemTitle", "�n�X" );
		map.put("ItemText", "login out");
		menu.add(map);
		
		
		SimpleAdapter listitemAdapter=new SimpleAdapter(this,  
        										menu, 
        										R.layout.no_listview_style,
        										new String[]{"ItemTitle","ItemText"}, 
        										new int[]{R.id.topTextView,R.id.bottomTextView}  
        										);  
        listview.setAdapter(listitemAdapter);              
        listview.setOnItemClickListener(new OnItemClickListener() 
        {          
        	   @Override  
        	   public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,  
        	     long arg3) 
        	   {
        		   
        		   Intent intent = null;
        		   
        		   switch (arg2)
        		   {
	        		   case 0:
		       				intent = new Intent();
		    				intent.setClass(main.this, diet.class);
		    		
		    				startActivity(intent);
	        			   	break;
	        		   case 1:
		       				intent = new Intent();
		    				intent.setClass(main.this, diet.class);
		    		
		    				startActivity(intent);
	        			   	break;
	        		   case 2:
		       				intent = new Intent();
		    				intent.setClass(main.this, diet.class);
		    		
		    				startActivity(intent);
	        			   	break;
	        		   case 3:
		       				intent = new Intent();
		    				intent.setClass(main.this, diet.class);
		    		
		    				startActivity(intent);
	        			   	break;
	        		   case 4:
		       				intent = new Intent();
		    				intent.setClass(main.this, diet.class);
		    		
		    				startActivity(intent);
	        			   	break;
	        		   case 5:
		       				intent = new Intent();
		    				intent.setClass(main.this, diet.class);
		    		
		    				startActivity(intent);
	        			   	break;
	        		   case 6:
	        			    finish();
	        			   	break;
        		   }
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
