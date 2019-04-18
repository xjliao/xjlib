# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/xjliao/android-sdk-macosx/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

###############################去掉所有打印
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** e(...);
    public static *** i(...);
    public static *** v(...);
    public static *** println(...);
    public static *** w(...);
    public static *** wtf(...);
}

-assumenosideeffects class android.util.Log {
   public static *** d(...);
   public static *** v(...);
}

-assumenosideeffects class android.util.Log {
    public static *** e(...);
    public static *** v(...);
}

-assumenosideeffects class android.util.Log {
    public static *** i(...);
    public static *** v(...);
}

-assumenosideeffects class android.util.Log {
    public static *** w(...);
    public static *** v(...);
}
-assumenosideeffects class java.io.PrintStream {
    public *** println(...);
    public *** print(...);
}

-ignorewarnings

-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
 -keep class android.support.**{*;}

-dontwarn com.squareup.**

-dontwarn com.squareup.**

-dontwarn okhttp3.**

-dontwarn okio.**

-keep class com.rtzc.pnca.BuildConfig { *; }

-keepattributes InnerClasses
-keep class **.R$* {*;}

-keep class org.xmlpull.v1.** { *;}
 -dontwarn org.xmlpull.v1.**
