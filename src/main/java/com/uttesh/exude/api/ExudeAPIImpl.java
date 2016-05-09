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

import com.uttesh.exude.common.Constants;
import com.uttesh.exude.common.ExudeRequest;
import com.uttesh.exude.common.ExudeResponse;
import com.uttesh.exude.exception.InvalidDataException;
import com.uttesh.exude.stopping.StoppingParser;
import com.uttesh.exude.stopping.TrushDuplicates;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Rivetlabs
 */
public class ExudeAPIImpl {

    protected static ExudeAPIImpl instance = null;

    TrushDuplicates trushDuplicates = TrushDuplicates.getInstance();

    public static ExudeAPIImpl getInstance() {
        if (instance == null) {
            instance = new ExudeAPIImpl();
        }
        return instance;
    }

    protected ExudeAPIImpl() {
    }

    ;

    public ExudeResponse filterStoppings(ExudeRequest exudeRequest) throws InvalidDataException {
        String tempData = "";
        StringBuilder finalFilteredData = new StringBuilder();
        try {
            String fileData = exudeRequest.getData();
            tempData = trushDuplicates.filterDuplicates(fileData);
            StoppingParser stoppingParser = StoppingParser.getInstance();
            Set<String> dataSet = trushDuplicates.filterData(tempData);
            Iterator<String> iterable = dataSet.iterator();
            while (iterable.hasNext()) {
                String line = iterable.next();
                stoppingParser.filterStoppingWords(line.replaceAll(Constants.MULTIPLE_SPACE_TAB_NEW_LINE, " "));
            }
            trushDuplicates.filterDuplicate(stoppingParser.getResultSet());
            Iterator<String> _iterable = trushDuplicates.getTempSet().iterator();
            while (_iterable.hasNext()) {
                String line = _iterable.next();
                finalFilteredData.append(line.trim() + " ");
            }
            stoppingParser.reset();
            trushDuplicates.reset();
            return populateResponse(Constants.STATUS.SUCCESS.name(), finalFilteredData.toString());
        } catch (Exception e) {
            return populateResponse(Constants.STATUS.FAILURE.name(), e.getMessage());
        }
    }

    public ExudeResponse filterStoppingWithDuplicate(ExudeRequest exudeRequest) throws InvalidDataException {
        String tempData = "";
        StringBuilder finalFilteredData = new StringBuilder();
        try {
            tempData = exudeRequest.getData();
            StoppingParser stoppingParser = StoppingParser.getInstance();
            List<String> dataSet = trushDuplicates.filterDataKeepDuplicate(tempData);
            Iterator<String> iterable = dataSet.iterator();
            while (iterable.hasNext()) {
                String line = iterable.next();
                stoppingParser.filterStoppingWordsKeepDuplicates(line.replaceAll(Constants.MULTIPLE_SPACE_TAB_NEW_LINE, " "));
            }
            Iterator<String> _iterable = trushDuplicates.getTempList().iterator();
            while (_iterable.hasNext()) {
                String line = _iterable.next();
                finalFilteredData.append(line.trim() + " ");
            }
            stoppingParser.reset();
            trushDuplicates.reset();
            return populateResponse(Constants.STATUS.SUCCESS.name(), finalFilteredData.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new InvalidDataException("Invalid Data");
    }

    public ExudeResponse getSwearWords(ExudeRequest exudeRequest) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private ExudeResponse populateResponse(String status, String result) throws InvalidDataException {
        ExudeResponse response = new ExudeResponse();

        switch (Constants.STATUS.valueOf(status)) {
            case SUCCESS:
                response.setStatus(Constants.STATUS.SUCCESS.name().toUpperCase());
                response.setMessage("Sucessfully Processed the data");
                response.setResultData(result);
                return response;
            case FAILURE:
                response.setStatus(Constants.STATUS.FAILURE.name().toUpperCase());
                response.setMessage("Sucessfully Processed the data");
                response.setResultData(result);
                return response;
            default:
                break;

        }
        throw new InvalidDataException("Invalid Data");
    }

}
