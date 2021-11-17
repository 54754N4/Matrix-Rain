# Matrix Screen Saver

Matrix screen saver that supports multi-monitor setups, written in `Java`.

**Note:** The first time you run the screen saver it will generate a `properties.conf` file that allows you to modify matrix parameters.

## Building

1. Compile the program into a runnable `.jar`.
2. Use `Launch4j` to compile into `.exe` (you can load the `MatrixScreenSaver.xml` configuration file into `Launch4J`). Then click on the cog to build either the wrapper or executable.
3. Rename the generated `.exe` to `.scr` and then double click to run.

## Setting as default

1. Move to `C:\Windows\system32`[^1]
2. Right click the `.scr` file and click install

[^1]: This is where default screen savers are located. Since `.scr` are also executables, windows won't allow screen savers that haven't been put there by an administrator. Also it won't be listed by default if it's not in that location.

## References

http://launch4j.sourceforge.net/docs.html

## Preview

This is the screenshot of the screen saver on a dual monitor setup (top and bottom).

![Matrix Screen Saver](./Preview.png)
