package com.codeup;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.*;
import java.util.*;

import static java.lang.System.out;

public class XMLHandler {

    protected String indention = "";
    protected StringBuilder output;
    protected static PrintWriter pw;
    boolean textField = false;
    int bulletCount;

    final static List<String> EMPTY_BODY_ELEMENTS = List.of("br", "img", "span");

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
        Map<String, String> attributes = convertAttributesToMap(startElement);
        String qName = startElement.getName().getLocalPart();
        if (qName.equalsIgnoreCase("file")) {
            String name = attributes.remove("name");
            String title = attributes.remove("title");
            String toc = attributes.remove("toc");
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
            String indent = attributes.remove("indent");
            bulletCount++;
            output = new StringBuilder(String.format("" +
                    indention + "<table class=\"slide_table\">\n" +
                    indention + "    <tr>\n" +
                    indention + "        <td class=\"bullet-star " + indent + "\">\n" +
                    indention + "            <button type=\"button\" class=\"btn btn-info\"\n" +
                    indention + "                        onclick=\"showText%02d()\">\n" +
                    indention + "                <span class=\"glyphicon glyphicon-next\"></span>\n" +
                    indention + "                <strong class=\"star\">*</strong>\n" +
                    indention + "            </button>\n" +
                    indention + "        </td>\n" +
                    indention + "        <td class=\"bullet-subpoint\"" + getPassThroughAttributes(attributes) + ">\n" +
                    indention + "            <table id=\"text%02d\">\n" +
                    indention + "                <tr>\n" +
                    indention + "                    <td>\n",
                    bulletCount, bulletCount));
            indention += "                        ";
            write(output);
        } else if (qName.equalsIgnoreCase("text")) {
            textField = true;
            output = new StringBuilder(
                    indention + "<table>\n" +
                    indention + "    <tr>\n" +
                    indention + "        <td>\n" +
                    indention + "            <span class=\"horizontal-scroll\">\n");
            indention += "                ";
            write(output);
        } else if (EMPTY_BODY_ELEMENTS.contains(qName.toLowerCase())) {
            openInlineElement(qName.toLowerCase(), attributes);
        } else {
            openSimpleElement(qName, attributes);
        }
    }

    public void endElement(EndElement endElement) {
        if (endElement.getName().getLocalPart().equalsIgnoreCase("bullet")) {
            output = new StringBuilder();
            indention = indention.substring(0, indention.length() - 4);
            output.append(indention).append("</td>\n");
            indention = indention.substring(0, indention.length() - 4);
            output.append(indention).append("</tr>\n");
            indention = indention.substring(0, indention.length() - 4);
            output.append(indention).append("</table>\n");
            indention = indention.substring(0, indention.length() - 4);
            output.append(indention).append("</td>\n");
            indention = indention.substring(0, indention.length() - 4);
            output.append(indention).append("</tr>\n");
            indention = indention.substring(0, indention.length() - 4);
            output.append(indention).append("</table>\n");
            write(output);
        } else if (endElement.getName().getLocalPart().equalsIgnoreCase("text")) {
            textField = false;
            output = new StringBuilder();
            indention = indention.substring(0, indention.length() - 4);
            output.append(indention).append("</span>\n");
            indention = indention.substring(0, indention.length() - 4);
            output.append(indention).append("</td>\n");
            indention = indention.substring(0, indention.length() - 4);
            output.append(indention).append("</tr>\n");
            indention = indention.substring(0, indention.length() - 4);
            output.append(indention).append("</table>\n");
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
        } else if (EMPTY_BODY_ELEMENTS.contains(endElement.getName().getLocalPart().toLowerCase())) {
            closeInlineElement(endElement.getName().getLocalPart().toLowerCase());
        } else {
            closeSimpleElement(endElement.getName().getLocalPart());
        }
    }

    public void characters(Characters characters) {
        if (characters == null) return;
        String chars = characters.getData();
        if (textField) {
            chars = chars.trim().replaceAll("\n+", "<br/>") + "<br>\n";
        } else {
            chars = chars.trim().replaceAll("\\s+", " ") + "\n";
        }
        if (chars.length() != 0 && !chars.equals("\n")) write(indention + chars);
    }

    protected void openSimpleElement(String element, Map<String, String> attributes) {
        String elementAndAttributes = "<" + element + getPassThroughAttributes(attributes) + ">\n";
        output = new StringBuilder(indention + elementAndAttributes);
        indention += "    ";
        write(output);
    }

    protected void closeSimpleElement(String element) {
        indention = indention.substring(0, indention.length() - 4);
        String output = indention + "</" + element + ">\n";
        write(output);
    }

    protected void openInlineElement(String element, Map<String, String> attributes) {
        String elementAndAttributes;
        if (element.equals("br") || element.equals("img")) {
            elementAndAttributes = indention + "<" + element + getPassThroughAttributes(attributes) + "/>\n";
        } else {
            elementAndAttributes = indention + "<" + element + getPassThroughAttributes(attributes) + ">\n";
        }
        output = new StringBuilder(elementAndAttributes);
        write(output);
    }

    protected void closeInlineElement(String element) {
        String output = "";
        if (!(element.equals("br") || element.equals("img"))) {
            output = "</" + element + ">";
        }
        write(output);
    }

    protected Map<String, String> convertAttributesToMap(StartElement startElement) {
        Map<String, String> attributes = new HashMap<>();
        Iterator<Attribute> iterator = startElement.getAttributes();
        while (iterator.hasNext()) {
            Attribute entry = iterator.next();
            attributes.put(entry.getName().getLocalPart(), entry.getValue());
        }
        return attributes;
    }

    protected String getPassThroughAttributes(Map<String, String> attributes) {
        StringBuilder attributeString = new StringBuilder();
        for (String key: attributes.keySet()) {
            attributeString.append(" ").append(key).append("=\"").append(attributes.get(key)).append("\"");
        }
        return attributeString.toString();
    }

    protected static void write(String output) {
        pw.print(output);
        out.print(output);
    }

    protected static void write(StringBuilder output) {
        write(output.toString());
    }
}