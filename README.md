[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)  [ ![Download](https://api.bintray.com/packages/xjliao/maven/xjlib/images/download.svg) ](https://bintray.com/xjliao/maven/xjlib/_latestVersion)

# xjlib

Build.gradle added:

compile 'me.xjliao:xjlib:1.0.0'


#Test your integration

##1. Run the packager
$ yarn start

##2. Run the app
Now build and run your Android app as normal.

#Creating a release build

react-native bundle --platform android --entry-file <entryFile> --bundle-output <releaseFolder>/<bundleOutput> --assets-dest <releaseFolder> --dev false

react-native bundle --platform android --dev false --entry-file index.js --bundle-output android/app/src/main/assets/index.bundle --assets-dest android/app/src/main/res/

##Code push
