# Glossary #

| **Term**                      | **Definition**                                                                        |
|:------------------------------| :----------------------------------------------------------------------------------- |
| Accessibility                 | it's a value object that represents the accessibility of the aisles where the AGV's can take out the packages ("orders").
| Administrator                 | responsible for managing system users (e.g. creation, activation, granting permissions) and general system maintenance and configuration.          
| Address                       | it's a value object that represents an address.
| AggregateCustomer             | it's an aggregate that contains customer's entity as well as the respective value objects.
| AggregateOrder                | it's an aggregate that contains order's entity, ShipmentMethod entity and Status entity as well as the respective value objects.
| AggregateProductCategory      | it's an aggregate that contains the product's category entity and the product's entity as well as the respective value objects.
| AggregateQuestion             | it's an aggregate that contatins question and questionType entity. It also has Question's value object and QuestionType's value object.
| AggregateQuestionnaire        | it's an aggregate that contains the questionnaire, section and question entity. Its also shows the relations with survey entity.
| AggreateSurveys               | It's an aggregate that contains the Survey and Questionnaire entity. It also shows the relation with User and Customer.
| AggregateUser                 | it's an aggregate that contains user's entity as well as the respective value objects.
| AggregateAGV                  | it's an aggregate that contains the AGV entity as well as the respective value objects.
| AGV                           | it's an entity that represents an automated guided vehicle responsible for the transport orders inside the WareHouse.
| AGVDocks                      | it's an entity that represents the base location of an AGV where can he can charge it's battery.
| Aisles                        | it's an entity that represents an aisles ("shelf") that contains rows with the objective to store packages ("orders").
| AnswerSortingOptions          | it's a value object that represents the sorting options question type.
| AnswerSingleAndMultipleChoice | it's a value object that represents the single and multiple choice question type.
| AnswerScalingOptions          | it's a value object that represents the scaling options question type.
| AnswerFreeTextAndNumeric      | it's a value object that represents the freeText and numeric question type.
| Autonomy Hours                | it's a value object that represents the amount of time that the AGV can be moving. 
| Birthdate                     | it's a value object that represents the user birthdate.
| Brand                         | it's a value object that represents a text with 50 chars maximum representing product's brand. 
| Code                          | it's a value object that represents the alphanumeric code of a category.
| Customer                      | it's an entity that represents a person that frequently access the system to browse and search for products with the intent of placing orders.
| Date                          | it's a value object that represents a date.
| Description                   | it's a value object that represents a text that describes an object. 
| Email                         | it's a value object that represents the email of a user.
| ExtendedDescription           | it's a value object that represents a text  with a minimum of 20 chars and 100 chars maximum describing the product. 
| FreeTextAndNumeric            | it's a value object that represents the freeText or numeric question type.
| Gender                        | it's a value object that represents the user's gender.
| Max Volume                    | it's a value object that represents the amount of volume the AGV can transport.
| Max Weight                    | it's a value object that represents the amount of weight the AGV can transport.
| Name                          | it's a value object that represents the user name.
| Option                        | it's a value object that represents an option that the customer can choose in a questionnaire.
| Order                         | it's an entity that represents the products that a client purchases. 
| Password                      | it's a value object that represents the password of a user.
| PhoneNumber                   | it's a value object that represents the user phone number.
| Photo                         | it's a value object that represents an image file with the regular formats (e.g png, jpeg, svg).)
| Position                      | it's a value object that represents the position of the AGV.
| Product                       | it's an entity that represents an item from the catalog.
| Product Category              | it's an entity that represents a category of products from the catalog.
| Product Code                  | it's a value object that represents a alphanumeric code with at 23 chars maximum.
| Price                         | it's a value object that represents an amount of money.
| Question                      | it's an entity that represents a question that the customer will be able to answer.
| Questionnaire                 | it's an entity that represents a questionnaire that intends to a survey.
| QuestionType                  | it's an entity that represents all the questions types.
| Reference                     | it's a value object that represents an alphanumeric code with at 23 chars maximum.
| Rows                          | it's an entity that represents the rows on a Aisle where is stored orders.
| ScalingOptions                | it's an entity that represents the scaling options question type.
| Section                       | it's an entity that represents a part of the questionnaire that can have one or multiple questions.
| ShipmentMethod                | it's an entity that represents the shipment method of an order.
| ShortDescription              | it's a value object that represents a text with 30 chars maximum describing the text.
| SingleAndMultipleChoice       | it's a value object that represents the single and multiple choice question type.
| SortingOptions                | it's a value object that represents the sorting options question type.
| Status                        | it's an entity that represents the state of a specific process.
| StatusAGV                     | it's a value object that represents the current status of an AGV.
| StatusTask                    | it's a value object that represents the current status of an Task.
| Survey                        | it's an entity that represents a survey that will be given to a specific Customer.
| Task                          | it's an entity that represents a responsibility of actions done by an AGV.
| TechnicalDescription          | it's a value object that represents multiple lines of text describing the product.
| Value-Added Tax (VAT)         | it's a value object that represents a costumer Value-Added Tax (VAT).
| User                          | it's an entity that can represent people or external systems that access the system and use it in diverse ways.
| User role                     | it's a value object that represents the role given to a user in the system.
| WarehouseLocation             | it's a value object that represents the warehouse location of the specific product.


