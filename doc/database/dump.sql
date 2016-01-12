/*
 Navicat Premium Data Transfer

 Source Server Type    : MySQL
 Source Server Version : 50616
 Source Database       : qihua

 Target Server Type    : MySQL
 Target Server Version : 50616
 File Encoding         : utf-8

*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_article`
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `article_id` int(10) NOT NULL,
  `article_type` int(1) DEFAULT NULL,
  `content` longtext,
  `status` int(1) DEFAULT NULL,
  `insert_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_article`
-- ----------------------------
BEGIN;
INSERT INTO `t_article` VALUES ('1', '1', '<p><img alt=\"\" src=\"/Qihua/resources/images/upload/ckfinder/images/partner.png\" style=\"height:192px; width:1002px\" /></p>\r\n', '1', '2015-11-30 20:38:42', '2015-11-30 20:38:42'), ('2', '2', '<p style=\"text-align: center;\"><img alt=\"\" src=\"/Qihua/resources/images/upload/ckfinder/images/partner.png\" style=\"height:192px; width:1002px\" /></p>\r\n', '1', '2015-02-06 19:35:48', '2015-02-06 19:35:48'), ('3', '3', '<p><img alt=\"\" src=\"/Qihua/resources/images/upload/ckfinder/images/partner.png\" style=\"height:192px; width:1002px\" /></p>\r\n', '1', '2015-11-30 20:38:57', '2015-11-30 20:38:57');
COMMIT;

-- ----------------------------
--  Table structure for `t_card`
-- ----------------------------
DROP TABLE IF EXISTS `t_card`;
CREATE TABLE `t_card` (
  `card_id` varchar(32) NOT NULL,
  `upload_id` int(10) DEFAULT NULL,
  `card_no` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `score` int(10) DEFAULT NULL,
  `recharge_time` timestamp NULL DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `insert_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`card_id`),
  KEY `idx_card_no` (`card_no`),
  KEY `idx_card_no_password` (`card_no`,`password`),
  KEY `idx_card_insert_time` (`insert_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_card`
-- ----------------------------
BEGIN;
INSERT INTO `t_card` VALUES ('000437712e3b49b3b3ac8bc458aa9996', '1', '7626e38d9e9d4202abf9280de6f13dad', 'bc0e616b-a', '3673', '2015-01-29 23:09:43', '2', '2015-01-25 21:06:12', '2015-01-29 23:09:44'), ('00044b84859c42cfaed0433fadc99b2a', '1', 'c454aa60ce0e47f4936245f54ffbab60', '3d2f15fb-e', '1864', '2015-02-02 19:07:37', '2', '2015-01-25 21:06:12', '2015-02-02 19:07:38'), ('000a4a46c56e42728650a68cc59907f9', '1', '22771cfde443437d8911e8551194a8df', '6290f102-0', '4928', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('001361f50e3e48bfb7e210f01c3fa0fd', '1', 'e27d88733dc7407abd28d42415f347e8', '9a764409-1', '2600', '2015-01-31 22:43:29', '2', '2015-01-25 21:06:12', '2015-01-31 22:43:29'), ('00144c130ad7497197f2da2034d78f3e', '2', '29da3c90604b4d11aec87e9e600e4b65', '83cfc262-6', '1499', '2015-11-15 13:27:18', '2', '2015-01-31 14:25:02', '2015-11-15 13:27:18'), ('00190b59cf444abc8ea9eea00ec8d9e6', '1', '3332d7970371476d8d65cbfcd65577a0', '3d0322a2-4', '1917', '2015-01-31 23:44:49', '2', '2015-01-25 21:06:12', '2015-01-31 23:44:49'), ('001917fb29d64013a26ec6f72b33488c', '1', '8db635fd200c4e59a2f1c81c12156406', '2b6616b2-1', '4043', '2015-01-31 23:34:42', '2', '2015-01-25 21:06:12', '2015-01-31 23:34:42'), ('001af8027f354566b37adb62a5af8ade', '1', 'bc62d5fdd8a64f0ebc7e1e9525762222', 'e4030970-a', '3231', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('001c2240174541ec8ae8d11896196034', '1', 'b5eac7854a664a8bb79bed50f6ee6c8b', '604f7211-d', '1724', '2015-02-01 07:53:25', '2', '2015-01-25 21:06:12', '2015-02-01 07:53:25'), ('001f1e771d124a1d9ffbea458f3f6cf1', '1', '15776d7389ec48e3b9d34564d3503e19', 'e9dea5a3-c', '2070', '2015-02-08 20:58:35', '2', '2015-01-25 21:06:12', '2015-02-08 20:58:35'), ('002140e36cd442d68b422cbd93ab58d2', '1', '67f109efea114820a9ccb0de8739492d', '36840b86-e', '1000', '2015-02-02 21:24:38', '2', '2015-01-25 21:06:12', '2015-02-02 21:24:38'), ('002570b9c37e4ba48b20d69222e16f41', '1', 'c44c9dea9f30485493b0813fc0044f33', 'f1d86a33-c', '2771', '2015-11-15 13:41:10', '2', '2015-01-25 21:06:12', '2015-11-15 13:41:10'), ('0026513bb5224595b3e3210c90aa6125', '2', '7c83db1ed426403a821a0955a1d2533d', '4dc5f78f-4', '3546', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('002bfe9b36db492ab5db683647f3debf', '1', 'bc4de9fc51ba4203bbe34939191e4086', '1a4aaad9-8', '1027', '2015-02-08 20:44:54', '2', '2015-01-25 21:06:12', '2015-02-08 20:44:55'), ('002e9bf522ab46128a84d27a6ea97548', '1', 'd545bea2d65a4345bffd04f6a096c89a', 'aa39a773-b', '4176', '2015-02-03 20:37:29', '2', '2015-01-25 21:06:12', '2015-02-03 20:37:29'), ('00330c91e0ac498c885ad6d16131440b', '1', 'a7ea4e67c92841e0bb45da17b4a6683a', 'dafb269c-6', '4252', '2015-02-03 22:07:10', '2', '2015-01-25 21:06:12', '2015-02-03 22:07:10'), ('003313aceefb45e09a5145c33901edf1', '2', 'd218d37797b043c2a4db588991d205e2', '78594549-7', '1316', '2015-11-15 15:11:43', '2', '2015-01-31 14:25:02', '2015-11-15 15:11:43'), ('0035df17cde14c73a0fbb58e8d1f8d26', '2', '569e4316c53d4791b0d5fab11a2e3fc4', '4455d4af-5', '4021', '2015-02-03 21:59:16', '2', '2015-01-31 14:25:02', '2015-02-03 21:59:16'), ('0035f14565f4438c9eb4d19688ce84b4', '2', 'f60ad065d78b49b983c1d71810c107ec', 'bb50764c-5', '406', '2015-02-08 20:47:42', '2', '2015-01-31 14:25:02', '2015-02-08 20:47:42'), ('003c494905ae43abafce616a57e515f9', '2', '1b6d21a872b04e2b833cc8fac853a261', '38ac3f8b-b', '2444', '2015-02-08 20:49:25', '2', '2015-01-31 14:25:02', '2015-02-08 20:49:25'), ('003da80fea5a4c1b81fcffae3a2e9572', '1', '0ac37a1cd390439f8b362523694fcbed', '0782a416-a', '4711', '2015-02-08 20:51:41', '2', '2015-01-25 21:06:12', '2015-02-08 20:51:41'), ('00442a647d0d4493adf99f9779f4d803', '1', '95af94d513fd4c28b577fb5705e9ee36', 'd50f7692-d', '1154', '2015-02-08 20:52:36', '2', '2015-01-25 21:06:12', '2015-02-08 20:52:36'), ('00442ce919f1418fa3079f76175e11d5', '2', '7f83f6eceb474dfd883589541f51ed23', '0e55ee47-8', '1886', null, '1', '2015-01-31 14:24:59', '2015-01-31 14:24:59'), ('004b4ae033844532a042ab6602f30bf7', '1', '86f7b8e78bf241058063b38e729f5631', 'f25458d1-f', '3666', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('004d63e2e43b445f914c718f858c1bb7', '1', '5607ffd27f0146d6b4cf4440f6362040', '2fcf88f7-9', '3495', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('0053669e46414ab293a29950215277b2', '2', 'fe5cd72cff624a95b6a5f483900e4469', 'e6d98ed8-1', '4388', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('00604966d5a642ee88507596efd23fe7', '1', '02d5eaea271d49eead0b1c7a0ac41b9b', 'e0bcd4d0-a', '2309', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('006f1e24dd1f4bf1a45bb6b99d7b20d8', '1', '0c7f398dc01c4e4fabe4f8e37752cd54', 'd82ca92f-1', '4081', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('006fecc9b13845148b67b2b76bbce680', '2', 'aa5b99a6eea2442282ba17aa454bf9b6', '8f4df152-c', '3853', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('00842aa726404539a0443bf2ef165535', '1', '653cde33b25646b689685ac49e4ecb4c', '59b296db-8', '715', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('0086e1cdd8954b7193993f8c82788af0', '2', '307a6bdb0cea4b499dafedd5842fcc1f', '19bdbb68-1', '1437', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('00871c3cdf66482a9670c410a3af7658', '2', '28c8246bc1754360bcebc184941fff90', 'fac78162-1', '3369', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('0087ad88151e4bffaba37a6a7a00decc', '2', '5bce7b61b7ff4b1c9659875e6e999402', '09408081-6', '1932', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('008d5de076be4bdd870ea76016435a81', '1', '68e32475f81748bc9c7798b9fe1e0a57', '1b173004-4', '3962', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('0091320e892547728196d6f272f1b30d', '1', 'da68751e87334b5eb8af85f9e2fa9ee6', '227369fe-6', '1557', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('0095b58cfed74aa7a047e265ab806921', '1', '9c25b3f10cc042ffaaf841190c49308e', '40b597a2-5', '3485', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('00a5e2b92faf4ac09319b38a3d16df26', '1', 'b608d4e3c75c4f35936703c1aea5b7d4', 'b6232287-2', '4505', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('00a5f89b2d74499aa4e6e6d4ff4b7d03', '1', 'fde235e04b5d4885b9223f091dd8bc90', 'b4bac19c-e', '2887', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('00a704be4b244f45af6d687936b7cb89', '1', 'f12e8f9e4cdb4351990688f2486b6918', 'd6439775-6', '4356', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('00a9632787244c09a2a5b555f07b5453', '1', '5dfd9ab953724e088a57ab05e2dc529a', '4810ddf0-5', '4274', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('00b2830390594426a0a5dea8fec175a1', '1', '96ffbc0e126e4eb2aa7c8a8e32305ec5', '1878928c-e', '3890', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('00b51ab44f5b43df8685021868a1205b', '2', '3f9fc1e7d4ae4730a32751e3775f6dbd', 'b36efb9b-d', '1008', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('00b971dd3b244796aa8e4f60b648a2fa', '2', 'b571310825f943c0b8f0f6213772cda0', '467e29ab-a', '2477', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('00ba80ae184747a29af11b363177399c', '2', '7c9a4def0ff04adc87c0751d93712caf', '411e4815-c', '3882', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('00bbf63818d24bf78712b6e947ace463', '1', '12937726fe7f4ca8bdeb12151e46308e', '52beb67a-6', '1379', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('00c16791b0a3473d997b5c4cd971de0f', '2', '1350efeaedfe44f685fe2d9a6b5cfca6', '003c1e96-e', '2598', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('00c7f2d93750490f8bd501df0a31df35', '2', '63b25bbd4d014ff09618b299046e0cda', 'ad041f2c-4', '1882', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('00ce35e2428f4adf9cfc083c8f58ee00', '2', '5269f3b15a9041ab8d4bc690f0df5bd3', '611c69f1-5', '2274', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('00cf48b941b34556add34d272af187fa', '1', 'deef6f6f85e7446fa6d7a50c4d7e68e3', '02c92ab5-1', '2086', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('00d439a1e03c47dc8daa1e3da42db2e3', '1', '2c58c240b8654198aca30a4798819681', 'c3eefaf8-e', '481', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('00d8503525a146e4b5b51ffae6229b33', '2', '5e7882dcd8bd4c2ea7140ca588c5508a', 'd943480d-0', '2866', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('00db108a362d42c2a78a74fd56ca403b', '1', '21532625adf54db29b3e23666bcd16c4', '283aa0c1-d', '3162', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('00dc77dc7d3b4d97b7a3892813dd7529', '1', '503b2308ca844f90b7a640d526a920b8', '23ff34c3-2', '1430', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('00e336f1a25d48e1a715d80ae4f29570', '2', 'd05731ffaf154ec495ae1f6be253b9e8', '9f78d4fb-3', '863', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('00e80183d57d4c129719cd22dbc83eb4', '1', '3e931ec4e78f4e27b6374e7cd970ed2f', 'd250c3ce-e', '188', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('00fcd43fcdd9416d9b4f1884e9ffdbf6', '1', '793b3e13d8b54780aa70fa60758f352f', '71bae191-6', '176', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('00ff9a53122f4d7cb36043fb62def79e', '2', '1fdf3305337741a4a0e02a0385a3e00e', '474c75db-1', '2820', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('01016764feda43f99f84c82952834cb8', '2', '0ce3a49596214cd4bdcdd7094b7a3c18', '2b2c178b-f', '4022', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('010e192a58ff4bfba10341ca19f807ca', '1', '627973380bb14d829ba3a6173ba8db4e', '85487473-5', '4101', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('01100b163c90404b8fb06485267e2bba', '1', '11d751de17cc49488ed41de54a63a38a', 'f20bfe23-2', '2603', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('011177786dcb4572be7c4822782123a0', '1', '510e9217d1674660974e4660082558f8', '4608d8ea-c', '4231', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('0112de115a6a4e129f2ef31a3a6a27d3', '1', '9d6fe7a9097545e8ae5bb4050933f6d7', '95a56d51-c', '4170', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('012138c07cf74164ad0829ccd058205c', '1', '6f7fdeeec4eb46feb86045b762e88bb2', '84fed0c7-e', '3153', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('0121b6c491a24e3eb2ab265dce82eb27', '2', '5cb8054f9bd94f4eb14ef87429bbff87', '6f8e478f-8', '3356', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('01239926bbe649d88c8b46b30df7b41b', '2', '4bb650176cf14a9cb0d496b4e06da98f', 'a0b88be2-0', '3315', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('0124e71ad29347eeaaa5dbb324e97ee2', '1', '872389dcad8f474bb7d0c49a735f9455', '8427c519-8', '1480', null, '1', '2015-01-25 21:06:11', '2015-01-25 21:06:11'), ('013d783f29914f2b8b71602021856e73', '2', '6b93e0e796264ae4b4beca209ef6434b', '4367c6b1-a', '859', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('013f007f5af84ce484d1808fa4687a12', '2', 'c47c344f084a43f79c19397b0fad02da', 'eae107ba-1', '2625', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('01402b798cdb41eb9c8a2db25f93821a', '1', 'f9e74c9e89b24b9da01bf75c99c88f8d', 'bdf621c8-5', '330', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('01408ffcc54e40cfb0c14c31a379cfbf', '1', '9a0c6856bfa048f0b9405c7e10593dd4', '48cb7a7b-0', '230', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('014b371cb26843178d31dd531fa6ebfb', '1', '1ceec4436ecb49bf9e63927fe859b5bc', '754e4941-6', '1586', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('015229547c764e2a9faa2b191f9c50a5', '1', 'b72bc9a25ecf4ced8e5e31b81200b58f', '60af43c3-8', '3899', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('01549f667f4543ea99b4ed1eb928cb54', '1', '8c0d8973f0fd40b1a07f507455cef74e', 'e5666198-b', '2973', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('01560193e7e8454a81569ad199cad1e3', '2', '8239fe4f17f54ea2af35edac4f55b989', 'fc18dd38-2', '2724', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('015974ee23c846e399852757f38df8ab', '1', '75f6311bbfd94d03a9c5baa45b6ebfd7', '73b9f737-b', '4747', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('0159c69147234828a5e2b6ec8ff922c6', '1', '9fde4f285a7745b59dd8ca75ca039902', 'ca586401-a', '2114', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('015a1849854c48a8baae5a771b0f6c1a', '1', 'cc58b53b82e24f1b9b8550038a42a90f', '4f13427c-7', '3094', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('015c7a29f2b84c44b58e338a1e9a3841', '1', 'e7582eebdee94d8d81230edb04417111', '63c435d0-4', '4926', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('01662d0fb5f14dbdad2996807d5b6d63', '2', 'efad2d4e12974beda7d0f4da1f5aa1d1', '70c757e7-9', '3965', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('016ef9efe247426bbb25eaea4446cca1', '1', '0b0597575d0242d2b21c6829bcb94472', 'b51024ab-e', '529', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('0174335f30344ea89d992f56eef10074', '1', 'bb23897f6f194bc4a920a4cedb4c8e80', '69c029ee-d', '171', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('0175e0554565430bbc07af6250c8113e', '1', 'dbae852d55674ae98ae31d32bf94a6cd', 'a4dd90f6-c', '2958', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('01830c7bfbad4d70965fb9f7f7e60a3f', '2', '692f1fa990c44ebea644c7f8a790e609', 'e7e57d1f-0', '2877', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('01836c4bc9eb4a90927e1b499efde298', '2', '3dc98777282740e2a799edfb8bcdc650', '74fcf025-c', '4350', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('018ab0aebf254e5ea8ced2ea07b46829', '2', '6f8058b03b1f4ceb95a5737032e8ea20', '50a6bc9e-5', '2212', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('018c85f92c0c44c7b2d92f381fba7ebe', '2', '01e575011fbe4c8894f60639f7d3bcf9', 'd699b87e-1', '2961', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('018db26d9cb34db7ae7053c6e93c91a4', '1', 'e46b38e398e24a019b08bc4044e5b368', 'e4c8087a-f', '1949', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('01950b3bf4a548ffa3e4a41b0fa3526b', '2', '2f8cbb4dc71340c098366b6699c602ec', 'aa09a41b-6', '2301', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('01a4e7a343924277ad84b8679310c711', '1', '62b247f6420941c790d40bdeebf84174', '3bf58cc3-2', '1798', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('01a58883a0e64932930290b07737e7e4', '2', 'baa4aa9826cf4269a171cb88ac5ea1ad', '40d9c228-f', '401', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('01aa6a5014c94fbbaf448ebf8c8e4a6a', '2', '4cc9e65c4e7941d8a43169d419c3515a', '17e0d837-0', '641', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('01b179b209d649c9b28203d3369b9662', '1', '17c804b62d494aeca800424a6287929b', 'a3f6df69-4', '1276', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('01b2567e6cc447cbb48445c96b0b957d', '2', '3c5f9b02702444138db6029250a7b158', '34fa9dfb-9', '1335', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('01b25bbab8524de1832b5627f647dc90', '1', 'f38c437545064599a4b5516110cd2cf6', '2de30894-d', '1189', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('01b320edafa14b0090ac6018e80b870b', '1', 'db5884cf280b45ef8b308a469bf11023', '873f80a6-b', '852', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('01b536d0ecd347cfabc720b5fe52d240', '1', 'e6de7b264ac14654944eeae0ba00d5c0', '6fde1efc-5', '2721', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('01b561da115745be9c4c7b8470cbd49b', '1', '011b8bbc463145b4b17dcdb1227a4333', '7c4dea0a-0', '3680', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('01b575370f284bbe90d1ae055aba3aa1', '1', '898c5877da984649920d317320eeeb60', '3fa890bc-c', '2540', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('01be3e79cc124353a0978efd10a245cb', '1', '113419bad81a40ccaa9b8d2881c9184f', '9b8a1d81-9', '3476', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('01c6314f02dd49189ca36bbf2baeb668', '1', '6fef07f4a7b641b59a7ed42597c86aed', 'edbe023e-3', '177', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('01c743c4bc3047699d9911ff74664a57', '2', '9a6c1670edf14e20992d7328afc63576', 'ebc67ec4-d', '1048', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('01c774c9cbe54217aea6aa0727736571', '2', '0cb17562fe8b49f4aab54472d36576c9', '74d0fc7a-4', '2100', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('01cce4932f084f9ba7016ed8d974388f', '1', '309c6b8bdaa647c58ab901cd38aa7e8a', 'd82a7afa-b', '2443', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('01ce3cb55ac64ecd81cc4be7b39075ba', '2', '50b03deb2d1c4099b0c36e2f317cbbd1', 'b8946264-5', '607', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('01cf4e9b647a43369c33ff206a92f8b2', '2', 'f26dce3142a24b27aec6cc709217ed57', '6fde86ce-d', '4501', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('01d0b7b39593490fa887e7bfedc08a22', '1', 'ad4ec65ff1454c0dbffb96ff58629c8b', 'dfc050b8-b', '4616', null, '1', '2015-01-25 21:06:11', '2015-01-25 21:06:11'), ('01d1e80284124cd7a321558ed132fa52', '1', 'd7241ff3b0d548d8a73a0b3e3245174f', 'afc78b77-8', '4544', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('01d2d578b3fb422e9fdeff664320faf9', '2', '919ac50aa0af4fae998e0d5f56013fb5', '2075e570-8', '2723', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('01d8a34933e6486e85a47ccf3cf2feb7', '2', '970e46b972f240c09c28ab4e61b6083f', 'af43ec7c-b', '1724', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('01d920df05304a4e8037c4c00ed041d7', '2', '340a8e0b242c4d17b7b7fc680d0923cb', 'cb05d86f-2', '4687', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('01d9f84112984742a38366076907b291', '2', 'b4831d3dd7e34649bac92c911b8d06bd', '7d8164a1-7', '3244', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('01dd5f63f555413ca03848332983287a', '1', '04b4c202cf29413a8b99fbe57a42ee14', '8a6a7b68-1', '3481', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('01e3dd3a0ceb4ea9810906cd83c6eb00', '1', '68b0c0a15c024fd3a0ba1d73aee46971', 'c82f534a-f', '2014', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('01eb28b0caec42fb93b6682ceb304613', '1', '046d8170332049199b6297cbe372dbd3', '13f9cc29-3', '2178', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('01f094f075de455199f42b89ccc868a3', '1', '75f490c4252f4a1eb43bbcb169873c21', 'b5b75f71-8', '3483', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('01f2196970fb4c4a95b27b0ea081eb42', '1', '1f4be0d0cff8463eaaf05f4eecdaff4b', '0aba6bef-a', '4118', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('01f3f3f829d045c2932371d3de26dc8d', '2', 'da14243d4162495d9345e64b0cf8bd4f', 'd9454afd-d', '1745', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('02095bb52c0a439b91c889ffeae94b0c', '2', 'd081b6ddb16c43d9a472de074ab63d70', '65383646-5', '376', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('020fdf6a51354779bb95edc98420549e', '1', 'c4e9ab5ff47846f3bf62ecc7a9f26d35', '456e2f32-c', '4734', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('0210682e4d184926b9105d17a485a75b', '2', '8ee67faf078e49e2b593c5f77d3daf84', '450954b8-4', '2489', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('02136e3d44854efaaa930f3eb3eb4a62', '2', '32ff44d43cc04c97a9bd34a69ae0658c', '5dec7d4c-a', '2325', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('02144d8cd4b94fce96ca35982d7b10d9', '2', '1de900790ad4486eb7f1448c7df17186', '7cf9bb04-3', '1157', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('021620467bad4e1d894e45ba8db310c3', '1', '0bf25fcbf2ca4837b2ddeb173a8c143b', 'd97f64db-7', '113', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('0219ee27d7ad4d8ca530f568cdb92f05', '1', 'f59c15024f54473ca41665a39a9d0d0b', '5d22fbc1-c', '1720', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('021eb624a53240bb9233e011f4cf5691', '1', '4a2ffa1ea85445e68f53e65d50631e59', '0f054d76-9', '510', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('0224172da63d406cbe24f79cd752146f', '2', '083b205f131646ca854b54f67ad294cb', 'fd16547d-e', '2040', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('02241ab5373b423d99e9d94358555e9d', '1', 'd2293be57e444db3a926e09754604de5', '86b44344-7', '1042', null, '1', '2015-01-25 21:06:11', '2015-01-25 21:06:11'), ('022be743a4624e0d8e92c44348d7271c', '1', 'fbe37c418c794c76a4605c3636aecd4a', '4a1ec0a0-1', '2740', null, '1', '2015-01-25 21:06:11', '2015-01-25 21:06:11'), ('022e6d3825d946adaf800b60d2801132', '2', 'b383474aee384ef6892f41462963bfb3', 'b70b100d-b', '3788', null, '1', '2015-01-31 14:25:02', '2015-01-31 14:25:02'), ('022fc6db8849472da5302d6cdf70d219', '1', 'eb244ffc83784cfba8a2bcc67473fab7', '46f7e0ef-d', '2240', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('0230a53bc5c340fdb6c3ad6f3eea0e2f', '1', '94f5a4d6d5f7491fa8fa42a092b5c26a', 'fce544c2-f', '4947', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('0233ee48ca444a2bbd6f22babf35ee34', '1', 'd22d46eca9bb4a95adeb275f3e91d40f', 'ba96cac1-b', '891', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('023a43fc212c4aac951bc8b91657c10d', '1', '000dcf761b964abb8ae1e79568d753f6', '78710bf8-d', '235', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('024730a63d0f4123af2c9edbfa1462e6', '1', '4e23fd3ab2244a4b9b9d41fc5f6c3f59', 'dcc6bdf8-7', '4408', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('024b815403d141cca41c0b6a65a41f50', '1', '16514f30b00246f8ad05f4936efd7b5d', '00e0f892-7', '517', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('024ebd61fa3c4d418f8095bc7290be7f', '1', '84a32a3e2250427b92ecfb7654c8a2e9', 'c7800a31-e', '4655', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('0252ec3bb70d4d38abf7349a8671dbea', '1', '3f9eb21f76af4d9f84092d4e4fdfda30', '110b6ae7-9', '2941', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('025436cc07004d3fa40238c3e39c90fe', '1', 'e8e2fe6ed8564f7bbbbc86581a14b762', '3ef861eb-9', '1586', null, '1', '2015-01-25 21:06:11', '2015-01-25 21:06:11'), ('02551113850841bca058d5bd41687911', '1', '09b4fd05c4774ef98b42de77b08a1ce7', '3b4af395-0', '2578', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('0255c144a9f44d9312asdf1e9671', '1', '1c47120babad48d395ac9c84d35fd9bb', '6c971ee7-b', '1033', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('0255c144a9f44d93bf32f7b6501e9671', '1', '936d7375b62745c1aa0987597e35d5a5', '045c8843-3', '4748', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12'), ('025840502ddb4b338d3719421c9c48d7', '1', 'ce8e2770a3d648359dbbbb4acb9db7f6', '81661c68-9', '3686', null, '1', '2015-01-25 21:06:12', '2015-01-25 21:06:12');
COMMIT;

-- ----------------------------
--  Table structure for `t_card_upload`
-- ----------------------------
DROP TABLE IF EXISTS `t_card_upload`;
CREATE TABLE `t_card_upload` (
  `upload_id` int(11) NOT NULL,
  `total` int(10) DEFAULT NULL,
  `filename` varchar(255) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `insert_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`upload_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_card_upload`
-- ----------------------------
BEGIN;
INSERT INTO `t_card_upload` VALUES ('1', '7999', '2015-01-25_ff8bc764ccc34c77bfa206f21c0ce6b3.xlsx', '1', '2015-01-25 21:06:03', '2015-01-25 21:06:17'), ('2', '4999', '2015-01-31_d5d722de07b04c1a93488ce485c87cd9.xls', '1', '2015-01-31 14:24:54', '2015-01-31 14:25:04');
COMMIT;

-- ----------------------------
--  Table structure for `t_cart`
-- ----------------------------
DROP TABLE IF EXISTS `t_cart`;
CREATE TABLE `t_cart` (
  `cart_id` varchar(32) NOT NULL,
  `member_id` varchar(32) DEFAULT NULL,
  `product_id` varchar(32) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `product_img_name` varchar(255) DEFAULT NULL,
  `score` int(10) DEFAULT NULL,
  `quantity` int(10) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `insert_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`cart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_cart`
-- ----------------------------
BEGIN;
INSERT INTO `t_cart` VALUES ('600363d9e6b24df0a1787cc4b57192bc', '82ac42b5e1fb4766bad2f9d65597794d', '2bec035fc64440a095cffb7d1693296c', '蓝光雨伞手机壳 iPhone三星型号适用', 'c316c6a2e71a4901ad3d8236ae0e9ec3.jpg', '168', '1', '1', '2015-02-06 20:33:53', '2015-02-06 20:33:53'), ('e6f7b14b53bb4544a26550ff24487c35', '82ac42b5e1fb4766bad2f9d65597794d', '0a8906d6087e4e8cb3decfea2eeeba34', '柒花蜂性活性肽全营养原浆面膜（六边形套盒）', '23efef2c72904d90b9285e41ecaf62f7.png', '376', '2', '1', '2015-02-10 19:24:12', '2015-02-10 19:24:12');
COMMIT;

-- ----------------------------
--  Table structure for `t_home_img`
-- ----------------------------
DROP TABLE IF EXISTS `t_home_img`;
CREATE TABLE `t_home_img` (
  `name` varchar(32) NOT NULL,
  `size` bigint(20) DEFAULT NULL,
  `extension` varchar(10) DEFAULT NULL,
  `product_id` varchar(32) DEFAULT NULL,
  `position` varchar(1) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `insert_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`name`),
  KEY `idx_pi_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_home_img`
-- ----------------------------
BEGIN;
INSERT INTO `t_home_img` VALUES ('4eda733504644413b6264e303a74a802', '890257', 'png', null, '1', null, '2015-02-07 11:57:13', '2015-02-07 11:57:13'), ('6a74a0acf51345cda57c5fe2002c32d2', '1587062', 'png', null, '1', null, '2015-02-07 11:57:13', '2015-02-07 11:57:13'), ('723b68b033a84afba28398dbae411f80', '470543', 'png', null, '2', null, '2015-02-07 11:57:21', '2015-02-07 11:57:21'), ('bf6f25731f1c4b74ae53e7fdcc53d86c', '470543', 'png', null, '2', null, '2015-02-07 11:57:19', '2015-02-07 11:57:19'), ('c9135976cd4f4985b1a65c0fc4595041', '1359858', 'png', null, '1', null, '2015-02-07 11:57:13', '2015-02-07 11:57:13');
COMMIT;

-- ----------------------------
--  Table structure for `t_member`
-- ----------------------------
DROP TABLE IF EXISTS `t_member`;
CREATE TABLE `t_member` (
  `member_id` varchar(32) NOT NULL,
  `member_name` varchar(50) DEFAULT NULL,
  `password` varchar(32) NOT NULL,
  `mobile` varchar(20) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `score` int(10) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `insert_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`member_id`),
  UNIQUE KEY `idx_member_mobile` (`mobile`) USING BTREE,
  UNIQUE KEY `idx_member_email` (`email`) USING BTREE,
  KEY `idx_member_name` (`member_name`),
  KEY `idx_member_name_password` (`member_name`,`password`),
  KEY `idx_mobile_password` (`password`,`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_member`
-- ----------------------------
BEGIN;
INSERT INTO `t_member` VALUES ('0c3889a322604d4b856330df52da43c9', 'nickevin', 'a66abb5684c45962d887564f08346e8d', '13818112388', 'nickevin@gmail.com', '湖州市吴兴区浙江省湖州市中级人民法院(仁皇山路)', '5180', '1', '2015-12-06 20:39:36', '2015-12-06 20:40:24'), ('240871917dfc46ca94603c9dd9d1a3d1', '', 'a66abb5684c45962d887564f08346e8d', '15215215252', '152@163.com', '', '11552', '1', '2015-02-08 20:43:27', '2015-02-08 20:59:06'), ('82ac42b5e1fb4766bad2f9d65597794d', 'aopfilter', 'a66abb5684c45962d887564f08346e8d', '13818112391', 'aopfilter@163.com', '上海市徐汇区上海市徐汇区房地产交易中心', '61', '1', '2015-11-15 09:34:03', '2015-11-15 09:34:03');
COMMIT;

-- ----------------------------
--  Table structure for `t_member_card`
-- ----------------------------
DROP TABLE IF EXISTS `t_member_card`;
CREATE TABLE `t_member_card` (
  `member_id` varchar(32) NOT NULL,
  `member_name` varchar(50) DEFAULT NULL,
  `card_no` varchar(100) DEFAULT NULL,
  `card_password` varchar(100) DEFAULT NULL,
  `score` int(10) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `insert_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  KEY `idx_card_no` (`card_no`),
  KEY `idx_card_no_password` (`card_no`,`card_password`),
  KEY `idx_card_insert_time` (`insert_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_member_card`
-- ----------------------------
BEGIN;
INSERT INTO `t_member_card` VALUES ('82ac42b5e1fb4766bad2f9d65597794d', 'aopfilter', 'a7ea4e67c92841e0bb45da17b4a6683a', 'dafb269c-6', '4252', '2', '2015-02-03 22:07:10', '2015-02-03 22:07:10'), ('240871917dfc46ca94603c9dd9d1a3d1', '', 'bc4de9fc51ba4203bbe34939191e4086', '1a4aaad9-8', '1027', '2', '2015-02-08 20:44:54', '2015-02-08 20:44:54'), ('240871917dfc46ca94603c9dd9d1a3d1', '', 'f60ad065d78b49b983c1d71810c107ec', 'bb50764c-5', '406', '2', '2015-02-08 20:47:42', '2015-02-08 20:47:42'), ('240871917dfc46ca94603c9dd9d1a3d1', '', '1b6d21a872b04e2b833cc8fac853a261', '38ac3f8b-b', '2444', '2', '2015-02-08 20:49:25', '2015-02-08 20:49:25'), ('240871917dfc46ca94603c9dd9d1a3d1', '', '0ac37a1cd390439f8b362523694fcbed', '0782a416-a', '4711', '2', '2015-02-08 20:51:41', '2015-02-08 20:51:41'), ('240871917dfc46ca94603c9dd9d1a3d1', '', '95af94d513fd4c28b577fb5705e9ee36', 'd50f7692-d', '1154', '2', '2015-02-08 20:52:36', '2015-02-08 20:52:36'), ('240871917dfc46ca94603c9dd9d1a3d1', '', '15776d7389ec48e3b9d34564d3503e19', 'e9dea5a3-c', '2070', '2', '2015-02-08 20:58:35', '2015-02-08 20:58:35'), ('0c3889a322604d4b856330df52da43c9', '13818112388', '29da3c90604b4d11aec87e9e600e4b65', '83cfc262-6', '1499', '1', '2015-11-15 13:27:18', '2015-11-15 13:27:18'), ('0c3889a322604d4b856330df52da43c9', '13818112388', 'c44c9dea9f30485493b0813fc0044f33', 'f1d86a33-c', '2771', '1', '2015-11-15 13:41:10', '2015-11-15 13:41:10'), ('0c3889a322604d4b856330df52da43c9', '13818112388', 'd218d37797b043c2a4db588991d205e2', '78594549-7', '1316', '1', '2015-11-15 15:11:43', '2015-11-15 15:11:43');
COMMIT;

-- ----------------------------
--  Table structure for `t_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `MENU_ID` int(10) NOT NULL,
  `MENU_NAME` varchar(100) NOT NULL,
  `PARENT_ID` int(10) DEFAULT NULL,
  `MENU_URL` varchar(100) DEFAULT NULL,
  UNIQUE KEY `uni_menu_id` (`MENU_ID`) USING BTREE,
  UNIQUE KEY `uni_menu_name_url` (`MENU_NAME`,`MENU_URL`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_menu`
-- ----------------------------
BEGIN;
INSERT INTO `t_menu` VALUES ('1', '根系统', '0', null), ('10', '系统', '1', null), ('20', '业务数据', '1', null), ('1001', '用户管理', '10', '/console/user'), ('2001', '产品管理', '20', null), ('2002', '订单管理', '20', '/console/order'), ('2003', '文章管理', '20', ''), ('2004', '会员管理', '20', '/console/member'), ('2005', '礼券管理', '20', '/console/card'), ('2006', '首页管理', '20', '/console/home'), ('200101', '商品分类', '2001', '/console/product/category'), ('200102', '商品管理', '2001', '/console/product'), ('200103', '图片块管理', '2001', '/console/product/img-block'), ('200301', '俱乐部', '2003', '/console/article?p=club'), ('200302', '伙伴入驻', '2003', '/console/article?p=partner'), ('200303', '联盟商家', '2003', '/console/article?p=merchant');
COMMIT;

-- ----------------------------
--  Table structure for `t_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `order_id` varchar(32) NOT NULL,
  `order_no` varchar(32) DEFAULT NULL,
  `member_id` varchar(32) DEFAULT NULL,
  `recipient` varchar(255) DEFAULT NULL,
  `mobile` varchar(50) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `zip` varchar(10) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `remarks` varchar(500) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `insert_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_order`
-- ----------------------------
BEGIN;
INSERT INTO `t_order` VALUES ('b548fa0f37534c14912617fcd7e834dd', '20151201210117761', '0c3889a322604d4b856330df52da43c9', 'nickevin', '13818112388', '', '211300', '上海市浦东新区上海abb工程有限公司', '', '1', '2015-12-01 21:01:18', '2015-12-01 21:01:18'), ('db22e2ea75894992b06e278c5b608803', '20151201204649154', '0c3889a322604d4b856330df52da43c9', 'nickevin', '13818112388', '', '211300', '上海市浦东新区上海abb工程有限公司', '', '1', '2015-12-01 20:46:49', '2015-12-01 20:46:49'), ('dcd39586c67d4e25a984fb80efa2901a', '20151201212534201', '0c3889a322604d4b856330df52da43c9', 'nickevin', '13818112388', '', '211300', '上海市浦东新区上海abb工程有限公司', '', '1', '2015-12-01 21:25:34', '2015-12-01 21:25:34'), ('e1d17f6074ac444eac416c4b4ebbea5b', '20151206204023522', '0c3889a322604d4b856330df52da43c9', 'nickevin', '13818112388', '', '211300', '上海市浦东新区上海abb工程有限公司', '', '1', '2015-12-06 20:40:24', '2015-12-06 20:40:24'), ('fc2e4c5160db408f872c082b5dd908c2', '20151201210301748', '0c3889a322604d4b856330df52da43c9', 'nickevin', '13818112388', '', '211300', '上海市浦东新区上海abb工程有限公司', '', '1', '2015-12-01 21:03:02', '2015-12-01 21:03:02');
COMMIT;

-- ----------------------------
--  Table structure for `t_order_item`
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item`;
CREATE TABLE `t_order_item` (
  `item_id` varchar(32) DEFAULT NULL,
  `order_id` varchar(32) DEFAULT NULL,
  `product_id` varchar(32) DEFAULT NULL,
  `product_name` varchar(50) DEFAULT NULL,
  `product_img_name` varchar(255) DEFAULT NULL,
  `quantity` int(10) DEFAULT NULL,
  `score` int(10) DEFAULT NULL,
  KEY `fk_order_id` (`order_id`),
  CONSTRAINT `fk_order_id` FOREIGN KEY (`order_id`) REFERENCES `t_order` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_order_item`
-- ----------------------------
BEGIN;
INSERT INTO `t_order_item` VALUES ('a862ef84d249424a8a830e114f998fa5', 'db22e2ea75894992b06e278c5b608803', '1411f65b30c94e74b4428f93772eb6a5', '糖果色彩格子棉拖鞋', '763ebf2da6494fcf9291426d75ed0616.png', '1', '10'), ('05c683ebe8da41b8bf1e48cf85a76f24', 'b548fa0f37534c14912617fcd7e834dd', '1411f65b30c94e74b4428f93772eb6a5', '糖果色彩格子棉拖鞋', '763ebf2da6494fcf9291426d75ed0616.png', '1', '10'), ('032a968e259f4146808c03982600334f', 'fc2e4c5160db408f872c082b5dd908c2', '1411f65b30c94e74b4428f93772eb6a5', '糖果色彩格子棉拖鞋', '763ebf2da6494fcf9291426d75ed0616.png', '1', '10'), ('acf1062c3ccc4690ade139f888e81321', 'dcd39586c67d4e25a984fb80efa2901a', '1411f65b30c94e74b4428f93772eb6a5', '糖果色彩格子棉拖鞋', '763ebf2da6494fcf9291426d75ed0616.png', '2', '10'), ('3788bc26f6dd444d9eaa597413742fb6', 'e1d17f6074ac444eac416c4b4ebbea5b', '2a073ffec5ae4d3aa808cc653256299a', '祖科光源 探照灯 +移动电源', '1e8a3fc852ed4b218bce001f1e4965ff.jpg', '1', '356');
COMMIT;

-- ----------------------------
--  Table structure for `t_product`
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product` (
  `product_id` varchar(32) NOT NULL,
  `product_code` varchar(255) DEFAULT NULL,
  `product_name` varchar(100) DEFAULT NULL,
  `category_id` int(10) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `score` int(10) DEFAULT NULL,
  `rate` int(1) DEFAULT NULL,
  `is_recommend` int(1) DEFAULT NULL,
  `is_flash_sale` int(1) DEFAULT NULL,
  `on_sale_date` date DEFAULT NULL,
  `countdown` int(2) DEFAULT NULL,
  `description` longtext,
  `status` int(1) DEFAULT NULL,
  `insert_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `idx_product_name` (`product_name`),
  KEY `idx_product_score` (`score`),
  KEY `idx_product_insert_time` (`insert_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_product`
-- ----------------------------
BEGIN;
INSERT INTO `t_product` VALUES ('0a8906d6087e4e8cb3decfea2eeeba34', '0105553600002', '柒花蜂性活性肽全营养原浆面膜（六边形套盒）', '250150', '0.00', '188', '4', '2', '2', '2015-02-05', '12', '<p><span style=\"font-size:24px\"><span style=\"font-family:微软雅黑\">柒花蜂性活性肽全营养原浆面膜（六边形套盒）</span></span></p>\r\n\r\n<p><br />\r\n<img alt=\"\" src=\"/Qihua/resources/images/upload/ckfinder/images/product-1.jpg\" style=\"height:7828px; width:759px\" /></p>\r\n\r\n<p>&nbsp;</p>\r\n', '1', '2015-02-09 19:01:28', '2015-02-09 19:01:28'), ('1411f65b30c94e74b4428f93772eb6a5', '0105672990002', '糖果色彩格子棉拖鞋', '100150', '0.00', '10', '3', '2', '1', '2015-02-05', null, '<p>var editor = CKEDITOR.replace(&#39;description&#39;, {<br />\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;height: 1000<br />\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;});<br />\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;CKFinder.setupCKEditor(editor, &#39;&lt;%=basePath%&gt;/resources/lib/ckfinder&#39;);</p>\r\n', '1', '2015-02-05 22:18:03', '2015-02-05 22:18:03'), ('2054ff4ae831492fad90ea290600c806', '0105363000002', '酷迪尔中国风迷你可爱移动电源', '400150', '0.00', '380', '4', '1', '1', '2015-02-05', null, '', '1', '2015-02-05 23:30:08', '2015-02-05 23:30:08'), ('21b1eeb892a1445bb2d1df135a3d25bb', '010567WS00002', '正品保温杯男女士便携杯 不锈钢大肚杯情侣创意学生水杯儿童杯子', '200150', '0.00', '580', '3', '1', '1', '2015-02-05', null, '', '1', '2015-02-05 22:18:00', '2015-02-05 22:18:00'), ('2943758d3cf8428a8ebcb12923bc1060', '0105183300026', '超维 一指禅 充电 LED野营灯 露营照明灯 创意触控开关 亮度可调', '400150', '0.00', '260', '3', null, '1', null, null, '', '1', '2015-01-31 16:22:02', '2015-01-31 16:22:02'), ('2a073ffec5ae4d3aa808cc653256299a', '0100273000004', '祖科光源 探照灯 +移动电源', '300150', '0.00', '178', '3', '1', '1', '2015-02-05', null, '', '1', '2015-02-05 22:17:58', '2015-02-05 22:17:58'), ('2bec035fc64440a095cffb7d1693296c', '0105742800002', '蓝光雨伞手机壳 iPhone三星型号适用', '350150', '0.00', '168', '3', '1', '1', '2015-02-05', null, '', '1', '2015-02-05 22:17:57', '2015-02-05 22:17:58'), ('2cb0dba0588f42769977c4474c0f449a', '0100193000004', '郁金香 移动电源又是一件精美的挂饰，轻松便携', '250150', '0.00', '199', '3', null, '1', null, null, '', '1', '2015-01-31 16:20:09', '2015-01-31 16:20:09'), ('487f156feafa406ea2c19491bfb984b3', '0105672900002', '电热水壶不锈钢电烧水壶快壶电水壶', '250100', '0.00', '30', '3', '1', '1', '2015-02-05', null, '<p><img alt=\"\" src=\"/Qihua/resources/upload/ckfinder/images/2.jpg\" style=\"height:260px; width:340px\" /></p>\r\n', '1', '2015-02-05 22:17:57', '2015-02-05 22:17:57'), ('51bb5658a7df442980c10701e71db9a6', '0105523000001', '新款正品LED时尚风台灯触摸护眼学习充电USB台灯 时尚简约风格', '350100', '0.00', '1997', '3', '1', '1', '2015-02-05', null, '', '1', '2015-02-06 07:46:08', '2015-02-06 07:46:08'), ('5a0a37c1badd4bb5b3973f0af97b0318', 'DW05672900002', '超维蓝牙音箱重低音炮手机无线音响迷你音响便携式户外外放', '500150', '0.00', '668', '3', null, null, null, null, '', '1', '2015-01-30 21:51:06', '2015-01-30 22:05:38'), ('6a428bab13af41458dc1ad38a211c592', '0105372900001', '柯达—超大容量数码像框可 放置于任何位置', '350150', '0.00', '180', '3', null, null, null, null, '', '1', '2015-01-31 16:21:14', '2015-01-31 16:21:14'), ('72ee686d00e44ae7928840466eeaa956', '0100202100024', '典雅生活卫浴套装（三件套）', '200150', '0.00', '628', '3', null, null, null, null, '', '1', '2015-01-30 22:03:10', '2015-01-30 22:03:10'), ('73622b956917478e9bc6d10ae03d5629', '010567290ZX02', '无声无光苹果鼠标 （水晶盒包装）', '200100', '0.00', '220', '4', '1', '1', '2015-02-11', null, '<p>无声无光</p>\r\n', '1', '2015-02-06 07:40:37', '2015-02-06 07:40:37'), ('7f8ebbf3d40e4134a15fbb524a597042', '01056XD900002', '恒源祥 罗马假日四件套', '450100', '0.00', '260', '3', null, null, null, null, '', '1', '2015-01-30 21:50:11', '2015-01-30 22:05:21'), ('83e280f5807a47f38e0dcf4b8a674a44', '0105463000008', 'MD80摄像机 质感好 有档次', '450150', '0.00', '480', '2', null, null, null, null, '', '1', '2015-01-30 21:55:35', '2015-01-30 22:00:50'), ('9024718520144f74b616725b6314ecb0', '0105433100007', '屁颠虫音响', '350150', '0.00', '876', '3', null, null, null, null, '', '1', '2015-01-31 16:23:29', '2015-01-31 16:23:29'), ('a47b40d880c34f778b60c6b6f6f49c6a', '0105363100013', '音悦圣蛋蓝牙音箱', '350150', '0.00', '786', '5', null, null, null, null, '', '1', '2015-01-31 17:49:35', '2015-01-31 17:49:35'), ('a5b468a2ee0f4536830777d27c5ea93d', '0105691000006', '布洛克登山鞋 男鞋防水户外鞋 女士情侣新款防滑徒步鞋', '100150', '0.00', '648', '2', null, null, null, null, '', '1', '2015-01-30 23:15:56', '2015-01-30 23:16:10'), ('a92806f7d73a4ce2a0a3417c601fb58f', 'A105672900002', '大井栏紫砂茶具套装', '400100', '0.00', '20', '5', null, null, null, null, '', '1', '2015-01-30 21:49:33', '2015-01-30 22:05:15'), ('cf876a95d021415db639674891cf8e4d', '0105303600004', 'DNA蓝酮精华液：采用高科技微脂囊包覆技术精华渗透肌肤抗衰老', '400150', '0.00', '840', '5', null, null, null, null, '', '1', '2015-01-30 22:02:47', '2015-01-30 22:02:47'), ('d335a843726b411ebd80fd034fae5ba0', '0100075800002', '电购首选现代高清行车记录仪', '250150', '0.00', '987', '3', null, null, null, null, '', '1', '2015-01-31 16:23:43', '2015-01-31 16:23:43'), ('d930f5bf7d484f4698de5b3a1c5c73b1', '0100043300016', '福瑞客折叠电吹风 温热风调节', '250100', '0.00', '10', '3', '1', '1', '2015-02-07', '0', '', '1', '2015-02-07 12:06:23', '2015-02-07 12:06:23'), ('db96674b7be244d6ad57a8f136d8a51b', '0105072900002', '蒸滋味 传热迅速 节能省时', '500100', '0.00', '70', '5', null, null, null, null, '', '1', '2015-01-30 21:50:30', '2015-01-30 22:05:09'), ('e3f07becffcc4e15b1a4e8a42678ab1e', '0105213000010', '移动电源 水晶盒装充电宝礼品', '300150', '0.00', '258', '3', null, null, null, null, '', '1', '2015-01-31 16:22:22', '2015-01-31 16:22:22'), ('e621dee0a8aa44b39e69ce405482a3e0', '0105742800003', '蓝光蝴蝶手机壳 手机配件', '100150', '0.00', '159', '3', null, null, null, null, '', '1', '2015-01-31 16:19:23', '2015-01-31 16:19:23'), ('efd2117017364bbfbb5f19affd325c44', '0100365500001', '厨房万用纸巾 原价198礼券直降99礼券，活动仅限500个，兑完立即恢复原价', '100100', '0.00', '1450', '3', null, null, null, null, '', '1', '2015-02-04 20:38:16', '2015-02-04 20:38:16'), ('f13dcf6af2ec45338f3e3bfb82b1d094', '0100273000009', '炫派智能行车记录仪', '400150', '0.00', '699', '3', null, null, null, null, '', '1', '2015-01-31 16:23:13', '2015-01-31 16:23:13');
COMMIT;

-- ----------------------------
--  Table structure for `t_product_block_img`
-- ----------------------------
DROP TABLE IF EXISTS `t_product_block_img`;
CREATE TABLE `t_product_block_img` (
  `name` varchar(32) NOT NULL,
  `size` bigint(20) DEFAULT NULL,
  `extension` varchar(10) DEFAULT NULL,
  `product_id` varchar(32) DEFAULT NULL,
  `position` varchar(1) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `insert_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`name`),
  KEY `idx_pi_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_product_block_img`
-- ----------------------------
BEGIN;
INSERT INTO `t_product_block_img` VALUES ('0ce138ece8974dbeb5de0da234c9f710', '209422', 'png', 'db96674b7be244d6ad57a8f136d8a51b', 'f', null, '2015-02-07 12:41:24', '2015-02-07 12:41:24'), ('0d6e58b937f64b64b900318d361a8374', '266681', 'png', 'a47b40d880c34f778b60c6b6f6f49c6a', 'b', null, '2015-02-07 12:41:04', '2015-02-07 12:41:04'), ('220d201531eb43ae8ac88386ce445d47', '266681', 'png', 'a47b40d880c34f778b60c6b6f6f49c6a', 'c', null, '2015-02-07 12:41:14', '2015-02-07 12:41:14'), ('22ea75481a444981b8dab01fa50d1c21', '260552', 'png', 'd930f5bf7d484f4698de5b3a1c5c73b1', 'd', null, '2015-02-07 12:41:18', '2015-02-07 12:41:18'), ('44f8ef8069154ddb87b742300a797b21', '217943', 'png', '2bec035fc64440a095cffb7d1693296c', 'e', null, '2015-02-07 12:41:21', '2015-02-07 12:41:21'), ('6233390c0f4d4a8890f8981fccc85a34', '876926', 'png', '7f8ebbf3d40e4134a15fbb524a597042', 'a', null, '2015-02-07 12:40:20', '2015-02-07 12:40:20');
COMMIT;

-- ----------------------------
--  Table structure for `t_product_category`
-- ----------------------------
DROP TABLE IF EXISTS `t_product_category`;
CREATE TABLE `t_product_category` (
  `category_id` int(10) NOT NULL,
  `category_name` varchar(50) NOT NULL,
  `subcategory_id` int(10) NOT NULL,
  `subcategory_name` varchar(50) NOT NULL,
  `status` int(1) NOT NULL,
  `insert_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_product_category`
-- ----------------------------
BEGIN;
INSERT INTO `t_product_category` VALUES ('100', '服装服饰', '100100', '品牌女装', '1', '2015-02-05 18:28:12', '2015-01-22 16:42:52'), ('100', '服装服饰', '100150', '品牌男装', '1', '2015-02-05 18:28:15', '2015-01-22 16:42:52'), ('150', '日化用品', '150100', '美妆用品', '1', '2015-02-06 20:18:39', '2015-01-22 16:42:52'), ('150', '日化用品', '150150', '基础护理', '1', '2015-02-06 20:18:41', '2015-01-22 16:42:52'), ('200', '鞋类箱包', '200100', '时尚女鞋', '1', '2015-02-06 20:19:34', '2015-01-22 16:42:52'), ('200', '鞋类箱包', '200150', '品牌男鞋', '2', '2015-02-06 20:19:36', '2015-01-22 16:42:52'), ('250', '居家生活', '250100', '工艺饰品', '1', '2015-02-06 20:43:24', '2015-01-22 16:42:52'), ('250', '居家生活', '250150', '家居用品', '1', '2015-02-06 20:43:29', '2015-01-22 16:42:52'), ('300', '婴幼专区', '300100', '儿童用品', '1', '2015-02-06 20:43:33', '2015-01-22 16:42:52'), ('300', '婴幼专区', '300150', '洗护用品', '1', '2015-02-06 20:20:08', '2015-01-22 16:42:52'), ('350', '电器专区', '350100', '家用电器', '1', '2015-02-06 20:20:13', '2015-01-22 16:42:52'), ('350', '电器专区', '350150', '数码通讯', '1', '2015-02-06 20:20:15', '2015-01-22 16:42:52'), ('400', '健康生活', '400100', '生态食品', '1', '2015-02-06 20:20:24', '2015-01-22 16:42:52'), ('400', '健康生活', '400150', '养生保健', '1', '2015-02-06 20:20:29', '2015-01-22 16:42:52'), ('450', '休闲旅游', '450100', '景区门票', '1', '2015-02-06 20:20:34', '2015-01-22 16:42:52'), ('450', '休闲旅游', '450150', '自驾旅游', '1', '2015-02-06 20:20:38', '2015-01-22 16:42:52'), ('500', '每周一品', '500100', '特价产品', '1', '2015-02-06 20:20:41', '2015-01-22 16:42:52'), ('500', '每周一品', '500150', '热门兑换', '1', '2015-02-06 20:20:42', '2015-01-22 16:42:52');
COMMIT;

-- ----------------------------
--  Table structure for `t_product_flash_sale_img`
-- ----------------------------
DROP TABLE IF EXISTS `t_product_flash_sale_img`;
CREATE TABLE `t_product_flash_sale_img` (
  `name` varchar(32) NOT NULL,
  `size` bigint(20) DEFAULT NULL,
  `extension` varchar(10) DEFAULT NULL,
  `product_id` varchar(32) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `insert_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`name`),
  KEY `idx_pi_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_product_flash_sale_img`
-- ----------------------------
BEGIN;
INSERT INTO `t_product_flash_sale_img` VALUES ('9917f54b99354f44bb28f4a14cd113e8', '876926', 'png', '2cb0dba0588f42769977c4474c0f449a', null, '2015-02-07 11:56:30', '2015-02-07 11:56:30'), ('f4ff29ff53d0481fb15d712a5ad7a54c', '224892', 'png', '0a8906d6087e4e8cb3decfea2eeeba34', null, '2015-02-07 12:11:41', '2015-02-07 12:11:41');
COMMIT;

-- ----------------------------
--  Table structure for `t_product_img`
-- ----------------------------
DROP TABLE IF EXISTS `t_product_img`;
CREATE TABLE `t_product_img` (
  `name` varchar(32) NOT NULL,
  `size` bigint(20) DEFAULT NULL,
  `extension` varchar(10) DEFAULT NULL,
  `product_id` varchar(32) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `insert_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`name`),
  KEY `idx_pi_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_product_img`
-- ----------------------------
BEGIN;
INSERT INTO `t_product_img` VALUES ('0511be4f721e4bc3b0b8e251438932f3', '47024', 'jpg', 'cf876a95d021415db639674891cf8e4d', null, '2015-02-07 12:10:05', '2015-02-07 12:10:05'), ('0d518a71882642279f6d8701ff260601', '90489', 'png', 'd930f5bf7d484f4698de5b3a1c5c73b1', null, '2015-02-07 12:06:37', '2015-02-07 12:06:37'), ('12fca70b458f4159a269dd76d7517954', '100225', 'png', '83e280f5807a47f38e0dcf4b8a674a44', null, '2015-02-07 12:09:45', '2015-02-07 12:09:45'), ('1b6608a0dcc94dfcb555acd5582b6f74', '45112', 'jpg', 'a5b468a2ee0f4536830777d27c5ea93d', null, '2015-02-07 12:09:54', '2015-02-07 12:09:54'), ('1e77e1b3fe9c49f0a31785409d1a1489', '45112', 'jpg', 'd930f5bf7d484f4698de5b3a1c5c73b1', null, '2015-02-07 12:06:36', '2015-02-07 12:06:36'), ('1e8a3fc852ed4b218bce001f1e4965ff', '45112', 'jpg', '2a073ffec5ae4d3aa808cc653256299a', null, '2015-02-07 12:42:21', '2015-02-07 12:42:21'), ('1f11595f341141978d062fbe010e972b', '45112', 'jpg', '9024718520144f74b616725b6314ecb0', null, '2015-02-07 12:14:15', '2015-02-07 12:14:15'), ('262b5b1cd5b1414f8742602395ed793e', '100225', 'png', '2a073ffec5ae4d3aa808cc653256299a', null, '2015-02-07 12:42:21', '2015-02-07 12:42:21'), ('29ac04b71c0145ccbaed2e7e7ff7031e', '100225', 'png', 'a47b40d880c34f778b60c6b6f6f49c6a', null, '2015-02-07 12:09:49', '2015-02-07 12:09:49'), ('2e416bb138584872b5660397cb8551be', '90489', 'png', 'f13dcf6af2ec45338f3e3bfb82b1d094', null, '2015-02-07 12:10:24', '2015-02-07 12:10:24'), ('324334c7e6e74bbf84b8689b2e9ab66e', '100225', 'png', '9024718520144f74b616725b6314ecb0', null, '2015-02-07 12:14:15', '2015-02-07 12:14:15'), ('35d0485d7480426f80afb165bcbfb0e1', '100225', 'png', '2054ff4ae831492fad90ea290600c806', null, '2015-02-07 12:08:57', '2015-02-07 12:08:57'), ('3623bb753cda4a868fa9bd1493849b5a', '47024', 'jpg', 'd930f5bf7d484f4698de5b3a1c5c73b1', null, '2015-02-07 12:06:36', '2015-02-07 12:06:36'), ('371eabfc91784c34be432ae6a35cdd75', '45112', 'jpg', '72ee686d00e44ae7928840466eeaa956', null, '2015-02-07 12:09:32', '2015-02-07 12:09:32'), ('4011bc2992094574a5a3fd70d9d263a2', '100225', 'png', '7f8ebbf3d40e4134a15fbb524a597042', null, '2015-02-07 12:09:41', '2015-02-07 12:09:41'), ('4353083739dc45b288d90f9fdcc61f24', '45112', 'jpg', '2cb0dba0588f42769977c4474c0f449a', null, '2015-02-07 11:55:59', '2015-02-07 11:55:59'), ('468d1317e2704f888970ee357e281e44', '47024', 'jpg', '0a8906d6087e4e8cb3decfea2eeeba34', null, '2015-02-07 12:08:45', '2015-02-07 12:08:45'), ('48f78b98a374461ca7cd500cda016539', '90489', 'png', '2054ff4ae831492fad90ea290600c806', null, '2015-02-07 12:08:57', '2015-02-07 12:08:57'), ('528c4a7975dc42a1b7ac13aade9dfed5', '47024', 'jpg', '487f156feafa406ea2c19491bfb984b3', null, '2015-02-07 12:09:15', '2015-02-07 12:09:15'), ('620a8e47b87d425783d3184fee217b8e', '45112', 'jpg', '21b1eeb892a1445bb2d1df135a3d25bb', null, '2015-02-07 12:09:01', '2015-02-07 12:09:01'), ('6a0e1625abac4e4f80bcf75a291ac086', '85708', 'png', 'e3f07becffcc4e15b1a4e8a42678ab1e', null, '2015-02-07 12:10:15', '2015-02-07 12:10:15'), ('763ebf2da6494fcf9291426d75ed0616', '100225', 'png', '1411f65b30c94e74b4428f93772eb6a5', null, '2015-02-07 12:08:51', '2015-02-07 12:08:51'), ('794fe8adf36a4d7f819f1be09fb5dfde', '45112', 'jpg', '2bec035fc64440a095cffb7d1693296c', null, '2015-02-07 12:11:23', '2015-02-07 12:11:23'), ('7d31992faa754dc08b7d7b647f2a7aca', '90489', 'png', '83e280f5807a47f38e0dcf4b8a674a44', null, '2015-02-07 12:09:45', '2015-02-07 12:09:45'), ('81e8ae88504f4fc9860800814c43b0f2', '100225', 'png', 'd930f5bf7d484f4698de5b3a1c5c73b1', null, '2015-02-07 12:06:36', '2015-02-07 12:06:36'), ('8343219e11d5418292836c018dc38d97', '90489', 'png', '5a0a37c1badd4bb5b3973f0af97b0318', null, '2015-02-07 12:09:24', '2015-02-07 12:09:24'), ('83b14b501df84c97b99c5f35cab50ae7', '85708', 'png', 'a92806f7d73a4ce2a0a3417c601fb58f', null, '2015-02-07 12:10:01', '2015-02-07 12:10:01'), ('84941ac76e8441e091ff76d9f856384d', '45112', 'jpg', 'a92806f7d73a4ce2a0a3417c601fb58f', null, '2015-02-07 12:10:01', '2015-02-07 12:10:01'), ('850dda822db0496c95a845f61823fd68', '85708', 'png', '2a073ffec5ae4d3aa808cc653256299a', null, '2015-02-07 12:42:21', '2015-02-07 12:42:21'), ('87c0fe91dcb044fb8497ff3494d9778f', '90489', 'png', 'a47b40d880c34f778b60c6b6f6f49c6a', null, '2015-02-07 12:09:49', '2015-02-07 12:09:49'), ('8a12962de7ef4ccf9eaf2fccf0717433', '90489', 'png', 'cf876a95d021415db639674891cf8e4d', null, '2015-02-07 12:10:05', '2015-02-07 12:10:05'), ('8ac11048a269416b9145365a2452fd02', '45112', 'jpg', '6a428bab13af41458dc1ad38a211c592', null, '2015-02-07 12:09:28', '2015-02-07 12:09:28'), ('8d2ee5a9501847778fc5b9adfa8eddf5', '85708', 'png', '21b1eeb892a1445bb2d1df135a3d25bb', null, '2015-02-07 12:09:01', '2015-02-07 12:09:01'), ('9a8c7e40f80b40279e4bea513722aff7', '90489', 'png', '487f156feafa406ea2c19491bfb984b3', null, '2015-02-07 12:09:15', '2015-02-07 12:09:15'), ('9c3edfebb83b48c181ffc2cc0f0ab64d', '85708', 'png', '1411f65b30c94e74b4428f93772eb6a5', null, '2015-02-07 12:08:51', '2015-02-07 12:08:51'), ('9e27eead6d6d49f78e3dff011b109735', '85708', 'png', '2943758d3cf8428a8ebcb12923bc1060', null, '2015-02-07 12:09:05', '2015-02-07 12:09:05'), ('9fd797aee1ff4d7c92160187971da35a', '100225', 'png', '5a0a37c1badd4bb5b3973f0af97b0318', null, '2015-02-07 12:09:24', '2015-02-07 12:09:24'), ('a405164f2edb4f8fbfbc6153ad863edc', '100225', 'png', 'e621dee0a8aa44b39e69ce405482a3e0', null, '2015-02-07 12:10:18', '2015-02-07 12:10:18'), ('ad37f92a724d4d118bf19a0af2703a08', '100225', 'png', '73622b956917478e9bc6d10ae03d5629', null, '2015-02-07 12:09:37', '2015-02-07 12:09:37'), ('b08382b2cb854f459aee0057ec1dbe5b', '85708', 'png', '51bb5658a7df442980c10701e71db9a6', null, '2015-02-07 12:09:20', '2015-02-07 12:09:20'), ('b28588fe9252422c86840786adfaf5bb', '85708', 'png', '2054ff4ae831492fad90ea290600c806', null, '2015-02-07 12:08:57', '2015-02-07 12:08:57'), ('b299b04e2ef84c10b066c8f531dc298d', '47024', 'jpg', '7e7b7ba555224543ade94dffbfb42fb2', null, '2015-02-07 12:10:27', '2015-02-07 12:10:27'), ('b8445c7609d24e0fb58cdcb3132f1afb', '85708', 'png', '2cb0dba0588f42769977c4474c0f449a', null, '2015-02-07 11:56:12', '2015-02-07 11:56:12'), ('baad6c59316b452bac76bf4239488102', '85708', 'png', 'db96674b7be244d6ad57a8f136d8a51b', null, '2015-02-07 12:10:12', '2015-02-07 12:10:12'), ('bb07fa0bacd5408799863833ffbd55b8', '45112', 'jpg', '0a8906d6087e4e8cb3decfea2eeeba34', null, '2015-02-07 12:08:45', '2015-02-07 12:08:45'), ('bf09c31c849049fb8f96e4cb90ff1f60', '47024', 'jpg', '9024718520144f74b616725b6314ecb0', null, '2015-02-07 12:14:15', '2015-02-07 12:14:15'), ('bf687ce803d844099dcc4a86154d7f9f', '47024', 'jpg', '72ee686d00e44ae7928840466eeaa956', null, '2015-02-07 12:09:32', '2015-02-07 12:09:32'), ('c13920dbad7c4648bccf48387464b98c', '90489', 'png', '51bb5658a7df442980c10701e71db9a6', null, '2015-02-07 12:09:20', '2015-02-07 12:09:20'), ('c6872957d978465c9ba42fa640ff1882', '100225', 'png', '6a428bab13af41458dc1ad38a211c592', null, '2015-02-07 12:09:28', '2015-02-07 12:09:28'), ('ca15c9802257443cac60673719ea52dc', '85708', 'png', 'a5b468a2ee0f4536830777d27c5ea93d', null, '2015-02-07 12:09:54', '2015-02-07 12:09:54'), ('ca8578d39a884af9aea62382e85ffae1', '47024', 'jpg', '2943758d3cf8428a8ebcb12923bc1060', null, '2015-02-07 12:09:05', '2015-02-07 12:09:05'), ('d40971bf8b674beb8b737bbbe9fdadce', '47024', 'jpg', '7f8ebbf3d40e4134a15fbb524a597042', null, '2015-02-07 12:09:41', '2015-02-07 12:09:41'), ('e2b171b1aac84ade88b0e9260b1e8698', '47024', 'jpg', '2cb0dba0588f42769977c4474c0f449a', null, '2015-02-07 11:55:59', '2015-02-07 11:55:59'), ('e32d356887534d52af8c7df8154e87ba', '85708', 'png', 'd930f5bf7d484f4698de5b3a1c5c73b1', null, '2015-02-07 12:06:36', '2015-02-07 12:06:36'), ('e715e5fb44704a6595350ca141128616', '45112', 'jpg', '73622b956917478e9bc6d10ae03d5629', null, '2015-02-07 12:09:37', '2015-02-07 12:09:37'), ('ef5fb3b510574c58a2499513a34722ce', '85708', 'png', '9024718520144f74b616725b6314ecb0', null, '2015-02-07 12:14:15', '2015-02-07 12:14:15'), ('f93bee41e8654ef4ab2ab34b08f299ac', '45112', 'jpg', 'd335a843726b411ebd80fd034fae5ba0', null, '2015-02-07 12:10:08', '2015-02-07 12:10:08'), ('fed106387767449e9a61b34482509c3a', '100225', 'png', 'efd2117017364bbfbb5f19affd325c44', null, '2015-02-07 12:10:21', '2015-02-07 12:10:21');
COMMIT;

-- ----------------------------
--  Table structure for `t_product_recommend_img`
-- ----------------------------
DROP TABLE IF EXISTS `t_product_recommend_img`;
CREATE TABLE `t_product_recommend_img` (
  `name` varchar(32) NOT NULL,
  `size` bigint(20) DEFAULT NULL,
  `extension` varchar(10) DEFAULT NULL,
  `product_id` varchar(32) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `insert_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`name`),
  KEY `idx_pi_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_product_recommend_img`
-- ----------------------------
BEGIN;
INSERT INTO `t_product_recommend_img` VALUES ('14c48fd7f2e34a4ab3356e81fbcc530a', '664697', 'png', '2a073ffec5ae4d3aa808cc653256299a', null, '2015-02-07 13:20:28', '2015-02-07 13:20:28'), ('52be48e4f1d047bc84cb81c06697c8a1', '664697', 'png', '2cb0dba0588f42769977c4474c0f449a', null, '2015-02-07 11:56:26', '2015-02-07 11:56:26'), ('98bbad3bfcec48adb41453fae2e71917', '861986', 'png', '1411f65b30c94e74b4428f93772eb6a5', null, '2015-02-10 18:43:47', '2015-02-10 18:43:47'), ('af2ae95a4da14d7badb848677fa4fb68', '861986', 'png', 'd930f5bf7d484f4698de5b3a1c5c73b1', null, '2015-02-07 12:06:52', '2015-02-07 12:06:52'), ('b14df2a673be44c18d5d58d621ce0427', '664697', 'png', '0a8906d6087e4e8cb3decfea2eeeba34', null, '2015-02-07 12:10:46', '2015-02-07 12:10:46'), ('e82b48ea4b504002a0bfa632d948b89c', '861986', 'png', '2a073ffec5ae4d3aa808cc653256299a', null, '2015-02-07 13:20:37', '2015-02-07 13:20:37');
COMMIT;

-- ----------------------------
--  Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `ROLE_ID` int(10) NOT NULL,
  `ROLE_NAME` varchar(100) NOT NULL,
  PRIMARY KEY (`ROLE_ID`),
  UNIQUE KEY `uni_role_name` (`ROLE_NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_role`
-- ----------------------------
BEGIN;
INSERT INTO `t_role` VALUES ('1', '系统管理员'), ('0', '超级管理员');
COMMIT;

-- ----------------------------
--  Table structure for `t_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu` (
  `ROLE_ID` int(10) NOT NULL,
  `MENU_ID` int(10) NOT NULL,
  KEY `idx_rm_role_id` (`ROLE_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_role_menu`
-- ----------------------------
BEGIN;
INSERT INTO `t_role_menu` VALUES ('1', '10'), ('1', '20'), ('1', '2001'), ('1', '2002'), ('1', '2003'), ('1', '2004'), ('1', '2005'), ('1', '200101'), ('1', '200102'), ('1', '1'), ('1', '1001'), ('1', '2006'), ('1', '200301'), ('1', '200302'), ('1', '200303'), ('1', '200103');
COMMIT;

-- ----------------------------
--  Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` varchar(32) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL,
  `real_name` varchar(50) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `description` longtext,
  `status` int(1) DEFAULT NULL,
  `insert_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `uni_username` (`username`) USING BTREE,
  KEY `idx_user_mobile` (`mobile`),
  KEY `idx_user_real_name` (`real_name`),
  KEY `idx_user_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_user`
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES ('11', 'root2', 'a66abb5684c45962d887564f08346e8d', '超级管理员', '13761622012', 'sam.tian@163.com', null, null, '2015-01-21 20:14:28', '2015-01-21 20:14:28'), ('2a5d3de3893e454da61445a4e95c6259', 'admin', 'a66abb5684c45962d887564f08346e8d', '管理员', '13761620049', 'nickevin@gmail.com', '<blockquote>\r\n<p><span style=\"color:rgb(85, 85, 85); font-family:trebuchet ms,helvetica,sans-serif; font-size:16px\">I am a web developer and </span><span style=\"font-size:36px\"><span style=\"color:rgb(255, 165, 0)\"><span style=\"font-family:trebuchet ms,helvetica,sans-serif\">desginer </span></span></span><span style=\"color:rgb(85, 85, 85); font-family:trebuchet ms,helvetica,sans-serif; font-size:16px\">based in Medell&iacute;n - Colombia, I like read books, good music and nature.I am a web developer and desginer </span><span style=\"color:#FF8C00\"><span style=\"font-family:trebuchet ms,helvetica,sans-serif; font-size:16px\">based </span></span><span style=\"color:rgb(85, 85, 85); font-family:trebuchet ms,helvetica,sans-serif; font-size:16px\">in Medell&iacute;n - Colombia, I like read books, good music and nature.I am a web </span><span style=\"color:#EE82EE\"><span style=\"font-family:trebuchet ms,helvetica,sans-serif; font-size:16px\">developer </span></span><span style=\"color:rgb(85, 85, 85); font-family:trebuchet ms,helvetica,sans-serif; font-size:16px\">and desginer based in Medell&iacute;n - Colombia, I like read books, good music and nature.</span></p>\r\n</blockquote>\r\n', '1', '2015-02-09 19:06:04', '2015-02-09 19:06:04'), ('aeffe47a3d7d4001abcdf184c281bd4a', 'sam.tian', 'fd28e770000849b91d5e2b2dc7d363bc', 'sam.tian', '13761622012', 'sam.tian@163.com', '', '1', '2015-12-12 10:30:37', '2015-12-12 10:30:37');
COMMIT;

-- ----------------------------
--  Table structure for `t_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `USER_ID` varchar(32) NOT NULL,
  `ROLE_ID` int(10) NOT NULL,
  KEY `FK_UR_ROLE_ID` (`ROLE_ID`),
  KEY `FK_UR_USER_ID` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_user_role`
-- ----------------------------
BEGIN;
INSERT INTO `t_user_role` VALUES ('bb4fc6416ca14317adff61981bc9c414', '0'), ('2a5d3de3893e454da61445a4e95c6259', '1'), ('aeffe47a3d7d4001abcdf184c281bd4a', '1'), ('f57745d628fa42bf97db93a09c6eb4a2', '1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
