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

## Phase 4: Task 2
Thu Aug 10 15:23:26 PDT 2023  
New functional group created: a  
Thu Aug 10 15:23:29 PDT 2023  
New functional group created: b  
Thu Aug 10 15:23:29 PDT 2023   
New functional group created:   
Thu Aug 10 15:23:30 PDT 2023   
New functional group created: c   
Thu Aug 10 15:23:41 PDT 2023    
New pathway added: a -> b     
Thu Aug 10 15:23:46 PDT 2023    
New pathway added: b -> c    
Thu Aug 10 15:23:52 PDT 2023   
New pathway added: c -> a   
  
Process finished with exit code 0

## Phase 4: Task 3
Having viewed the UML class diagram, I think the program could be improved by making the association between the SynthesisGraphGUI and ProductsPane bi-directional in the same way that MainMenuPanel and ReactionProductsManager are associated. For the sake of preserving modularity, the latter two classes influence the SynthesisGraph class indirectly by calling methods from the main GUI class, through the bi-directional association. For the sake of time, I had the ProductsPane "inherit" the SynthesisGraph object instantiated by the associated SynthesisGraphGUI classes' constructor, creating two references to the same object. This works fine as well, but could be troublesome when making future changes to the program. 