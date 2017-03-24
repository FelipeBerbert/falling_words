# falling_words

# how much time was invested (within the given limit)
A bit over 6 hours;

# how was the time distributed (concept, model layer, view(s), game mechanics)
concept: 2 hours, model layer: less than 1 hour, view: 1 hour, game mechanics: about 3 hours;

# decisions made to solve certain aspects of the game
Used simple RXJava observables to deal with timers and delays;
Simple animator to show the words "falling", I am not sure what was expected here;

# decisions made because of restricted time;
Locked the app to the portrait position, so I could save time dealing with the activity/fragment lifecycle.
Didn't use a dependency injection library like Dagger2 as I think it would take more time than save;
Ugly layout =(
Didn't put the feedback sounds that I had planned;

# what would be the first thing to improve or add if there had been more time
write more unit tests;