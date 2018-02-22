import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample1 {
	public static void main(String[] args) {	
		Scanner sc = new Scanner(System.in);
		System.out.println(" ��ǰ�� : ");
		String product = sc.nextLine();
		System.out.println("���� ����: ");
		String orderBy = sc.nextLine();
	
		//�˻��ϰ� ���� ��ǰ�� �̸��� �Է� �޾� �˻�. (�κ���ġ �˻��...)
		
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver"); //�� Ŭ���� �̸� ������ �� ã��.
			System.out.println("����Ŭ JDBC ����̹� �ε� ����");
			String sql = "select* from goodsinfo " +"where name like '%" +product +  "%' " + "order by" + orderBy;
			System.out.println(sql);
		} catch (ClassNotFoundException e) {
			System.err.println("����̹� �ε� ����" + e.getMessage());
		}

		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		//�α��ο� ���� // sql��������� ��� ��û
		try (Connection conn = (Connection) DriverManager.getConnection(url, "iot", "1234");
				Statement stat = (Statement) conn.createStatement();
				ResultSet rs = stat.executeQuery("select code, name, price, maker from goodsinfo")){
			//System.out.println("������ ���̽��� �����߽��ϴ�.");
			System.out.println("��ǰ �ڵ� ��ǰ�� \t\t ���� ������");
			System.out.println("---------------------------------------");
			while(rs.next()) {
				
				String code = rs.getString("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				String maker = rs.getString("maker");
				System.out.printf("%8s %s \t%12d %s%n", code, name, price, maker); //�� �������� ����϶��� ��.
			}
		
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		
		
		
		
	//	boolean exist = rs.next(); // ���� ����� ���� �� ��ġ�� �̵��ϴ� �޼ҵ�
		// �� ���������� ���� false�� ���ϵ�. ù��° ȣ���ߴµ� false�� ���ٸ� ����Ʈ �� ���� ���ٴ°�. 
		

	}
}
