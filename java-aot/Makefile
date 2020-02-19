
# make run-HelloWorld JVMARGS="-XX:+PrintCompilation -XX:+PrintAOT"
# make run-RandomSum  JVMARGS="-XX:+PrintCompilation -XX:+PrintAOT"
# make run-RandomSum  JVMARGS="-XX:+PrintCompilation -XX:+PrintAOT" AOT=1
# make run-RandomSum  JVMARGS="-XX:+PrintCompilation -XX:+PrintAOT" AOT=1 TIERED=1

%.class: %.java
	javac $<

lib%$(if $(TIERED),-tiered)$(if $(PARALLEL),-parallelgc).so: %.class
	jaotc --verbose --output $@ $(if $(TIERED),--compile-for-tiered) $(if $(PARALLEL),-J-XX:+UseParallelGC) $*

.PHONY: run-%
run-%: $(if $(AOT),lib%$(if $(TIERED),-tiered)$(if $(PARALLEL),-parallelgc).so) %.class
	java $(if $(AOT),-XX:+UnlockExperimentalVMOptions -XX:AOTLibrary=./$<) $(if $(PARALLEL),-XX:+UseParallelGC) $(JVMARGS) $* | grep "^\|$*" --color='always'

