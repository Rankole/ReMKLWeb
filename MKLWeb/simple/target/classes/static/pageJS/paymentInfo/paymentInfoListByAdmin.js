layui.use(['form', 'laydate', 'jquery', 'upload', 'table', 'layer'], function () {
    var form = layui.form,
        table = layui.table,
        laydate = layui.laydate,
        layer = layui.layer,
        $ = layui.jquery,
        upload = layui.upload;

    // 初始化交易明细表格
    var tableIns = table.render({
        elem: '#paymentInfoList',
        url: '/api/getPaymentInfoList',
        cellMinWidth: 95,
        id: "paymentInfoList",
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
            {field: 'userName', title: '用户名', align: 'center'},
            {field: 'paymentExplain', title: '类型说明', align: 'center'},
            {field: 'money', sort: true, title: '金额', align: 'center'},
            {field: 'createDatetime', sort: true, title: '发生时间', align: 'center'},
            {title: '操作', width: 170, templet: '#paymentInfoListBar', fixed: "right", align: "center"}
        ]]
    });

    // 交易明细列表工具栏事件监听
    table.on('tool(paymentInfoList)', function (obj) {
        var event = obj.event,
            data = obj.data;
        if (event == 'paymentInfo_detail') {
            paymentInfoDetail(data);
        } else if (event == 'paymentInfo_delete') {
            deletePaymentInfo(data);
        }
    });

    let paymentInfoIds = [];
    // 表格的复选框选择事件
    table.on('checkbox(paymentInfoList)', function (obj) {
        if (obj.type == 'one') {
            if (obj.checked) {
                // 如果复选框是选中事件, 将id加入到参数数组中
                paymentInfoIds.push(obj.data.id);
            } else {
                // 如果复选框是取消选中事件, 将id从参数数组中删除
                paymentInfoIds.remove(obj.data.id);
            }
        } else if (obj.type == 'all') {
            // 如果是全选, 将全选的id从参数数组中加入或删除
            var checkStatus = table.checkStatus('paymentInfoList');
            var data = checkStatus.data;
            $.each(data, function (index, dataObj) {
                if (obj.checked) {
                    paymentInfoIds.push(dataObj.id);
                } else {
                    paymentInfoIds.remove(dataObj.id);
                }
            })
        }

    })

    // 充值按钮点击事件
    $('.addPaymentInfo_btn').on('click', function () {
        addPaymentInfo();
    });
    // 删除paymentInfo信息按钮点击事件
    $('.deletePaymentInfo_btn').on('click', function () {
        if (paymentInfoIds.length == 0) {
            layer.msg('请至少选择一条记录进行删除!', {icon: 2});
            return false;
        }
        layer.confirm('确定要删除吗?', function () {
            let ids = paymentInfoIds.join(',');
            $.ajax({
                url: '/api/deletePaymentInfoByIds/' + ids,
                type: 'DELETE',
                success: function (response) {
                    layer.msg('删除成功!', {icon: 1});
                    tableIns.reload();
                }
            });
        });
    });

    // 查询按钮点击事件
    $('.paymentInfoListSearch').on('click', function () {
        table.reload("paymentInfoList", {
            where: form.val('searchPaymentInfoListForm')
        })
    });

    // 打开交易明细详情
    function paymentInfoDetail(data) {
        var index = layer.open({
            title: '交易明细详情',
            type: 2,
            content: "/page/paymentInfo>paymentInfoDetail",
            success: function (layero, index) {
                var body = layer.getChildFrame('body', index);
                body.find('#paymentInfoId').val(data.id);
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

    // 弹出充值页面
    function addPaymentInfo() {

        layer.prompt({
            formType: 0,
            value: 1,
            title: '请输入充值金额',
            area: ['150', '150'] //自定义文本域宽高
        }, function (value, index, elem) {
            var isNum = /^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/;
            if (!isNum.test(value)) {
                layer.msg('金额数值非法!', {icon: 2, time: 1200});
                return;
            }
            var openIndex = layer.open({
                type: 1,
                title: '支付信息',
                skin: 'layui-layer-rim layer-center', //加上边框
                area: ['300px', '500px'], //宽高
                btn: ['支付完成'],
                btnAlign: 'c',
                btn1: function () {
                    payment({money: value});
                    window.location.reload();
                },
                content: '<div style="text-align:center"><img src="/images/alipay.jpg" width="75%" height="85%"/> </br>' +
                    '充值金额: ' + value + '元 </br></div>'
            });
        });
    }

    // 充值
    function payment(data) {
        $.ajax({
            url: '/api/payment',
            type: 'POST',
            async: false,
            data: data,
            success: function (response) {
                if (response.code == 0) {
                    layer.msg('充值成功!', {icon: 1, time: 1000});
                } else {
                    layer.msg(response.msg, {icon: 2, time: 1000});
                }
            }
        });
    }
});
