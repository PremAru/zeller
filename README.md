# Zeller
A banking app that allows users to transact money and view their transaction history

# Zeller Banking screen
![alt text](https://github.com/PremAru/zeller/blob/master/images/Transfer.png)

![alt text](https://github.com/PremAru/zeller/blob/master/images/TransferHistory.png)

## Getting Started

Install Android Studio

	http://developer.android.com/sdk/index.html

Install Android SDK & buildtools

	Android 8.0 (API 26)
	buildtools 26.0.1 and above

Open workspace

	Clone this repository and import into Android Studio
	git clone git@github.com:PremAru/github-api.git
	
	Android Studio -> File -> Open

Run Unit Tests

	$ ./gradlew build

Build project

	$ gradlew build
# Language
	Kotlin
	
# Development
The project follows MVVM architecture with AndroidX libraries. Dagger is used heavily for dependancy injection as it helps to avoid error-prone boilerplate code and makes unit testing efficient. 
	
	
TDD is followed for the development of the application. Red, Green, Refactor approach of TDD is followed to efficiantly model the classes and inject dependancies.
    
# Libraries used:


*	Dagger2

*	Mockito

*	Timber

*	Expresso

