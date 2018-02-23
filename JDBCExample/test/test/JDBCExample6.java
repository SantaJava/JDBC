package test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JDBCExample6 {
	public static void main(String[] args) {
		if(args.length!= 4) {
			System.out.println("Usage: java JEBCExample4 ��ǰ�ڵ� ��ǰ�� ���� ������");
			return;
		}
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String sql = "insert into goodsinfo(code ,name ,price ,maker)"+
					"values(?,?,?,?)";
		
		try(Connection conn = (Connection)DriverManager.getConnection(url, "iot", "1234");
			PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, args[0]);
			pstmt.setString(2, args[1]);
			pstmt.setInt(3, Integer.parseInt(args[2]));
			pstmt.setString(4, args[3]);
			
			int rowNum = pstmt.executeUpdate();
			System.out.println(rowNum + "���� �߰��Ǿ����ϴ�.");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}