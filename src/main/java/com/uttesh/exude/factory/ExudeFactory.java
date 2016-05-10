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
package com.uttesh.exude.factory;

import com.uttesh.exude.api.ExudeAPI;
import com.uttesh.exude.common.Constants;
import com.uttesh.exude.common.ExudeRequest;
import com.uttesh.exude.common.ExudeResponse;
import com.uttesh.exude.exception.InvalidDataException;
import com.uttesh.exude.api.ExudeFileData;
import com.uttesh.exude.api.ExudeTextData;
import com.uttesh.exude.api.ExudeWebLinkData;
import java.util.regex.Pattern;

/**
 *
 * @author Uttesh Kumar T.H.
 */
public class ExudeFactory {

    protected ExudeAPI exudeAPI = null;
    private ExudeRequest exudeRequest;

    public ExudeFactory(ExudeRequest exudeRequest) {
        boolean isFile = Pattern.matches(Constants.FILE_PATH_REGULAR_EXPRESSION, exudeRequest.getData());
        boolean isURL = Pattern.matches(Constants.URL_REGULAR_EXPRESSION, exudeRequest.getData());
        if (isFile || isURL) {
            exudeRequest.setType(isFile == true ? Constants.ExudeType.FILE.name()
                    : Constants.ExudeType.WEB_LINK.name());
        } else {
            exudeRequest.setType(Constants.ExudeType.TEXT_DATA.name());
        }
        this.exudeRequest = exudeRequest;
    }

    private ExudeAPI getExudeAPI() {
        switch (Constants.ExudeType.valueOf(getExudeRequest().getType())) {
            case TEXT_DATA:
                exudeAPI = new ExudeTextData();
                break;
            case FILE:
                exudeAPI = new ExudeFileData();
                break;
            case WEB_LINK:
                exudeAPI = new ExudeWebLinkData();
                break;
        }
        return exudeAPI;
    }

    public ExudeResponse filterStopppingData() throws InvalidDataException {
        try {
            exudeAPI = getExudeAPI();
            return exudeRequest.isKeepDuplicate() ? exudeAPI.filterStoppingKeepDuplicate(exudeRequest)
                    : exudeAPI.filterStoppings(exudeRequest);
        } catch (Exception e) {
            throw new InvalidDataException("Invalid Input Data ", e);
        }
    }
    
     public ExudeResponse getSwearWords() throws InvalidDataException {
         try {
            exudeAPI = getExudeAPI();
            return exudeAPI.getSwearWords(exudeRequest);
        } catch (Exception e) {
            throw new InvalidDataException("Invalid Input Data ", e);
        }
     }

    public ExudeRequest getExudeRequest() {
        return exudeRequest;
    }

    public void setExudeRequest(ExudeRequest exudeRequest) {
        this.exudeRequest = exudeRequest;
    }

}
