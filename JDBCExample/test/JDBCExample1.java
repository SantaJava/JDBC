import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample1 {
	public static void main(String[] args) {	
		Scanner sc = new Scanner(System.in);
		System.out.println(" 상품명 : ");
		String product = sc.nextLine();
		System.out.println("정렬 기준: ");
		String orderBy = sc.nextLine();
	
		//검색하고 싶은 상품의 이름을 입력 받아 검색. (부분일치 검사로...)
		
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver"); //이 클래스 이름 가지는 것 찾기.
			System.out.println("오라클 JDBC 드라이버 로드 성공");
			String sql = "select* from goodsinfo " +"where name like '%" +product +  "%' " + "order by" + orderBy;
			System.out.println(sql);
		} catch (ClassNotFoundException e) {
			System.err.println("드라이버 로드 실패" + e.getMessage());
		}

		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		//로그인에 성공 // sql실행시켜줘 라고 요청
		try (Connection conn = (Connection) DriverManager.getConnection(url, "iot", "1234");
				Statement stat = (Statement) conn.createStatement();
				ResultSet rs = stat.executeQuery("select code, name, price, maker from goodsinfo")){
			//System.out.println("데이터 베이스에 접속했습니다.");
			System.out.println("상품 코드 상품명 \t\t 가격 제조사");
			System.out.println("---------------------------------------");
			while(rs.next()) {
				
				String code = rs.getString("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				String maker = rs.getString("maker");
				System.out.printf("%8s %s \t%12d %s%n", code, name, price, maker); //이 포멧으로 출력하라라는 것.
			}
		
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		
		
		
		
	//	boolean exist = rs.next(); // 실행 결과의 다음 행 위치로 이동하는 메소드
		// 맨 마지막까지 가면 false가 리턴됨. 첫번째 호출했는데 false가 떴다면 셀렉트 된 행이 없다는것. 
		

	}
}
