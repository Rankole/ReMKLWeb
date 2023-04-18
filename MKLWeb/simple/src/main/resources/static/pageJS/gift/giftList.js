layui.use(['form', 'laydate', 'jquery', 'upload', 'table', 'layer'], function () {
    var form = layui.form,
        table = layui.table,
        laydate = layui.laydate,
        layer = layui.layer,
        $ = layui.jquery,
        upload = layui.upload;

    // 初始化积分商品表格
    var tableIns = table.render({
        elem: '#giftList',
        url: '/api/getGiftList',
        cellMinWidth: 95,
        id: "giftList",
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
            {field: 'name', title: '商品名称', align: 'center'},
            {field: 'price', title: '数量', align: 'center'},
            {field: 'productDesc', title: '商品说明', align: 'center'},
            {field: 'score', title: '兑换所需积分', align: 'center'},
            {field: 'usedName', title: '可使用洗衣机品牌', align: 'center'},
            {title: '操作', width: 170, templet: '#giftListBar', fixed: "right", align: "center"}
        ]]
    });

    // 积分商品列表工具栏事件监听
    table.on('tool(giftList)', function (obj) {
        var event = obj.event,
            data = obj.data;
        if (event == 'gift_detail') {
            giftDetail(data);
        }else if(event == 'gift_edit'){
            addGift(data);
        }
    });

    let giftIds = [];
    // 表格的复选框选择事件
    table.on('checkbox(giftList)', function (obj) {
        if (obj.type == 'one') {
            if (obj.checked) {
                // 如果复选框是选中事件, 将id加入到参数数组中
                giftIds.push(obj.data.id);
            } else {
                // 如果复选框是取消选中事件, 将id从参数数组中删除
                giftIds.remove(obj.data.id);
            }
        } else if (obj.type == 'all') {
            // 如果是全选, 将全选的id从参数数组中加入或删除
            var checkStatus = table.checkStatus('giftList');
            var data = checkStatus.data;
            $.each(data, function (index, dataObj) {
                if (obj.checked) {
                    giftIds.push(dataObj.id);
                } else {
                    giftIds.remove(dataObj.id);
                }
            })
        }

    })

    // 新增gift信息按钮点击事件
    $('.addGift_btn').on('click', function () {
        addGift();
    });
    // 删除gift信息按钮点击事件
    $('.deleteGift_btn').on('click', function () {
        if(giftIds.length == 0){
            layer.msg('请至少选择一条记录进行删除!', {icon: 2});
            return false;
        }
        layer.confirm('确定要删除吗?', function () {
            let ids = giftIds.join(',');
            $.ajax({
                url: '/api/deleteGiftByIds/'+ids,
                type: 'DELETE',
                success: function (response) {
                    layer.msg('删除成功!', {icon: 1});
                    tableIns.reload();
                }
            });
        });
    });

    // 查询按钮点击事件
    $('.giftListSearch').on('click', function () {
        table.reload("giftList", {
            where: form.val('searchGiftListForm')
        })
    });

    // 打开积分商品详情
    function giftDetail(data) {
        var index = layer.open({
            title: '积分商品详情',
            type: 2,
            content: "/page/gift>giftDetail",
            success: function (layero, index) {
                var body = layer.getChildFrame('body', index);
                body.find('#giftId').val(data.id);
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

    // 新增积分商品
    function addGift(edit) {
        var index = layer.open({
            title: "积分商品",
            type: 2,
            content: "/page/gift>giftAdd",
            success: function (layero, index) {
                var body = layer.getChildFrame('body', index);
                body.find('#giftId').val(edit.id);
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
