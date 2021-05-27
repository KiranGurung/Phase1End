package com.hcl.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.hcl.model.User;

@Repository
public class UserDAOImpl extends JdbcDaoSupport implements UserDAO {

	@Autowired
	DataSource ds;

	@PostConstruct
	// Initializes the datasource for JDBCDao Support
	private void initialize(){
		setDataSource(ds);
	}
	
	@Override
	public void createUser(User user) {
		// Inserts user into users table
		String sql = "INSERT INTO users VALUES(?,?,1)";
		getJdbcTemplate().update(sql, new Object[] {
				user.getUsername(), user.getPassword()
		});
	}

	@Override
	public User getUser(String userName) {
		// Gets username and password of specific user
		String sql = "SELECT * FROM users WHERE username=?";
		List<User> check = getJdbcTemplate().query(sql, new Object[]{userName},new int[] {Types.VARCHAR}, new RowMapper<User>(){
			@Override
			public User mapRow(ResultSet rs, int rwNumber) throws SQLException {
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				return user;
			}
		});
		// If there were no users return null
		if(check.size() == 0) {
			return null;
		}
		return check.get(0);
	}

}
