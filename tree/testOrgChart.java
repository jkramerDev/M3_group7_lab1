package tree;

import tree.OrgChart;
import tree.OrgChartImpl;

public class testOrgChart {

	public static int DEPTH_FIRST_TEST = 0;
	public static int BREADTH_FIRST_TEST = 1;

	static Employee e1;
	static Employee e2;
	static Employee e3;
	static Employee e4;
	static Employee e5;
	static Employee e6;
	
	public static void main(String[] args) {

		// Fill the org chart
		build_employees();

		// Show breadth first
		System.out.println("\nBREADTH_FIRST_TEST");
		TEST_TREE(BREADTH_FIRST_TEST);

		// Depth first
		System.out.println("\nDEPTH_FIRST_TEST");
		TEST_TREE(DEPTH_FIRST_TEST);

	}
	
	public static void build_employees() {
		e1 = new Employee();
		e1.setId(100);
		e1.setName("Rob");
		e1.setPosition("CEO");

		e2 = new Employee();
		e2.setId(200);
		e2.setName("Todd");
		e2.setPosition("Manager");

		e3 = new Employee();
		e3.setId(300);
		e3.setName("Sally");
		e3.setPosition("Worker");

		e4 = new Employee();
		e4.setId(400);
		e4.setName("Ralph");
		e4.setPosition("Manager");

		e5 = new Employee();
		e5.setId(500);
		e5.setName("Ronnie");
		e5.setPosition("Worker");

		e6 = new Employee();
		e6.setId(600);
		e6.setName("Tim");
		e6.setPosition("Manager");

	}

	public static void TEST_TREE(int type) {
		OrgChart orgChart = new OrgChartImpl();
		orgChart.addRoot(e1);
		orgChart.addDirectReport(e1, e2);
		// Todd reports to rob

		print(orgChart, type);

		orgChart.addDirectReport(e2, e3);
		// Sally reports to Todd

		print(orgChart, type);

		orgChart.addDirectReport(e1, e4);
		// Ralph reports to Rob

		print(orgChart, type);

		orgChart.addDirectReport(e4, e5);
		// Ronnie reports to Ralph

		print(orgChart, type);
		orgChart.addDirectReport(e1, e6);
		// Tim reports to Rob

		print(orgChart, type);
		orgChart.removeEmployee(e2);
		// Remove Todd
		// Sally should then report to Rob

		System.out.println("--- Todd removed --- ");
		print(orgChart, type);

	}

	static void print(OrgChart orgChart, int flag) {
		System.out.println("- - - ");

		if (flag == BREADTH_FIRST_TEST) {
			orgChart.showOrgChartBreadthFirst();
		}
		else {
			orgChart.showOrgChartDepthFirst();
		}

		System.out.println("- - - ");
		System.out.println(" ");
	}

}
