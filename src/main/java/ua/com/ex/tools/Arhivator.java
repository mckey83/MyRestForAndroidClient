package ua.com.ex.tools;

import ua.com.ex.exception.ToolsException;

public interface Arhivator {

    public void unzip(String achive, String result) throws ToolsException;
    
}
