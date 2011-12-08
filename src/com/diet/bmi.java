package com.diet;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class bmi extends Activity implements OnClickListener
{
    private EditText height;
    private EditText weight;
    private TextView result;
    
    public ProgressDialog myDialog = null;
    
    static double bmi = 0;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi);
        
        height = (EditText)findViewById(R.id.height);
        weight = (EditText)findViewById(R.id.weight);
        result = (TextView)findViewById(R.id.result);
        
        Button A = (Button)findViewById(R.id.calBMI);
        A.setOnClickListener(this);
        
        result.setText("您現在BMI: " + bmi);
        
        Toast popup =  Toast.makeText(bmi.this, "請按上鍵頭跳出", Toast.LENGTH_SHORT);
        popup.show();
 
        
    }
    
    public void onClick(View v) 
    {
    	switch (v.getId()) {
	        case R.id.calBMI: // Do something when click button1
	        	
	        	if (height.getText().toString().equals("") || weight.getText().toString().equals(""))
	        	{
	        		openOptionsDialog("field is null");
	        		return;
	        	}
	        	
	        	int h = Integer.valueOf(height.getText().toString());
	        	int w = Integer.valueOf(weight.getText().toString());
	        		        	
	        	bmi = (double) h / w;
	        	
	        	String msg = "您的BMI值為: " + bmi;
	        	
	        	if (bmi < 18.5)
	        	{
	        		msg = msg + "\n體重過輕";
	        	}
	        	else if (bmi >= 18.5 && bmi < 24)
	        	{
	        		msg = msg + "\n正常範圍";
	        	}
	        	else if (bmi >= 24 && bmi < 27)
	        	{
	        		msg = msg + "\n過  重";
	        	}
	        	else if (bmi >= 24 && bmi < 27)
	        	{
	        		msg = msg + "\n輕度肥胖";
	        	}
	        	else if (bmi >= 27 && bmi < 35)
	        	{
	        		msg = msg + "\n中度肥胖";
	        	}
	        	else if (bmi > 35)
	        	{
	        		msg = msg + "\n重度肥胖";
	        	}
	        	result.setText(msg);
	        	
	        	break;
    	}
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
