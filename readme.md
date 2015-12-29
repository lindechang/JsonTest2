各个JSON版本性能测试
一、JSON测试条件（版本要求为最新稳定版）

    1、Jackson-2.6.4 (2015-12-08最新稳定版本)
    2、Fastjson-1.2.7（2015-9-01最新稳定版本）

二、生成和解析json数据测试

   1.生成json数据测试：测试三组数据：10000次 ，50000次，100000次

    10000次：

    *装配10000组数据所用时间（秒）：0.184  
    *fastjson所用时间（秒）：0.325
    *jackson所用时间（秒）：0.135

    50000次：
   
    *装配50000组数据所用时间（秒）：0.503
    *fastjson所用时间（秒）：0.939
    *jackson所用时间（秒）：0.315

    100000次：

    *装配100000组数据所用时间（秒）：1.028
    *fastjson所用时间（秒）：1.712
    *jackson所用时间（秒）：0.448

    

   2.解析json数据测试：测试三组数据：10000次 ，50000次，100000次

   10000次: 

   *Fastjson所用时间（秒）：0.249
   *Jackson所用时间（秒）：0.18

   50000次:

   *Fastjson所用时间（秒）：0.332
   *Jackson所用时间（秒）：0.264

   100000次:

   *Fastjson所用时间（秒）：0.372
   *Jackson所用时间（秒）：0.282
   

三、下载
json下载地址:
http://sourceforge.net/projects/json-lib/files/json-lib


fastjson下载地址:
http://repo1.maven.org/maven2/com/alibaba/fastjson/

jackson下载地址:
http://wiki.fasterxml.com/JacksonDownload



