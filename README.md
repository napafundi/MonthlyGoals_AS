# MonthlyGoals_AS
An Android Application version of the Java MonthlyGoals application

## Goals:
  - Re-create the Java MonthlyGoals application as a standalone Android app. 
  - Learn to use Android Studio effectively. 
  - Start with an in-memory database storage and then scale the application to use AWS's online database servers.
  
## Things I've Learned:
  - #### Sept 12, 2019
    - onClick listeners can be connected to activity items through the .xml file itself, or by setting it within the activity class file.
      - Through .xml:
        ```
        android:onClick="methodName"
        ```
      - Through class:
        ```
        button.setOnClickListener(new View.onClickListener() {
          public void onClick(View v) {
            // Code to be executed
          }
        }
        ```
    - For the app to be able to use activities, they must be declared within the manifest, along with needed attributes.
    - Intent filters provide the ability for activities to be launched on implicit requests, as well as explicit requests.
      - Explicit requests specify the application that will satisfy the intent, ex. an activity in your own app
      - Implicit requests don't declare a specific application. They declare a general action to perform, which will allow a component from another application to handle the intent. ex. a point on a map can be displayed by the Google Maps application
