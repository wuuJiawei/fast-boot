
/**
 * 路由模块
 * @author wujiawei0926@yeah.net
 */
layui.define(["layer", "admin"], function (exports) {
    "use strict";

    var MOD_NAME = 'easyRouter',
        $ = layui.jquery,
        layer = layui.layer,
        admin = layui.admin;
    var router = function () {
        this.v = '1.0';
    };

    var router = {
        to: '',
        jumpCallback: function () {},
        init: function (to, callback) {
            router.to = to;
            router.jumpCallback = callback;

            // 优先处理#!
            router.checkHashExist(function (cacheIndex) {
                var u = (window.location.href).substring(cacheIndex + 2);
                // 从localStorage取值
                var localData = layui.data('routerHash');
                if (localData[u] === undefined) {
                    window.location.href = window.location.href.substring(0, cacheIndex);
                }
                router.jump(u, localData[u]);
            }, function () {
                // 其次init
                var routerInitSelector = $('[router][router-init]').eq(0);
                if (routerInitSelector.length > 0) {
                    router.show(routerInitSelector);
                }
            });

            // 注册点击事件
            setTimeout(function () {
                $('body').on('click', '*[router]', function () {
                    // router.show($(this));
                    var that = $(this),
                        href = that.attr('href');
                    router.updateWindowLocation(href);
                    return false;
                });
            }, 500);


            // 开启hash监听
            router.listenHashChange();
        },
        show: function (elem) {
            var that = elem,
                href = that.attr('href'),
                to = that.attr('router') || router.to;
            router.jump(href, to);
        },
        jump: function (href, to) {
            admin.showLoading(to);
            $.ajax({
                url: href,
                type: 'GET',
                success: function (r) {
                    $(to).html(r);
                    var newUrl = router.updateWindowLocation(href);

                    layui.data('routerHash', {
                        key: href
                        ,value: to
                    });

                    // 回调
                    if (router.jumpCallback != undefined) {
                        setTimeout(function () {
                            router.jumpCallback(href, newUrl);
                        }, 600);
                    }
                },
                error: function (xhr) {
                    console.log(xhr)
                }
            });

        },
        updateWindowLocation: function (href) {
            var url = window.location.href,
                newUrl = '';

            router.checkHashExist(function (cacheIndex) {
                newUrl = url.substring(0, cacheIndex) + '#!' + href;
            }, function () {
                newUrl = url + '#!' + href;
            });
            window.location.href = newUrl;
            return newUrl;
        },
        checkHashExist: function (existCallback, dontCallback) {
            var url = window.location.href,
                cacheIndex = url.indexOf('#!');
            if (cacheIndex > 0) {
                existCallback(cacheIndex);
            } else {
                dontCallback(cacheIndex);
            }
        },
        listenHashChange: function () {
            if( ('onhashchange' in window) && ((typeof document.documentMode==='undefined') || document.documentMode==8)) {
                window.onhashchange = function () {
                    var url = window.location.href;
                    router.checkHashExist(function (cacheIndex) {
                        var u = url.substring(cacheIndex + 2);
                        var localData = layui.data('routerHash');
                        router.jump(u, localData[u]);
                    });
                }
            } else {
                // 不支持则用定时器检测的办法
                /*setInterval(function() {
                 // 检测hash值或其中某一段是否更改的函数， 在低版本的iE浏览器中通过window.location.hash取出的指和其它的浏览器不同，要注意
                 var ischanged = isHashChanged();
                 if(ischanged) {
                 hashChange();  // TODO，对应新的hash执行的操作函数
                 }
                 }, 150);*/
            }
        }
    };

    exports(MOD_NAME, router);
});