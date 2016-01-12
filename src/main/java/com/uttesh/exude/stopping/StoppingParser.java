package com.uttesh.exude.stopping;

import com.uttesh.exude.common.Constants;
import com.uttesh.exude.handler.ScrawlWords;
import com.uttesh.exude.stemming.Stemmer;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author Uttesh Kumar T.H.
 */
public class StoppingParser {
    
    public static StoppingParser instance = null;
    ScrawlWords scrawlWords = ScrawlWords.getInstance();
    public static Set<String> finalFilteredSet = new LinkedHashSet<String>();
    Stemmer stemmer = new Stemmer();
    
    protected StoppingParser(){};

    public static StoppingParser getInstance() {
        if (instance == null) {
            instance = new StoppingParser();
        }
        return instance;
    }
    
    public void finalData(StringBuilder finalFilteredData,String outputFile) {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), Constants.UTF_8));
            writer.write(finalFilteredData + " ");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private String[] getWords(String data){
        String[] words = data.split(Constants.SPACE);
        if(words == null || words.length ==1){
            words = data.split(Constants.COMMA);
        }
        return words;
    }

    public String filterStoppingWords(String data) {
        String[] words = getWords(data);
        StringBuilder filteredWords = new StringBuilder();
        try {
            if (words.length > 0) {
                for (String word : words) {
                    //word = stemmer.stem(word.trim());
                    word = scrawlWords.getOnlyStrings(word);
                    scrawlWords.filterStopingWord(word.trim(), filteredWords);
                }
            } else {
                //data = stemmer.stem(data.trim());
                data = scrawlWords.getOnlyStrings(data);
                scrawlWords.filterStopingWord(data.trim(), filteredWords);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finalFilteredSet.add(filteredWords.toString().toLowerCase());
        return filteredWords.toString();
    }

    public Set<String> getResultSet(){
        return finalFilteredSet;
    }

}
