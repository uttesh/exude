package com.uttesh.exude.common;

import com.uttesh.exude.stopping.StoppingConstatns;
import com.uttesh.exude.swear.SwearConstants;

/**
 *
 * @author Uttesh Kumar T.H.
 */
public interface Constants extends SwearConstants,StoppingConstatns{
    
    String ENGLISH_ALPHABETS = "abcdefghijklmnopqrstuvwxyz";
    String UTF_8="UTF8";
    String SPACE = "\\ ";
    String COMMA = ",";
    String WORD_PATTERN = "[^a-z A-Z 0-9]";
    String FILE_PATH_REGULAR_EXPRESSION = "([a-zA-Z]:)?(\\\\[a-zA-Z0-9_.-]+)+\\\\?";
    String URL_REGULAR_EXPRESSION = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
    String MULTIPLE_SPACE_TAB_NEW_LINE="  +|   +|\t|\r|\n";
    String INVALID_DATA="Invalid Data";
    
    enum ExudeType {
        WEB_LINK,FILE,TEXT_DATA
    }
    
    enum STATUS {
        SUCCESS,FAILURE
    }
    
}
