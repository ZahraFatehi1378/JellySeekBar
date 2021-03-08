![Jelly Seekbar](/previews/jelly_seekbar.gif)


## Installation
```
​Just download the package from here and add it to your project classpath, or just use the maven repo:

Gradle:

implementation ''
SBT:

libraryDependencies += "com.ramotion.fluidslider" % "fluid-slider" % "0.3.1"
Maven:

<dependency>
  <groupId></groupId>
  <artifactId></artifactId>
  <version></version>
  <type></type>
</dependency>


```

# Basic usage

Place the JellySeekBar in your layout.

Here is simple example, how to change range , 
set color and first position ,
set duration of animations , 
and To track the current position of the slider set the getSeekBarLocation,
 as shown below:

```
JellySeekBar jellySeekBar;

   jellySeekBar = findViewById(R.id.jellySeekBar);

        jellySeekBar.setRange(0, 80);

        jellySeekBar.setColor("#bdeaee" ,"#76b4bd" ,"#29a8ab");

        jellySeekBar.setSignFirstLocation(50);

        jellySeekBar.setBubblesDuration(700);

        jellySeekBar.setSignDuration(500);

        jellySeekBar.setFontForNum(getResources().getFont(R.font.font));

        jellySeekBar.getSeekBarLocation(System.out::println);

```

Here are the attributes you can specify through XML or related setters:

* start_range - Start (left) text of slider
* end_rage - end (right) text of slider
* sign_first_location  - Initial positon of "circle" in range .
* circle_color  - Color of circle inside main bar.
* main_color - Color of main bar.
* sign_txt_color - Color of text inside "circle".
* sign_Duration - Duration of "circle" rise in milliseconds.
* bubbles_Duration - Duration of "bubbles" remain in milliseconds.
* font - Font of number

