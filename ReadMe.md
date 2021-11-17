# Matrix Rain

Matrix screen saver that supports multi-monitor setups, written in `Java`.

**Note 1:** The first time you run the screen saver it will generate a `properties.conf` file that allows you to modify matrix parameters.

**Note 2:** JVM arguments to pass when executing are `-Xms64m -Xmx64m` to set the initial and maximum heap size to 64mb. On my dual monitor setup it's enough, however for more than that you might need to tweak these a bit (especially when building with `Launch4j`)

## Building

1. Compile the program into a runnable `.jar`.
2. Make sure you download a `JRE` and put it near the `Launch4J` executable so it can find it.
3. Use `Launch4j` to compile into `.exe` (you can load the `MatrixScreenSaver.xml` configuration file into `Launch4J`). Then click on the cog to build either the wrapper or executable.
4. Rename the generated `.exe` to `.scr` and then double click to run.

## Setting as default

1. Move to `C:\Windows\system32`[^1]
2. Right click the `.scr` file and click install

[^1]: This is where default screen savers are located. Since `.scr` are also executables, windows won't allow screen savers that haven't been put there by an administrator. Also it won't be listed by default if it's not in that location.

## Preview

This is the screenshot of the screen saver on a dual monitor setup (top and bottom).

![Matrix Preview](https://github.com/54754N4/Matrix-Rain/blob/master/Preview.png?raw=true)

## References

http://launch4j.sourceforge.net/docs.html
