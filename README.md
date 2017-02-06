# ASM-Instrumentation
Java bytecode manipulation and analysis framework

ASM is an all purpose Java bytecode manipulation and analysis framework. It can be used to modify existing classes or dynamically generate classes, directly in binary form. This demo will guide you to have basic understand how ASM works and what will ASM do.

## Environment
The ASM bytecode manipulation framework is written in Java. Java Runtime Environment is required.

### download ASM
You can download the latest binary file from the [ObjectWeb Forge](http://forge.ow2.org/projects/asm/). This demo uses the version 5.2.

### Eclipse plugin
Bytecode Outline plugin for Eclipse shows disassembled bytecode of current Java editor or class file. The Bytecode Outline plugin can be installed from the Eclipse Update Manager with the ObjectWeb Eclipse Update Site http://download.forge.objectweb.org/eclipse-update/

## Java Bytecode
Here is a quick review in case you are not familiar with Java Bytecode. Java Bytecode is an intermediate code between Java source code and assembly code. Java source code `.java` file can be compiled into Bytecode `.class` file and run on where any computers have a Java Runtime Environment.

[image]

As mentioned before, ASM framework includes tools to help you translate between those codes. Bytecode Outline shows disassembled bytecode of current Java editor or class file. Unlike `javap`, ASMifier on compiled classes allows you to see how any given bytecode could be generated with ASM.

## Reflection and Instrumentation
Reflection means the ability for a program to examine, introspect, and modify its own structure and behavior at runtime.<sup>[[1](http://www2.parc.com/csl/groups/sda/projects/reflection96/docs/malenfant/malenfant.pdf)]</sup> However refelction is not sufficient in many cases such as source in non-Java language. ASM framework uses a visitor-based approach to generate bytecode and drive transformations of existing classes. 

## Visitor Pattern
ASM utilizes [Visitor Pattern](https://en.wikipedia.org/wiki/Visitor_pattern) to accomplish dynamic dispatch on object and its behavior. 
The Core package can be logically divided into two major parts:
* Bytecode producers, such as a ClassReader or a custom class that can fire the proper sequence of calls to the methods of the above visitor classes.
* Bytecode consumers, such as writers (ClassWriter, FieldWriter, MethodWriter, and AnnotationWriter), adapters (ClassAdapter and MethodAdapter), or any other classes implementing the above visitor interfaces.

## Demo

## Reference
* "ASM: a code manipulation tool to implement adaptable systems", E. Bruneton, R. Lenglet and T. Coupaye, Adaptable and extensible component systems, November 2002, Grenoble, France.
* "Using ASM framework to implement common bytecode transformation patterns", E. Kuleshov, AOSD.07, March 2007, Vancouver, Canada.
* [Official Tutorial for ASM 2.0.](http://asm.ow2.org/doc/tutorial-asm-2.0.html)
* [Instrumenting Java Bytecode with ASM](http://web.cs.ucla.edu/~msb/cs239-tutorial/)
* Diving into Bytecode Manipulation: Creating an Audit Log with ASM and Javassist
* http://www.tomsquest.com/blog/2014/01/intro-java-agent-and-bytecode-manipulation/
