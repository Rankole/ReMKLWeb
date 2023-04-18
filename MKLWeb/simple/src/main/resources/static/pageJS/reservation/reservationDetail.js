layui.use(['form', 'laydate', 'jquery', 'layer', 'upload'], function () {
    var form = layui.form,
        laydate = layui.laydate,
        $ = layui.jquery,
        upload = layui.upload,
        layer = layui.layer;

    let reservationId = '';
    reservationId = $('#reservationId').val();
    $.ajax({
        url: '/api/getReservationById',
        type: 'GET',
        data: {reservationId: reservationId},
        success: function(response){
            let status = response.data.status;
            switch(status){
                case 0:
                    response.data.status = '预约中';
                    break;
                case 1:
                    response.data.status = '运行中';
                    break;
                case 2:
                    response.data.status = '运行结束';
                    break;
                case 3:
                    response.data.status = '逾期未使用';
                    break;

            }
            form.val('reservationDetailForm', response.data);
            form.render();
            disableForm('reservationDetailForm');
        }
    });

    // 禁用form表单得输入功能
    function disableForm (formFilter){
        var formObj = $('form[lay-filter='+formFilter+']').find('[name]');
        $.each(formObj, function(index, obj){
            $(obj).attr('disabled', '');
        });
    }

    // 启用form表单得输入功能
    function removeDisabled(formFilter){
        var formObj = $('form[lay-filter='+formFilter+']').find('[name]');
        $.each(formObj, function(index, obj){
            $(obj).removeAttr('disabled', '');
        });
    }
});


