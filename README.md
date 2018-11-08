#lmt-code-generator代码生成器
基于Velocity实现，自动生成基础mapper/扩展mapper/基础service/扩展service;
目前只实现了对mysql/oracle数据库的支持，其他数据库如有需要请自行添加，或联系我进行实现;
使用中如有问题，请留言或邮件沟通：adu003@163.com

##使用方法
根据数据库类型调整main/resource下配置文件
application.properties针对oracle数据库
application-mysql.peoperties针对mysql数据库

运行OracleGenerateServiceTest方法生成针对Oracle数据库的mapper文件
运行MyqlGenerateServiceTest方法生成针对MySQL数据库的mapper文件