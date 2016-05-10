/*
 Copyright 2016-2022 Uttesh Kumar T.H.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package com.uttesh.exude.stopping;

import com.uttesh.exude.common.Constants;
import com.uttesh.exude.handler.ScrawlWords;
import com.uttesh.exude.stemming.Stemmer;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Uttesh Kumar T.H.
 */
public class StoppingParser {

    public static StoppingParser instance = null;
    ScrawlWords scrawlWords = ScrawlWords.getInstance();
    public static Set<String> finalFilteredSet = new LinkedHashSet<String>();
    public static List<String> finalFilterListWithDuplicates = new ArrayList<String>();
    Stemmer stemmer = new Stemmer();

    protected StoppingParser() {};

    public static StoppingParser getInstance() {
        if (instance == null) {
            instance = new StoppingParser();
        }
        return instance;
    }

    public void finalData(StringBuilder finalFilteredData, String outputFile) {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), Constants.UTF_8));
            writer.write(finalFilteredData + " ");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String[] getWords(String data) {
        String[] words = data.split(Constants.SPACE);
        if (words == null || words.length == 1) {
            words = data.split(Constants.COMMA);
        }
        return words;
    }
    
    private String filterStoppings(String data){
        String[] words = getWords(data);
        StringBuilder filteredWords = new StringBuilder();
        try {
            if (words.length > 0) {
                for (String word : words) {
                    word = scrawlWords.getOnlyStrings(word);
                    scrawlWords.filterStopingWord(word.trim(), filteredWords);
                }
            } else {
                data = scrawlWords.getOnlyStrings(data);
                scrawlWords.filterStopingWord(data.trim(), filteredWords);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filteredWords.toString();
    }

    public String filterStoppingWordsKeepDuplicates(String data) {
        String filteredWords = filterStoppings(data);
        if(!filteredWords.isEmpty()){
            finalFilterListWithDuplicates.add(filteredWords.toLowerCase());
        }
        return filteredWords;
    }

    public String filterStoppingWords(String data) {
        String filteredWords = filterStoppings(data);
        finalFilteredSet.add(filteredWords.toLowerCase());
        return filteredWords;
    }

    public Set<String> getResultSet() {
        return finalFilteredSet;
    }

    public void reset() {
        finalFilteredSet = new LinkedHashSet<String>();
        finalFilterListWithDuplicates = new ArrayList<String>();
    }

    public static List<String> getFinalFilterListWithDuplicates() {
        return finalFilterListWithDuplicates;
    }

}
