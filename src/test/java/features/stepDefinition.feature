#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: Exercise "1"b
  
	Background:
		Given initialize browser
    And navigate to the Salesforce page "https://d5e000001pldleas-dev-ed.my.salesforce.com"
    When User enters username "hugo.borgiani@tcs.com" and password "Bilardo049716!2" and logs in
		
  Scenario Outline: "1"- Iteration between tabs in the Salesforce App
   	Given User selects the application <application>
     Then iteration through all the tabs shown clicking in the new and cancel button of the window
      And close the Browser
	
    Examples: 
    	|	application							|
      | "Service"								|
      #| "Marketing"							|
      #| "Community"							|
      #| "Salesforce Chatter"			|
      #| "Content"								|
      #| "Sales Console"					|
     	#| "Service Console"				|
    
  
   Scenario Outline: "2"- Account Record creation
   	Given User selects the application "Service"
   	 Then clicks on the Accounts tab
      And clicks on the new button
     Then fills the form completing all the fields
    	And clicks on the save button
      And close the Browser
  
      
	Scenario Outline: "3" - Account Record creation without filling required fields 
		Given User selects the application "Service"
		 Then clicks on the Accounts tab 
      And clicks on the new button
    	And clicks on the save button
     Then verifies the alert message
    	And close the Browser


	Scenario Outline: "4" - Contact Record creation in a New Tab
		Given User selects the application "Service"
	 	 Then clicks on the Contacts tab and opens a new tab in the browser
			And switches to the new tab
			And clicks on the new button
	 	 Then fills the required fields
			And Saves the record
			And switches to the first tab
			And close the Browser
		
      
	Scenario Outline: "5" - Account Record Modification
    Given User selects the application "Service"
     Then clicks on the Accounts tab
      And cliks on the more options menu
      And selects the edit option
		 Then modifies the Rating, Upsell Opportunity and Type fields
		  And clicks on the save button
		 Then checks that te modification was successful
		  And close the Browser
			      
      
	Scenario Outline: "6" - Account Record Modification filling the Employee field with a wrong Value
  	 Given User selects the application "Service"
      Then clicks on the Accounts tab
       And cliks on the more options menu
       And selects the edit option
      Then Edits the Employee field with the value "1431655766"
       And clicks on the save button
      Then verifies that the alert message displayed is "Employees: value outside of valid range on numeric field: 1431655766"
       And close the Browser
   
      
	Scenario Outline: "7" - Accounts creation using cucumber data provider
		Given User selects the application "Service"
		 Then clicks on the Accounts tab
      And clicks on the new button
     Then fills the Account Name <Account Name>, Phone <Phone>, Account Number <Account Number> and Website <Website> fields
      And clicks on the save button
      And close the Browser
      
      Examples:
      | Account Name 		| Phone	  	| Account Number 	 | Website 									 |
      | "Gaby"			  	|"98765432" | "123457"			 	 | "https://www.facebook.com"|
      | "Samir"			  	|"98765431" | "123458"				 | "https://www.google.com"  |
      | "Baloo"			  	|"98765430"	| "666"				 		 | "https://es.wikipedia.org"|
      | "Nestor"			  |"45678910" | "123459"				 | "https://www.youtube.com" |