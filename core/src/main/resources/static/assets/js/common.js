/** EasyWeb iframe v3.1.4 date:2019-08-05 License By http://easyweb.vip */


var layer;


// 以下代码是配置layui扩展模块的目录，每个页面都需要引入
layui.config({
    version: true,
    base: getProjectUrl() + 'assets/module/'
}).extend({
    formSelects: 'formSelects/formSelects-v4',
    treetable: 'treetable-lay/treetable',
    dropdown: 'dropdown/dropdown',
    notice: 'notice/notice',
    step: 'step-lay/step',
    dtree: 'dtree/dtree',
    citypicker: 'city-picker/city-picker',
    tableSelect: 'tableSelect/tableSelect',
    Cropper: 'Cropper/Cropper',
    zTree: 'zTree/zTree',
    introJs: 'introJs/introJs',
    fileChoose: 'fileChoose/fileChoose',
    tagsInput: 'tagsInput/tagsInput',
    CKEDITOR: 'ckeditor/ckeditor',
    Split: 'Split/Split',
    cascader: 'cascader/cascader',
    easyRouter: 'router',
    timeago: 'timeago',
    easemob: 'easemob/easemob',
    jqueryUI: 'jquery-ui/jquery-ui'
}).use(['admin', 'element', 'notice', 'layer'], function () {
    var $ = layui.jquery;
    var admin = layui.admin;
    var element = layui.element;
    var notice = layui.notice;
    layer = layui.layer;

    // 移除loading动画
    setTimeout(function () {
        admin.removeLoading();
    }, window == top ? 600 : 100);

    $('.front.banner').children('.title').addClass('active');

    //滚动监听
    $(window).scroll(function() {
        var scr=$(document).scrollTop();
        scr > 0 ? $(".nav").addClass('scroll') : $(".nav").removeClass('scroll');
    });

    //导航切换
    var btn = $('.nav').find('.nav-list').children('button')
        ,spa = btn.children('span')
        ,ul = $('.nav').find('.nav-list').children('.layui-nav');
    btn.on('click', function(){
        if(!$(spa[0]).hasClass('spa1')){
            spa[0].className = 'spa1';
            spa[1].style.display = 'none';
            spa[2].className = 'spa3';
            $('.nav')[0].style.height = 90 + ul[0].offsetHeight + 'px';
        }else{
            spa[0].className = '';
            spa[1].style.display = 'block';
            spa[2].className = '';
            $('.nav')[0].style.height = 60 + 'px';
        }
    });

    // 图片自动解析，转换为img，添加tip
    $('body').on('mouseenter', '.img-tip', function (e) {
        var that = $(this);
        var html = '<div  style="width: 210px; position: relative;"><img src="'+ that.attr('src') +'" style="width: 100%;"></div>'

        layer.tips(html, that, {
            time: -1
        });

        $('.layui-layer-tips').width(232);

        e.preventDefault();
        e.stopPropagation();
    }).on('mouseleave', '.img-tip', function () {
        layer.closeAll('tips');
    });

});

// 获取当前项目的根路径，通过获取layui.js全路径截取assets之前的地址
function getProjectUrl() {
    var layuiDir = layui.cache.dir;
    if (!layuiDir) {
        var js = document.scripts, last = js.length - 1, src;
        for (var i = last; i > 0; i--) {
            if (js[i].readyState === 'interactive') {
                src = js[i].src;
                break;
            }
        }
        var jsPath = src || js[last].src;
        layuiDir = jsPath.substring(0, jsPath.lastIndexOf('/') + 1);
    }
    return layuiDir.substring(0, layuiDir.indexOf('assets'));
}

var common = {
    /**
     * 根据返回值进行提示
     * @param response
     */
    message: function (response, successFn) {
        var o = {title: '消息通知', message: response.msg, position: 'topRight'};
        if (response.code == 200) {
            if (successFn) {
                successFn();
            }
            top.notice.success(o);
        } else {
            top.notice.warning(o);
        }
    }
};

// 字符串处理
var utils = {
    /**
     * 是否为空
     */
    isEmpty: function (str) {
        if (str === null || str === undefined || str === "") {
            return true;
        }
        return false;
    },
    /**
     * 时间戳转日期格式
     * @param str
     */
    unixTimeToDateString: function (time) {
        console.log(time)
        var unixTimestamp = new Date(time * 1000);
        return  unixTimestamp.toLocaleString();
    },
    /**
     * 日期时间格式转十位时间戳
     * @param str 格式要求：2014-04-23 18:55:49
     * @return number: 1398250549
     */
    dateStringTo10UnixTime: function(str) {
        return Date.parse(str) / 1000;
    },
    /**
     * 毫秒转时分秒（e.g. 3小时2分钟15秒）
     * @param msd
     * @returns {string}
     */
    millisecondToDate: function (msd) {
        var second_time = parseFloat(msd) / 1000;

        var time = parseInt(second_time) + "秒";
        if( parseInt(second_time )> 60){

            var second = parseInt(second_time) % 60;
            var min = parseInt(second_time / 60);
            time = min + "分" + second + "秒";

            if( min > 60 ){
                min = parseInt(second_time / 60) % 60;
                var hour = parseInt( parseInt(second_time / 60) /60 );
                time = hour + "小时" + min + "分" + second + "秒";

                if( hour > 24 ){
                    hour = parseInt( parseInt(second_time / 60) /60 ) % 24;
                    var day = parseInt( parseInt( parseInt(second_time / 60) /60 ) / 24 );
                    time = day + "天" + hour + "小时" + min + "分" + second + "秒";
                }
            }


        }

        return time;
    },
    /**
     * 在原有日期基础上，增加days天数，默认增加1天
     * @param date
     * @param days
     * @returns {string}
     */
    addDate: function(date, days) {
        if (days == undefined || days == '') {
            days = 1;
        }
        var date = new Date(date);
        date.setDate(date.getDate() + days);
        var month = date.getMonth() + 1;
        var day = date.getDate();
        return date.getFullYear() + '-' + getFormatDate(month) + '-' + getFormatDate(day);


        function getFormatDate(arg) {
            if (arg == undefined || arg == '') {
                return '';
            }

            var re = arg + '';
            if (re.length < 2) {
                re = '0' + re;
            }

            return re;
        }

    },
    /**
     * 消息提醒
     * @param text
     */
    msg: function (text) {
        layer.msg(text, {icon: 0});
    }

};


// 提示
var tip = {
    /**
     * 图片路径转换img，提供tip提示
     * @param url 图片路径
     */
    image: function (url) {
        var u = url;
        if (u === '') {
            return '暂无图片';
        }
        return '<img src="'+ u +'" style="height: 30px;" class="img-tip">';
    }
};


