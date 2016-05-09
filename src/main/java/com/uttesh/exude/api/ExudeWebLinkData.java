package com.uttesh.exude.api;

import com.uttesh.exude.common.ExudeRequest;
import com.uttesh.exude.common.ExudeResponse;
import com.uttesh.exude.exception.InvalidDataException;
import com.uttesh.exude.swear.SwearParser;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

/**
 *
 * @author Rivetlabs
 */
public class ExudeWebLinkData implements ExudeAPI {

    Logger logger = Logger.getLogger("ExudeWebLinkData");

    @Override
    public ExudeResponse filterStoppings(ExudeRequest exudeRequest) throws InvalidDataException {
        try {
            String linkData = getLinkData(exudeRequest.getData()).toString();
            exudeRequest.setData(linkData);
            return ExudeAPIImpl.getInstance().filterStoppings(exudeRequest);
        } catch (InvalidDataException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        throw new InvalidDataException("Invalid Data");
    }

    @Override
    public ExudeResponse filterStoppingKeepDuplicate(ExudeRequest exudeRequest) throws InvalidDataException {
        try {
            String linkData = getLinkData(exudeRequest.getData()).toString();
            exudeRequest.setData(linkData);
            return ExudeAPIImpl.getInstance().filterStoppingWithDuplicate(exudeRequest);
        } catch (InvalidDataException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        throw new InvalidDataException("Invalid Data");
    }

    public ExudeResponse getSwearWords(ExudeRequest exudeRequest) throws InvalidDataException {
        StringBuilder finalFilteredData = new StringBuilder();
        try {
            String linkData = getLinkData(exudeRequest.getData()).toString();
            SwearParser swearParser = SwearParser.getInstance();
            finalFilteredData.append(swearParser.getSwearWords(linkData));
            swearParser.resetSwearWords();
            ExudeResponse response = new ExudeResponse();
            response.setResultData(finalFilteredData.toString());
            return response;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            throw new InvalidDataException("Invalid Data");
        }
    }

    public BodyContentHandler getLinkData(String data) throws InvalidDataException {
        try {
            Parser parser = new AutoDetectParser();
            BodyContentHandler handler = new BodyContentHandler(10 * 1024 * 1024);
            Metadata metadata = new Metadata();
            try {
                URL url = new URL(data);
                URLConnection conn = url.openConnection();
                ParseContext context = new ParseContext();
                parser.parse(conn.getInputStream(), handler, metadata, context);
            } catch (UnknownHostException unknownHostException) {
                logger.log(Level.SEVERE, "UnkonwnHost : " + unknownHostException.getMessage());
            }
            return handler;
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (SAXException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (TikaException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        throw new InvalidDataException("Invalid Data");
    }

}
