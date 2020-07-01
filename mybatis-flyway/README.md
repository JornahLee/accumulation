# mybatis & flyway db

## mybatis 核心元素
- spring配置文件:
  - 配置mapper映射文件路径
  - 配置entity别名简称
- 注解 @MapperScan  @Mapper
- *Mapper.xml
  - mapper标签的namespace 为当前mapper接口的全路径
  
- 常用

## flyway db
数据库版本控制工具
## 作用
- 在新环境中，只需建立空数据库，启动项目时，会根据sql文件自动建立所有的表
- 需要修改数据库时，新添加SQL文件即可

fly会判断数据库结构版本与history存储是否一致
**大致原理**

# free mybatis plugin
方便mapper与xml快速切换
并且集成了mybatis generator（database选项卡右键对应的数据库表-> mybatis generator）
