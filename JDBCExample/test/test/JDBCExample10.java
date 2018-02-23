
package test;

import java.sql.Connection;
import java.sql.DriverManager;

import main.GoodInfo;
import main.GoodInfoDao;
import main.View;

public class JDBCExample10 {

	public static void main(String args[]) {

		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		// String sql = "insert into goodsinfo(code ,name ,price ,maker)" +
		// "values(?,?,?,?)";

		try {
			View view = View.getInstatnce();
			GoodInfoDao dao = new GoodInfoDao();
			// dao.printList(dao.getGoodInfoList(conn));
			//
			GoodInfo goodInfo = view.getGoodInfo();

			if (dao.getGoodInfo(goodInfo.getCode()) == null) {
				int rowNum = dao.insert(goodInfo);
				if (rowNum == 1) {
					System.out.println(goodInfo.getCode() + " | goods has been inserted.");
				}
			} else {
				System.out.println(goodInfo.getCode() + " | Your code is already registered");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
