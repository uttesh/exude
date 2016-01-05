package com.uttesh.exude.stopping;

import com.uttesh.exude.common.Constants;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;

/**
 *
 * @author Uttesh Kumar T.H.
 */
public class TrushDuplicates {

    Set<String> filteredSet = new HashSet<String>();

    public static TrushDuplicates instance = null;

    public static TrushDuplicates getInstance() {
        if (instance == null) {
            instance = new TrushDuplicates();
        }
        return instance;
    }

    public Set<String> filter(String file) throws IOException {
        try {
            List<String> strings = Files.readAllLines((new File(file)).toPath());
            filteredSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
            filteredSet.addAll(strings);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return filteredSet;
    }

    public Set<String> filterData(String data) throws IOException {
        try {
            String[] dataarr = data.split(Constants.SPACE);
            filteredSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
            filteredSet.addAll(Arrays.asList(dataarr));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filteredSet;
    }

    public void filterDuplicates(String inputFilePath, String outFilePath) throws IOException {
        BodyContentHandler contentHandler = getData(inputFilePath);
        Set<String> lines = new LinkedHashSet<String>();
        if (contentHandler != null) {
            String line = contentHandler.toString();
            String delims = " ";
            line = line.trim();
            StringTokenizer str = new StringTokenizer(line, delims);
            while (str.hasMoreElements()) {
                line = (String) str.nextElement();
                if (line.trim().length() > 0) {
                    lines.add(line.replace("\"", ""));
                }
            }
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(outFilePath), Constants.UTF_8));
                for (String unique : lines) {
                    writer.write(unique + " ");
                }
                writer.close();
        }
    }

    public String filterDuplicates(String path) throws IOException {
        BodyContentHandler contentHandler = getData(path);
        Set<String> lines = new LinkedHashSet<String>();
        StringBuilder stringBuilder = new StringBuilder();
        if (contentHandler != null) {
            String line = contentHandler.toString();
            String delims = " ";
            line = line.trim();
            StringTokenizer str = new StringTokenizer(line, delims);
            while (str.hasMoreElements()) {
                line = (String) str.nextElement();
                if (line.trim().length() > 0) {
                    lines.add(line.replace("\"", ""));
                }
            }
            for (String unique : lines) {
                stringBuilder.append(unique + " ");
            }
            return stringBuilder.toString();
        }
        return "";
    }

    public String filterDuplicatesInText(String inputData) throws IOException {
        Set<String> filterwords = new LinkedHashSet<String>();
        StringBuilder stringBuilder = new StringBuilder();
        if (inputData != null && inputData.trim().length() > 0) {
            String[] words = inputData.split("\\ ");
            if (words != null && words.length > 0) {
                for (int i = 0; i < words.length; i++) {
                    String word = (String) words[i];
                    if (word.trim().length() > 0) {
                        filterwords.add(word.replace("\"", ""));
                    }
                }
                for (String unique : filterwords) {
                    stringBuilder.append(unique + " ");
                }
                return stringBuilder.toString();
            } else {
                return inputData;
            }
        }
        return null;
    }

    public static BodyContentHandler getData(String data) {
        try {
            Parser parser = new AutoDetectParser();
            BodyContentHandler handler = new BodyContentHandler();
            Metadata metadata = new Metadata();
            FileInputStream inputstream = new FileInputStream(new File(data));
            ParseContext context = new ParseContext();
            parser.parse(inputstream, handler, metadata, context);
            return handler;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
