package com.ai.aris.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import oracle.jdbc.pool.OracleDataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.basecode.bean.AiscHisDatasourceBean;
import com.ai.aris.server.basecode.service.interfaces.IAiscHisDatasourceSV;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

/**
 * 
 * @author caorj
 * @version 1.0
 */
public class DAOUtils {
	private Log logger = LogFactory.getLog(DAOUtils.class);
	private static IAiscHisDatasourceSV sv = (IAiscHisDatasourceSV) ServiceFactory
			.getService(IAiscHisDatasourceSV.class);

	private DAOUtils() {
	}

	public static DataSource getDataSource(String orgId,String type) throws DAOException {
		OracleDataSource dataSource = null;
		if (dataSource == null) {
			try {
				//获取数据库用户/密码和url
				AiscHisDatasourceBean datasource = sv.getHisDatasource(orgId,type);

				if (datasource != null && !"".equals(datasource)) {
					dataSource = new OracleDataSource();
					dataSource.setUser(datasource.getSourceUser());
					dataSource.setPassword(datasource.getSourcePassword());
					dataSource.setURL(datasource.getSourceUrl());
				}
			} catch (Exception e) {
				throw new DAOException(e);
			}
		}
		return dataSource;
	}
	
	public static DataSource getSqlServerDataSource(String orgId,String type) throws DAOException {
		SQLServerDataSource dataSource = null;
		if (dataSource == null) {
			try {
				AiscHisDatasourceBean datasource = sv.getHisDatasource(orgId,type);

				if (datasource != null && !"".equals(datasource)) {
					dataSource = new SQLServerDataSource();
					dataSource.setUser(datasource.getSourceUser());
					dataSource.setPassword(datasource.getSourcePassword());
					dataSource.setURL(datasource.getSourceUrl());
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new DAOException(e);
			}
		}
		return dataSource;
	}
	
	public static Connection getSqlServerDBConnection(String source,String type) throws DAOException {
		try {
			return getSqlServerDataSource(source,type).getConnection();
		} catch (SQLException se) {
			throw new DAOException("SQL Exception while getting "
					+ "DB connection : \n" + se);
		}
	}

	/**
	 * jdbc_oracle
	 * 
	 * @param source
	 * @return
	 * @throws DAOException
	 */
	public static Connection getDBConnection(String source,String type) throws DAOException {
		try {
			return getDataSource(source,type).getConnection();
		} catch (SQLException se) {
			throw new DAOException("SQL Exception while getting "
					+ "DB connection : \n" + se);
		}
	}


	public static void closeConnection(Connection dbConnection)
			throws DAOException {
		try {
			if (dbConnection != null && !dbConnection.isClosed()) {
				dbConnection.close();
			}
		} catch (SQLException se) {
			throw new DAOException("SQL Exception while closing "
					+ "DB connection : \n" + se);
		}
	}

	public static void closeResultSet(ResultSet result) throws DAOException {
		try {
			if (result != null) {
				result.close();
			}
		} catch (SQLException se) {
			throw new DAOException("SQL Exception while closing "
					+ "Result Set : \n" + se);
		}
	}

	public static void closeStatement(PreparedStatement stmt)
			throws DAOException {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException se) {
			throw new DAOException("SQL Exception while closing "
					+ "Statement : \n" + se);
		}
	}

}
