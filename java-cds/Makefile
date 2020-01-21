
%.class: %.java
	javac $<

%.lst: %.class
	java -Xshare:off -XX:DumpLoadedClassList=$@ $*

%.jsa: %.lst
	java -Xshare:dump -XX:SharedClassListFile=$< -XX:SharedArchiveFile=$@

.PHONY: run-%
run-%: %.jsa %.class
	java -Xshare:on -XX:SharedArchiveFile=$< $(JVMARGS) $*
