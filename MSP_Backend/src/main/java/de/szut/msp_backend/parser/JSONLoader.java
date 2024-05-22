package de.szut.msp_backend.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;

public class JSONLoader
{
    private static JSONLoader instance;

    public static JSONLoader getInstance()
    {
        if (instance == null)
        {
            instance = new JSONLoader();
        }
        return instance;
    }

    private JSONLoader()
    {

    }

    private URL getResourceURL(String filename)
    {
        filename += ".json";
        return this.getClass().getClassLoader().getResource(filename);
    }

    public String getJsonString(String filename) throws FileNotFoundException
    {
        return new FileReader(getResourceURL(filename).toString()).toString();
    }
}
