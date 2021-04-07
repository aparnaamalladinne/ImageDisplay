# ImageDisplay

    The ImageDisplay application displays the the Image view groups in the launch page of the application.
    The buttons are added dynamically based on the response from Manifest.
    
    Clicking on the view group launches next activity with first image in the view group and with page controls.
    The next button takes the user to the next view group.
    Back buttn takes the user back to the View group page.


### LiveData
    Implemented LiveData observer pattern to persist the data and avoid multiple network calls.

### MVVM
    Implemented the application in the MVVM pattern to achieve proper separation of concerns and 
    maintainability and easy extensibility in the application.
    
## Third Party dependencies used

### Glide
    Used Glide to load and cache the images.
    
### Retrofit
    Used Retrofit for the service calls and serializing the responses.


## Focus Areas

    My focus area was on having the application scalable to more number of view groups should the manifest be changed.
    Maintaining the aspect ratio of the images.
    
    