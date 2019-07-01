# SOEN387
A project where the main focus was to split tasks in different paterns. 
There are 3 main layer of the web service. 
-Application : where user's request is processing and the respond is being send back. 
-Domain: the request is being processed here and so is the responce. The instantiation of nessary objects is happining here.
-Technical Services: This is the lowest layer and it is used to load or save the information to or from databases.
Each layer is using different paterns such as:
Front Controlers, Dispatcher and Template View for Application layer.
Commands, Unit of Work, Factory, Input and Output Mappers, Proxy for Domain layer.
Table Date Gateaway and Finder for Technical Services.

This project was using library SoenEA which is Proffessor's Stuard Thiel library that we had to use in order to achieve success of this project. 


