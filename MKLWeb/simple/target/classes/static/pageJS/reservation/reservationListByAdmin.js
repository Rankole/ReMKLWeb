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
            {field: 'washingTimes', title: '洗衣时长(单位:分钟)', align: 'center'},
            {field: 'reservationDatetime', title: '预约时间', align: 'center'},
            {field: 'usedDatetime', title: '使用时间', align: 'center'},
            {field: 'status', title: '状态;0.预约中, 1.运行中, 2.运行结束, 3.逾期未使用', align: 'center'},
            {field: 'reserUserName', title: '预约者用户名', align: 'center'},
            {title: '操作', width: 170, templet: '#reservationListBar', fixed: "right", align: "center"}
        ]]
    });

    // 预约管理列表工具栏事件监听
    table.on('tool(reservationList)', function (obj) {
        var event = obj.event,
            data = obj.data;
        if (event == 'reservation_detail') {
            reservationDetail(data);
        }else if(event == 'reservation_edit'){
            addReservation(data);
        }
    });

    let reservationIds = [];
    // 表格的复选框选择事件
    table.on('checkbox(reservationList)', function (obj) {
        if (obj.type == 'one') {
            if (obj.checked) {
                // 如果复选框是选中事件, 将id加入到参数数组中
                reservationIds.push(obj.data.id);
            } else {
                // 如果复选框是取消选中事件, 将id从参数数组中删除
                reservationIds.remove(obj.data.id);
            }
        } else if (obj.type == 'all') {
            // 如果是全选, 将全选的id从参数数组中加入或删除
            var checkStatus = table.checkStatus('reservationList');
            var data = checkStatus.data;
            $.each(data, function (index, dataObj) {
                if (obj.checked) {
                    reservationIds.push(dataObj.id);
                } else {
                    reservationIds.remove(dataObj.id);
                }
            })
        }

    })

    // 新增reservation信息按钮点击事件
    $('.addReservation_btn').on('click', function () {
        addReservation();
    });
    // 删除reservation信息按钮点击事件
    $('.deleteReservation_btn').on('click', function () {
        if(reservationIds.length == 0){
            layer.msg('请至少选择一条记录进行删除!', {icon: 2});
            return false;
        }
        layer.confirm('确定要删除吗?', function () {
            let ids = reservationIds.join(',');
            $.ajax({
                url: '/api/deleteReservationByIds/'+ids,
                type: 'DELETE',
                success: function (response) {
                    layer.msg('删除成功!', {icon: 1});
                    tableIns.reload();
                }
            });
        });
    });

    // 查询按钮点击事件
    $('.reservationListSearch').on('click', function () {
        table.reload("reservationList", {
            where: form.val('searchReservationListForm')
        })
    });

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

    // 新增预约管理
    function addReservation(edit) {
        var index = layer.open({
            title: "预约管理",
            type: 2,
            content: "/page/reservation>reservationAdd",
            success: function (layero, index) {
                var body = layer.getChildFrame('body', index);
                body.find('#reservationId').val(edit.id);
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
