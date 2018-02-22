import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JDBCExample3 {

	public static void main(String[] args) throws Exception {
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		Scanner s = new Scanner(System.in);
		System.out.println("�ڵ�");
		String search = s.nextLine();

		String sql = "select * from goodsinfo " + "where code = '" + search + "'";
		try (Connection conn = (Connection) DriverManager.getConnection(url, "iot", "1234");) {
			GoodInfoDao dao = new GoodInfoDao(conn);
			
			GoodInfo goodInfo = dao.getGoodInfo(conn, search);
			if (goodInfo != null) {
				System.out.println(goodInfo);
			} else {
				System.out.println("�������� �ʴ� ��ǰ�Դϴ�.");
			}

			List<GoodInfo> list = dao.getGoodInfoList(conn);
			for (GoodInfo g : list) {
				System.out.println(g);
			}

		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
	}
}
