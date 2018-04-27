package com.gmail.gm.jcant.javaPro;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "query")
public class Query {

    private int count;
    private String created;
    private String lang;
    private Results results;

    public Query() {
        super();
    }

    public int getCount() {
        return count;
    }

    @XmlAttribute(namespace = "http://www.yahooapis.com/v1/base.rng")
    public void setCount(int count) {
        this.count = count;
    }

    public String getCreated() {
        return created;
    }

    @XmlAttribute(namespace = "http://www.yahooapis.com/v1/base.rng")
    public void setCreated(String created) {
        this.created = created;
    }

    public String getLang() {
        return lang;
    }

    @XmlAttribute(namespace = "http://www.yahooapis.com/v1/base.rng")
    public void setLang(String lang) {
        this.lang = lang;
    }

    public Results getResults() {
        return results;
    }

    @XmlElement
    public void setResults(Results results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Query{" +
                "count=" + count +
                ", created='" + created + '\'' +
                ", lang='" + lang + '\'' +
                ", results=" + results +
                '}';
    }
}
