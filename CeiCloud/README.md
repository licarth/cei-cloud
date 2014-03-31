Introduction
------------

CeiCloud is a Supélec IT department project. Designed for experiments detailed in paper release [Bin packing algorithms applied to load balancing in a cloud environment](link!), this project is a framework to describe problems, implement several algorithms to solve these problems and compare their relative precision.

This framework covers :
*	Problem definitions
*	Instance definitions
*	Generation of instances of those problems
*	Algorithms definitions (for a certain problem)
*	Benchmarks to compare algorithms

This formalism has been defined in order to be used in paper realease mentioned above.

Build
-----

This project relies on Maven dependency management. The easiest way to import dependencies and build is to use Maven.


To do
----

To make this tool more complete, we could :

*	Enhance benchmarking classes (benckmark package) to automatically compare algorithm precision and output the results in a particular form
*	Create 
*	Use a logging API (instead of System.out.print!)

Authors & Contributors
-------

[Thomas Carli](mailto:thomascarli@gmail.com)

Examples
--------

Although this tool has been developped to evaluate the precision of VSCIFP problems defined in the paper release mentioned in the introduction, one can define its own Problem, Instances, Algorithms and Generators.

Let's run through a simple example. Let's say we are trying to assess the precision of Bin Packing Algorithms. A bin packing Problem class (BPP) could look like :

 
