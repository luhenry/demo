
%.class: %.java
	javac $<

lib%$(if $(TIERED),-tiered)$(if $(PARALLEL),-nong1).so: %.class
	jaotc --verbose --output $@ $(if $(TIERED),--compile-for-tiered) $(if $(PARALLEL),-J-XX:+UseParallelGC) -J-Daot.keep.objFile=true $*

.PHONY: run-%
run-%: lib%$(if $(TIERED),-tiered)$(if $(PARALLEL),-nong1).so %.class
	java $(if $(NOAOT),,-XX:+UnlockExperimentalVMOptions -XX:AOTLibrary=./$<) $(if $(PARALLEL),-XX:+UseParallelGC) $(JVMARGS) $*

