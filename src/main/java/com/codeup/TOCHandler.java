package com.codeup;

import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;

public class TOCHandler extends XMLHandler {

    private String name;
    private String navName = "";
    private String includePath = "";
    private boolean includeMode = false;

    public String getIncludePath() {
        return includePath;
    }

    public void setIncludePath(String includePath) {
        this.includePath = includePath;
    }

    public boolean isIncludeMode() {
        return includeMode;
    }

    public void setIncludeMode(boolean includeMode) {
        this.includeMode = includeMode;
    }

    @Override
    public void startElement(StartElement startElement) throws IOException {
        Map<String, String> attributes = convertAttributesToMap(startElement);
        String qName = startElement.getName().getLocalPart();
        if (qName.equalsIgnoreCase("file")) {
            if (includeMode) return;
            String name = attributes.remove("name");
            try {
                pw = new PrintWriter(new FileOutputStream("output/" + name + ".js"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String title = attributes.remove("title");
            String home = attributes.remove("title");
            output = new StringBuilder("document.write(`\n");
            output.append("    <div class=\"sidenav\">\n");
            output.append("        <label><strong>Table of contents</strong></label>\n");
            output.append("        <ul class=\"list-unstyled\">\n");
            indention = "            ";
            write(output);
        } else if (qName.equalsIgnoreCase("li")) {
            name = includePath + attributes.remove("name");
            String title = attributes.remove("title");
            String url = "";
            if (navName.length() != 0) {
                if (navName.contains("#")) {
                    int indexOf = navName.indexOf("#");
                    url = navName.substring(0, indexOf) + ".html#" + name + "\"";
                } else {
                    url = navName + ".html#" + name + "\"";
                }
            } else {
                if (name.contains("#")) {
                    int indexOf = name.indexOf("#");
                    url = name.substring(0, indexOf) + ".html" + name.substring(indexOf) + "\"";
                    System.out.println(url);
                } else {
                    url = name + ".html\"";
                }
            }
            output = new StringBuilder(indention + "<li>\n");
            output.append(indention + "    <a href=\"./" + url + " title=\"" + title + "\">\n");
            output.append(indention + "        " + title + "\n");
            output.append(indention + "    </a>\n");
            indention += "    ";
            write(output);
        } else if (qName.equalsIgnoreCase("ali")) {
            String url = attributes.remove("url");
            String label = attributes.remove("label");
            output = new StringBuilder(indention + "<li>\n");
            output.append(indention + "    <a href=\"" + url + "\" target=\"_blank\">" + label + "</a>\n");
            output.append(indention + "</li>\n");
            indention += "    ";
            write(output);
        } else if (qName.equalsIgnoreCase("nav")) {
            output = new StringBuilder(indention + "<nav>\n");
            output.append(indention + "    <ul>\n");
            navName = name;
            indention += "        ";
            write(output);
        } else if (qName.equalsIgnoreCase("include")) {
            write(indention + "<hr>");
            String includeFilename = attributes.remove("filename");
            String includePath = attributes.remove("path");
            XMLHandler XMLHandler = new TOCHandler();
            ((TOCHandler) XMLHandler).setIncludePath(includePath);
            ((TOCHandler) XMLHandler).setIncludeMode(true);
            XMLHandler.indention = indention;
            XMLHandler.parseXMLFile(XMLHandler, "data/02_HTMLandCSS/" + includeFilename);
        } else {
            openSimpleElement(qName, attributes);
        }
    }

    @Override
    public void endElement(EndElement endElement) {
        if (endElement.getName().getLocalPart().equalsIgnoreCase("li")) {
            indention = indention.substring(0, indention.length() - 4);
            String output = indention + "</li>\n";
            write(output);
        } else if (endElement.getName().getLocalPart().equalsIgnoreCase("nav")) {
            indention = indention.substring(0, indention.length() - 4);
            String output = indention + "</ul>\n";
            write(output);
            indention = indention.substring(0, indention.length() - 4);
            output = indention + "</nav>\n";
            navName = "";
            write(output);
        } else if (endElement.getName().getLocalPart().equalsIgnoreCase("ul")) {
            write(output);
        } else if (endElement.getName().getLocalPart().equalsIgnoreCase("include")) {
            // nothing to do
        } else if (endElement.getName().getLocalPart().equalsIgnoreCase("file")) {
            if (!includeMode) {
                output = new StringBuilder("        </ul>\n");
                output.append("    </div>\n");
                output.append("`);\n");
                write(output);
                pw.close();
            }
        } else {
            closeSimpleElement(endElement.getName().getLocalPart());
        }
    }
}