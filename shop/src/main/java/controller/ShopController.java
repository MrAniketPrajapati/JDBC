//Aniket Prajapati
package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.postgresql.Driver;

import model.Product;


public class ShopController {
	public int addProduct(int id , String name , int price , int quantity , boolean availability) {
		Connection connection = null;
		int rowsAffected = 0;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
//			FileInputStream fileInputStream = new FileInputStream("shop.properties");
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			 connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop_jdbc", properties);
			
			PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO product VALUES (?,?,?,?,?)");
			prepareStatement.setInt(1, id);
			prepareStatement.setString(2, name);
			prepareStatement.setInt(3, price);
			prepareStatement.setInt(4, quantity);
			prepareStatement.setBoolean(5, availability);
			rowsAffected = prepareStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return rowsAffected;
		
	}
	public void addMultipleProduct(ArrayList<Product> product) {
		Connection connection = null;
		
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop_jdbc", properties);
			PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO product VALUES (?,?,?,?,?)");
			
			for(Product products : product) {
				prepareStatement.setInt(1, products.getP_id());
				prepareStatement.setString(2, products.getP_name());
				prepareStatement.setInt(3, products.getP_price());
				prepareStatement.setInt(4, products.getP_quantity());
				prepareStatement.setBoolean(5, products.isP_availability());
				prepareStatement.addBatch();
			}
			prepareStatement.executeBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public ResultSet fetchProduct(int id) {
		Connection connection = null;
		ResultSet resultSet = null;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop_jdbc", properties);
			PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM product WHERE p_id=?");
			prepareStatement.setInt(1, id);
		    resultSet = prepareStatement.executeQuery();
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return resultSet;
	}
	
	public int  removeProduct(int id) {
		int idDeleted = 0;
		Connection connection = null;
		
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop_jdbc", properties);
			PreparedStatement prepareStatement = connection.prepareStatement("DELETE FROM product WHERE p_id=?");
			prepareStatement.setInt(1, id);
			idDeleted = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return idDeleted;
	}
	
	public  int updateProductName(int id , String name) {
		      Connection connection = null;
		      int updateVerify = 0;
		      
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop_jdbc", properties);
			PreparedStatement prepareStatement = connection.prepareStatement("UPDATE product SET p_name  = ? WHERE p_id = ? ;");
			prepareStatement.setString(1, name);
			prepareStatement.setInt(2, id);
			updateVerify = prepareStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return updateVerify;
		
	}

}
