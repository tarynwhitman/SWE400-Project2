# Project2SWE400 - Team Huo No

**UI & Commands - Dan, Josh K, Madeline, Adam**
**Domain Objects - Marlee & Taryn**
**Mappers - Ace & Joshua B**

*Story 1: A user should be able to create an element that gets added to the data source. - due 10/11*
* create a UI - 10/11
* create an ExecuterForCommands class that calls execute on proper command - 10/10
* create a Command interface - 10/10
* create the concrete command AddElementCmd - 10/10
* create an Element domain object class - 10/11
* create an ElementMapperInterface - 10/11
* create an ElementMapper for each inheritance pattern - 10/11
* method in ElementMapper to create an element


*Story 2: A user should be able to modify how many moles we have of a chemical.* - **due 10/11**
* Create a UI - 10/11
* Create an ExecuterForCommands class that calls execute on proper command - 10/10
* Create a Command interface - 10/10
* Create the concrete command ModifyChemicalAmountCmd - 10/10
* Create a Chemical domain object class - 10/11
* Create a ChemicalMapperInterface - 10/11
* Create a ChemicalMapper for each inheritance pattern - 10/11
* method to modify the amount of a chemical


*Story 3: A user should be able to modify the atomic number of an element.* - **due 10/12**
* Create components in the UI to choose element and input new atomic number
* Create command to modify atomic number
* Add that command to executer 
* Add finder constructor to mapper
* create components in ui to choose element and input new atomic number


*Story 4: A user should be able to specify what acid dissolves a particular metal.* - **due 10/12**
* Create a UI - 10/12
* Create an ExecuteForCommands class that calls execute on proper command - 10/11
* Create a command interface - 10/10
* Create the concrete command OverwriteAcidToDissolveMetalCmd - 10/11
* Create a Metal domain object class - 10/12
* Create a Metal Mapper interface - 10/12
* Create a concrete Metal class for each inheritance pattern 10/12
* method to set dissolvedBy in Metal


*Story 5: A user should be able to generate a report of which chemicals are low in quantity.* - **due 10/14**
* Create a UI
* Create an ExecuterForCommands class that calls execute on proper command
* Create a Command interface - 10/10
* Create the concrete command GenerateLowChemicalsReportCmd
* Create a Chemical domain object class
* Create a ChemicalMapperInterface
* Create a ChemicalMapper for each inheritance pattern
* method to generate a low chemicals report


*Story 6: A user should be able to add an element to an existing compound.* - **due 10/14**
* Create components in the UI to find compound and write element to add to it
* Create command 
* Add that command to executer 
* Use compound domain object 
* Add method to compound mapper (create if mapper doesnt exist) to add an element to a compound


*Story 7: A user should be able to modify the quantity of an element in an existing compound.* - **due 10/16**
* Create a UI
* Create an ExecuterForCommands class that calls execute on proper command
* Create a Command interface - 10/10
* Create the concrete command ModifyElementInCompoundCmd
* Create a Compound domain object class
* Create a CompoundMapperInterface
* Create a CompoundMapper for each inheritance pattern
* Method to modify the quantity of an element in the compound


*Story 8: A user should be able to remove an element from an existing compound.* - **due 10/16**
* create a UI
* create an ExecuterForCommands class that calls execute on proper command
* create a Command interface - 10/10
* create the concrete command DeleteElementFromCompoundCmd
* create an Compound domain object class
* create a Compound Mapper interface
* create a concrete Compound Mapper for each inheritance pattern
* method to remove an element from the compound


# Project1SWE400 - Team Huo No

Overall Plan:

 -- First Database: finished by Wednesday, September 23

 -- Second Database: finished by Saturday, September 26

 --Third Database: finished by Tuesday, September 29


**Responsibilities**

*Single Table Classes*
*  Connections/Runner (Josh B and Ace) -- 9/21
*  ChemicalDTO(Adam and Madeline) -- 9/21
*  ChemicalTableDataGateway & test class(Josh K and Dan) -- 9/22
*  ChemicalRowDataGateway & test class(Taryn and Marlee) -- 9/23
  
*Concrete Table Classes*
*  Connections/Runner(Josh B and Ace) -- 9/21
*  BaseDTO(Josh B and Ace)
*  BaseRowDataGateway & test class (Josh B and Ace)
*  BaseTableDataGateway & test class(Josh B and Ace)
*  ElementRowDataGateway & test class (Dan and Josh K) -- 9/25
*  ElementTableDataGateway & test class(Dan and Josh K) -- 9/25
*  ElementDTO(Dan and Josh K) -- 9/25
*  MetalRowDataGateway & test class (Dan and Josh K) -- 9/25
*  MetalTableDataGateway & test class (Dan and Josh K) -- 9/25
*  MetalDTO(Dan and Josh K) -- 9/25
*  CompoundDTO(Marlee and Taryn) -- 9/25
*  CompoundMadeOfElement (Marlee and Taryn) -- 9/25
*  CompoundRowDataGateway & test class(Marlee and Taryn) -- 9/26
*  CompoundTableDataGateway & test class(Marlee and Taryn) -- 9/26
*  AcidDTO (Adam and Madeline) -- 9/23
*  AcidRowDataGateway & test class (Adam and Madeline) -- 9/24
*  AcidTableDataGateway & test class (Adam and Madeline) -- 9/25
  
*Class Table Classes*(done by 9/23)
*  Connections/Runner (Josh B and Ace)
*  BaseDTO(Josh B and Ace)
*  BaseRowDataGateway & test class (Josh B and Ace)
*  BaseTableDataGateway & test class(Josh B and Ace)
*  ElementRowDataGateway & test class (Josh K and Dan) -- 9/28
*  ElementTableDataGateway & test class (Josh K and Dan) -- 9/28
*  ElementDTO(Josh K and Dan) -- 9/28
*  MetalRowDataGateway & test class (Josh K and Dan) -- 9/28
*  MetalTableDataGateway & test class(Josh K and Dan) -- 9/28
*  MetalDTO(Josh K and Dan) -- 9/28
*  CompoundDTO (Marlee and Taryn) -- 9/28
*  CompoundMadeOfElement (Marlee and Taryn) -- 9/28
*  CompoundRowDataGateway & test class (Marlee and Taryn) -- 9/29
*  CompoundTableDataGateway & test class (Marlee and Taryn) -- 9/29
*  AcidDTO(Adam and Madeline) -- 9/26
*  AcidRowDataGateway & test class(Adam and Madeline) -- 9/27
*  AcidTableDataGateway & test class(Adam and Madeline) -- 9/28


Notes:
* naming: i.e. metalFindByName, getCompoundID,  etc.
* type numbering system: 0 Chemical, 1 Base, 2 Acid, 3 Element, 4 Metal, 5 Compound
* when you begin working on an issue, move it into the "doing" category and create a branch for it
* when you finish an issue, move it into the "closed" category and submit a merge request for it



**Game Plan**
1.  Determine phase deadlines. [check]
2.  Create all issues for the project. [check]
3.  Create branches as we move issues into "doing" category.
4.  Merge branches as we close issues.
5.  Woohoo :)