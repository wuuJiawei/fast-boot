/**
 * 环信IM集成
 * @author wujiawei0926@yeah.net
 * @date 2019/08/29
 */
layui.define(['layer'], function (exports) {
    "use strict";

    var MOD_NAME = 'easemob',
        $ = layui.jquery,
        layer = layui.layer;

    layui.script(layui.cache.base + 'easemob/EMedia_sdk-dev.js');
    layui.script(layui.cache.base + 'easemob/EMedia_x1v1.js');
    layui.script(layui.cache.base + 'easemob/webimSDK3.0.5.js');

    var config = {
        xmppURL: 'im-api.easemob.com',            // xmpp Server地址，对于在console.easemob.com创建的appKey，固定为该值
        apiURL: 'http://a1.easemob.com',          // rest Server地址，对于在console.easemob.com创建的appkey，固定为该值
        appkey: '',        // App key
        https : false,                            // 是否使用https
        isHttpDNS: true,                         //防止DNS劫持从服务端获取XMPPUrl、restUrl
        isMultiLoginSessions: false,              // 是否开启多页面同步收消息，注意，需要先联系商务开通此功能
        isAutoLogin: true,                        // 自动出席，（如设置为false，则表示离线，无法收消息，需要在登录成功后手动调用conn.setPresence()才可以收消息）
        isDebug: false,                           // 打开调试，会自动打印log，在控制台的console中查看log
        autoReconnectNumMax: 2,                   // 断线重连最大次数
        autoReconnectInterval: 2,                 // 断线重连时间间隔
        heartBeatWait: 4500,                       // 使用webrtc（视频聊天）时发送心跳包的时间间隔，单位ms
        delivery: true                           // 是否发送已读回执
    };

    var easemob = {
        conn: null,
        // 渲染组件
        render: function (conf) {
            config.appkey = conf.appkey;

            setTimeout(function () {
                console.log(WebIM);

                easemob.conn = new WebIM.default.connection({
                    appKey: config.appkey,
                    isHttpDNS: config.isHttpDNS,
                    isMultiLoginSessions: config.isMultiLoginSessions,
                    https: config.https,
                    url: config.xmppURL,
                    apiUrl: config.apiURL,
                    isAutoLogin: false,
                    heartBeatWait: config.heartBeatWait,
                    autoReconnectNumMax: config.autoReconnectNumMax,
                    autoReconnectInterval: config.autoReconnectInterval,
                    isStropheLog: config.isStropheLog,
                    delivery: config.delivery
                });
                easemob.open(conf);
                easemob.conn.listen(conf);
            }, 800);


            return easemob.conn;
        },
        // 登录
        open: function (options) {
            // var options = {
            //     apiUrl: WebIM.config.apiURL,
            //     user: 'username',
            //     pwd: 'password',
            //     appKey: WebIM.config.appkey,
            //     success: function (token) {
            //         var token = token.access_token;
            //         WebIM.utils.setCookie('webim_' + encryptUsername, token, 1);
            //     },
            //     error: function(){
            //     }
            // };
            var opts = options;
            opts.apiUrl = config.apiURL;
            opts.appKey = config.appkey;
            easemob.conn.open(options);
        },
        // 退出
        close: function () {
            easemob.conn.close();
        },
        // 发送文本消息（单聊）
        sendPrivateText: function (options) {
            var id = easemob.conn.getUniqueId();                 // 生成本地消息id
            var msg = new WebIM.message('txt', id);      // 创建文本消息
            // msg.set({
            //     msg: 'message content',                  // 消息内容
            //     to: 'username',                          // 接收消息对象（用户id）
            //     roomType: false,
            //     ext: {},                                  //扩展消息
            //     success: function (id, serverMsgId) {
            //         console.log('send private text Success');
            //     },                                       // 对成功的相关定义，sdk会将消息id登记到日志进行备份处理
            //     fail: function(e){
            //         console.log("Send private text error");
            //     }                                        // 对失败的相关定义，sdk会将消息id登记到日志进行备份处理
            // });
            var opt = options;
            opt.roomType = false;
            msg.set(opt);
            easemob.conn.send(msg.body);
        },
        // 发送文本消息（群组）
        sendGroupText: function (options) {
            var id = easemob.conn.getUniqueId();            // 生成本地消息id
            var msg = new WebIM.message('txt', id); // 创建文本消息
            // var option = {
            //     msg: 'message content',             // 消息内容
            //     to: 'group id',                     // 接收消息对象(群组id)
            //     roomType: false,                    // 群聊类型，true时为聊天室，false时为群组
            //     ext: {},                            // 扩展消息
            //     success: function () {
            //         console.log('send room text success');
            //     },                                  // 对成功的相关定义，sdk会将消息id登记到日志进行备份处理
            //     fail: function () {
            //         console.log('failed');
            //     }                                   // 对失败的相关定义，sdk会将消息id登记到日志进行备份处理
            // };
            var opt = options;
            opt.roomType = false;
            msg.set(opt);
            msg.setGroup('groupchat');              // 群聊类型
            easemob.conn.send(msg.body);
        },
        // 聊天室发送文本消息
        sendRoomText: function (options) {
            var id = easemob.conn.getUniqueId();         // 生成本地消息id
            var msg = new WebIM.message('txt', id); // 创建文本消息
            // var option = {
            //     msg: 'message content',          // 消息内容
            //     to: 'chatroom id',               // 接收消息对象(聊天室id)
            //     roomType: true,                  // 群聊类型，true时为聊天室，false时为群组
            //     ext: {},                         // 扩展消息
            //     success: function () {
            //         console.log('send room text success');
            //     },                               // 对成功的相关定义，sdk会将消息id登记到日志进行备份处理
            //     fail: function () {
            //         console.log('failed');
            //     }                                // 对失败的相关定义，sdk会将消息id登记到日志进行备份处理
            // };
            var opt = options;
            opt.roomType = true;
            msg.set(opt);
            msg.setGroup('groupchat');           // 群聊类型
            easemob.conn.send(msg.body);
        }
    }


    exports(MOD_NAME, easemob);
});