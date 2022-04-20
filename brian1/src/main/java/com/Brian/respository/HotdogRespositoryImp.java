package com.Brian.respository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Brian.model.Hotdog;
import com.Brian.service.AccountLogger;
import com.Brian.util.ConnectionFactory;
import com.Brian.util.ResourceCloser;

public class HotdogRespositoryImp implements HotdogRepository{

	private static final Logger logger = LoggerFactory.getLogger(HotdogRespositoryImp.class);
	public ArrayList<Hotdog> findAllHotdogs() {
		ArrayList<Hotdog> hotdogs = new ArrayList<Hotdog>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		final String SQL = "select * from hotdogs";
		try {
			/*
			 * We made a utility method that puts in our DB credentials and returns a new connection
			 * to us.
			 */
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			//At this point, we have executed a statement and we have the records.
			set = stmt.executeQuery(SQL);
			while(set.next()) {
				hotdogs.add(new Hotdog(
						set.getInt(1),						
						set.getString(2),
						set.getInt(3),
						set.getFloat(4),
						set.getString(5)
					));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ResourceCloser.closeConnection(conn);
			ResourceCloser.closeResultSet(set);
			ResourceCloser.closeStatement(stmt);
		}
		logger.info("The retrieved hotdogs are:" + hotdogs);
		return hotdogs;
	}

	public Hotdog findHotdogByStyle(String flavor) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<Hotdog> findHotdogsByStyle(String... flavors) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void save(Hotdog hotdog) {
		// TODO Auto-generated method stub
		
	}

	public void update(Hotdog hotdog) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Hotdog cupcake) {
		// TODO Auto-generated method stub
		
	}



	
	
}
