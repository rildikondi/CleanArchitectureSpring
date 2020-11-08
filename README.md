# Clean Architecture

Clean Architecture with multimodules written in Java and the web layer is build with the help of Spring.

<img src="https://blog.cleancoder.com/uncle-bob/images/2012-08-13-the-clean-architecture/CleanArchitecture.jpg" alt="Clean Architecture img">



Implementation schema of  Gauss elimination for linear equations with Clean Architecture.  
Clean Architecture main directory contains all the modules which realize the working of application.
webapplication, inmemorydb and applicationdi modules are part of the Framework and drivers layer.
interfaceadpters module is part of Interface Adapters layer.
usecases module is part of Application Business Rules layer.
entities module is part of Enterprise Business Rules layer.
<img src="https://github.com/rildikondi/CleanArchitectureSpring/blob/clean_architecture_by_layer/images/main_module_schema.jpg" >


applicationdi module uses inmemorydb, usecases, interfaceadpters modules to inject the dependencies that are needed in this application.

<img src="https://github.com/rildikondi/CleanArchitectureSpring/blob/clean_architecture_by_layer/images/applicationdi_schema.jpg" >


Webapplication module is part of framework and drivers layer.
Framework and drivers layer is the outermost layer and depends on layers below it such as interface adapters and usecases.
Webapplication module uses spring framework. The endpoints which are in this layer with help of spring annotations build together
the entrypoints for the input.

<img src="https://github.com/rildikondi/CleanArchitectureSpring/blob/clean_architecture_by_layer/images/webapplication_schema.jpg" >

## References:
[https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)

[https://github.com/grant-burgess/clean-architecture-example-java-spring-boot)
