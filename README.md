<p align="center">
  <img src="https://raw.githubusercontent.com/viniciusmo/keyboard-visibility-event-android/master/logo.png">
</p>


<p align="center"> 
	<img src="https://img.shields.io/badge/kotlin-v1.2.71-blue.svg" alt="Kotlin">
	<img src="https://travis-ci.org/anfederico/Clairvoyant.svg?branch=master" alt="Build Status">
	<img src="https://img.shields.io/badge/dependencies-up%20to%20date-brightgreen.svg" alt="Dependencies">
	<img src="https://img.shields.io/github/issues/viniciusmo/keyboard-visibility-event-android.svg" alt="GitHub Issues">
	<img src="https://img.shields.io/badge/contributions-welcome-violet.svg" alt="Contributions welcome">
	<img src="https://img.shields.io/badge/license-MIT-blue.svg" alt="License">
</p>


About
--------
A DSL to handle soft keyboard visibility change event written in Kotlin.


How to use?
--------

Step 1. Add it in your root build.gradle at the end of repositories:

```gradle
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

Step 2. Add the dependency

```gradle
dependencies {
	implementation 'com.github.viniciusmo:keyboard-visibility-event-android:Tag'
}
```

Step 3. Code example
```java
keyboard {
    onClosed { alert("onKeyboardHide").show() }
    onOpened { alert("onKeyboardVisible").show() }
}

```
