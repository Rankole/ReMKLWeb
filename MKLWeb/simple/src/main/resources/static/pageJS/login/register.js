layui.use(['form', 'laydate', 'jquery'], function () {
    var form = layui.form,
        laydate = layui.laydate
        $ = layui.jquery;

    laydate.render({
        elem: '#inShcoolDate' //指定元素
    });

    //监听提交
    form.on('submit(formDemo)', function (data) {
        fieldData = data.field;
        var password = $('#password').val();
        var passwordAgain = $('#passwordAgain').val();
        if(password != passwordAgain){
            layer.msg('两次输入密码不一致!', {icon: 2});
            return false;
        }
        $.ajax({
            url: '/api/register',
            type: 'POST',
            data: data.field,
            success: function(response){
                // 注册成功
                if(response.code == 0){
                    layer.msg('注册成功!', {
                        icon: 1,
                        time: 2000 // 2秒关闭
                    }, function(){
                        //先得到当前iframe层的索引
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    });
                }else{
                    // 注册失败
                    layer.msg(response.msg, {icon: 2});
                }
            }
        });
        return false;
    });

    // 发送验证码
    $('.sendCode').on('click', function(){
        let email = $('#userName').val();
        $.ajax({
            url: '/api/sendEmail',
            type: 'POST',
            data: {toMailUser: email},
            success: function(response){
                if(response.code == 0){
                    layer.msg('发送成功, 请前往您的邮箱查看!');
                }else{
                    layer.msg(response.msg, {icon: 2});
                }
            }
        });
    })
});
