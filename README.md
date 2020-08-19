## HostelApp

This project is my final work during Kodilla Java Boot Camp. 
The app try to solve a real business problem, a property management of hostel. 

### Main Features

* providing information about how many free beds is in each room in the hostel, by:
    * adding Resident to Room and subtract count of beds
    * Removing Resident from Room after CheckOut 
* counting value of total payment for the stay
* providing automatically Euro exchange ratio from external API few time per day 
(overwriting the previous value - > 
in case of problem with external API there is some Euro exchange ratio in DB)
*  providing basic information about Resident in relational with Reservation

### Other Features

* current weather in location of hostel - just for fun
  
### Technologies

* Back-end:
    * Java 11
    * Spring-Boot 2.0
    * Hibernate
    * JUnit 5
* Front-end:
    * N/A
* Patterns:
    * Builder  
    
### How to run this App

This app can't be run as-is. 
You have to create your own application instance in the IDE and run.

## License

This project was made for training purpose. 
If you find any bugs please contact me.
MIT License.

### Upcoming release

* FlyWay migration

## Author

zxkuba