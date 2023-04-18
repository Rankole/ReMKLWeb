layui.use(['form', 'laydate', 'jquery', 'upload', 'table', 'layer'], function () {
    var form = layui.form,
        table = layui.table,
        laydate = layui.laydate,
        layer = layui.layer,
        $ = layui.jquery,
        upload = layui.upload;

    // 初始化洗衣机模式表格
    var tableIns = table.render({
        elem: '#washingModelList',
        url: '/api/getWashingModelList',
        cellMinWidth: 95,
        id: "washingModelList",
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
            {field: 'brandTypeName', title: '型号名称', align: 'center'},
            {field: 'modelName', title: '模式名称', align: 'center'},
            {field: 'runTime', title: '洗衣时长(单位:分钟)', align: 'center'},
            {title: '操作', width: 170, templet: '#washingModelListBar', fixed: "right", align: "center"}
        ]]
    });

    // 洗衣机模式列表工具栏事件监听
    table.on('tool(washingModelList)', function (obj) {
        var event = obj.event,
            data = obj.data;
        if (event == 'washingModel_detail') {
            washingModelDetail(data);
        }else if(event == 'washingModel_edit'){
            addWashingModel(data);
        }
    });

    let washingModelIds = [];
    // 表格的复选框选择事件
    table.on('checkbox(washingModelList)', function (obj) {
        if (obj.type == 'one') {
            if (obj.checked) {
                // 如果复选框是选中事件, 将id加入到参数数组中
                washingModelIds.push(obj.data.id);
            } else {
                // 如果复选框是取消选中事件, 将id从参数数组中删除
                washingModelIds.remove(obj.data.id);
            }
        } else if (obj.type == 'all') {
            // 如果是全选, 将全选的id从参数数组中加入或删除
            var checkStatus = table.checkStatus('washingModelList');
            var data = checkStatus.data;
            $.each(data, function (index, dataObj) {
                if (obj.checked) {
                    washingModelIds.push(dataObj.id);
                } else {
                    washingModelIds.remove(dataObj.id);
                }
            })
        }

    })

    // 新增washingModel信息按钮点击事件
    $('.addWashingModel_btn').on('click', function () {
        addWashingModel();
    });
    // 删除washingModel信息按钮点击事件
    $('.deleteWashingModel_btn').on('click', function () {
        if(washingModelIds.length == 0){
            layer.msg('请至少选择一条记录进行删除!', {icon: 2});
            return false;
        }
        layer.confirm('确定要删除吗?', function () {
            let ids = washingModelIds.join(',');
            $.ajax({
                url: '/api/deleteWashingModelByIds/'+ids,
                type: 'DELETE',
                success: function (response) {
                    layer.msg('删除成功!', {icon: 1});
                    tableIns.reload();
                }
            });
        });
    });

    // 查询按钮点击事件
    $('.washingModelListSearch').on('click', function () {
        table.reload("washingModelList", {
            where: form.val('searchWashingModelListForm')
        })
    });

    // 打开洗衣机模式详情
    function washingModelDetail(data) {
        var index = layer.open({
            title: '洗衣机模式详情',
            type: 2,
            content: "/page/washingModel>washingModelDetail",
            success: function (layero, index) {
                var body = layer.getChildFrame('body', index);
                body.find('#washingModelId').val(data.id);
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

    // 新增洗衣机模式
    function addWashingModel(edit) {
        var index = layer.open({
            title: "洗衣机模式",
            type: 2,
            content: "/page/washingModel>washingModelAdd",
            success: function (layero, index) {
                var body = layer.getChildFrame('body', index);
                body.find('#washingModelId').val(edit.id);
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
