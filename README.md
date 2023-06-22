# Java NIO2 API

Java NIO2 API is a project that utilizes the newest version of NIO2 to provide high-speed reading of CSV files with millions of records in seconds. Additionally, it can detect if a file has been changed.

## Features

- Uses the latest version of NIO2
- Implements the Factory and Template Method design patterns
- Can read CSV files with millions of records quickly
- Can detect if a file has been changed

## Factory Pattern
The Factory pattern is used to create objects without specifying the exact class of object that will be created. In this project, the Factory pattern is used to create instances of `CSVReaderFactory`   class.

## Template Method Pattern
The Template Method pattern defines the skeleton of an algorithm in a base class, allowing subclasses to provide specific implementations of certain steps. In this project, the Template Method pattern is used in the `CSVReaderTemplate` class to provide a framework for reading and writing CSV files.
## Installation

To use Java NIO2 API, follow these steps:

1. Download the latest release from the [My Github Repository](https://github.com/PhucNghi176/NIO2).
2. Add the library to your Java project's classpath.
3. Import the necessary packages in your code.

```java
import com.example.nio2api.NIO2CSVReader;
```

## Usage

Once you have installed Java NIO2 API and imported the necessary packages, you can use it in your code to read CSV files.
You need to change the file path in Main.Java
```java
String CSV_FILE_PATH = "/* Your file path location */";
String IMPORT_FOLDER_PATH = "/* Your folder you want to import */";;
```

## License

Java NIO2 API is licensed under the MIT License. See LICENSE for more information.
