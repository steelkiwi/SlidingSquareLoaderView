# SlidingSquareLoaderView

[![Made in SteelKiwi](https://github.com/steelkiwi/SlidingSquareLoaderView/blob/master/assets/made_in_steelkiwi.png)](http://steelkiwi.com/blog/)
[ ![Download](https://api.bintray.com/packages/soulyaroslav/maven/sliding-square-loader-view/images/download.svg) ](https://bintray.com/soulyaroslav/maven/sliding-square-loader-view/_latestVersion)

Marvelous sliding square loader view inspired this [Design](https://site.uplabs.com/posts/slidin-squares-loader)

# View

![](https://github.com/steelkiwi/SlidingSquareLoaderView/blob/master/assets/sliding_square_loader_view.gif)

# Download

## Gradle

```gradle
compile 'com.steelkiwi:sliding-square-loader-view:1.0.0'
```

# Usage

Add SlidingSquareLoaderView to your xml layout

```xml
<com.steelkiwi.library.SlidingSquareLoaderView
    android:id="@+id/view"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:sslv_corner_radius="@dimen/corner_radius"
    app:sslv_margin="@dimen/margin"
    app:sslv_end_gradient_color="@color/end_gradient_color"
    app:sslv_start_gradient_color="@color/start_gradient_color"
    app:sslv_background_color="@color/background"
    app:sslv_square_size="@dimen/square_size"/>
```

You can customize view, through this attributes

* app:sslv_corner_radius - view corner radius
* app:sslv_margin - view margin
* app:sslv_end_gradient_color - color for start position of gradient
* app:sslv_start_gradient_color - color for ent position of gradient
* app:sslv_background_color - parent background color
* app:sslv_square_size - view size

To use it in the code you need only call this methods

```java
// to show loading
view.show()
// to hide loading
view.hide()
```

# License

```
Copyright © 2017 SteelKiwi, http://steelkiwi.com

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```