package com.diet;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CheckXMLHandler extends DefaultHandler
{
	  private boolean h_id = false;
	  private boolean h_scuess = false;
	  
	  private CheckXMLStruct myParsedExampleDataSet = new CheckXMLStruct();
	  
	  public CheckXMLStruct getParsedData() 
	  {
	       return this.myParsedExampleDataSet;
	  }
	  
	  public void startDocument() throws SAXException 
	  {
	       this.myParsedExampleDataSet = new CheckXMLStruct();
	  }

	  @Override
	  public void endDocument() throws SAXException {
	       // Nothing to do
	  }

	  @Override
	  public void startElement(String namespaceURI, String localName,
	            String qName, Attributes atts) throws SAXException 
	  {
	       if (localName.toLowerCase().equals("check")) {
	           this.h_scuess = true;
	       }
	  }
	  @Override
	  public void endElement(String namespaceURI, String localName, String qName)
	           throws SAXException {
	    if (localName.toLowerCase().equals("check")) {
		      this.h_scuess = false;
	    }
	  }
	  

	  @Override
	 public void characters(char ch[], int start, int length) {
	    if(this.h_scuess){
	       myParsedExampleDataSet.setscuess(Integer.parseInt(new String(ch,start,length)));
	    }
	 }

}
