package ua.com.ex.tools.file;

import ua.com.ex.exception.ToolsException;

public interface FileOperation {

    public void cleanOldFile(String string) ;

    public String readTextFile(String path) throws ToolsException;

    public byte[] readBinaryFile(String path) throws ToolsException;

    public void save(String path, String image) throws ToolsException;

    public boolean isExist(String path);

    public Long getLastModifiedDate(String path);
    
    public void setLastModifiedDate(String path, Long date);

}
