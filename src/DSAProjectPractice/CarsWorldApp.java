package DSAProjectPractice;
import java.util.ArrayList;
import java.util.Scanner;

// CarsWorldApp.java - Command line version of the GUI you showed.
// This file holds the MAIN method and menu handling for admin and user.
public class CarsWorldApp {

    // Hard-coded admin credentials (for demo only)
    private static final String ADMIN_ID = "17103";
    private static final String ADMIN_PASSWORD = "admin";

    private CarLinkedList inventory = new CarLinkedList();       // Linked list of cars
    private FeedbackQueue feedbackQueue = new FeedbackQueue(50); // Queue for feedback
    private ActionStack undoStack = new ActionStack(50);         // Stack for undo

    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        CarsWorldApp app = new CarsWorldApp();
        app.seedSampleData(); // load a few cars like in screenshots
        app.mainMenu();
    }

    // Fill inventory with some sample cars so the user can search immediately
    private void seedSampleData() {
        inventory.add(new Car("Honda", "City", "Hatchback", 2015, 2500000));
        inventory.add(new Car("Honda", "City", "Hatchback", 2020, 3500000));
        inventory.add(new Car("Honda", "Civic", "Sedan", 2020, 4000000));
        inventory.add(new Car("Honda", "Vezel Hybrid Z", "SUV", 2017, 4700000));

        inventory.add(new Car("Kia", "Picanto", "Hatchback", 2021, 2600000));
        inventory.add(new Car("Kia", "Sportage", "SUV", 2019, 6000000));

        inventory.add(new Car("Toyota", "Corolla GLi 1.3", "Sedan", 2018, 3000000));
        inventory.add(new Car("Toyota", "Altis Grande 1.8", "Sedan", 2016, 3200000));
        inventory.add(new Car("Toyota", "Yaris", "Sedan", 2021, 3000000));
        inventory.add(new Car("Toyota", "Fortuner", "SUV", 2021, 8000000));
    }

    // MAIN menu similar to the first GUI screen
    private void mainMenu() {
        int choice;
        do {
            System.out.println("\n===== CarsWorld =====");
            System.out.println("1. Admin Login");
            System.out.println("2. User Dashboard");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            choice = readInt();

            switch (choice) {
                case 1:
                    handleAdminLogin();
                    break;
                case 2:
                    userDashboard();
                    break;
                case 3:
                    System.out.println("Exiting CarsWorld. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 3);
    }

    // Simple helper to read integer safely
    private int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return value;
    }

    private long readLong() {
        while (!scanner.hasNextLong()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        long value = scanner.nextLong();
        scanner.nextLine(); // consume newline
        return value;
    }

    // ADMIN LOGIN screen
    private void handleAdminLogin() {
        System.out.print("Enter Admin ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Password: ");
        String pass = scanner.nextLine();

        if (ADMIN_ID.equals(id) && ADMIN_PASSWORD.equals(pass)) {
            System.out.println("Login successful. Welcome Admin!\n");
            adminDashboard();
        } else {
            System.out.println("Invalid ID or Password.\n");
        }
    }

    // ADMIN DASHBOARD menu (Add, Edit price, Delete, View etc.)
    private void adminDashboard() {
        int choice;
        do {
            System.out.println("\n===== ADMIN DASHBOARD =====");
            System.out.println("1. Add Car Data");
            System.out.println("2. Edit Car Price");
            System.out.println("3. Delete Car");
            System.out.println("4. View All Cars");
            System.out.println("5. Process Feedback Queue");
            System.out.println("6. Undo Last Action");
            System.out.println("7. Log Out");
            System.out.print("Enter choice: ");
            choice = readInt();

            switch (choice) {
                case 1:
                    addCarData();
                    break;
                case 2:
                    editCarPrice();
                    break;
                case 3:
                    deleteCar();
                    break;
                case 4:
                    printCars(inventory.getAllCars());
                    break;
                case 5:
                    processFeedback();
                    break;
                case 6:
                    undoLastAction();
                    break;
                case 7:
                    System.out.println("Logging out from admin dashboard.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 7);
    }

    // Add Data screen
    private void addCarData() {
        System.out.println("\n--- Add New Car ---");
        System.out.print("Enter Car Brand: ");
        String brand = scanner.nextLine();
        System.out.print("Enter Car Model: ");
        String model = scanner.nextLine();
        System.out.print("Enter Car Category (Sedan/SUV/Hatchback): ");
        String category = scanner.nextLine();
        System.out.print("Enter Car Year: ");
        int year = readInt();
        System.out.print("Enter Car Price: ");
        long price = readLong();

        Car car = new Car(brand, model, category, year, price);
        inventory.add(car);

        // push action to stack so we can undo
        undoStack.push(new AdminAction(AdminActionType.ADD, new Car(car), 0));

        System.out.println("Car added successfully.");
    }

    // Edit Price screen
    private void editCarPrice() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        System.out.println("\n--- Edit Car Price ---");
        System.out.print("Enter Car Model whose price you want to change: ");
        String model = scanner.nextLine();
        Car car = inventory.findFirstByModel(model);
        if (car == null) {
            System.out.println("No car found with that model.");
            return;
        }
        System.out.println("Current details: ");
        System.out.println(formatHeader());
        System.out.println(car);
        System.out.print("Enter new price: ");
        long newPrice = readLong();

        long oldPrice = inventory.updatePriceByModel(model, newPrice);
        if (oldPrice != -1) {
            // Save previous state for undo
            Car snapshot = new Car(car);
            snapshot.setPrice(oldPrice); // ensure old price in snapshot
            undoStack.push(new AdminAction(AdminActionType.UPDATE_PRICE, snapshot, oldPrice));
            System.out.println("Price updated successfully.");
        }
    }

    // Delete Data screen
    private void deleteCar() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        System.out.println("\n--- Delete Car ---");
        System.out.print("Enter Car Model to delete: ");
        String model = scanner.nextLine();
        Car removed = inventory.deleteByModel(model);
        if (removed == null) {
            System.out.println("No car found with that model.");
        } else {
            undoStack.push(new AdminAction(AdminActionType.DELETE, new Car(removed), 0));
            System.out.println("Car deleted successfully.");
        }
    }

    // Process feedback queue (Admin reads messages in FIFO order)
    private void processFeedback() {
        System.out.println("\n--- Feedback Queue ---");
        if (feedbackQueue.isEmpty()) {
            System.out.println("No feedback at the moment.");
            return;
        }
        Feedback f;
        while ((f = feedbackQueue.dequeue()) != null) {
            System.out.println("Feedback: " + f.getMessage());
            System.out.println("(press Enter to read next)");
            scanner.nextLine();
        }
        System.out.println("All feedback processed.");
    }

    // Undo last admin action using STACK
    private void undoLastAction() {
        AdminAction action = undoStack.pop();
        if (action == null) {
            System.out.println("No actions to undo.");
            return;
        }

        switch (action.getType()) {
            case ADD:
                // remove the car that was last added (by model)
                inventory.deleteByModel(action.getCarSnapshot().getModel());
                System.out.println("Undo successful: last added car removed.");
                break;
            case DELETE:
                // re-insert the deleted car
                inventory.add(new Car(action.getCarSnapshot()));
                System.out.println("Undo successful: last deleted car re-added.");
                break;
            case UPDATE_PRICE:
                // restore old price
                inventory.updatePriceByModel(action.getCarSnapshot().getModel(),
                        action.getOldPrice());
                System.out.println("Undo successful: price restored.");
                break;
        }
    }

    // USER DASHBOARD menu
    private void userDashboard() {
        int choice;
        do {
            System.out.println("\n===== USER DASHBOARD =====");
            System.out.println("1. Search Car By Brand");
            System.out.println("2. Search Car By Model");
            System.out.println("3. View All Cars");
            System.out.println("4. Cost Calculator");
            System.out.println("5. Give Feedback");
            System.out.println("6. Exit to Main Menu");
            System.out.print("Enter choice: ");
            choice = readInt();

            switch (choice) {
                case 1:
                    searchCarByBrand();
                    break;
                case 2:
                    searchCarByModel();
                    break;
                case 3:
                    printCars(inventory.getAllCars());
                    break;
                case 4:
                    costCalculator();
                    break;
                case 5:
                    giveFeedback();
                    break;
                case 6:
                    System.out.println("Leaving user dashboard.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 6);
    }

    // SEARCH BY BRAND screen
    private void searchCarByBrand() {
        System.out.print("Enter Car Brand: ");
        String brand = scanner.nextLine();
        ArrayList<Car> result = inventory.findByBrand(brand);
        if (result.isEmpty()) {
            System.out.println("No cars found for this brand.");
        } else {
            printCars(result);
        }
    }

    // SEARCH BY MODEL screen
    private void searchCarByModel() {
        System.out.print("Enter Car Model: ");
        String model = scanner.nextLine();
        ArrayList<Car> result = inventory.findByModel(model);
        if (result.isEmpty()) {
            System.out.println("No cars found for this model.");
        } else {
            printCars(result);
        }
    }

    // COST CALCULATOR screen
    private void costCalculator() {
        System.out.print("Enter Car Model to calculate cost for: ");
        String model = scanner.nextLine();
        Car car = inventory.findFirstByModel(model);
        if (car == null) {
            System.out.println("Car not found.");
            return;
        }
        System.out.print("Enter quantity: ");
        int qty = readInt();
        long total = car.getPrice() * qty;
        System.out.println("Base total = " + total);

        // Just to make it a bit interesting: add 5% tax
        double tax = total * 0.05;
        double grandTotal = total + tax;
        System.out.println("Tax (5%) = " + tax);
        System.out.println("Grand Total = " + grandTotal);
    }

    // USER FEEDBACK screen
    private void giveFeedback() {
        System.out.print("Enter your feedback about CarsWorld: ");
        String msg = scanner.nextLine();
        feedbackQueue.enqueue(new Feedback(msg));
        System.out.println("Thank you! Your feedback has been recorded.");
    }

    // Utility method to print a list of cars in table format
    private void printCars(ArrayList<Car> cars) {
        if (cars.isEmpty()) {
            System.out.println("No cars to display.");
            return;
        }
        System.out.println("\n" + formatHeader());
        for (Car c : cars) {
            System.out.println(c);
        }
    }

    private String formatHeader() {
        return String.format("%-10s %-18s %-10s %-6s %-10s",
                "Brand", "Model", "Category", "Year", "Price");
    }
}



/*
How DSA is used in this project

Quick summary of where each data structure appears and why:

Linked List – CarLinkedList + CarNode

Stores the entire car inventory as a singly linked list.

Operations:

add(Car) – inserts a new car at the end.

deleteByModel – walks the list and removes a node by matching the model.

updatePriceByModel / findByBrand / findByModel.

This mimics how your GUI shows a dynamic table of cars that supports insert, delete, and update.

ArrayList – search results & view all

CarLinkedList returns results as ArrayList<Car> in:

findByBrand, findByModel, getAllCars.

CarsWorldApp then prints those lists.

This demonstrates how an array-based dynamic list is convenient for random access and iteration once the data is collected from the linked list.

Queue – FeedbackQueue

Implements a feedback box for users.

giveFeedback() enqueues Feedback objects at the rear.

processFeedback() in the admin dashboard dequeues them in the same order they were received (FIFO).

Internally it uses a circular array (front, rear, size), which is the textbook implementation of a queue.

Stack – ActionStack + AdminAction

Each admin operation (ADD, DELETE, UPDATE_PRICE) is saved as an AdminAction object on a stack.

When a car is added → push action type ADD.

When a car is deleted → push DELETE with a snapshot of the removed car.

When price is updated → push UPDATE_PRICE with the old price.

undoLastAction() pops the top action and reverses it:

Undo ADD → delete that car.

Undo DELETE → re-add the car.

Undo UPDATE_PRICE → restore old price.

This stack follows LIFO – the last action is undone first, exactly like Ctrl+Z in editors.

So overall:
Totally fair question—let’s slow down and take this project apart like Lego and see exactly where and how each data structure works.

I’ll explain:

1. **Big picture** of the program
2. **Linked List** – how it stores all cars
3. **ArrayList** – how it’s used with search & display
4. **Queue** – how feedback works
5. **Stack** – how Undo works
6. Walk-through of common actions (Add, Edit, Delete, Search, Feedback, Undo) and which DSA is used at each step

I’ll reference class names from the code I gave you earlier.

---

## 1. Big picture: what is this program doing?

Conceptually we have:

* A **car inventory** (all the cars and their details)
* An **admin** who can:

  * Add cars
  * Change prices
  * Delete cars
  * View cars
  * Read user feedback
  * Undo his last actions
* A **user** who can:

  * Search cars by brand or model
  * View all cars
  * Use a cost calculator
  * Give feedback

To implement that, we store data in memory using these DSA:

* Inventory → **Linked List**
* Results / output views → **ArrayList**
* Feedback → **Queue**
* History of admin operations → **Stack**

Now let’s zoom into each.

---

## 2. Linked List – the “backbone” of all cars

### Conceptual idea

A **linked list** is like a chain of boxes.
Each box (node) has:

* The **data** (the car)
* A **link** (pointer) to the next box

`head` points to the **first** node.

Visually:

```text
head
 ↓
[Car1 | next] → [Car2 | next] → [Car3 | next] → null
```

If we want to go through all cars, we start at `head` and keep following `next`.

### Where is it in code?

Two classes:

* `Car` → holds car brand, model, year, price, category
* `CarNode` → one node in the linked list

```java
public class CarNode {
    Car data;     // the car itself
    CarNode next; // link to next node
}
```

And the actual list:

```java
public class CarLinkedList {
    private CarNode head;
    ...
}
```

`CarLinkedList` is what the app uses as its **inventory**.

In `CarsWorldApp`:

```java
private CarLinkedList inventory = new CarLinkedList();
```

So **all cars** live inside that `inventory` linked list.

### Operations on this Linked List

1. **Adding a car** (`add(Car car)`)

```java
public void add(Car car) {
    CarNode newNode = new CarNode(car);
    if (head == null) {
        head = newNode;      // list was empty
        return;
    }
    CarNode current = head;
    while (current.next != null) {
        current = current.next; // move to last node
    }
    current.next = newNode; // attach new node at end
}
```

Plain English:

* If the list is empty, the new car becomes the first node.
* Otherwise, walk to the last node and link its `next` to the new node.
* This is exactly what happens in `addCarData()` in `CarsWorldApp`.

2. **Deleting a car by model** (`deleteByModel`)

```java
public Car deleteByModel(String model) {
    if (head == null) return null;

    // If first node matches
    if (head.data.getModel().equalsIgnoreCase(model)) {
        Car removed = head.data;
        head = head.next; // move head to next node
        return removed;
    }

    CarNode current = head;
    while (current.next != null &&
           !current.next.data.getModel().equalsIgnoreCase(model)) {
        current = current.next;
    }

    if (current.next == null) return null; // not found

    Car removed = current.next.data;
    current.next = current.next.next; // skip over the found node
    return removed;
}
```

Plain English:

* If list is empty → nothing to delete.
* If the **first node** is the car → move `head` to second node.
* Otherwise, walk until the **next** node is the one we want, then:

  * connect the current node directly to *next.next* (skip the one we’re deleting).
* Return the removed car (so we can store it on the undo stack later).

Used when admin chooses **Delete Car**.

3. **Update price by model** (`updatePriceByModel`)

```java
public long updatePriceByModel(String model, long newPrice) {
    Car car = findFirstByModel(model);
    if (car == null) return -1;
    long oldPrice = car.getPrice();
    car.setPrice(newPrice);
    return oldPrice;
}
```

`findFirstByModel` just walks the list until it finds the first car whose model matches.

Used when admin chooses **Edit Car Price**.

4. **Search by brand / model** → returns `ArrayList<Car>`

```java
public ArrayList<Car> findByBrand(String brand) {
    ArrayList<Car> result = new ArrayList<>();
    CarNode current = head;
    while (current != null) {
        if (current.data.getBrand().equalsIgnoreCase(brand)) {
            result.add(current.data);
        }
        current = current.next;
    }
    return result;
}
```

It simply walks the linked list, collects matching cars into an **ArrayList**.
We’ll talk about ArrayList next.

5. **Get all cars** (`getAllCars`)

Same idea: traverse from `head` to end, add every car to an ArrayList and return it.

---

## 3. ArrayList – result / display list

### Conceptual idea

An `ArrayList` is like a **resizable array**.

* Under the hood it uses an array, but you don’t worry about size.
* You can `add()` items and it grows automatically.
* You can access by index (0,1,2,…) easily.

We use it for:

* A temporary container of **search results**
* A convenient way to iterate for printing

### Where is it in code?

Inside `CarLinkedList`:

* `findByBrand` returns `ArrayList<Car>`
* `findByModel` returns `ArrayList<Car>`
* `getAllCars` returns `ArrayList<Car>`

Inside `CarsWorldApp`:

```java
private void printCars(ArrayList<Car> cars) {
    if (cars.isEmpty()) {
        System.out.println("No cars to display.");
        return;
    }
    System.out.println("\n" + formatHeader());
    for (Car c : cars) {
        System.out.println(c);
    }
}
```

So the **linked list** is our main storage, but **ArrayList** is used when:

1. We want a **subset** of cars (like search results)
2. We want a **simple way to loop** and display them nicely

Why not only ArrayList?
We *could*, but the assignment is to show different DSAs working together, so:

* Linked list = main structure
* ArrayList = helper structure

---

## 4. Queue – user feedback

### Conceptual idea

A **queue** is like a real-world line:

* People enter at the **back**.
* The person at the **front** is served first.
* Order: **First In, First Out (FIFO)**.

### Where is it in code?

Class: `FeedbackQueue`

Internally it uses an **array** and acts like a circular queue:

```java
private Feedback[] data;
private int front;
private int rear;
private int size;
```

* `front` → index of the first element
* `rear` → index of the last element
* `size` → number of elements currently in queue

Constructor:

```java
public FeedbackQueue(int capacity) {
    data = new Feedback[capacity];
    front = 0;
    rear = -1;
    size = 0;
}
```

#### Enqueue (user gives feedback)

```java
public void enqueue(Feedback f) {
    if (isFull()) {
        System.out.println("Feedback queue is full.");
        return;
    }
    rear = (rear + 1) % data.length; // move rear circularly
    data[rear] = f;
    size++;
}
```

Plain English:

* Move `rear` one step right (wrap around at end).
* Put feedback in that slot.
* Increase size.

#### Dequeue (admin reads feedback)

```java
public Feedback dequeue() {
    if (isEmpty()) {
        return null;
    }
    Feedback f = data[front];
    data[front] = null;
    front = (front + 1) % data.length;
    size--;
    return f;
}
```

Plain English:

* Take the feedback at `front`.
* Move `front` one step forward.
* Decrease size.

### Where is it used in app?

`CarsWorldApp`:

```java
private FeedbackQueue feedbackQueue = new FeedbackQueue(50);
```

User side (`giveFeedback()`):

```java
System.out.print("Enter your feedback about CarsWorld: ");
String msg = scanner.nextLine();
feedbackQueue.enqueue(new Feedback(msg));
System.out.println("Thank you! Your feedback has been recorded.");
```

Admin side (`processFeedback()`):

```java
Feedback f;
while ((f = feedbackQueue.dequeue()) != null) {
    System.out.println("Feedback: " + f.getMessage());
    ...
}
```

So we’re enforcing **first user who sent feedback is the first whose message is read**.

That’s exactly what a queue is for.

---

## 5. Stack – undo admin actions

### Conceptual idea

A **stack** is like a stack of plates:

* You put plates on **top** (push).
* You take plates from **top** (pop).
* Order: **Last In, First Out (LIFO)**.

Undo behavior is naturally LIFO:

* The last action you did is the first you undo.

### Where is it in code?

Two main pieces:

1. `AdminActionType` – enum of types of actions:

   * `ADD`
   * `UPDATE_PRICE`
   * `DELETE`

2. `AdminAction` – object storing:

   * type (`AdminActionType`)
   * snapshot of the car
   * oldPrice (for update price actions)

```java
public class AdminAction {
    private AdminActionType type;
    private Car carSnapshot;
    private long oldPrice;
    ...
}
```

3. `ActionStack` – our stack implementation:

```java
public class ActionStack {
    private AdminAction[] data;
    private int top; // index of top element
}
```

* `top = -1` means stack is empty.

**push**:

```java
public void push(AdminAction action) {
    if (isFull()) {
        System.out.println("Undo stack is full. Oldest actions will be lost.");
        // drop oldest by shifting left
        for (int i = 1; i <= top; i++) {
            data[i - 1] = data[i];
        }
        top--;
    }
    data[++top] = action;
}
```

**pop**:

```java
public AdminAction pop() {
    if (isEmpty()) return null;
    AdminAction a = data[top];
    data[top] = null;
    top--;
    return a;
}
```

### Where is it used?

In `CarsWorldApp`:

```java
private ActionStack undoStack = new ActionStack(50);
```

Whenever admin performs an action:

1. **Add car**

```java
undoStack.push(
    new AdminAction(AdminActionType.ADD, new Car(car), 0)
);
```

We store a copy of the added car.

2. **Delete car**

```java
Car removed = inventory.deleteByModel(model);
if (removed != null) {
    undoStack.push(
        new AdminAction(AdminActionType.DELETE, new Car(removed), 0)
    );
}
```

We store the deleted car so we can re-add it.

3. **Update price**

```java
long oldPrice = inventory.updatePriceByModel(model, newPrice);
if (oldPrice != -1) {
    Car snapshot = new Car(car);
    snapshot.setPrice(oldPrice);
    undoStack.push(
        new AdminAction(AdminActionType.UPDATE_PRICE, snapshot, oldPrice)
    );
}
```

We store the old price and car snapshot.

#### Undo operation

`undoLastAction()`:

```java
AdminAction action = undoStack.pop();
if (action == null) {
    System.out.println("No actions to undo.");
    return;
}

switch (action.getType()) {
    case ADD:
        // remove the car that was last added
        inventory.deleteByModel(action.getCarSnapshot().getModel());
        break;
    case DELETE:
        // reinsert the deleted car
        inventory.add(new Car(action.getCarSnapshot()));
        break;
    case UPDATE_PRICE:
        // restore old price
        inventory.updatePriceByModel(
            action.getCarSnapshot().getModel(),
            action.getOldPrice()
        );
        break;
}
```

So:

* Last added car → removed
* Last deleted car → added back
* Last price change → reverted

That’s pure **stack (LIFO)** behavior.

---

## 6. Putting it all together: step-by-step flows

Let’s trace some common actions and see which DSAs are involved.

### A. Admin adds a car

1. User fills car info in console.
2. App creates `Car` object.
3. App calls `inventory.add(car)` → **Linked List**:

   * If empty: `head = newNode`
   * Else: walk to last node and append.
4. App pushes `AdminAction(ADD, carCopy, 0)` onto `undoStack` → **Stack**.

DSAs used: **Linked List + Stack**

---

### B. Admin edits price

1. Admin enters model.
2. App calls `inventory.findFirstByModel(...)` → traverses **linked list**.
3. If car found, display info.
4. Admin enters new price.
5. App calls `inventory.updatePriceByModel(model, newPrice)`:

   * Traverse linked list to find car.
   * Store oldPrice, set newPrice.
6. App pushes `AdminAction(UPDATE_PRICE, snapshotWithOldPrice, oldPrice)` to **stack**.

DSAs used: **Linked List + Stack**

---

### C. Admin deletes a car

1. Admin enters model.
2. App calls `inventory.deleteByModel(model)`:

   * Traverse linked list, fix links to remove node.
3. If removed, push `AdminAction(DELETE, removedCar, 0)` to **stack**.

DSAs used: **Linked List + Stack**

---

### D. User searches by brand

1. User enters brand.
2. App calls `inventory.findByBrand(brand)`:

   * Traverse **linked list** checking each `CarNode`.
   * Matching `Car` objects are added to `ArrayList<Car> result`.
3. App calls `printCars(result)`:

   * Loop through ArrayList and print each car.

DSAs used: **Linked List + ArrayList**

---

### E. User searches by model

Same as above but using `findByModel(model)`.

DSAs: **Linked List + ArrayList**

---

### F. User views all cars

1. App calls `inventory.getAllCars()`:

   * Traverse linked list, add every `Car` to an ArrayList.
2. Prints the list.

DSAs: **Linked List + ArrayList**

---

### G. User gives feedback

1. User types some text.
2. App creates `Feedback` object.
3. App calls `feedbackQueue.enqueue(feedback)`:

   * Place it at `rear` of circular array.
   * `size++`.

DSA: **Queue**

---

### H. Admin processes feedback

1. Admin chooses “Process Feedback Queue”.
2. Loop:

   * `Feedback f = feedbackQueue.dequeue();`
   * If null → queue empty → stop.
   * Otherwise print `f.getMessage()` and wait Enter.
3. Messages are processed **in the order they were given**.

DSA: **Queue**

---

### I. Admin presses Undo

1. App calls `undoStack.pop()`:

   * Last `AdminAction` returned (LIFO).
2. Depending on `action.type`:

   * `ADD` → delete that car from inventory (linked list).
   * `DELETE` → add the snapshot car back to inventory (linked list).
   * `UPDATE_PRICE` → restore `oldPrice` in inventory (linked list).

DSAs: **Stack + Linked List**

---

## Very short recap

* **Linked List (`CarLinkedList`)**

  * Main storage of all car data.
  * Good for easy insert/delete in middle.
  * We manually walk node by node.

* **ArrayList**

  * Used for temporary collections of cars (search results, all cars).
  * Easy to iterate and print.

* **Queue (`FeedbackQueue`)**

  * Stores feedback messages from users.
  * FIFO: older feedback is read before newer feedback.

* **Stack (`ActionStack`)**

  * Stores previous admin actions.
  * LIFO: last action is undone first.

If you want, next I can:

* Draw small diagrams for **each operation** with 2–3 cars to show how `head`, `front`, `rear`, `top` move, or
* Simplify the code further and write pseudo-code next to each method so you can memorize the patterns.

 */