package com.uttesh.exude.handler;

import com.uttesh.exude.common.BaseResource;
import com.uttesh.exude.common.Constants;
import com.uttesh.exude.stopping.StoppingResource;
import com.uttesh.exude.swear.SwearResource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScrawlWords {

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
        boolean valid = true;
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
                            valid = false;
                        }
                    }
                } else if (baseResource instanceof SwearResource) {
                    if (Character.isLetter((char) firstCharater)) {
                        String searchProp = String.valueOf(word.charAt(0));
                        String words = baseResource.getProperties(searchProp);
                        if (words != null && words.contains(word.toLowerCase())) {
                            valid = false;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("word : "+word.toLowerCase()+": is valid : "+valid);
        return valid;
    }
}
