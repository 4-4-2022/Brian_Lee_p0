package com.Brian.respository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.Brian.model.Hotdog;
import com.Brian.util.ConnectionFactory;
import com.Brian.util.ResourceCloser;

public class hotdogRespositoryImp implements hotdogRepository{

	public Set<Hotdog> findAllHotdogs() {
		Set<Hotdog> hotdogs = new HashSet<Hotdog>();
		
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
						set.getFloat(4)
					));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ResourceCloser.closeConnection(conn);
			ResourceCloser.closeResultSet(set);
			ResourceCloser.closeStatement(stmt);
		}
		
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
