<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href="/crm/jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="/crm/jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="/crm/jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>


<link href="/crm/jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="/crm/jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="/crm/jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>

<script type="text/javascript">
	$(function(){
		$("#isCreateTransaction").click(function(){
			if(this.checked){
				$("#create-transaction2").show(200);
			}else{
				$("#create-transaction2").hide(200);
			}
		});
	});
</script>

</head>
<body>
	
	<!-- 搜索市场活动的模态窗口 -->
	<div class="modal fade" id="searchActivityModal" role="dialog" >
		<div class="modal-dialog" role="document" style="width: 90%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title">搜索市场活动</h4>
				</div>
				<div class="modal-body">
					<div class="btn-group" style="position: relative; top: 18%; left: 8px;">
						<form class="form-inline" role="form">
						  <div class="form-group has-feedback">
						    <input type="text" id="queryActvityByName" class="form-control" style="width: 300px;" placeholder="请输入市场活动名称，支持模糊查询">
						    <span class="glyphicon glyphicon-search form-control-feedback"></span>
						  </div>
						</form>
					</div>
					<table id="activityTable" class="table table-hover" style="width: 900px; position: relative;top: 10px;">
						<thead>
							<tr style="color: #B3B3B3;">
								<td></td>
								<td>名称</td>
								<td>开始日期</td>
								<td>结束日期</td>
								<td>所有者</td>
								<td></td>
							</tr>
						</thead>
						<tbody id="activityModal">
						</tbody>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td><button onclick="addActivitySource()">确定</button></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>

	<div id="title" class="page-header" style="position: relative; left: 20px;">
		<h4>转换线索 <small>${clue.fullname}${clue.appellation}-${clue.company}</small></h4>
	</div>
	<div id="create-customer" style="position: relative; left: 40px; height: 35px;">
		新建客户：${clue.company}
	</div>
	<div id="create-contact" style="position: relative; left: 40px; height: 35px;">
		新建联系人：${clue.fullname}${clue.appellation}
	</div>
	<div id="create-transaction1" style="position: relative; left: 40px; height: 35px; top: 25px;">
		<input type="checkbox" id="isCreateTransaction"/>
		为客户创建交易
	</div>
	<div id="create-transaction2" style="position: relative; left: 40px; top: 20px; width: 80%; background-color: #F7F7F7; display: none;" >
	
		<form id="transactionForm">
		  <div class="form-group" style="width: 400px; position: relative; left: 20px;">
		    <label for="amountOfMoney">金额</label>
		    <input type="text" class="form-control" id="amountOfMoney" name="money">
		  </div>
		  <div class="form-group" style="width: 400px;position: relative; left: 20px;">
		    <label for="tradeName">交易名称</label>
		    <input type="text" class="form-control" id="tradeName" name="name" value="${clue.company}-">
		  </div>
		  <div class="form-group" style="width: 400px;position: relative; left: 20px;">
		    <label for="expectedClosingDate">预计成交日期</label>
		    <input type="text" class="form-control" name="expectedDate" id="expectedClosingDate">
		  </div>
		  <div class="form-group" style="width: 400px;position: relative; left: 20px;">
		    <label for="stage">阶段</label>
		    <select id="stage"  name="stage" class="form-control">
		    	<option></option>
				<%--取出数据词典中的数据--%>
				<c:forEach items="${dictionaryType}" var="dictype">
					<%--如果是stage，就遍历它的value--%>
					<c:if test="${dictype.code eq 'stage'}">
						<c:forEach items="${dictype.dictionaryValueList}" var="dictionaryValue">
							<option value="${dictionaryValue.value}">${dictionaryValue.text}</option>
						</c:forEach>
					</c:if>
				</c:forEach>>
		    </select>
		  </div>
		  <div class="form-group" style="width: 400px;position: relative; left: 20px;">
		    <label for="activityName">市场活动源&nbsp;&nbsp;<a href="javascript:void(0);" onclick="queryActivitySource()" data-toggle="modal" data-target="#searchActivityModal" style="text-decoration: none;"><span class="glyphicon glyphicon-search"></span></a></label>
		    <input type="text" class="form-control" id="activityName" placeholder="点击上面搜索" readonly>
			  <input type="hidden" name="activityId" id="activityId"/>
		  </div>
		</form>
		
	</div>
	
	<div id="owner" style="position: relative; left: 40px; height: 35px; top: 50px;">
		记录的所有者：<br>
		<b>${clue.owner}</b>
	</div>
	<div id="operation" style="position: relative; left: 40px; height: 35px; top: 100px;">
		<input class="btn btn-primary" type="button" onclick="convert()" value="转换">
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input class="btn btn-default" type="button" onclick="cancel2333()" value="取消">
	</div>
</body>
<script>
	/**
	 * 对线索进行转换
	 * 只需要传入线索的id进入后台即可，后台根据线索id进行相应的处理
	 * */
	function convert() {
		var isCreateTransaction;
		if ($("#isCreateTransaction").prop("checked")) {
			isCreateTransaction = '1';
		} else {
			isCreateTransaction = '0';
		}
		var serialize = $('#transactionForm').serialize();
		alert(serialize);

		$.ajax({
			url:"/crm/workbench/clue/convert?"+ serialize,
			dataType:"json",
			data:{
				"clueId":"${clue.id}",
				"isCreateTransaction":isCreateTransaction
			},
			type:"get",
			success: function (data) {

				alert(data.msg);
				if (data.oK) {
					window.location = "/crm/toView/clue/index";
				}


			}
		})
	}

	/*线索转换页面点击为客户创建交易，进行市场活动源的查询*/
	function queryActivitySource() {
		$.ajax({
			url:"/crm/workbench/clue/selectActivitiesByClueId",
			dataType:"json",
			data:{
				"clueId":'${clue.id}'

			},
			type:"get",
			success:function (data) {
				$("#activityModal").empty();
				for (var i = 0; i < data.length; i++) {
					$("#activityModal").append("<tr>\n" +
							"\t\t\t\t\t\t\t\t<td><input type=\"radio\" name='activityId' value='"+data[i].id+"'/></td>\n" +
							"\t\t\t\t\t\t\t\t<td>"+data[i].name+"</td>\n" +
							"\t\t\t\t\t\t\t\t<td>"+data[i].startDate+"</td>\n" +
							"\t\t\t\t\t\t\t\t<td>"+data[i].endDate+"</td>\n" +
							"\t\t\t\t\t\t\t\t<td>"+data[i].owner+"</td>\n" +
							"\t\t\t\t\t\t\t</tr><input type='hidden' id='"+data[i].id+"' value='"+data[i].name+"'/>");
				}

			}
		})
	}
	/**
	 * 点击模态窗口中查询框，进行查询市场活动，按下回车键
	 * */
	$("#queryActvityByName").keypress(function (event) {
		if (event.keyCode == 13) {
			var val = $("#queryActvityByName").val();
			$.ajax({
				url:"/crm/workbench/clue/selectActivitiesByClueId",
				dataType:"json",
				data:{
					"clueId":'${clue.id}',
					"name":val

				},
				type:"get",
				success:function (data) {
					$("#activityModal").empty();
					for (var i = 0; i < data.length; i++) {
						$("#activityModal").append("<tr>\n" +
								"\t\t\t\t\t\t\t\t<td><input type=\"radio\" name='activityId' value='"+data[i].id+"'/></td>\n" +
								"\t\t\t\t\t\t\t\t<td>"+data[i].name+"</td>\n" +
								"\t\t\t\t\t\t\t\t<td>"+data[i].startDate+"</td>\n" +
								"\t\t\t\t\t\t\t\t<td>"+data[i].endDate+"</td>\n" +
								"\t\t\t\t\t\t\t\t<td>"+data[i].owner+"</td>\n" +
								"\t\t\t\t\t\t\t</tr><input type='hidden' id='"+data[i].id+"' value='"+data[i].name+"'/>");
					}
				}
			})
		}
		return false;
	});


	/**
	 * 添加市场活动源
	 * */
	function addActivitySource(){
		var val = $(":radio:checked").val();
	//	点击确定之后将选中的市场活动设置到交易表单中
		$("#activityName").val($("#"+val+"").val());
		$("#activityId").val(val);
		$('#searchActivityModal').modal('hide');

	}


	//预计成交日期插件
	$("#expectedClosingDate").datetimepicker({
		language:  "zh-CN",
		format: "yyyy-mm-dd",//显示格式
		minView: "month",//设置只显示到月份
		initialDate: new Date(),//初始化当前日期
		autoclose: true,//选中自动关闭
		todayBtn: true, //显示今日按钮
		clearBtn : true,
		pickerPosition: "bottom-left"
	});

	function cancel2333() {
		window.location = "/crm/toView/clue/index";
	}

</script>
</html>