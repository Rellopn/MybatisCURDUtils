## docker 下修改 mysql sql_mode和配置文件  时间和宿主机同步  用户设置

进入容器查看mysql配置

docker exec -it mysql-docker /bin/bash

cat /etc/mysql/my.cnf

复制docker mysql的配置，退出容器，在外部新建个my.cnf，粘贴刚才复制的内容，在尾部追加sql_mode配置。

```shell
# Copyright (c) 2017, Oracle and/or its affiliates. All rights reserved.
#
# This program is free software; you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation; version 2 of the License.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program; if not, write to the Free Software
# Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA

#
# The MySQL  Server configuration file.
#
# For explanations see
# http://dev.mysql.com/doc/mysql/en/server-system-variables.html

[mysqld]
pid-file        = /var/run/mysqld/mysqld.pid
socket          = /var/run/mysqld/mysqld.sock
datadir         = /var/lib/mysql
secure-file-priv= NULL
# Disabling symbolic-links is recommended to prevent assorted security risks
symbolic-links=0
sql_mode      = STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION
# Custom config should go here
!includedir /etc/mysql/conf.d/

```

用复制命令复制到容器对应的目录

```shell
 docker cp my.cnf mysql:/etc/mysql/
```

重启mysql服务

### 用户设置

[用户设置](https://www.jianshu.com/p/256a5f76fc85)

ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY 'password';

### 时间和宿主机同步

[时间和宿主机同步](https://www.cnblogs.com/kevingrace/p/5570597.html)

