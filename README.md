# C++ vs Java Runtime & Architectural Differences

| C++ | Java |
| :--- | :--- |
| Code compiles directly to **native machine code** | Code compiles to platform-independent **bytecode** |
| Compiler creates `.exe` / native executable binaries | Compiler creates `.class` files (or `.jar` archives) |
| Runs directly on OS / CPU hardware | Runs inside the **JVM (Java Virtual Machine)** |
| No virtual machine required | Requires JVM to execute `.class` files |
| Memory management can be manual (`new` / `delete`) | **Garbage Collector** automatically manages memory and reclaims unreferenced objects |
| Pointers are directly available with pointer arithmetic | No direct pointer manipulation or pointer arithmetic |
| Uses header files (`.h` / `.hpp`) for declarations | No traditional header files |
