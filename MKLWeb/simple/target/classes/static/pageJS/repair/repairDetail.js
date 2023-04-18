layui.use(['form', 'laydate', 'jquery', 'layer', 'upload'], function () {
    var form = layui.form,
        laydate = layui.laydate,
        $ = layui.jquery,
        upload = layui.upload,
        layer = layui.layer;

    let repairId = '';
    repairId = $('#repairId').val();
    $.ajax({
        url: '/api/getRepairById',
        type: 'GET',
        data: {repairId: repairId},
        success: function(response){
            let status = response.data.status;
            switch (status){
                case 0:
                    response.data.status = '未维修';
                    break;
                case 1:
                    response.data.status = '已维修';
                    break;
            }
            form.val('repairDetailForm', response.data);
            form.render();
            disableForm('repairDetailForm');
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


