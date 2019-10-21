/*
 Navicat Premium Data Transfer

 Source Server         : wax
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : blogsystem

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 21/10/2019 17:23:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_code` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `del_tag` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('0130ea06-be03-478c-97d6-49235c2a58aa', 'xinwang100', '1111', '王鑫109', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('08e9b2ec-3fba-49ad-a17e-c91381c6c30d', 'xinwang33', '123456', '王鑫', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('1', 'wax', '123456', '王鑫', 'admin', '0', NULL);
INSERT INTO `user` VALUES ('10', 'wax', '123456', '王鑫', 'admin', '0', NULL);
INSERT INTO `user` VALUES ('11', 'wax', '123456', '王鑫', 'admin', '0', NULL);
INSERT INTO `user` VALUES ('12', 'wax', '123456', '王鑫', 'admin', '0', NULL);
INSERT INTO `user` VALUES ('12660a09-8f88-45c4-bb9d-be0415e9de47', 'user100', '1111', '用户100', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('13', 'wax', '123456', '王鑫', 'admin', '1', NULL);
INSERT INTO `user` VALUES ('14', 'wax', '123456', '王鑫', 'admin', '1', NULL);
INSERT INTO `user` VALUES ('15', 'wax', '123456', '王鑫', 'admin', '1', NULL);
INSERT INTO `user` VALUES ('16', 'wax1', '123456', '王鑫1', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('1790ac61-c618-4357-82b5-d6273eb68143', 'user102', '1111', '用户102', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('1b4e712c-709e-4684-bcbf-1f22f8444cb1', 'user106', '1111', '用户106', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('2', 'wax', '123456', '王鑫', 'admin', '0', NULL);
INSERT INTO `user` VALUES ('21157384-f282-4afe-bbd5-d7bca794bc91', 'xinwang', '1111', '王鑫', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('2390ea66-e9c9-47f5-a068-636f0b1d48af', 'user22', '1111', '用户22', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('2869f62f-44ea-4a5a-bfcb-49cfb5a37e6c', 'user109', '1111', '用户109', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('3', 'wax', '123456', '王鑫', 'admin', '0', NULL);
INSERT INTO `user` VALUES ('30897eb8-1299-4bad-a4f3-b2d904ed60b7', 'user33', '1111', '用户33', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('34828856-686c-4ba2-8fe1-086288b10b69', NULL, NULL, NULL, 'user1', '1', NULL);
INSERT INTO `user` VALUES ('35a0737b-31c2-479e-b794-51dfdf976920', 'user107', '1111', '用户107', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('363cf0cc-5798-449a-a4cb-d4c804cff803', 'user103', '1111', '用户103', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('39fe97d5-a116-42b4-8de7-420816eca44f', 'user77', '1111', '用户77', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('4', 'wax', '123456', '王鑫', 'admin', '0', NULL);
INSERT INTO `user` VALUES ('4044fab8-1fdd-43c4-bc82-f9cbb1714187', 'wangxin1', '1111', '王鑫1', 'user2', '1', '2019-10-18 15:23:40');
INSERT INTO `user` VALUES ('4457bd64-930e-4a23-826e-603165514d14', 'user10', '1111', '用户10', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('45b5d537-000c-4dd3-813b-d45ace19f37a', 'testLogin', 'testLogin', '王鑫', 'user1', '1', '2019-10-21 14:00:39');
INSERT INTO `user` VALUES ('4b1db60c-7653-4c9e-b58e-d81f7ff85e82', 'user1', '12333', '用户1', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('4df9708b-63d4-4bfe-8682-622d1748d077', 'user77', '1111', '用户77', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('4fa8ddd8-48bd-4035-b41d-a5156b36a233', 'user66', '1111', '用户66', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('5', 'wax', '123456', '王鑫', 'admin', '0', NULL);
INSERT INTO `user` VALUES ('5875cda5-9154-4975-9f6d-7bdb9d401d8a', 'user66', '1111', '用户66', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('5daef4b6-b39e-4735-a770-148833e10f30', 'user1', '12333', '用户1', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('5e54ec08-3bf2-4849-b819-3562cd353bec', 'user44', '1111', '用户44', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('6', 'wax', '123456', '王鑫', 'admin', '0', NULL);
INSERT INTO `user` VALUES ('62019693-b487-44ca-b500-48a39d790e4b', 'waxpt', '12345', '王鑫pt', 'user1', '1', NULL);
INSERT INTO `user` VALUES ('635e5d97-20b2-479d-a0a6-020a4bf03049', 'user55', '1111', '用户55', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('6571f83e-3c46-471a-b9eb-4f16cb2495ab', 'wangxin', '123456', '王鑫', 'user2', '1', '2019-10-18 15:20:16');
INSERT INTO `user` VALUES ('66916f81-2ce8-44b9-a60e-506141da8a1a', 'user105', '1111', '用户105', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('68b37ed1-e7f4-43b1-b81f-8eda546efa7b', 'user10', '1111', '用户10', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('6ba264b9-6026-4e37-8e5a-b76288553449', 'test99', '1111', '用户99', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('7', 'wax', '123456', '王鑫', 'admin', '0', NULL);
INSERT INTO `user` VALUES ('75e1c04b-5cde-431d-9378-79784657f2dd', 'test99', '1111', '用户99', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('7b2f463a-bd46-4be8-9cf8-ac730bac334e', 'user10', '1111', '用户10', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('7c77b1f4-a202-40a7-abae-ccc312afb69b', 'user22', '1111', '用户22', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('8', 'wax', '123456', NULL, 'admin', '0', NULL);
INSERT INTO `user` VALUES ('9', 'wax', '123456', '王鑫', 'admin', '0', NULL);
INSERT INTO `user` VALUES ('90450c11-65e1-4eea-aa58-40294279b3a4', 'user66', '1111', '用户66', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('932eacf8-bfd4-42ae-b3b8-46f7a3fb1d32', 'user88', '1111', '用户88', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('97dd5c43-d273-4167-8f0b-51ebbf6d4d82', 'user55', '1111', '用户55', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('9c4b7cff-b8d5-41f2-b731-7af2cef2e7dc', 'user104', '1111', '用户104', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('a0584be0-649f-475f-83f0-905ade906ade', 'test99', '1111', '用户99', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('a30851da-a722-4d3e-8167-aa96f5a6748b', 'xinwang33', '123456', '王鑫', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('a4662cc0-4320-49aa-b33b-568b935d3754', 'user22', '1111', '用户22', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('a4d0cbd8-03bb-44be-85dc-c0e29a3a49f2', 'user33', '1111', '用户33', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('b1eee3cf-84a5-40af-936d-c16387dbd46e', 'user1', '12333', '用户1', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('b41697f8-9943-4343-b27e-b6f7accedca2', 'user22', '1111', '用户22', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('bb92ecb1-5133-4681-b3aa-b0d57c302702', 'user111', '1111', '用户111', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('bbeb8852-9616-489b-b23c-af617eeb4690', 'user44', '1111', '用户44', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('ca261ae5-eb0f-4dc0-8f36-bb7785523244', 'user88', '1111', '用户88', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('d6468e0d-e67d-4457-8ea2-b73fe15a12d2', 'user101', '1111', '用户101', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('de73ab0d-8af7-4985-bf27-683f6d438cc5', 'user77', '1111', '用户77', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('ea8b89e0-57f7-44bb-92a8-088fe267b1f0', 'user44', '1111', '用户44', 'user2', '1', NULL);
INSERT INTO `user` VALUES ('feed335e-e9ff-4569-83fd-630dd3f55e7a', 'user88', '1111', '用户88', 'user2', '1', NULL);

SET FOREIGN_KEY_CHECKS = 1;
