# Project 1 - Instagram Photo Viewer

Instagram Photo Viewer is an android app that allows a user to check out popular photos from Instagram. The app utilizes Instagram API to display images and basic image information to the user.

Time spent: 10 hours spent in total

## User Stories

The following **required** functionality is completed:

* [x] User can **scroll through current popular photos** from Instagram
* [x] For each photo displayed, user can see the following details:
  * [x] Graphic, Caption, Username
  * [x] Relative timestamp, like count, user profile image

The following **optional** features are implemented:

* [x] User can **pull-to-refresh** popular stream to get the latest popular photos
* [x] Show latest comments for each photo
* [x] Display each photo with the same style and proportions as the real Instagram
* [x] Display each user profile image using a RoundedImageViewDisplay each user profile image using a [RoundedImageView](https://github.com/vinc3m1/RoundedImageView)
* [x] Display a nice default placeholder graphic for each image during loading
* [x] Improved the user interface through styling and coloring

The following **bonus** features are implemented:

* [x] Show last 2 comments for each photo
* [ ] Allow user to view all comments for an image within a separate activity or dialog fragment
* [ ] Allow video posts to be played in full-screen using the VideoView

The following **additional** features are implemented:

* [x] Display commenter user profile image
* [x] Display poster's full name
* [x] display error dialog on connection issues

![connection-error-dialog.png](connection-error-dialog.png)

## Video Walkthrough 

Here's a walkthrough of implemented user stories:

![instagram-photo-viewer-walkthrough.gif](instagram-photo-viewer-walkthrough.gif)

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

To compile and run this app, you will need to edit the `Configuration.getInstagramClientId()` method to return a valid client id.  Otherwise, the app will crash upon boot.

* Ran into a lot of issues trying to dynamically scale the TextView icons to match the height, eventually bailing on that.
* Had some issues with the relative layout not cascading below/above to elements which were left/right of another element.

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Picasso](http://square.github.io/picasso/) - Image loading and caching library for Android

## License

    Copyright 2015 Anthony Caliendo

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.