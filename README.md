# bigrestaurants
It is provide food services for many things like restaurants and mall and dishes and make relations between them for make user satisfied.

## Content
- [Description](https://github.com/anasabuhussein/bigrestaurants#description)

- [Project Tools](https://github.com/anasabuhussein/bigrestaurants#project-tools)

- [Project Componenets](https://github.com/anasabuhussein/bigrestaurants#project-componenets)

- [Uses](https://github.com/anasabuhussein/bigrestaurants#uses)

- [Future Work](https://github.com/anasabuhussein/bigrestaurants#future-work)

- [Endpoints](https://github.com/anasabuhussein/bigrestaurants#endpoints)

- [Pic](https://github.com/anasabuhussein/bigrestaurants#pic)

- [Auther](https://github.com/anasabuhussein/bigrestaurants#auther)

### Description
>This project provide api service (Json and xml) for multi restaurants want to show them dishes and users orders this dishes; can user orders (Ingredients of the dish) from malls; The user can orders the (ingredients of the dish) from mall; the project provide location of user; the project shall provide follow path of delivery man.

### Project Tools
	- Spring Boot frameWorks.
	- MongoDB.
	- Rest API. 
	
### Project Componenets
	- Malls
	- Restaurans
	- Users and Delivery Man
	- Orders For Dishes
	- Orders For Malls
	
### Uses 
>The poject can use by phone platforms or web platform for access to this services; and use to increase user  of restaurants and malls and provide best service from them and make user are satisfied.

### Future Work
> Make project support malls api service and delivery man service.
and add it to project and endpoints.

### Endpoints
	* Dishes : 
	  * dishes : GET	
		 * /api/v1/dishes
		 * /api/v1/dishes/{id}
		 * /api/v1/dishes/name/{name}
	  
	  * dishes : POST
		 * /api/v1/dishes
		 * /api/v1/dishes/{id}/comments
	  	    
	  * dishes : PUT, DELETE
	    * /api/v1/dishes/{id}
	    * /api/v1/dishes/name/{name}
	    * /api/v1/dishes/{id}/comments
	    
	* Users : 
	  * users : GET
	    * /api/v1/users
	    * /api/v1/users/{id}
	    * /api/v1/users/name/{name}
	    
	  * users : POST
	    * /api/v1/users
	    
	  * users : PUT, DELETE
	    * /api/v1/users/{id}
	    
	* Orders of Dishes : 
	  * orders : GET
	    * /api/v1/dishes/orders
	    * /api/v1/dishes/orders/{id}
	    * /api/v1/dishes/orders/restaurant/{name}
	    
	  * orders : POST
	    * /api/v1/dishes/orders
	    * /api/v1/dishes/orders/restaurant/{name}
	    
	  * orders : PUT, DELETE
	  	 * /api/v1/dishes/orders/{id}
	    * /api/v1/dishes/orders/{id}/restaurant/{name}
	    
### Pic
[GitHub Logo](/images/logo.png)
	    
### Auther 
> ** - Anas Abu Hussein : Fullstack Developer **


 		
