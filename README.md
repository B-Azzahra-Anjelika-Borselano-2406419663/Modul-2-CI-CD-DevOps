# Modul 2 - CI/CD & DevOps
Azzahra Anjelika Borselano  
2406419663  

Deployment: https://modul-2-ci-cd-devops-6pyx.onrender.com

## Reflection

## 1. Code Quality Issues Fixed and Strategy  
During the exercise, I fixed several code quality issues detected by PMD, particularly using the Best Practices and Error Prone rulesets.  
The main issue reported by PMD was:

> *The String literal "productId" appears 4 times in this file; the first occurrence is on line 40.*

This warning indicates a violation of the **Avoid Duplicate Literals** rule. Repeating the same string literal multiple times in a class reduces maintainability and increases the risk of inconsistency if the value needs to be changed in the future.
To address this issue, I refactored the code by defining a constant variable.
Then, I replaced all occurrences of the repeated string literal with this constant.

Additionally, during deployment, I encountered an issue where the **product list page** and **create product page** did not load in the deployed environment. The root cause was a mismatch between the template name referenced in the controller and the actual HTML file name. For example, the controller returned:
return "productList" while the actual file was named ProductList.html. I fixed the issue by correcting the template reference in the controller to match the exact case of the file name. This resolved the deployment error.
  
## 2. Has the CI/CD Implementation Met the Definition of CI and CD?  

Yes, the current implementation satisfies the definition of both **Continuous Integration (CI)** and **Continuous Deployment (CD)**.  
First, Continuous Integration is achieved because every push to any branch automatically triggers workflows that build the project, run tests, and execute static code analysis (PMD). This ensures that integration issues are detected early and code quality is continuously monitored.  
Second, the project implements Continuous Deployment through automatic deployment to the PaaS (Render). After a successful push to the repository, the deployment process is triggered automatically. This means that changes are consistently and reliably delivered to the production environment.  
Third, the pipeline enforces automation and repeatability. Each commit is validated through the same standardized process (build, test, analysis, and deployment), reducing human error and ensuring consistent quality.

# Modul 3 - Maintainability & OO Principles

## Implementation of SOLID Principles

### **1. Single Responsibility Principle (SRP)**
I applied SRP by separating `CarController` and `ProductController` so that each controller handles only its respective domain. Additionally, the project structure is divided into Controllers, Services, Repositories, and Models to clearly separate concerns.

### **2. Open/Closed Principle (OCP)**
I applied OCP by introducing service interfaces such as `CarService` and `ProductService`, allowing the system to be extended without modifying existing controller code. Instead of changing existing classes, new behaviors can be added by creating new implementations of these interfaces. This ensures that the system is open for extension but closed for modification.

### **3. Liskov Substitution Principle (LSP)**
I applied LSP by designing service implementations (`CarServiceImpl`) to fully conform to their respective interfaces (`CarService`). This allows any implementation of the interface to replace another without breaking the controller logic. The controller depends only on the abstraction.

### **4. Interface Segregation Principle (ISP)**
I applied ISP by separating service interfaces into `CarService` and `ProductService` instead of creating one large, general interface. This ensures that implementing classes only depend on the methods they actually use. As a result, the system remains modular and avoids unnecessary dependencies.

### **5. Dependency Inversion Principle (DIP)**
I applied DIP by modifying the controllers to depend on service interfaces instead of concrete implementations. Previously, `CarController` depended directly on `CarServiceImpl`, but I refactored it to depend on the `CarService` interface. Similarly, services also depend on repository interfaces rather than concrete classes.

## **2) Advantages of Applying SOLID Principles**
Applying SOLID principles improves maintainability, scalability, and testability of the project. For example, by separating `CarController` and `ProductController` (SRP), changes in the car module do not affect the product module, making the system easier to maintain.  
Using interfaces such as `CarService` and `ProductService` (OCP and DIP) allows new implementations to be added without modifying existing controller code, which makes the system easier to extend. Additionally, because controllers depend on abstractions rather than concrete classes, unit testing becomes easier since mock implementations can be injected during testing.

## **3) Disadvantages of Not Applying SOLID Principles**
Without SOLID principles, the project would become harder to maintain. For example, if `CarController` directly depended on `CarServiceImpl`, any change in the implementation could require modifying the controller as well, increasing the risk of bugs.  
If responsibilities were not separated, it makes the code harder to understand and modify. Over time, this would lead to rigid, fragile code that is difficult to scale and test.

