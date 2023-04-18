layui.use(['form', 'laydate', 'jquery', 'layer', 'upload'], function () {
    var form = layui.form,
        laydate = layui.laydate,
        $ = layui.jquery,
        upload = layui.upload,
        layer = layui.layer;

    let usedId = '';
    usedId = $('#usedId').val();
    $.ajax({
        url: '/api/getUsedById',
        type: 'GET',
        data: {usedId: usedId},
        success: function(response){
            let status = response.data.status;
            switch(status){
                case 1:
                    response.data.status = '运行中';
                    break;
                case 2:
                    response.data.status = '运行结束';
                    break;
            }
            form.val('usedDetailForm', response.data);
            form.render();
            disableForm('usedDetailForm');
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


