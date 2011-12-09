package com.diet;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class sport extends Activity implements OnClickListener
{
    public static ArrayList<String> listarray = new ArrayList<String>();
	public int kind_id = -1;
	public String home_sport[], khome_sport[], play_sport[],hplay_sport[], sport[], hsport[], other_sport[], hother_sport[];
	public String msg [] = null;
	public String hmsg[] = null;
	private TextView showlist;
	private EditText et[]; 
	private Spinner sr[]; 
    public static double hot = 0;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sport);
        
        home_sport = this.getResources().getStringArray(R.array.home_sport);
        khome_sport = this.getResources().getStringArray(R.array.khome_sport);
        play_sport = this.getResources().getStringArray(R.array.play_sport);
        hplay_sport = this.getResources().getStringArray(R.array.hplay_sport);
        sport = this.getResources().getStringArray(R.array.sport);
        hsport = this.getResources().getStringArray(R.array.hsport);
        other_sport = this.getResources().getStringArray(R.array.other_sport);
        hother_sport = this.getResources().getStringArray(R.array.hother_sport);
        
        showlist = (TextView)findViewById(R.id.showlist);
        
        Button A = (Button)findViewById(R.id.cal);
        A.setOnClickListener(this);
        Button B = (Button)findViewById(R.id.clear);
        B.setOnClickListener(this);
        Button C = (Button)findViewById(R.id.exit);
        C.setOnClickListener(this);
        Button D = (Button)findViewById(R.id.schoice);
        D.setOnClickListener(this);
        
        Spinner spinner = (Spinner) findViewById(R.id.foodkind);
 
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
				R.array.kind_sport,
				android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView adapterView, View view, int position, long id)
            {
            	kind_id = position;
            }
            
            public void onNothingSelected(AdapterView arg0) {
            }
        });
        
        showlist.setText(showlist());
        
    }
    
    public void onClick(View v) 
    {
    	switch (v.getId()) {
	        case R.id.cal:
	        	//String listv = showlist.getText().toString();
	        	
	        	openOptionsDialog("消耗 總熱量 ＝ " + hot + "卡");

	        	break;
	        case R.id.clear:
	        	openOptionsDialog("clear all");
	        	listarray.clear();
	        	showlist.setText("");
	        	hot=0;
	        	break;
	        case R.id.exit:
	        	finish();
	        	break;
	        case R.id.schoice:
	        	if (kind_id != -1)
	        	{
	        		switch  (kind_id)
	        		{
	        			case 0:
	        				msg = home_sport;
	        				hmsg = khome_sport;
	        				break;
	        			case 1:
	        				msg = play_sport;
	        				hmsg = hplay_sport;
	        				break;
	        			case 2:
	        				msg = sport;
	        				hmsg = hsport;
	        				break;	        			
	        			case 3:
	        				msg = other_sport;
	        				hmsg = hother_sport;
	        				break;
	        		}
	        		
	        		ScrollView sv = new ScrollView(this);
	        		LinearLayout ll = new LinearLayout(this);
	        		ll.setOrientation(LinearLayout.VERTICAL);
	        		sv.addView(ll);
	        		
        			TextView tv = new TextView(this);
        			tv.setText("[時間選擇 unit,30分鐘]");
        			ll.addView(tv);	       
        			
	        		et = new EditText[msg.length];
	        		sr = new Spinner[msg.length]; 
	        		for(int i = 0; i < 3; i++) 
	        		{
	        			et[i] = new EditText(this);
	        			et[i].setText("0");
	        			ll.addView(et[i]);	        			
	        			sr[i] = new Spinner(this);
	        			
	        			ArrayAdapter<CharSequence> adapter = null;
	        			
		        		switch  (kind_id)
		        		{
		        			case 0:
		        				adapter = ArrayAdapter.createFromResource(this,
		        						R.array.home_sport,
		        						android.R.layout.simple_spinner_item);
		        				break;
		        			case 1:
		        				adapter = ArrayAdapter.createFromResource(this,
		        						R.array.play_sport,
		        						android.R.layout.simple_spinner_item);
		        				break;
		        			case 2:
		        				adapter = ArrayAdapter.createFromResource(this,
		        						R.array.sport,
		        						android.R.layout.simple_spinner_item);
		        				break;	        			
		        			case 3:
		        				adapter = ArrayAdapter.createFromResource(this,
		        						R.array.other_sport,
		        						android.R.layout.simple_spinner_item);
		        				break;
		        		}	        			
	        			
	        		    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        		    sr[i].setAdapter(adapter);	
	        		   
	        			ll.addView(sr[i]);	        			
	        		}	        		
	        		
	        		
	        		AlertDialog.Builder builder = new AlertDialog.Builder(this)
	        		.setTitle("請選擇")
	        		.setMessage("")
	        		.setView(sv)
	        		.setPositiveButton("Ok",
	        		        new DialogInterface.OnClickListener() {
	        		        public void onClick(DialogInterface dialog, int which) 
	        		        {
	        		        	showlist.setText("");
	        		        	double total = 0;
	        	        		for(int i = 0; i < 3; i++) 
	        	        		{
	        	        			
	        	        			if (et[i].getText().toString().equals("0")) continue;
	        	        			
	        	        				int index = sr[i].getSelectedItemPosition();

	        	        				listarray.add(et[i].getText().toString() + "分鐘, " + sr[i].getSelectedItem().toString() + " (" + hmsg[index] + ")");
		        	        			
		        	        			try
		        	        			{
		        	        			  total = total + (double) (Double.parseDouble(et[i].getText().toString())/30) * Integer.parseInt(hmsg[index]);
		        	        		
		        	        			}
		        	        			catch (Exception x)
		        	        			{
		        	        				x.printStackTrace();
		        	        			}
	        	        		}
	        	        		
	        	        		hot += total;
	        	        		 
	        	        	   	String listv = "";
	        	            	for (int j=0; j<listarray.size(); j++)
	        	            	{
	        	            			listv = listv + listarray.get(j) + "\n"; 
	        	            	}
	        	            		        	        		 
	        		        	showlist.setText(listv);
	        		        }
	        		});
	        		
	        		builder.show();
	        		
	        	}
	        	break;
    	}
    }
    
    
    private void select(int kind_id2) {
		// TODO Auto-generated method stub
		
	}
    
    public static String showlist()
    {
    	//show
    	String listv = "";
    	for (int j=0; j<listarray.size(); j++)
    	{
    			listv = listv + listarray.get(j) + ","; 
    	}
    	
    	return listv;
    }

	private void openOptionsDialog(String info)
	{
	    new AlertDialog.Builder(this)
	    .setTitle("訊息")
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
