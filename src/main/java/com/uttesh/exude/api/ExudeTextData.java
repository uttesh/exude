/*
 * Copyright 2016 Rivetlabs.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.uttesh.exude.api;

import com.uttesh.exude.common.ExudeRequest;
import com.uttesh.exude.common.ExudeResponse;
import com.uttesh.exude.exception.InvalidDataException;
import com.uttesh.exude.swear.SwearParser;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rivetlabs
 */
public class ExudeTextData implements ExudeAPI {

    Logger logger = Logger.getLogger("ExudeTextData");

    @Override
    public ExudeResponse filterStoppings(ExudeRequest exudeRequest)throws InvalidDataException{
        try {
            return ExudeAPIImpl.getInstance().filterStoppings(exudeRequest);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            throw new InvalidDataException("Invalid Data");
        }
    }

    @Override
    public ExudeResponse filterStoppingKeepDuplicate(ExudeRequest exudeRequest) throws InvalidDataException{
        try {
            return ExudeAPIImpl.getInstance().filterStoppingWithDuplicate(exudeRequest);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            throw new InvalidDataException("Invalid Data");
        }
    }

    @Override
    public ExudeResponse getSwearWords(ExudeRequest exudeRequest) throws InvalidDataException{
        StringBuilder finalFilteredData = new StringBuilder();
        try {
            if (exudeRequest.getData().isEmpty()) {
                throw new InvalidDataException("Invalid Data");
            }
            SwearParser swearParser = SwearParser.getInstance();
            finalFilteredData.append(swearParser.getSwearWords(exudeRequest.getData()));
            swearParser.resetSwearWords();
            ExudeResponse response = new ExudeResponse();
            response.setResultData(finalFilteredData.toString());
            return response;
        } catch (Exception e) {
           logger.log(Level.SEVERE, e.getMessage());
            throw new InvalidDataException("Invalid Data");
        }
    }

}
