package com.uttesh.exude;

import com.uttesh.exude.exception.InvalidDataException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 *
 * @author Uttesh Kumar T.H.
 */
public class SimpleTextDataTest {

    ExudeData exudeData = null;
    
    String sampleSwearData = "testing the swearing words tit";

    String sampleData = "testing testing testing the keep keep the the duplicate data data in result";

    @BeforeTest
    public void init() {
        exudeData = ExudeData.getInstance();
    }
    
    @Test(enabled = true)
    public void filterStoppingFromData() throws InvalidDataException {
        String output = exudeData.filterStoppings(sampleData);
        Assert.assertEquals(getCount(output, "the"), 0);
    }

    @Test(enabled = true)
    public void filterStoppingFromDataKeepDuplicates() throws InvalidDataException {
        String output = exudeData.filterStoppingsKeepDuplicates(sampleData);
        Assert.assertEquals(getCount(output, "testing"), 3);
    }
    
    @Test(enabled = true)
    public void findSwearingFromData() throws InvalidDataException {
        String output = exudeData.getSwearWords(sampleSwearData);
        Assert.assertEquals(getCount(output, "tit"), 1);
    }
    
    @Test(enabled = true)
    public void findNoSwearingFromData() throws InvalidDataException {
        String output = exudeData.getSwearWords(sampleData);
        Assert.assertEquals(getCount(output, "tit"), 0);
    }
    
    
    
    
     private int getCount(String data, String input) {
        Pattern pattern = Pattern.compile(input);
        Matcher matcher = pattern.matcher(data);
        int count = 0;
        while (matcher.find()) {
            count += 1;
        }
        return count;
    }

}
