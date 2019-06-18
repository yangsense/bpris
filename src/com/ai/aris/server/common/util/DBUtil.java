package com.ai.aris.server.common.util;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * JDBC公共方法
 * @author xuepf
 *
 */
public class DBUtil {

	/** 
     * @param rs  :结果集 
     * @param stmt:在程序中将使用PrepareStatement,其中Statement是其父类 
     * @param conn:数据库连接 
     */  
    public static void release(ResultSet rs, Statement stmt, Connection conn) {  
        // 判断结果集是否为空，如果不为空，关闭清空  
        if (rs != null) {  
            try {  
                rs.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
            rs = null;  
        }  
        // 判断声明是否为空，如果不为空，关闭清空  
        if (stmt != null) {  
            try {  
                stmt.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
            stmt = null;  
        }  
        // 判断连接是否为空，如果不为空，关闭清空  
        if (conn != null) {  
            try {  
                conn.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
            conn = null;  
        }  
    }  
}  
