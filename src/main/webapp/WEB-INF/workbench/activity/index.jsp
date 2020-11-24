<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href="/crm/jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="/crm/jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />

<script type="text/javascript" src="/crm/jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="/crm/jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/crm/jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="/crm/jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>


	<%--导入分页插件--%>
	<link type="text/css" rel="stylesheet" href="/crm/jquery/bs_pagination/jquery.bs_pagination.min.css"/>
	<script type="text/javascript" src="/crm/jquery/bs_pagination/jquery.bs_pagination.min.js"></script>
	<script type="text/javascript" src="/crm/jquery/bs_pagination/en.js"></script>

<script type="text/javascript">

	$(function(){
		
		
		
	});
	
</script>
</head>
<body>

	<!-- 创建市场活动的模态窗口 -->
	<div class="modal fade" id="createActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel1">创建市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form class="form-horizontal" role="form" id="create-activityForm">
					
						<div class="form-group">
							<label for="create-marketActivityOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" name="owner" id="create-marketActivityOwner">
								  <%--<option>zhangsan</option>
								  <option>lisi</option>
								  <option>wangwu</option>--%>
								</select>
							</div>
                            <label for="create-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" name="name" id="create-marketActivityName">
                            </div>
						</div>
						
						<div class="form-group">
							<label for="create-startTime" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" name="startDate" id="create-startTime">
							</div>
							<label for="create-endTime" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" name="endDate" id="create-endTime">
							</div>
						</div>
                        <div class="form-group">

                            <label for="create-cost" class="col-sm-2 control-label">成本</label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" name="cost" id="create-cost">
                            </div>
                        </div>
						<div class="form-group">
							<label for="create-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" name="description" id="create-describe"></textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" id="discardActivity" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="saveActivity">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 修改市场活动的模态窗口 -->
	<div class="modal fade" id="editActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel2">修改市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form class="form-horizontal" role="form">
                        <input type="hidden" id="activityId" />
						<div class="form-group">
							<label for="edit-marketActivityOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-marketActivityOwner">
								  <%--<option>zhangsan</option>
								  <option>lisi</option>
								  <option>wangwu</option>--%>
								</select>
							</div>
                            <label for="edit-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="edit-marketActivityName">
                            </div>
						</div>

						<div class="form-group">
							<label for="edit-startTime" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-startTime">
							</div>
							<label for="edit-endTime" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-endTime">
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-cost" class="col-sm-2 control-label">成本</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-cost">
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="edit-describe"></textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="updateActivity" data-dismiss="modal">更新</button>
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	<div>
		<div style="position: relative; left: 10px; top: -10px;">
			<div class="page-header">
				<h3>市场活动列表</h3>
			</div>
		</div>
	</div>
	<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">
		<div style="width: 100%; position: absolute;top: 5px; left: 10px;">
		
			<div class="btn-toolbar" role="toolbar" style="height: 80px;">
				<form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">名称</div>
				      <input class="form-control" type="text" id="name"/>
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">所有者</div>
				      <input class="form-control" type="text" id="owner"/>
				    </div>
				  </div>


				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">开始日期</div>
					  <input class="form-control" type="text" id="startDate" />
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">结束日期</div>
					  <input class="form-control" type="text" id="endDate">
				    </div>
				  </div>
					<button type="button" class="btn btn-default" onclick = pageList()>查询</button>
				</form>

			</div>
			<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 5px;">
				<div class="btn-group" style="position: relative; top: 18%;">
				  <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#createActivityModal" id="create_activity"><span class="glyphicon glyphicon-plus"></span> 创建</button>
				  <button type="button" class="btn btn-default" data-toggle="modal" id="edit-activity"><span class="glyphicon glyphicon-pencil"></span> 修改</button>
				  <button type="button" class="btn btn-danger" id="deleteActivityBtn"><span class="glyphicon glyphicon-minus"></span> 删除</button>
				</div>
				
			</div>
			<div style="position: relative;top: 10px;">
				<table class="table table-hover">
					<thead>
						<tr style="color: #B3B3B3;">
							<td><input type="checkbox" class="father"/></td>
							<td>名称</td>
                            <td>所有者</td>
							<td>开始日期</td>
							<td>结束日期</td>
						</tr>
					</thead>
					<%--此处是数据，通过请求后台进行展示的数据--%>
					<tbody id="pageInfo">
					</tbody>
				</table>
			</div>
			<%--分页展示--%>
			<div style="height: 50px; position: relative;top: 30px;">
				<div id="activityPage"></div>
<%--				<div>
					<button type="button" class="btn btn-default" style="cursor: default;">共<b>50</b>条记录</button>
				</div>
				<div class="btn-group" style="position: relative;top: -34px; left: 110px;">
					<button type="button" class="btn btn-default" style="cursor: default;">显示</button>
					<div class="btn-group">
						<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
							10
							<span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">20</a></li>
							<li><a href="#">30</a></li>
						</ul>
					</div>
					<button type="button" class="btn btn-default" style="cursor: default;">条/页</button>
				</div>
				<div style="position: relative;top: -88px; left: 285px;">
					<nav>
						<ul class="pagination">
							<li class="disabled"><a href="#">首页</a></li>
							<li class="disabled"><a href="#">上一页</a></li>
							<li class="active"><a href="#">1</a></li>
							<li><a href="#">2</a></li>
							<li><a href="#">3</a></li>
							<li><a href="#">4</a></li>
							<li><a href="#">5</a></li>
							<li><a href="#">下一页</a></li>
							<li class="disabled"><a href="#">末页</a></li>
						</ul>
					</nav>
				</div>--%>
			</div>
			
		</div>
		
	</div>
<script>
	<%--提交保存活动的表单--%>
	$("#saveActivity").click(function () {
		var serialize = $("#create-activityForm").serialize();


	//	提交数据，因为此处提交数据需要进行弹窗提醒，所以必须使用Ajax进行提交
		$.ajax({
			url: "/crm/workbench/activity/saveActivity?" + serialize,
			type:"get",
			dataType:"json",
			success:function (data) {
				alert(data.msg);

				/*
				modal函数:弹窗函数 show:显示 hide:隐藏
				 */
				//隐藏模态窗口
				$('#createActivityModal').modal('hide');

				//对模态窗口的数据进行清空
				// $('#create-marketActivityOwner').val("");
				clearCreateModal();
				pageList();
			}
		})

	});



	<%--点击创建按钮之后，不仅要弹出模态窗口，而且还需要进行后台数据的请求，进行数据的回显(jquery过滤器不够熟练)--%>
	$("#create_activity").click(function () {
	//	此处需要对owner进行查询以及回显，查询的具体是user表
		$.ajax({
			url:"/crm/workbench/activity/queryAllUsers",
			data:{
				"":"",
				"":"",
			},
			type:"get",
			dataType:"json",
			success:function (data) {
				$("#create-marketActivityOwner").html("");
				for (var i = 0; i < data.length; i++) {
					//	查询回来用户数据之后，进行数据的拼接，展示
					$("#create-marketActivityOwner").append("<option value='"+data[i].id+"'>" +
							data[i].name+"</option>");
				}
			}
		})
	});
	<%--进入页面之后就要进行数据查询--%>
	pageList(1,2);
	/*
	* 定义一个函数，pageList，功能就是进行页面的数据展示。
	* 参数有页数，以及每页的数据的条数。
	* */
	function pageList(page, pageSize) {
		$.ajax({
			url:"/crm/workbench/activity/queryActivity",
			data:{
				"page":page,
				"pageSize":pageSize,
				"name":$("#name").val(),
				"owner":$("#owner").val(),
				"startDate":$("#startDate").val(),
				"endDate":$("#endDate").val()
			},
			dataType : "json",
			type : "get",
			success:function (data) {
				var dataList = data.pageInfo.list;
				//在对页面元素进行拼接前，要对之前的元素进行清空
				$("#pageInfo").html("");
				for (var i = 0; i < dataList.length; i++) {
				//	进行元素拼接，展示数据在jsp页面上
					/*
					* <tr class="active">
							<td><input type="checkbox" /></td>
							<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='detail.jsp';">发传单</a></td>
                            <td>zhangsan</td>
							<td>2020-10-10</td>
							<td>2020-10-20</td>
						</tr>
					* */
					//	此处可以对返回的数据进行处理，返回的是一个list集合，其中的元素是一个map集合
					$("#pageInfo").append("<tr class='active'><td><input type='checkbox' class='son' value='"+dataList[i].id+"'/></td><td><a style='text-decoration: none; cursor: pointer;'" +
							"onclick=\"window.location.href='/crm/workbench/queryActivityDetailById?id="+  dataList[i].id +"'\">"+dataList[i].name+"</a></td><td>"+dataList[i].uname+"</td>" +
							"<td>"+dataList[i].startDate+"</td><td>"+dataList[i].endDate+"</td>");

				}


			//	此处对分页进行拼接，需要使用提供的分页插件，引入相关的js以及css样式
				$("#activityPage").bs_pagination({
					currentPage: data.page, // 页码
					rowsPerPage: data.pageSize, // 每页显示的记录条数
					maxRowsPerPage: 20, // 每页最多显示的记录条数
					totalPages: data.pages, // 总页数
					totalRows: data.total, // 总记录条数
					visiblePageLinks: 3, // 显示几个卡片
					showGoToPage: true,
					showRowsPerPage: true,
					showRowsInfo: true,
					showRowsDefaultInfo: true,
					//回调函数，用户每次点击分页插件进行翻页的时候就会触发该函数
					onChangePage : function(event, obj){

						//刷新页面，obj.currentPage:当前点击的页码
						pageList(obj.currentPage,obj.rowsPerPage);
					}
				});
			}
		});
	}


	//条件查询日历插件
	$("#startDate").datetimepicker({
		language:  "zh-CN",
		format: "yyyy-mm-dd",//显示格式
		minView: "month",//设置只显示到月份
		initialDate: new Date(),//初始化当前日期
		autoclose: true,//选中自动关闭
		todayBtn: true, //显示今日按钮
		clearBtn : true,
		pickerPosition: "bottom-left"
	});

	$("#endDate").datetimepicker({
		language:  "zh-CN",
		format: "yyyy-mm-dd",//显示格式
		minView: "month",//设置只显示到月份
		initialDate: new Date(),//初始化当前日期
		autoclose: true,//选中自动关闭
		todayBtn: true, //显示今日按钮
		clearBtn : true,
		pickerPosition: "bottom-left"
	});

	$("#create-startTime").datetimepicker({
		language:  "zh-CN",
		format: "yyyy-mm-dd",//显示格式
		minView: "month",//设置只显示到月份
		initialDate: new Date(),//初始化当前日期
		autoclose: true,//选中自动关闭
		todayBtn: true, //显示今日按钮
		clearBtn : true,
		pickerPosition: "bottom-left"
	});

	$("#create-endTime").datetimepicker({
		language:  "zh-CN",
		format: "yyyy-mm-dd",//显示格式
		minView: "month",//设置只显示到月份
		initialDate: new Date(),//初始化当前日期
		autoclose: true,//选中自动关闭
		todayBtn: true, //显示今日按钮
		clearBtn : true,
		pickerPosition: "bottom-left"
	});


	//全选与全不选
	$(".father").click(function () {
		$(".son").prop("checked",$(this).prop('checked'));
	});

	setTimeout(function () {
		$(".son").each(function(){
			$(this).click(function () {
                var length = $(".son").length;
                //表单对象属性过滤
                var checkedLeng = $(".son:checked").length;
                if (checkedLeng == length) {
                    $(".father").prop("checked", true);
                } else {
                    $(".father").prop("checked", false);
                }
			});
		});
	});



    /*
    *
    * 点击修改按钮，进行数据回显
    * 此处不能使用data-target，因为只有在满足条件的情况下才可以弹出模态窗口
    * */
    $('#edit-activity').bind('click',function () {
    //    此处需要进行checkbox的判断，只能选择一个checkbox
        if ($('.son:checked').length > 1) {
            alert("只能选择一条数据！");
        }else if ($('.son:checked').length == 0) {
            alert("至少选择一条数据进行修改！");
        } else {

			//对模态窗口的数据进行清空
			clearEditModal();

            //模态窗口进行手动显示
            $("#editActivityModal").modal("show");
        //    获取被选择的数据的主键进行查询，（查询活动数据以及owner的姓名，考虑查询两次返回一个集合）
            $.ajax({
                url:"/crm/workbench/queryActivityByPrimary",
                data:{
                    'id':$('.son:checked').val()
                },
                dataType:"json",
                type:"get",
                success:function (data) {
                //    此处返回的是封装好的数据
                //    对下拉列表进行拼接
                    for (var i = 0; i < data.users.length; i++) {
                        $("#edit-marketActivityOwner").append("<option value='"+data.users[i].id+"'>" +
                            data.users[i].name+"</option>");
                    }
                    //选中该条数据所属的所有者
					var owner = data.activity.owner;
					$("#edit-marketActivityOwner option").each(function () {
						if (owner == $(this).val()) {
							$(this).prop("selected", true);
						}
					});
                    //对模态窗口的数据进行赋值
                    $('#edit-marketActivityName').val(data.activity.name);
                    $('#edit-startTime').val(data.activity.startDate);
                    $('#edit-endTime').val(data.activity.endDate);
                    $('#edit-cost').val(data.activity.cost);
                    $('#edit-describe').val(data.activity.description);

                    //作为隐藏域的value，主要用于主键更新数据
                    $("#activityId").val(data.activity.id);
                //    至此，模态窗口的数据已经拼接成功
                }
            })

        }
    });

    //此处进行修改活动的数据的提交
    $('#updateActivity').click(function () {

        $.ajax({
            url:"/crm/workbench/updateActivity",
            data:{
                //根据主键进行更新
                'id' : $('#activityId').val(),
                //此处select标签的val就是option的value
                'owner' : $('#edit-marketActivityOwner').val(),
                'name' : $('#edit-marketActivityName').val(),
                'startDate' : $('#edit-startTime').val(),
                'endDate' : $('#edit-endTime').val(),
                'cost' : $('#edit-cost').val(),
                'description' : $('#edit-describe').val(),
            },
            dataType:"json",
            type:"get",
            success:function (data) {
            //    此处接收处理结果，更新成功还是更新失败
                alert(data.msg);
            //    重新查询页面
                pageList();
            }
        })

    });

//    此处进行数据的删除
    $("#deleteActivityBtn").bind("click",function () {
        var array = [];
        if ($('.son:checked').length == 0) {
            alert("请您选择要删除的数据！！！");
        } else {
        //    对选中的数据进行删除
            $('.son:checked').each(function () {
                array.push($(this).val());
            })
        //    向后台发送请求，删除数据，根据主键进行删除，checkbox中存放了主键
            $.ajax({
                url: "/crm/workbench/deleteActivities?ids=" + array.join(","),
                dataType:"json",
                data:{

                },
                type:"get",
                success:function (data) {
                //    此处返回处理结果
                    alert(data.msg);
                    pageList();
                }
            })

        }
    });

    /**
	 * 点击关闭用来添加数据的模态窗口，清空之前输入的数据
	 * */
    $('#discardActivity').click(function () {
		clearCreateModal();
	});

    /**
	 * 清空添加数据的模态窗口
	 * */
    function clearCreateModal() {
		$('#create-marketActivityName').val("");
		$('#create-startTime').val("");
		$('#create-endTime').val("");
		$('#create-cost').val("");
		$('#create-describe').val("");
	}
	/**
	 * 清空修改数据的模态窗口
	 * */
	function clearEditModal() {
		$('#edit-marketActivityOwner').empty();
		$('#edit-marketActivityName').val("");
		$('#edit-startTime').val("");
		$('#edit-endTime').val("");
		$('#edit-cost').val("");
		$('#edit-describe').val("");
	}

</script>
</body>
</html>