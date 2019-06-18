package com.ai.aris.util;

/**
 * <p>Title: Telecom CRM</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: </p>
 *
 * @author Alan
 * @version 1.0
 */

public class DAOException extends RuntimeException {
    public DAOException() {}
    public DAOException(String msg) {
        super(msg);
    }
    public DAOException(String msg, Throwable cause) {
        super(msg, cause);
    }
    public DAOException(Throwable cause) {
        super(cause);
    }
}
