layui.use(['form', 'laydate', 'jquery', 'upload', 'table', 'layer'], function () {
    var form = layui.form,
        table = layui.table,
        laydate = layui.laydate,
        layer = layui.layer,
        $ = layui.jquery,
        upload = layui.upload;

    // 初始化用户信息表格
    var tableIns = table.render({
        elem: '#userList',
        url: '/api/getUserList',
        cellMinWidth: 95,
        id: "userList",
        page: true,
        limit: 10,
        limits: [10, 15, 20],
        request: {
            pageName: 'current',
            limitName: 'size'
        },
        parseData: function (res) {
            return {
                "count": res.data.total,
                "data": res.data.records,
                "code": res.code,
                "msg": res.msg
            }
        },
        done: function (res, curr, count) {
        },
        cols: [[
            {field: "id", hide: true},
            {type: 'checkbox'},
            {field: 'userName', title: '用户名', align: 'center'},
            {field: 'password', title: '密码', align: 'center'},
            {field: 'balance', title: '余额', align: 'center'},
            {field: 'score', title: '积分', align: 'center'},
            {field: 'phone', title: '电话', align: 'center'},
            {field: 'address', title: '住址', align: 'center'},
            {title: '操作', width: 170, templet: '#userListBar', fixed: "right", align: "center"}
        ]]
    });

    // 用户信息列表工具栏事件监听
    table.on('tool(userList)', function (obj) {
        var event = obj.event,
            data = obj.data;
        if (event == 'user_detail') {
            userDetail(data);
        }else if(event == 'user_edit'){
            addUser(data);
        }
    });

    let userIds = [];
    // 表格的复选框选择事件
    table.on('checkbox(userList)', function (obj) {
        if (obj.type == 'one') {
            if (obj.checked) {
                // 如果复选框是选中事件, 将id加入到参数数组中
                userIds.push(obj.data.id);
            } else {
                // 如果复选框是取消选中事件, 将id从参数数组中删除
                userIds.remove(obj.data.id);
            }
        } else if (obj.type == 'all') {
            // 如果是全选, 将全选的id从参数数组中加入或删除
            var checkStatus = table.checkStatus('userList');
            var data = checkStatus.data;
            $.each(data, function (index, dataObj) {
                if (obj.checked) {
                    userIds.push(dataObj.id);
                } else {
                    userIds.remove(dataObj.id);
                }
            })
        }

    })

    // 新增user信息按钮点击事件
    $('.addUser_btn').on('click', function () {
        addUser();
    });
    // 删除user信息按钮点击事件
    $('.deleteUser_btn').on('click', function () {
        if(userIds.length == 0){
            layer.msg('请至少选择一条记录进行删除!', {icon: 2});
            return false;
        }
        layer.confirm('确定要删除吗?', function () {
            let ids = userIds.join(',');
            $.ajax({
                url: '/api/deleteUserByIds/'+ids,
                type: 'DELETE',
                success: function (response) {
                    layer.msg('删除成功!', {icon: 1});
                    tableIns.reload();
                }
            });
        });
    });

    // 查询按钮点击事件
    $('.userListSearch').on('click', function () {
        table.reload("userList", {
            where: form.val('searchUserListForm')
        })
    });

    // 打开用户信息详情
    function userDetail(data) {
        var index = layer.open({
            title: '用户信息详情',
            type: 2,
            content: "/page/user>userDetail",
            success: function (layero, index) {
                var body = layer.getChildFrame('body', index);
                body.find('#userId').val(data.id);
                setTimeout(function () {
                    layui.layer.tips('点击此处返回', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        })
        layui.layer.full(index);
        window.sessionStorage.setItem("index", index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize", function () {
            layer.full(window.sessionStorage.getItem("index"));
        })
    }

    // 新增用户信息
    function addUser(edit) {
        var index = layer.open({
            title: "用户信息",
            type: 2,
            content: "/page/user>userAdd",
            success: function (layero, index) {
                var body = layer.getChildFrame('body', index);
                body.find('#userId').val(edit.id);
                setTimeout(function () {
                    layui.layer.tips('点击此处返回', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        })
        layui.layer.full(index);
        window.sessionStorage.setItem("index", index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize", function () {
            layer.full(window.sessionStorage.getItem("index"));
        })
    }
});
