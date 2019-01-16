# Github - Android

The project follows the entire line of Google's standard development, based on Jetpack.

This application only aims to show a list of items so that the user can see some data.

And it also handles error handling if there is any failure to process the request made by the API.

[APK](https://github.com/charleston10/github-android/blob/master/apk/app-dev-debug.apk?raw=true) || [VIDEO](https://github.com/charleston10/github-android/blob/master/assets/videos/device-2018-12-27-174651.mp4?raw=true) || [YOUTUBE](https://youtu.be/029L0QRRjWE)



<table>
  <thead>
    <tr>
      <th>BASE</th>
      <th>Architecture</th>
      <th>IU</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>AppCompact</td>
      <td>DataBinding</td>
      <td>Navigation</td>
    </tr>
    <tr>
      <td>Android KTX</td>
      <td>Lifecycles</td>
      <td>Material Components</td>
    </tr>
     <tr>
      <td>Kotlin</td>
      <td>LiveData</td>
    </tr>
     <tr>
      <td>Android Arch</td>
      <td>ViewModel</td>
    </tr>
  </tbody>
</table>


**Screens**
<table>  
  <th>Initial</th>
  <th>Listening</th>
<tr>

<td>
   <img src="https://github.com/charleston10/github-android/blob/master/assets/screens/device-2018-12-27-173826.png?raw=true"/>
  </td>
<td>
   <img src="https://github.com/charleston10/github-android/blob/master/assets/screens/device-2018-12-27-174416.png?raw=true"/>
  </td>
</tr>
</table>
<table>
  <th>Searching</th>
  <th>Found</th>
  <th>List</th>
<tr>
  <td>
    <img src="https://github.com/charleston10/github-android/blob/master/assets/screens/device-2018-12-27-174430.png?raw=true"/>
  </td>
<td>
   <img src="https://github.com/charleston10/github-android/blob/master/assets/screens/device-2018-12-27-174438.png?raw=true"/>
  </td>
<td>
   <img src="https://github.com/charleston10/github-android/blob/master/assets/screens/device-2018-12-27-174449.png?raw=true"/>
  </td>
</tr>
</table>


<table>
  <th>Navigation</th>
<tr>
  <td>
   <img src="https://github.com/charleston10/github-android/blob/master/assets/screens/navigation.png?raw=true"/>
  </td>
</tr>
</table>

## CODE
- **IDE - Android Studio 3.3 Beta 4** 

- **Gradle 3.2.1**

- **Kotlin 1.3.0**

- **AAC Android Architecture Components** *using guide Google JetPack*

- **Clean Architecture** *for apply DRY, KISS, SOLID*

- **DataBinding** *bind data model with view*

- **ViewModel** *for interact view with business rules*
 
 - **RX Android** *for manipulate data and events in different layers of application*
 
 ## LIBRARIES
 
 - Retrofit
 - Dagger Android
 - Material Component
 - Timber
 - Lottie
 - RX Java / RX Android
 - Google Listen
 

 ## API

Github Search Repositories Documentation: https://developer.github.com/v3/search/#search-repositories


## DESIGN

**Material Components**

https://github.com/material-components

- Toolbar
- RecyclerView
- MaterialButton

**Lottie**

http://airbnb.io/lottie/
