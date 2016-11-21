################# 2016-11-17
提交了完整的Demo测试，启动文件：GameTest_MongoDB.java
LS工程结构说明：
LS-----
    |
    src-----代码包
         |
         CREATE_CLASS-----生成对象的主函数包
         |
         data-----外部文件导入的数据包
         |
         database-----数据库Mon包
         |
         domain-----数据域（一般存放的是一个具体对象所包含的Mon集合）
         |
         logic-----静态逻辑包
         |
         tool-----工具包
         |
         LSCollectionManage.java-----放置domain具体对象的集合
         |
         LSServerManage.java-----放置启动时需要加载的相关逻辑和数据的初始化
    |
    data-----数据文件
    |
    config------配置文件
    		|
    		log4j.properties-----log4j日志
    		|
    		log4j.xml-----预计后期使用xml替换properties
    		|
    		LS.properties-----LS服务器所需配置
    		|
    		MonClass.txt-----自动生成Mon对象的根文件
    		|
    		mybatis-config.xml-----mybatis配置文件
    
    
         


################# 2016-11-10
移植github基本完成，后续的注释以及之前代码的注释变更在后续更新，之后除了master版本后，会出现多个base版本的管理方式，master为稳定版本
目前由蓝港二研相关技术进行技术方向
test:GameTest为可运行的小测试程序。客户端由网易QT大神林枫协助调试

################# 2016-9-19
创办人EnglishName变更为:lightning lu.
工程从华娱SVN移植到github上，感谢华娱科技这么多年的硬件支持，非常感谢。
感谢：杨韬，张江，付海阔，符乐超，朱鹏程，凤飞，杨楠，刘楠，程继峰等人的技术支持。

################# 2014-9-1
GameCoreServer是lupengzhan,EnglishName:lulei Alix，创办。
公网SVN由华娱友情提供。
主要服务于游戏服务器核心部件。