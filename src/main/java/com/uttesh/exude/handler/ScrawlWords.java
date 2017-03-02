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
package com.uttesh.exude.handler;

import com.uttesh.exude.common.BaseResource;
import com.uttesh.exude.common.Constants;
import com.uttesh.exude.stopping.StoppingResource;
import com.uttesh.exude.swear.SwearResource;
import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Uttesh Kumar T.H.
 */
public class ScrawlWords {

    private static final String SPLIT_ON_COMMA_AND_REMOVE_SPACE = "\\s*,\\s*";

    public static ScrawlWords instance = null;

    static Pattern pattern = Pattern.compile(Constants.WORD_PATTERN);

    protected ScrawlWords() {
    }

    ;

    public static ScrawlWords getInstance() {
        if (instance == null) {
            instance = new ScrawlWords();
        }
        return instance;
    }

    public static String getOnlyStrings(String s) {
        Matcher matcher = pattern.matcher(s);
        String text = matcher.replaceAll(" ");
        return text;
    }

    public void filterStopingWord(String word, StringBuilder filteredWords) {
        try {
            BaseResource baseResource = new StoppingResource();
            if (word.length() > 1) {
                if (isValidWord(word, baseResource)) {
                    filteredWords.append(word.trim() + " ");
                }
            } else if (word.length() > 1) {
                filteredWords.append(word.trim() + " ");
            }
        } catch (Exception e) {

        }
    }

    public void filterSwearWord(String word, StringBuilder filteredWords) {
        try {
            BaseResource baseResource = new SwearResource();
            if (word.length() > 1 && word.length() < 6) {
                if (isValidWord(word, baseResource)) {
                    filteredWords.append(word + " ");
                }
            } else {
                filteredWords.append(word + " ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getSwearWord(String word) {
        StringBuilder swearWords = new StringBuilder();
        try {
            BaseResource baseResource = new SwearResource();
            if (!isValidWord(word, baseResource)) {
                if (word.trim().length() > 1) {
                    swearWords.append(word + " ");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return swearWords.toString();
    }

    private boolean isValidWord(String word, BaseResource baseResource) {
        try {
            word = word.toUpperCase();

            if (word != null && word.trim().length() > 1) {
                char firstCharater = word.charAt(0);
                char secondCharater = word.charAt(1);
                if (baseResource instanceof StoppingResource) {
                    if (Character.isLetter((char) firstCharater)
                            && Character.isLetter((char) secondCharater)) {
                        String searchProp = String.valueOf(word.charAt(0)) + String.valueOf(word.charAt(1));
                        String words = baseResource.getProperties(searchProp);
                        if (words != null && words.contains(word.toLowerCase())) {
                            return false;
                        }
                    }
                } else if (baseResource instanceof SwearResource) {
                    if (Character.isLetter((char) firstCharater)) {
                        String searchProp = String.valueOf(word.charAt(0));
                        String words = baseResource.getProperties(searchProp);

                        if (words != null && words.contains(word.toLowerCase())) {
                            final String[] swearWords = words.trim().split(SPLIT_ON_COMMA_AND_REMOVE_SPACE);
                            final String wordToCheck = word.toLowerCase();

                            for (final String swearWord : swearWords) {
                                if (StringUtils.equals(swearWord, wordToCheck))
                                    return false;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("word : "+word.toLowerCase()+": is valid : "+valid);
        return true;
    }
}
