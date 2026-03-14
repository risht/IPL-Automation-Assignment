package com.main.iplautomation;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IPLAutomation {

	public static void main(String[] args) {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		WebDriver driver = new ChromeDriver(options);
		
		driver.get("https://www.iplt20.com/points-table/men/2025");
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		
		//Step 1: Find the entire web table

		
		By tableLocator = By.className("ih-td-tab");
		
		WebElement table= wait.until(ExpectedConditions.visibilityOfElementLocated(tableLocator));
		
		//Step 2 : Locate the tbody with chaining of webelement
		
		By tbodyLocator = By.id("pointsdata");
		
		WebElement tbody = table.findElement(tbodyLocator);
		
		
		//Step 3: Find all table rows
		
		By tableRowLocator = By.tagName("tr");
		
		List<WebElement> tableRowList=tbody.findElements(tableRowLocator);
		
		//System.out.println(tableRowList.size());
		
		
		List<Team> teamList = new ArrayList<Team>();
		
		for(WebElement row:tableRowList) 
		{
			
			By tableDataRowLocator = By.tagName("td");
			
			List<WebElement> tableDataList= row.findElements(tableDataRowLocator);
			
			//for(WebElement tableData:tableDataList)
			{
				//System.out.println(tableData.getText());
		
			
			
				Team team = new Team(tableDataList.get(0).getText(),
					    tableDataList.get(2).getText(),
					    Double.parseDouble(tableDataList.get(7).getText()), 
					    Integer.parseInt(tableDataList.get(10).getText()));

				teamList.add(team);	
		
				
			}
			
		}
		
//			for(Team data:teamList){
//			{
//				System.out.println(data);
//			}
		
//			teamList.stream().forEach(i->System.out.println(i));
			
			
//			teamList.stream()
//			.map(i->i.getTeamName())
//			.forEach(i->System.out.println(i));
//		
//			
//			
//			teamList.stream()
//			.map(i->i.getPoints())
//			.forEach(i->System.out.println(i));
			
			//Filteration
			
//			teamList.stream()
//			.filter(i->i.getPoints()==19)
//			.map(i->i.getTeamName())//Stream <Team> to Stream<String>
//			.forEach(i->System.out.println(i));
			

			int maxpoints=teamList.stream()
			.mapToInt(i->i.getPoints())
			.max()
			.orElse(0);
			System.out.println(maxpoints);
			

			
			teamList.stream()
			.filter(i->i.getPoints()==maxpoints)
			.map(i->i.getTeamName())//Stream <Team> to Stream<String>
			.forEach(i->System.out.println(i));			
		
	
			Team maxnrr=teamList.stream()
			.filter(i->i.getPoints()==maxpoints)
			.max(Comparator.comparingDouble(i->i.getNrr()))
			.orElse(null);
			System.out.println(maxnrr);
	
	}
		
	}
	
	
	

