# vue学习指南

## 学习资料网址

官网：https://cn.vuejs.org/

学习视频：https://www.bilibili.com/video/BV1vE411871g?from=search&seid=11663517240025560042

参考文档地址：https://vue.docschina.org/v2/cookbook/debugging-in-vscode.html

### [vue安装教程](http://note.youdao.com/noteshare?id=0cd6f17aa646d1c781691b2c4d0c839a&sub=BB8295E549A649C4A402133FF7E9451C)
环境配置：

```java
NODE_PATH
D:\Program Files\nodejs\node_global\node_modules
```

![](imgs\vue-c01.png)

![](imgs\vue1.png)



### vue调试

[vue-devtools-5.11]([vue-devtools](https://github.com/vuejs/vue-devtools))

参考网址：https://www.jianshu.com/p/63f09651724c

主要步骤：

```
1.git clone https://github.com/vuejs/vue-devtools.git
2.npm install //如果太慢的话，可以安装一个cnpm, 然后命令换成 cnpm install
3.npm run build
4.游览器输入地址“chrome://extensions/”进入扩展程序页面，点击“加载已解压的扩展程序...”按钮，选择vue-devtools>shells下的chrome文件夹。
/**
*如果看不见“加载已解压的扩展程序...”按钮，则需要勾选“开发者模式”。
*/
```

vue-devtools如何使用,F12调试

![msg](imgs\vd05.png)



### NPM常用命令

```nodejs
npm init : 创建一个package.json文件，包括名称、版本、作者等信息；
npm install <name> : 安装nodejs的依赖包；
npm install <name> -g : 将包安装到全局环境中；
npm install <name> --save : 安装的同时，将包的信息写入package.json中；
npm remove <name> : 移除包；
npm update <name> : 更新包；
npm ls : 查看当前安装的所有包；
npm root : 查看当前包的安装路径；
npm root -g : 查看全局安装包的路径；
npm help <topic> : 在默认浏览器中打开指定命令的文档页面，如果topic为空，则列出npm的所有命令；

加快下载速度
安装nrm工具 npm install nrm -g
查询下载源 nrm ls
切换数据源 nrm use taobao

```

## VSCODE使用指南

### vscode安装

https://blog.csdn.net/weixin_43748812/article/details/84960266

![msg](imgs\vcode1.PNG)

### 常用插件

vetur

### vscode调试vue

[vscode调试](https://blog.csdn.net/lilinoscar/article/details/82287108)

vscode如何调试vue项目，通过F5进行断点调试，类似与vs进行调试，vscode需要安装插件以及配置launch.json文件。

步骤一：

1.找到“扩展”或者按快捷键“Ctrl+Shift+X”，如下图，输入Debugger for Chrome查找，并安装。

2.项目里创建“launch.json”文件，内容如下：

```json
{

"name": "chrome",

"type": "chrome",

"request": "launch",

"url": "http://localhost:8080/",

"webRoot": "${workspaceRoot}"

}
```

3.在“package.json”文件里的"scripts"中添加："dev": "vue-cli-service serve --open" 此命令是启动服务。如下图：

![msg](imgs\vd01.png)

4.按“Ctrl+Shift+Y” 调出终端，输入：npm run dev

5.找到“Ctrl+Shift+D”，点击绿色按钮开始调试，会弹出google浏览器访问网站，在你想要的地方添加断点，如下图：

![msg](imgs\vd02.png)

![msg](imgs\vc02.png)













## 项目实战源码

链接：https://pan.baidu.com/s/1wr8I9zdCfdTezBIQ43hylQ 
提取码：love

### 脚手架

执行安装：

**npm install @vue/cli -g**

查看安装vue --version

**npm install webpack -g**

查看安装webpack -v

执行：vue ui

### element-ui

https://element.eleme.cn/#/zh-CN/guide/design

### 商城实战

**项目后台vue_api_server启动**

- shift+右键 打开PowerShell

- npm install

- node ./app.js





STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION