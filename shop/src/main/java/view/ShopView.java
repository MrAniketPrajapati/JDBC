package view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import controller.ShopController;
import model.Product;

public class ShopView {

	static Scanner myInput = new Scanner(System.in);
	static Product product = new Product();
	static ShopController shopController = new ShopController();
	
	public static void main(String[] args) throws SQLException {
		do {
			System.out.println("Select opertion to perform ");
			System.out.println("1.Add Product\n2.Remove Product\n3.Update Product detail\n4.Fetch product\n0.exit");
			System.out.print("Enter digit respective to desired option : ");
			int userInput = myInput.nextInt();
			myInput.nextLine();
			switch (userInput) {
			case 0:
				myInput.close();
				System.out.println("--------Exited--------");
				System.exit(0);
				break;
			case 1:
				System.out.println("How many product you want to add ?\n1. Single product\n2.Multiple product ");
				int productCount = myInput.nextInt();
				myInput.nextLine();
				
		if(productCount == 1){
			System.out.println("Enter Product id: ");
			int i_p_id = myInput.nextInt();
			myInput.nextLine();
			System.out.println("Enter Product Name: ");
			String i_p_name = myInput.nextLine();
			System.out.println("Enter Price: ");
			int i_p_price = myInput.nextInt();
			myInput.nextLine();
			System.out.println("Enter Quantity: ");
			int i_p_quantity = myInput.nextInt();
			myInput.nextLine();
			boolean i_p_avalability=false;
			if (i_p_quantity > 0) {
				i_p_avalability=true;
			}
			if ((shopController.addProduct(i_p_id, i_p_name, i_p_price, i_p_quantity, i_p_avalability)) != 0) {
				System.out.println("Product Added");
			}else {
				System.out.println("Product not Added");
			} 
		} else {
			boolean toContinue = true;
			ArrayList<Product> products = new ArrayList<Product>();
			do {
				Product product = new Product();
				System.out.print("enter id : ");
				product.setP_id(myInput.nextInt());
				myInput.nextLine();
				System.out.print("enter name : ");
				product.setP_name(myInput.nextLine());
				System.out.print("enter price : ");
				product.setP_price(myInput.nextInt());
				myInput.nextLine();
				System.out.print("enter quantity : ");
				int quantity = myInput.nextInt();
				product.setP_quantity(quantity);
				myInput.nextLine();
				boolean i_p_avaialability = false;
				if(quantity > 0) {
					i_p_avaialability = true;
				}
				product.setP_availability(i_p_avaialability);
				products.add(product);
				System.out.println("Press 1 to continue adding products, Press 0 to stop adding products : ");
				int toAdd = myInput.nextInt();
				myInput.nextLine();
				if(toAdd == 0) {
					toContinue = false;
				}
				
			} while (toContinue);
			shopController.addMultipleProduct(products);
		}
			
			    break;
			case 2:
				//Handle product remove
				System.out.println("Enter product id to remove: ");
				int productIdRemove = myInput.nextInt();
				myInput.nextLine();
				if (shopController.removeProduct(productIdRemove) != 0) {
					System.out.println("DATA Deleted Successfully");
				} else {
					System.out.println("Product with given id does not exits , no remove operation ");
				}
				System.out.println();
				break;
			case 3:
				//Handle product details update
				System.out.println("Enter product id to update: ");
				int productIdToUpdate = myInput.nextInt();
				myInput.nextLine();
				ResultSet product = shopController.fetchProduct(productIdToUpdate);
				if (product.next()) {
					System.out.println("What do you want to update: ?");
					System.out.println("1.Name\n2.Price\n3.Quantity");
					System.out.println("Enter Number respective to desired option : ");
					byte updateOption = myInput.nextByte();
					myInput.nextLine();
					switch (updateOption) {
					case 1:
						System.out.println("Enter Name to update: ");
						String nameToUpdate = myInput.nextLine();
						if(shopController.updateProductName(productIdToUpdate, nameToUpdate) !=0) {
							System.out.println("Record Updated");
						}else {
							System.out.println("Record Not Updated");
						}
						break;
						
                    case 2:
						
						break;
						
                    case 3:
						
						break;

					default:
						System.out.println("Invalid selection");
						break;
					}
				} else {
					System.out.print("Product with given id does not exits , Updat opertion can not be performed ");
				}
				break;
				
			case 4:
				System.out.println("Enter Product id to fetch : ");
				int productIdToFind = myInput.nextInt();
				myInput.nextLine();
				ResultSet fetchProduct = shopController.fetchProduct(productIdToFind);
				boolean next = fetchProduct.next();
				if(next) {
					System.out.println("Product details");
					System.out.println("ID : " + fetchProduct.getInt(1));
					System.out.println("Name : " + fetchProduct.getString(2));
					System.out.println("Price : " + fetchProduct.getInt(3));
					System.out.println("Quantity : " + fetchProduct.getInt(4));
					if(fetchProduct.getBoolean(5)) {
						System.out.println("Avilability : Available");
					}else {
						System.out.println("Avilability : Not Available");
					}
					System.out.println();
				}else {
					System.out.println("Product with id : " + productIdToFind + "does not exit.");
					System.out.println();
				}
			
			   break;
			case 5:
			
			   break;
			   
			default:
				System.out.println("-------InvalidSection--------");
				break;
			}
		} while (true);
	}
}
