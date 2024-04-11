package de.szut.msp_backend.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;

public class JSONLoader
{
    private static JSONLoader instance = null;

    public static JSONLoader getInstance()
    {
        if (instance == null)
        {
            instance = new JSONLoader();
        }
        return instance;
    }

    public FileReader getResourceFileFromName(String filename)
    {
        try
        {
            filename += ".json";
            URL fileURL = this.getClass().getClassLoader().getResource(filename);
            if (fileURL == null)
            {
                System.exit(404);
            }
            return new FileReader(fileURL.getFile());
        }
        catch (FileNotFoundException ex)
        {
            System.exit(404);
            //IntelliJ was weird and demanded a return statement even though this should never be able to be reached...
            return null;
        }
    }
}
