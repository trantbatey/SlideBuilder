package com.codeup;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileReader;
import java.io.IOException;

/**
 * This application will build a JavaScript Table of Contents for html files based on an xml input file.
 */
public class TOCBuilderApplication {

    public static void main(String[] args) {
        XMLHandler XMLHandler = new TOCHandler();
        XMLHandler.parseXMLFile(XMLHandler, "data/ConsoleTOC.xml");
    }
}
