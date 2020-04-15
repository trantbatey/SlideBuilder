package com.codeup;

public class SlideBuilderApplication {

    public static void main(String[] args) {
        XMLHandler XMLHandler = new XMLHandler();
        XMLHandler.parseXMLFile(XMLHandler, "data/MySQLIntro.xml");
    }
}
