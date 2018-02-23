package test;

import java.sql.Connection;
import java.sql.DriverManager;

import main.GoodInfo;
import main.GoodInfoDao;
import main.View;

public class JDBCExample11_delete {
	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try(Connection conn = (Connection)DriverManager.getConnection(url,"iot", "1234")){
			View view = View.getInstatnce();
			GoodInfoDao dao = new GoodInfoDao();
			
			view.printList(dao.getGoodInfoList());
			String code = view.getString("Insert Code to Delete : ");
						
			if(dao.getGoodInfo(code) != null) {
				dao.delete(code);				
				System.out.println( " | Delete Complete");
			}else {
				System.out.println("This code doesn't exist");
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}				
	}
}
