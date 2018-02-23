package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GoodInfoDao {
	private Connection conn;

	//필요없어짐
//	public GoodInfoDao(Connection conn) {
//		super();
//		this.conn = conn;
//	}
	
	public GoodInfoDao() { //커넥션을 얻는다.
		conn = ConnectionProvider.getConnection();
	}

	// 전체 데이터 수 확인.
	public int getCount() throws Exception {
		String sql = "select count(*) total from goodsinfo";
		try (Statement stmt = (Statement) conn.createStatement(); ResultSet rs = stmt.executeQuery(sql);) {
			rs.next();
			// return rs.getInt(1); //첫번째 rs는 항상 true. group하면 항상 1행 나오니까. 첫번째 칼럼을 달라는 것~
			return rs.getInt("total");
		}
	}

	public GoodInfo getGoodInfo(String code) throws Exception {
		GoodInfo goodInfo = null;
		// 보통 sql한개당 하나의 메소드로 호출.
		String sql = "select * from goodsinfo " + "where code = '" + code + "'";

		try (Statement stmt = (Statement) conn.createStatement(); ResultSet rs = stmt.executeQuery(sql);
		// rs : 쿼리 문을 수행해서 나오는 결과 행 (여기서 code가 primary key이기 때문에 하나이거나 null이다.)
		) {
			// 순수하게 모델객체를 만드는데 집중해야 함.
			if (rs.next()) { // primary key 가 하나거나 없거나 이기 때문에, if, else문으로 묶음.
				goodInfo = map(rs);
			}
		}
		return goodInfo;
	}

	// 이제 안쓴다.
	// public GoodInfo getGoodInfo(Connection conn, String code) throws Exception {
	// GoodInfo goodInfo = null;
	// // 보통 sql한개당 하나의 메소드로 호출.
	// String sql = "select * from goodsinfo " + "where code = '" + code + "'";
	//
	// try (Statement stmt = (Statement) conn.createStatement(); ResultSet rs =
	// stmt.executeQuery(sql);
	// // rs : 쿼리 문을 수행해서 나오는 결과 행 (여기서 code가 primary key이기 때문에 하나이거나 null이다.)
	// ) {
	// // 순수하게 모델객체를 만드는데 집중해야 함.
	// if (rs.next()) { // primary key 가 하나거나 없거나 이기 때문에, if, else문으로 묶음.
	// goodInfo = map(rs);
	// }
	// }
	// return goodInfo;
	// }

	public List<GoodInfo> getGoodInfoList() {
		List<GoodInfo> list = new ArrayList<>();
		String sql = "select* from goodsinfo";
		GoodInfo goodInfo = null;

		try (Statement stmt = (Statement) conn.createStatement(); ResultSet rs = stmt.executeQuery(sql);)// 한개의 행.
		{
			while (rs.next()) { // rs.next()할 때마다 다음행 호출. 그 다음행이 없을 때 종료.

				list.add(map(rs));
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return list;
	}

	// goodinfolist공사중
	public List<GoodInfo> getGoodInfoPage(int start, int end) throws Exception {
		List<GoodInfo> list = new ArrayList<>();
		String sql = "SELECT * " + "FROM ("
				+ "SELECT row_number() over(order by code) as seq, code, name, price, maker " + "FROM goodsinfo)"
				+ " WHERE seq BETWEEN ? AND ? ";

		GoodInfo goodInfo = null;

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					goodInfo = map(rs);
					list.add(goodInfo);
				}
				return list;
			}
		}
	}

	public GoodInfo map(ResultSet rs) throws SQLException {

		GoodInfo goodInfo = new GoodInfo();
		goodInfo.setCode(rs.getString("code"));
		goodInfo.setName(rs.getString("name"));
		goodInfo.setPrice(rs.getInt("price"));
		goodInfo.setMaker(rs.getString("maker"));

		return goodInfo;
	}

	public int insert(GoodInfo goodInfo) throws Exception {

		String sql = "insert into goodsinfo " + "(code,name,price,maker) " + "values(?,?,?,?)";

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, goodInfo.getCode());
			pstmt.setString(2, goodInfo.getName());
			pstmt.setInt(3, goodInfo.getPrice());
			pstmt.setString(4, goodInfo.getMaker());

			// return문까지 왔다는 것은 이제 종료된다는 것 ! 그 전에 일어난 오류는 전부 exception으로 들어감.
			return pstmt.executeUpdate();
		}
	}

	public int update(GoodInfo goodInfo) throws Exception {

		String sql = "update goodsinfo set " + "name = ?," + "price = ?," + "maker = ?" + " where code = ?";

		// where앞에 콤마 없는 것 주의!
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, goodInfo.getName());
			pstmt.setInt(2, goodInfo.getPrice());
			pstmt.setString(3, goodInfo.getMaker());
			pstmt.setString(4, goodInfo.getCode());

			return pstmt.executeUpdate();
		}
	}

	public int delete(String code) throws Exception {

		code = String.format("%-5s", code);

		String sql = "delete from goodsinfo " + "where code = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, code);
			return pstmt.executeUpdate();
		}
	}
}
