layui.use(['form', 'laydate', 'jquery', 'layer', 'upload'], function () {
    var form = layui.form,
        laydate = layui.laydate,
        $ = layui.jquery,
        upload = layui.upload,
        layer = layui.layer;

    // 获取userId
    let userId = $('#userId').val()
    if(userId){
        $.ajax({
            url: '/api/getUserById',
            type: 'GET',
            data: {userId: userId},
            success: function(response){
                form.val('userForm', response.data);
                $('#submitUser').attr('lay-filter', 'userEditSubmit')
                form.render();
            }
        });
    }

    //监听提交
    form.on('submit(userAddSubmit)', function (data) {
        var formData = data.field;
        layer.confirm('确定要提交吗?', {icon: 1, title: '提示'}, function (index) {
            $.ajax({
                url: '/api/register',
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
    form.on('submit(userEditSubmit)', function (data) {
        var formData = data.field;
        formData.id = userId;
        layer.confirm('确定要修改吗?', {icon: 1, title: '提示'}, function (index) {
            $.ajax({
                url: '/api/updateUser',
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
