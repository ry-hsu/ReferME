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
| Title<br>(Essential/Desirable/Optional) | Search database for a provider (Essential)                                                                                                                               |
|-----------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Description                             | As a user, I want to be able to search for a provider that I want to review or see reviews of                                                                            |
| Mockups                                 | ![Search](./Doc/SearchMockup.png)                                                                                                                                        |
| Acceptance Tests                        | Given a search bar is shown on the screen, When the user enters a name, results of existing providers are returned. If new, the option to create a new posting is given. |
| Test Results                            | Home, Search, Results, Review Summary, Single Review, Add Provider, Login all work                                                                                       |
| Status                                  | Done                                                                                                                                                                     |

|Title<br>(Essential/Desirable/Optional) | Give a review (Essential)                                                                                                                                |
|---|----------------------------------------------------------------------------------------------------------------------------------------------------------|
|Description| As a user, I want to be able to review a provider                                                                                                        |
|Mockups| ![Review](./Doc/ReviewMockup.png)                                                                                                                        |
|Acceptance Tests| When the user finds a posting of a provider, they are given the option to review the provider. This review process can be found in the following feature |
|Test Results| Can give reviews to a provider and have it saved to a document in Firebase                                                                               |
|Status| Done                                                                                                                                                     |

|Title<br>(Essential/Desirable/Optional) | Review system (Essential)                                                                                                   |
|---|-----------------------------------------------------------------------------------------------------------------------------|
|Description| As a user, I want to be able to review a provider quickly and/or write a review                                             |
|Mockups| ![ReviewSystem](./Doc/ReviewMockup.png)                                                                                     |
|Acceptance Tests| When the user chooses to review a provider, the new review system is presented with the option of writing a review as well. |
|Test Results| Review system with sliders working for a single review                                                                      |
|Status| Done                                                                                                                        |

|Title<br>(Essential/Desirable/Optional) | Search by name, specialty, zip (Essential)                                                                                                   |
|---|----------------------------------------------------------------------------------------------------------------------------------------------|
|Description| As a user, I want to be able to search by specialty for providers to review or read reviews of                                               |
|Mockups| ![Search](./Doc/SearchMockup.png)                                                                                                            |
|Acceptance Tests| Given a search bar is shown on the screen, When the user enters a specialty, results of existing providers with that specialty are returned. |
|Test Results| A composite query to Firebase is working                                                                                                     |
|Status| Done                                                                                                                                         |

|Title<br>(Essential/Desirable/Optional) | Register as a user (Essential)                                                                                                        |
|---|---------------------------------------------------------------------------------------------------------------------------------------|
|Description| As a user, I want to be able to review under a username that may not include my personal information but just pertinent information   |
|Mockups| ![Review](./Doc/ReviewMockup.png)                                                                                                     |
|Acceptance Tests| When the user wants to give a review, they must register with ReferME and provide some pertinent information, but nothing identifying |
|Test Results| Login Authentication and Registration through Firebase is working                                                                     |
|Status| Done |

|Title<br>(Essential/Desirable/Optional) | Database of Providers (Essential)                                          |
|---|----------------------------------------------------------------------------|
|Description| As a user, I want to have a database of providers in the system to review  |
|Mockups| ![Review](./Doc/ProviderPlatformMockup.png)                                |
|Acceptance Tests| When the user reviews a provider, it will be saved in the ReferME database |
|Test Results| Providers are saved in Firebase ![Firebase](./Doc/Firebase.png)            |
|Status| Done |

|Title<br>(Essential/Desirable/Optional) | Utilize Google Business API for search (Optional)                                                                             |
|---|-------------------------------------------------------------------------------------------------------------------------------|
|Description| As a user, I want to be able to search the businesses Google is already aware of to save me from having to enter my own data. |
|Mockups|                                                                                                                               |
|Acceptance Tests| Given a search bar is shown on the screen, When the user enters a search term, Google's API will return results.              |
|Test Results|                                                                                                                               |
|Status| Not Done |

## Design and Implementation

### Iteration 0
ReferMe is mainly focused on being a new review system. However, for this it has to utilize a simple search UI for users to search for providers given a location. 
After searching for providers, users can review them with a simple review system. The search functionality is not as big a focus as there are different APIs that could
help this functionality. However, ReferMe will allow the user to search by a provider and not just a registered business.

The review system will ask the user to rate from 1 to 10 regarding four major areas: Bedside manner, Expertise, Front Office staff, and Facility. This allows users to quickly
review a provider or clinic without having to write down each of these important things. This gives more of a concise review and quantifies each area for users to more easily
differentiate depending on what is more important to them. These 10 points will be displayed as 5 stars.
So each point is worth half a star. 

Eventually, the app may be able to use Google's Business API to help search functionality. 

### Iteration 1
Create UIs for search, results and review fragments with their corresponding layout XMLs.

The following files created:
* SearchFragment.kt 
* ReviewFragment.kt 
* ResultsFragment.kt 
* SearchActivity.kt 
* fragment_review.xml 
* fragment_search.xml 
* fragment_results.xml 
* fragment_review.xml 
* fragment_review_item.xml 
* fragment_search.xml 
* fragment_search_item.xml 
* searchable.xml
* bottom_nav_menu.xml
* Provider.kt

**ReviewFragment.kt and fragment_review.xml**
The UI for when a single provider has been clicked to view includes the provider picture, if it exists,
their name, specialty and contact info. Then, using a linear layout, four categories will be displayed
below using a ratings bar for each category: Bedside Manner, Expertise, Front Office and Facility.

I am still considering what to do for the bottom portion and am going between either having a rolling
RecyclerView of written reviews or maybe a RecyclerView of keywords from written reviews.

Fragment Displaying a Single Searched Provider
![Review Fragment](./Doc/ReviewFragment.png)

**fragment_first.xml**
The UI for when the user reviews a provider has the same avatar plus provider information as the review
fragment. I think this could be centralized since they are the same in a later iteration. The review
section uses Material UI discrete sliders and has some textual descriptions of bad - good to help
the user. 

Also, I will update the name of the xml to be something more descriptive.

Fragment Displaying Screen to Review a Provider
![Review Single Fragment](./Doc/SingleReviewUpdated.png)


**SearchFragment.kt, fragment_search.xml, SearchActivity.kt, searchable.xml**
I started work on the search functionality. The hope is to use the SearchBar from the Materials UI 
components. For this I started created a searchable activity and a menu layout to later place in the 
tool bar in activity_main.xml. For now a layout with just a RecyclerView is the place holder. 

Fragment Displaying Search Results
![Search Results](/Doc/SearchResultFragment.png)

_This fragment may be recycled to for a different use after Search functionality is completed
as it may be done in the toolbar instead_

**Navigation, bottom_nav_menu.xml**
A bottom navigation has been added to the main activity and will be the main way users can move between
UIs. Currently, there are three buttons: home, search and results. For now, search will be the search
UI with a searchbar, results will be the UI that holds the results and as for now home has nothing
on it but may just have a single search text box that on click moves the user to the search bar on the
search fragment when data is entered.

A fourth button will be added in iteration 4 that adds user registration.
![Bottom Navigation](./Doc/BottomNav.png)

A searchable activity was also created in advance for search functionality to be done
in the Iteration 4

Intent Added to Manifest File
![Manifest Search Activity](./Doc/Search1)
Searchable XML Resource Created
![Searchable XML Resource](./Doc/Search2)

The next step is finishing the logic behind these UIs, for now a companion object may be used just
to test these UIs with a temporary Provider instance. Eventually, this will be replaced with a database.

There is also one last piece to the UI that needs to be designed which is the "written review" section.
The thought behind this app is to remove the need to read reviews, so this may be simple logic that
reads the reviews, grabs the keywords used the most often and display the keywords in a gridlayout.

### Iteration 2
  Create Datalayer, ViewModels and continue work on logic for edit and add fragments
  The following files were added:
* Provider.kt and Review.kt data classes
* ProviderDao.kt and ReviewDao.kt
* ReferMeDatabase.kt
* ReferMeRepository.kt
* ReferMeApplication
* AddProviderFragment.kt
  ViewModels
* AddViewModel
* curProviderViewModel
* curReviewViewModel
* reviewListViewModel
  Layouts
* fragment_add_prov.xml
* fragment_Second.xml
* fragment_single_review.xml

**Datalayer**
The ReferMeDatabase has two entities one for providers and one for reviews. The database will
hold two different tables, one for each of these entities.

The reviews table has the following schema

| Field       | Type   | Key     | Null | Default       |
|-------------|--------|---------|------|---------------|
| reviewId    | Int    | Primary | N    | autogenerated |
| date        | String | MUL     | N    | none          |
 | providerID  | Int    | MUL     | N    | none          |
 | userID      | String | MUL     | N    | none          |
| bedManner   | Float  | MUL     | N    | none          |
| expertise   | Float  | MUL     | N    | none          |
| frontOffice | Float  | MUL     | N    | none          |
| facility    | Float  | MUL     | N    | none          | 
| text        | String | MUL     | Y    | none          |

The providers table has the following schema

| Field     | Type   | Key     | Null | Default       |
|-----------|--------|---------|------|---------------|
| provId    | Int    | Primary | N    | autogenerated |
| name      | String | MUL     | N    | none          |
| zip       | Int    | MUL     | Y    | none          |
| specialty | String | MUL     | Y    | none          |
| phone     | String | MUL     | Y    | none          |
| email     | String | MUL     | Y    | none          |

The datalayer will be accessed using Kotlin Coroutines so the IO actions don't interfere
with the UI thread. ReferMeRepository has both ReviewDao and ProviderDao functions implemented.
Currently ReferMeDatabase has no TypeConverters added but this may be added later when there is time
to update some of the datatypes of the fields to things like Lists and Date objects.

**ViewModels**
AddViewModel.kt handles the interaction between the User and the providers table. Adding, deleting and
getting a provider by name can be done through this view model.

curProviderViewModel and curReviewViewModel will be used to handle when viewing a provider and their 
reviews and when viewing a specific review for a specific provider.

ReviewListViewModel will handle the interaction for when the provider view displays all the reviews 
below the user. This view model will allow you to get a list of reviews for a provider, add reviews,
and delete reviews for a provider.

ProviderListViewModel will handle the interaction for when the user is looking at results for a provider
that is already in the database.

**Add a Provider**
A new fragment AddProviderFragment.kt and layout fragment_add_prov.kt were created to add a provider 
manually to the database using the AddViewModel.kt

![Provider Fragment](./Doc/AddProvider.png)

### Iteration 3
Implemented Firebase Authentication and Storage
Finished Review Summary and Give a Review functionality
Query for Providers and display in RecyclerView

**Firebase**
Firebase authentication was added for login functionality. This was done by using the provided Android
Studio login activity template and modifying the data source to be Firebase as well as modifying the
way it checks for a logged in user. After getting authentication from Firebase, the logged in user is
updated and when it is updated the logged in result is changed to SUCCESS and then the login activity
is ended using "finish()" and an intent is send to MainActivity.kt to start the "main" activity for the
application. LoginActivity.kt is set as the MAIN in the manifest file because that is where the users
should start but the application is hidden behind this authentication. 

For storage, two collections were created in Firestore: reviews and providers. These can also be created
from within the app by adding a new provider if the database is empty and then a review on a provider
if no reviews exist. 

The providers collection stores all providers that have been added from the app. So for now, the application
relies on users to populate the database. In the future I hope to implement Google search api to search
for businesses and then use those results if a user chooses them to add to the database.

The reviews collection is simple and is mapped to a provider by using the document.id of a provider.
This value is the same key passed when a new review is added to Firebase.

![Firebase](./Doc/Firebase.png)

To get providers and reviews from Firebase queries are used to gather just the pertinent providers and 
reviews. As this database could be quite large it doesn't make sense to gather all 
documents in a collection. For providers, the query is by name, specialty and/or zipcode. Any of the
three or all three could entered and then the query would use a Firebase index to search for results.
In order to populate more data it is recommended to search for zip code 20202 to see more results on
the screen when testing.

![Search](./Doc/SearchScreen.png)

In order to get reviews the reviews collection is queried for documents where the providerId matches
the provider we are viewing. That ID is the autogenerated document id from Firebase that had been
saved when the provider document is retrieved.

**Review Summary and Give a Review**
When a provider is selected a summary page of reviews for the provider is shown. This is a simple rating
bar for each category: Bed Side Manner, Expertise, Front Office and Facility. The math behind it is
quite simple as the sliders when giving a review are out of 100 the values for all reviews are added together.
Then, to make the values out of 5 stars as is seen, the summation is divided by 10 to get the value out of 
10 and then again by 2 (by 20 total) to be out of 5 stars. 

![Ratings Math](./Doc/RatingsSummary.png)

The summary of the reviews is then presented in one screen. From that same screen a user can give a
review as well.

![Review Summary](./Doc/ReviewSummary.png)

![Review](./Doc/SingleReview.png)

**RecyclerView**
As the data was moved to Firebase the RecyclerView now has the query results applied to the list that
drives the view. Each card in the RecyclerView then has a click listener that sends the provider to
the Review Summary fragment with just the single Provider. 

## Project Structure
Current Project Structure 
Iteration 2
![Project Directory 1](./Doc/ProjectDirectory_It2_0.png)
![Project Directory 2](./Doc/ProjectDirectory_It2_2.png)

Iteration 1
![Project Directory](./Doc/Directory1.png)



## Timeline
Iteration 0 - Planning and proposal phase
- Provide mockups of essential requirements for a minimum viable product
- Give the overview of the systems purpose
- Decide on the minimum sdk Android 9 (there are features from API 28 that want to be used)

Iteration 1 - UI Design 

Iteration 2 - Database and ViewModel work
- Create database and daos for providers and reviews
- Create ViewModels to interact with database from fragments

Iteration 3 - Completion
- Firebase implementation
- Search functionality to query for providers and under the hood query for reviews
- Give a review and view summary of reviews fragments created

| Iteration | Application Requirements (Essential/Desirable/Optional)            | Android Components and Features        | 
|-----------|--------------------------------------------------------------------|----------------------------------------|
| 0         | Mockups for all essential requirements and documentation           | None                                   |
| 1         | Create UIs for Results (Essential) and Review (Essential)          | Layouts and Navigation                 |
| 2         | Database of Reviews and Providers (Essential)                      | Activities, Services, Content Provider |
| 3         | Search database for provider (Essential), Give a Review (Essential) | Activities, Content Provider           |


## Future Work (Optional)
Add to UI so it is more streamlined.
- Add summary of reviews to the RecyclerView so users do not need to click a name to see the ratings
- Utiltize more APIs to increase search results, currently relying on what is in the database. 
- Providers need to be added manually which is tedious find a solution for this.
- Fix navigation issues

## Project Demo Links


## References
https://play.google.com/store/apps/details?id=com.yelp.android&hl=en_US&gl=US
<br>
https://play.google.com/store/apps/details?id=com.google.android.apps.maps&hl=en_US&gl=US
