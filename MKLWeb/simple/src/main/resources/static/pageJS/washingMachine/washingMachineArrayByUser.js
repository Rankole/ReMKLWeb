layui.use(['form', 'laydate', 'jquery', 'upload', 'table', 'layer'], function () {
    var form = layui.form,
        table = layui.table,
        laydate = layui.laydate,
        layer = layui.layer,
        $ = layui.jquery,
        upload = layui.upload;

    // 初始化洗衣机信息表格
    var tableIns = table.render({
        elem: '#washingMachineList',
        url: '/api/getWashingMachineList',
        cellMinWidth: 95,
        id: "washingMachineList",
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
            {field: 'brandName', title: '品牌名称', align: 'center'},
            {field: 'typeName', title: '型号名称', align: 'center'},
            {field: 'price', title: '单价', align: 'center'},
            {field: 'number', title: '编号', align: 'center'},
            {field: 'status', title: '状态', align: 'center', templet: function (d) {
                    switch (d.status) {
                        case 0:
                            return '<i class="layui-icon layui-icon-face-smile" style="color: #009688"></i> 待使用';
                            break;
                        case 1:
                            return '<i class="layui-icon layui-icon-face-cry" style="color: #FC5531"></i> 已预约';
                            break;
                        case 2:
                            return '<i class="layui-icon layui-icon-face-surprised" style="color: #FFB800"></i> 报修中';
                            break;
                        case 3:
                            return '<i class="layui-icon layui-icon-console" style="color: #FF5722"></i> 使用中';
                            break;
                    }
                }},
            {title: '操作', width: 170, templet: '#washingMachineListBar', fixed: "right", align: "center"}
        ]]
    });

    // 洗衣机信息列表工具栏事件监听
    table.on('tool(washingMachineList)', function (obj) {
        var event = obj.event,
            data = obj.data;
        if (event == 'washingMachine_detail') {
            washingMachineDetail(data);
        }
    });

    // 查询按钮点击事件
    $('.washingMachineListSearch').on('click', function () {
        table.reload("washingMachineList", {
            where: form.val('searchWashingMachineListForm')
        })
    });

    // 打开洗衣机信息详情
    function washingMachineDetail(data) {
        var index = layer.open({
            title: '洗衣机信息详情',
            type: 2,
            content: "/page/washingMachine>washingMachineDetail",
            success: function (layero, index) {
                var body = layer.getChildFrame('body', index);
                body.find('#washingMachineId').val(data.id);
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
