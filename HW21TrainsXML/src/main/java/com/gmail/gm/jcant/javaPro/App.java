package com.gmail.gm.jcant.javaPro;

import com.gmail.gm.jcant.JDate;

import java.util.Arrays;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        JDate.setDefaultDateFormat("dd.MM.yyyy");
        JDate.setDefaultTimeFormat("HH:mm");

        TrainsXMLList tList = new TrainsXMLList();
        tList.loadFromXML("./trains.xml");

        tList.addTrain(new Train(3,"Dnepr", "Kiev", JDate.getDate("28.04.2018", "14:30")));
        tList.addTrain(new Train(4,"Kiev", "Dnepr", JDate.getDate("29.04.2018", "18:30")));
        tList.addTrain(new Train(5,"Odessa", "Kiev", JDate.getDate("28.04.2018", "9:45")));
        tList.addTrain(new Train(6,"Kiev", "Odessa", JDate.getDate("29.04.2018", "19:15")));
        tList.addTrain(new Train(7,"Lviv", "Odessa", JDate.getDate("28.04.2018", "4:10")));
        tList.addTrain(new Train(8,"Odessa", "Lviv", JDate.getDate("29.04.2018", "15:00")));

        System.out.println(tList);

        tList.saveToXML("./trains_gen.xml");

        Date start = JDate.getDate("27.04.2018","19:00");
        Date finish = JDate.getDate("28.04.2018","5:00");

        System.out.println(tList.getTrainsBeetwen(start,finish));
    }
}
