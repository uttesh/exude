
import com.uttesh.exude.ExudeData;
import java.io.IOException;
import java.net.UnknownHostException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 *
 * @author Uttesh Kumar T.H.
 */
public class TestData {

    ExudeData exudeData = null;

    @BeforeTest
    public void init() throws UnknownHostException, IOException {
        exudeData = ExudeData.getInstance();
    }

    @Test(enabled = true)
    public void filterStoppingFromData() throws IOException {
        String inputData = "Kannada is a Southern Dravidian language, and according to Dravidian scholar Sanford Steever, its history can be conventionally divided into three periods; Old Kannada (halegannada) from 450–1200 A.D., Middle Kannada (Nadugannada) from 1200–1700 A.D., and Modern Kannada from 1700 to the present.[20] Kannada is influenced to an appreciable extent by Sanskrit. Influences of other languages such as Prakrit and Pali can also be found in Kannada language.";
        String output = exudeData.filterStoppings(inputData);
        System.out.println("filterStoppingFromData output : " + output);
    }

    @Test(enabled = false)
    public void filterStoppingFromFileData() throws IOException {
        String inputData = "E:\\rivetsys\\documents\\sentry\\sentry-gateway-api.docx";
        String output = exudeData.filterStoppings(inputData);
        System.out.println("filterStoppingFromFileData output : " + output);
    }

    @Test(enabled = true)
    public void getSwearWordsWithSpaceFromData() throws IOException {
        String inputData = "testing the bad words f               u                c                  k sorry to use the bad word in samples";
        String output = exudeData.getSwearWords(inputData);
        System.out.println("getSwearWordsWithSpaceFromData output swear words : " + output);
    }

    @Test(enabled = true)
    public void getSwearWordsFromData() throws IOException {
        String inputData = "testing the bad words boobs sorry to use the bad word in samples";
        String output = exudeData.getSwearWords(inputData);
        System.out.println("getSwearWordsFromData output swear words : " + output);
    }

    @Test(enabled = true)
    public void filterStoppingFromLinkData() throws IOException {
        long start = System.currentTimeMillis();
        String inputData = "https://en.wikipedia.org/wiki/Rama";
        String output = exudeData.filterStoppings(inputData);
        System.out.println("total time taken : "+((System.currentTimeMillis() - start)/1000));
        System.out.println("filterStoppingFromLinkData output : " + output);
    }
}
