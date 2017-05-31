#!/bin/sh  
java -jar yunqiu_upload-0.0.1-SNAPSHOT.jar & #注意：必须有&让其后台执行，否则没有pid生成  
echo $! > yunqiu_upload.pid #将jar包启动对应的pid写入文件中，为停止时提供pid