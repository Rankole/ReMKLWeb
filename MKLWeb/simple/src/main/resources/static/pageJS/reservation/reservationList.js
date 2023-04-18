layui.use(['form', 'laydate', 'jquery', 'upload', 'table', 'layer'], function () {
    var form = layui.form,
        table = layui.table,
        laydate = layui.laydate,
        layer = layui.layer,
        $ = layui.jquery,
        upload = layui.upload;

    // 初始化预约管理表格
    var tableIns = table.render({
        elem: '#reservationList',
        url: '/api/getReservationList',
        cellMinWidth: 95,
        id: "reservationList",
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
            {field: 'washingMachineId', hide: true},
            {field: 'washingModelId', hide: true},
            {field: 'runTimes', title: '洗衣时长(单位:分钟)', align: 'center'},
            {field: 'reservationDatetime', title: '预约时间', align: 'center'},
            {field: 'usedDatetime', title: '使用时间', align: 'center'},
            {field: 'status', title: '状态', align: 'center', templet: function(d){
                    switch (d.status) {
                        case 0:
                            return '<i class="layui-icon layui-icon-face-smile" style="color: #009688"></i> 预约中';
                            break;
                        case 3:
                            return '<i class="layui-icon layui-icon-face-cry" style="color: #FC5531"></i> 逾期未使用';
                            break;
                        case 2:
                            return '<i class="layui-icon layui-icon-praise" style="color: #FFB800"></i> 运行结束';
                            break;
                        case 1:
                            return '<i class="layui-icon layui-icon-console" style="color: #FF5722"></i> 运行中';
                            break;
                    }
            }},
            {title: '操作', width: 170, templet: '#reservationListBar', fixed: "right", align: "center"}
        ]]
    });

    // 预约管理列表工具栏事件监听
    table.on('tool(reservationList)', function (obj) {
        var event = obj.event,
            data = obj.data;
        if (event == 'reservation_detail') {
            reservationDetail(data);
        }else if(event == 'reservation_use'){
            useReservationWashing(data);
        }
    });


    // 查询按钮点击事件
    $('.reservationListSearch').on('click', function () {
        table.reload("reservationList", {
            where: form.val('searchReservationListForm')
        })
    });

    // 使用预约的洗衣机
    function useReservationWashing(data){
        layer.confirm('确定使用这台洗衣机吗?', {icon: 1, title: '提示'}, function() {
            $.ajax({
                url: '/api/usedWashingMachine',
                type: 'POST',
                data: {
                    washingMachineId: data.washingMachineId,
                    washingModelId: data.washingModelId,
                    reservationId: data.id
                },
                success: function (response) {
                    if(response.code == 0){
                        layer.msg('操作成功!', {time: 1000}, function(){
                            window.location.reload();
                        })
                    }
                }
            });
        });
        }

    // 打开预约管理详情
    function reservationDetail(data) {
        var index = layer.open({
            title: '预约管理详情',
            type: 2,
            content: "/page/reservation>reservationDetail",
            success: function (layero, index) {
                var body = layer.getChildFrame('body', index);
                body.find('#reservationId').val(data.id);
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
