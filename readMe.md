# Overview
Finding duplicate data from file.

From Main.java, there are 2 files that program trying to process(student.csv and teacher.csv).

* student.csv
  * have 5 rows
  * data is duplicate when at least 2 matched from id, dob or idType + idNumber
* teacher.csv
  * have 2 rows
  * data is duplicate when name is matched
```
        FileProcessor<Student> studentFileProcessor = new StudentFileProcessor();
        studentFileProcessor.readFileAndFindDuplicate();

        FileProcessor<Teacher> teacherFileProcessor = new TeacherFileProcessor();
        teacherFileProcessor.readFileAndFindDuplicate();
```


# Java class and Purpose

| Java Class                |                                          Purpose                                           |
|---------------------------|:------------------------------------------------------------------------------------------:|
| FileProcessor.java        |        Abstract class that able to read file, find duplicate and print duplicate id        |
| Main.java                 |                              Main class with executable code                               |
| TeacherFileProcessor.java | Sub-class from FileProcessor.java. Has its own definition of duplicate and file separators |
| StudentFileProcessor.java | Sub-class from FileProcessor.java. Has its own definition of duplicate and file separators |

# Problem
1. How to ensure that the file must contain id ?
2. How to define duplicate data ? Different files may have different definitions of duplicate
3. What if different files have different separators ?

# Solution - 1
From FileProcessor.java declaration, we can identify that the T must extend from Person.java. Person.java has id field.
```
abstract class FileProcessor<T extends Person>
```
```
    protected String id;

    public Person(String id) {
        this.id = id;
    }
```

# Solution - 2
FileProcessor.java will iterate lines from a given file and cache it into ArrayList. Hence, its recommend to override equal method from you generic class,T.

If it equals, then it consider as duplicate
```
@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(name, teacher.name);
    }
```

# Solution - 3
A class extend from FileProcessor.java, can overide getFileDelimiter(). 

By default, the separator is "," and example below define the file separator is "|"
```
@Override
public String getFileDelimiter() {
return "-";
}
```

# Location for csv file
Its recommend to place csv file into resource folder

