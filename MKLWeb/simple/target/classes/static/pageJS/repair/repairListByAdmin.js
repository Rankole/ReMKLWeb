layui.use(['form', 'laydate', 'jquery', 'upload', 'table', 'layer'], function () {
    var form = layui.form,
        table = layui.table,
        laydate = layui.laydate,
        layer = layui.layer,
        $ = layui.jquery,
        upload = layui.upload;

    // 初始化报修管理表格
    var tableIns = table.render({
        elem: '#repairList',
        url: '/api/getRepairList',
        cellMinWidth: 95,
        id: "repairList",
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
            let status = res.data.status;
            switch(status){
                case 0:
                    res.data.status = '待维修';
                    break;
                case 1:
                    res.data.status = '已维修';
                    break;
            }
        },
        cols: [[
            {field: "id", hide: true},
            {field: 'brand', title: '洗衣机品牌', align: 'center'},
            {field: 'brandType', title: '洗衣机型号', align: 'center'},
            {field: 'washingMachineNumber', title: '洗衣机编号', align: 'center'},
            {field: 'repairUserName', title: '报修者', align: 'center'},
            {field: 'workerName', title: '维修工姓名', align: 'center'},
            {field: 'workerPhone', title: '维修工电话', align: 'center'},
            {field: 'repairDateime', title: '报修时间', align: 'center'},
            {field: 'solveDatetime', title: '处理时间', align: 'center'},
            {field: 'status', title: '状态', align: 'center', templet: function(d){
                    switch (d.status){
                        case 0:
                            return '待维修';
                            break;
                        case 1:
                            return '已维修';
                            break;
                    }
            }},
            {title: '操作', width: 170, templet: '#repairListBar', fixed: "right", align: "center"}
        ]]
    });

    // 报修管理列表工具栏事件监听
    table.on('tool(repairList)', function (obj) {
        var event = obj.event,
            data = obj.data;
        if (event == 'repair_detail') {
            repairDetail(data);
        }else if(event == 'repair_done'){
            repairDone(data);
        }
    });


    // 新增repair信息按钮点击事件
    $('.addRepair_btn').on('click', function () {
        addRepair();
    });
    // 删除repair信息按钮点击事件
    $('.deleteRepair_btn').on('click', function () {
        if(repairIds.length == 0){
            layer.msg('请至少选择一条记录进行删除!', {icon: 2});
            return false;
        }
        layer.confirm('确定要删除吗?', function () {
            let ids = repairIds.join(',');
            $.ajax({
                url: '/api/deleteRepairByIds/'+ids,
                type: 'DELETE',
                success: function (response) {
                    layer.msg('删除成功!', {icon: 1});
                    tableIns.reload();
                }
            });
        });
    });

    // 查询按钮点击事件
    $('.repairListSearch').on('click', function () {
        table.reload("repairList", {
            where: form.val('searchRepairListForm')
        })
    });

    // 打开报修管理详情
    function repairDetail(data) {
        var index = layer.open({
            title: '报修管理详情',
            type: 2,
            content: "/page/repair>repairDetail",
            success: function (layero, index) {
                var body = layer.getChildFrame('body', index);
                body.find('#repairId').val(data.id);
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

    // 新增报修管理
    function addRepair(edit) {
        var index = layer.open({
            title: "报修管理",
            type: 2,
            content: "/page/repair>repairAdd",
            success: function (layero, index) {
                var body = layer.getChildFrame('body', index);
                body.find('#repairId').val(edit.id);
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

    // 维修完成按钮
    function repairDone(data){
        let repairId = data.id;
        layer.confirm('确定进行维修完成操作吗?', {icon: 1, title: '提示'}, function(){
            $.ajax({
                url: '/api/serviceDone',
                type: 'POST',
                data: {repairId: repairId},
                success: function(response){
                    if(response.code == 0){
                        layer.msg('操作成功!', {time: 1000, icon: 1}, function(){
                            window.location.reload();
                        });

                    }else{
                        layer.msg(response.msg, {time: 1000, icon: 2});
                    }
                }
            });
        })
    }
});
