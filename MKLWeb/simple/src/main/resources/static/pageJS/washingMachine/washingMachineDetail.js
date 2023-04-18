layui.use(['form', 'laydate', 'jquery', 'layer', 'upload'], function () {
    var form = layui.form,
        laydate = layui.laydate,
        $ = layui.jquery,
        upload = layui.upload,
        layer = layui.layer;

    let washingMachineId = '';
    washingMachineId = $('#washingMachineId').val();
    $.ajax({
        url: '/api/getWashingMachineById',
        type: 'GET',
        data: {washingMachineId: washingMachineId},
        success: function(response){
            let status = response.data.status;
            switch(status){
                case 0:
                    response.data.status = '待使用';
                    break;
                case 1:
                    response.data.status = '已预约';
                    break;
                case 2:
                    response.data.status = '报修中';
                    break;
                case 3:
                    response.data.status = '使用中';
                    break;
            }
            form.val('washingMachineDetailForm', response.data);
            form.render();
            disableForm('washingMachineDetailForm');
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


