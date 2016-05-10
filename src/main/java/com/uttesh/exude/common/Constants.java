/*
 Copyright 2016 Uttesh Kumar T.H.

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
