# Low Level Design (LLD) - Key Concepts

## Table of Contents
1. [What is Low Level Design?](#what-is-low-level-design)
2. [SOLID Principles](#solid-principles)
3. [Design Patterns](#design-patterns)
4. [Object-Oriented Programming Concepts](#object-oriented-programming-concepts)
5. [Best Practices](#best-practices)

---

## What is Low Level Design?

Low Level Design (LLD) is the process of designing the internal structure of individual components or modules in a software system. It focuses on:
- **Class diagrams** and relationships
- **Method signatures** and implementation details
- **Data structures** and algorithms
- **Design patterns** application
- **Code organization** and modularity

### LLD vs HLD (High Level Design)
| Aspect | High Level Design (HLD) | Low Level Design (LLD) |
|--------|------------------------|------------------------|
| Focus | System architecture | Component implementation |
| Abstraction | High-level components | Classes, methods, data structures |
| Output | Architecture diagrams, component diagrams | Class diagrams, sequence diagrams |
| Audience | Architects, stakeholders | Developers, engineers |

---

## SOLID Principles

SOLID is an acronym for five design principles that make software designs more maintainable and scalable.

### 1. Single Responsibility Principle (SRP)
**"A class should have only one reason to change."**
- Each class should have a single, well-defined responsibility
- Promotes high cohesion and low coupling
- Makes code easier to test and maintain

```java
// Bad: Multiple responsibilities
class User {
    void saveToDatabase() { }
    void sendEmail() { }
    void generateReport() { }
}

// Good: Single responsibility
class User { }
class UserRepository { void save(User user) { } }
class EmailService { void send(User user) { } }
class ReportGenerator { void generate(User user) { } }
```

### 2. Open/Closed Principle (OCP)
**"Software entities should be open for extension but closed for modification."**
- Add new functionality without changing existing code
- Use abstraction and polymorphism
- Reduces risk of breaking existing functionality

```java
// Good: Open for extension
interface PaymentProcessor {
    void processPayment(double amount);
}

class CreditCardProcessor implements PaymentProcessor {
    public void processPayment(double amount) { }
}

class PayPalProcessor implements PaymentProcessor {
    public void processPayment(double amount) { }
}
```

### 3. Liskov Substitution Principle (LSP)
**"Objects of a superclass should be replaceable with objects of its subclasses without breaking the application."**
- Subclasses must honor the contract of the parent class
- No unexpected behavior in derived classes

```java
// Bad: Violates LSP
class Bird {
    void fly() { }
}
class Penguin extends Bird {
    void fly() { throw new UnsupportedOperationException(); }
}

// Good: Follows LSP
interface Bird { }
interface FlyingBird extends Bird {
    void fly();
}
class Sparrow implements FlyingBird {
    public void fly() { }
}
class Penguin implements Bird { }
```

### 4. Interface Segregation Principle (ISP)
**"Clients should not be forced to depend on interfaces they don't use."**
- Create specific, focused interfaces
- Avoid "fat" interfaces with too many methods

```java
// Bad: Fat interface
interface Worker {
    void work();
    void eat();
    void sleep();
}

// Good: Segregated interfaces
interface Workable {
    void work();
}
interface Eatable {
    void eat();
}
interface Sleepable {
    void sleep();
}
```

### 5. Dependency Inversion Principle (DIP)
**"Depend on abstractions, not on concretions."**
- High-level modules should not depend on low-level modules
- Both should depend on abstractions
- Enables flexibility and testability

```java
// Bad: Direct dependency
class MySQLDatabase { }
class UserService {
    private MySQLDatabase database = new MySQLDatabase();
}

// Good: Depend on abstraction
interface Database { }
class MySQLDatabase implements Database { }
class UserService {
    private Database database;
    public UserService(Database database) {
        this.database = database;
    }
}
```

---

## Design Patterns

Design patterns are reusable solutions to common software design problems.

### Creational Patterns
Control object creation mechanisms.

#### 1. Singleton Pattern
**Ensures a class has only one instance and provides global access to it.**
- **Use Case**: Database connections, logging, configuration managers
- **Implementation**: Private constructor, static instance holder
- See: [MySingleton.java](MySingleton.java)

**Key Concepts:**
- Thread-safe lazy initialization (Bill Pugh Singleton)
- Prevent reflection and serialization attacks
- Consider alternatives like Dependency Injection

#### 2. Builder Pattern
**Constructs complex objects step by step.**
- **Use Case**: Objects with many optional parameters
- **Benefits**: Immutable objects, readable code, flexible construction
- See: [MyBuilder.java](MyBuilder.java)

**Key Concepts:**
- Fluent API with method chaining
- Separates construction from representation
- Alternative to telescoping constructors

#### 3. Factory Pattern
**Creates objects without specifying the exact class.**
- **Use Case**: When the exact type of object isn't known until runtime
- **Benefits**: Loose coupling, easier to extend

```java
interface Shape {
    void draw();
}
class Circle implements Shape { public void draw() { } }
class Rectangle implements Shape { public void draw() { } }

class ShapeFactory {
    public static Shape createShape(String type) {
        if (type.equals("circle")) return new Circle();
        if (type.equals("rectangle")) return new Rectangle();
        return null;
    }
}
```

#### 4. Prototype Pattern
**Creates new objects by cloning existing ones.**
- **Use Case**: When object creation is expensive
- **Benefits**: Reduces initialization overhead

### Structural Patterns
Organize relationships between entities.

#### 1. Adapter Pattern
**Converts one interface to another that clients expect.**
- **Use Case**: Integrating incompatible interfaces
- **Example**: Making legacy code work with new systems

#### 2. Decorator Pattern
**Adds new functionality to objects dynamically.**
- **Use Case**: Adding responsibilities without subclassing
- **Example**: Java I/O streams (BufferedReader, InputStreamReader)

#### 3. Facade Pattern
**Provides a simplified interface to a complex subsystem.**
- **Use Case**: Simplifying complex APIs
- **Benefits**: Reduces coupling, easier to use

#### 4. Proxy Pattern
**Provides a surrogate or placeholder for another object.**
- **Use Case**: Lazy loading, access control, logging
- **Types**: Virtual, Protection, Remote proxies

### Behavioral Patterns
Manage algorithms, relationships, and responsibilities.

#### 1. Observer Pattern
**Defines a one-to-many dependency where objects are notified of state changes.**
- **Use Case**: Event handling, pub-sub systems, MVC
- **Benefits**: Loose coupling between subject and observers
- See: [MyObserver.java](MyObserver.java)

**Key Concepts:**
- Subject (Observable) and Observer
- Push vs Pull models
- Memory leaks with dangling observers

#### 2. Strategy Pattern
**Defines a family of algorithms and makes them interchangeable.**
- **Use Case**: Multiple ways to perform an operation
- **Benefits**: Eliminates conditional statements

```java
interface SortStrategy {
    void sort(int[] array);
}
class QuickSort implements SortStrategy { }
class MergeSort implements SortStrategy { }

class Sorter {
    private SortStrategy strategy;
    public void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }
    public void sort(int[] array) {
        strategy.sort(array);
    }
}
```

#### 3. Command Pattern
**Encapsulates a request as an object.**
- **Use Case**: Undo/redo, queuing operations, callbacks
- **Benefits**: Decouples sender and receiver

#### 4. Template Method Pattern
**Defines the skeleton of an algorithm, letting subclasses override specific steps.**
- **Use Case**: When algorithms have similar structure but differ in details

#### 5. Iterator Pattern
**Provides sequential access to elements without exposing underlying structure.**
- **Use Case**: Traversing collections
- **Example**: Java's Iterator interface

---

## Object-Oriented Programming Concepts

### Four Pillars of OOP

#### 1. Encapsulation
**Bundling data and methods that operate on that data within a single unit (class).**
- Hide internal state using private fields
- Provide public getters/setters for controlled access
- Protects data integrity

#### 2. Abstraction
**Hiding complex implementation details and showing only essential features.**
- Use abstract classes and interfaces
- Focus on "what" rather than "how"
- Reduces complexity

#### 3. Inheritance
**Mechanism where a new class derives properties from an existing class.**
- Promotes code reuse
- Establishes "is-a" relationship
- Use carefully to avoid tight coupling

#### 4. Polymorphism
**Ability of objects to take multiple forms.**
- **Compile-time (Method Overloading)**: Same method name, different parameters
- **Runtime (Method Overriding)**: Subclass provides specific implementation
- Enables flexibility and extensibility

### Additional Concepts

#### Composition vs Inheritance
- **Inheritance**: "is-a" relationship
- **Composition**: "has-a" relationship
- **Prefer composition** over inheritance for flexibility

```java
// Inheritance
class Car extends Vehicle { }

// Composition (preferred)
class Car {
    private Engine engine;
    private Transmission transmission;
}
```

#### Coupling and Cohesion
- **Loose Coupling**: Minimize dependencies between modules
- **High Cohesion**: Elements within a module are closely related
- Goal: Loose coupling + High cohesion

#### Immutability
- Objects whose state cannot be modified after creation
- **Benefits**: Thread-safe, easier to reason about, cacheable
- Use `final` keyword, no setters, defensive copying

---

## Best Practices

### 1. Design for Testability
- Use dependency injection
- Keep methods small and focused
- Avoid static methods and global state
- Write unit tests alongside code

### 2. Code to Interfaces, Not Implementations
```java
// Good
List<String> names = new ArrayList<>();

// Avoid
ArrayList<String> names = new ArrayList<>();
```

### 3. Follow Naming Conventions
- **Classes**: PascalCase (UserService, PaymentProcessor)
- **Methods**: camelCase (getUserById, processPayment)
- **Constants**: UPPER_SNAKE_CASE (MAX_SIZE, DEFAULT_TIMEOUT)
- Use meaningful, descriptive names

### 4. Keep It Simple (KISS)
- Avoid over-engineering
- Don't apply patterns unnecessarily
- Write code that's easy to understand

### 5. Don't Repeat Yourself (DRY)
- Extract common code into reusable methods/classes
- Use inheritance, composition, or delegation
- Maintain single source of truth

### 6. YAGNI (You Aren't Gonna Need It)
- Don't add functionality until it's needed
- Avoid speculative generality
- Focus on current requirements

### 7. Law of Demeter (Principle of Least Knowledge)
**"Only talk to your immediate friends."**
```java
// Bad: Violates Law of Demeter
customer.getWallet().getCard().charge(amount);

// Good: Tell, don't ask
customer.makePayment(amount);
```

### 8. Error Handling
- Use exceptions for exceptional conditions
- Don't use exceptions for control flow
- Provide meaningful error messages
- Clean up resources (use try-with-resources)

### 9. Documentation
- Write self-documenting code
- Add comments for complex logic
- Use Javadoc for public APIs
- Keep documentation up-to-date

### 10. Code Reviews
- Get feedback from peers
- Look for design issues, not just syntax
- Share knowledge and best practices

---

## Common LLD Interview Topics

### 1. System Component Design
- Design a parking lot
- Design a library management system
- Design a restaurant reservation system
- Design an elevator system
- Design a chess game

### 2. Design Patterns Application
- When to use Singleton vs Factory
- Strategy vs State pattern
- Decorator vs Proxy pattern
- Observer vs Pub-Sub

### 3. Problem-Solving Approach
1. **Clarify requirements**: Ask questions about use cases, constraints
2. **Identify entities**: Nouns in requirements become classes
3. **Define relationships**: Inheritance, composition, association
4. **Apply patterns**: Choose appropriate design patterns
5. **Consider SOLID**: Ensure principles are followed
6. **Draw diagrams**: Class diagram, sequence diagram
7. **Write code**: Implement core classes and methods

---

## Resources

### Books
- "Design Patterns: Elements of Reusable Object-Oriented Software" (Gang of Four)
- "Head First Design Patterns" by Eric Freeman
- "Clean Code" by Robert C. Martin
- "Effective Java" by Joshua Bloch
- "Refactoring" by Martin Fowler

### Online Resources
- Refactoring.Guru (Design Patterns)
- SourceMaking.com
- Java Design Patterns (GitHub repository)

---

## Summary

Low Level Design is about creating maintainable, scalable, and robust software by:
1. Following **SOLID principles**
2. Applying **design patterns** appropriately
3. Writing **clean, testable code**
4. Understanding **OOP concepts** deeply
5. Practicing **best practices** consistently

**Remember**: Design patterns are tools, not rules. Use them when they solve a real problem, not because they're "cool" or "best practice."
