package com.gmail.gm.jcant.javaPro;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.MalformedURLException;
import java.net.URL;

public class YahooLoader {

    public static Query getRequestResult(String urlString) {
        Query result = null;

        try {
            URL url = new URL(urlString);
            JAXBContext jaxbContext = JAXBContext.newInstance(Query.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            result = (Query) unmarshaller.unmarshal(url);
        } catch (JAXBException | MalformedURLException e) {
            e.printStackTrace();
        }

        return result;

    }

}

