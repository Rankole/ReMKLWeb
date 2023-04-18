layui.use(['form', 'laydate', 'jquery', 'layer', 'upload'], function () {
    var form = layui.form,
        laydate = layui.laydate,
        $ = layui.jquery,
        upload = layui.upload,
        layer = layui.layer;

    // 获取repairId
    let repairId = $('#repairId').val()
    if (repairId) {
        $.ajax({
            url: '/api/getRepairById',
            type: 'GET',
            data: {repairId: repairId},
            success: function (response) {
                form.val('repairForm', response.data);
                $('#submitRepair').attr('lay-filter', 'repairEditSubmit')
                form.render();
            }
        });
    }

    //监听提交
    form.on('submit(repairAddSubmit)', function (data) {
        var formData = data.field;
        layer.confirm('确定要提交吗?', {icon: 1, title: '提示'}, function (index) {
            $.ajax({
                url: '/api/addRepair',
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
    form.on('submit(repairEditSubmit)', function (data) {
        var formData = data.field;
        formData.id = repairId;
        layer.confirm('确定要修改吗?', {icon: 1, title: '提示'}, function (index) {
            $.ajax({
                url: '/api/updateRepair',
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

    initBrand();
    initMaintenanceWorker();

    form.on('select(brandName)', function (data) {
        let value = data.value;
        initBrandType(value);
    });

    form.on('select(brandType)', function (data) {
        let value = data.value;
        initWashingMachineNumber($('#brandName').val(), value);
    });

    // 初始化品牌下拉框
    function initBrand() {
        let brandContent = '';
        $.ajax({
            url: '/api/getAllBrand',
            type: 'GET',
            data: {},
            async: false,
            success: function (response) {
                if (response.code == 0) {
                    $.each(response.data, function (index, obj) {
                        if (index == 0) {
                            initBrandType(obj.id);
                        }
                        brandContent += '<option value="' + obj.id + '">' + obj.name + '</option>\n'
                    });
                }
                $('#brandName').html(brandContent);
                form.render();
            }
        });
    }

    // 初始化型号下拉框
    function initBrandType(brandId) {
        let brandTypeContent = '';
        $.ajax({
            url: '/api/getBrandTypeByBrand',
            type: "GET",
            async: false,
            data: {brandId: brandId},
            success: function (response) {
                if (response.code == 0) {
                    $.each(response.data, function (index, obj) {
                        if (index == 0) {
                            initWashingMachineNumber(brandId, obj.id);
                        }
                        brandTypeContent += '<option value="' + obj.id + '">' + obj.typeName + '</option>\n'
                    });
                }
                $('#typeName').html(brandTypeContent);
                form.render();
            }
        });
    }

    // 初始化洗衣机编号下拉框
    function initWashingMachineNumber(brandId, typeId) {
        let numberContent = '';
        $.ajax({
            url: '/api/getWashingMachineListByBrand',
            type: 'GET',
            async: false,
            data: {brand: brandId, brandType: typeId},
            success: function (response) {
                if (response.code == 0) {
                    $.each(response.data, function (index, obj) {
                        if (obj.status == 1) {
                            numberContent += '<option disabled value="' + obj.id + '">' + obj.number + ' 已被预约' + '</option>\n'
                        } else if (obj.status == 2) {
                            numberContent += '<option disabled value="' + obj.id + '">' + obj.number + ' 已被报修' + '</option>\n'
                        } else if (obj.status == 3) {
                            numberContent += '<option disabled value="' + obj.id + '">' + obj.number + ' 使用中' + '</option>\n'
                        } else {
                            numberContent += '<option value="' + obj.id + '">' + obj.number + '</option>\n'
                        }
                    });
                    $('#number').html(numberContent);
                    form.render();
                }
            }
        });
    }

    // 初始化维修人员列表
    function initMaintenanceWorker() {
        let workerContent = '';
        $.ajax({
            url: '/api/getWorkers',
            type: 'GET',
            data: {},
            success: function (response) {
                if(response.code == 0){
                    $.each(response.data, function (index, obj) {
                        workerContent += '<option value="' + obj.id + '">' + obj.name + '</option>\n'
                    });
                    $('#workerId').html(workerContent);
                    form.render();
                }
            }
        });
    }
});
