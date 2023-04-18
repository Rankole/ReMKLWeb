layui.use(['form', 'laydate', 'jquery', 'layer', 'upload'], function () {
    var form = layui.form,
        laydate = layui.laydate,
        $ = layui.jquery,
        upload = layui.upload,
        layer = layui.layer;

    $('#brand').attr('disabled', '');
    $('#typeIns').attr('disabled', '');

    let washingMachineId = $('#washingMachineId').val();
    if(washingMachineId){
        $.ajax({
            url: '/api/getWashingMachineById',
            type: 'GET',
            data: {washingMachineId: washingMachineId},
            success: function(response){
                form.val('washingMachineForm', response.data);
                $('#submitWashingMachine').attr('lay-filter', 'washingMachineEditSubmit')
                form.render();
            }
        });
    }

    let brandId = $('#brandId').val();
    let brandTypeList;
    selectPaymentMode();
    // 初始化型号单选框
    $.ajax({
        url: '/api/getBrand',
        type: 'GET',
        data: {brandId: brandId},
        success: function(response){
            brandTypeList = response.data;
            let radioContent = '';
            $.each(brandTypeList, function(index, obj){
                if(index == 0){
                    brandTypeCheck(obj.id);
                    radioContent += '<input checked lay-filter="brandType" type="radio" name="type" value="'+obj.id+'" title="'+obj.typeName+'">'+'\n';
                }else{
                    radioContent += '<input lay-filter="brandType" type="radio" name="type" value="'+obj.id+'" title="'+obj.typeName+'">'+'\n';
                }
            })
            $('#brand').val(brandTypeList[0].brandName);
            $('#brandTypeDiv').html(radioContent);
            form.render();
        }
    });

    //监听使用洗衣机
    form.on('submit(useWashingMachine)', function (data) {
        let formData = data.field;
        let washingMachineId = formData.number;
        let washingModelId = formData.model;
        let userGiftId = formData.userGiftId;
        if(!washingMachineId){
            layer.msg('请选择洗衣机!', {icon: 2});
            return false;
        }
        if(!washingModelId){
            layer.msg('请选择洗衣模式!', {icon: 2});
            return false;
        }
        layer.confirm('确定使用这台洗衣机吗?', {icon: 1, title: '提示'}, function (index) {
            $.ajax({
                url: '/api/usedWashingMachine',
                type: 'POST',
                data: {washingMachineId: washingMachineId, washingModelId: washingModelId, userGiftId: userGiftId},
                success: function (response) {
                    if (response.code == 0) {
                        layer.msg('使用成功!', {icon: 1, time: 1000}, function(){
                            window.parent.location.reload();
                        });
                    } else {
                        layer.msg(response.msg, {icon: 2});
                    }
                }
            });
        });

        return false;
    });

    //监听预约洗衣机
    form.on('submit(reserveWashingMachine)', function (data) {
        let formData = data.field;
        let washingMachineId = formData.number;
        let washingModelId = formData.model;
        let userGiftId = formData.userGiftId;
        if(!washingMachineId){
            layer.msg('请选择洗衣机!', {icon: 2});
            return false;
        }
        if(!washingModelId){
            layer.msg('请选择洗衣模式!', {icon: 2});
            return false;
        }

        layer.confirm('确定预约这台洗衣机吗?', {icon: 1, title: '提示'}, function (index) {
            $.ajax({
                url: '/api/reservationWashingMachine',
                type: 'POST',
                data: {washingMachineId: washingMachineId, washingModelId: washingModelId, userGiftId: userGiftId},
                success: function (response) {
                    if (response.code == 0) {
                        layer.msg('预约成功,请及时在"我的预约"页面进行使用!', {icon: 1, time: 1000}, function(){
                            layer.close(index);
                            window.parent.location.reload();
                        });
                    } else {
                        layer.msg(response.msg, {icon: 2});
                    }
                }
            });
        });

        return false;
    });

    function selectPaymentMode(){
        $.ajax({
            url: '/api/getPaymentModeInfo',
            type: 'GET',
            data: {},
            success: function(response){
                let content = '<option value=-1>余额('+response.data.balance+'元)</option>\n';
                $.each(response.data.userGiftList, function(index, obj){
                    if(obj.giftUsedId == brandId){
                        content += '<option value="'+obj.id+'">' + obj.giftName + ' 可用于 ' + obj.giftUsedName + '牌洗衣机 (剩余' + obj.amount +'张)</option>\n';
                    }else{
                        content += '<option disabled value="'+obj.id+'">' + obj.giftName + ' 可用于 ' + obj.giftUsedName + '牌洗衣机 (剩余' + obj.amount +'张)</option>\n';
                    }
                });
                $('#paymentMode').html(content);
                form.render();
            }
        });
    }

    // 监听型号选择事件
    form.on('radio(brandType)', function(data){
        brandTypeCheck(data.value);
    });

    // 选择型号事件
    function brandTypeCheck(brandType){
        let brandId = $('#brandId').val();
        $.each(brandTypeList, function(index, obj){
            if(obj.id == brandType){
                $('#typeIns').val(obj.typeIns);
                $('#brandTypeImg').attr('src', obj.imgPath+'?k=Math.random()');
            }
        });
        initModel(brandId, brandType);
        initWashingMachineNumber(brandId, brandType);
        form.render();
    }

    // 监听模式选择事件
    form.on('select(model)', function(data){
        selectedWashingModel(data.value);
    })

    // 初始化洗衣模式下拉框
    function initModel(brandId, typeId){
        let modelContent = '';
        $.ajax({
            url: '/api/getWashingModel',
            type: 'GET',
            async: false,
            data: {brand: brandId, brandType: typeId},
            success: function(response){
                if(response.code == 0){
                    $.each(response.data, function(index, obj){
                        if(index == 0){
                            selectedWashingModel(obj.id);
                        }
                        modelContent += '<option value="'+obj.id+'">'+obj.modelName+'</option>\n'
                    });
                    $('#model').html(modelContent);
                    form.render();
                }
            }
        });
    }

    // 初始化洗衣机编号下拉框
    function initWashingMachineNumber(brandId, typeId){
        let numberContent = '';
        $.ajax({
            url: '/api/getWashingMachineListByBrand',
            type: 'GET',
            async: false,
            data: {brand: brandId, brandType: typeId},
            success: function(response){
                if(response.code == 0){
                    $.each(response.data, function(index, obj){
                        if(obj.status == 1){
                            numberContent += '<option disabled value="'+obj.id+'">'+obj.number+' 已被预约'+'</option>\n'
                        }else if(obj.status ==2){
                            numberContent += '<option disabled value="'+obj.id+'">'+obj.number+' 已被报修'+'</option>\n'
                        }else if (obj.status == 3){
                            numberContent += '<option disabled value="'+obj.id+'">'+obj.number+' 使用中'+'</option>\n'
                        }else{
                            numberContent += '<option value="'+obj.id+'">'+obj.number+'  (价格:'+ obj.price + '元/次)' +'</option>\n'
                        }
                    });
                    $('#number').html(numberContent);
                    form.render();
                }
            }
        });
    }

    // 根据洗衣模式id查询洗衣模式
    function selectedWashingModel(washingModelId){
        $.ajax({
            url: '/api/getWashingModelById',
            type: 'GET',
            data: {washingModelId: washingModelId},
            success: function(response) {
                if(response.code == 0){
                    $('#runTime').val(response.data.runTime);
                }
            }
        });
    }

});
