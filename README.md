# Organic Chemistry Synthesis Pathway Solver


In university Organic Chemistry courses, there is a common type of problem  
on exams called *synthesis problems*. These problems will specify a   
**starting product** and **final product**––either by name or chemical––and  
ask the student to provide the *sequence of reactions* required to produce  
the final product from the starting product. The many testable chemical  
groups and the reaction pathways between them naturally form a *graph*, thus  
making it suitable for a computer to solve programmatically. 


The application will take two chemical groups provided by the user as input, and  
return a list of possible synthetic pathways to generate the final product from the  
starting material. Optional parameters may be implemented to give the user more  
control for example:
- only return shortest path.
- list specific reactants available (limiting potential pathways). 
- add user-defined chemical groups to the existing graph stored in data.

This application will be useful for students studying organic chemistry and  
want to check their answers, learn alternative routes they may not have thought of, 
and most importantly provide a data structure to visualize associated reactions while learning. This project is of interest to me as I am double  
majoring in biochemistry and CS, and enjoy applying programming to the biochemistry  
field. 


## User Stories:
As a user, I want to be able to:
- Define starting and final chemical groups.
- Generate sequences of chemical groups connecting the starting  
  product to the final one.
- Add chemical groups / reaction paths to the existing graph. 
- View graphical data such as child nodes of a specific chemical group.
- have the option of *saving* added graph data to file
- have the option of *loading* graph data from file and resume at a later time