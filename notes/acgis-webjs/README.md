# arcgis学习指南

## [ArcGIS JS API下载网址](https://developers.arcgis.com/downloads/#javascript)

![](D:\workspaces\git\github\KnowledgePoints\notes\acgis-webjs\images\acg-01.png)

## [ArcGIS JS API学习路线](https://www.bilibili.com/video/BV1X7411C7Tf)

![](D:\workspaces\git\github\KnowledgePoints\notes\acgis-webjs\images\acg-02.png)

## vue整合案例

![](D:\workspaces\git\github\KnowledgePoints\notes\acgis-webjs\images\acg-03.png)

![](D:\workspaces\git\github\KnowledgePoints\notes\acgis-webjs\images\acg-04.png)

## ArcGIS API for JavaScript开发初探——基础知识

```
1、前言
在ArcGIS Web API开发体系中一共有四大类，分别为：

ArcGIS API for Flex
ArcGIS API for JavaScript
ArcGIS API for REST
ArcGIS API for Silverlight
其中Flex和Silverlight为富客户端Ria技术体系，但是随着历史潮流的发展，Html5的完善，这两项技术也将可能逐渐沉寂在历史的浪潮里，所以笔者建议，对于想学习ArcGIS前端开发技术的同学们，尤其是还没有开发经验的同学，建议直接学习JavaScript相关技术体系内容，避免走了弯路，具体详细原因请百度之。

 

2、基础知识
2.1、ArcGIS API for JavaScript是什么？
ArcGIS JavaScript API是一个在web应用中嵌入GIS地图和任务的轻量级的方式。通过这个API可以很容易地建立和部署应用。可以在应用中使用所有需要的ArcGIS Server上可用的地图和工具。

2.2、ArcGIS API for JavaScript可以做什么？
快速创建交互式地图应用
使用ArcGIS Server 的Rest API,实现显示查询分析等功能
调用ArcGIS Server的GP服务提供专业的分析结果
可以同时调用多个ArcGIS Server的服务，轻松实现服务聚合
2.3 、特点
一切基于服务
简单易学的语言基础
多种的多样的开发方式
丰富的网络资源
基于功能强大的Dojo JavaScript工具包
开发和部署都是完全免费的
2.4、组成地图的几个基本要素
Map，Layer，Graphic，Geometry，Symbol，Attribute

Map——地图容器

Layer——图层：包括GraphicLayer，ArcGISDynamicMapServiceLayer，ArcGISImageServiceLayer，ArcGISTiledMapServiceLayer，FeatureLayer，LabelLayer，WMSLayer等。

Geometry——矢量要素信息，包括点（Point），线（Polyline），面（Polygon）等。

Symbol——要素符号化信息，点线面的颜色大小形状等控制。包括：SimpleMarkerSymbol, PictureMarkerSymbol，SimpleLineSymbol, CartographicLineSymbol，SimpleFillSymbol, PictureFillSymbol， TextSymbol等。

Attribute——要素属性信息

以上几个要素的关系梳理如下：

例如：如何显示一个点信息，由Geometry，Symbol，Attribute共同组建一个Graphic，将Graphic加入到Layer中，再将Layer加入Map中
登录
gis-luq
移动GIS技术研究

ArcGIS API for JavaScript开发初探——基础知识
1、前言
在ArcGIS Web API开发体系中一共有四大类，分别为：

ArcGIS API for Flex
ArcGIS API for JavaScript
ArcGIS API for REST
ArcGIS API for Silverlight
其中Flex和Silverlight为富客户端Ria技术体系，但是随着历史潮流的发展，Html5的完善，这两项技术也将可能逐渐沉寂在历史的浪潮里，所以笔者建议，对于想学习ArcGIS前端开发技术的同学们，尤其是还没有开发经验的同学，建议直接学习JavaScript相关技术体系内容，避免走了弯路，具体详细原因请百度之。

 

2、基础知识
2.1、ArcGIS API for JavaScript是什么？
ArcGIS JavaScript API是一个在web应用中嵌入GIS地图和任务的轻量级的方式。通过这个API可以很容易地建立和部署应用。可以在应用中使用所有需要的ArcGIS Server上可用的地图和工具。

2.2、ArcGIS API for JavaScript可以做什么？
快速创建交互式地图应用
使用ArcGIS Server 的Rest API,实现显示查询分析等功能
调用ArcGIS Server的GP服务提供专业的分析结果
可以同时调用多个ArcGIS Server的服务，轻松实现服务聚合
2.3 、特点
一切基于服务
简单易学的语言基础
多种的多样的开发方式
丰富的网络资源
基于功能强大的Dojo JavaScript工具包
开发和部署都是完全免费的
2.4、组成地图的几个基本要素
Map，Layer，Graphic，Geometry，Symbol，Attribute

Map——地图容器

Layer——图层：包括GraphicLayer，ArcGISDynamicMapServiceLayer，ArcGISImageServiceLayer，ArcGISTiledMapServiceLayer，FeatureLayer，LabelLayer，WMSLayer等。

Geometry——矢量要素信息，包括点（Point），线（Polyline），面（Polygon）等。

Symbol——要素符号化信息，点线面的颜色大小形状等控制。包括：SimpleMarkerSymbol, PictureMarkerSymbol，SimpleLineSymbol, CartographicLineSymbol，SimpleFillSymbol, PictureFillSymbol， TextSymbol等。

Attribute——要素属性信息

以上几个要素的关系梳理如下：

例如：如何显示一个点信息，由Geometry，Symbol，Attribute共同组建一个Graphic，将Graphic加入到Layer中，再将Layer加入Map中。

 

3、参考链接
https://developers.arcgis.com/javascript/

https://developers.arcgis.com/javascript/jsapi/

```

https://developers.arcgis.com/python/guide/

## 学习资源

[arcgis开发指南](https://developers.arcgis.com/javascript/latest/)

[ArcGIS API for JavaScript 入门教程目录](https://www.cnblogs.com/onsummer/p/9080204.html)

[ArcGIS API For JS官方文档解析教程](https://blog.csdn.net/gisdoer/article/details/88079171)

[X北辰北](https://xuqwblog.blog.csdn.net/)

https://xuqwblog.blog.csdn.net/article/details/108402072

react javaScript库

https://react.docschina.org/

## 参考文档网站

```
开发者网站
https://developers.arcgis.com/javascript/index.html
ArcGIS通用数据类型
https://developers.arcgis.com/documentation/common-data-types/overview-of-common-data-types.htm
Esri/esri-loder
https://github.com/Esri/esri-loader/
Esri/resource-proxy
https://github.com/Esri/resource-proxy/releases
ArcGIS REST API
https://developers.arcgis.com/rest/

重要参考
https://resources.arcgis.com/en/help/
https://resources.arcgis.com/zh-cn/help/


```

![](D:\workspaces\git\github\KnowledgePoints\notes\acgis-webjs\images\yzr\yzr056.png)

### 记录:GIS学习资源

```
https://zhuanlan.zhihu.com/c_165676639

https://desktop.arcgis.com/zh-cn/arcmap/10.3/map/working-with-layers/adding-layers-to-a-map.htm

```

### 学习博客（重要）

https://www.cnblogs.com/onsummer/p/9091052.html

### ArcGIS API for JavaScript之基础篇

https://blog.csdn.net/weixin_41665391/article/details/79342758

### ArcGIS api for javascript全套教程

https://www.cnblogs.com/xiaotian-222/category/960683.html

## 学习视频

https://edu.csdn.net/course/detail/27649?pre_view=1

https://space.bilibili.com/52862082

较贵只是做下记录：

https://ke.qq.com/course/2993375?tuin=1d7d9cb4

### ArcGIS API for JavaScript开发技术视频教程全集

https://malagis.com/arcgis-for-javascript-webgis-develop-vedio-summary.html?replyTo=2777

### ArcGIS API For Javascript入门视频（易智瑞）

https://www.bilibili.com/video/BV1Mf4y1D7Eb/?spm_id_from=333.788.videocard.1

图片地址images/yzr

### 从零开始编写GIS JS API系列视频课程

https://www.bilibili.com/video/BV1qK411N7sr/?spm_id_from=333.788.videocard.15

### 第九章 ArcGIS API for JavaScript应用开发

https://www.bilibili.com/video/BV1pi4y187oL/?spm_id_from=333.788.videocard.1

![](D:\workspaces\git\github\KnowledgePoints\notes\acgis-webjs\images\ArcGIS API for JavaScript应用开发\003.png)

https://community.esri.com/community/developers/web-developers/arcgis-api-for-javascript/

http://zhihu.geoscene.cn/

http://zhihu.geoscene.cn/

https://developers.arcgis.com/downloads/api-and-sdks?product=javascript

### 不一样的前端，JavaScript之arcgis api教程

https://www.bilibili.com/video/BV11D4y197wM?from=search&seid=10240106064419986228

### ArcGIS for JavaScript

https://edu.csdn.net/course/detail/1071

### ArcGIS教程-ArcGIS API for JavaScript教程

https://list.youku.com/albumlist/show/id_27346469.html?sf=10401

https://www.bilibili.com/video/BV1r4411X792/?spm_id_from=333.788.videocard.8

### arcgis api for js入门开发系列二不同地图服务展示(含源代码)

http://zhihu.geoscene.cn/question/13480



https://www.bilibili.com/video/BV1Sy4y127xp/?spm_id_from=333.788.videocard.0



https://www.bilibili.com/video/BV1Kx411B7V7?p=2

## Portal for ArcGIS

https://desktop.arcgis.com/zh-cn/arcmap/10.3/get-started/license-manager-guide/portal-for-arcgis.htm



## ArcGIS API for JavaScript: Getting Started with Web Development

https://www.youtube.com/watch?v=zQTkkFUhzLI



## arcgis api（三）arcgis api for js 4.x 加载高德地图、谷歌地图、天地图

https://blog.csdn.net/zhengjie0722/article/details/81780860?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-8.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-8.control

## 学习基础视频

https://www.bilibili.com/video/BV11D4y197wM/?spm_id_from=333.788.videocard.5

https://www.bilibili.com/video/BV1qK411N7sr?from=search&seid=17644116689783027491



## 使用操作

### 创建不同类型地图（超图）

![](D:\workspaces\git\github\KnowledgePoints\notes\acgis-webjs\images\acg-05.png)



## arcgis本地配置

修改init.js文件，在init.js里搜索[HOSTNAME_AND_PATH_TO_JSAPI] , 替换为127.0.0.1:8080/arcgis_js_api/library/4.18/ （以我本机上面的JS API放置的路径为例）效果如下：

![](D:\workspaces\git\github\KnowledgePoints\notes\acgis-webjs\images\acg-07-本地部署.png)

参考：https://www.sohu.com/a/213002551_740819

https://www.cnblogs.com/jerry-liuyu/p/12716405.html

demo

```

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="initial-scale=1, maximum-scale=1,user-scalable=no" />
    <title>Test Map</title>
    <link rel="stylesheet" href="http://localhost:8080/arcgis_js_api/library/4.18/dijit/themes/claro/claro.css" />
    <link rel="stylesheet" href="http://localhost:8080/arcgis_js_api/library/4.18/esri/themes/light/main.css" />
    <style>
        html,
        body,
        #viewDiv {
            margin: 0;
            padding: 0;
            width: 100%;
            height: 100%;
        }
    </style>
    <script src="http://localhost:8080/arcgis_js_api/library/4.18/dojo/dojo.js"></script>
    <script>
        var myMap, view;
        require([
            "esri/Basemap",
            "esri/layers/TileLayer",
            "esri/Map",
            "esri/views/MapView"
        ], function (Basemap, TileLayer, Map, MapView){
            // --------------------------------------------------------------------
            // If you do not have public internet access then use the Basemap class
            // and point this URL to your own locally accessible cached service.
            //
            // Otherwise you can just use one of the named hosted ArcGIS services.
            // https://services.arcgisonline.com/ArcGIS/rest/services/World_Street_Map/MapServer
            // --------------------------------------------------------------------
            var layer = new TileLayer({
                url:"https://services.arcgisonline.com/arcgis/rest/services/World_Physical_Map/MapServer"
               // url: "https://js.arcgis.com/arcgis/rest/services/Folder/Custom_Base_Map/MapServer"
            });
            var customBasemap = new Basemap({
                baseLayers: [layer],
                title: "Custom Basemap",
                id: "myBasemap"
            });
            myMap = new Map({
                basemap: customBasemap
            });
            view = new MapView({
                center: [-111.87, 40.57], // long, lat
                container: "viewDiv",
                map: myMap,
                zoom: 6
            });
        });
    </script>
</head>
<body class="claro">
<div id="viewDiv"></div>
</body>
</html>
```



