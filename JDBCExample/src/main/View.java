package main;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class View {
	// UI/UX 문이므로 scanner 를 준다.
	Scanner scanner;

	// Singletone Pattern
	private View() {
		scanner = new Scanner(System.in);
	}

	private static View view = new View();

	public static View getInstatnce() {
		return view;
	}

	public String getString(String msg) {
		System.out.println(msg);
		String line = scanner.nextLine();

		return line;
	}

	public int getInt(String msg) {
		System.out.println(msg);
		int input = scanner.nextInt();
		scanner.nextLine(); // 개행 문자 제거.

		return input;
	}

	public GoodInfo getGoodInfo() {

		String code = getString("Product Code : ");
		String name = getString("Product Name : ");
		int price = getInt("Product price: ");
		String maker = getString("Product Maker: ");

		return new GoodInfo(code, name, price, maker);
	}

	// 기존에 있는 값을 수정하는 getGoodInfo overwrite
	public GoodInfo getGoodInfo(GoodInfo g) throws Exception { //edit

		String prompt;
		System.out.printf("ProductCode[%s] Edit %n", g.getCode());
		// 보통 sql한개당 하나의 메소드로 호출.
		prompt = String.format("ProductName [%s] : ", g.getName()); // 기존 제품명 출력.
		String name = getString(prompt); // 엔터쳤을 떄 : 비어있는 문자가 전달 됨.

		if (name.isEmpty()) { // edit name.
			name = g.getName();
		}

		prompt = String.format("ProductPrice [%d] : ", g.getPrice());
		int price = getInt(prompt);
		if (price == -1) { // edit name.
			price = g.getPrice(); // 기존 가격 그대로 가져가겠다.
		}

		prompt = String.format("ProductMaker [%s] : ", g.getMaker());
		String maker = getString(prompt);
		if (maker.isEmpty()) { // edit name.
			maker = g.getMaker();
		}
		return new GoodInfo(g.getCode(), name, price, g.getCode());
	}

	public void printList(List<GoodInfo> list) {
		System.out.println("상품코드 상품명 \t\t 가격 제조사 ");
		System.out.println("------------------------------");

		for (GoodInfo info : list) {
			System.out.printf("%5d %8s %s \t%12d %s%n", info, info.getCode(), info.getName(), info.getPrice(), info.getMaker());
		}
		System.out.println("-----------------------------");
	}
	
	//페이지리스트 공사중
//	public void printPageList(List<GoodInfo> list) {
//		System.out.println("상품코드 상품명 \t\t 가격 제조사 ");
//		System.out.println("------------------------------");
//
//		for (GoodInfo info : list) {
//			System.out.printf("%8s %s \t%12d %s%n", info.getCode(), info.getName(), info.getPrice(), info.getMaker());
//		}
//		System.out.println("-----------------------------");
//	}


	public void printListPage(List<GoodInfo> list, int start, int end, int page, int totalPage, int total) {

		System.out.println("  Code Name \t\t Price Maker");
		System.out.println("------------------------------");
		int count =1;
		
		for (GoodInfo info : list) {
			System.out.printf("%d %8s %s \t%12d %s%n", count, info.getCode(), info.getName(), info.getPrice(), info.getMaker());
			count++;
		}
		
		System.out.println("-----------------------------");		
		System.out.printf("%d page(%d~%d)\n", page, start, end);
		System.out.printf("%d/%d(total %d datas) \n", page, totalPage, total);
		System.out.println();		
	}
}
