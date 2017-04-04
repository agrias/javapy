# javapy
Python wrapper for Java/Scala using native libraries

Jython (http://www.jython.org/) has a lot of code overhead especially when you need to use 3rd party python libraries.

Jep (https://github.com/mrj0/jep) requires managment of jni libraries which is a hassle. 

JavaPy allows you to invoke python using your local python installation from the JVM and get a String response. If you need access to objects this is probably not for you. Otherwise this seems to be the simplest and the most easy to understand solution to use python exclusive libraries in the JVM.
