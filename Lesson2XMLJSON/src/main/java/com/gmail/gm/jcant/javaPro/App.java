package com.gmail.gm.jcant.javaPro;

import com.gmail.gm.jcant.javaPro.JAXB.JAXBTest;
import com.gmail.gm.jcant.javaPro.JSON.GSONTest;
import com.gmail.gm.jcant.javaPro.JSON.GSONTest2;
import com.gmail.gm.jcant.javaPro.XMLParser1.SimpleXMLParser;
import com.gmail.gm.jcant.javaPro.XMLParser2.XMLParser2;

public class App 
{
    public static void main( String[] args )
    {
    	System.out.println("*** Simple XML parsing: (XMLParser1) ***");
    	
    	SimpleXMLParser xml = new SimpleXMLParser("./1.xml");
        String value = xml.get("catalog/book/author");
        System.out.println(value);
        System.out.println();
        
        System.out.println("*** DOM XML parsing: (XMLParser2) ***");
        XMLParser2.parse();
        System.out.println();
        
        System.out.println("*** JAXB XML parsing: ***");
        JAXBTest.test();
        System.out.println();
        
        System.out.println("*** JSON parsing (GSON): ***");
        try {
			//GSONTest.test();
			GSONTest2.test();
		} catch (Exception e) {
			e.printStackTrace();
		}
        System.out.println();
    }
}
