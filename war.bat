@echo 如果发布到正式环境请加参数 -Pproduct
@echo 可选参数包括 -Ptest 打测试环境war包 -Pproduct 打生产环境war包 -Pliux打linux环境下的war包
::注释
:war:manifest
:设置变量 -Dclient=NL
@echo 示例:
@echo war.bat -e "-Dclient=NL" 
@echo  -e 打印详细错误 "-Dclient=NL" 设置变量 trunk为NL
@MAVEN_OPTS为java虚拟机参数配置如:MAVEN_OPTS="-Xms128m -Xmx512m"
::
set MAVEN_OPTS="-Dfile.encoding=UTF-8"
mvn clean scm:update process-classes  war:war  %1 %2 %3 %4 %5 %6 %7 %8 %9
pause