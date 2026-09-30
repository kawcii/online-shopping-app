# Online Shopping Mobile Application

## Project Title
 Big V SuperMarket Mobile Shopping Application

## Student Names
Course: IS223 Object Oriented Programming
Institution: Papua New Guinea Univeristy of Technology

Group Members: Joseph.SEETO  ID#: 25530061
                Theodorah.ROY  ID#:
                Michael.FAN   ID#:
                Dasha.TELIKADAH ID#: 25530085
                
                
## Project Description

    This project is a mobile shopping app for a BIG V Supermarket in Morobe Province, lae TopTown.
    The SuperMarket sells variety of products that fits into the categories: Groceries, Fresh produce, Fresh meat, Fashion/Clothing, liquor, Electronics and Bakery
    However, my group chose only four categories: Groceries, Electronics, Clothing, and Household since it is oberseved to be bought by majority of customers when they shop.

    The purpose of this application is to help customers locate 

## Application Objectives
The objectives of the project are to:
1.	Develop an Android mobile application for BIG V Supermarket.
2.	Provide customers with a digital product catalogue.
3.	Organize products into different supermarket categories.
4.	Allow users to select products and add them to a shopping cart.
5.	Maintain selected products while users navigate between activities.
6.	Calculate the total value of products in the shopping cart.
7.	Collect customer name, telephone number, and delivery location.
8.	Implement a simulated checkout process.
9.	Apply Java Object-Oriented Programming principles.
10.	Develop XML-based Android user interfaces.
11.	Test the main application workflow.
12.	Provide a user-friendly prototype for future development.


## Development Tools

The application was developed using Java, Android SDK, Jetpack, XML layouts, and Material 3 design guidelines.

| Tool / Technology | Purpose |
|---|---|
| **Java** | Main programming language used for the app logic and activities |
| **Android SDK** | Provides the APIs and tools needed to build and run the Android app |
| **Jetpack** | Android libraries used to structure the app and simplify development |
| **XML Layouts** | Used to design the user interface of each screen |
| **Material 3** | Design guidelines and components used for a consistent, modern look |
| **Android Studio** | IDE used to write, build and test the app |
| **Git and GitHub** | Version control and team collaboration |
| **Android Emulator / Device** | Used to run the app and carry out testing |

## Main Features

- **Welcome Screen:** An introductory screen that opens the app and leads to the home page.
- **Home Screen:** A central hub with access to Categories, Products and the Cart.
- **Category Browsing:** Products are organised into categories (Grocery, Electronics and Clothing).
- **Product Listing:** Shows all items in a chosen category, or all products together.
- **Product Details:** Displays the selected item's name, price, image and description.
- **Add to Cart:** Users can add items to their shopping cart.
- **Cart Management:** Users can view, update quantities of, and remove items in the cart.
- **Automatic Total Calculation:** The cart total updates when items or quantities change.
- **Checkout:** Users enter their details and review the order before submitting.
- **Order Confirmation:** A final screen confirms that the order was placed successfully.
- **Material 3 Interface:** A clean, consistent design built with XML layouts.

## OOP Concepts Demonstrated

| OOP Concept | How it is implemented in the project |
|---|---|
| **Encapsulation** | `Product` keeps `productName`, `price` and `category` private and exposes them through getters and setters. `Customer` also keeps the customer's name, telephone number and delivery location together in one class. |
| **Inheritance** | `Grocery`, `Electronics`, `Clothing` and `Household` all extend the parent class `Product`. Each adds its own field: `unitType`, `warrantyMonths`, `size` and `fragile`. |
| **Polymorphism** | The subclasses override the `displayProduct()` method from `Product`, so each product type can display itself differently. |
| **Composition** | The `Order` class is built from a `Customer` object and a `Cart` object. |
| **Singleton Pattern** | `CartManager` holds one shared `Cart` (`sharedCart`), so the cart stays available as the user moves between activities. |

## Installation Instructions
[Steps to clone, open, and run the project]

## Screenshots
[Screenshots of the 8 app screens]
