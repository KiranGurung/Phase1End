package com.hcl.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.hcl.model.Authority;

@Repository
public class AuthorityDAOImpl extends JdbcDaoSupport implements AuthorityDAO {
	
	@Autowired
	DataSource ds;

	@PostConstruct
	// Initializes the datasource for JDBCDao Support
	private void initialize(){
		setDataSource(ds);
	}
	
	@Override
	public void createAuthority(Authority auth) {
		// Creates new authorities
		String sql = "INSERT INTO authorities VALUES(?,?)";
		getJdbcTemplate().update(sql, new Object[] {
				auth.getUsername(), auth.getAuthority()
		});

	}

	@Override
	public Authority getAuth(String userName) {
		// Gets authorities of specific user
		String sql = "SELECT * FROM authorities WHERE username=?";
		List<Authority> check = getJdbcTemplate().query(sql, new Object[]{userName}, new RowMapper<Authority>(){
			@Override
			public Authority mapRow(ResultSet rs, int rwNumber) throws SQLException {
				Authority auth = new Authority();
				auth.setUsername(rs.getString("username"));
				auth.setAuthority(rs.getString("authority"));
				return auth;
			}
		});
		// If there were no users return null
		if(check.size() == 0) {
			return null;
		}
		return check.get(0);
	}

}
