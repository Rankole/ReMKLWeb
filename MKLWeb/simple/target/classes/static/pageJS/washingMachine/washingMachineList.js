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
            {type: 'checkbox'},
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
            {field: 'reservationUserName', title: '预约人用户名', align: 'center'},
            {field: 'usedUserName', title: '使用人用户名', align: 'center'},
            {field: 'repairUserName', title: '报修用户名', align: 'center'},
            {field: 'repairRemark', title: '报修备注', align: 'center'},
            {title: '操作', width: 170, templet: '#washingMachineListBar', fixed: "right", align: "center"}
        ]]
    });

    // 洗衣机信息列表工具栏事件监听
    table.on('tool(washingMachineList)', function (obj) {
        var event = obj.event,
            data = obj.data;
        if (event == 'washingMachine_detail') {
            washingMachineDetail(data);
        }else if(event == 'washingMachine_edit'){
            addWashingMachine(data);
        }
    });

    let washingMachineIds = [];
    // 表格的复选框选择事件
    table.on('checkbox(washingMachineList)', function (obj) {
        if (obj.type == 'one') {
            if (obj.checked) {
                // 如果复选框是选中事件, 将id加入到参数数组中
                washingMachineIds.push(obj.data.id);
            } else {
                // 如果复选框是取消选中事件, 将id从参数数组中删除
                washingMachineIds.remove(obj.data.id);
            }
        } else if (obj.type == 'all') {
            // 如果是全选, 将全选的id从参数数组中加入或删除
            var checkStatus = table.checkStatus('washingMachineList');
            var data = checkStatus.data;
            $.each(data, function (index, dataObj) {
                if (obj.checked) {
                    washingMachineIds.push(dataObj.id);
                } else {
                    washingMachineIds.remove(dataObj.id);
                }
            })
        }

    })

    // 新增washingMachine信息按钮点击事件
    $('.addWashingMachine_btn').on('click', function () {
        addWashingMachine();
    });
    // 删除washingMachine信息按钮点击事件
    $('.deleteWashingMachine_btn').on('click', function () {
        if(washingMachineIds.length == 0){
            layer.msg('请至少选择一条记录进行删除!', {icon: 2});
            return false;
        }
        layer.confirm('确定要删除吗?', function () {
            let ids = washingMachineIds.join(',');
            $.ajax({
                url: '/api/deleteWashingMachineByIds/'+ids,
                type: 'DELETE',
                success: function (response) {
                    layer.msg('删除成功!', {icon: 1});
                    tableIns.reload();
                }
            });
        });
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

    // 新增洗衣机信息
    function addWashingMachine(edit) {
        var index = layer.open({
            title: "洗衣机信息",
            type: 2,
            content: "/page/washingMachine>washingMachineAdd",
            success: function (layero, index) {
                var body = layer.getChildFrame('body', index);
                body.find('#washingMachineId').val(edit.id);
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
