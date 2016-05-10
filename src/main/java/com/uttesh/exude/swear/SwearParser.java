/*
 Copyright 2015 Uttesh Kumar T.H.

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
package com.uttesh.exude.swear;

import com.uttesh.exude.common.Constants;
import com.uttesh.exude.handler.ScrawlWords;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Uttesh Kumar T.H.
 */
public class SwearParser {

    static String swearBySpace = null;
    private static SwearParser instance = null;
    private static Set<String> swearWords = new LinkedHashSet<String>();
    ScrawlWords scrawlWords = ScrawlWords.getInstance();

    protected SwearParser() {};
    public static SwearParser getInstance() {
        if (instance == null) {
            instance = new SwearParser();
        }
        return instance;
    }

    public String filterSwearWords(String data) {
        String[] words = data.split(Constants.SPACE);
        StringBuilder filteredWords = new StringBuilder();
        try {
            if (words.length > 0) {
                int prevSwearIndex = 0;
                for (int i = 0; i < words.length; i++) {
                    String word = words[i];
                    ValidateSwearBySpace(word.trim(), i, prevSwearIndex);
                    scrawlWords.filterSwearWord(word.trim(), filteredWords);
                }
            } else {
                scrawlWords.filterSwearWord(data.trim(), filteredWords);
            }

            if (swearWords.size() > 0) {
                for (String _data : swearWords) {
                    scrawlWords.filterSwearWord(_data.trim(), filteredWords);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filteredWords.toString();
    }

    public String getSwearWords(String data) {
        String[] words = data.split(Constants.SPACE);
        StringBuilder filteredWords = new StringBuilder();
        try {
            if (words.length > 0) {
                int prevSwearIndex = 0;
                for (int i = 0; i < words.length; i++) {
                    String word = words[i];
                    ValidateSwearBySpace(word, i, prevSwearIndex);
                    String result = scrawlWords.getSwearWord(word.trim());
                    if (result.trim().length() > 0) {
                        filteredWords.append(result + " ");
                    }
                }
            } else if (data.trim().length() > 11) {
                String result = scrawlWords.getSwearWord(data.trim());
                if (result.trim().length() > 1) {
                    filteredWords.append(result + " ");
                }
            }

            if (swearWords.size() > 0) {
                for (String _data : swearWords) {
                    if (_data.trim().length() > 1) {
                        filteredWords.append(_data + " ");
                    }
                }
            }
            return filteredWords.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    Pattern Spacepattern = Pattern.compile("\\s");

    public void ValidateSwearBySpace(String word, int currentIndex, int prevSwearIndex) {
        try {
            Matcher matcher = Spacepattern.matcher(word);
            boolean spacefound = matcher.find();
            if (word.length() <= 2) {
                if (prevSwearIndex == 0) {
                    prevSwearIndex = currentIndex;
                }
                prevSwearIndex++;
                if (prevSwearIndex - currentIndex == 1 && !spacefound) {
                    if (swearBySpace == null) {
                        swearBySpace = word.trim();
                    } else {
                        swearBySpace = swearBySpace + word.trim();
                    }
                }
            } else if (swearBySpace != null) {
                prevSwearIndex = 0;
                String result = scrawlWords.getSwearWord(swearBySpace.trim());
                if (result != null && result.trim().length() > 0) {
                    swearWords.add(swearBySpace);
                }
                swearBySpace = null;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    public void resetSwearWords(){
        swearWords = new LinkedHashSet<String>();
    }
}
