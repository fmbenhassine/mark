# Meet Mark

Mark is a simple CLI bookmark manager built with Spring Shell, powered by Spring Boot and backed by SQLite.
It stores your bookmarks in `~/.mark/bookmarks.db`. You can add, remove and list bookmarks with simple and intuitive commands:

```
$>java -jar mark-1.0.0.jar
  __  __            _
 |  \/  |          | |
 | \  / | __ _ _ __| | __
 | |\/| |/ _` | '__| |/ /
 | |  | | (_| | |  |   <
 |_|  |_|\__,_|_|  |_|\_\

Mark v1.0.0: The simple stupid CLI bookmark manager!
Built with Spring Shell (v2.0.0.RELEASE)
Powered by Spring Boot  (v2.0.0.RELEASE)

bookmark:>add google http://www.google.com
bookmark added
bookmark:>list
Bookmark{id=1, name='google', url='http://www.google.com'}
bookmark:>help
AVAILABLE COMMANDS

Built-In Commands
        clear: Clear the shell screen.
        exit, quit: Exit the shell.
        help: Display help about available commands.

Mark Commands
        add: Add a bookmark
        list: List all bookmarks
        remove: Remove a bookmark

bookmark:>exit
$>
```

Mark requires Java 8 to run. You can download the latest stable release from the [releases page](https://github.com/benas/mark/releases).

# Build from sources

To build Mark from sources, you need to use [maven](http://maven.apache.org):

```
$>git clone https://github.com/benas/mark
$>cd mark
$>mvn package
```

# License

Mark is released under the terms of the MIT License:

```
The MIT License

Copyright (c) 2020, Mahmoud Ben Hassine (mahmoud.benhassine@icloud.com)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
```
