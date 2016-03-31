<%@ page language="java" contentType="text/html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8"/>
		<title>Accounts</title>
		<meta name="description" content="" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		<link rel="stylesheet" href="css/main.css" />
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" />
	</head>
	<body>
		<div class="clearfix"></div>
		<div class="main-wrap">
			<section class="main-cont nomargin">
				<div class="blue-nav">
					<div class="blue-pad">
						<div class="dropdown referal-drop pull-left">
							<a href="#">
								 Accounts 
							</a>
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
				<div class="clearfix"></div>
				<div class="tab-content">
					<div class="tab-pane active" id="open">
						<div class="collapse in" id="filter">
							<div class="fileter-wrap">
								<form class="form-horizontal" id="strategicForm" method="post" >
									<table class="table">
										<tbody>
											<tr>
												<td style="width:400px">
													<div class="form-group">
														<label class="col-sm-4 control-label">Stategic Initiative Type</label>
														<div class="col-sm-8">
															<select class="input-sm"  name="stategicInitiativeType" >
																<option value="">--Select Record Type--</option>
																<option value="${selectedOptionValue}">Strategic Goal</option>
															</select>
														</div>
													</div>
												</td>
												<td></td>
												<td></td>
											</tr>
										</tbody>
									</table>
									<table class="table" style="border-bottom: #2A6892 2px solid;margin-bottom: 15px;">
										<tbody>
											<tr>
												<td>
													<div class="form-group">
														<label class="col-sm-3 control-label">Strategic Goal Name</label>
														<div class="col-sm-9">
															<input type="text" name="parentProgramName" id="parentProgramName" value="${parentProgramName}" style="display:none"/>
															<input type="text" name="parentProgramId" id="parentProgramId" value="${parentProgramId}" style="display:none" />
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label">Goal Note</label>
														<div class="col-sm-9">
															${goalNote}
															<input type="text" name="goalNote" id="goalNote" value="${goalNote}" style="display:none"/>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label">Goal Type</label>
														<div class="col-sm-9">
															${goalType}
															<input type="text" name="goalType" id="goalType" value="${goalType}" style="display:none"/>
														</div>
													</div>
												</td>
												<td>
													<div class="form-group">
														<label class="col-sm-3 control-label">Parent Program</label>
														<div class="col-sm-9">
															${parentProgram}
															<input type="text" name="parentProgram" id="parentProgram" value="${parentProgram}" style="display:none"/>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label">Goal Status</label>
														<div class="col-sm-9">
															${goalStatus}
															<input type="text" name="goalStatus" id="goalStatus" value="${goalStatus}" style="display:none"/>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label">Start Date</label>
														<div class="col-sm-9">
															${startDate}
															<input type="text" name="startDate" id="startDate" value="${startDate}" style="display:none"/>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label">Completed Date</label>
														<div class="col-sm-9">
															${completedDate}
															<input type="text" name="completedDate" id="completedDate" value="${completedDate}" style="display:none"/>
														</div>
													</div>
												</td>
												
											</tr>
										</tbody>
									</table>
								</form>
								<form class="form-horizontal" id="accountForm" method="post" >
									<table class="table">
										<tbody>
											<tr>
												<td class="w30p">
													<div class="form-group">
														<label class="col-sm-3 control-label">Account Name</label>
														<div class="col-sm-9">
															<input type="text" name="accountName" />
															<input type="hidden" id="contextPath" value="${pageContext.request.contextPath}" />
															<input type="hidden" name="pageNo" id="pageNo" value="1" />
															<input type="hidden" name="noOfRecords" id="noOfRecords" value="500" />
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label">Brand Name</label>
														<div class="col-sm-9">
															<input type="text" name="brandName"  id="brandName"  />
															<a class="fa fa-search" href="#" onclick="loadLookupData('Brand','')"></a>
															<a class="fa fa-remove" href="#" onclick="clearFilter('Brand')"></a>
															<input type="text" name="brandId" id="brandId" style="display:none" />
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label">Primary Channel</label>
														<div class="col-sm-9">
															<input type="text"  name="primaryChannel" id="primaryChannel"/>
															<a class="fa fa-search" href="#" onclick="loadLookupData('PrimaryChannel','')"></a>
															<a class="fa fa-remove" href="#" onclick="clearFilter('PrimaryChannel')"></a>
															<input type="text"  name="primaryChannelId" id="primaryChannelId"  style="display:none"/>
														</div>
													</div>
												</td>
												<td class="w30p">
													<div class="form-group">
														<label  class="col-sm-3 control-label">Market/Region</label>
														<div class="col-sm-9">
															<input type="text"  name="market" />
														</div>
													</div>
													<div class="form-group">
														<label  class="col-sm-3 control-label">Product Name</label>
														<div class="col-sm-9">
															<input type="text"  name="productName" id="productName" />
															<a class="fa fa-search" href="#" onclick="loadLookupData('Product','')"></a>
															<a class="fa fa-remove" href="#" onclick="clearFilter('Product')"></a>
															<input type="text"  name="productId" id="productId"  style="display:none"/>
														</div>
													</div>
													<div class="form-group">
														<label  class="col-sm-3 control-label">Account Status</label>
														<div class="col-sm-9">
															<select class="input-sm"  name="accountStatus" >
																<option value="-1">-- Select Account Status --</option>
																<option value="true">Active</option>
																<option value="false">Inactive</option>
															</select>
														</div>
													</div>
												</td>       
												<td class="w30p">
													<div class="form-group">
														<label  class="col-sm-3 control-label">Parent Account</label>
														<div class="col-sm-9">
															<input type="text" name="parentAccount" id="parentAccount"  />
															<a class="fa fa-search" href="#" onclick="loadLookupData('Account','')"></a>
															<a class="fa fa-remove" href="#" onclick="clearFilter('Account')"></a>
															<input type="text" name="parentAccountId" id="parentAccountId"  style="display:none" />
														</div>
													</div>
													<div class="form-group">
														<label  class="col-sm-3 control-label">Type</label>
														<div class="col-sm-9">
															<select class="input-sm"  name="type" >
																<option value="-1">-- Select Type --</option>
																<option value="Customer - Direct">Customer - Direct</option>
																<option value="Proposal">Proposal</option>
																<option value="Customer - Channel">Customer - Channel</option>
															</select>
														</div>
													</div>
													<div class="form-group" style="display:none">
														<label  class="col-sm-3 control-label">Brand Status</label>
														<div class="col-sm-9">
															<select class="input-sm"  name="brandStatus" >
																<option value="-1">-- Select Brand Status --</option>
																<option value="Active">Active</option>
																<option value="Inactive">Inactive</option>
															</select>
														</div>
													</div>
												</td>   
											</tr>
										</tbody>
									</table>
								</form>
							</div>
						</div>
						<div class="darkblue-nav">
							<div class="darkpad">
								<button class="btn btn-xs btn-aquagrey mr5" onclick="runQuery();">Run Query</button>
								<button class="btn btn-xs btn-aquagrey mr5" onclick="addSelectedAccounts()">Add Selected Accounts</button>
								<button class="btn btn-xs btn-aquagrey mr5" onclick="addAllAccounts();">Add All Accounts</button>
								<button class="btn btn-xs btn-aquagrey mr5" onclick="resetSearch();">Reset Search</button>
								<button class="btn btn-xs btn-aquagrey mr5" onclick="reviewGoal()">Review Goals</button>
								<div class="clearfix"></div>
							</div>
						</div>
						<div class="dash-height" id="searchResponse">
							<input id="totalRecords" type="hidden" value=" ${accountListSize}" />
								<div id="table_wrapper" style="margin-right:17px;">
								  	<table class="table table-bordered table-fixed aqua-table tele-icons" style="margin-bottom:0px;">							
									  	<thead>  	
									    	<th class="dycol1"></th>
									    	<th class="dycol2">Name</th>
									    	<th class="dycol3">Market</th>
									    	<th class="dycol4">Phone</th>
									    	<th class="dycol5">Record Type</th>
								    	</thead>
								  	</table>
							  	</div>
	  							<div id="tbody" style="overflow-y:scroll;overflow-x:hidden;">
									<table class="table table-bordered table-fixed aqua-table tele-icons" id="this-table">
										<c:if test="${accountListSize==0}">
											<tr>
												<td colspan="5">No records found for the search.</td>
											</tr>
										</c:if>
										<c:forEach var="account" items="${accountList}">
											<tr>
												<td class="dycol1"><input type="checkbox" name="s_account" class="mr10 pull-left" value="${account.id}" />
												<i class="icon icon-exlametry mt4 ml20"></i></td>
												<td class="dycol2">${account.name}</td>
												<td class="dycol3">${account.market}</td>
												<td class="dycol4">${account.phone}</td>
												<td class="dycol5">${account.recordtype}</td>
											</tr>
										</c:forEach>
									</table>
								</div>
							</div>
						</div>
						<div class="clearfix spacer10"></div>
						<div class="foot-control">
							<div class="pull-right mr10 custy-pagey">
								<button class="btn btn-lgrey btn-sm btn-xs  mr5" id="btnNext" onclick="prevPage()">Prev</button>
								<button class="btn btn-lgrey btn-sm btn-xs " id="btnPrev" onclick="nextPage()">Next</button>
							</div>
							<div class="pull-right mr10 custy-pagey">
								<select class="input-sm" id="pageSize" onchange="currentPage()">
									<option>500</option>
									<option>1000</option>
								</select>
							</div>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>
			</section>
		</div>
		<div class="modal fade bluetheme" id="referal" data-backdrop="static" data-keyboard="false">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
					<i class="fa fa-3x fa-spinner"></i>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade bluetheme" id="lookupPopup">
			<div class="modal-dialog" style="width:400px">
				<div class="modal-content">
					<div class="modal-header clearfix">
						<i class="fa fa-2x fa-times-circle-o pull-right" data-dismiss="modal" aria-hidden="true" style="padding: 1px;color: white;"></i>
						<h4 class="modal-title" id="myModalLabel"></h4>
					</div>
					<div class="modal-body grey-bg" id="lookupContainer">
						
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade bluetheme" id="reviewPopup">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header clearfix">
						<i class="fa fa-2x fa-times-circle-o pull-right" data-dismiss="modal" aria-hidden="true" style="padding: 1px;color: white;"></i>
						<h4 class="modal-title" id="myModalLabel">Review Goal</h4>
					</div>
					<div class="modal-body grey-bg" id="reviewContainer">
						
					</div>
					<div class="modal-footer grey-bg nomargin">
						<div class="pull-right mr10 custy-pagey">
							<input type="hidden" name="pageNoReview" id="pageNoReview" value="1" />
							<input type="hidden" name="noOfRecordsReview" id="noOfRecordsReview" value="10" />
							<button class="btn btn-lgrey btn-sm btn-xs  mr5" id="btnNextReview" onclick="prevPageReview()">Prev</button>
							<button class="btn btn-lgrey btn-sm btn-xs " id="btnPrevReview" onclick="nextPageReview()">Next</button>
						</div>
						<div class="pull-right mr10 custy-pagey">
							<select class="input-sm" id="pageSizeReview" onchange="currentPageReview()" style="padding: 0px;height: 24px;">
								<option>10</option>
								<option>100</option>
							</select>
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="modal hide" id="pleaseWaitDialog"><div class="modal-body" style="text-align:center"><div class="spinner-div"><div class="fa fa-spinner fa-pulse fa-3x"></div><div style="padding-top:10px;"><b>Please wait...</b></div></div></div></div>
		<script src="js/vendor/jquery.js"></script>
		<script src="js/vendor/bootstrap.js"></script>
		<script src="js/vendor/jquery-ui.js"></script>
		<script src="js/vendor/floatThead.js"></script>
		<script src="js/vendor/bootstrap-notify.min.js"></script>
		<script src="js/jquery-ui-timepicker-addon.js"></script>
		<script src="js/main.js"></script>
		<script src="js/account.js"></script>
		<script>
			/*var $demo1 = $('#this-table');
			$demo1.floatThead({
				scrollContainer: function($table){
					return $table.closest('.dash-height');
				}
			});*/
			
			
		</script>
	</body>
</html>
