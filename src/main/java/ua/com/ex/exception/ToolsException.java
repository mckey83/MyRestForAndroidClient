package ua.com.ex.exception;

public class ToolsException extends Exception{   
        
    private static final long serialVersionUID = 1L;

        public ToolsException() {
        }

        public  ToolsException(String message) {
            super(message);
        }

        public  ToolsException(String message, Throwable cause) {
            super(message, cause);
        }

        public  ToolsException(Throwable cause) {
            super(cause);
        }

        public  ToolsException (String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    
    
}
