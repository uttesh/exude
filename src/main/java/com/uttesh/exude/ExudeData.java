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
package com.uttesh.exude;

import com.uttesh.exude.common.Constants;
import com.uttesh.exude.common.ExudeRequest;
import com.uttesh.exude.common.ExudeResponse;
import com.uttesh.exude.exception.InvalidDataException;
import com.uttesh.exude.factory.ExudeFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Uttesh Kumar T.H.
 */
public class ExudeData {

    Logger logger = Logger.getLogger("ExudeData");
    private static ExudeData instance = null;
    private ExudeRequest exudeRequest = new ExudeRequest();

    protected ExudeData() {
    }

    public static ExudeData getInstance() {
        if (instance == null) {
            instance = new ExudeData();
        }
        return instance;
    }

    public String filterStoppings(String data) throws InvalidDataException {
        try {
            if (data.isEmpty()) {
                throw new InvalidDataException(Constants.INVALID_DATA);
            }
            exudeRequest.setData(data);
            ExudeFactory exudeFactory = new ExudeFactory(exudeRequest);
            ExudeResponse response = exudeFactory.filterStopppingData();
            return response.getResultData();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            throw new InvalidDataException("Invalid Data", e);
        }
    }

    public String filterStoppingsKeepDuplicates(String data) throws InvalidDataException {
        try {
            if (data.isEmpty()) {
                throw new InvalidDataException(Constants.INVALID_DATA);
            }
            exudeRequest.setData(data);
            exudeRequest.setKeepDuplicate(true);
            ExudeFactory exudeFactory = new ExudeFactory(exudeRequest);
            ExudeResponse response = exudeFactory.filterStopppingData();
            return response.getResultData();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            throw new InvalidDataException("Invalid Data", e);
        }
    }

    public String getSwearWords(String data) throws InvalidDataException {
        try {
            if (data.isEmpty()) {
                throw new InvalidDataException(Constants.INVALID_DATA);
            }
            exudeRequest.setData(data);
            exudeRequest.setKeepDuplicate(true);
            ExudeFactory exudeFactory = new ExudeFactory(exudeRequest);
            ExudeResponse response = exudeFactory.getSwearWords();
            return response.getResultData();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            throw new InvalidDataException("Invalid Data", e);
        }
    }

}
