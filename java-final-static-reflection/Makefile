
%.class: %.java
	javac $<

.PHONY: run-%
run-%: %.class
	java $(JVMARGS) $*

