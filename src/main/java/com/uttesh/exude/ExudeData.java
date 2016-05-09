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
