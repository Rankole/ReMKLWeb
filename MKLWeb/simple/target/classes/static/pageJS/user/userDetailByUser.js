layui.use(['form', 'laydate', 'jquery', 'layer', 'upload'], function () {
    var form = layui.form,
        laydate = layui.laydate,
        $ = layui.jquery,
        upload = layui.upload,
        layer = layui.layer;

    let userId = '';
    userId = $('#userId').val();

    function initUserInfo(){
        $.ajax({
            url: '/api/getUserById',
            type: 'GET',
            data: {userId: userId},
            success: function(response){
                form.val('userDetailForm', response.data);
                disableForm('userDetailForm');
                form.render();
            }
        });
    }

    initUserInfo();

    form.on('submit(editUserInfo)', function(data){
        $('[name="userName"]').removeAttr('disabled', '');
        $('[name="password"]').removeAttr('disabled', '');
        $('[name="phone"]').removeAttr('disabled', '');
        $('[name="address"]').removeAttr('disabled', '');
        let editHtml = '<button class="layui-btn" lay-submit lay-filter="submitUserInfo">提交</button> <button class="layui-btn layui-btn-normal" lay-submit lay-filter="cancel">取消</button>'
        $('#editUserInfoDiv').html(editHtml);
        form.render();
        return false;
    });

    form.on('submit(cancel)', function(data){
        disableForm('userDetailForm');
        form.render();
        return false;
    });

    form.on('submit(submitUserInfo)', function(data){
        var formData = data.field;
        formData.id = userId;
        layer.confirm('确定要修改吗?', {icon: 1, title: '提示'}, function (index) {
            $.ajax({
                url: '/api/updateUser',
                type: 'POST',
                data: formData,
                async: false,
                success: function (response) {
                    if (response.code == 0) {
                        layer.msg('修改成功!', {icon: 1});
                        layer.close(index);
                        let editHtml = '<button id="editUserInfo" class="layui-btn" lay-submit lay-filter="editUserInfo">修改</button>';
                        $('#editUserInfoDiv').html(editHtml);
                        initUserInfo();
                    } else {
                        layer.msg(response.msg, {icon: 2});
                    }
                }
            })});
        return false;
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


