package com.jsmy.chongyin.contents;

import android.os.Environment;

import com.jsmy.chongyin.utils.SharePrefUtil;

/**
 * Created by Administrator on 2017/3/28.
 */

public class ServiceCode {
    //请求类型 get  post
    public static final int NETWORK_GET = 1;
    public static final int NETWORK_POST = 2;
    public static final int NETWORK_UPFILE = 3;
    public static final int NETWORK_DOAWLOADFILE = 4;
    //文件保存路径
    public static final String BASE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/.chongyin/pet/";
    public static final String BASE_IMG_CROP = "cropImg.png";
    public static final String BASE_IMG_TX = "txImg.png";
    //上传文件的类型
    public static final String VIDEO_TYPE = "video/*";
    public static final String IMAGE_TYPE = "image/*";
    //背景音乐
    public static final String MUSIC_PLAY = "play";
    public static final String MUSIC_STOP = "stop";

    // 接口服务器请求地址
    //内网
//    public static final String SERVICE_URL = "http://192.168.3.170:8080/chongyin/";
    //外网
//    public static final String SERVICE_URL = "http://47.92.153.135/chongyin/";
    //外网测试
   public static final String SERVICE_URL = "http://47.92.169.240/chongyin/";

    /**
     * 1.01 注册
     */
    public static final String GET_REGISTER = "sys/getregister.do";
    public static final String GET_REGISTER_NUM = "1.01";

    /**
     * 1.02 登录
     */
    public static final String GET_LOGIN = "sys/getlogin.do";
    public static final String GET_LOGIN_NUM = "1.02";
    /**
     * 1.021 APP版本号
     */
    public static final String GET_APP = "sys/getapp.do";
    public static final String GET_APP_NUM = "1.021";
    /**
     * 1.022 登录消息
     */
    public static final String GET_LOG_SXX = "sys/getlogsxx.do";
    public static final String GET_LOG_SXX_NUM = "1.022";
    /**
     * 1.023 推送附近的人
     */
    public static final String GET_FJDR = "sys/getfjdr.do";
    public static final String GET_FJDR_NUM = "1.023";
    /**
     * 1.03 首页 基础数据
     */
    public static final String GET_SY_JCSJ = "sys/getsyjcsj.do";
    public static final String GET_SY_JCSJ_NUM = "1.03";
    /**
     * 1.04 首页 随机收获物品列表
     */
    public static final String GET_SJWP_list = "sys/getsjwplist.do";
    public static final String GET_SJWP_list_NUM = "1.04";
    /**
     * 1.041 首页 收集随机物品
     */
    public static final String UP_DATE_SJWP = "sys/updatesjwp.do";
    public static final String UP_DATE_SJWP_NUM = "1.041";
    /**
     * 1.05 首页 更换宠物昵称
     */
    public static final String UP_DATE_PET_NC = "sys/updatepetnc.do";
    public static final String UP_DATE_PET_NC_NUM = "1.05";
    /**
     * 1.06 首页 购买食物
     */
    public static final String UP_DATE_SP_SHOP = "sys/updatespshop.do";
    public static final String UP_DATE_SP_SHOP_NUM = "1.06";
    /**
     * 1.07 首页 背景图列表
     */
    public static final String GET_BJTP_LIST = "sys/getbjtplist.do";
    public static final String GET_BJTP_LIST_NUM = "1.07";
    /**
     * 1.08 首页 排行榜列表
     */
    public static final String GET_PHB_LIST = "sys/getphblist.do";
    public static final String GET_PHB_LIST_NUM = "1.08";
    /**
     * 1.09 首页 我的好友列表
     */
    public static final String GET_FRIEND_LIST = "sys/getfriendlist.do";
    public static final String GET_FRIEND_LIST_NUM = "1.09";
    /**
     * 1.091 好友首页
     */
    public static final String GET_FRIEND_SY = "sys/getfriendsy.do";
    public static final String GET_FRIEND_SY_NUM = "1.091";
    /**
     * 1.092 好友首页随机爱心
     */
    public static final String GET_FRIEND_SYAX = "sys/getfriendsyax.do";
    public static final String GET_FRIEND_SYAX_NUM = "1.092";
    /**
     * 1.093 好友的基本资料
     */
    public static final String GET_FRIEND_ZL = "sys/getfriendzl.do";
    public static final String GET_FRIEND_ZL_NUM = "1.093";
    /**
     * 1.094 删除好友
     */
    public static final String UP_DATE_FRIEND_DEL = "sys/updatefrienddel.do";
    public static final String UP_DATE_FRIEND_DEL_NUM = "1.094";
    /**
     * 1.095 帮好友收取爱心
     */
    public static final String UP_DATE_HYAX = "sys/updatehyax.do";
    public static final String UP_DATE_HYAX_NUM = "1.095";
    /**
     * 1.096 偷取好友爱心
     */
    public static final String UP_DATE_HYSJAX = "sys/updatehysjax.do";
    public static final String UP_DATE_HYSJAX_NUM = "1.096";
    /**
     * 1.097 收取宠物肚子里爱心
     */
    public static final String UP_DATE_YHPET_AX = "sys/updateyhpetax.do";
    public static final String UP_DATE_YHPET_AX_NUM = "1.097";
    /**
     * 1.098 送爱心
     */
    public static final String UP_DATE_AXS = "sys/updateaxs.do";
    public static final String UP_DATE_AXS_NUM = "1.098";
    /**
     * 1.10 抽奖列表
     */
    public static final String GET_CJ_LIST = "sys/getcjlist.do";
    public static final String GET_CJ_LIST_NUM = "1.10";
    /**
     * 1.11 抽奖
     */
    public static final String UP_DATE_CJ = "sys/updatecj.do";
    public static final String UP_DATE_CJ_NUM = "1.11";
    /**
     * 1.12 回调支付
     */
    public static final String UP_ADTE_PAY = "sys/upadtepay.do";
    public static final String UP_ADTE_PAY_NUM = "1.12";

    /**
     * 1.13 微信支付
     */
    public static final String WX_PAY = "sys/pay.do";
    public static final String WX_PAY_NUM = "1.13";

    /**
     * 1.14 支付宝支付
     */
    public static final String ALI_PAY = "sys/alipay.do";
    public static final String ALI_PAY_NUM = "1.14";

    /**
     * 2.01 食品商店列表
     */
    public static final String GET_FOOD_SHOP_LIST = "homepage/getfoodshoplist.do";
    public static final String GET_FOOD_SHOP_LIST_NUM = "2.01";
    /**
     * 2.02 宠物商店列表
     */
    public static final String GET_PET_SHOP_LIST = "homepage/getpetshoplist.do";
    public static final String GET_PET_SHOP_LIST_NUM = "2.02";
    /**
     * 2.04 元宝商店
     */
    public static final String GET_YB_SHOP_LIST = "homepage/getybshop.do";
    public static final String GET_YB_SHOP_LIST_NUM = "2.04";
    /**
     * 2.05 食品分类列表
     */
    public static final String GET_SPFL_LIST = "homepage/getspfllist.do";
    public static final String GET_SPFL_LIST_NUM = "2.05";
    /**
     * 2.06 下载宠物图片
     */
    public static final String GET_XZ_LIST = "homepage/getxzlist.do";
    public static final String GET_XZ_LIST_NUM = "2.06";
    /**
     * 2.061 下载好友宠物图片
     */
    public static final String GET_HYPET_TP_LIST = "homepage/gethypettplist.do";
    public static final String GET_HYPET_TP_LIST_NUM = "2.061";
    /**
     * 2.062 是否下在宠物图片
     */
    public static final String UP_DATE_IS_PETTP = "homepage/updateispettp.do";
    public static final String UP_DATE_IS_PETTP_NUM = "2.062";
    /**
     * 2.063 宠物图片列
     */
    public static final String GET_PET_TP = "homepage/getpettp.do";
    public static final String GET_PET_TP_NUM = "2.063";
    /**
     * 2.07 启动页
     */
    public static final String GET_QDY = "homepage/getqdy.do";
    public static final String GET_QDY_NUM = "2.07";
    /**
     * 2.08 桌面宠物
     */
    public static final String UP_DATE_PET_ZM = "homepage/updatepetzm.do";
    public static final String UP_DATE_PET_ZM_NUM = "2.08";
    /**
     * 6.02 我的 食物
     */
    public static final String GET_FOOD_LIST = "wo/getfoodlist.do";
    public static final String GET_FOOD_LIST_NUM = "6.02";
    /**
     * 6.03 我的 个人信息 列表
     */
    public static final String GET_WO_MATERIAL_LIST = "wo/getwomaterial.do";
    public static final String GET_WO_MATERIAL_LIST_NUM = "6.03";
    /**
     * 6.031 我的 修改头像
     */
    public static final String UP_DATE_TX = "wo/updatetx.do";
    public static final String UP_DATE_TX_NUM = "6.031";
    /**
     * 6.032 我的 修改昵称
     */
    public static final String UP_DATE_NC = "wo/updatenc.do";
    public static final String UP_DATE_NC_NUM = "6.032";
    /**
     * 6.033 我的 修改性别
     */
    public static final String UP_DATE_XB = "wo/updatexb.do";
    public static final String UP_DATE_XB_NUM = "6.033";
    /**
     * 6.034 我的 修改签名
     */
    public static final String UP_DATE_QM = "wo/updateqm.do";
    public static final String UP_DATE_QM_NUM = "6.034";
    /**
     * 6.035 我的 修改年龄
     */
    public static final String UP_DATE_AGE = "wo/updateage.do";
    public static final String UP_DATE_AGE_NUM = "6.035";
    /**
     * 6.04 我的 vip特权列表
     */
    public static final String GET_VIP = "wo/getvip.do";
    public static final String GET_VIP_NUM = "6.04";
    /**
     * 6.05 我的 购买vip会员
     */
    public static final String UP_DATE_GM_VIP = "wo/updategmvip.do";
    public static final String UP_DATE_GM_VIP_NUM = "6.05";
    /**
     * 6.06 我的 购买元宝
     */
    public static final String UP_DATE_YB_SHOP = "wo/updateybshop.do";
    public static final String UP_DATE_YB_SHOP_NUM = "6.06";
    /**
     * 6.07 我的 喂养宠物
     */
    public static final String UP_DATE_PET_EAT = "wo/updatepeteat.do";
    public static final String UP_DATE_PET_EAT_NUM = "6.07";
    /**
     * 6.09 我的 设置宠物
     */
    public static final String UP_DATE_PET = "wo/updatepet.do";
    public static final String UP_DATE_PET_NUM = "6.09";
    /**
     * 6.10 我的 新朋友 消息列表
     */
    public static final String GET_NEW_FRIEND_LIST = "wo/getnewfriendlist.do";
    public static final String GET_NEW_FRIEND_LIST_NUM = "6.10";
    /**
     * 6.11 我的 新朋友 申请
     */
    public static final String UP_DATE_FRIEND_SQ = "wo/updatefriendsq.do";
    public static final String UP_DATE_FRIEND_SQ_NUM = "6.11";
    /**
     * 6.12 我的 搜索好友 好友基本资料
     */
    public static final String GET_NEW_FRIEND_SS = "wo/getnewfriendss.do";
    public static final String GET_NEW_FRIEND_SS_NUM = "6.12";
    /**
     * 6.13 我的 新朋友 同意
     */
    public static final String UP_DATE_NEW_FRIEND_SSTJ = "wo/updatenewfriendsstj.do";
    public static final String UP_DATE_NEW_FRIEND_SSTJ_NUM = "6.13";
    /**
     * 6.14 我的 新朋友 拒绝
     */
    public static final String UP_DATE_FRIEND_JJ = "wo/updatefriendjj.do";
    public static final String UP_DATE_FRIEND_JJ_NUM = "6.14";
    /**
     * 6.15 我的 更换背景图
     */
    public static final String UP_DATE_BJTP = "wo/updatebjtp.do";
    public static final String UP_DATE_BJTP_NUM = "6.15";
    /**
     * 6.151 我的 上传背景图
     */
    public static final String UP_DATE_BJTUSC = "wo/updatebjtpsc.do";
    public static final String UP_DATE_BJTUSC_NUM = "6.151";
    /**
     * 6.152 我的 删除背景图
     */
    public static final String UP_DATE_BJTP_DEL = "wo/updatebjtpdel.do";
    public static final String UP_DATE_BJTP_DEL_NUM = "6.152";
    /**
     * 6.16 绑定第三方账号列表
     */
    public static final String GET_ZH_BD = "wo/getzhbd.do";
    public static final String GET_ZH_BD_NUM = "6.16";
    /**
     * 6.161 绑定第三方账号
     */
    public static final String UP_DATE_ZH_BD = "wo/updatezhbd.do";
    public static final String UP_DATE_ZH_BD_NUM = "6.161";
    /**
     * 6.17 我的 购买宠物
     */
    public static final String UP_DATE_PET_SHOP = "wo/updatepetshop.do";
    public static final String UP_DATE_PET_SHOP_NUM = "6.17";
    /**
     * 6.18 我的 清除记录
     */
    public static final String DEL_THYSQJL = "wo/updatedeljl.do";
    public static final String DEL_THYSQJL_NUM = "6.18";
    /**
     * 6.19 我的 位置
     */
    public static final String UP_DATE_WZ = "wo/updatewz.do";
    public static final String UP_DATE_WZ_NUM = "6.19";
    /**
     * 6.20 我的 退出
     */
    public static final String UP_DATE_ESC = "wo/updateEsc.do";
    public static final String UP_DATE_ESC_NUM = "6.20";
    /**
     * 6.21 新朋友申请列表
     */
    public static final String APPLY_LIST= "wo/getapplylist.do";
    public static final String APPLY_LIST_NUM= "6.21";
    /**
     *  1.011 获取验证码
     */
    public static final String REGISTER_YZM= "sys/registeryzm.do";
    public static final String REGISTER_YZM_NUM= "1.011";
    /**
     *   1.012 手机号登录
     */
    public static final String GET_DH_LOGIN= "sys/getdhlogin.do";
    public static final String GET_DH_LOGIN_NUM= "1.012";
    /**
     *   1.013 手机号注册
     */
    public static final String UPDATE_DHZC= "sys/updatedhzc.do";
    public static final String UPDATE_DHZC_NUM= "1.013";
    /**
     *    1.014 设置密码
     */
    public static final String UPDATE_MM= "sys/updatemm.do";
    public static final String UPDATE_MM_NUM= "1.014";
    /**
     *    1.015 忘记密码获取验证码
     */
    public static final String REGISTER_WJMM_YZM= "sys/registerwjmmyzm.do";
    public static final String REGISTER_WJMM_YZM_NUM= "1.015";
    /**
     *    1.016 修改密码任务剩余天数
     */
    public static final String GET_SYTS= "sys/getsyts.do";
    public static final String GET_SYTS_NUM= "1.016";

    /**
     *     1.017 手机号修改密码
     */
    public static final String UPDATE_DHMM= "sys/updatedhmm.do";
    public static final String UPDATE_DHMM_NUM= "1.017";
}
