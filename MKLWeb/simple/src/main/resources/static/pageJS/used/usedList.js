layui.use(['form', 'laydate', 'jquery', 'upload', 'table', 'layer'], function () {
    var form = layui.form,
        table = layui.table,
        laydate = layui.laydate,
        layer = layui.layer,
        $ = layui.jquery,
        upload = layui.upload;

    // 初始化使用记录表格
    var tableIns = table.render({
        elem: '#usedList',
        url: '/api/getUsedList',
        cellMinWidth: 95,
        id: "usedList",
        page: true,
        limit: 10,
        limits: [10, 15, 20],
        request: {
            pageName: 'current',
            limitName: 'size'
        },
        parseData: function (res) {
            return {
                "count": res.data.total,
                "data": res.data.records,
                "code": res.code,
                "msg": res.msg
            }
        },
        done: function (res, curr, count) {
        },
        cols: [[
            {field: "id", hide: true},
            {field: 'brand', title: '洗衣机品牌', align: 'center'},
            {field: 'brandType', title: '洗衣机型号', align: 'center'},
            {field: 'washingMachineNumber', title: '洗衣机编号', align: 'center'},
            {field: 'washingModel', title: '洗衣模式', align: 'center'},
            {field: 'washingTimes', title: '洗衣时长(单位:分钟)', align: 'center'},
            {field: 'usedDatetime', title: '使用时间', align: 'center'},
            {field: 'status', title: '状态', align: 'center', templet:
                    function(d){
                        if(d.status == 1){
                            return '<i class="layui-icon layui-icon-console" style="color: #FF5722"></i> 运行中';
                        }else if(d.status == 2){
                            return '<i class="layui-icon layui-icon-log" style="color: #FF5722"></i> 运行结束';
                        }
                    }
            },
            {field: 'usedUserName', title: '使用者用户名', align: 'center'},
            {title: '操作', width: 170, templet: '#usedListBar', fixed: "right", align: "center"}
        ]]
    });

    // 使用记录列表工具栏事件监听
    table.on('tool(usedList)', function (obj) {
        var event = obj.event,
            data = obj.data;
        if (event == 'used_detail') {
            usedDetail(data);
        }else if(event == 'used_edit'){
            addUsed(data);
        }
    });

    let usedIds = [];
    // 表格的复选框选择事件
    table.on('checkbox(usedList)', function (obj) {
        if (obj.type == 'one') {
            if (obj.checked) {
                // 如果复选框是选中事件, 将id加入到参数数组中
                usedIds.push(obj.data.id);
            } else {
                // 如果复选框是取消选中事件, 将id从参数数组中删除
                usedIds.remove(obj.data.id);
            }
        } else if (obj.type == 'all') {
            // 如果是全选, 将全选的id从参数数组中加入或删除
            var checkStatus = table.checkStatus('usedList');
            var data = checkStatus.data;
            $.each(data, function (index, dataObj) {
                if (obj.checked) {
                    usedIds.push(dataObj.id);
                } else {
                    usedIds.remove(dataObj.id);
                }
            })
        }

    })

    // 新增used信息按钮点击事件
    $('.addUsed_btn').on('click', function () {
        addUsed();
    });
    // 删除used信息按钮点击事件
    $('.deleteUsed_btn').on('click', function () {
        if(usedIds.length == 0){
            layer.msg('请至少选择一条记录进行删除!', {icon: 2});
            return false;
        }
        layer.confirm('确定要删除吗?', function () {
            let ids = usedIds.join(',');
            $.ajax({
                url: '/api/deleteUsedByIds/'+ids,
                type: 'DELETE',
                success: function (response) {
                    layer.msg('删除成功!', {icon: 1});
                    tableIns.reload();
                }
            });
        });
    });

    // 查询按钮点击事件
    $('.usedListSearch').on('click', function () {
        table.reload("usedList", {
            where: form.val('searchUsedListForm')
        })
    });

    // 打开使用记录详情
    function usedDetail(data) {
        var index = layer.open({
            title: '使用记录详情',
            type: 2,
            content: "/page/used>usedDetail",
            success: function (layero, index) {
                var body = layer.getChildFrame('body', index);
                body.find('#usedId').val(data.id);
                setTimeout(function () {
                    layui.layer.tips('点击此处返回', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        })
        layui.layer.full(index);
        window.sessionStorage.setItem("index", index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize", function () {
            layer.full(window.sessionStorage.getItem("index"));
        })
    }

    // 新增使用记录
    function addUsed(edit) {
        var index = layer.open({
            title: "使用记录",
            type: 2,
            content: "/page/used>usedAdd",
            success: function (layero, index) {
                var body = layer.getChildFrame('body', index);
                body.find('#usedId').val(edit.id);
                setTimeout(function () {
                    layui.layer.tips('点击此处返回', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        })
        layui.layer.full(index);
        window.sessionStorage.setItem("index", index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize", function () {
            layer.full(window.sessionStorage.getItem("index"));
        })
    }
});
