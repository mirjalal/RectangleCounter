# ![][app_logo] Rectangle Counter
The home task received from [Qwello GmbH][qwello].


## Made with :heart: in Azerbaijan.

## Screenshots

|         Screenshot1         |         Screenshot2         |         Screenshot3         |         Screenshot4         |
|:---------------------------:|:---------------------------:|:---------------------------:|:---------------------------:|
| ![Screenshot1][screenshot1] | ![Screenshot2][screenshot2] | ![Screenshot3][screenshot3] | ![Screenshot4][screenshot4] |

## Requirements:
1. Android Studio v4.0.1 or up
2. Kotlin v1.4.10 or up
3. Java 8

## Building
To build the project run either `./gradlew build` (in Linux or Mac, or `gradlew build` in Windows) or 
click `Rebuild Project` option from `Build` menu in your IDE.

## Arch
Service Locator pattern applied in the application. To do it I used [Koin][koin] because of its simplicity. As you explore 
you'll see `StateFlow` used to show rectangle & selected items count in the UI.

## More info
I took the logic from [this source][logic_source]. The `Python` code was manually translated into `Kotlin` (by me of course).
Due to it has several bugs, they fixed and applied.

## Copyrights:
1. [App logo][app_logo] made by [Freepik] from [Flaticon].

[app_logo]: art/app_logo.png
[screenshot1]: art/ss1.png
[screenshot2]: art/ss2.png
[screenshot3]: art/ss3.png
[screenshot4]: art/ss4.png

[koin]: https://github.com/InsertKoinIO/koin

[logic_source]: https://www.geeksforgeeks.org/find-rectangles-filled-0/

[Freepik]: https://www.flaticon.com/authors/freepik
[Flaticon]: https://www.flaticon.com/free-icon/shapes_601418?term=rectangle%20circle%20shape&page=1&position=1

[qwello]: https://qwello.eu/en
