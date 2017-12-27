package com.dlwx.plana.utiles;

/**
 * Created by Administrator on 2017/9/12/012.
 */

public class HttpUtiles {

    public static final String BaseUrl = "Http://120.27.14.79/A_plan/index.php/User/";
    public static final String Login = BaseUrl + "Login/login";//登录
    public static final String Register = BaseUrl + "Register/add";// 注册
    public static final String Regist_Deal =  "http://120.27.14.79/a_plan/web/userProtocol.html"; //注册协议
    public static final String ForgetPwd = BaseUrl + "Register/find_pwd";//忘记密码
    public static final String PerMess = BaseUrl + "User/get_userinfo";//个人资料
    public static final String UpHead = BaseUrl + "User/update_info";//上传头像
    public static final String ChangeNickName = BaseUrl + "User/update_info";//修改昵称
    public static final String ChangePhone = BaseUrl + "User/update_info";//修改手机号
    public static final String Vip_mess = BaseUrl + "Member/member_show";//会员信息
    public static final String Pay = BaseUrl + "Bill/topay";//支付
    public static final String School = BaseUrl + "Other/school";//内部学堂
    public static final String ChangePwd = BaseUrl + "User/update_pwd";//修改密码
    public static final String HelpCenter = BaseUrl + "Other/help_center";//帮助中心列表
    public static final String Help_desc = BaseUrl + "Other/help_detail";//帮助中心详情
    public static final String Mess_List = BaseUrl + "Message/message_list";//消息列表
    public static final String TotalAndBalance = BaseUrl + "Member/asserts";//总额和余额
    public static final String UpVIp = BaseUrl + "Member/promote"; //升级Vip
    public static final String IncomeDetail = BaseUrl + "Member/income";//收入明细
    public static final String With_number = BaseUrl + "Withdraw/withdraw_number";//获取提现帐号
    public static final String Change_Witchd = BaseUrl + "Withdraw/up_withdraw_number";//修改提现帐号或图片
    public static final String Service = BaseUrl + "Other/custom_service";//客服
    public static final String Member = BaseUrl + "Member/form";//报表
    public static final String Withdraw = BaseUrl + "Withdraw/withdraw_deposit";//发起提现
    public static final String Message = BaseUrl + "Message/center";//消息
    public static final String Home_AllData = BaseUrl + "Index/true_index"; //首页轮播
    public static final String Product_List = BaseUrl + "Index/classify_list";//首页假数据
    public static final String Seach_List = BaseUrl + "Index/search";//搜索页面
    public static final String IsCanWitde = BaseUrl + "Withdraw/withdraw_can";//是否能提现
    public static final String MyGener = BaseUrl + "Member/my_exten";//我的推广
    public static final String MyGenerList = BaseUrl + "Member/exten_list";//我的推广列表
    public static final String upVersion = BaseUrl + "Version/getversion";//版本升级
    public static final String Get_SMS = BaseUrl + "Register/sms";//获取验证码
    public static final String Sett_payPwd = BaseUrl + "User/setPayPwd";//设置支付密码修改
    public static final String ForPayPwd = BaseUrl + "User/find_paypwd";//忘记支付密码
    public static final String CheckPayPwd = BaseUrl + "User/check_old_paypwd";//校验支付密码
    public static final String Pay_Protocol ="http://120.27.14.79/a_plan/web/payProtocol.html";//支付服务协议
    public static final String Share_Pic = "http://120.27.14.79/A_plan/apk/share.png";//分享出去的图片地址
    public static final String Get_Token_code = BaseUrl + "Register/token_sms";//通过token获取验证码
    public static final String Code_Verify = BaseUrl + "User/token_code";//验证验证码
}
