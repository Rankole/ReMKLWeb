layui.use(['form', 'laydate', 'jquery', 'layer', 'upload'], function () {
    var form = layui.form,
        laydate = layui.laydate,
        $ = layui.jquery,
        upload = layui.upload,
        layer = layui.layer;

    // 获取maintenanceWorkerId
    let maintenanceWorkerId = $('#maintenanceWorkerId').val()
    if(maintenanceWorkerId){
        $.ajax({
            url: '/api/getMaintenanceWorkerById',
            type: 'GET',
            data: {maintenanceWorkerId: maintenanceWorkerId},
            success: function(response){
                form.val('maintenanceWorkerForm', response.data);
                $('#submitMaintenanceWorker').attr('lay-filter', 'maintenanceWorkerEditSubmit')
                form.render();
            }
        });
    }

    //监听提交
    form.on('submit(maintenanceWorkerAddSubmit)', function (data) {
        var formData = data.field;
        layer.confirm('确定要提交吗?', {icon: 1, title: '提示'}, function (index) {
            $.ajax({
                url: '/api/addMaintenanceWorker',
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
    form.on('submit(maintenanceWorkerEditSubmit)', function (data) {
        var formData = data.field;
        formData.id = maintenanceWorkerId;
        layer.confirm('确定要修改吗?', {icon: 1, title: '提示'}, function (index) {
            $.ajax({
                url: '/api/updateMaintenanceWorker',
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
