Feature: Productms GetProduct

@soapui    
Scenario Outline: Get Product detail for customer of Type1
  Given I have a getProduct service
  When I call getProduct service for customerTypeone with <id> and <customerType>
  Then I get a response code of "200"
  And I get a Product json response with date in format YYYY/MM/DD
  
  Examples: Successfull Parameters 
    |id | customerType |
    | 1 | 1 |
  
Scenario Outline: Get Product detail for customer of Type2
  Given I have a getProduct service
  When I call getProduct service for customerTypetwo with <id> and <customerType>
  Then I get a response code of "200"
  And I get a Product json response with date in format MM/DD/YY
  
  Examples: Successfull Parameters 
    |id | customerType |
    | 1 | 2 |
    


