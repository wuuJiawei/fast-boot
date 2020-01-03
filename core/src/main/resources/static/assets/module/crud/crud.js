/**

 @Title: crud 增删改查
 @Author: wujiawei0926@yeah.net
 @License：内部使用

 */


layui.define(['layer', 'form', 'table', 'util', 'admin', 'laydate', 'upload', 'element'], function (exports) {
    "use script";

    var $ = layui.$;
    var hint = layui.hint();
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var util = layui.util;
    var admin = layui.admin;
    var laydate = layui.laydate;
    var upload = layui.upload;
    var element = layui.element;

    // 外部接口
    var crud = {
        config: {} // 全局配置项

        // 设置全局项
        ,set: function(options){
            var that = this;
            that.config = $.extend({}, that.config, options);
            return that;
        }

        // 事件监听
        ,on: function(events, callback){
            return layui.onevent.call(this, MOD_NAME, events, callback);
        }
    };

    // 字符常量
    var MOD_NAME = 'crud', CLASS_NAME = 'layui-crud', THIS = 'layui-this', SHOW = 'layui-show', HIDE = 'layui-hide', DISABLED = 'layui-disabled'
        , BTN_SEARCH = 'btn-crud-search', BTN_INSERT = 'btn-crud-insert', BTN_DELETE = 'btn-crud-delete';

    // 构造器
    var Class = function (options) {
        var that = this;
        that.config = $.extend({}, that.config, crud.config, options);
        that.render();
    };

    // 默认配置
    Class.prototype.config = {
        elem: '#app'
        , table: {
            elem: '#crud-table'
        }
        // 异步数据接口相关参数。其中 url 参数为必填项
        , url: ''
        , search: {
            elem: '#crud-toolbar'
        }
        , insert: true
        , update: true
        , delete: true
        , export: true
        // 是否显示加载条（默认：true）。如果设置 false，则在切换分页时，不会出现加载条。该参数只适用于 url 参数开启的方式
        , loading: true

    };

    // 渲染
    Class.prototype.render = function () {
        var that = this,
            options = that.config;

        options.elem = $(options.elem);


    };

    // 绘制DOM
    Class.prototype.draw = function () {
        var that = this,
            options = that.config;

        var tmp = '';

        // 是否绘制loading
        if (options.loading) {
            tmp += '<div class="page-loading"><div class="rubik-loader"></div></div>';
        }

        // card视图 --START--
        tmp += '<div class="layui-fluid '+ CLASS_NAME +'"><div class="layui-card"><div class="layui-card-body">';

        // 是否绘制search
        if (options.search) {
            tmp += '<form class="layui-form crud-toolbar" id="'+ options.search.elem +'" action="javascript:;">';

            // 根据fields绘制筛选条件
            if (options.fields) {
                options.fields.forEach(function (item, index) {
                    if (item.search) {
                        // 判断是否重写了模板
                        if (item.search.templet) {

                        }
                    }
                });
            }

            tmp += '<div class="layui-inline">' +
                '<button id="'+ BTN_SEARCH +'" lay-filter="btn-crud-search" lay-submit class="layui-btn icon-btn"><i class="layui-icon">&#xe615;</i>搜索</button>';

            if (options.insert) {
                tmp += '<button id="'+ BTN_INSERT +'" class="layui-btn icon-btn"><i class="layui-icon">&#xe654;</i>添加</button>';
            }

            if (options.delete) {
                tmp += '<button id="'+ BTN_DELETE +'" class="layui-btn icon-btn"><i class="layui-icon layui-icon-delete"></i> 删除</button>';
            }

            tmp += '</div>';
            tmp += '</form>';
        }

        // 是否绘制table
        if (options.table) {
            var filter = (options.table.elem).split('#')[1];
            tmp += '<table id="'+ options.table.elem +'" lay-filter="'+ filter +'"></table>';
        }


        // card视图 --END--
        tmp += '</div></div></div>';


        $(options.elem).html(tmp);
    };


    //核心入口
    crud.render = function(options){
        return new Class(options);
    };

    exports(MOD_NAME, crud);

    var crud2 = {

        /**
         * 渲染的DOM，在该elem中渲染所有的增删改查组件
         */
        elem: '',


        /**
         * 通用的请求路径，例如：${ctxPath}/system/user/，required
         */
        url: '',

        render: function(opts){

        },


        /**
         * 渲染数据表格
         * @param {
         *     elem: '渲染的源DOM, required',
         *     url: '完整的数据来源地址',
         *     urlSimple: '精简的数据来源地址，通过[crud.commonRequestPath + urlSimple]组成完整的数据来源地址',
         *     toolbar: {   // 最右侧工具栏
         *         enabled: '是否启动：true/false，默认true' ,
         *         fixed: '是否固定在右侧：true/false，默认false',
         *         elem: '源DOM，默认#toolbar',
         *         minWidth: '最小宽度，通layui-table，默认180',
         *         width: '指定宽度，同layui-table，默认空'
         *     },
         *     colConfig: {  // 字段通用配置
         *         align: '排列位置：left/center/right，默认left',
         *         checkbox: '是否启用checkbox列，默认true',
         *         numbers: '是否启用数字序号列，默认false'
         *     }
         *     cols: [  // 字段数组
         *         {
         *             field: '字段名称, required',
         *             title: '字段标题, required',
         *             sort: '是否排序：true/false，默认true',
         *             edit: '是否支持编辑：true/false，默认true',
         *             width: '宽度，同layui-table，默认空'
         *         }
         *     ]
         * }
         * @returns 表格渲染后的实例
         */
        tableRender : function (opts) {
            return table.render({
                elem: opts.elem,
                url: crud.url + 'query',
                page: true,
                cellMinWidth: 100,
                limit: 20,
                limits: [10, 20, 50, 100, 200, 999],
                cols: [[
                    {type: 'checkbox'},
                    {field: 'id', align: 'left', title: 'ID', sort: true},
                    {field: 'listOrder', align: 'left', title: '排序', sort: true, edit: true},
                    {field: 'name', align: 'left', title: '名称', sort: true},
                    {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 180}
                ]]
            });
        }
    };

});
