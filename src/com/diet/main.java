package com.diet;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

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
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class main extends Activity 
{
    private ListView listview;
    private String abouttp;
    
    public ProgressDialog myDialog = null;
	private ArrayList<HashMap<String, Object>> menu;
    private CheckXMLStruct data;
    private int result_check;
	
    private String murl = "";
	
    private int year, month, day;

    final Calendar c = Calendar.getInstance();
    
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
          
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH) + 1;
        day = c.get(Calendar.DAY_OF_MONTH);          
        
        listview = (ListView)findViewById(R.id.listview);
        
        menu = new ArrayList<HashMap<String, Object>>();
        
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("ItemTitle", "BMI值檢測" );
		map.put("ItemText", "BMI checking");
		menu.add(map);

		map = new HashMap<String, Object>();
		map.put("ItemTitle", "食物攝取" );
		map.put("ItemText", "eat food");
		menu.add(map);

		map = new HashMap<String, Object>();
		map.put("ItemTitle", "運動耗量" );
		map.put("ItemText", "sport");
		menu.add(map);

		map = new HashMap<String, Object>();
		map.put("ItemTitle", "健康資訊" );
		map.put("ItemText", "health information");
		menu.add(map);

		map = new HashMap<String, Object>();
		map.put("ItemTitle", "輸入日期紀錄" );
		map.put("ItemText", "input data/record");
		menu.add(map);

		map = new HashMap<String, Object>();
		map.put("ItemTitle", "登出" );
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
		    				intent.setClass(main.this, bmi.class);
		    		
		    				startActivity(intent);
	        			   	break;
	        		   case 1:
		       				intent = new Intent();
		    				intent.setClass(main.this, food.class);
		    		
		    				startActivity(intent);
	        			   	break;
	        		   case 2:
		       				intent = new Intent();
		    				intent.setClass(main.this, sport.class);
		    		
		    				startActivity(intent);
	        			   	break;
	        		   case 3:

	        			   	break;
	        		   case 4:
	        			   check_upload();
	        			   	break;
	        		   case 5:
	        			   finish();
	        			   break;
	        		   case 6:
	        			    finish();
	        			   	break;
        		   }
        	   }  
        });
    }
    
    public void check_upload()
    {
    	
    	result_check = 0;
    	
         murl = (String) this.getResources().getText(R.string.url);

	      //Progress
	      myDialog = ProgressDialog.show
	                 (
	                   main.this,
	                   "檢查 和 上載中",
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
	                String uriAPI = murl + "check_date.php?today=" + year + "/" + month + "/" + day;
	                
	                URL url = null;
	                try{
		                url = new URL(uriAPI);
		                
		                SAXParserFactory spf = SAXParserFactory.newInstance();
		                SAXParser sp = spf.newSAXParser();
		                XMLReader xr = sp.getXMLReader();
		                //Using check handler for xml
		                CheckXMLHandler myHandler = new CheckXMLHandler();
		                xr.setContentHandler(myHandler);
		                //open connection
		                xr.parse(new InputSource(url.openStream()));
		        		//verify OK
		      	        data = myHandler.getParsedData();
	                }
	                catch(Exception e){
	     	            e.printStackTrace();
	     	           myDialog.dismiss();
	    	            return;
	                }
	                
	      }
	      catch (Exception e)
	      {
	            e.printStackTrace();
	            myDialog.dismiss();
	      }
	      finally
	      {
	        	 try
	        	 {
	  	              if (data.getscuess() == 1)
	  	              {
	  	            	//update
		  				toweb(murl + "update.php?date=" + year + "/" + month + "/" + day + "&bmi=" + bmi.bmi +
									   "&addhot="  + food.hot + "&losehot=" + sport.hot + "&addmenu=" + food.showlist() + "&losemenu=" + sport.showlist()); 
		              }
	  	              else
	  	              {
	  	            	//insert
	  					toweb(murl + "insert.php?date=" + year + "/" + month + "/" + day + "&bmi=" + bmi.bmi +
								   "&addhot="  + food.hot + "&losehot=" + sport.hot + "&addmenu=" + food.showlist() + "&losemenu=" + sport.showlist()); 
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
