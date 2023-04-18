layui.use(['form', 'laydate', 'jquery', 'layer', 'upload'], function () {
    var form = layui.form,
        laydate = layui.laydate,
        $ = layui.jquery,
        upload = layui.upload,
        layer = layui.layer;

    // 获取giftId
    let giftId = $('#giftId').val()
    if(giftId){
        $.ajax({
            url: '/api/getGiftById',
            type: 'GET',
            data: {giftId: giftId},
            success: function(response){
                form.val('giftForm', response.data);
                $('#submitGift').attr('lay-filter', 'giftEditSubmit')
                form.render();
            }
        });
    }

    initBrand();

    // 初始化品牌下拉框
    function initBrand(){
        let brandContent = '';
        $.ajax({
            url: '/api/getAllBrand',
            type: 'GET',
            data: {},
            async: false,
            success: function(response) {
                if(response.code == 0){
                    $.each(response.data, function(index, obj){
                        brandContent += '<option value="'+obj.id+'">'+obj.name+'</option>\n'
                    });
                }
                $('#usedId').html(brandContent);
                form.render();
            }
        });
    }

    //监听提交
    form.on('submit(giftAddSubmit)', function (data) {
        var formData = data.field;
        layer.confirm('确定要提交吗?', {icon: 1, title: '提示'}, function (index) {
            $.ajax({
                url: '/api/addGift',
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
    form.on('submit(giftEditSubmit)', function (data) {
        var formData = data.field;
        formData.id = giftId;
        layer.confirm('确定要修改吗?', {icon: 1, title: '提示'}, function (index) {
            $.ajax({
                url: '/api/updateGift',
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
