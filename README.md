# Kotlin Task Manager

This project is a console-based Task Manager application developed in Kotlin. It allows users to create, manage, and organize their daily tasks with features like priority setting, categorization, and file persistence. The application demonstrates core programming concepts including mutable/immutable variables, conditional logic, loops, functions, and object-oriented class structures.

It also implements three stretch challenges:
1.  **Collections:** Using `MutableList` to store task objects.
2.  **Data Classes:** Using a Kotlin `data class` for the Task model.
3.  **When Expression:** Using the `when` keyword for clean menu selection logic.

## Instructions for Build and Use

**Steps to build and/or run the software:**

1.  Open **IntelliJ IDEA**.
2.  Select **Open** and navigate to the project folder (`TaskManager`).
3.  Locate the file `src/Main.kt` in the Project view on the left.
4.  Click the green **Run (▶)** button next to the `main()` function or in the top toolbar.
5.  *Note: If you cannot type in the console, go to Run Configuration > Modify Options and enable "Emulate terminal in output console".*

**Instructions for using the software:**

1.  Upon running, a menu will appear with options (1-6).
2.  **Add a Task (Option 1):** Enter a description, priority (High/Normal/Low), and a category (e.g., Work, School).
3.  **List Tasks (Option 2):** View all current tasks in a formatted table.
4.  **Complete a Task (Option 3):** Enter the numeric ID of the task to mark it as done.
5.  **Search (Option 4):** Type a keyword to find matching tasks.
6.  **Sort (Option 5):** View tasks sorted by priority (High -> Low).
7.  **Exit (Option 6):** Closes the program. All tasks are automatically saved to `tasks.txt` and will load the next time you run the app.

## Development Environment

To recreate the development environment, you need the following software and/or libraries with the specified versions:

* **IDE:** IntelliJ IDEA (Community Edition or Ultimate)
* **Language:** Kotlin (Version 1.9 or newer)
* **JDK:** Java Development Kit (JDK 17 or 21 recommended)
* **Libraries:** Standard Java/Kotlin libraries (`java.util.Scanner`, `java.io.File`)

## Useful Websites to Learn More

I found these websites useful in developing this software:

* [Kotlin Documentation - Control Flow](https://kotlinlang.org/docs/control-flow.html)
* [Kotlin Documentation - Data Classes](https://kotlinlang.org/docs/data-classes.html)
* [Kotlin Standard Library - Collections](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/)

## Future Work

The following items I plan to fix, improve, and/or add to this project in the future:

* [ ] Add a "Due Date" field to tasks and sort by upcoming deadlines.
* [ ] Create a graphical user interface (GUI) using JavaFX or Compose for Desktop.
* [ ] Transition from a text file (`tasks.txt`) to a SQLite database for better data management.
