Feature: Add New Employee

  @AddSingleEmployee @Sanity
  Scenario: Add employee with valid details
    Given I am logged into OrangeHRM as an admin with Username "Admin" and Password "admin123"
    And I navigate to the PIM module
    When I click on Add Employee
    When I enter employee details
  | firstName | middleName | lastName | empId  |
  | Deepak    | Kumar      | R        | EMP2509|
    And I click on Save
    Then the employee should be added successfully
    
   #@AddMultipleEmployee 
  Scenario: Add employee with valid details
    Given I am logged into OrangeHRM as an admin with Username "Admin" and Password "admin123"
    And I navigate to the PIM module
    When I enter employees detail then click on Add and Save
  | firstName | middleName | lastName | empId  |
  | Deepak    | Kumar      | R        | EMP1230|
  | Arjun     | Srinivasan | S        | EMP1231|
  | Harish    | Baskar     | B        | EMP1232|
  
    Then the employee should be added successfully
    
    @SearchEmployee @Sanity
    Scenario: Search existing employee by name
    Given I am logged into OrangeHRM as an admin with Username "Admin" and Password "admin123"
    And I navigate to the PIM module
    When I navigate to the Employee List
    And I search for employee with name "Charles"
    Then the employee record should be displayed for "Charles"
    