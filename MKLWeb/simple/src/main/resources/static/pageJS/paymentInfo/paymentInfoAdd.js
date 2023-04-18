layui.use(['form', 'laydate', 'jquery', 'layer', 'upload'], function () {
    var form = layui.form,
        laydate = layui.laydate,
        $ = layui.jquery,
        upload = layui.upload,
        layer = layui.layer;

    // 获取paymentInfoId
    let paymentInfoId = $('#paymentInfoId').val()
    if(paymentInfoId){
        $.ajax({
            url: '/api/getPaymentInfoById',
            type: 'GET',
            data: {paymentInfoId: paymentInfoId},
            success: function(response){
                form.val('paymentInfoForm', response.data);
                $('#submitPaymentInfo').attr('lay-filter', 'paymentInfoEditSubmit')
                form.render();
            }
        });
    }

    //监听提交
    form.on('submit(paymentInfoAddSubmit)', function (data) {
        var formData = data.field;
        layer.confirm('确定要提交吗?', {icon: 1, title: '提示'}, function (index) {
            $.ajax({
                url: '/api/addPaymentInfo',
                type: 'POST',
                data: formData,
                success: function (response) {
                    if (response.code == 0) {
                        layer.msg('提交成功!', {icon: 1});
                        layer.close(index);
                        window.parent.location.reload();
                    } else {
                        layer.msg(response.msg, {icon: 2});
                    }
                }
            });
        });

        return false;
    });

    //监听修改
    form.on('submit(paymentInfoEditSubmit)', function (data) {
        var formData = data.field;
        formData.id = paymentInfoId;
        layer.confirm('确定要修改吗?', {icon: 1, title: '提示'}, function (index) {
            $.ajax({
                url: '/api/updatePaymentInfo',
                type: 'POST',
                data: formData,
                success: function (response) {
                    if (response.code == 0) {
                        layer.msg('修改成功!', {icon: 1});
                        layer.close(index);
                        window.parent.location.reload();
                    } else {
                        layer.msg(response.msg, {icon: 2});
                    }
                }
            });
        });

        return false;
    });
});
