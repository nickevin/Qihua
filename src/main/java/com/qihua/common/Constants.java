package com.qihua.common;

import com.qihua.util.FileUtils;

/**
 * Class description goes here.
 *
 * @author zhen.ni@ebaotech.com
 * @since 2013-10-11
 * @version 1.0
 * @see
 */
public class Constants {

  // 用户信息（后台）
  public static final String SESSION_USER = "SESSION_USER";
  // 用户信息（前台）
  public static final String SESSION_MEMBER = "SESSION_MEMBER";
  // 用户权限 ID
  public static final String SESSION_USER_ROLE = "SESSION_USER_ROLE";
  // 用户有权访问的菜单
  public static final String SESSION_MENU = "SESSION_MENU";
  // Session 超时后，访问的最后一次 URI
  public static final String SESSION_TIMEOUT_URI = "SESSION_TIMEOUT_URI";
  // Session 超时请求的参数
  public static final String SESSION_TIMEOUT_REQUEST_PARAMETERS = "SESSION_TIMEOUT_REQUEST_PARAMETERS";
  // Session 超时请求的 form method
  public static final String SESSION_TIMEOUT_REQUEST_METHOD = "SESSION_TIMEOUT_REQUEST_METHOD";

  public static final String SESSION_MEMBER_TIMEOUT_URI = "SESSION_MEMBER_TIMEOUT_URI";
  public static final String SESSION_MEMBER_TIMEOUT_REQUEST_METHOD = "SESSION_MEMBER_TIMEOUT_REQUEST_METHOD";
  public static final String SESSION_MEMBER_TIMEOUT_REQUEST_PARAMETERS = "SESSION_MEMBER_TIMEOUT_REQUEST_PARAMETERS";

  public static final String COMPANY_NAME = "柒花网"; // 公司名称
  public static final String COPYRIGHT_INFO = "&copy; 2015 版权所有"; // 版权信息
  public static final String WEBSITE_NAME = "柒花网"; // 网站名称
  public static final String WEBSITE_URL = "www.qihuazg.com"; // 网站URL
  public static final String SYSTEM_NAME = "后台管理系统"; // 系统名称
  public static final String DEFAULT_MAIL = "qihuacloud@163.com"; // 默认邮箱
  public static final String DEFAULT_PASSWORD = "password"; // 默认密码

  public static final int ROLE_ADMIN = 1; // 角色：管理员
  public static final int ROLE_APPROVER = 2; // 角色：审批
  public static final int ROLE_NORMAL = 3; // 角色：普通

  public static final int ARTICLE_TYPE_CLUB = 1;
  public static final int ARTICLE_TYPE_PARTNER = 2;
  public static final int ARTICLE_TYPE_MERCHANT = 3;
  public static final String ARTICLE_TYPE_CLUB_NAME = "俱乐部";
  public static final String ARTICLE_TYPE_PARTNER_NAME = "伙伴入驻";
  public static final String ARTICLE_TYPE_MERCHANT_NAME = "联盟商家";

  // 状态
  public static final int DISABLE = 1;
  public static final int ENABLE = 2;

  // 充值卡状态
  public static final int CARD_TYPE_ENABLE = 1;
  public static final int CARD_TYPE_DISABLE = 2;
  public static final String CARD_TYPE_ENABLE_NAME = "待充值";
  public static final String CARD_TYPE_DISABLE_NAME = "已充值";

  // 订单状态
  public static final int ORDER_TYPE_PENDING = 1;
  public static final int ORDER_TYPE_SHIPPED = 2;
  public static final int ORDER_TYPE_FINISHED = 3;
  public static final String ORDER_TYPE_PENDING_NAME = "已下单";
  public static final String ORDER_TYPE_SHIPPED_NAME = "已发货";
  public static final String ORDER_TYPE_FINISHED_NAME = "交易完成";

  public static final int PAGE_SIZE = 50; // 分页

  public static final String CONTENT_TYPE_EXCEL = "application/vnd.ms-excel";

  public static final String APPLICATION_PROPERTIES = "application.properties"; // 配置文件名

  public static final String DEPLOY_URL = FileUtils.readProperties("deploy.url");
  public static final String CARD_FILE_UPLOAD_DIR = FileUtils.readProperties("card.file.upload.dir");
  public static final String PRODUCT_IMG_UPLOAD_DIR = FileUtils.readProperties("product.img.upload.dir");

  public static final String CONTEXT_URL = FileUtils.readProperties("context.url");
  public static final String PRODUCT_IMG_URL = FileUtils.readProperties("product.img.url");
  public static final String HOME_IMG_URL = FileUtils.readProperties("home.img.url");

  public static final String BAIDU_LBS_AK = FileUtils.readProperties("baidu.lbs.ak");

  public static final String HOME_IMG_POSISION_CENTER = "1";
  public static final String HOME_IMG_POSISION_BOTTOM = "2";

  public static final String PRODUCT_IMG_BLOCK_POSISION_A = "a";
  public static final String PRODUCT_IMG_BLOCK_POSISION_B = "b";
  public static final String PRODUCT_IMG_BLOCK_POSISION_C = "c";
  public static final String PRODUCT_IMG_BLOCK_POSISION_D = "d";
  public static final String PRODUCT_IMG_BLOCK_POSISION_E = "e";
  public static final String PRODUCT_IMG_BLOCK_POSISION_F = "f";

}
