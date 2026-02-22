Modul 2 - CI/CD & DevOps  
Azzahra Anjelika Borselano  
2406419663  

# Reflection  

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
