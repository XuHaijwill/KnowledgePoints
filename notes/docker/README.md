<<<<<<< HEAD
# Docker使用指南

## vmware使用

**VM14.0安装**

官方下载地址：

https://download3.vmware.com/software/wkst/file/VMware-workstation-full-14.0.0-6661328.exe

```
2018年3月4日新增密钥：

AU5WA-0EF9M-0811P-REP5X-ZFK9A

ZV382-6VX96-H81LP-1ZZG9-PVKF4

GU34A-41G4Q-H88CY-3WPNT-XUK8F

FF590-2DX83-M81LZ-XDM7E-MKUT4

CG54H-D8D0H-H8DHY-C6X7X-N2KG6

ZC3WK-AFXEK-488JP-A7MQX-XL8YF

AC5XK-0ZD4H-088HP-9NQZV-ZG2R4

```



```
yum -y install net-tools
```











=======
# 工作记录-离线创建docker私有仓库

在联网环境下建立docker私有仓库很简单，在另一篇文章中已经介绍。但实际情况是服务器无法连接外网，且要在其上建立私有仓库，供内网其他服务器获取镜像。

和联网环境相比，有以下变化：
\1. docker安装：通过二进制文件安装
\2. docker镜像获取：通过文件系统导入镜像

首先，docker的安装，在[这里](https://docs.docker.com/installation/binaries/)参考二进制文件安装方式。按照介绍操作即可，注意要保证各种依赖的完整性，比如git的安装。

其次，docker镜像的导入。

1. 可联网的虚拟机中先获取registry镜像，然后导出tar格式的镜像文件。

```
docker save registry > /tmp/registry.tar1
```

1. 在目标虚拟机中导入tar格式的镜像文件。

```
docker load < registry.tar1
```

# Docker的离线安装，Docker仓库镜像安装与配置

https://blog.csdn.net/u013302586/article/details/100526898



# 持续集成-Jenkins离线安装与配置

https://galaxyyao.github.io/2019/08/14/%E6%8C%81%E7%BB%AD%E9%9B%86%E6%88%90-Jenkins%E7%A6%BB%E7%BA%BF%E5%AE%89%E8%A3%85%E4%B8%8E%E9%85%8D%E7%BD%AE/

# 基于Docker+Jenkins+Git 实现企业持续集成持续部署（CI/CD）

https://www.bilibili.com/video/BV1z7411u7gg?from=search&seid=16239893339196111060

# 2020版 Git+Jenkins+Harbor+Docker实现CICD

https://www.bilibili.com/video/BV1Vv411C7gn/?spm_id_from=333.788.recommend_more_video.-1



![](imgs\imgs.png)
>>>>>>> 448d44d69602c4395e2c30cf04e197780b2fdcb2
