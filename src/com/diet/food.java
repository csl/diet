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

public class food extends Activity implements OnClickListener
{
    public static ArrayList<String> listarray = new ArrayList<String>();
	public int kind_id = -1;
	public String five[], hfive[], ebfish[], hebfish[], fruit[], hfruit[], veg[], hveg[], oil[], hoil[];
	public String msg [] = null;
	public String hmsg[] = null;
	private TextView showlist;
	private TextView cb[];
	private EditText et[]; 
    public static int hot = 0;
    food mydiet = this;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food);
        
        five = this.getResources().getStringArray(R.array.five);
        hfive = this.getResources().getStringArray(R.array.hfive);
        ebfish = this.getResources().getStringArray(R.array.ebfish);
        hebfish = this.getResources().getStringArray(R.array.hebfish);
        fruit = this.getResources().getStringArray(R.array.fruit);
        hfruit = this.getResources().getStringArray(R.array.hfruit);
        veg = this.getResources().getStringArray(R.array.veg);
        hveg = this.getResources().getStringArray(R.array.hveg);
        oil = this.getResources().getStringArray(R.array.oil);
        hoil = this.getResources().getStringArray(R.array.hoil);
        
        showlist = (TextView)findViewById(R.id.showlist);
        
        Button A = (Button)findViewById(R.id.cal);
        A.setOnClickListener(this);
        Button B = (Button)findViewById(R.id.clear);
        B.setOnClickListener(this);
        Button C = (Button)findViewById(R.id.exit);
        C.setOnClickListener(this);
        Button D = (Button)findViewById(R.id.foodchoice);
        D.setOnClickListener(this);        
        Spinner spinner = (Spinner) findViewById(R.id.foodkind);
 
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
				R.array.kind,
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
        
    }
    
    public void onClick(View v) 
    {
    	switch (v.getId()) {
	        case R.id.cal:
	        	String listv = showlist.getText().toString();
	        	
	        	openOptionsDialog("總熱量 ＝ " + hot + "卡");

	        	break;
	        case R.id.clear:
	        	listarray.clear();
	        	showlist.setText("");
	        	hot = 0;
	        	break;
	        case R.id.exit:
	        	finish();
	        	break;
	        case R.id.foodchoice:
	        	if (kind_id != -1)
	        	{
	        		switch  (kind_id)
	        		{
	        			case 0:
	        				msg = five;
	        				hmsg = hfive;
	        				break;
	        			case 1:
	        				msg = ebfish;
	        				hmsg = hebfish;
	        				break;
	        			case 2:
	        				msg = fruit;
	        				hmsg = hfruit;
	        				break;
	        			case 3:
	        				msg = veg;
	        				hmsg = hveg;
	        				break;
	        			case 4:
	        				msg = oil;
	        				hmsg = hoil;
	        				break;
	        		}
	        		
	        		if (msg == null)  return;
	        		
	        		
	        		ScrollView sv = new ScrollView(this);
	        		LinearLayout ll = new LinearLayout(this);
	        		ll.setOrientation(LinearLayout.VERTICAL);
	        		sv.addView(ll);
	        			       
	        		cb = new TextView[msg.length];
	        		et = new EditText[msg.length];
	        		for(int i = 0; i < msg.length; i++) {
	        			cb[i] = new TextView(this);
	        			cb[i].setText(msg[i].toString());
	        			ll.addView(cb[i]);
	        			et[i] = new EditText(this);
	        			et[i].setText("0");
	        			ll.addView(et[i]);	        			
	        		}	        		
	        		
	        		
	        		AlertDialog.Builder builder = new AlertDialog.Builder(this)
	        		.setTitle("請選擇")
	        		.setMessage("")
	        		.setView(sv)
	        		.setPositiveButton("Ok",
	        		        new DialogInterface.OnClickListener() {
	        		        public void onClick(DialogInterface dialog, int which) 
	        		        {
	        		        	int total = 0;
	        	        		for(int i = 0; i < msg.length; i++) {
	        	        			
	        	        			if (et[i].getText().toString().equals("0") || et[i].getText().toString().equals("")) continue;
	        	        			
		        	        			listarray.add(msg[i] + "(" + hmsg[i] + ")");
		        	        			
		        	        			try
		        	        			{
		        	        			  total = total + Integer.parseInt(et[i].getText().toString()) * Integer.parseInt(hmsg[i]);
		        	        		
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
    
    
    public static String showlist()
    {
    	
    	//show
    	String listv = "";
    	for (int j=0; j<listarray.size(); j++)
    	{
    			listv = listv + listarray.get(j) + "\n"; 
    	}
    	
    	return listv;
   	
    }
    
    private void select(int kind_id2) {
		// TODO Auto-generated method stub
		
	}

	private void openOptionsDialog(String info)
	{
	    new AlertDialog.Builder(this)
	    .setTitle("message")
	    .setMessage(info)
	    .setPositiveButton("OK",
	        new DialogInterface.OnClickListener()
	        {
	         public void onClick(DialogInterface dialoginterface, int i)
	         {
	          	    new AlertDialog.Builder(mydiet)
	        	      .setTitle("問題")
	        	      .setMessage("是否要繼續計算運動耗量")
	        	      .setNegativeButton("NO",
	        	          new DialogInterface.OnClickListener() {
	        	          
	        	            public void onClick(DialogInterface dialoginterface, int i) {
	        	              
	        	            }
	        	      }
	        	      )
	        	   
	        	      .setPositiveButton("YES",
	        	          new DialogInterface.OnClickListener() {
	        	          public void onClick(DialogInterface dialoginterface, int i) 
	        	          {
	        	        	    Intent  intent = new Intent();
			    				intent.setClass(food.this, sport.class);
			    		
			    				startActivity(intent);
			    				finish();
	        	          }
	        	          
	        	      }
	        	      )
	        	      .show();	        	
	         }
	         }
	        )
	    .show();
	  }
}
