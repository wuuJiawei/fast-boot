layui.define(['table', 'jquery', 'form'], function (exports) {
    "use strict";

    var MOD_NAME = 'tableSelect',
        $ = layui.jquery,
        table = layui.table,
        form = layui.form;
    var tableSelect = function () {
        this.v = '1.2.0';
    };

    /**
    * 初始化表格选择器
    */
    tableSelect.prototype.render = function (opt) {
        var elem = $(opt.elem);
        var tableDone = opt.table.done || function(){};
		
        //默认设置
        opt.searchKey = opt.searchKey || 'keyword';
        opt.searchPlaceholder = opt.searchPlaceholder || '关键词搜索';
        // opt.checkedKey = opt.checkedKey;
        opt.table.page = opt.table.page || true;
        opt.table.height = opt.table.height || 315;

        // v1.2 - 1.新增search支持多条件搜索
        var search = opt.search;
        //.. v1.2 - search支持多条件搜索

        elem.off('click').on('click', function(e) {
            e.stopPropagation();

            if($('div.tableSelect').length >= 1){
                return false;
            }

            var t = elem.offset().top + elem.outerHeight()+"px";
            var l = elem.offset().left +"px";
            var tableName = "tableSelect_table_" + new Date().getTime();
            var tableBox = '<div class="tableSelect layui-anim layui-anim-upbit" style="left:'+l+';top:'+t+';border: 1px solid #d2d2d2;background-color: #fff;box-shadow: 0 2px 4px rgba(0,0,0,.12);padding:10px 10px 0 10px;position: absolute;z-index:66666666;margin: 5px 0;border-radius: 2px;min-width:530px;">';
                tableBox += '<div class="tableSelectBar">';
                tableBox += '<form class="layui-form" action="" style="display:inline-block;">';

            // v1.2 - search支持多条件搜索
            if (search) {
                var arr = [];
                search.forEach(function (item, index) {
                    if (item.type === 'text') {
                        arr.push('<input style="display:inline-block;width:190px;height:30px;vertical-align:middle;margin-right:2px;border: 1px solid #C9C9C9;" type="text" name="'+item.key+'" placeholder="'+item.placeholder+'" autocomplete="off" class="layui-input">')
                    } else if (item.type === 'select') {
                        arr.push('<select style="display:inline-block;width:190px;height:30px;vertical-align:middle;margin-right:2px;border: 1px solid #C9C9C9;" type="text" name="'+item.key+'">')
                        arr.push(' <option value="">'+ item.placeholder +'：全部</option>');
                        if (item.data) {
                            item.data.forEach(function (x, j) {
                                arr.push('<option value="'+ x.key +'">'+ x.value +'</option>');
                            });
                        }
                        arr.push('</select>');
                    }
                });
                tableBox += arr.join('');
            } else {
                tableBox += '<input style="display:inline-block;width:190px;height:30px;vertical-align:middle;margin-right:-1px;border: 1px solid #C9C9C9;" type="text" name="'+opt.searchKey+'" placeholder="'+opt.searchPlaceholder+'" autocomplete="off" class="layui-input">'
            }
            //.. v1.2 - search支持多条件搜索

                tableBox += '<button class="layui-btn layui-btn-sm layui-btn-primary tableSelect_btn_search" lay-submit lay-filter="tableSelect_btn_search"><i class="layui-icon layui-icon-search"></i></button>';
                tableBox += '</form>';
                tableBox += '<button style="float:right;" class="layui-btn layui-btn-sm tableSelect_btn_select">选择<span></span></button>';
                tableBox += '<button style="float:right;margin-right: 6px;" class="layui-btn layui-btn-primary layui-btn-sm tableSelect_btn_clear">清空<span></span></button>';
                tableBox += '</div>';
                tableBox += '<table id="'+tableName+'" lay-filter="'+tableName+'"></table>';
                tableBox += '</div>';
                tableBox = $(tableBox);
            $('body').append(tableBox);
            
            //数据缓存
            var checkedKeyData = [], pageData = [], firstLoad = true;

            //渲染TABLE
            opt.table.elem = "#"+tableName;
            opt.table.id = tableName;
            opt.table.done = function(res, curr, count){
                defaultChecked(res, curr, count);
                setChecked(res, curr, count);
                tableDone(res, curr, count);
            };
            var tableSelect_table = table.render(opt.table);

            //分页选中保存数组
            table.on('radio('+tableName+')', function(obj){
                if(opt.checkedKey){
                    checkedKeyData = table.checkStatus(tableName).data[opt.checkedKey]
                }
                updateButton(table.checkStatus(tableName).data.length)
            })
			table.on('checkbox('+tableName+')', function(obj){
                if(opt.checkedKey){
                    // 全选
                    if(obj.type === 'all'){
                        if (obj.checked) {
                            pageData.forEach(function (x, i) {
                                checkedKeyData.push(x + '')
                            });
                        } else {
                            pageData.forEach(function (x, i) {
                                checkedKeyData.forEach(function (y, j) {
                                    if(y == x){
                                        checkedKeyData.splice(j,1)
                                    }
                                })

                            });
                        }

                    // 单选
                    }else{
                        if(obj.checked){
                            checkedKeyData.push(obj.data[opt.checkedKey] + '')
                        } else {
                            checkedKeyData.forEach(function (y, j) {
                                if(obj.data[opt.checkedKey] == y){
                                    checkedKeyData.splice(j,1)
                                }
                            });
                        }
                    }

                    checkedKeyData = uniqueObjArray(checkedKeyData);

                    // elem.attr('ts-selected', checkedKeyData.join(','));

                    updateButton(checkedKeyData.length)
                }else{
                    updateButton(table.checkStatus(tableName).data.length)
                }
            });

            //渲染表格后选中
            function setChecked (res, curr, count) {
                var list = res.data;
				for(var i=0;i<list.length;i++){
            		for (var j=0;j<checkedKeyData.length;j++) {
            			if(list[i][opt.checkedKey] == checkedKeyData[j]){
                            list[i].LAY_CHECKED = true;
                            var index= list[i]['LAY_TABLE_INDEX'];
                            var checkbox = $('#'+tableName+'').next().find('tr[data-index=' + index + '] input[type="checkbox"]');
            				    checkbox.prop('checked', true).next().addClass('layui-form-checked');
                            var radio  = $('#'+tableName+'').next().find('tr[data-index=' + index + '] input[type="radio"]');
                                radio.prop('checked', true).next().addClass('layui-form-radioed').find("i").html('&#xe643;');
            			}
            		}
            	}
            	var checkStatus = table.checkStatus(tableName);
				if(checkStatus.isAll){
					$('#'+tableName+'').next().find('.layui-table-header th[data-field="0"] input[type="checkbox"]').prop('checked', true);
					$('#'+tableName+'').next().find('.layui-table-header th[data-field="0"] input[type="checkbox"]').next().addClass('layui-form-checked');
				}
				updateButton(checkedKeyData.length)
            }
            
            //写入默认选中值(puash checkedKeyData)
            function defaultChecked (res, curr, count) {

                if (firstLoad) {
                    var selected = elem.attr('ts-selected');
                    if(opt.checkedKey && selected){
                        var selected = selected.split(",");
                        checkedKeyData = selected;
                    }
                    firstLoad = false;
                }

                pageData = [];
                res.data.forEach(function (x, i) {
                    pageData.push(x[opt.checkedKey])
                });
            }

			//更新选中数量
			function updateButton (n) {
				tableBox.find('.tableSelect_btn_select span').html(n==0?'':'('+n+')')
            }
            
            //数组去重
			function uniqueObjArray(arr, type){
                var newArr = [];
                var tArr = [];
                if(arr.length == 0){
                    return arr;
                }else{
                    if(type){
                        for(var i=0;i<arr.length;i++){
                            if(!tArr[arr[i][type]]){
                                newArr.push(arr[i]);
                                tArr[arr[i][type]] = true;
                            }
                        }
                        return newArr;
                    }else{
                        for(var i=0;i<arr.length;i++){
                            if(!tArr[arr[i]]){
                                newArr.push(arr[i]);
                                tArr[arr[i]] = true;
                            }
                        }
                        return newArr;
                    }
                }
            }

			//FIX位置
			var overHeight = (elem.offset().top + elem.outerHeight() + tableBox.outerHeight() - $(window).scrollTop()) > $(window).height();
			var overWidth = (elem.offset().left + tableBox.outerWidth()) > $(window).width();
			    overHeight && tableBox.css({'top':'auto','bottom':'0px'});
			    overWidth && tableBox.css({'left':'auto','right':'5px'})
			
            //关键词搜索
            form.on('submit(tableSelect_btn_search)', function(data){
                tableSelect_table.reload({
                    where: data.field,
                    page: {
                      curr: 1
                    }
                });
                return false;
            });

            //双击行选中
            table.on('rowDouble('+tableName+')', function(obj) {
                var checkStatus = {data:[obj.data]};
                selectDone(checkStatus);
            });

            //按钮选中
            tableBox.find('.tableSelect_btn_select').on('click', function() {
                selectDone(checkedKeyData);
            });

            // 清空
            tableBox.find('.tableSelect_btn_clear').on('click', function () {
                checkedKeyData = [];
                updateButton(0);
                tableSelect_table.reload();
            });

            //写值回调和关闭
            function selectDone (checkedData){
                elem.attr("ts-selected",checkedData.join(","));
                opt.done(elem, checkedData);
                tableBox.remove();
                checkedKeyData = [];
            }
            
            //点击其他区域关闭
            $(document).mouseup(function(e){
                var userSet_con = $(''+opt.elem+',.tableSelect');
                if(!userSet_con.is(e.target) && userSet_con.has(e.target).length === 0){
                    tableBox.remove();
                    checkedKeyData = [];
                }
            });


        })
    }

    /**
    * 隐藏选择器
    */
    tableSelect.prototype.hide = function (opt) {
        $('.tableSelect').remove();
    }

    //自动完成渲染
    var tableSelect = new tableSelect();

    //FIX 滚动时错位
    if(window.top == window.self){
        $(window).scroll(function () {
            tableSelect.hide();
        });
    }

    exports(MOD_NAME, tableSelect);
})