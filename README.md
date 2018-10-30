[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)  [ ![Download](https://api.bintray.com/packages/xjliao/maven/xjlib/images/download.svg) ](https://bintray.com/xjliao/maven/xjlib/_latestVersion)

# xjlib

Build.gradle added:

compile 'me.xjliao:xjlib:1.0.0'


#Test your integration

##1. Run the packager
$ yarn start --projectRoot ./src

##2. Run the app
Now build and run your Android app as normal.

#Creating a release build

react-native bundle --platform android --entry-file <entryFile> --bundle-output <releaseFolder>/<bundleOutput> --assets-dest <releaseFolder> --dev false

react-native bundle --platform android --dev false --entry-file index.js --bundle-output android/app/src/main/assets/index.bundle --assets-dest android/app/src/main/res/

#Code push
参照文档 https://docs.microsoft.com/en-us/appcenter/distribution/codepush/  

简化步骤  
npm install -g appcenter-cli  

###1、登录
      $ appcenter login  
      
###2、创建应用
      $ appcenter apps create -d xjltest -o Android -p React-Native  

###3、创建测试版本
      $ appcenter codepush deployment add -a lxj990/xljtest Staging 
      创建生产版本  
      $ appcenter codepush deployment add -a lxj990/xljtest Production  

###4、查看版本信息
      $ appcenter codepush deployment list -a lxj990/xjltest  

###5、查看版本key信息
      $ appcenter codepush deployment list -a lxj990/xjltest --displayKeys  

###6、发布测试版本在线更新
     $ appcenter codepush release-react -a lxj990/xjltest -e ./src/index.js -b ./index.bundle -s Staging -t "*" -m --description "Some upgrade things info" 
     -t "*" 代表所有版本（不加则默认从build.gradle里获取的版本)
     -t 1.0.0 只更新1.0.0的版本
     -t "1.2.x" 更新1.2的任何小版本
     -t 1.2.3 - 1.2.7 更新1.2.3至1.2.7的版本(包含1.2.3和1.2.7)
     -t >=1.2.3 <1.2.7 更新1.2.3至1.2.7的版本（包含1.2.3，不包含1.2.7）
     -t ~1.2.3  更新>=1.2.3且<1.3.0的版本
     -t ^1.2.3  更新>=1.2.3且<2.0.0的版本
     其他地方需要用到此参数的地方同理  

###7、同步更新到生产版本
     $ appcenter codepush promote -a lxj990/xjltest -s Staging -d Production  -t "*" --description "同步更新到生产版本"

表达式	 谁得到了更新？
1.2.3	只有运行特定二进制应用程序的设备才会存储您应用的1.2.3版本
*	任何配置为使用CodePush应用程序更新的设备
1.2.x版本	运行主要版本1，次要版本2和应用程序的任何修补程序版本的设备
1.2.3 - 1.2.7	运行1.2.3（含）和1.2.7（含）之间任何二进制版本的设备
> = 1.2.3 <1.2.7	运行1.2.3（含）和1.2.7（独占）之间任何二进制版本的设备
1.2	相当于> = 1.2.0 <1.3.0
1.2.3〜	相当于> = 1.2.3 <1.3.0
^ 1.2.3	相当于> = 1.2.3 <2.0.
     
