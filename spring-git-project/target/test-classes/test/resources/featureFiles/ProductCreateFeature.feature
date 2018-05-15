Feature: Productms CreateProduct

@soapui    
Scenario Outline: Create Product failed missing of bar code
  Given I have a createProduct service
  When I call createProduct service for a product with barcode missing with <id>,<productName>,<description>,<mfgdate>,<sku>,<amount>
  Then I get a response code of "200"
  And I get  a boolean status as false
  
  Examples: Failure Parameters 
    |id | productName | description | mfgdate      | sku     | amount |
    | 9 | "Product9"  | "Product9"  | "10/10/2006" | "abcd1" | 0      |
    
    
Scenario Outline: Create Product failed missing data in warehouse
  Given I have a createProduct service
  When I call createProduct service for a product  missing data in warehouse with <id>,<productName>,<description>,<mfgdate>,<sku>,<amount>
  Then I get a response code of "200"
  And I get  a boolean status as false
  
 Examples: Failure Parameters 
    |id | productName   | description   | mfgdate        | sku     | amount |
    | 1 | "ProductX"    | "ProductX"    | "10/10/2006"   | "abcd1" | 0      |

Scenario Outline: Create Product valid input
  Given I have a createProduct service
  When I call createProduct service with <id>,<productName>,<description>,<mfgdate>,<sku>,<amount>
  Then I get a response code of "200"
  And I get a boolean status as true
  
  Examples: Successfull Parameters 
    |id | productName   | description   | mfgdate    | sku      | amount |
    | 8 | "Product8"    | "Product8"    | "10102006" | "abcd1"  | 0      |


