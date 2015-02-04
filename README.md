Algorithm_practice_1
====================

Analysis of algorith, Princeton univercity

need to copy algs4 to ~/algs4

checkstyle-algs4 Path_to_java.java
findbug the same



//=======================================
howto compile and run java project in ubuntu from terminal: 	

if you have single class in your app called flights.java and all of your required jar are located at /home/ubuntu/test/libs/ then use this

javac -cp '.:/home/ubuntu/test/libs/*.jar' flights.java

and to run

java -cp '.:/home/ubuntu/test/libs/*.jar' flights


//========================================
how to enable/disable assertions

java -ea MyProgram
java -de MyProgram (default)
