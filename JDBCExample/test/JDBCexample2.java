
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCexample2 {

	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:XE"; //���ͳݿ��� ������ �����ͺ��̽�.

		Scanner s = new Scanner(System.in);
		System.out.println("�ڵ�");
		String search = s.nextLine();

//		System.out.println("���ı��� :");
//		String search = s.nextLine();

		// String sql = "select * from goodsinfo " + "where name like '%" + product +
		// "%' " + "order by " + orderBy;

		String sql = "select * from goodsinfo " + "where code = '" + search + "'";
		try (Connection conn = (Connection) DriverManager.getConnection(url, "iot", "1234");
				Statement stmt =  (Statement)conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);

		) {
			if (rs.next()) {
				System.out.println("   ��ǰ�ڵ�  ��ǰ�� \t\t���� ������");
				System.out.println("------------------------------------------------");
				String code = rs.getString("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				String maker = rs.getString("maker");
				System.out.printf("%8s %s \t%12d %s%n", code, name, price, maker);
			} else {
				System.out.println("��ǰ�ڵ�: " + search + "�� �������� �ʽ��ϴ�.");
			}

		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
	}
}
