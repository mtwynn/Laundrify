JFLAGS = -g
JC = javac -d .
JVM = java
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $.src/*.java

CLASSES = \
	src/Main.java

MAIN = Main

run: classes
	$(JVM)	$(MAIN)

default: classes

classes: $(CLASSES:.java=.class)

clean:
	 $(RM) *.class
