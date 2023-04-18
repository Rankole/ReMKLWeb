layui.use(['form', 'laydate', 'jquery', 'layer', 'upload'], function () {
    var form = layui.form,
        laydate = layui.laydate,
        $ = layui.jquery,
        upload = layui.upload,
        layer = layui.layer;

    // 获取userGiftId
    let userGiftId = $('#userGiftId').val()
    if(userGiftId){
        $.ajax({
            url: '/api/getUserGiftById',
            type: 'GET',
            data: {userGiftId: userGiftId},
            success: function(response){
                form.val('userGiftForm', response.data);
                $('#submitUserGift').attr('lay-filter', 'userGiftEditSubmit')
                form.render();
            }
        });
    }

    //监听提交
    form.on('submit(userGiftAddSubmit)', function (data) {
        var formData = data.field;
        layer.confirm('确定要提交吗?', {icon: 1, title: '提示'}, function (index) {
            $.ajax({
                url: '/api/addUserGift',
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
    form.on('submit(userGiftEditSubmit)', function (data) {
        var formData = data.field;
        formData.id = userGiftId;
        layer.confirm('确定要修改吗?', {icon: 1, title: '提示'}, function (index) {
            $.ajax({
                url: '/api/updateUserGift',
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
