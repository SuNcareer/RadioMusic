# RadioMusic
1) Struggles faced

=> Test is too long along with many small features and I can not able to get time because of daily current job and social life. Still I have implemented all possible mentioned features.

=> Not faced any problem during development, Just here, I have observed Radio is starting after few seconds or min.


Features
=========
1. When you open app it will redirect on second tab where Last 10 song displayed
2. Used MVVM structure and Retrofit for calling API
3. Made Data class along with Entity to use common for API call and Room Database
4. Once API call is done all list will add in Room database and then get from Database
5. List will be display on History screen
6. Once it loaded you can check without internet for offline load
7. Bottom footer will have Title, Artist and Album along with play/pause button
8. Click on Play it will show first ads which I have used testing key which was provided by google.
9. Added one progress dialog on click of play button, once dialog is close button will change as pause.dialog
10. Home/Station screen also have all detail and I have added Image over there.dialog
11. You can swap between two view

Application have below architecture in development
==========================
1. MVVM Structure
2. View Binding
3. Room Database
4. Retrofit + OkHttp for API call
5. InterstitialAd
6. MediaPlayer for radio url
7. TabHost & ViewPager for add Tabs and swapping for change view
8. BaseActivity and BaseFragment along with child DashboardActivity and two sub fragment
