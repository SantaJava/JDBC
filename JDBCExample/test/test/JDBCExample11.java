package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import main.GoodInfo;
import main.GoodInfoDao;
import main.View;

public class JDBCExample11 {

	public static void main(String args[]) {

		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		// String sql = "insert into goodsinfo(code ,name ,price ,maker)" +
		// "values(?,?,?,?)";

		try (Connection conn = (Connection) DriverManager.getConnection(url, "iot", "1234")) {
			View view = View.getInstatnce();
			GoodInfoDao dao = new GoodInfoDao();

			// 상품목록 추출 및 출력
			List<GoodInfo> list = dao.getGoodInfoList();
			view.printList(list);

			// 상품 선택
			String code = view.getString("수정 상품 코드: ");
			GoodInfo goodInfo = dao.getGoodInfo(code);

			if (goodInfo == null) {
				System.out.println("This is a wrong code");
				return;
			}

			// 수정값 입력
			GoodInfo goodInfo2 = view.getGoodInfo(goodInfo); // 수정
			System.out.println(goodInfo2);
			// DB수정
			int result = dao.update(goodInfo2);
			System.out.println(result);
			System.out.println("Update Complete");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
