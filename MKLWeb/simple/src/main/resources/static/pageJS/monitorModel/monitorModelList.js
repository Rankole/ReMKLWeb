layui.use(['form', 'laydate', 'jquery', 'upload', 'table', 'layer'], function () {
    var form = layui.form,
        table = layui.table,
        laydate = layui.laydate,
        layer = layui.layer,
        $ = layui.jquery,
        upload = layui.upload;

    // 初始化监测模型信息表格
    var tableIns = table.render({
        elem: '#monitorModelList',
        url: '/api/getMonitorModelList',
        cellMinWidth: 95,
        id: "monitorModelList",
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
            {field: 'batteryId', title: '电池编号', align: 'center'},
            {field: 'scenario', title: '应用场景', align: 'center'},
            {field: 'etuName', title: 'ETU名称', align: 'center'},
            {field: 'batterySize', title: '电池规格', align: 'center'},
            {field: 'dataInterval', title: '数据间隔', align: 'center'},
            {field: 'alarmRole', title: '预警规则', align: 'center'},
            {field: 'algorithms', title: '智能算法', align: 'center'},
            {field: 'state', title: '状态', align: 'center'},
            {title: '操作', width: 170, templet: '#monitorModelListBar', fixed: "right", align: "center"}
        ]]
    });

    // 维修工信息列表工具栏事件监听
    table.on('tool(monitorModelList)', function (obj) {
        var event = obj.event,
            data = obj.data;
        if (event == 'monitorModel_detail') {
            monitorModelDetail(data);
        }else if(event == 'monitorModel_edit'){
            addMonitorModel(data);
        }
    });

    let monitorModelIds = [];
    // 表格的复选框选择事件
    table.on('checkbox(monitorModelList)', function (obj) {
        if (obj.type == 'one') {
            if (obj.checked) {
                // 如果复选框是选中事件, 将id加入到参数数组中
                monitorModelIds.push(obj.data.id);
            } else {
                // 如果复选框是取消选中事件, 将id从参数数组中删除
                monitorModelIds.remove(obj.data.id);
            }
        } else if (obj.type == 'all') {
            // 如果是全选, 将全选的id从参数数组中加入或删除
            var checkStatus = table.checkStatus('monitorModelList');
            var data = checkStatus.data;
            $.each(data, function (index, dataObj) {
                if (obj.checked) {
                    monitorModelIds.push(dataObj.id);
                } else {
                    monitorModelIds.remove(dataObj.id);
                }
            })
        }

    })

    // 新增MonitorModel信息按钮点击事件
    $('.addMonitorModel_btn').on('click', function () {
        addMonitorModel();
    });
    // 删除MonitorModel信息按钮点击事件
    $('.deleteMonitorModel_btn').on('click', function () {
        if(monitorModelIds.length == 0){
            layer.msg('请至少选择一条记录进行删除!', {icon: 2});
            return false;
        }
        layer.confirm('确定要删除吗?', function () {
            let ids = monitorModelIds.join(',');
            $.ajax({
                url: '/api/deleteMonitorModelByIds/'+ids,
                type: 'DELETE',
                success: function (response) {
                    layer.msg('删除成功!', {icon: 1});
                    tableIns.reload();
                }
            });
        });
    });

    // 查询按钮点击事件
    $('.monitorModelListSearch').on('click', function () {
        table.reload("monitorModelList", {
            where: form.val('searchMonitorModelListForm')
        })
    });

    // 打开信息详情
    function monitorModelDetail(data) {
        var index = layer.open({
            title: '监测模型信息详情',
            type: 2,
            content: "/page/monitorModel>monitorModelDetail",
            success: function (layero, index) {
                var body = layer.getChildFrame('body', index);
                body.find('#monitorModelId').val(data.id);
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
    function addMonitorModel(edit) {
        var index = layer.open({
            title: "监测模型信息",
            type: 2,
            content: "/page/monitorModel>monitorModelAdd",
            success: function (layero, index) {
                var body = layer.getChildFrame('body', index);
                body.find('#monitorModelId').val(edit.id);
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
