## RAP2 前后端配置--centos7.6+mysql 8.0+redis5.0+nodejs 10

---

### 后端配置  RAP2-DELOS

1. 安装redis和mysql,分别启动。

``` docker pull mysql```

```docker pull redis```
```docker run --name mysql -v md:/var/lib/mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 -d mysql```

```docker run --name redis -v redisconfig:/usr/local/etc/redis/redis.conf -p 6379:6379 -d redis --requirepass "a65tcTVZ3EQ2YNzX"```

2. 进入到mysql容器中创建rap2所需的数据库

``` docker exec -it mysql bash```

```mysql -uroot -p123456```

```mysql -e 'CREATE DATABASE IF NOT EXISTS RAP2_DELOS_APP DEFAULT CHARSET utf8 COLLATE utf8_general_ci'```

3. clone代码库

``` git clone https://github.com/thx/rap2-delos```

```cd rap2-delos && npm install```

4. 配置。因为是直接用了，就只编辑生产模式的就行了```src/config/config.prod.ts```

   ``` vim src/config/config.prod.ts ```

   配置如下

   ```typescript
   import { IConfigOptions } from "../types";
   
   // 先从环境变量取配置
   let config: IConfigOptions =  {
       version: '2.3',
       serve: {
           port: (process.env.EXPOSE_PORT && parseInt(process.env.EXPOSE_PORT)) || 8080,
       },
       keys: ['some secret hurr'],
       session: {
           key: 'rap2:sess',
       },
       db: {
           dialect: 'mysql',
           host: 'localhost',
           port: (process.env.MYSQL_PORT && parseInt(process.env.MYSQL_PORT)) || 3306,
           username: process.env.MYSQL_USERNAME || 'root',
           password: process.env.MYSQL_PASSWD || '123456',
           database: process.env.MYSQL_SCHEMA || 'RAP2_DELOS_APP',
           pool: {
               max: 80,
               min: 0,
               idle: 20000,
               acquire: 20000,
           },
           logging: false,
       },
       redis: {
           host: process.env.REDIS_URL || 'localhost',
           port: (process.env.REDIS_PORT && parseInt(process.env.REDIS_PORT)) || 6379,
           password: '123456',     
       },
       mail: {
         host: 'smtp-mail.outlook.com',
         port: 587,
         secure: false,
         auth: {
             user: 'rap2_notify@outlook.com',
             pass: ''
         }
       },
       mailSender: 'rap2_notify@outlook.com',
   }
   export default config
   ```

   5. #### 安装&&编译typescript

      ```shell
      npm install -g pm2
      npm install -g typescript
      npm run build
      ```

      如果出现any的错误，打开```tsconfig.ts```,设置```"noImplicitAny": false```

6. #### 初始化数据库表&&启动

   ```shell
   npm run create-db
   ```

   ```shell
   npm start
   ```

   #### 一些问题

   mysql： 因为js库不支持最新的mysql，所以需要设置密码的加密方式

   ```ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY '你的密码';```

   ---

   ### 前端

   1. 进入包目录运行```npm install```

      可能出现问题:

      # npm install  【如果报下面错误】

      错误

      gyp ERR! configure error 
      gyp ERR! stack Error: EACCES: permission denied, mkdir '/data/wwwroot/rap2-dolores/node_modules/node-sass/.node-gyp'

      错误解决

      1、在项目根目录创建.npmrc文件，复制下面代码到该文件
      phantomjs_cdnurl=http://cnpmjs.org/downloads
      sass_binary_site=https://npm.taobao.org/mirrors/node-sass/
      registry=https://registry.npm.taobao.org

      2、删除之前安装node-sass包
      # npm uninstall node-sass
      3、重新安装
      # npm install -g node-sass

      如果上面还是不成功还是报错 
      ERR! configure error 
      gyp ERR! stack Error: EACCES: permission denied, mkdir '/data/wwwroot/rap2-dolores/node_modules/node-sass/.node-gyp'

      安装淘宝 npm
      # npm install -g cnpm --registry=https://registry.npm.taobao.org
      使用cnpm 安装node-sass
      # cnpm install -g node-sass

      执行成功后 再执行npm install

      2. 编辑配置地址

         ```vim src/config/config.prod.js```

``` typescript
module.exports = {
  serve: 'http://47.92.136.223:8080',//必须是外网ip
  keys: ['some secret hurr'],
  session: {
    key: 'koa:sess'
  }
}
```

3. 运行```npm run build```,之后可以把build目录放到nginx下。