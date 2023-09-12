# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /home/arjan/Android/Sdk/tools/proguard/proguard-android.txt
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

# BEGIN: Crashlytics suggestions
# Allow for meaningful crash reports
-keepattributes *Annotation*,EnclosingMethod,Signature,SourceFile,LineNumberTable
# Custom exceptions should be be skipped during obfuscation
-keep public class * extends java.lang.Exception
# BEGIN: Crashlytics suggestions

# BEGIN: fix https://github.com/square/retrofit/issues/3751
# Note: remove this block once the issue is resolved, i.e. a new Retrofit version is released
# Keep annotation default values (e.g., retrofit2.http.Field.encoded).
-keepattributes AnnotationDefault

# Keep inherited services.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface * extends <1>

# Keep generic signature of Call, Response (R8 full mode strips signatures from non-kept items).
-keep,allowobfuscation,allowshrinking interface retrofit2.Call
-keep,allowobfuscation,allowshrinking class retrofit2.Response

# With R8 full mode generic signatures are stripped for classes that are not
# kept. Suspend functions are wrapped in continuations where the type argument
# is used.
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation
# END: fix https://github.com/square/retrofit/issues/3751
