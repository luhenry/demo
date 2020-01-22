
# export PATH="/usr/lib/jvm/jdk-13.0.2+8/bin/:$PATH"
# make run-HelloWorld JVMARGS="-Xlog:class+load=info"
# make run-HelloWorld JVMARGS="-Xlog:class+load=info" CDS=1
# make run-HelloWorld JVMARGS="-Xlog:class+load=info" CDS=1 APPCDS=1
# make run-HelloWorld JVMARGS="-Xlog:class+load=info" CDS=1 DYNCDS=1
# make run-HelloWorld JVMARGS="-Xlog:class+load=info" CDS=1 APPCDS=1

%.class: %.java
	javac $<

%-manifest.txt:
	echo "Main-Class: $*" > $@

%.jar: %.class %-manifest.txt
	jar cfm $@ $*-manifest.txt $<

%.lst: %.jar
	java -Xshare:off -XX:DumpLoadedClassList=$@ -jar $< $*

%.jsa: %.jar %.lst
	java -Xshare:dump -XX:SharedClassListFile=$*.lst -XX:SharedArchiveFile=$@ -jar $<

.PHONY: run-%
run-%: %.jar $(if $(APPCDS),%.jsa)
	java -Xshare:$(if $(CDS),on,off) $(if $(APPCDS),-XX:SharedArchiveFile=$*.jsa) $(if $(DYNCDS),-XX:ArchiveClassesAtExit=$*.jsa) $(JVMARGS) -jar $< $*
