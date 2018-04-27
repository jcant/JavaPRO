package com.gmail.gm.jcant.javaPro;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        TrainsXMLList tlist = new TrainsXMLList();
        tlist.loadFromXML("./trains.xml");
        
        System.out.println(tlist);
        
        tlist.saveToXML("./trains_gen.xml");
    }
}
