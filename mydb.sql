/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : mydb

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 09/10/2023 02:52:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_table
-- ----------------------------
DROP TABLE IF EXISTS `admin_table`;
CREATE TABLE `admin_table`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '管理员用户名',
  `password` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '管理员帐号密码(加密后)',
  `createTime` bigint NULL DEFAULT NULL COMMENT '管理员帐号创建时间',
  `lastAccessTime` bigint NULL DEFAULT NULL COMMENT '管理员帐号最后一次访问时间',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '管理员帐号状态(正常,冻结)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '管理员用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_table
-- ----------------------------
INSERT INTO `admin_table` VALUES (18, 'aa', '202cb962ac59075b964b07152d234b70', 1679289101464, 1681365213842, '正常');
INSERT INTO `admin_table` VALUES (21, 'bb', '202cb962ac59075b964b07152d234b70', 1679541667828, 1680501982391, '冻结');
INSERT INTO `admin_table` VALUES (22, 'cc', 'be8fe4c12c4e43217c06098a2595a950', 1679541673496, 0, '冻结');
INSERT INTO `admin_table` VALUES (25, 'admin', '202cb962ac59075b964b07152d234b70', 1681358515509, 1696527248458, '正常');

-- ----------------------------
-- Table structure for book_table
-- ----------------------------
DROP TABLE IF EXISTS `book_table`;
CREATE TABLE `book_table`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '书名',
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '作者',
  `press` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '出版社',
  `price` decimal(10, 0) NULL DEFAULT 0 COMMENT '价格',
  `sale` int NULL DEFAULT 100 COMMENT '折扣(0-100)',
  `stock` int NULL DEFAULT 0 COMMENT '库存',
  `coverUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '封面图片路径',
  `info` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '图书详情',
  `publishDate` date NULL DEFAULT NULL COMMENT '出版时间',
  `marketDate` date NULL DEFAULT NULL COMMENT '上架时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '图书表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_table
-- ----------------------------
INSERT INTO `book_table` VALUES (4, '重构：改善既有代码的设计(第2版)', '(美)Martin Fowler', '人民邮电出版社', 88, 90, 9, '/bookApp1/cover/1696788123158.png', '                                                                本书清晰揭示了重构的过程，解释了重构的原理和最佳实践方式，并给出了何时以及何地应该开始挖掘代码以求改善。书中给出了70 多个可行的重构，每个重构都介绍了一种经过验证的代码变换手法的动机和技术。本书提出的重构准则将帮助你一次一小步地修改你的代码，从而减少了开发过程中的风险。\\r\\n\\r\\n本书适合软件开发人员、项目管理人员等阅读，也可作为高等院校计算机及相关专业师生的参考读物。\"\r\n       \r\n       \r\n       \r\n       \r\n       \r\n       \r\n       \r\n       ', '2015-08-01', '2023-02-01');
INSERT INTO `book_table` VALUES (5, '大话设计模式', '程杰', '清华大学出版社', 94, 100, 9, '/bookApp1/cover/1696788131118.png', '                                                感受设计演变过程中所蕴含的大智慧，体会乐与怒的程序人生中值得回味的一幕幕。\\r\\n\\r\\n设计模式的趣味解读，面向对象的深入剖析。在诙谐与温馨中做一次面向对象编程思维的体操。\\r\\n\\r\\n本书是准备攀登面向对象编程高峰朋友们的引路人和提携者；本书是学习、体会和领悟了众多大师智慧结晶后的图书作品；本书是你深入理解和感受GoF的《设计模式》及其它大师作品的书籍；本书授之以“鱼”，更授之以“渔”。\"\r\n       \r\n       \r\n       \r\n       \r\n       \r\n       ', '2007-12-01', '2023-01-01');
INSERT INTO `book_table` VALUES (6, '一本小小的蓝色逻辑书', '(加) 布兰登·罗伊尔', '九州出版社', 85, 95, 17, '/bookApp1/cover/1696788102206.png', '                        大约两千五百年前，苏格拉底创建了一套方法，通过一系列探问，也就是后世所说的“苏格拉底方法”，来获取问题的答案，并对这些答案进行推断。通过这种方式，他就可以找出各种复杂现象背后的关键问题，揭开很多貌似有理实则荒谬的说法背后的真相。这套方法就是后来我们所说的“逻辑推理”\\\\r\\\\n\\\\r\\\\n本质上来说，逻辑推理是一个“收集信息、评估信息的过程”。要想做出正确的决定，我们首先要占有充分的信息，而要想占有充分的信息，首先需要提出正确的问题。所以那些擅长逻辑推理的人，往往也比较善于提问，搜集相关信息，并用“正确的”方式 对这些信息进行评估。*重要的是，他们可以在不受他人干扰的情况下独立完成这一过程。\\\\r\\\\n\\\\r\\\\n本书的目的，就是为读者提炼出一套*有用、*完整的逻辑推理概念，帮助读者在学习和现实生活中更好地理清思路，解决问题。\r\n       \r\n       \r\n       \r\n       ', '2017-07-02', '2023-03-02');
INSERT INTO `book_table` VALUES (7, 'Head First 设计模式（中文版）', 'Eric Freeman &Elisabeth Freeman with Kathy Sierra & Bert Bates', '中国电力出版社', 61, 100, 0, '/bookApp1/cover/1696788114052.png', '                                                                               本书趋近完美，因为它在提供专业知识的同时，仍然具有相当高的可读性。\\r\\n       本书共有14章，每章都介绍了几个设计模式，完整地涵盖了四人组版本全部23个设计模式。\\r\\n\\r\\n       ★前言介绍了这本书的用法\\r\\n       ★第1章至第11章陆续介绍了设计模式：Strategy、Observer、Decorator、Abstract　Factory、Factory　Method、Singleton、Command、Adapter、Facade、TemplatMethod、Iterator、Composite、State、Proxy。\\r\\n       ★第12章介绍了如何将两个以上的设计模式结合起来成为新的设计模式（例如，著名的MVC模式），作者称其为复合设计模式。\\r\\n       ★第13章介绍了如何进一步学习设计模式，如何发掘新的设计模式等主题。\\r\\n       ★附录A介绍了剩下的设计模式：Bridge（桥接）、Builder（生成器）、Chainof Responsibility（责任链）、Flyweight（蝇量）、Interpreter（解释器）、Mediator（中介者）、Memento（备忘录）、Prototype（原型）、Visitor（访问者）。\\r\\n        另外，第1章至第9章还穿插介绍了四个OO基本概念（抽象、封装、继承、多态），以及九个OO原则（Principle）。千万不要轻视这些OO原则，因为每个设计模式背后都包含了几个OO原则的概念。很多时候，在设计出现两难情况时我们必须回归到OO原则，以方便判断取舍。\\r\\n\\r\\n        可以这么说：OO原则是我们的目标，而设计模式是我们的做法！\"\r\n       \"\r\n       \r\n       \r\n       \r\n       \r\n       \r\n       \r\n       \r\n       ', '2007-09-01', '2023-03-01');
INSERT INTO `book_table` VALUES (8, '阿里巴巴Java开发手册', '杨冠宝', '电子工业出版社', 75, 100, 9, '/bookApp1/cover/1696788139527.png', '                                        《*Java开发手册》的愿景是码出高效，码出质量。它结合作者的开发经验和架构历程，提炼*集团技术团队的集体编程经验和软件设计智慧，浓缩成为立体的编程规范和*实践。众所周知，现代软件行业的高速发展对开发者的综合素质要求越来越高，因为不仅是编程相关的知识点，其他维度的知识点也会影响软件的*终交付质量，比如，数据库的表结构和索引设计缺陷可能带来软件的架构缺陷或性能风险；单元测试的失位导致集成测试困难；没有鉴权的漏洞代码易被黑客攻击等。所以，本手册以开发者为中心视角，划分为编程规约、异常日志、单元测试、安全规约、MySQL数据库、工程结构、设计规约七个维度，每个条目下有相应的扩展解释和说明，正例和反例，全面、立体、形象地帮助到开发者的成长和团队代码规约文化的形成。从严格意义上讲，本手册跨越了Java语言本身，明确作为一名合格开发者应该具备的基本素质，因此本手册适合计算机相关行业的管理者和研发人员、高等院校的计算机专业师生、求职者等阅读，希望成为大家如良师益友般的工作手册、工具字典和床头书。\r\n       \r\n       \r\n       \r\n       \r\n       ', '2023-03-01', '2022-12-01');

-- ----------------------------
-- Table structure for cart_table
-- ----------------------------
DROP TABLE IF EXISTS `cart_table`;
CREATE TABLE `cart_table`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `customerId` int NOT NULL COMMENT '顾客id',
  `bookId` int NOT NULL COMMENT '图书id',
  `num` int NULL DEFAULT 1 COMMENT '购买数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 182 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '购物车表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cart_table
-- ----------------------------

-- ----------------------------
-- Table structure for customer_table
-- ----------------------------
DROP TABLE IF EXISTS `customer_table`;
CREATE TABLE `customer_table`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '顾客用户名',
  `password` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '顾客密码(加密后)',
  `createTime` bigint NULL DEFAULT NULL COMMENT '顾客帐号创建时间',
  `lastAccessTime` bigint NULL DEFAULT NULL COMMENT '顾客最后一次访问时间',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '顾客账号状态(正常,冻结)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '顾客用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_table
-- ----------------------------
INSERT INTO `customer_table` VALUES (14, 'tom', '202cb962ac59075b964b07152d234b70', 1679104885328, 1696528171710, '正常');
INSERT INTO `customer_table` VALUES (15, 'jerry', '202cb962ac59075b964b07152d234b70', 1679104889340, NULL, '正常');
INSERT INTO `customer_table` VALUES (16, 'bill', '202cb962ac59075b964b07152d234b70', 1679276052099, 1679276061195, '冻结');
INSERT INTO `customer_table` VALUES (17, 'mike', '202cb962ac59075b964b07152d234b70', 1679283023334, NULL, '冻结');
INSERT INTO `customer_table` VALUES (18, 'abc', '202cb962ac59075b964b07152d234b70', 1679289282627, 1679289291658, '冻结');

-- ----------------------------
-- Table structure for order_table
-- ----------------------------
DROP TABLE IF EXISTS `order_table`;
CREATE TABLE `order_table`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `orderId` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '订单编号',
  `customerId` int NULL DEFAULT NULL COMMENT '顾客id',
  `books` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '购买图书及数量(图书1id:图书1数量;图书2id:图书2数量;...)',
  `money` decimal(10, 0) NULL DEFAULT NULL COMMENT '订单金额',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '订单状态(未付款,已付款,已发货,已完成,已取消)',
  `createTime` bigint NULL DEFAULT NULL COMMENT '订单创建时间',
  `updateTime` bigint NULL DEFAULT NULL COMMENT '订单状态更新时间',
  `receiverName` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '收货人姓名',
  `receiverTel` varchar(18) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '收货人电话',
  `receiverAddress` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '收货人地址',
  `expressNumber` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '快递单号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_table
-- ----------------------------
INSERT INTO `order_table` VALUES (9, '168110672116017', 17, '5:1,4:1,', 173, '已取消', 1681106721160, 1681369700292, 'a', 'b', 'c', NULL);
INSERT INTO `order_table` VALUES (10, '168110683217717', 17, '5:1,4:1,', 173, '已发货', 1681106832177, 1681368547680, 'aaaaa', 'bbbbb', 'bcccc', '13131313131');
INSERT INTO `order_table` VALUES (11, '168110805618617', 17, '6:2,7:1,', 221, '已付款', 1681108056186, 1681108127925, 'aa', 'bb', 'cc', NULL);
INSERT INTO `order_table` VALUES (13, '168135612539817', 17, '6:2,7:1,', 221, '已完成', 1681356125398, 1681368622170, 'a', 'b', 'c', NULL);
INSERT INTO `order_table` VALUES (15, '168137469853017', 17, '6:1,7:1,', 141, '已完成', 1681374698530, 1681374914058, '张三', '12121', 'xxxx', NULL);
INSERT INTO `order_table` VALUES (16, '168143971542617', 17, '7:1,', 61, '已完成', 1681439715426, 1681439934938, 'a', 'a', 'a', NULL);
INSERT INTO `order_table` VALUES (17, '168144136761017', 17, '6:3,7:1,', 301, '未付款', 1681441367610, NULL, 'a', 'b', 'c', NULL);
INSERT INTO `order_table` VALUES (18, '168145289915617', 17, '8:1,6:2,', 235, '未付款', 1681452899156, NULL, '李四', '121212', 'sdff', NULL);
INSERT INTO `order_table` VALUES (19, '168145303082617', 17, '7:3,', 183, '已完成', 1681453030826, 1681453119082, '王五', '1212313', '花木成畦手自栽sadf', NULL);
INSERT INTO `order_table` VALUES (22, '168154398487218', 18, '8:2,', 150, '已取消', 1681543984872, 1681544013604, 'jerry', '2435', 'asdf', NULL);
INSERT INTO `order_table` VALUES (23, '168154513734618', 18, '7:1,4:2,', 219, '已付款', 1681545137347, 1681545139991, 'jerry', '121212', '131313', NULL);
INSERT INTO `order_table` VALUES (24, '168164601254818', 18, '4:1,', 79, '已完成', 1681646012548, 1681712162759, 'jerry2', '2222', 'adfadsf', NULL);
INSERT INTO `order_table` VALUES (26, '168169911410818', 18, '4:3,7:1,', 299, '已完成', 1681699114109, 1681699644324, '成大', '3452345', '基材dsa', NULL);
INSERT INTO `order_table` VALUES (27, '168171192087318', 18, '4:3,8:1,', 313, '未付款', 1681711920873, NULL, '成子', '121212', '鞋材d', NULL);
INSERT INTO `order_table` VALUES (29, '169578394126217', 17, '6:2,7:1,', 223, '已发货', 1695783941263, 1695784030560, 'asdf', '32q4532', 'adsfsa', '3413414321');
INSERT INTO `order_table` VALUES (30, '169579768578117', 17, '6:2,4:1,5:1,', 335, '已完成', 1695797685783, 1695797906039, '11111', '212121', '121212', NULL);
INSERT INTO `order_table` VALUES (37, '169648698444714', 14, '6:2,5:1,', 256, '已发货', 1696486984447, 1696487047910, 'tom', '123456', 'adsfsa', '12121212121212');
INSERT INTO `order_table` VALUES (39, '169651506605414', 14, '6:1,', 81, '已取消', 1696515066054, 1696515138901, '1212', '12', '12', NULL);
INSERT INTO `order_table` VALUES (41, '169651647085814', 14, '8:1,', 75, '已完成', 1696516470858, 1696516580600, 'afds', 'asdf', 'asdf', NULL);
INSERT INTO `order_table` VALUES (54, '169652697235414', 14, '6:1,', 81, '已发货', 1696526972354, 1696527080078, '66', '6', '6', 'adfafdsa');
INSERT INTO `order_table` VALUES (55, '169652723099614', 14, '4:1,', 79, '已发货', 1696527230996, 1696527287419, 'a', 'a', 'a', 'asdfadfafdasdf');

SET FOREIGN_KEY_CHECKS = 1;
