# **Lost Dog Tracker**

## ***Description of my project***

This application would be for finding lost dogs in a particular area. Users would be able to enter information about their lost dogs including a rough location of where and when they were lost and any descriptions about them or pictures. There would also be a feature for entering dogs found in the area and providing possible matches between dogs lost and dogs found by comparing fields provided in the description of the dog. 

The intended target audience is the general public. Anyone who has lost a dog can input their dogs information or anyone who has found a stray dog can input that information. People who have lost dogs would be able to browse through found dogs in the area and search for theirs but there would also be a feature that automatically compares dogs for possible matches. This project interests me because I recently lost 2 of my dogs and an application like this would have made the search a lot easier rather than searching through numerous posts in facebook groups. 

## ***User Stories***

- As a user, I want to be able to report my lost or found dog.
- As a user, I want to be able to view all the currently reported lost or found dogs in my area. 
- As a user, I want to be able to remove my lost or found dog entry after the dog or owner is found. 
- As a user, I want to be able to compare my lost or found dog to entries from other users in my area. 
- As a user, I want to be able to claim a dog if it is my lost dog and get the contact of the user who posted it. 
- As a user, I want to be able to save a record of all the lost or found dogs and save one specific dog as the current dog to search.
- As a user, I want to be able to load a record of all the lost or found dogs and load my last saved specific dog.

# Instructions for End User
 - After running, there will be a screen with 4 options. To add a Dog to the list of lost/found dogs select whether you lost a dog or found a dog followed by clicking the  Report Lost/Found Dog button. Entering the information when prompted will add the dog to the relevant list. 
 - You can generate the first required action related to the user story "adding multiple Xs to a Y" by removing a dog from the list of lost/found dogs by clicking on the Remove Lost/Found Dog Report button. After verification the report will be removed. 
 - You can generate the second required action related to the user story "adding multiple Xs to a Y" by filtering through the dogs in the opposite list (i.e if you lost a dog you can search through the found dogs) by clicking the Check Found/Lost Dogs For Your Lost/Found Dog button, and if there is a potential match it will display all the options.
 - You can also check the current dog you are comparing by clicking the View Current Dog button and look at the entire opposite list by clicking the View Found/Lost Dogs button.
 - You can locate my visual component by adding images of the dogs. When filing a report, when prompted to add a picture you can either use your own image or use the ones added already by specifying the file path i.e "Images/Cutie.PNG". When looking through the lists, the images will also be there. 
 - You can save the state of my application by clicking the Save Data button in the main menu.
 - You can reload the state of my application by clicking the Load Data button in the main menu.

# Phase 4: Task 2

- Wed Aug 06 20:11:07 PDT 2025
- Current dog changed
- Wed Aug 06 20:11:09 PDT 2025
- Lost dog added
- Wed Aug 06 20:11:16 PDT 2025
- Current dog changed
- Wed Aug 06 20:11:18 PDT 2025
- Found dog added
- Wed Aug 06 20:11:23 PDT 2025
- Looked through all found dogs
- Wed Aug 06 20:11:28 PDT 2025
- Searched found dogs for potential matches
- Wed Aug 06 20:11:31 PDT 2025
- Looked through all lost dogs
- Wed Aug 06 20:11:32 PDT 2025
- Searched lost dogs for potential matches
- Wed Aug 06 20:11:35 PDT 2025
- Looked through all lost dogs
- Wed Aug 06 20:11:35 PDT 2025
- Lost dog removed
- Wed Aug 06 20:11:39 PDT 2025
- Looked through all found dogs
- Wed Aug 06 20:11:39 PDT 2025
- Found dog removed

# Phase 4: Task 3

- We can see that a lot of classes are overlapping for example the personFound and personLost, their functionality is very similar so it would be better to have an abstract class of person and just have personFound and personLost extend that to reduce repetition of code. There is also a similar argument for creating an abstract class for ListPersonFound and ListPersonLost. 
 
- As for the classes in the ui package, a lot of the functionality is also shared so an abstract class for the gui version and a seperate one for the console version would reduce a lot of code repetition. I could also refactor by creating a helper methods class that has all the basic design functionality such as adding a display/ getting a picture and this would also reduce repetition of code. 
