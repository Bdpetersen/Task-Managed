import java.io.File
import java.util.Scanner

// --- 1. Data Class ---
data class Task(
    val id: Int,
    var description: String,
    var priority: String = "Normal",
    var category: String = "General",
    var isCompleted: Boolean = false
)

// --- 2. Task Manager Class ---
class TaskManager {
    private val tasks = mutableListOf<Task>()
    private var nextId = 1
    private val fileName = "tasks.txt" // File to save tasks

    init {
        loadTasksFromFile()
    }

    fun addTask(description: String, priority: String, category: String) {
        val newTask = Task(nextId++, description, priority, category)
        tasks.add(newTask)
        println("✅ Task added: [${newTask.id}] ${newTask.description}")
        saveTasksToFile()
    }

    fun listTasks(sortByPriority: Boolean = false) {
        if (tasks.isEmpty()) {
            println("📭 Task list is empty.")
            return
        }

        // Sort by priority if requested
        val listToShow = if (sortByPriority) {
            tasks.sortedBy {
                when (it.priority.lowercase()) {
                    "high" -> 1
                    "normal" -> 2
                    else -> 3
                }
            }
        } else {
            tasks
        }

        println("\n--- 📋 Current Tasks ---")
        println("%-5s %-25s %-10s %-10s %-8s".format("ID", "Desc", "Priority", "Category", "Done?"))
        println("-".repeat(65))

        for (task in listToShow) {
            val status = if (task.isCompleted) "[X]" else "[ ]"
            // Formatting to keep the table neat
            var shortDesc = task.description
            if (shortDesc.length > 22) shortDesc = shortDesc.take(20) + "..."

            println("%-5d %-25s %-10s %-10s %-8s".format(
                task.id, shortDesc, task.priority, task.category, status
            ))
        }
    }

    fun completeTask(id: Int) {
        val task = tasks.find { it.id == id }
        if (task != null) {
            task.isCompleted = true
            println("⭐ Task $id marked as complete!")
            saveTasksToFile()
        } else {
            println("❌ Task ID not found.")
        }
    }

    fun searchTasks(keyword: String) {
        val results = tasks.filter { it.description.contains(keyword, ignoreCase = true) }
        if (results.isNotEmpty()) {
            println("\n--- 🔍 Search Results ---")
            results.forEach { println("${it.id}: ${it.description} (${it.priority})") }
        } else {
            println("No tasks found matching '$keyword'")
        }
    }

    // --- File Saving (Persistence) ---
    private fun saveTasksToFile() {
        try {
            val file = File(fileName)
            var data = ""
            tasks.forEach {
                data += "${it.id}|${it.description}|${it.priority}|${it.category}|${it.isCompleted}\n"
            }
            file.writeText(data)
        } catch (e: Exception) {
            println("Could not save to file: ${e.message}")
        }
    }

    private fun loadTasksFromFile() {
        val file = File(fileName)
        if (file.exists()) {
            try {
                val lines = file.readLines()
                var maxId = 0
                for (line in lines) {
                    val parts = line.split("|")
                    if (parts.size >= 5) {
                        val id = parts[0].toInt()
                        tasks.add(Task(id, parts[1], parts[2], parts[3], parts[4].toBoolean()))
                        if (id > maxId) maxId = id
                    }
                }
                nextId = maxId + 1
            } catch (e: Exception) {
                println("Note: New task list created.")
            }
        }
    }
}

// --- 3. Main Function (Interactive) ---
fun main() {
    val manager = TaskManager()
    val scanner = Scanner(System.`in`) // Using Scanner for better input handling
    var shouldExit = false

    println("🚀 Interactive Task Manager Loaded")

    while (!shouldExit) {
        println("\nOPTIONS: (1)Add (2)List (3)Complete (4)Search (5)Sort (6)Exit")
        print("Selection > ")

        if (scanner.hasNextLine()) {
            val input = scanner.nextLine().trim()

            when (input) {
                "1" -> {
                    print("Description: ")
                    val desc = scanner.nextLine()
                    print("Priority (High/Normal/Low): ")
                    val prio = scanner.nextLine()
                    print("Category: ")
                    val cat = scanner.nextLine()
                    manager.addTask(desc, prio, cat)
                }
                "2" -> manager.listTasks()
                "3" -> {
                    print("Enter ID to complete: ")
                    val idStr = scanner.nextLine()
                    val id = idStr.toIntOrNull()
                    if (id != null) manager.completeTask(id) else println("Invalid ID")
                }
                "4" -> {
                    print("Search keyword: ")
                    val key = scanner.nextLine()
                    manager.searchTasks(key)
                }
                "5" -> manager.listTasks(sortByPriority = true)
                "6" -> {
                    shouldExit = true
                    println("Goodbye! 👋")
                }
                else -> println("Invalid option. Please type 1-6.")
            }
        }
    }
}