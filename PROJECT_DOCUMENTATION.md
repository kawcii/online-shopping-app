# PROJECT TECHNICAL DOCUMENTATION
## BIG V SUPERMARKET MOBILE APPLICATION (IS223 - PNG UNITECH)

**Course:** IS223 — Bachelor of Business in IT (PNG University of Technology)  
**Project Name:** Online Shopping Mobile Application (BIG V SUPERMARKET, Lae City, PNG)  
**Development Team Roles Covered:** Lead Java Developer (OOP classes & architecture) & Systems Analyst / Integration Lead (Cart logic, Java-to-XML wiring, GitHub management)  
**Tech Stack:** Java, Android SDK, Jetpack, XML Layouts, Material 3 Design Guidelines  
**Currency:** Papua New Guinea Kina (K)  
**Version Control:** Single GitHub branch (`master`)  

---

## 1. EXECUTIVE SUMMARY

This technical documentation outlines the architecture, object-oriented design, UI-to-Java binding, cart management, and checkout workflow implemented for the **BIG V Supermarket** Android mobile application. 

Designed for a real supermarket context in Lae City, Papua New Guinea, the application bridges core Object-Oriented Programming principles in Java with declarative Android XML layouts. It respects pre-built UI components and view IDs while delivering a robust, fully wired multi-activity mobile shopping experience.

---

## 2. SYSTEM ARCHITECTURE & COMPONENT MAP

The application follows the Model-View-Controller (MVC) architectural pattern adapted for Android activities, with centralized state management handled via a Singleton pattern (`CartManager`).

```
[ WelcomeActivity ] (Splash Screen)
       │
       ▼
[ HomeActivity ] ──┬──> [ CategoriesActivity ] ──> [ ProductListActivity ] (Filtered)
                   ├──> [ ProductListActivity ] (All Products)
                   └──> [ CartActivity ] ────────> [ CheckoutActivity ] ──> [ OrderConfirmationActivity ]
```

---

## 3. OBJECT-ORIENTED PROGRAMMING (OOP) IMPLEMENTATION

As Lead Java Developer, strict OOP principles were applied across all domain models: **Encapsulation**, **Inheritance**, **Polymorphism**, and **Composition**.

### 3.1. `Product.java` (Parent Class)
- **Encapsulation:** Private fields (`productName`, `price`, `category`) accessed via public getters and setters.
- **Constructors:** 
  - `Product(String productName, double price, String category)`
  - `Product(String productName, double price)` — defaults category to `"General"`.
- **Polymorphism:** Defines base `displayProduct()` method.

### 3.2. Specialized Subclasses (Inheritance & Overriding)
- **`Grocery.java`**: 
  - Represents packaged groceries, fresh produce, and beverages.
  - Adds private `unitType` field (e.g., `"kg"`, `"bag"`, `"litre"`, `"pack"`, `"tin"`).
  - Overrides `displayProduct()`.
- **`Electronics.java`**: 
  - Represents electronics and electrical hardware.
  - Adds private `warrantyMonths` field.
  - Overrides `displayProduct()`.
- **`Clothing.java`**: 
  - Represents apparel and footwear.
  - Adds private `size` field (e.g., `"M"`, `"42"`, `"One Size"`).
  - Hardcodes internal category to `"Apparel & Footwear"` in `super(name, price, "Apparel & Footwear")`.
  - Overrides `displayProduct()`.
- **`Household.java`**: 
  - Represents cleaning supplies, kitchenware, linens, and personal care.
  - Adds private `fragile` boolean field.
  - Overrides `displayProduct()`.

### 3.3. Customer & Order Composition
- **`Customer.java`**: Encapsulates customer name, telephone number, and delivery location in Lae City.
- **`Order.java`**: Composes a `Customer` and a `Cart`, calculates total order value (`cart.calculateTotal()`), and provides simulated checkout processing (`order.checkout()`).

---

## 4. SYSTEMS ANALYSIS & CART INTEGRATION

As Systems Analyst / Integration Lead, data flow and state persistence across activities were solved using a Singleton pattern.

### 4.1. `Cart.java`
- Manages an `ArrayList<Product>` collection.
- Methods: `addProduct(Product)`, `removeProduct(String)`, `calculateTotal()`, `getItemCount()`, and `viewCart()`.

### 4.2. `CartManager.java` (Singleton)
- Maintains a single static `Cart` instance (`sharedCart`).
- Ensures items added on the product catalog screen persist when navigating to the `CartActivity` and `CheckoutActivity`.

---

## 5. ANDROID ACTIVITIES & UI-TO-JAVA WIRING

All 8 XML layouts provided by Member 2 were integrated without altering view IDs or structural design.

| Activity | Associated XML Layout | Core Functionality & Wiring |
| :--- | :--- | :--- |
| **`WelcomeActivity`** | `activity_welcome.xml` | Splash screen launcher (`MAIN`, `LAUNCHER` intent filter). Wires `btnEnterShop` to open `HomeActivity`. |
| **`HomeActivity`** | `activity_home.xml` | Central navigation hub. Wires `btnViewProducts`, `btnCategories`, and `btnShoppingCart` via explicit `Intent`s. |
| **`CategoriesActivity`** | `activity_categories.xml` | Displays supermarket categories (`Grocery`, `Electronics`, `Clothing`, `Household`). Wires card clicks to `ProductListActivity`, passing category filter via `EXTRA_CATEGORY`. |
| **`ProductListActivity`** | `activity_product_list.xml` | Houses an in-memory catalog of 23 products (covering all PNG drawable assets). Dynamically renders rows inside `productListContainer` with image mapping (`getImageForProduct`), price formatting in Kina (K), and `Toast` confirmation when added to cart via `CartManager`. |
| **`ProductDetailActivity`**| `activity_product_detail.xml`| Displays individual product details, pricing, category, and an "ADD TO CART" button. |
| **`CartActivity`** | `activity_cart.xml` | Displays cart items preview, quantity calculation, subtotal, and accurate total across all cart contents. Wires `btnCheckout` to `CheckoutActivity`. |
| **`CheckoutActivity`** | `activity_checkout.xml` | Validates customer input (Name, Phone, Delivery Location), calculates order summary, displays the mandatory assignment disclaimer (*"PROTOTYPE - NOT A REAL TRANSACTION"*), creates `Customer` and `Order` objects, clears the cart, and advances to `OrderConfirmationActivity`. |
| **`OrderConfirmationActivity`**| `activity_order_confirmation.xml` | Displays order success confirmation, summary, and a "BACK TO HOME" button clearing the activity task stack. |

---

## 6. PRODUCT CATALOG & ASSET MAPPING

The application includes 23 products mapped to PNG drawable resources in `res/drawable/`:

| Product Name | Category | Price (Kina) | Drawable Resource |
| :--- | :--- | :--- | :--- |
| Rice | Grocery | K25.00 / bag | `product_rice` |
| Chicken | Grocery | K35.00 / kg | `product_chicken` |
| Bluetooth Speaker | Electronics | K120.00 | `product_speaker` |
| Extension Cable | Electronics | K45.00 | `product_extension_cable` |
| Laundry Powder | Household | K18.00 | `product_laundry_powder` |
| Storage Bucket | Household | K22.00 | `product_storage_bucket` |
| Shoes | Clothing | K85.00 | `product_shoes` |
| Backpack | Clothing | K65.00 | `product_backpack` |
| Apple | Grocery | K12.50 / kg | `product_apple` |
| Bath Towel | Household | K28.00 | `product_bath_towel` |
| Bleach | Household | K15.50 | `product_bleach` |
| Capsicum | Grocery | K14.00 / kg | `product_capsicum` |
| Instant Noodles | Grocery | K2.50 / pack | `product_instant_noodles` |
| Large Tin Fish | Grocery | K8.50 / tin | `product_large_tuna_tinfish` |
| White Rice | Grocery | K22.50 / bag | `product_normal_rice` |
| Orange | Grocery | K10.00 / kg | `product_orange` |
| Prima Sausage | Grocery | K12.50 / pack | `product_prima_sausage` |
| Small Tin Fish | Grocery | K4.50 / tin | `product_small_tuna_tinfish` |
| Thongs | Clothing | K18.00 | `product_thongs` |
| Toilet Tissue | Household | K12.00 | `product_toilet_tissue` |
| Tomato | Grocery | K11.50 / kg | `product_tomato` |
| Black Bag | Clothing | K75.00 | `product_black_bag` |
| Blue Jeans | Clothing | K65.00 | `product_blue_jeans` |

---

## 7. VERSION CONTROL & GITHUB WORKFLOW

- **Repository Branch Strategy:** Strictly utilized a single `master` branch as mandated by project guidelines.
- **Commit History:** Incremental, descriptive commits were performed following each milestone (OOP model setup, activity wiring, cart integration, dynamic layout container refactoring, asset additions, and Gradle build verifications).
- **Remote Repo:** Synchronized with `https://github.com/kawcii/online-shopping-app.git`.

---

## 8. TESTING & VERIFICATION

- **Build Status:** Verified via Gradle (`app:assembleDebug`) resulting in **Zero Errors**.
- **UI Verification:** Tested layout responsiveness, ScrollView view hierarchy, and dynamic LinearLayout container inflation in `ProductListActivity`.
- **Runtime Flow:** Validated End-to-End user journey from Welcome screen -> Home -> Category filtering -> Product selection & Cart addition -> Checkout with validation & disclaimer -> Order Confirmation -> Return to Home.
