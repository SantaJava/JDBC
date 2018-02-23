package test;

import java.sql.Connection;
import java.sql.DriverManager;

import main.GoodInfoDao;
import main.View;

public class PaginationExample {
	static final int PER_PAGE = 5;

	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:XE";

		try {
			View view = View.getInstatnce();
			GoodInfoDao dao = new GoodInfoDao();
			int total = dao.getCount();
			int totalPage = (int) Math.ceil((double) total / PER_PAGE);
			int page = 1;

			System.out.printf("Total %d Pages(total %d datas)\n",totalPage, total);

			while (page != -1) {

				page = view.getInt("page : ");

				if (page >= 1 && page <= totalPage) {
					int start = (page - 1) * PER_PAGE;
					int end = start + PER_PAGE - 1;
					// System.out.println(start);
					// System.out.println(end);
					view.printListPage(dao.getGoodInfoPage(start, end), start, end, page, totalPage, total);

				} else if (page == -1) {
					System.out.println("------------------");
					System.out.println("Closed");
					break;
				} else {
					System.out.println("this page doesn't exist");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
}
