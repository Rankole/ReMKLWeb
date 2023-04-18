layui.use(['form', 'laydate', 'jquery', 'upload', 'table', 'layer'], function () {
    var form = layui.form,
        table = layui.table,
        laydate = layui.laydate,
        layer = layui.layer,
        $ = layui.jquery,
        upload = layui.upload;

    // 初始化维修工信息表格
    var tableIns = table.render({
        elem: '#maintenanceWorkerList',
        url: '/api/getMaintenanceWorkerList',
        cellMinWidth: 95,
        id: "maintenanceWorkerList",
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
            {type: 'checkbox'},
            {field: 'name', title: '维修工姓名', align: 'center'},
            {field: 'sex', title: '维修工性别', align: 'center'},
            {field: 'age', title: '维修工年龄', align: 'center'},
            {field: 'phone', title: '维修工电话', align: 'center'},
            {field: 'workNumber', title: '维修工工号', align: 'center'},
            {title: '操作', width: 170, templet: '#maintenanceWorkerListBar', fixed: "right", align: "center"}
        ]]
    });

    // 维修工信息列表工具栏事件监听
    table.on('tool(maintenanceWorkerList)', function (obj) {
        var event = obj.event,
            data = obj.data;
        if (event == 'maintenanceWorker_detail') {
            maintenanceWorkerDetail(data);
        }else if(event == 'maintenanceWorker_edit'){
            addMaintenanceWorker(data);
        }
    });

    let maintenanceWorkerIds = [];
    // 表格的复选框选择事件
    table.on('checkbox(maintenanceWorkerList)', function (obj) {
        if (obj.type == 'one') {
            if (obj.checked) {
                // 如果复选框是选中事件, 将id加入到参数数组中
                maintenanceWorkerIds.push(obj.data.id);
            } else {
                // 如果复选框是取消选中事件, 将id从参数数组中删除
                maintenanceWorkerIds.remove(obj.data.id);
            }
        } else if (obj.type == 'all') {
            // 如果是全选, 将全选的id从参数数组中加入或删除
            var checkStatus = table.checkStatus('maintenanceWorkerList');
            var data = checkStatus.data;
            $.each(data, function (index, dataObj) {
                if (obj.checked) {
                    maintenanceWorkerIds.push(dataObj.id);
                } else {
                    maintenanceWorkerIds.remove(dataObj.id);
                }
            })
        }

    })

    // 新增maintenanceWorker信息按钮点击事件
    $('.addMaintenanceWorker_btn').on('click', function () {
        addMaintenanceWorker();
    });
    // 删除maintenanceWorker信息按钮点击事件
    $('.deleteMaintenanceWorker_btn').on('click', function () {
        if(maintenanceWorkerIds.length == 0){
            layer.msg('请至少选择一条记录进行删除!', {icon: 2});
            return false;
        }
        layer.confirm('确定要删除吗?', function () {
            let ids = maintenanceWorkerIds.join(',');
            $.ajax({
                url: '/api/deleteMaintenanceWorkerByIds/'+ids,
                type: 'DELETE',
                success: function (response) {
                    layer.msg('删除成功!', {icon: 1});
                    tableIns.reload();
                }
            });
        });
    });

    // 查询按钮点击事件
    $('.maintenanceWorkerListSearch').on('click', function () {
        table.reload("maintenanceWorkerList", {
            where: form.val('searchMaintenanceWorkerListForm')
        })
    });

    // 打开维修工信息详情
    function maintenanceWorkerDetail(data) {
        var index = layer.open({
            title: '维修工信息详情',
            type: 2,
            content: "/page/maintenanceWorker>maintenanceWorkerDetail",
            success: function (layero, index) {
                var body = layer.getChildFrame('body', index);
                body.find('#maintenanceWorkerId').val(data.id);
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

    // 新增维修工信息
    function addMaintenanceWorker(edit) {
        var index = layer.open({
            title: "维修工信息",
            type: 2,
            content: "/page/maintenanceWorker>maintenanceWorkerAdd",
            success: function (layero, index) {
                var body = layer.getChildFrame('body', index);
                body.find('#maintenanceWorkerId').val(edit.id);
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
