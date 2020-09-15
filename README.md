# NBody

NBody is a 2-D animated simulation of planetary behaviors in the solar system. It was build using Java. 

## Installation

In case the java class files are not contained in the project package, run 

```bash
javac *.java
```
to compile all files in bash/terminal. However, there should be class files in /production folder. 
## Usage

There are a lot of planet data contained in the data package, and the planets.txt contains information about planets. Simply copy and run. For example,  

```bash
java NBody 15770000.0 25000.0 data/planets.txt
```
will output 

![NBody Demo] (https://github.com/jiaheyi-maggie/Nbody/blob/master/production/demo.gif)


Please make sure to update tests as appropriate.
