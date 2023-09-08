
It has a total 3 activities and 2 Fragments.

## Activities: 

A. SplashActivity: It is the first activity that is shown when the app is launched. It is shown for 4 seconds and then it is closed.

B. MainActivity: It is the main activity that is shown after the SplashActivity. It has 2 fragments.

C. LoginActivity: This is the activity that is shown when the user clicks on the login button in the MainActivity. It has 2 fragments.

## Fragments:

A. Thread Fragment: This is the fragment that is shown in the MainActivity. It has a recycler view which shows the list of threads. Upon clicking on reply button of any thread, it opens the reply fragment.

B. Reply Fragment: This is the fragment that is shown when the user clicks on the reply button of any thread. Upon clicking on reply button the agent can reply to the query of the User.

# Packages
A. data: It contains the data classes used to store the data.

B. network: It contains the network classes that are used to make the network calls (Repository and Services).

C. views: It contains the UI classes (Fragments and Adapters).

D. utils: It contains the utility classes which are used to perform some utility functions.

E. viewmodels: It contains the viewmodel classes which are used to store the data and perform the business logic.

# Libraries Used
A. Retrofit: It is used to make the network calls.

B. Gson: It is used to parse the JSON response.

C. Coroutines: It is used to perform the asynchronous tasks.

D. DataStore: It is used to store the data in the local storage.

E. Navigation Component: It is used to navigate between the fragments.



