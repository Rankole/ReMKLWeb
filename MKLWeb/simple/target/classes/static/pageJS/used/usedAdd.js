layui.use(['form', 'laydate', 'jquery', 'layer', 'upload'], function () {
    var form = layui.form,
        laydate = layui.laydate,
        $ = layui.jquery,
        upload = layui.upload,
        layer = layui.layer;

    // 获取usedId
    let usedId = $('#usedId').val()
    if(usedId){
        $.ajax({
            url: '/api/getUsedById',
            type: 'GET',
            data: {usedId: usedId},
            success: function(response){
                form.val('usedForm', response.data);
                $('#submitUsed').attr('lay-filter', 'usedEditSubmit')
                form.render();
            }
        });
    }

    //监听提交
    form.on('submit(usedAddSubmit)', function (data) {
        var formData = data.field;
        layer.confirm('确定要提交吗?', {icon: 1, title: '提示'}, function (index) {
            $.ajax({
                url: '/api/addUsed',
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
    form.on('submit(usedEditSubmit)', function (data) {
        var formData = data.field;
        formData.id = usedId;
        layer.confirm('确定要修改吗?', {icon: 1, title: '提示'}, function (index) {
            $.ajax({
                url: '/api/updateUsed',
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
