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
        if (event == 'gift_exchange') {
            exchangeProduct(data);
        } else  if (event == 'gift_detail') {
            giftDetail(data);
        }
    });


    // 查询按钮点击事件
    $('.giftListSearch').on('click', function () {
        table.reload("giftList", {
            where: form.val('searchGiftListForm')
        })
    });

    // 积分兑换商品
    function exchangeProduct(data) {
        layer.prompt({
            formType: 0,
            value: 1,
            title: '请输入兑换数量',
            area: ['150', '150'] //自定义文本域宽高
        }, function (value, index, elem) {
            if (!(/(^[1-9]\d*$)/.test(value))) {
                layer.msg("数量必须是正整数!", {icon: 2, time: 1000});
                return;
            }
            $.ajax({
                url: '/api/exchangeProduct',
                type: 'POST',
                data: {id: data.id, amount: value},
                success: function (response) {
                    if (response.code == 0) {
                        layer.msg('兑换成功!', {time: 1000, icon: 1});
                        layer.close(index);
                        window.parent.location.reload();
                    } else {
                        layer.msg(response.msg, {time: 1000, icon: 2});
                    }
                }
            });
        });
    }

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
});
