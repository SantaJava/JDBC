import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GoodInfoDao {
	private Connection conn;

	public GoodInfoDao(Connection conn) {
		super();
		this.conn = conn;
	}

	public GoodInfo getGoodInfo(Connection conn, String code) throws Exception {
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

	public List<GoodInfo> getGoodInfoList(Connection conn) {
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

	public GoodInfo map(ResultSet rs) throws SQLException {

		GoodInfo goodInfo = new GoodInfo();
		goodInfo.setCode(rs.getString("code"));
		goodInfo.setName(rs.getString("name"));
		goodInfo.setPrice(rs.getInt("price"));
		goodInfo.setMaker(rs.getString("maker"));

		return goodInfo;
	}
}
