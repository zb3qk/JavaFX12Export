# JavaFX12Export
This is a project exploring the ability to use JavaFX 12 in conjunction with Gradle, all running on Java 12. 
This process has become a bit more complicated due to the exclusion of JavaFX from the Java SDK, but using a few tricks, it can be done.
The output of this project should be to have a runnable jar file which includes all the files necessary, and then convert it to an exe to be run on any Windows device.

## The Process
To include all necessary files in the jar file, this jar will have to be what is called a <i>fat jar</i>, which means it includes all 
necessary libraries to run the file. This is where a build tool like Gradle is very useful. Gradle allocates all these libraries, and such
such a recursive copy, all necessary files in the Gradle project will be placed in the jar file.

```Gradle
jar {
    manifest {
        attributes 'Main-Class': 'hellofx.Launcher'
    }
    from {
        configurations.runtimeClasspath.collect { it.isDirectory() ? it : zipTree(it) }
    }
}
```

This fat jar will also include any JavaFX 12 libraries that are included in the Gradle Project.

An error I ran into was that the jar file could only be run from the command line due to an inconsistincy with Java SE. This is why I 
converted this file into a Windows Executable, as described below.

## JavaFX 12
Using the guide on the <a href="https://openjfx.io/openjfx-docs/#gradle">JavaFX 12 website</a>, you can set up JavaFX 12 in gradle relatively easily.
The adjustment I made was to add a few more modules to be included from JavaFX to this Gradle Project.

```Gradle
javafx {
    version = "12.0.1"
    modules = [ 'javafx.controls','javafx.fxml','javafx.media' ]
}
```

Be sure to remember to also add these modules to your ```module-info.java``` file as described in the setup tutorial.
If you want a more general JavaFX 12/Gradle project, take a look at this <a href="https://github.com/openjfx/samples/tree/master/HelloFX/Gradle">OpenJFX example</a>.

## Resource Acquisition
I have attempted to use the following, safer and reproducible structure to acquire a media file in the project directory to no avail.
```Java
URL loc = this.getClass().getClassLoader().getResource("src/main/resources/hellofx/Blend_W-gladRLvno7U.mp4");
```

Although using a more hardcoded path method, the media file was able to be retrieved. This worked for both relative and absolute 
file paths when running the Java file, but when compressing the project to the jar format, only the absolute path worked.
```Java
String absolutePath = Paths.get("C:\\Users\\Student\\Google Drive\\Second Year\\Java Projects\\strangeExe\\src\\main\\resources\\hellofx\\Blend_W-gladRLvno7U.mp4").toUri().toString();
```

## To .exe
Using the <a href="http://launch4j.sourceforge.net/">Launch4J</a> you may convert your jar files into Windows executable files for easy
distribution. 

