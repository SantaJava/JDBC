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

			// ��ǰ��� ���� �� ���
			List<GoodInfo> list = dao.getGoodInfoList();
			view.printList(list);

			// ��ǰ ����
			String code = view.getString("���� ��ǰ �ڵ�: ");
			GoodInfo goodInfo = dao.getGoodInfo(code);

			if (goodInfo == null) {
				System.out.println("This is a wrong code");
				return;
			}

			// ������ �Է�
			GoodInfo goodInfo2 = view.getGoodInfo(goodInfo); // ����
			System.out.println(goodInfo2);
			// DB����
			int result = dao.update(goodInfo2);
			System.out.println(result);
			System.out.println("Update Complete");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
