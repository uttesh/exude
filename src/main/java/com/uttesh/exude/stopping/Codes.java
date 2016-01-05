package com.uttesh.exude.stopping;

import com.uttesh.exude.common.Constants;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Uttesh Kumar T.H.
 */
public class Codes {
 
    Map<Character,Integer> alphabetAsciiMap = new HashMap<Character, Integer>();
    
    public void populateAsciiMap(){
        for(int i =0;i< Constants.ENGLISH_ALPHABETS.length();i++){
            alphabetAsciiMap.put(Constants.ENGLISH_ALPHABETS.charAt(i), (int)Constants.ENGLISH_ALPHABETS.charAt(i));
        }
    }
}
