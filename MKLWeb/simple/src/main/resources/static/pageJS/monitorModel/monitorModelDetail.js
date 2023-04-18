layui.use(['form', 'laydate', 'jquery', 'layer', 'upload'], function () {
    var form = layui.form,
        laydate = layui.laydate,
        $ = layui.jquery,
        upload = layui.upload,
        layer = layui.layer;

    let monitorModelId = '';
    monitorModelId = $('#monitorModelId').val();
    $.ajax({
        url: '/api/getMonitorModelById',
        type: 'GET',
        data: {monitorModelId: monitorModelId},
        success: function(response){
            form.val('monitorModelDetailForm', response.data);
            form.render();
            disableForm('monitorModelDetailForm');
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


