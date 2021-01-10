# Appach FTP使用指南

## 部署指南参考

文档：springboot整合apache ftpserver详细教...
链接：http://note.youdao.com/noteshare?id=df7f1056f55b2c25a974214ce4ea485b&sub=7954490E67614678B5ECC9946FCED9E8

## 建表语句（重要）

```
DROP TABLE IF EXISTS `ftp_user`;
CREATE TABLE `ftp_user`  (
  `userid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userpassword` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `homedirectory` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `enableflag` tinyint(1) NULL DEFAULT 1,
  `writepermission` tinyint(1) NULL DEFAULT 0,
  `idletime` int(11) NULL DEFAULT 0,
  `uploadrate` int(11) NULL DEFAULT 0,
  `downloadrate` int(11) NULL DEFAULT 0,
  `maxloginnumber` int(11) NULL DEFAULT 0,
  `maxloginperip` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ftp_user
-- ----------------------------
INSERT INTO `ftp_user` VALUES ('szch3bid', 'szch3bid@202101', '/Home', 1, 1, 300, 48000000, 48000000, 3, 3);
INSERT INTO `ftp_user` VALUES ('szch5bid', 'szch5bid@202102', '/Home', 1, 1, 300, 48000000, 48000000, 3, 3);

SET FOREIGN_KEY_CHECKS = 1;
```

