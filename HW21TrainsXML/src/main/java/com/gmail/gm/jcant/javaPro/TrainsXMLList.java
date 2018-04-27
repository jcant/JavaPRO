package com.gmail.gm.jcant.javaPro;

import java.io.File;
import java.util.ArrayList;
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

	public void loadFromXML(String fname) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(TrainsXMLList.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			trains = ((TrainsXMLList) unmarshaller.unmarshal(new File(fname))).trains;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public void saveToXML(String fname) {
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(TrainsXMLList.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(this, new File(fname));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "TrainsXMLList [trains=" + trains + "]";
	}

}
