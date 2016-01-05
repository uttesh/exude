package com.uttesh.exude;

import com.uttesh.exude.stopping.StoppingParser;
import com.uttesh.exude.stopping.TrushDuplicates;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Uttesh Kumar T.H.
 */
public class ExudeData {

    public void filterStoppings(String data, String result) throws IOException {
        try {
            TrushDuplicates trushDuplicates = TrushDuplicates.getInstance();
            trushDuplicates.filterDuplicates(data, result);
            StoppingParser stoppingParser = StoppingParser.getInstance();
            Set<String> dataSet = trushDuplicates.filterData(result);
            Iterator<String> iterable = dataSet.iterator();
            StringBuilder finalFilteredData = new StringBuilder();
            while (iterable.hasNext()) {
                String line = iterable.next();
                finalFilteredData.append(stoppingParser.filterStoppingWords(line));
            }
            result = finalFilteredData.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void filterStoppings(File inputFile, File outputFile,File tempFile) {
        try {
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
            stoppingParser.finalData(finalFilteredData,outputFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
