layui.use(['form', 'laydate', 'jquery', 'layer', 'upload'], function () {
    var form = layui.form,
        laydate = layui.laydate,
        $ = layui.jquery,
        upload = layui.upload,
        layer = layui.layer;

    initBrand();

    // 获取washingMachineId
    let washingMachineId = $('#washingMachineId').val()
    if(washingMachineId){
        $.ajax({
            url: '/api/getWashingMachineById',
            type: 'GET',
            data: {washingMachineId: washingMachineId},
            success: function(response){
                initBrandType(response.data.brandId);
                form.val('washingMachineForm', response.data);
                $('#submitWashingMachine').attr('lay-filter', 'washingMachineEditSubmit');
                form.render();
            }
        });
    }



    // 品牌下拉框选择后初始化型号下拉框
    form.on('select(brandName)', function(data){
        let elem = $(data.elem),
            value = data.value;
        initBrandType(value);
    });

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
                $('#typeName').html(brandTypeContent);
                form.render();
            }
        });
    }

    // 型号下拉框监听
    form.on('select(typeName)', function(data){
        let elem = $(data.elem),
            value = data.value;
        let matchingContent = '';
        let brandId = $('#brandName').val();
        let typeId = value;
        $.ajax({
            url: '/api/getWashingMachineListByBrand',
            type: 'GET',
            data: {brand: brandId, brandType: typeId},
            success: function(response){
                if(response.code == 0){
                    $.each(response.data, function(index, obj){
                        matchingContent += '<option value="'+obj.id+'">'+obj.number+'</option>\n';
                    });
                }
            }
        });
        $('#typeName').html(matchingContent);
        form.render();
    });

    //监听提交
    form.on('submit(washingMachineAddSubmit)', function (data) {
        var formData = data.field;
        layer.confirm('确定要提交吗?', {icon: 1, title: '提示'}, function (index) {
            $.ajax({
                url: '/api/addWashingMachine',
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
    form.on('submit(washingMachineEditSubmit)', function (data) {
        var formData = data.field;
        formData.id = washingMachineId;
        layer.confirm('确定要修改吗?', {icon: 1, title: '提示'}, function (index) {
            $.ajax({
                url: '/api/updateWashingMachine',
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
