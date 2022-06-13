package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Staff;


public class StaffDAO {
	private String jdbcURL = "jdbc:postgresql://ec2-52-73-184-24.compute-1.amazonaws.com:5432/detss51crqfso1";
	private String jdbcUsername = "xnxlvtplroyzmo";
	private String jdbcPassword = "194a1d07595c2cb401f727a4b1a405a090918a683473a6aaaae2d4ebf176e6fa";

	private static final String INSERT_STAFFS_SQL = "INSERT INTO staffs" + "  (name, address, phone, email, role) VALUES "
			+ " (?, ?, ?,?,?);";

	private static final String SELECT_STAFF_BY_ID = "select id,name,address,phone,email,role from staffs where id =?";
	private static final String SELECT_ALL_STAFFS = "select * from staffs";
	private static final String DELETE_STAFFS_SQL = "delete from staffs where id = ?;";
	private static final String UPDATE_STAFFS_SQL = "update staffs set name = ?, address = ?, phone = ?,email= ?, role =? where id = ?;";

	public StaffDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertStaff(Staff staff) throws SQLException {
		System.out.println(INSERT_STAFFS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STAFFS_SQL)) {
			preparedStatement.setString(1, staff.getName());
			preparedStatement.setString(2, staff.getAddress());
			preparedStatement.setString(3, staff.getPhone());
			preparedStatement.setString(4, staff.getEmail());
			preparedStatement.setString(5, staff.getRole());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Staff selectStaff(int id) {
		Staff staff = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STAFF_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				String role = rs.getString("role");
				staff = new Staff(id, name, address,phone, email, role);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return staff;
	}

	public List<Staff> selectAllStaffs() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Staff> staffs = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STAFFS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				String role = rs.getString("role");
				staffs.add(new Staff(id, name,address,phone, email, role));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return staffs;
	}

	public boolean deleteStaff(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_STAFFS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateStaff(Staff staff) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_STAFFS_SQL);) {
			statement.setString(1, staff.getName());
			statement.setString(2, staff.getAddress());
			statement.setString(3, staff.getPhone());
			statement.setString(4, staff.getEmail());
			statement.setString(5, staff.getRole());
			statement.setInt(6, staff.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
