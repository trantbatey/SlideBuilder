package com.codeup;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.*;
import java.util.Iterator;
import static java.lang.System.out;

public class XMLHandler {

    protected String src;
    protected String indention = "";
    protected StringBuilder output;
    protected static PrintWriter pw;
    int bulletCount;

    public static void parseXMLFile(XMLHandler XMLHandler, String inputFilename) {

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader =
                    factory.createXMLEventReader(new FileReader(inputFilename));

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                switch (event.getEventType()) {

                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        XMLHandler.startElement(startElement);
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        XMLHandler.characters(characters);
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();
                        XMLHandler.endElement(endElement);
                        break;
                }
            }
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }
    }

    public void startElement(StartElement startElement) throws IOException {
        String qName = startElement.getName().getLocalPart();
        if (qName.equalsIgnoreCase("file")) {
            String name = getAttribute("name", startElement);
            String title = getAttribute("title", startElement);
            String toc = getAttribute("toc", startElement);
            try {
                pw = new PrintWriter(new FileOutputStream("output/" + name + ".html"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            output = new StringBuilder(
                    String.format("<!DOCTYPE html>\n" +
                            "<html lang=\"en\">\n" +
                            "<head>\n" +
                            "    <meta charset=\"UTF-8\">\n" +
                            "    <title>%s</title>\n" +
                            "    <link rel=\"stylesheet\" href=\"resource/css/bootstrap.min.css\">\n" +
                            "    <link rel=\"stylesheet\" href=\"resource/css/general.css\">\n" +
                            "</head>\n" +
                            "<body>\n" +
                            "\n" +
                            "    <script src=\"%s.js\"></script>\n" +
                            "    <div class=\"container main\">\n" +
                            "        <h1 class=\"mainTitle\">%s</h1>\n", title, toc, title));
            indention = "        ";
            write(output);
        } else if (qName.equalsIgnoreCase("bullet")) {
            bulletCount++;
            output = new StringBuilder(String.format("" +
                    indention + "<table cellpadding=\"10\" class=\"slide_table\">\n" +
                    indention + "    <tr>\n" +
                    indention + "        <td>\n" +
                    indention + "            <button type=\"button\" class=\"btn btn-info\"\n" +
                    indention + "                        onclick=\"showText%02d()\">\n" +
                    indention + "                <span class=\"glyphicon glyphicon-next\"></span>\n" +
                    indention + "                <h3 style=\"display: inline;\">*</h3>\n" +
                    indention + "            </button>\n" +
                    indention + "        </td>\n" +
                    indention + "        <td class=\"bullet-subpoint\"" + getPassThroughAttributes(startElement) + ">\n" +
                    indention + "            <span id=\"text%02d\">\n" +
                    indention + "                <table cellpadding=\"4\">\n", bulletCount, bulletCount));
            indention += "                    ";
            write(output);
        } else if (qName.equalsIgnoreCase("text")) {
            output = new StringBuilder(String.format("" +
                    indention + "<table>\n" +
                    indention + "    <tr>\n" +
                    indention + "        <td>\n" +
                    indention + "            <span class=\"horizontal-scroll\">\n"));
            indention += "                ";
            write(output);
        } else if (qName.equalsIgnoreCase("td")) {
            writeSimpleElement("td", startElement);
        } else if (qName.equalsIgnoreCase("img-s")) {
            src = getAttribute("src", startElement);
            output = new StringBuilder(indention + "<img class=\"sm-img\" src=\"" + src + "\">\n");
            write(output);
        } else if (qName.equalsIgnoreCase("img-m")) {
            src = getAttribute("src", startElement);
            output = new StringBuilder(indention + "<img class=\"md-img\" src=\"" + src + "\">\n");
            write(output);
        } else if (qName.equalsIgnoreCase("h1")) {
            writeSimpleElement("h1", startElement);
        } else if (qName.equalsIgnoreCase("h2")) {
            writeSimpleElement("h2", startElement);
        } else if (qName.equalsIgnoreCase("h3")) {
            writeSimpleElement("h3", startElement);
        } else if (qName.equalsIgnoreCase("strong")) {
            writeSimpleElement("strong", startElement);
        } else if (qName.equalsIgnoreCase("span")) {
            writeSimpleElement("span", startElement);
        } else if (qName.equalsIgnoreCase("em")) {
            writeSimpleElement("em", startElement);
        } else if (qName.equalsIgnoreCase("br")) {
            output = new StringBuilder(indention + "<br>\n");
            write(output);
        }
    }

    public void endElement(EndElement endElement) {
        if (endElement.getName().getLocalPart().equalsIgnoreCase("bullet")) {
            output = new StringBuilder();
            indention = indention.substring(0, indention.length() - 4);
            output.append(indention + "</table>\n");
            indention = indention.substring(0, indention.length() - 4);
            output.append(indention + "</span>\n");
            indention = indention.substring(0, indention.length() - 4);
            output.append(indention + "</td>\n");
            indention = indention.substring(0, indention.length() - 4);
            output.append(indention + "</tr>\n");
            indention = indention.substring(0, indention.length() - 4);
            output.append(indention + "</table>\n");
            write(output);
        } else if (endElement.getName().getLocalPart().equalsIgnoreCase("text")) {
            output = new StringBuilder();
            indention = indention.substring(0, indention.length() - 4);
            output.append(indention + "</span>\n");
            indention = indention.substring(0, indention.length() - 4);
            output.append(indention + "</td>\n");
            indention = indention.substring(0, indention.length() - 4);
            output.append(indention + "</tr>\n");
            indention = indention.substring(0, indention.length() - 4);
            output.append(indention + "</table>\n");
            write(output);
        } else if (endElement.getName().getLocalPart().equalsIgnoreCase("td")) {
            indention = indention.substring(0, indention.length() - 4);
            String output = indention + "</td>\n";
            write(output);
        } else if (endElement.getName().getLocalPart().equalsIgnoreCase("h1")) {
            indention = indention.substring(0, indention.length() - 4);
            String output = indention + "</h1>\n";
            write(output);
        } else if (endElement.getName().getLocalPart().equalsIgnoreCase("h2")) {
            indention = indention.substring(0, indention.length() - 4);
            String output = indention + "</h2>\n";
            write(output);
        } else if (endElement.getName().getLocalPart().equalsIgnoreCase("h3")) {
            indention = indention.substring(0, indention.length() - 4);
            String output = indention + "</h3>\n";
            write(output);
        } else if (endElement.getName().getLocalPart().equalsIgnoreCase("span")) {
            indention = indention.substring(0, indention.length() - 4);
            String output = indention + "</span>\n";
            write(output);
        } else if (endElement.getName().getLocalPart().equalsIgnoreCase("em")) {
            indention = indention.substring(0, indention.length() - 4);
            String output = indention + "</em>\n";
            write(output);
        } else if (endElement.getName().getLocalPart().equalsIgnoreCase("strong")) {
            indention = indention.substring(0, indention.length() - 4);
            String output = indention + "</strong>\n";
            write(output);
        } else if (endElement.getName().getLocalPart().equalsIgnoreCase("file")) {
            output = new StringBuilder("" +
                    "    </div>\n" +
                    "\n" + "</body>\n" +
                    "    <script src=\"resource/js/General.js\"></script>\n" +
                    "    <script src=\"resource/js/bootstrap.min.js\"></script>\n" +
                    "</html>");
            write(output);
            pw.close();
        }
    }

    public void characters(Characters characters) {
        if (characters == null) return;
        String chars = characters.getData().trim().replaceAll("\\s+", " ");
        if (chars.length() != 0) write(indention + chars + "\n");
    }

    protected String getAttribute(String attributeName, StartElement startElement) {
        String attributeValue = "";
        Iterator<Attribute> attributes = startElement.getAttributes();
        while (attributes.hasNext()) {
            Attribute attribute = attributes.next();
            if (attribute.getName().getLocalPart().equals(attributeName)) {
                attributeValue = attribute.getValue();
                break;
            }
        }
        return attributeValue;
    }

    protected void writeSimpleElement(String type, StartElement startElement) {
        String element = "<" + type + getPassThroughAttributes(startElement) + ">\n";
        output = new StringBuilder(indention + element);
        indention += "    ";
        write(output);
    }

    protected String getPassThroughAttributes(StartElement startElement) {
        String attribitueString = "";
        Iterator<Attribute> attributes = startElement.getAttributes();
        while (attributes.hasNext()) {
            Attribute attribute = attributes.next();
            attribitueString += " " + attribute.getName() + "=\"" + attribute.getValue() + "\"";
        }
        return attribitueString;
    }

    protected static void write(String output) {
        pw.print(output);
        out.print(output);
    }

    protected static void write(StringBuilder output) {
        write(output.toString());
    }
}