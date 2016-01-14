package com.uttesh.exude;

import com.uttesh.exude.common.Constants;
import com.uttesh.exude.stopping.StoppingParser;
import com.uttesh.exude.stopping.TrushDuplicates;
import com.uttesh.exude.swear.SwearParser;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;

/**
 *
 * @author Uttesh Kumar T.H.
 */
public class ExudeData {

    private static ExudeData instance = null;

    protected ExudeData() {
    }

    public static ExudeData getInstance() {
        if (instance == null) {
            instance = new ExudeData();
        }

        return instance;
    }

    public String filterStoppings(String data) throws IOException {
        StringBuilder finalFilteredData = new StringBuilder();
        try {
            if (data == null || data.trim().length() == 0) {
                return "";
            }
            TrushDuplicates trushDuplicates = TrushDuplicates.getInstance();
            boolean isFile = Pattern.matches(Constants.FILE_PATH_REGULAR_EXPRESSION, data);
            boolean isURL = Pattern.matches(Constants.URL_REGULAR_EXPRESSION, data);
            String tempData = "";
            if (isFile || isURL) {
                tempData = trushDuplicates.filterDuplicates(data);
            } else {
                tempData = trushDuplicates.filterDuplicatesInText(data);
            }
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

            stoppingParser.resetResultSet();
            trushDuplicates.resetnTempSet();
            return finalFilteredData.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getSwearWords(String data) throws IOException {
        StringBuilder finalFilteredData = new StringBuilder();
        try {
            if (data == null || data.trim().length() == 0) {
                return "";
            }
            SwearParser swearParser = SwearParser.getInstance();
            finalFilteredData.append(swearParser.getSwearWords(data));
            swearParser.resetSwearWords();
            return finalFilteredData.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public void filterStoppings(String inputFile, String outputFile, String tempFile) {
        try {
            if (inputFile == null || inputFile.trim().length() == 0
                    || outputFile == null || outputFile.trim().length() == 0
                    || tempFile == null || tempFile.trim().length() == 0) {
                // do nothing
            }
            TrushDuplicates trushDuplicates = TrushDuplicates.getInstance();
            trushDuplicates.filterDuplicates(inputFile, tempFile);
            StoppingParser stoppingParser = StoppingParser.getInstance();
            Set<String> dataSet = trushDuplicates.filter(tempFile);
            Iterator<String> iterable = dataSet.iterator();
            StringBuilder finalFilteredData = new StringBuilder();
            while (iterable.hasNext()) {
                String line = iterable.next();
                finalFilteredData.append(stoppingParser.filterStoppingWords(line));
            }
            stoppingParser.finalData(finalFilteredData, outputFile);
            stoppingParser.resetResultSet();
            trushDuplicates.resetnTempSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
