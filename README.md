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
      - Explicit requests specify the application that will satisfy the intent, ex. an activity in your own app.
      - Implicit requests don't declare a specific application. They declare a general action to perform, which will allow a component from another application to handle the intent. ex. a point on a map can be displayed by the Google Maps application.
    - Tests:
      - Test dependencies are declared within the app's build.gradle file
      - Tests can be run in different environments, which include: real device, virtual device (emulator), or a simulated device (Robolectric).
        - Real device testing offers the most exactness(?) in how the application will run.
        - Simulated devices provide faster test speed at a cost of real-time performance results.
        - Virtual devices provide a good balance of test speed and providing real performance feedback.
      - Espresso allows for the testing of UI interactions.
        - ex. User clicks, text inputs etc.

  - #### Sept 14, 2019
    - Activities can be closed using the finish() method.
      - Testing activity results can be done by checking the results of the activity using the getActivityResult() method.
      
  - #### Sept 16, 2019
    - Form validation support can be handled by using myField.setError("Error goes here") when the controller receives invalid input.
      - This displays an error message connected to the field receiving invalid input.
    - RecyclerViews use an adapter to to convert objects stored in data structures into a list's row item.
      - The adapter needs a ViewHolder object which describes how each item row will be populated and act.
    - Android applications can use 'Rooms' to store data.
      - They provide an abstraction layer over SQLite to improve database access.
      - Room is also very useful for storing database information when a user is using an application offline. (Can store online DB info in Room)
      - Entities are tables within a database.
      - DAOs are classes that contain methods for interacting with the database.
      
  - #### Sept 19, 2019
    - Certain data types need TypeConverters when being used in a Room database.
    - Testing room databases is more effective by creating an in-memory version of the database. (Mocking the DAO classes)
      - This prevents the need for a full database to be created when not testing the entire database.
      
  - #### Sept 20, 2019
    - Certain DAO methods require the primary key of the data to be executed.
      - This is obvious for the @Delete method. So when deleting a row from the db, if using the standard @Delete, the object passed in must be the entity with a primary key.
    - viewHolder view elements can be given specific tags (e.g. position, name) within the adapter's onBindViewHolder method to make other interactions easier.
    - Android provides the ability to created animated drawables through vectorDrawables.
      - The advantage of using vectors, is that they do not lose quality when scaled up or down.
      - The vectors contain data paths that tell how to draw the element.
        - Basic path commands are comprised of characters followed by numbers dictating the drawing action.
            - Uppercase letters mean "absolute position" while lowercase letters mean "relative position".
            - M(X,Y) or m(X,Y): Move the cursor to the given x,y position.
            - Z or z: Draws a line from the current cursor position to the start position of the path.
            - L(X,Y) or l(X,Y): Draws a line from the current position to the given x,y position.
            - H(X) or h(X): Draws a horizontal line to the position at x.
            - V(X) or v(X): Draws a vertical line to the position at y.
      - Rendering these drawables is time and memory consuming, best used for simple and flat graphics.
      - Read about attaching onClickListeners() to the recyclerView item elements.
        - Different approaches can be used. Although the method I found (easiest for now) can become inefficient with larger data sets.
          - It relies on notifyDataSetChanged() which is a costly method call.
          
  - #### Sept 21, 2019
    - Implementing a swipe to "do action" functionality for RecyclerView uses the ItemTouchHelper class.
    - Android icons can be obtained from their website.
      - When using a downloaded icon, add a new "vector asset" to the drawables directory and select "from SVG/PNG".
        - This will create an xml file containing the icon drawable.
    - SnackBars are UI elements that can appear on the screen to prompt users for actions.
      - One common application of the SnackBar is the "undo" when deleting an item (think Gmail app).
      
      