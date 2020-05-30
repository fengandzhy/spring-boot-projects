
$(function(){
    $('#tb_Company').bootstrapTable({
        url: '/project1/company/findAllSimplePageMap',         //请求后台的URL（*）
        method: 'post',                      //请求方式（*）post/get
        //striped: true,                      //是否显示行间隔色
        classes:"table table-bordered table-hover",//启用bootstrap的表格样式
        theadClasses:"bg-info",         //表头的背景色
        //toolbar: '#toolbar',                //工具按钮用哪个容器
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        //queryParamsType:'',                //'limit'：RESTFul风格，包括limit, offset, search, sort, order，如果为空''：包括pageSize, pageNumber, searchText, sortName, sortOrder
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 5,                       //每页的记录行数（*）
        pageList: [3,5,20],                 //可供选择的每页的行数（*）
        search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: true,                  //默认为false，设置为 true启用 全匹配搜索，否则为模糊搜索
        showColumns: false,                  //是否显示所有的列
        showRefresh: false,                  //是否显示刷新按钮
        minimumCountColumns: 10,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        //height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数设置表格高度
        uniqueId: "uuid",                     //每一行的唯一标识，一般为主键列
        showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                   //是否显示父子表
        queryParams: function(params){
            //重写参数，这样方便后期增加查询条件
            var param = {
                size: params.limit,//页面大小
                page: (params.offset / params.limit),//页码

                // //页面的查询条件
                // comname:$("#comname").val(),
                // employeenumber:$("#employeenumber").val(),
                // comstatus:$("#comstatus").val()
            };
            return param;
        },
        columns: [{
            title: '编号',
            formatter:function(value,row,index)
            {
                var pageSize=$('#tb_Company').bootstrapTable('getOptions').pageSize;
                var pageNumber=$('#tb_Company').bootstrapTable('getOptions').pageNumber;
                return pageSize * (pageNumber - 1) + index + 1;
            },
            align:'center',
            width:55
        },{
            field: 'uuid',
            title: 'UUID',
            visible:false
        },{
            field: 'companyName',
            title: '公司名称'
        }, {
            field: 'companyAddress',
            title: '公司地址'
        }, {
            field: 'companyUrl',
            title: '公司网址'
        }, {
            field: 'companyPhone',
            title: '公司座机'
        },{
            field: 'establishDate',
            title: '成立日期'
        }, {
            field: 'staffAmount',
            title: '员工人数'

        },  {
            field: 'companyDesc',
            title: '公司简介',
            visible:false
        }, {
            field: 'contactor',
            title: '联系人姓名',
            visible:false
        },{
            field: 'contactorMobile',
            title: '联系人手机号',
            visible:false
        },{
            field: 'contactorEmail',
            title: '联系人邮箱',
            visible:false
        }]
    });
});

