layui.use(['form', 'laydate', 'jquery', 'upload', 'table', 'layer'], function () {
    var form = layui.form,
        table = layui.table,
        laydate = layui.laydate,
        layer = layui.layer,
        $ = layui.jquery,
        upload = layui.upload;

    // 初始化我的积分商品表格
    var tableIns = table.render({
        elem: '#userGiftList',
        url: '/api/getUserGiftList',
        cellMinWidth: 95,
        id: "userGiftList",
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
            {field: 'giftName', title: '商品名称', align: 'center'},
            {field: 'userName', title: '用户', align: 'center'},
            {field: 'amount', title: '兑换数量', align: 'center'},
            {field: 'giftDesc', title: '商品说明', align: 'center'},
            {field: 'giftScore', title: '兑换所需积分', align: 'center'},
            {field: 'giftUsedName', title: '可以使用品牌名称', align: 'center'},
            {title: '操作', width: 170, templet: '#userGiftListBar', fixed: "right", align: "center"}
        ]]
    });

    // 我的积分商品列表工具栏事件监听
    table.on('tool(userGiftList)', function (obj) {
        var event = obj.event,
            data = obj.data;
        if (event == 'userGift_detail') {
            userGiftDetail(data);
        }
    });

    let userGiftIds = [];
    // 表格的复选框选择事件
    table.on('checkbox(userGiftList)', function (obj) {
        if (obj.type == 'one') {
            if (obj.checked) {
                // 如果复选框是选中事件, 将id加入到参数数组中
                userGiftIds.push(obj.data.id);
            } else {
                // 如果复选框是取消选中事件, 将id从参数数组中删除
                userGiftIds.remove(obj.data.id);
            }
        } else if (obj.type == 'all') {
            // 如果是全选, 将全选的id从参数数组中加入或删除
            var checkStatus = table.checkStatus('userGiftList');
            var data = checkStatus.data;
            $.each(data, function (index, dataObj) {
                if (obj.checked) {
                    userGiftIds.push(dataObj.id);
                } else {
                    userGiftIds.remove(dataObj.id);
                }
            })
        }

    })

    // 新增userGift信息按钮点击事件
    $('.addUserGift_btn').on('click', function () {
        addUserGift();
    });
    // 删除userGift信息按钮点击事件
    $('.deleteUserGift_btn').on('click', function () {
        if(userGiftIds.length == 0){
            layer.msg('请至少选择一条记录进行删除!', {icon: 2});
            return false;
        }
        layer.confirm('确定要删除吗?', function () {
            let ids = userGiftIds.join(',');
            $.ajax({
                url: '/api/deleteUserGiftByIds/'+ids,
                type: 'DELETE',
                success: function (response) {
                    layer.msg('删除成功!', {icon: 1});
                    tableIns.reload();
                }
            });
        });
    });

    // 查询按钮点击事件
    $('.userGiftListSearch').on('click', function () {
        table.reload("userGiftList", {
            where: form.val('searchUserGiftListForm')
        })
    });

    // 打开我的积分商品详情
    function userGiftDetail(data) {
        var index = layer.open({
            title: '我的积分商品详情',
            type: 2,
            content: "/page/userGift>userGiftDetail",
            success: function (layero, index) {
                var body = layer.getChildFrame('body', index);
                body.find('#userGiftId').val(data.id);
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

    // 新增我的积分商品
    function addUserGift(edit) {
        var index = layer.open({
            title: "我的积分商品",
            type: 2,
            content: "/page/userGift>userGiftAdd",
            success: function (layero, index) {
                var body = layer.getChildFrame('body', index);
                body.find('#userGiftId').val(edit.id);
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
