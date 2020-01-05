
let layer, notice, $;

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
    $ = layui.jquery;
    var admin = layui.admin;
    var element = layui.element;
    notice = layui.notice;
    layer = layui.layer;

    // 移除loading动画
    setTimeout(function () {
        admin.removeLoading();
    }, window == top ? 600 : 100);

    $('.front.banner').children('.title').addClass('active');

    //滚动监听
    $(window).scroll(function () {
        var scr = $(document).scrollTop();
        scr > 0 ? $(".nav").addClass('scroll') : $(".nav").removeClass('scroll');
    });

    //导航切换
    var btn = $('.nav').find('.nav-list').children('button')
        , spa = btn.children('span')
        , ul = $('.nav').find('.nav-list').children('.layui-nav');
    btn.on('click', function () {
        if (!$(spa[0]).hasClass('spa1')) {
            spa[0].className = 'spa1';
            spa[1].style.display = 'none';
            spa[2].className = 'spa3';
            $('.nav')[0].style.height = 90 + ul[0].offsetHeight + 'px';
        } else {
            spa[0].className = '';
            spa[1].style.display = 'block';
            spa[2].className = '';
            $('.nav')[0].style.height = 60 + 'px';
        }
    });

    // 图片自动解析，转换为img，添加tip
    $('body').on('mouseenter', '.img-tip', function (e) {
        var that = $(this);
        var html = '<div  style="width: 210px; position: relative;"><img src="' + that.attr('src') + '" style="width: 100%;"></div>'

        layer.tips(html, that, {
            time: -1
        });

        $('.layui-layer-tips').width(232);

        e.preventDefault();
        e.stopPropagation();
    }).on('mouseleave', '.img-tip', function () {
        layer.closeAll('tips');
    });


    $.prototype.serializeJson = function() {
        var serializeObj = {};
        var array = this.serializeArray();
        var str = this.serialize();
        $(array).each(
            function() {
                if (serializeObj[this.name]) {
                    if ($.isArray(serializeObj[this.name])) {
                        serializeObj[this.name].push(this.value);
                    } else {
                        serializeObj[this.name] = [
                            serializeObj[this.name], this.value ];
                    }
                } else {
                    serializeObj[this.name] = this.value;
                }
            });
        return serializeObj;
    };



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
     * 错误通知
     * @param response
     */
    error: function (info) {
        var o = {title: '错误通知', message: info, position: 'topRight'};
        notice.error(o);
    },
    /**
     * 消息通知
     * @param response
     */
    success: function (info) {
        var o = {title: '成功通知', message: info, position: 'topRight'};
        notice.success(o);
    },
    /**
     * 消息通知
     * @param response
     */
    warning: function (info) {
        var o = {title: '消息通知', message: info, position: 'topRight'};
        notice.warning(o);
    },
    /**
     * 文字输入弹窗
     * @param title
     * @param defaultValue
     * @param callback
     */
    prompt: function (title, defaultValue, callback) {
        layer.prompt({title: title, formType: 0, value: defaultValue}, function (value, index, elem) {
            layer.close(index);
            callback(value);
        });
    },
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
            notice.success(o);
        } else {
            notice.warning(o);
        }
    },
    /**
     * 发起post请求
     * @param url
     * @param paras
     * @param next 成功的回调
     */
    post: function (url, paras, next) {
        $.ajax({
            url: url,
            type: "POST",
            data: paras,
            success: function(rsp){
                if(rsp.code !== 200){
                    common.error(rsp.msg);
                }else{
                    //成功
                    if(next!=null){
                        next(rsp.data);
                    }else{
                        common.success('操作成功');
                    }
                }
            },
            error:function(rsp){
                common.error(rsp.message);
            }
        })
    },
    /**
     * 获取表格的选中行数据
     * @param layuiTable layui表格对象
     * @param tableId 表格ID
     */
    getOneFromTable: function (layuiTable, tableId) {
        var checkStatus = layuiTable.checkStatus(tableId)
            , data = checkStatus.data;
        if (data.length == 0) {
            common.warning("请选中一条记录");
        } else if (data.length > 1) {
            common.warning("只能选中一条记录")
        } else {
            return data[0];
        }
    },
    /**
     * Dialog信息展示框
     * @param url
     * @param title
     */
    dialog: function (url, title) {
        var index = layer.open({
            type: 2,
            content: url,
            title: title,
            maxmin: false
        });
        layer.full(index);
    },
    confirm: function (content, callback, callBackNo) {
        var index = layer.confirm(content, {
            btn: ['确认', '取消'] //按钮
        }, function () {
            if (callback != null) {
                callback();
            }
            layer.close(index);
        }, function () {
            if (callBackNo != null) {
                callBackNo()
            }
            layer.close(index);
        });

    },
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
        return unixTimestamp.toLocaleString();
    },
    /**
     * 日期时间格式转十位时间戳
     * @param str 格式要求：2014-04-23 18:55:49
     * @return number: 1398250549
     */
    dateStringTo10UnixTime: function (str) {
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
        if (parseInt(second_time) > 60) {

            var second = parseInt(second_time) % 60;
            var min = parseInt(second_time / 60);
            time = min + "分" + second + "秒";

            if (min > 60) {
                min = parseInt(second_time / 60) % 60;
                var hour = parseInt(parseInt(second_time / 60) / 60);
                time = hour + "小时" + min + "分" + second + "秒";

                if (hour > 24) {
                    hour = parseInt(parseInt(second_time / 60) / 60) % 24;
                    var day = parseInt(parseInt(parseInt(second_time / 60) / 60) / 24);
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
    addDate: function (date, days) {
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


