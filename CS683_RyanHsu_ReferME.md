# METCS683Assignments

**This README is used for note taking throughout the class project. The Project Report can be found in**
[Project Report](./CS683_RyanHsu_ReferME.md)

Ryan Hsu

## Instructions
- This is the template of your project report.  As this document will be constantly updated during the semester, please enable the “track changes” in your doc. Or if you prefer to use the md file, we can also see the changes in the commits or use diff.
- Please name your report as CS683_\<your name\>_\<ProjectTitle\>. It can be either a PDF or Word document.
- Make sure to push all your code into your github repository, create a release/tag and submit the link on blackboard.
- Please provide your feedback in the “Add comments” section when submitting your report. Thanks!


## Overview

*(This section should be completed in __iteration 0__ as part of your proposal. It can be modified in later iterations. )

*(This section should give an overview of your project. It should include the motivation, the purpose and the potential users of the proposed application. This section should be completed in iteration 0 as part of your proposal. It can be modified in later iterations.)*

Online reviews are everywhere for restaurants, products, and services and healthcare is not far behind those. To that end, 
online reviews for healthcare is growing rapidly but is also hard to find and hard to give reviews. It is also a field where
personal referrals still hold a lot of weight. 

The purpose of this app, ReferMe *(working title), is to provide a platform to grade, review and refer 
healthcare as a service to the public, by the public and for the public. And as it is only to review healthcare services
it will be more specific in it's UX to healthcare specific attributes. 

The users of this app would be anyone that is needs to find any health service. For example, which PCP should I go to if I just
moved to an area. What specialist is most liked for this issue? What office is easiest to be a patient at? Which clinic has
the best laboratory facility? 

## Related Work

*(This section should be completed in __iteration 0__ as part of your proposal. It can be modified in later iterations. )


Similar apps in the app store:

Yelp
Similarities
- Allows for the businesses to post their service
- Allows for any user to review a service (mostly restaurants) 
- Allows users to write a review 
- Allows users to give a score and averages this score allowing the platform to sort restaurants by these scores
- Points out helpful attributes of the service (e.g. good for kids, takeout and delivery available etc.)

Differences
- Yelp relegates the score to a 1 to 5 star system. 
  - ReferMe will combine the grade of 3 major items to provide a more balanced score
    3 Categories being: Bedside manner, Expertise, Front office *(subject to change) 


Google Maps (Reviews for businesses)
- Allows for the businesses to post their service
- Allows for any user to review a service (mostly restaurants)
- Allows users to write a review
- Allows users to give a score and averages this score allowing the platform to sort restaurants by these scores

Differences
- Needs a searchable businesses to give a review
  - ReferMe will allow users to search by provider 



## Requirement Analysis and Testing

*(This section should clearly describe all features/requirements that you __plan to implement or have implemented__ for your application. You should separate them into three categories: essential, desirable and optional.

| Title<br>(Essential/Desirable/Optional) | Search database for a provider (Essential)                                                                                                                               |
|-----------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Description                             | As a user, I want to be able to search for a provider that I want to review or see reviews of                                                                            |
| Mockups                                 | ![Search](./Doc/SearchMockup.png)                                                                                                                                        |
| Acceptance Tests                        | Given a search bar is shown on the screen, When the user enters a name, results of existing providers are returned. If new, the option to create a new posting is given. |
| Test Results                            |                                                                                                                                                                          |
| Status                                  | UI is still under works. Not yet implemented yet.                                                                                                                        |

|Title<br>(Essential/Desirable/Optional) | Give a review (Essential)                                                                                                                                |
|---|----------------------------------------------------------------------------------------------------------------------------------------------------------|
|Description| As a user, I want to be able to review a provider                                                                                                        |
|Mockups| ![Review](./Doc/ReviewMockup.png)                                                                                                                        |
|Acceptance Tests| When the user finds a posting of a provider, they are given the option to review the provider. This review process can be found in the following feature |
|Test Results|                                                                                                                                                          |
|Status|                                                                                                                                                          |


|Title<br>(Essential/Desirable/Optional) | Review system (Essential)                                                                                                   |
|---|-----------------------------------------------------------------------------------------------------------------------------|
|Description| As a user, I want to be able to review a provider quickly and/or write a review                                             |
|Mockups| ![ReviewSystem](./Doc/ReviewMockup.png)                                                                                     |
|Acceptance Tests| When the user chooses to review a provider, the new review system is presented with the option of writing a review as well. |
|Test Results|                                                                                                                             |
|Status|                                                                                                                             |

|Title<br>(Essential/Desirable/Optional) | Search by specialty (Desirable)                                                                                                              |
|---|----------------------------------------------------------------------------------------------------------------------------------------------|
|Description| As a user, I want to be able to search by specialty for providers to review or read reviews of                                               |
|Mockups| ![Search](./Doc/SearchMockup.png)                                                                                                            |
|Acceptance Tests| Given a search bar is shown on the screen, When the user enters a specialty, results of existing providers with that specialty are returned. |
|Test Results|                                                                                                                                              |
|Status|                                                                                                                                              |

|Title<br>(Essential/Desirable/Optional) | Register as a user (Essential)                                                                                                        |
|---|---------------------------------------------------------------------------------------------------------------------------------------|
|Description| As a user, I want to be able to review under a username that may not include my personal information but just pertinent information   |
|Mockups| ![Review](./Doc/ReviewMockup.png)                                                                                                     |
|Acceptance Tests| When the user wants to give a review, they must register with ReferME and provide some pertinent information, but nothing identifying |
|Test Results|                                                                                                                                       |
|Status|

|Title<br>(Essential/Desirable/Optional) | Database of Providers (Essential)                                          |
|---|----------------------------------------------------------------------------|
|Description| As a user, I want to have a database of providers in the system to review  |
|Mockups| ![Review](./Doc/ProviderPlatformMockup.png)                                |
|Acceptance Tests| When the user reviews a provider, it will be saved in the ReferME database |
|Test Results|                                                                            |
|Status|

|Title<br>(Essential/Desirable/Optional) | Utilize Google Business API for search (Optional)                                                                             |
|---|-------------------------------------------------------------------------------------------------------------------------------|
|Description| As a user, I want to be able to search the businesses Google is already aware of to save me from having to enter my own data. |
|Mockups|                                                                                                                               |
|Acceptance Tests| Given a search bar is shown on the screen, When the user enters a search term, Google's API will return results.              |
|Test Results|                                                                                                                               |
|Status|

## Design and Implementation

*(In iteration 0, you can provide an overview or simply  list some basic implementation features.
In later iterations, this section should be updated to provide detailed explanation on how you implement your requirements. You shall provide some explanation as well as supporting evidence, such as sample code snippets (or the file name and line numbers of the code you try to explain). In particular, if you used any features that are not discussed in the class, provide a detailed explanation here.)*

ReferMe is mainly focused on being a new review system. However, for this it has to utilize a simple search UI for users to search for providers given a location. 
After searching for providers, users can review them with a simple review system. The search functionality is not as big a focus as there are different APIs that could
help this functionality. However, ReferMe will allow the user to search by a provider and not just a registered business.

The review system will ask the user to rate from 1 to 5 regarding four major areas: Bedside manner, Expertise, Front Office staff, and Facility. This allows users to quickly
review a provider or clinic without having to write down each of these important things. This gives more of a concise review and quantifies each area for users to more easily
differentiate depending on what is more important to them. 

Eventually, the app may be able to use Google's Business API to help search functionality. 

## Project Structure
*(Please provide a screenshot(s) of your current project structure, which should show all the packages, kotlin/java files and resource files in your project. You should also highlight any files/packages you have changed, added/deleted in this iteration compared with the previous iteration. __This is not needed for iteration 0__)*

## Timeline

*(Please provide  a summary of the requirements implemented and Android/third party components used in the past and current iterations, and the plan in the future iteration. __This is needed for every iteration including iteration 0.__ In iteration 0, you shall give a plan for __all future iterations__. In later iterations, you shall update it according to your progress such as describe what you have implemented in current iteration and modify the future iteration plan accordingly. The last two columns on the right are only needed if your project is a group project. )*

Iteration 0 - Planning and proposal phase
- Provide mockups of essential requirements for a minimum viable product
- Give the overview of the systems purpose
- Decide on the minimum sdk Android 9 (there are features from API 28 that want to be used)

Iteration 1 - UI Design and 

| Iteration | Application Requirements (Eseential/Desirable/Optional)             | Android Components and Features        | 
|-----------|---------------------------------------------------------------------|----------------------------------------|
| 0         | Mockups for all essential requirements and documentation            | None                                   |
| 1         | Register as a User (Essential), Database of Providers (Essential)   | Activities, Services, Content Provider |
| 2         | Search database for provider (Essential), Give a Review (Essential) | Activities, Content Provider           |
| 3         | Search database for specialty (Desirable), Google API (Optional)    | Activities, Services, Content Provider |


## Future Work (Optional)
*(This section can describe possible future works. Particularly the requirements you planned but didn’t get time to implement, and possible Android components or features to implement them.
This section is optional, and you can include this section in the final iteration if you want.)*


##Project Demo Links
*(For on campus students, we will have project presentations in class. __For online students, you are required to submit a video of your project presentation which includes a demo of your app and explanation of your implementation.__ You can use Kaltura to make the video and then submit it on blackboard. Please check the following link for the details of using Kaltura to make and submit videos on blackboard. You can also use other video tools and upload your video to youtube if you like: https://onlinecampus.bu.edu/bbcswebdav/courses/00cwr_odeelements/metcs/cs_Kaltura.htm  )*


## References
https://play.google.com/store/apps/details?id=com.yelp.android&hl=en_US&gl=US
<br>
https://play.google.com/store/apps/details?id=com.google.android.apps.maps&hl=en_US&gl=US
