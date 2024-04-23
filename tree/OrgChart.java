package tree;

public interface OrgChart {
	
	// If there is no orgchart, start it
	public void addRoot(Employee e); 
	
	// Get rid of the org chart
	public void clear();
		
	// Add the newPerson as a direct report (child) of manager
	public void addDirectReport(Employee manager, Employee newPerson);
	
	// Remove the employee, give their direct reports to their supervisor
	public void removeEmployee(Employee firedPerson);
		
	public void showOrgChartDepthFirst(); 
	
	public void showOrgChartBreadthFirst();
	
}
