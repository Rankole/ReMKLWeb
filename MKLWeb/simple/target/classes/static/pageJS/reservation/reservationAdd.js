layui.use(['form', 'laydate', 'jquery', 'layer', 'upload'], function () {
    var form = layui.form,
        laydate = layui.laydate,
        $ = layui.jquery,
        upload = layui.upload,
        layer = layui.layer;

    // 获取reservationId
    let reservationId = $('#reservationId').val()
    if(reservationId){
        $.ajax({
            url: '/api/getReservationById',
            type: 'GET',
            data: {reservationId: reservationId},
            success: function(response){
                form.val('reservationForm', response.data);
                $('#submitReservation').attr('lay-filter', 'reservationEditSubmit')
                form.render();
            }
        });
    }

    //监听提交
    form.on('submit(reservationAddSubmit)', function (data) {
        var formData = data.field;
        layer.confirm('确定要提交吗?', {icon: 1, title: '提示'}, function (index) {
            $.ajax({
                url: '/api/addReservation',
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
    form.on('submit(reservationEditSubmit)', function (data) {
        var formData = data.field;
        formData.id = reservationId;
        layer.confirm('确定要修改吗?', {icon: 1, title: '提示'}, function (index) {
            $.ajax({
                url: '/api/updateReservation',
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
