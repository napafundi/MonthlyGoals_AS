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
    - Tests:
      - Test dependencies are declared within the app's build.gradle file
      - Tests can be run in different environments, which include: real device, virtual device (emulator), or a simulated device (Robolectric)
        - Real device testing offers the most exactness(?) in how the application will run
        - Simulated devices provide faster test speed at a cost of real-time performance results
        - Virtual devices provide a good balance of test speed and providing real performance feedback
      - Espresso allows for the testing of UI interactions
        - ex. User clicks, text inputs etc.