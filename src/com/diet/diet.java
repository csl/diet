package com.diet;

import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class diet extends Activity 
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
        setContentView(R.layout.diet);
        
        listview = (ListView)findViewById(R.id.listview);
        
        //String list
	    abouttp = (String) this.getResources().getText(R.string.about_me);
        
        menu = new ArrayList<HashMap<String, Object>>();
        
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("ItemTitle", "關於我" );
		map.put("ItemText", "about me");
		menu.add(map);

		map = new HashMap<String, Object>();
		map.put("ItemTitle", "登錄" );
		map.put("ItemText", "login");
		menu.add(map);

		map = new HashMap<String, Object>();
		map.put("ItemTitle", "離開" );
		map.put("ItemText", "exit");
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
        		   switch (arg2)
        		   {
	        		   case 0:
	        			    openOptionsDialog(abouttp);
	        			   	break;
	        		   case 1:
	        			   
	        			   	break;
	        		   case 2:
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
