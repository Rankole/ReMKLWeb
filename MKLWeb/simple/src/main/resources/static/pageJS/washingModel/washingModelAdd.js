layui.use(['form', 'laydate', 'jquery', 'layer', 'upload'], function () {
    var form = layui.form,
        laydate = layui.laydate,
        $ = layui.jquery,
        upload = layui.upload,
        layer = layui.layer;

    initBrand();

    // 获取washingModelId
    let washingModelId = $('#washingModelId').val()
    if(washingModelId){
        $.ajax({
            url: '/api/getWashingModelById',
            type: 'GET',
            data: {washingModelId: washingModelId},
            success: function(response){
                form.val('washingModelForm', response.data);
                initBrandType(response.data.brandId);
                $('#submitWashingModel').attr('lay-filter', 'washingModelEditSubmit')
                form.render();
            }
        });
    }



    //监听提交
    form.on('submit(washingModelAddSubmit)', function (data) {
        var formData = data.field;
        layer.confirm('确定要提交吗?', {icon: 1, title: '提示'}, function (index) {
            $.ajax({
                url: '/api/addWashingModel',
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
    form.on('submit(washingModelEditSubmit)', function (data) {
        var formData = data.field;
        formData.id = washingModelId;
        layer.confirm('确定要修改吗?', {icon: 1, title: '提示'}, function (index) {
            $.ajax({
                url: '/api/updateWashingModel',
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



    // 品牌下拉框选择后初始化型号下拉框
    form.on('select(brandName)', function(data){
        let elem = $(data.elem),
            value = data.value;
        initBrandType(value);
    });

    function initBrand(){
        // 初始化品牌下拉框
        let brandContent = '';
        $.ajax({
            url: '/api/getAllBrand',
            type: 'GET',
            async: false,
            data: {},
            success: function(response) {
                if(response.code == 0){
                    $.each(response.data, function(index, obj){
                        if(index == 0){
                            initBrandType(obj.id);
                        }
                        brandContent += '<option value="'+obj.id+'">'+obj.name+'</option>\n'
                    });
                }
                $('#brandName').html(brandContent);
                form.render();
            }
        });
    }

    // 初始化型号下拉框
    function initBrandType(brandId){
        let brandTypeContent = '';
        $.ajax({
            url: '/api/getBrandTypeByBrand',
            type : "GET",
            async: false,
            data: {brandId: brandId},
            success : function(response) {
                if(response.code == 0){
                    $.each(response.data, function(index, obj){
                        brandTypeContent += '<option value="'+obj.id+'">'+obj.typeName+'</option>\n'
                    });
                }
                $('#brandTypeName').html(brandTypeContent);
                form.render();
            }
        });
    }
});
