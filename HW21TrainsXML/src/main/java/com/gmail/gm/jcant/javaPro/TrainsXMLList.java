package com.gmail.gm.jcant.javaPro;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "trains")
public class TrainsXMLList {

    @XmlElement(name = "train")
    private List<Train> trains = new ArrayList<>();

    public TrainsXMLList() {
        super();
    }

    public void addTrain(Train train) {
        if (train == null) {
            throw new IllegalArgumentException("Can`t add empty object");
        }
        trains.add(train);
    }

    public void loadFromXML(String fileName) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(TrainsXMLList.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            trains = ((TrainsXMLList) unmarshaller.unmarshal(new File(fileName))).trains;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void saveToXML(String fileName) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(TrainsXMLList.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(this, new File(fileName));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public List<Train> getTrainsBeetwen(Date start, Date finish) {
        List<Train> result = new ArrayList<>();

        for (Train train : trains) {
            if (train.getDateTime() != null) {
                if ((train.getDateTime().after(start)) && (train.getDateTime().before(finish))) {
                    result.add(train);
                }
            }
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TrainsXMLList:").append(System.lineSeparator());
        for (Train train : trains) {
            sb.append("\t" + train).append(System.lineSeparator());
        }

        return sb.toString();
    }

}
